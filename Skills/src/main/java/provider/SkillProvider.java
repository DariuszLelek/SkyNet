/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package provider;


import dao.WordDao;
import file.FileUtility;
import hibernate.provider.DaoProvider;
import org.apache.log4j.Logger;
import skill.EmptySkill;
import skill.Skill;
import utilities.StringUtilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SkillProvider implements SkillProvide {
  private static final Logger logger = Logger.getLogger(SkillProvider.class);

  private static final String SKILLS_DIRECTORY = "skills";
  private static final String SKILLS_LOCATION_PACKAGE = SKILLS_DIRECTORY + ".";
  private final String SKILLS_LOCATION =
      getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
          + SKILLS_DIRECTORY + "/";

  private final Map<String, String> skillSynonymCache = new HashMap<>();
  private final FileUtility fileUtility = new FileUtility();

  private final DaoProvider<WordDao> wordProvider = new DaoProvider<>(WordDao.class);

  SkillProvider() {
    loadSkills();
  }

  @Override
  public Skill getSkill(String skillName) {
    final Object object = tryGetObjectFromConstructor(tryGetClassConstructor(tryGetSkillClass(skillName)));
    return Skill.class.isInstance(object) ? (Skill) object : new EmptySkill();
  }

  @Override
  public boolean hasSkill(String skillName) {
    return skillSynonymCache.keySet().stream().anyMatch(synonym -> synonym.equalsIgnoreCase(skillName));
  }

  private Object tryGetObjectFromConstructor(Constructor<?> constructor){
    Object object = new Object();

    try {
      if(constructor != null){
        object = constructor.newInstance();
      }
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      logger.error("tryGetObjectFromConstructor()", e);
    }

    return object;
  }

  private Constructor<?> tryGetClassConstructor(Class<?> clazz ){
    Constructor<?> constructor = null;

    try {
      constructor = clazz.getConstructor();
    } catch (NoSuchMethodException e) {
      logger.error("tryGetClassConstructor()", e);
    }

    return constructor;
  }

  private Class<?> tryGetSkillClass(final String skillName){
    Class<?> clazz = Object.class;

    try {
      clazz = Class.forName(SKILLS_LOCATION_PACKAGE + getClassNameFromSkillName(skillName));
    } catch (ClassNotFoundException e) {
      logger.error("tryGetSkillClass() - Skill [" + skillName + "] not found in location: "
          + SKILLS_LOCATION + " (package " + SKILLS_LOCATION_PACKAGE + ")");
    }

    return clazz;
  }

  private String getClassNameFromSkillName(String skillName){
    String skillClassName = skillName.toLowerCase();
    return skillClassName.substring(0, 1).toUpperCase() + skillClassName.substring(1);
  }

  private void loadSkills(){
    insertSkillsToSynonymCache(fileUtility.getFileNamesInDirectory(SKILLS_LOCATION));
  }

  private void insertSkillsToSynonymCache(Collection<String> skillCLasses) {
    skillSynonymCache.clear();

    skillCLasses.forEach(skillClass -> {
      insertToCache(skillClass.toLowerCase(), skillClass);
      getSynonymsForSkillName(skillClass).forEach(synonym -> insertToCache(synonym, skillClass));
    });
  }

  private void insertToCache(String skillName, String skillClass){
    if(!skillName.isEmpty() && StringUtilities.isAlphabetic(skillName)){
      skillSynonymCache.put(skillName, skillClass);
    }
  }

  private Collection<String> getSynonymsForSkillName(String skillName){
    WordDao word = wordProvider.getByUniqueKey("word", skillName);
    return word != null ? word.getSynonymsList() : new ArrayList<>();
  }
}

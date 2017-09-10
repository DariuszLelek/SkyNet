package skill.provider;

import dictionary.DictionaryFactory;
import org.apache.log4j.Logger;
import skill.model.Skill;
import utility.FileUtility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SkillProvider implements SkillProvide {
  private static final Logger logger = Logger.getLogger(SkillProvider.class);

  private static final String SKILLS_LOCATION_PACKAGE = "skill.";
  private final String SKILLS_LOCATION = getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "skill/";

  private final Map<String, String> skillSynonymCache = new HashMap<>();
  private final FileUtility fileUtility = new FileUtility();

  SkillProvider() {
    loadSkills();
  }

  @Override
  public Skill getSkill(String skillName) {
    Object object = tryGetObjectFromConstructor(tryGetClassConstructor(tryGetSkillClass(skillName)));

    Skill skill = new Skill();
    if(object instanceof Skill){
      skill = (Skill) object;
    }

    return skill;
  }

  @Override
  public boolean hasSkill(String skillName) {
    return skillSynonymCache.keySet().stream().anyMatch(skill -> skill.equalsIgnoreCase(skillName));
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
      logger.error("tryGetSkillClass()", e);
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

  private void insertSkillsToSynonymCache(Collection<String> skills) {
    skillSynonymCache.clear();

    skills.forEach(skill -> {
      skillSynonymCache.put(skill.toLowerCase(), skill);
      getSynonymsForSkillName(skill).forEach(skillSynonym -> skillSynonymCache.put(skillSynonym, skill));
    });
  }

  private Collection<String> getSynonymsForSkillName(String skillName){
    return DictionaryFactory.getWordProvider().getWord(skillName).getSynonymsList();
  }
}

/*
 * Created by Dariusz Lelek on 9/16/17 2:28 AM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import skill.SkillProvider;

import java.util.Collection;

public class SkillFactory {
  private static final SkillProvider skillProvider = new SkillProvider();

  public static Skill getSkill(String skillName) {
    return skillProvider.getSkill(skillName);
  }

  public static boolean hasSkill(String skillName) {
    return skillProvider.hasSkill(skillName);
  }

  public static Collection<String> getSkillNames() {
    return skillProvider.getSkills();
  }
}

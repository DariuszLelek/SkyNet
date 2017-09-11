/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package provider;


import skill.Skill;

public interface SkillProvide {
  Skill getSkill(String skillName);
  boolean hasSkill(String skillName);
}

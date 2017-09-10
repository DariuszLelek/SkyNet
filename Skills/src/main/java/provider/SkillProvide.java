package provider;

import skill.Skill;

public interface SkillProvide {
  Skill getSkill(String skillName);
  boolean hasSkill(String skillName);
}

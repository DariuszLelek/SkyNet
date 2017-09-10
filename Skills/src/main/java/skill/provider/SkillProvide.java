package skill.provider;

import skill.model.Skill;

public interface SkillProvide {
  Skill getSkill(String skillName);
  boolean hasSkill(String skillName);
}

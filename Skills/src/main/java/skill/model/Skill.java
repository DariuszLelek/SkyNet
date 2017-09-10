package skill.model;

import skill.provider.SkillPriority;

public class Skill{
  private SkillPriority priority = SkillPriority.NONE;
  private Instruction instruction = Instruction.EMPTY;

  public Skill() {
  }

  void setPriority(SkillPriority priority) {
    this.priority = priority;
  }

  public Instruction getInstruction() {
    return instruction;
  }

  public void setInstruction(Instruction instruction) {
    this.instruction = instruction;
  }

  public int getPriorityValue() {
    return priority.getValue();
  }

  public void process(){

  }

  public String getInfo() {
    return this.getClass().getName() + ", priority: " + priority.getValue();
  }
}

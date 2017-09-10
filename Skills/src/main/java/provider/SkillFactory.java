package provider;

public class SkillFactory {
  private final static SkillProvider skillProvider = new SkillProvider();

  public static SkillProvider getSkillProvider() {
    return skillProvider;
  }
}

import skill.provider.SkillFactory;
import skill.Add;

public class TestClass {


  public static void main(String[] args) {
    Add add = new Add();


    //System.out.println(add.getInfo());

    System.out.println(SkillFactory.getSkillProvider().hasSkill("add"));
    System.out.println(SkillFactory.getSkillProvider().hasSkill("iNSert"));
    SkillFactory.getSkillProvider().getSkill("add");


    //HibernateUtility.stopConnectionProvider();
  }
}

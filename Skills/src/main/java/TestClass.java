/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

import config.DataBaseConfig;
import hibernate.HibernateUtilityFactory;
import skill.SkillFactory;
import skills.Add;

public class TestClass {


  public static void main(String[] args) {
    Add add = new Add();

    //System.out.println(add.getInfo());

    System.out.println(SkillFactory.hasSkill("maiL"));
    System.out.println(SkillFactory.hasSkill("Remind"));
    SkillFactory.getSkill("add");

    System.out.println(SkillFactory.getSkillNames());

    HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD).closeSessionFactory();
  }
}

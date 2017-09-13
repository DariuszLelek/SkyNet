/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

import config.DataBaseConfig;
import hibernate.HibernateUtilityFactory;
import provider.SkillFactory;
import skills.Add;

public class TestClass {


  public static void main(String[] args) {
    Add add = new Add();

    //System.out.println(add.getInfo());

    System.out.println(SkillFactory.getSkillProvider().hasSkill("email"));
    System.out.println(SkillFactory.getSkillProvider().hasSkill("iNSert"));
    SkillFactory.getSkillProvider().getSkill("add");

    HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD).closeSessionFactory();
  }
}

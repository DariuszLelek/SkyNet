/*
 * Created by Dariusz Lelek on 9/10/17 9:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package provider;

public class SkillFactory {
  private final static SkillProvider skillProvider = new SkillProvider();

  public static SkillProvider getSkillProvider() {
    return skillProvider;
  }
}

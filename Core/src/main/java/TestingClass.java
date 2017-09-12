/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import config.DataBaseSchema;
import hibernate.HibernateUtilityFactory;
import process.ProcessorFactory;
import execute.ProcessableExecutor;
import process.message.MessageCreator;
import process.message.Message;
import process.message.MessageType;
import processable.Processable;
import skill.Skill;

public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

    Processable p = new Skill();

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.create("remove add", MessageType.VOICE);

    ProcessorFactory.getMessageProcessor().process(m1);

    Thread.sleep(5000);

    ProcessableExecutor.stopExecutorThread();

    HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).closeSessionFactory();
  }
}

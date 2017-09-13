/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import config.DataBaseSchema;
import hibernate.HibernateUtilityFactory;
import process.ProcessorFactory;
import execute.ProcessableExecutor;
import process.message.Message;
import process.message.MessageCreator;
import process.message.MessageType;


public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.create("email remind", MessageType.VOICE);
    Message m2 = messageCreator.create("send email to daro daro", MessageType.VOICE);
    Message m3 = messageCreator.create("remind verb me man add remove", MessageType.VOICE);

    //ProcessorFactory.getMessageProcessor().process(m1);
    ProcessorFactory.getMessageProcessor().process(m2);
    //ProcessorFactory.getMessageProcessor().process(m3);

    Thread.sleep(5000);

    //ProcessableExecutor.stopExecutor();

    //HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).closeSessionFactory();
  }
}

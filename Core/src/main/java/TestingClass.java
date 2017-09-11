/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import config.DataBaseSchema;
import hibernate.HibernateUtilityFactory;
import processing.ProcessorFactory;
import processing.executor.ProcessableExecutor;
import processing.message.MessageCreator;
import processing.message.Message;
import processing.message.MessageType;

public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.getMessage("remove add", MessageType.VOICE);

    ProcessorFactory.getMessageProcessor(MessageType.VOICE).process(m1);

    Thread.sleep(5000);

    ProcessableExecutor.stopExecutorThread();

    HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).closeSessionFactory();
  }
}

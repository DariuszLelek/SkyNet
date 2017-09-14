/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import dao.WordDAO;
import hibernate.preserver.DAOPreserver;
import hibernate.preserver.Preserver;
import hibernate.provider.DAOProvider;
import hibernate.provider.Provider;
import process.ProcessorFactory;
import process.message.Message;
import process.message.MessageCreator;
import process.message.MessageType;

import java.util.Collection;


public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.create("email remind", MessageType.VOICE);
    Message m2 = messageCreator.create("send email to DARIUS lele", MessageType.VOICE);
    Message m3 = messageCreator.create("remind verb me man add remove", MessageType.VOICE);

    ProcessorFactory.getMessageProcessor().process(m2);

    Thread.sleep(5000);

    //ProcessableExecutor.stopExecutor();

    //HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.DICTIONARY).closeSessionFactory();
  }
}

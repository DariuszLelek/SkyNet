/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import dao.TimeDAO;
import dao.WordDAO;
import file.FileUtility;
import hibernate.preserver.DAOPreserver;
import hibernate.preserver.Preserver;
import hibernate.provider.DAOProvider;
import hibernate.provider.Provider;
import process.ProcessorFactory;
import process.message.Message;
import process.message.MessageCreator;
import process.message.MessageType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

//    MessageCreator messageCreator = new MessageCreator();
//
//    Message m1 = messageCreator.create("", MessageType.VOICE);
//    Message m2 = messageCreator.create("send email to DARIUS lele", MessageType.VOICE);
//    Message m3 = messageCreator.create("remind verb me man add remove", MessageType.VOICE);
//
//    ProcessorFactory.getMessageProcessor().process(m1);
//
//    Thread.sleep(5000);
//
//    DAOPreserver<TimeDAO> timeSave = new DAOPreserver<>();
//
//    TimeDAO t = new TimeDAO();
//    t.setName("day");
//
//
//
//
//    timeSave.save(t);

    Collection<String> fileContent = new ArrayList<>();

    try {
      fileContent = FileUtility.getFileLines(new File("C:\\development\\SkyNet\\Model\\raw\\test.txt"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    DAOPreserver<WordDAO> wordSaver = new DAOPreserver<>();

    Collection<WordDAO> words = new ArrayList<>();

    if(!fileContent.isEmpty()){
      for(String line : fileContent){
        if(!line.isEmpty()){
          WordDAO w = new WordDAO();
          String[] chunks = line.split("##");

          if(chunks.length>0){
            w.setWord(chunks[0]);
            if(chunks.length>1){
              w.setWordClassString(chunks[1]);
              if(chunks.length>2){
                w.setDescription(chunks[2]);
              }
            }
          }else{
            w.setWord(line);
          }
          words.add(w);

          //wordSaver.save(w);
        }
      }
    }

    wordSaver.save(words);


    //ProcessableExecutor.stopExecutor();

    //HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.DICTIONARY).closeSessionFactory();
  }
}

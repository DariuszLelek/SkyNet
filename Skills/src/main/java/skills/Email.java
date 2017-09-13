/*
 * Created by Dariusz Lelek on 9/13/17 3:14 PM
 * Copyright (c) 2017. All rights reserved.
 */

package skills;

import credentials.Credentials;
import dictionary.WordClass;
import helper.HelperFactory;
import helper.HelperType;
import process.priority.Priority;
import skill.Skill;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class Email extends Skill {

  private Session mailSession;

  private final static String EMAIL_HOST = "smtp.gmail.com";
  private final static String EMAIL_TRANSPORT = "smtp";
  private final static String EMAIL_SUBJECT = "You got email from SkyNet";
  private final static String EMAIL_FOOTER = "<br><br>Sent by SkyNet in behalf of "
      + Credentials.ROOT_USER_NAME.getValue() + ".";

  public Email() {
    super(Priority.LOW, new ArrayList<WordClass>(){{
    }});
  }

  @Override
  public boolean process() {
    if(hasValidInstruction()){
      String recipient = HelperFactory.getHelper(HelperType.EMAIL).getValid(getInstruction().getQueue());
      // TODO get from content helper
      String content = "some content";

      if(recipient.isEmpty()){
        logFail("Failed to send Email - Cannot get valid recipient.");
        disable();
        return false;
      }else{
        setMailServerProperties();
        if(trySendMail(recipient, content)){
          logSuccess("Email sent to:" + recipient + ", content:" + content + ".");
          return true;
        }else{
          disable();
          return false;
        }
      }
    }
    return false;
  }

  private boolean trySendMail(String recipient, String content){
    try {
      sendEmail(getMessage(recipient, content));
      return true;
    } catch (javax.mail.MessagingException e) {
      logFail("Failed to send Email.", e);
      return false;
    }
  }

  private void setMailServerProperties()
  {
    Properties emailProperties = System.getProperties();

    emailProperties.put("mail.smtp.host", "smtp.gmail.com");
    emailProperties.put("mail.smtp.socketFactory.port", "587");
    emailProperties.put("mail.smtp.socketFactory.class", "javax.net.SocketFactory");
    emailProperties.put("mail.smtp.port", "586");
    emailProperties.put("mail.smtp.auth", "true");
    emailProperties.put("mail.smtp.ssl.enable", "false");
    emailProperties.put("mail.smtp.starttls.enable", "true");
    emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    mailSession = Session.getDefaultInstance(emailProperties, null);
  }

  private MimeMessage getMessage(final String recipient, final String content) throws AddressException, javax.mail.MessagingException
  {
    MimeMessage emailMessage = new MimeMessage(mailSession);
    emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    emailMessage.setSubject(EMAIL_SUBJECT);

    // TODO refactor to use different body types
    emailMessage.setContent(content + EMAIL_FOOTER, "text/html");
    return emailMessage;
  }

  private void sendEmail(MimeMessage emailMessage) throws AddressException, javax.mail.MessagingException
  {
    Transport transport = mailSession.getTransport(EMAIL_TRANSPORT);
    transport.connect(EMAIL_HOST, Credentials.MAIL_PROCESSOR_USER.getValue(),
        Credentials.MAIL_PROCESSOR_PASSWORD.getValue());

    transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
    transport.close();
  }

}

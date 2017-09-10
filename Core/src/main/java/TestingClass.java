import hibernate.HibernateUtility;
import processing.ProcessorFactory;
import processing.executor.ProcessableExecutor;
import processing.message.MessageCreator;
import processing.message.model.Message;
import processing.message.model.MessageType;

public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.getMessage("remove add", MessageType.VOICE);

    ProcessorFactory.getMessageProcessor(MessageType.VOICE).process(m1);

    Thread.sleep(5000);

    ProcessableExecutor.stopExecutorThread();

    HibernateUtility.stopConnectionProvider();
  }
}

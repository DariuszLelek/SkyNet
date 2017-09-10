import dictionary.DictionaryFactory;
import hibernate.HibernateUtility;
import processing.ProcessorFactory;
import processing.executor.ProcessableExecutor;

public class TestingClass {
  public static void main(String[] args) throws InterruptedException {
   // ProcessableExecutor pe = new ProcessableExecutor();

    ProcessorFactory.getMessageProcessor().processVoiceMessage("add some things to the table add then");
    ProcessorFactory.getMessageProcessor().processVoiceMessage("remove some");
    ProcessorFactory.getMessageProcessor().processVoiceMessage("yes some");

    Thread.sleep(5000);

    ProcessableExecutor.stopExecutorThread();

    HibernateUtility.stopConnectionProvider();
    //WordProcessor wp;
  }
}

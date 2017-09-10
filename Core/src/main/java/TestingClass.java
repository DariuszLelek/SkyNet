import processing.ProcessorFactory;
import processing.executor.ProcessableExecutor;

public class TestingClass {
  public static void main(String[] args) throws InterruptedException {
   // ProcessableExecutor pe = new ProcessableExecutor();

    ProcessorFactory.getMessageProcessor().processText("add some");
    ProcessorFactory.getMessageProcessor().processText("remove some");
    ProcessorFactory.getMessageProcessor().processText("yes some");

    Thread.sleep(5000);

    ProcessableExecutor.stopExecutorThread();


    //WordProcessor wp;
  }
}

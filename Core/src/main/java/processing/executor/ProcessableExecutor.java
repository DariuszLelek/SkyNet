package processing.executor;

import org.apache.log4j.Logger;
import processing.Processable;
import processing.message.Message;

import java.util.ArrayList;
import java.util.List;

public class ProcessableExecutor extends Executor {
  final static Logger logger = Logger.getLogger(ProcessableExecutor.class);

  private final static List<Processable> pendingProcessables = new ArrayList<>();
  private final static List<Processable> runningProcessables = new ArrayList<>();

  private static boolean running = true;

  private static final int PENDING_CHECK_DELAY = 1 * 1000;
  private static final int PROCESS_NEXT_DELAY = 100;

  static{
    startExecutorThread();
  }

  public synchronized static void addProcessable(Processable processable){
    pendingProcessables.add(processable);
  }

  private static void startExecutorThread(){
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while(running){
          synchronized (pendingProcessables){
            if(pendingProcessables.size() > 0){
              Processable pendingProcessable = getHighestPriorityProcessable();
              removeProcessable(pendingProcessable);
              pendingProcessable.startProcessing();
              try {
                Thread.sleep(PROCESS_NEXT_DELAY);
              } catch (InterruptedException e) {
                logger.error("startExecutorThread()" ,e);
              }
            }else{
              logger.debug("No pending processables, sleeping " + PENDING_CHECK_DELAY);
              try {
                Thread.sleep(PENDING_CHECK_DELAY);
              } catch (InterruptedException e) {
                logger.error("startExecutorThread()" ,e);
              }
            }
          }
        }
      }
    });

    thread.start();
  }

  public static void stopExecutorThread(){
    running = false;
  }

  private static Processable getHighestPriorityProcessable(){
    Processable highestPriorityProcessable = new Message();

    for(Processable processable : getPendingProcessables()){
      if(processable.getPriority() > highestPriorityProcessable.getPriority() &&
          processable.canBeProcessed()){
        highestPriorityProcessable = processable;
      }
    }

    return highestPriorityProcessable;
  }

  private synchronized static List<Processable> getPendingProcessables(){
    return pendingProcessables;
  }

  private synchronized static void removeProcessable(Processable processable){
    if(pendingProcessables.contains(processable)){
      pendingProcessables.remove(processable);
    }
  }
}

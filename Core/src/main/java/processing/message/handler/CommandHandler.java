package processing.message.handler;

import processing.message.Message;
import processing.message.handler.Command.Add;
import processing.message.predefined.Command;

import java.util.List;

public class CommandHandler extends Handler {

  @Override
  public boolean canHandle() {
    // TODO
    return true;
  }

  @Override
  public void handle(Message message) {
    Command command = Command.getByName(message.getHeader());
    List<String> content = message.getContent();

    if(command == Command.ADD){
      new Add(content);
    }else if(command == Command.REMOVE){

    }

  }
}

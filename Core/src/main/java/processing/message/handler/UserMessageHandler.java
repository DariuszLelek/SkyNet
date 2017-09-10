package processing.message.handler;

import org.apache.log4j.Logger;
import processing.ProcessorFactory;
import processing.message.model.Message;
import skill.provider.SkillFactory;

public class UserMessageHandler extends Handler {
  final static Logger logger = Logger.getLogger(UserMessageHandler.class);

  @Override
  public boolean canHandle() {
    // TODO
    return true;
  }

  @Override
  public void handle(Message message) {
    // TODO check against different actions Skills, other actions.

    String skill = message.getWords().peek().getWord();

    if(SkillFactory.getSkillProvider().hasSkill(skill)){


//      ProcessorFactory.getSkillProcessor()
//          .processSkill(SkillFactory.getSkillProvider().getSkill(skill));
    }

    message.stopProcessing();
  }
}

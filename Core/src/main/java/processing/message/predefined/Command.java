package processing.message.predefined;

public enum Command {
  EMPTY,

  ADD,
  REMOVE,
  FIND,
  REMIND;

  public static Command getByName(String name){
    for(Command command : values()){
      if(command.name().equalsIgnoreCase(name)){
        return command;
      }
    }
    return EMPTY;
  }
}

package link.webarata3.fx.oekaki.command;

import java.util.ArrayList;
import java.util.List;

public class CommandUnit {
    private List<Command> commandList;

    public CommandUnit() {
        commandList = new ArrayList<Command>();
    }

    public void add(Command command) {
        commandList.add(command);
    }

    public void execute() {
        for (Command command : commandList) {
            command.execute();
        }
    }
}

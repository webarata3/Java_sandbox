package link.webarata3.fx.oekaki.command;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<CommandUnit> commandUnitList;

    public CommandHistory() {
        commandUnitList = new ArrayList<CommandUnit>();
    }

    public void add(CommandUnit commandUnit) {
        commandUnitList.add(commandUnit);
    }

    public void execute() {
        for (CommandUnit commandUnit : commandUnitList) {
            commandUnit.execute();
        }
    }

    public void undo() {
        if (!commandUnitList.isEmpty()) {
            commandUnitList.remove(commandUnitList.size() - 1);
        }
    }
}

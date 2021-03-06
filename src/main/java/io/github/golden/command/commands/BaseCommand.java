package io.github.golden.command.commands;

import org.bukkit.entity.Player;

import io.github.golden.chat.ChatComponent;
import io.github.golden.chat.ChatUtils;

// TODO: Implement automatic command listener addition for new commands
// (in the commands list)
// and TODO: Implement automatic command completer addition
// (in the creation of a completion component)
// TODO: automatiaclly add the new command in the list of possible first subcommands completion
// (currently done in CommandCompleter line 17)
public abstract class BaseCommand {
    
    protected String commandName;
    protected int requiredArgsLenght;
    protected String requiredPermission = "sulfur.user";

    protected String usage;

    protected abstract void onCommand(Player executor, String... args);

    // this is the method that has to be called
    // in order to execute a subclass's command
    public void invoke(Player executor, String... args) {
        if(args.length < requiredArgsLenght) { 
            ChatUtils.sendMessage(executor,
                new ChatComponent("Correct usage: '%s'", usage));
            return;
        }

        if(!executor.hasPermission(requiredPermission)) {
            ChatUtils.sendMessage(executor, 
                new ChatComponent("Not enough permissions."));
            return;    
        }

        // if the conditions are met, we can execute the command
        onCommand(executor, args);
    }

    public String getCommandName() {
        return commandName;
    }

    public String getPermission() {
        return requiredPermission;
    }

}

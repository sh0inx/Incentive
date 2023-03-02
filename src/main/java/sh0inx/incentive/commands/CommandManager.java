package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sh0inx.incentive.Incentive;

public class CommandManager implements CommandExecutor{

    AboutCommand aboutCommand = new AboutCommand();
    ProfileCommand profileCommand = new ProfileCommand();
    HelpCommand helpCommand = new HelpCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        try {
            if(args[0] != null || args[0].length() != 0) {

                try {
                    switch(args[0].toLowerCase()) {
                        case "about", "info" ->
                                aboutCommand.onCommand(sender, command, s, args);
                        case "profile" ->
                                profileCommand.onCommand(sender, command, s, args);
                        case "help" ->
                            helpCommand.onCommand(sender, command, s, args);
                        default -> {
                            sender.sendMessage("Sorry, \"" + args[0] + "\" is not a valid command.");
                            sender.sendMessage("Use \"/incentive help\" for a list of valid commands for Incentive.");
                        }
                    }

                } catch (Exception e) {
                    Incentive.getLog().warning("ERROR: Failed to handle command \"" + args[0] + "\". (" + e + ")");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            Incentive.getLog().warning("ERROR: Failed to handle command. (" + e + ")");
            sender.sendMessage(Incentive.commandPrefix + "Sorry, you need to specify a command first.");
        }

        return true;
    }
}

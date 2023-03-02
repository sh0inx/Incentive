package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //TODO: Implement pages and clickthrough navigation for the help command.

        String message = (
                "Incentive" +
                "\nprefix: inc (incentive, reward, rewards)" +
                "\n" +
                "\nabout: shows information about Incentive." +
                "\nprofile: shows the server profile that Incentive has loaded." +
                "\nhelp: shows this menu."
                );

        sender.sendMessage(message);

        return true;
    }

}

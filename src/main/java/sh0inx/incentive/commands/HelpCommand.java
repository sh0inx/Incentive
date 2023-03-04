package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sh0inx.incentive.Incentive;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //TODO: Implement pages and click-through navigation for the help command.

        sender.sendMessage(String.format("""
                        %s
                        prefix: inc (incentive, reward, rewards)
           
                        about: shows information about Incentive.
                        profile: shows the server profile that Incentive has loaded.
                        help: shows this menu.
                        """), Incentive.commandPrefix);

        return true;
    }

}

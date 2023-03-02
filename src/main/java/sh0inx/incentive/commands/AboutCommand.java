package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sh0inx.incentive.Incentive;

public class AboutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        String message =
                ("Incentive" +
                 "\nAuthor: " + Incentive.pluginAuthor +
                 "\nVersion: " + Incentive.pluginVersion +
                 "\nModrinth: " + Incentive.link +
                 "\nSource: " + Incentive.source);

        sender.sendMessage(message);

        return true;
    }
}

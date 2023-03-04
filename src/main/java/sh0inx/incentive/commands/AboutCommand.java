package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sh0inx.incentive.Incentive;

public class AboutCommand implements CommandExecutor {

    Incentive plugin = CommandManager.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        sender.sendMessage(String.format(
                ("%s\n" +
                 "Author: %s\n" +
                 "Version: %s\n" +
                 "Modrinth: %s\n" +
                 "Source: %s").formatted
                 (Incentive.commandPrefix, plugin.getPluginAuthor(), plugin.getPluginVersion(), plugin.getLink(), plugin.getSource())));

        return true;
    }
}

package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sh0inx.incentive.Incentive;

public class AboutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        sender.sendMessage("""
                Incentive
                author: %s
                version: %s
                modrinth: %s
                source: %s""",
                Incentive.pluginAuthor, Incentive.pluginVersion, Incentive.link, Incentive.source);

        return false;
    }
}

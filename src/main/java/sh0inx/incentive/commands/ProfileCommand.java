package sh0inx.incentive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import sh0inx.incentive.Incentive;

public class ProfileCommand implements CommandExecutor {

    Incentive plugin = CommandManager.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        sender.sendMessage(String.format("""
                 Incentive
                 Profile: %s
                 Modules: %s
                 bStats: %s""", Incentive.getPlatform(), Incentive.getModulesOn(), plugin.getbStats()));

        return true;
    }
}

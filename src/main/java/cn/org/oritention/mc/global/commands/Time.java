package cn.org.oritention.mc.global.commands;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args[0]==null) { sender.sendMessage(Color.RED+"Invalid usage, correct one: /time [time]"); }
            else {
                ((Player) sender).setPlayerTime(Long.parseLong(args[0]),false);
            }
        }
        return false;
    }
}

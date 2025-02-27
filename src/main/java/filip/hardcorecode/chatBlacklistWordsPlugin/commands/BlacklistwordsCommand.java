package filip.hardcorecode.chatBlacklistWordsPlugin.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import filip.hardcorecode.chatBlacklistWordsPlugin.Config;
import filip.hardcorecode.chatBlacklistWordsPlugin.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class BlacklistwordsCommand implements TabExecutor {
    static Config config = ConfigUtil.getConfig("blacklistwords");
    static List<String> blacklistwords = new ArrayList<>(config.getStringList("BlackList"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return true;
        }
        if (args[0].equalsIgnoreCase("add")) {
            blacklistwords.add(args[1]);
            sender.sendMessage("§asuccess");
        }
        if (args[0].equalsIgnoreCase("remove")) {
            if (!blacklistwords.contains(args[1])) {
                sender.sendMessage("§cThis word is not in the blacklist!");
                return true;
            }
            blacklistwords.remove(args[1]);
            sender.sendMessage("§asuccess");
        }
        if (args[0].equalsIgnoreCase("list")) {
            if (blacklistwords.isEmpty()) {
                sender.sendMessage("§cnot words exist in the list!");
            } else {
                sender.sendMessage("§6Blacklist-Words: " + String.join(", ", blacklistwords));
            }
            return true;
        }
        config.set("BlackList", blacklistwords);
        config.save();
        return false;
    }

    @Override
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("add", "remove","list");
        }
        if (args.length == 2){
            return blacklistwords;
        }
        return Arrays.asList(new String[0]);
    }
}

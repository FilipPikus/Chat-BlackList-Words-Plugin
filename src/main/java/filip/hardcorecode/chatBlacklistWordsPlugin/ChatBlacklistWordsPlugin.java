package filip.hardcorecode.chatBlacklistWordsPlugin;

import filip.hardcorecode.chatBlacklistWordsPlugin.commands.BlacklistwordsCommand;
import filip.hardcorecode.chatBlacklistWordsPlugin.listener.Blacklistwords;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatBlacklistWordsPlugin extends JavaPlugin {
    public static ChatBlacklistWordsPlugin instance;
    @Override
    public void onEnable() {
        instance = this;
        getCommand("blacklistwords").setExecutor(new BlacklistwordsCommand());
        Bukkit.getPluginManager().registerEvents(new Blacklistwords(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

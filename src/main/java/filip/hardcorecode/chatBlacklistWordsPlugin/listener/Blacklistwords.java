package filip.hardcorecode.chatBlacklistWordsPlugin.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import filip.hardcorecode.chatBlacklistWordsPlugin.Config;
import filip.hardcorecode.chatBlacklistWordsPlugin.ConfigUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Blacklistwords implements Listener
{
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Config config = ConfigUtil.getConfig("blacklistwords");
        List<String> blacklistwords = new ArrayList<>(config.getStringList("BlackList"));
        Config config2 = ConfigUtil.getConfig("messages");
        String BlacklistwordsMessage = "§4Please choose your words carefully";
        config2.setDefault("the-error-message", BlacklistwordsMessage);
        BlacklistwordsMessage = config2.getString("the-error-message", BlacklistwordsMessage);
        for (String blacklistword : blacklistwords) {
            if (event.getMessage().toLowerCase(Locale.ROOT).contains(blacklistword.toLowerCase())) {
                event.getPlayer().sendMessage(BlacklistwordsMessage);
                event.setCancelled(true);
                return;
            }
        }
    }
}

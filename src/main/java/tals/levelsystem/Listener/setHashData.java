package tals.levelsystem.Listener;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tals.levelsystem.TalsLevelSystem;

import java.io.File;

public class setHashData implements Listener {

    TalsLevelSystem plugin = TalsLevelSystem.getPlugin(TalsLevelSystem.class);

    public setHashData() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        setData(player);
    }

    private void setData(Player player) {

        File directory = new File((Bukkit.getServer().getPluginManager().getPlugin(plugin.getName()).getDataFolder()), File.separator + "PlayerData");
        File playerData = new File(directory, File.separator + player.getUniqueId().toString() + ".yml");

        YamlConfiguration conf = YamlConfiguration.loadConfiguration(playerData);

        plugin.joinedList.add(player);
        plugin.exp.put(player, conf.getInt("EXP"));
        plugin.level.put(player, conf.getInt("Level"));
    }
}

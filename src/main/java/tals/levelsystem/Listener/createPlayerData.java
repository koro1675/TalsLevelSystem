package tals.levelsystem.Listener;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tals.levelsystem.TalsLevelSystem;

import java.io.File;
import java.io.IOException;

public class createPlayerData implements Listener {

    TalsLevelSystem plugin = TalsLevelSystem.getPlugin(TalsLevelSystem.class);

    public createPlayerData() {
        plugin.getLogger().info("createPalyerDataEventRegisted");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        createData(player);
    }


    private void createData(Player player) {
        File directory = new File((Bukkit.getServer().getPluginManager().getPlugin(plugin.getName()).getDataFolder()), File.separator + "PlayerData");
        File playerData = new File(directory, File.separator + player.getUniqueId().toString() + ".yml");

        YamlConfiguration conf = YamlConfiguration.loadConfiguration(playerData);

        try {
            conf.set("Name", player.getName());
            if (!playerData.exists()) {
                conf.set("Level", 0);
                conf.set("EXP", 0);
            }
            conf.save(playerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

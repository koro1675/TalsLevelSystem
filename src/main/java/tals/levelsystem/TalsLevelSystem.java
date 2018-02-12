package tals.levelsystem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tals.levelsystem.Listener.createPlayerData;
import tals.levelsystem.Listener.setHashData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class TalsLevelSystem extends JavaPlugin {

    public List<Player> joinedList = new ArrayList<>();
    public HashMap<Player, Integer> level = new HashMap<>();
    public HashMap<Player, Integer> exp = new HashMap<>();

    createPlayerData playerData;
    setHashData hashData;
    TalsLevelSystemCommands commands;
    TalsLevelSystemManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        playerData = new createPlayerData();
        hashData = new setHashData();
        commands = new TalsLevelSystemCommands();
        manager = new TalsLevelSystemManager();
        this.saveDefaultConfig();

        getServer().getPluginCommand("tlevel").setExecutor(commands);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        savePlayerData();
    }

    public void savePlayerData() {
        for (int i = 0; joinedList.size() > i; i++) {

            File directory = new File((Bukkit.getServer().getPluginManager().getPlugin(this.getName()).getDataFolder()), File.separator + "PlayerData");
            File playerData = new File(directory, File.separator + joinedList.get(i).getUniqueId().toString() + ".yml");

            YamlConfiguration conf = YamlConfiguration.loadConfiguration(playerData);

            try {
                conf.set("Level", level.get(joinedList.get(i)));
                conf.set("EXP", exp.get(joinedList.get(i)));
                conf.save(playerData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

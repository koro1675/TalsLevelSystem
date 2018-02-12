package tals.levelsystem;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class TalsLevelSystemManager {

    TalsLevelSystem plugin = TalsLevelSystem.getPlugin(TalsLevelSystem.class);

    String prefix = "§f§l[§c§lT§6§lA§a§lL§b§lS§3§lLevelSystem§f§l]§r";

    public void showHelp(Player player) {
        player.sendMessage(prefix + "§3§l: Help");
        player.sendMessage("§c§lOperatorCommands");
        player.sendMessage("§c§l| §4§l/tlevel setLevel [player]: set level to player");
        player.sendMessage("§c§l| §4§l/tlevel setExp: [player]: set exp to player");
        player.sendMessage("§c§l§m=================================================");
        player.sendMessage("§3§l/tlevel info [player]: show player info");
    }


    public void playerInfo(Player target, Player sender) {

        sender.sendMessage(prefix + " §3§l" + target.getName() + "'s Infomation");
        sender.sendMessage("§9§lPlayerName: " + target.getName());
        sender.sendMessage("§9§lUUID: " + target.getUniqueId());
        sender.sendMessage("§9§lLevel: " + plugin.level.get(target));
        sender.sendMessage("§9§lExp: " + plugin.exp.get(target));
    }


    public void setLevel(Player sender, Player target, Integer amount) {

        plugin.level.put(target, amount);

        sender.sendMessage(prefix + target.getName() + "§a§lさんのレベルを" + amount + "にしました");
        target.sendMessage(prefix + "§a§lあなたのレベルが" + amount + "になりました");
    }

    public void setExp(Player sender, Player target, Integer amount) {

        plugin.exp.put(target, amount);

        sender.sendMessage(prefix + target.getName() + "§a§lさんの経験値を" + amount + "にしました");
        target.sendMessage(prefix + "§a§lあなたの経験値が" + amount + "になりました");
    }


}

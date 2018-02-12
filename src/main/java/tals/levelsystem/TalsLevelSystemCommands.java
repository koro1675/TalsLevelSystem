package tals.levelsystem;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TalsLevelSystemCommands implements CommandExecutor {

    TalsLevelSystem plugin = TalsLevelSystem.getPlugin(TalsLevelSystem.class);

    String prefix = "§f§l[§c§lT§6§lA§a§lL§b§lS§3§lLevelSystem§f§l]§r";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("this command is executable for players only.");
            return false;
        }

        Player player = (Player) sender;

        //引数がない場合
        if (args.length == 0) {
            player.sendMessage(prefix + "§lVersion1.0");
            return false;
        }

        //ヘルプの表示
        if (args[0].equalsIgnoreCase("help")) {
            plugin.manager.showHelp(player);
            return false;
        }

        //infoの表示
        if (args[0].equalsIgnoreCase("info")) {
            if (args.length != 2) {
                player.sendMessage(prefix + "§3§l/tlevel info [PlayerName]");
                return false;
            }

            String targetName = String.valueOf(args[1]);

            //Playerが存在するか確認
            if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(targetName))) {
                player.sendMessage("Player is not online!");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            plugin.manager.playerInfo(target, player);
        }

        if (args[0].equalsIgnoreCase("setLevel")) {
            if (args.length != 3) {
                player.sendMessage(prefix + "§3§l/tlevel setLevel [PlayerName] [amount]");
                return false;
            }

            String targetName = String.valueOf(args[1]);

            //Playerが存在するか確認
            if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(targetName))) {
                player.sendMessage("Player is not online!");
                return false;
            }

            Integer amount = Integer.parseInt(args[2]);

            if (amount > 1025) {
                player.sendMessage(prefix + "§4§l数が大きすぎます、1024以下にしてください");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            plugin.manager.setLevel(player, target, amount);
        }


        if (args[0].equalsIgnoreCase("setExp")) {
            if (args.length != 3) {
                player.sendMessage(prefix + "§3§l/tlevel setExp [PlayerName] [amount]");
                return false;
            }

            String targetName = String.valueOf(args[1]);

            //Playerが存在するか確認
            if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(targetName))) {
                player.sendMessage("Player is not online!");
                return false;
            }

            Integer amount = Integer.parseInt(args[2]);

            if (amount > 100000) {
                player.sendMessage(prefix + "§4§l数が大きすぎます、100001以下にしてください");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            plugin.manager.setExp(player, target, amount);
        }


        return false;
    }
}

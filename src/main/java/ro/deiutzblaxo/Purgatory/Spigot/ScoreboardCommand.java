package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ScoreboardCommand extends Command {
	private MainSpigot plugin;

	protected ScoreboardCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(sender instanceof Player) {
			Player player =(Player) sender;
			if(plugin.getBanFactory().isBan(player.getUniqueId())) {
				if(player.getScoreboard() != null) {
					plugin.getScoreBoardAPI().createScoreboard(player, plugin.getTaskFactory().getTasks());
				}else {
					plugin.getScoreBoardAPI().removeScoreBroad(player);
				}
			}else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Scoreboard.notBanned")));
			}


		}else {
			sender.sendMessage("Only players can use this command!");
		}
		return false;
	}

}

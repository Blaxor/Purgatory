package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class tpoCommand extends Command {
	private MainSpigot plugin;
	public tpoCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
				player.teleport(plugin.getWorldManager().getDefault().getSpawnLocation());
				return true;
			}else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
						.getString(plugin.getConfigManager().getMessages(), "tpo.Error")));
				return false;
			}

		}else {
			sender.sendMessage("Only players can use this command");
		}
		return false;
	}

}

package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Troll.Trolls;

public class TrollCommand extends Command {
	private MainSpigot plugin;
	private Trolls troll;
	public TrollCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
		troll = new Trolls(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		if(sender instanceof Player) {


			Player player = (Player) sender;
			if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
				player.openInventory(troll.TrollsInventory(player));
				return true;
			}else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.Banned")));
			}
		}

		sender.sendMessage("Only Players can use this command!");
		return false;
	}

}

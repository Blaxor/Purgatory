package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Troll.Trolls;

public class TrollCommand extends Command {
	private MainSpigot plugin;
	public TrollCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		if(sender instanceof Player) {
			sender.sendMessage("Troll menu has been open!");
			Trolls troll = new Trolls(plugin);
			Player player = (Player) sender;
			player.openInventory(troll.TrollsInventory(player));
			return true;
		}
		sender.sendMessage("Only Players can use this command!");
		return false;
	}

}

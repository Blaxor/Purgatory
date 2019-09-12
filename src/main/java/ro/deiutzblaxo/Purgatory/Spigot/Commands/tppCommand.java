package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class tppCommand extends Command {
	MainSpigot plugin;

	public tppCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
			return true;
		}else {
			sender.sendMessage("Only players can use this command");
		}
		return false;
	}

}

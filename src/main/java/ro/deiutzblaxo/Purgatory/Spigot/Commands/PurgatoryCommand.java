package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class PurgatoryCommand extends Command {
	private MainSpigot plugin;
	private String str;
	public PurgatoryCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
		str = "&7[&a" + plugin.getName() + "&7]&a Plugin by &e" + plugin.getDescription().getAuthors().toString() + "/n&7[&a" +plugin.getName() + "&7]&a Link of the plugin: &ehttps://www.spigotmc.org/resources/65838/" ;
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {



		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', str));

		return false;
	}

}

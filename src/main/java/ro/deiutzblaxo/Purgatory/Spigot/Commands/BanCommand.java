package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class BanCommand extends Command{
	MainSpigot plugin;

	public BanCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;


	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		for(String str : plugin.getTaskFactory().getTasks()) {
			plugin.getTaskFactory().setTasks("this is a UUID :D");
			//test
		}
		return false;
	}

}

package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class Teleport2Command extends Command {
	private MainBungee plugin;
	public Teleport2Command(String name, MainBungee mainBungee) {
		super(name);
		plugin = mainBungee;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			player.connect(plugin.getServerManager().getHubServer());

		}

	}

}

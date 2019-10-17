package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class TeleportCommand extends Command {
	private MainBungee plugin;
	public TeleportCommand(String string, MainBungee mainBungee) {
		super (string);
		plugin = mainBungee;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			player.connect(plugin.getServerManager().getPurgatoryServer());

		}

	}

}

package ro.deiutzblaxo.Purgatory.Spigot.Troll;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class CheatersCommand extends Command implements Listener {
	private MainSpigot plugin;
	private Players players;
	public CheatersCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
		players = new Players(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			plugin.getConfigManager().loadMessages();

			if(plugin.getBanFactory().isBan(player.getUniqueId())) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("NoPermission")));
				return false;
			}
			if(!plugin.isBungeeEnabled()) {
				if(!(player.getLocation().getWorld().getName().equalsIgnoreCase(plugin.getWorldManager().getPurgatory().getName()))) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							plugin.getConfigManager().getMessages().getString("Menu.Cheaters.WrongWorld")));
					return false;
				}

				player.openInventory(players.getPlayersInventory());

			}else {
				player.openInventory(players.getPlayersInventory());
			}
		}
		return false;
	}



}

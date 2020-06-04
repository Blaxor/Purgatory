//    Purgatory , a ban system for servers of Minecraft
//    Copyright (C) 2020  Deiutz
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Troll.Players;

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
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"NoPermission")));
				return false;
			}
			if(!plugin.isBungeeEnabled()) {
				if(!(player.getLocation().getWorld().getName().equalsIgnoreCase(plugin.getWorldManager().getPurgatory().getName()))) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfigManager().getString
							(plugin.getConfigManager().getMessages(),"Menu.Cheaters.WrongWorld")));
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

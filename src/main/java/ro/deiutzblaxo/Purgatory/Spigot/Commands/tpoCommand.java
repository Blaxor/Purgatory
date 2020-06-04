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

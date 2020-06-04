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
package ro.deiutzblaxo.Purgatory.Bungee.Factory;

import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class WarningFactory {
	private MainBungee plugin;

	public WarningFactory(MainBungee mainBungee) {
		plugin = mainBungee;
	}
	public void setWarning(ProxiedPlayer player, CommandSender sender,String reason) {
		String uuid = player.getUniqueId().toString();

		plugin.getConfigManager().loadWarnings();
		if(!isWarning(player)) {
			plugin.getConfigManager().getWarnings().set(uuid + ".Value", 1);
			plugin.getConfigManager().getWarnings().set(uuid + ".Reason", reason);
			plugin.getConfigManager().saveWarnings();
		}else {
			int MaxWarning = plugin.getConfigManager().getConfig().getInt("MaxWarnings");
			int Warning = this.getWarningNumber(player) + 1;
			if(MaxWarning > Warning) {
				plugin.getConfigManager().getWarnings().set(uuid + ".Value" , Warning);
				plugin.getConfigManager().getWarnings().set(uuid + ".Reason", reason);
				plugin.getConfigManager().saveWarnings();
			}else if(MaxWarning <= Warning) {
				plugin.getBanFactory().setBan(player.getUniqueId() ,  reason,player.getName());
				if(plugin.getConfigManager().getConfig().getBoolean("Ban-Disconnect")) {

					plugin.getProxy().getPlayer(player.getUniqueId()).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&',
							plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Format").replaceAll("%reason%", reason))));
				}else {
					plugin.getProxy().getPlayer(player.getUniqueId()).connect(plugin.getServerManager().getPurgatoryServer());
					plugin.getProxy().getPlayer(player.getUniqueId()).sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
							plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Format").replaceAll("%reason%", reason))));
				}

				plugin.getProxy().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Broadcast")
						.replaceAll("%player%", player.getName()).replaceAll("%admin%", sender.getName()).replaceAll("%reason%", reason))));
			}

		}


	}




	public void removeWarning(ProxiedPlayer player) {

		UUID uuid = player.getUniqueId();
		plugin.getConfigManager().loadWarnings();
		plugin.getConfigManager().getWarnings().set(uuid.toString(), null);
		plugin.getConfigManager().saveWarnings();


	}
	public Integer getWarningNumber(ProxiedPlayer player) {
		String uuid = player.getUniqueId().toString();
		if(isWarning(player)) {
			plugin.getConfigManager().loadWarnings();
			return	plugin.getConfigManager().getWarnings().getInt(uuid + ".Value");
		}
		return 0;
	}
	public String getReason(ProxiedPlayer player) {
		String uuid = player.getUniqueId().toString();
		plugin.getConfigManager().loadWarnings();
		return plugin.getConfigManager().getWarnings().getString(uuid + ".Reason");

	}
	public boolean isWarning(ProxiedPlayer player) {
		String uuid = player.getUniqueId().toString();
		plugin.getConfigManager().loadWarnings();
		return plugin.getConfigManager().getWarnings().contains(uuid);


	}
	public int getMaxWarning() {
		return plugin.getConfigManager().getConfig().getInt("MaxWarnings");

	}



}

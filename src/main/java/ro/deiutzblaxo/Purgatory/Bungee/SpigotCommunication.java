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
package ro.deiutzblaxo.Purgatory.Bungee;

import java.util.UUID;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class SpigotCommunication implements Listener {
	protected MainBungee plugin;
	protected SpigotCommunication(MainBungee main){
		plugin = main;


	}

	@EventHandler
	public void onPluginMessage(PluginMessageEvent ev) {

		byte[] data = ev.getData();
		ByteArrayDataInput in = ByteStreams.newDataInput(data);
		if(ev.getTag().contentEquals("purgatory:main")) {
			String type = in.readUTF();
			if(type.equals("unban")) {
				UUID uuid = UUID.fromString(in.readUTF());
				plugin.getBanFactory().removeBan(uuid);
				if(plugin.getProxy().getPlayer(uuid) != null) {
					if(plugin.getConfigManager().getConfig().getBoolean("UnBan-Disconnect")) {
						if(plugin.getProxy().getPlayer(uuid) != null) {
							plugin.getProxy().getPlayer(uuid).setReconnectServer(plugin.getServerManager().getHubServer());
							plugin.getProxy().getPlayer(uuid).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&'
									, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
									.replaceAll("%admin%", plugin.getConfigManager().getMessages().getString("TasksCompleted"))));
						}
					}else {
						if(plugin.getProxy().getPlayer(uuid) != null) {
							plugin.getProxy().getPlayer(uuid).connect(plugin.getServerManager().getHubServer());
							plugin.getProxy().getPlayer(uuid).sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&'
									, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat")
									.replaceAll("%admin%", plugin.getConfigManager().getMessages().getString("TasksCompleted")))));
						}
					}
				}
			}

		}
	}

	public void send(UUID uuid , String[] str) {
		ByteArrayDataOutput output = ByteStreams.newDataOutput();
		output.writeUTF(str[0]);//TYPE
		output.writeUTF(uuid.toString());//PLAYER'S UUID
		if(str[0].equals("ban")) {
			output.writeUTF(str[1]);//REASON
			output.writeUTF(str[2]);//Player's name
		}else if(str[0].equals("unban")){


		}else if(str[0].equals("tempban")){
			output.writeUTF(str[1]);//REASON
			output.writeUTF(str[2]);//Player's name
			output.writeUTF(str[3]); // time

		}else {
			plugin.getProxy().getConsole().sendMessage(new TextComponent("UNAVALABILE TYPE AT ro.deiutzblaxo.Purgatory.Bungee.SpigotComunication.class AT send method"));
		}

		plugin.getServerManager().getPurgatoryServer().sendData("purgatory:main", output.toByteArray());

	}
}

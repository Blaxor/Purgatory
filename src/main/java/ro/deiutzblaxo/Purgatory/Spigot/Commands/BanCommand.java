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

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class BanCommand extends Command{
	private	MainSpigot plugin;
	private String reason,playername;
	private OfflinePlayer player ;
	private TitleManager titlemanager;

	public BanCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
		titlemanager = new TitleManager(main);

	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		plugin.getConfigManager().loadMessages();
		if(!sender.hasPermission("purgatory.ban")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission")));
			return false;
		}
		if(args.length < 1) {

			ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
			BaseComponent[] test = null;
			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")+" :"))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Ban") + " "
							+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")+" "
							+ plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).create();
			texts.add(test);
			test = new ComponentBuilder("/" + plugin.getConfig().getString("Command.Ban")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create())).create();
			texts.add(test);
			test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
			texts.add(test);
			test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.hover"))).create())).create();
			texts.add(test);
			ComponentBuilder proprozitie = new ComponentBuilder("");
			for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
				proprozitie.append(texts.get(fraze));
				proprozitie.append(" ");

			}
			sender.spigot().sendMessage(proprozitie.create());

			return false;

		}
		if(args.length == 0) {
			ArrayList<String> test = new ArrayList<String>();

			for(Player player : plugin.getServer().getOnlinePlayers()) {
				test.add(player.getName());
			}
			String[] completation = new String[test.size()];
			for(int a = 0 ; a < test.size() ; a++) {
				completation[a] = test.get(a);
			}

			this.tabComplete(sender, "", completation);
		}else {
			this.tabComplete(sender, "", new String[0]);
		}

		if(plugin.getServer().getPlayer(args[0]) != null || plugin.getServer().getOfflinePlayer(args[0]) != null) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				player = plugin.getServer().getPlayer(args[0]);
			}
			else if(plugin.getServer().getOfflinePlayer(args[0]) != null) {
				player = plugin.getServer().getOfflinePlayer(args[0]);
			}
		}else {
			sender.sendMessage("this player don`t exists!");
			return false;
		}

		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(),"Ban.isBan").replaceAll("%player%", player.getName())));
			return false;
		}else {
			playername = args[0];
			if(args.length >= 2) {
				args[0] = "";
				StringBuilder stringBuilder = new StringBuilder();
				for (String arg : args) {
					stringBuilder.append(arg).append(" ");
				}
				reason = stringBuilder.toString();
			}else {
				reason = plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"Ban.DefaultReason");
			}

			plugin.getBanFactory().setBan(player.getUniqueId(), playername , reason);
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
					.getString(plugin.getConfigManager().getMessages(), "Ban.broadcast")
					.replaceAll("%player%", player.getName()).replaceAll("%admin%", sender.getName().replaceAll("%reason%", reason))));
			if(player.isOnline()) {
				player.getPlayer().teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
				if(plugin.getConfig().getBoolean("Force-Kick")) {

					player.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Format").replaceAll("%reason%", reason)));

				}else {



					titlemanager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
							(plugin.getConfigManager().getMessages(),"Ban.Format").replaceAll("%reason%", reason)));

				}
			}



		}
		return false;
	}
}

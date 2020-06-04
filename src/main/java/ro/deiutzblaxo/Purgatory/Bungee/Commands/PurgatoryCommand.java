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
package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class PurgatoryCommand extends Command {
	private MainBungee plugin;

	public PurgatoryCommand(String name, MainBungee mainBungee) {
		super(name);
		plugin = mainBungee;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&7[&a" + plugin.getDescription().getName() + "&7] &ePlugin version " + plugin.getDescription().getVersion()))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&eClick on me for the plugin's page.")).create()))
				.event(new ClickEvent(ClickEvent.Action.OPEN_URL , "https://www.spigotmc.org/resources/65838/")).create());

		if(sender.hasPermission("purgatory.ban")) {
			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();

			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfigManager().getConfig().getString("Command.Ban").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.Ban").toLowerCase())).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] reason = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "<reason>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason"))).create())).create();
			raspuns.add(reason);
			BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eBan a player.")).create();
			raspuns.add(descriere);
			ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
			for(int parti = 0 ; parti < raspuns.size() ; parti++) {
				raspunsFinalizat.append(raspuns.get(parti));
				raspunsFinalizat.append(" ");

			}
			sender.sendMessage(raspunsFinalizat.create());


		}
		if(sender.hasPermission("purgatory.tempban")) {
			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfigManager().getConfig().getString("Command.TempBan").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.TempBan").toLowerCase() + " <player> <seconds> <reason>")).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] timp = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "<time>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Time"))).create())).create();
			raspuns.add(timp);
			BaseComponent[] reason = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "<reason>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason"))).create())).create();
			raspuns.add(reason);
			BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eTemporarily ban a player")).create();
			raspuns.add(descriere);
			ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
			for(int parti = 0 ; parti < raspuns.size() ; parti++) {
				raspunsFinalizat.append(raspuns.get(parti));
				if(parti != raspuns.size()) {
					raspunsFinalizat.append(" ");
				}
				raspunsFinalizat.reset();
			}
			sender.sendMessage(raspunsFinalizat.create());
		}

		if(sender.hasPermission("purgatory.warning")) {
			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfigManager().getConfig().getString("Command.Warning").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.Warning").toLowerCase() + " <player> <reason>")).event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.Warning").toLowerCase() + " <player> <reason>")).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] reason = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "<reason>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason"))).create())).create();
			raspuns.add(reason);
			BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eWarning a player")).create();
			raspuns.add(descriere);
			ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
			for(int parti = 0 ; parti < raspuns.size() ; parti++) {
				raspunsFinalizat.append(raspuns.get(parti));
				raspunsFinalizat.append(" ");
				raspunsFinalizat.reset();
			}
			sender.sendMessage(raspunsFinalizat.create());
		}
		if(sender.hasPermission("purgatory.purge")) {
			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfigManager().getConfig().getString("Command.UnBan").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.UnBan").toLowerCase() + " <player> <seconds> <reason>")).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&ePurge a player")).create();
			raspuns.add(descriere);
			ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
			for(int parti = 0 ; parti < raspuns.size() ; parti++) {
				raspunsFinalizat.append(raspuns.get(parti));
				raspunsFinalizat.append(" ");
				raspunsFinalizat.reset();
			}
			sender.sendMessage(raspunsFinalizat.create());
		}

		if(sender.hasPermission("purgatory.info")) {
			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();

			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfigManager().getConfig().getString("Command.Info").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "&/" + plugin.getConfigManager().getConfig().getString("Command.Info").toLowerCase() + " <player>")).create();

			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eShow informations about the player")).create();
			raspuns.add(descriere);
			ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
			for(int parti = 0 ; parti < raspuns.size() ; parti++) {
				raspunsFinalizat.append(raspuns.get(parti));
				raspunsFinalizat.append(" ");
				raspunsFinalizat.reset();
			}
			sender.sendMessage(raspunsFinalizat.create());
		}
		ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();

		BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfigManager().getConfig().getString("Command.tpp").toLowerCase()))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
				.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "&/" + plugin.getConfigManager().getConfig().getString("Command.tpp").toLowerCase())).create();

		raspuns.add(command);
		BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eTeleport you to the Purgatory!")).create();
		raspuns.add(descriere);
		ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
		for(int parti = 0 ; parti < raspuns.size() ; parti++) {
			raspunsFinalizat.append(raspuns.get(parti));
			raspunsFinalizat.append(" ");
			raspunsFinalizat.reset();
		}
		sender.sendMessage(raspunsFinalizat.create());

	}

}

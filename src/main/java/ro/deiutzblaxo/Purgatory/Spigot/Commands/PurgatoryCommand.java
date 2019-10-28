package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class PurgatoryCommand extends Command {
	private MainSpigot plugin;
	public PurgatoryCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		sender.spigot().sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&7[&a" + plugin.getDescription().getName() + "&7] &ePlugin version " + plugin.getDescription().getVersion()))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&eClick on me for the plugin's page.")).create()))
				.event(new ClickEvent(ClickEvent.Action.OPEN_URL , "https://www.spigotmc.org/resources/65838/")).create());

		if(!plugin.isBungeeEnabled()) {
			if(sender.hasPermission("purgatory.ban")) {
				ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
				BaseComponent[] test = null;
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.Ban"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Ban") + " "
								+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")+" "
								+ plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).create();
				texts.add(test);
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
				texts.add(test);
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.hover"))).create())).create();
				texts.add(test);
				test = new ComponentBuilder (ChatColor.translateAlternateColorCodes('&', "&f-&eBan a player.")).create();
				texts.add(test);
				ComponentBuilder proprozitie = new ComponentBuilder("");
				for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
					proprozitie.append(texts.get(fraze));
					proprozitie.append(" ");
					proprozitie.reset();
				}
				sender.spigot().sendMessage(proprozitie.create());
			}



			if(sender.hasPermission("purgatory.tempban")) {
				ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
				BaseComponent[] test = null;

				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.TempBan"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.TempBan") + " "
								+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")
								+" " + plugin.getConfigManager().getMessages().getString("InvalidCommand.Time.time") + " "
								+ plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).create();
				texts.add(test);
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
				texts.add(test);
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Time.time"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Time.hover"))).create())).create();
				texts.add(test);
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.hover"))).create())).create();
				texts.add(test);
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eTemporarily ban a player")).create();
				texts.add(test);
				ComponentBuilder proprozitie = new ComponentBuilder("");
				for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
					proprozitie.append(texts.get(fraze));
					proprozitie.append(" ");
					proprozitie.reset();

				}
				sender.spigot().sendMessage(proprozitie.create());
			}
			if(sender.hasPermission("purgatory.info")) {
				ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
				BaseComponent[] test = null;

				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.Info")))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Info") + " "
								+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player"))).create();
				texts.add(test);
				test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
				texts.add(test);
				BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eShow informations about the player")).create();
				texts.add(descriere);
				ComponentBuilder proprozitie = new ComponentBuilder("");
				for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
					proprozitie.append(texts.get(fraze));
					proprozitie.append(" ");
					proprozitie.reset();
				}
				sender.spigot().sendMessage(proprozitie.create());
			}
			if(sender.hasPermission("purgatory.purge")) {
				ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
				BaseComponent[] test = null;
				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.Purge")))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Purge") + " "
								+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player"))).create();
				texts.add(test);
				test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
				texts.add(test);
				BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&ePurge a player")).create();
				texts.add(descriere);
				ComponentBuilder proprozitie = new ComponentBuilder("");
				for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
					proprozitie.append(texts.get(fraze));
					proprozitie.append(" ");
					proprozitie.reset();
				}
				sender.spigot().sendMessage(proprozitie.create());
			}
			if(sender.hasPermission("purgatory.warning")) {
				ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
				BaseComponent[] test = null;


				test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.Warning"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Warning") + " "
								+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")
								+" " + plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).create();
				texts.add(test);
				test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
				texts.add(test);
				test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.hover"))).create())).create();
				texts.add(test);
				BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eWarning a player")).create();
				texts.add(descriere);
				ComponentBuilder proprozitie = new ComponentBuilder("");
				for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
					proprozitie.append(texts.get(fraze));
					proprozitie.append(" ");
					proprozitie.reset();
				}
				sender.spigot().sendMessage(proprozitie.create());
			}
			ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
			BaseComponent[] test = null;


			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.tpo"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.tpo"))).create();
			texts.add(test);
			BaseComponent[] descriere = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eTeleport to overworld")).create();
			texts.add(descriere);
			ComponentBuilder proprozitie = new ComponentBuilder("");
			for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
				proprozitie.append(texts.get(fraze));
				proprozitie.append(" ");
				proprozitie.reset();
			}
			sender.spigot().sendMessage(proprozitie.create());
			texts.clear();
			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&a/" + plugin.getConfig().getString("Command.tpp"))).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create()))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.tpp"))).create();
			texts.add(test);
			BaseComponent[] descriere2 = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&f-&eTeleport to purgatory")).create();
			texts.add(descriere2);
			ComponentBuilder proprozitie2 = new ComponentBuilder("");
			for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
				proprozitie2.append(texts.get(fraze));
				proprozitie2.append(" ");
				proprozitie2.reset();
			}
			sender.spigot().sendMessage(proprozitie2.create());
		}

		return false;
	}

}

package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.BaseComponent;
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
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		plugin.getConfigManager().loadMessages();
		if(!sender.hasPermission("purgatory.ban")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission")));
			return false;
		}
		if(args.length < 1) {
			//			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
			//					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.InvalidCommand"))));
			ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
			BaseComponent[] test = null;
			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&eUsage :")).create();
			texts.add(test);
			test = new ComponentBuilder("/ban").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder("This is the command!").color(net.md_5.bungee.api.ChatColor.WHITE).create())).create();
			texts.add(test);
			test = new ComponentBuilder("<player>").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
					new ComponentBuilder("here need to be name of a player").color(net.md_5.bungee.api.ChatColor.WHITE).create())).create();
			texts.add(test);
			test = new ComponentBuilder("<reason>").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder("here can be empty or a reason").color(net.md_5.bungee.api.ChatColor.WHITE).create())).create();
			texts.add(test);
			ComponentBuilder proprozitie = new ComponentBuilder("");
			for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
				proprozitie.append(texts.get(fraze));
				proprozitie.append(" ");

			}
			sender.spigot().sendMessage(proprozitie.create());
			return false;
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

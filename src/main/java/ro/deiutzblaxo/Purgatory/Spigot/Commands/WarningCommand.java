package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class WarningCommand extends Command {
	private MainSpigot plugin ;
	private OfflinePlayer player;
	private String reason;
	private TitleManager titlemanager;
	public WarningCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
		titlemanager = new TitleManager(mainSpigot);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		plugin.getConfigManager().loadWarningDataBase();
		plugin.getConfigManager().loadBanDataBase();
		plugin.getConfigManager().loadMessages();

		if(!sender.hasPermission("purgatory.warning")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission")));
			return false;
		}
		if(args.length < 1) {

			ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
			BaseComponent[] test = null;
			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")+" :"))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Warning") + " "
							+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")
							+" " + plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason.reason"))).create();
			texts.add(test);
			test = new ComponentBuilder("/" + plugin.getConfig().getString("Command.Warning")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
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

		if(plugin.getServer().getPlayer(args[0]) != null || plugin.getServer().getOfflinePlayer(args[0]) != null) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				player = plugin.getServer().getPlayer(args[0]);
			}
			else if(plugin.getServer().getOfflinePlayer(args[0]) != null) {
				player = plugin.getServer().getOfflinePlayer(args[0]);

			}

		}
		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Warning.isBanned").replaceAll("%player%", player.getName())));
			return false;
		}
		if(args.length == 1) {
			reason = plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Warning.DefaultReason");
		}else {
			args[0] = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (String arg : args) {
				stringBuilder.append(arg).append(" ");
			}
			reason = stringBuilder.toString();
		}

		plugin.getWarningFactory().setWarning(player, reason);
		int WarningMax, Warning;
		WarningMax = plugin.getWarningFactory().getMaxWarning();
		if(plugin.getWarningFactory().getWarningNumber(player) == 0) {
			Warning = WarningMax;
		}else {
			Warning = plugin.getWarningFactory().getWarningNumber(player);
		}
		if(player.isOnline()) {
			player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Warning.Receive"))
					.replaceAll("%warning%", Warning + "")
					.replaceAll("%warning_max%", WarningMax+ "")
					.replaceAll("%player%", player.getName())
					.replaceAll("%admin%", sender.getName())
					.replaceAll("%reason%", reason));
			if(plugin.getConfig().getBoolean("Title-Warning")) {
				titlemanager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"Warning.Receive"))
						.replaceAll("%warning%", Warning + "")
						.replaceAll("%warning_max%", WarningMax+ "")
						.replaceAll("%player%", player.getName())
						.replaceAll("%admin%", sender.getName())
						.replaceAll("%reason%", reason));
			}
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"Warning.Send"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason));
		Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"Warning.broadcast"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason));

		return true;

	}
}

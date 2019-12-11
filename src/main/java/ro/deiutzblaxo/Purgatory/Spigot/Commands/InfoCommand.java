package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class InfoCommand extends Command {

	private MainSpigot plugin;

	private String reason , isBanS;
	private Integer warnings, seconds;
	private OfflinePlayer player;
	public InfoCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		if(!sender.hasPermission("purgatory.info")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission")));
			return false;
		}

		if(args.length == 1) {
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
				reason = plugin.getBanFactory().reasonBan(player.getUniqueId());
				warnings = plugin.getWarningFactory().getWarningNumber(player);
			}else if(!plugin.getBanFactory().isBan(player.getUniqueId())|| plugin.getWarningFactory().isWarning(player)) {

				warnings = plugin.getWarningFactory().getWarningNumber(player);
				reason = plugin.getWarningFactory().getReason(player);
			}
			if(reason == null) reason = " ";

			isBanS = plugin.getBanFactory().isBan(player.getUniqueId()) ? plugin.getConfigManager().getMessages().getString("Yes") : plugin.getConfigManager().getMessages().getString("No");
			seconds = plugin.getBanFactory().isTempBan(player.getUniqueId()) ? plugin.getBanFactory().getTime(player.getUniqueId()) : 0;


			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Info.Format").replaceAll("%reason%", reason)
					.replaceAll("%warnings%", warnings+ "").replaceAll("%isban%", isBanS).replaceAll("%player%", player.getName()).replaceAll("%time%", seconds.toString())));


		}else {
			ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
			BaseComponent[] test = null;
			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")+" :"))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Info") + " "
							+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")))
					.create();
			texts.add(test);
			test = new ComponentBuilder("/" + plugin.getConfig().getString("Command.Info")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create())).create();
			texts.add(test);
			test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
			texts.add(test);
			ComponentBuilder proprozitie = new ComponentBuilder("");
			for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
				proprozitie.append(texts.get(fraze));
				proprozitie.append(" ");

			}
			sender.spigot().sendMessage(proprozitie.create());

			return false;
		}

		return false;
	}

}

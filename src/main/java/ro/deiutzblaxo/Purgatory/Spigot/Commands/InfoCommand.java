package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class InfoCommand extends Command {

	private MainSpigot plugin;
	private Boolean isBan;
	private String reason , isBanS;
	private Integer warnings;
	private OfflinePlayer player;
	public InfoCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {

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
				isBan = true;
			}else {
				isBan = false;
			}

			if(isBan) {
				reason = plugin.getBanFactory().reasonBan(player.getUniqueId());
				warnings = plugin.getWarningFactory().getWarningNumber(player);
			}else if(!isBan|| plugin.getWarningFactory().isWarning(player)) {

				warnings = plugin.getWarningFactory().getWarningNumber(player);
				reason = plugin.getWarningFactory().getReason(player);
			}else if(!isBan || plugin.getWarningFactory().isWarning(player)) {
				reason = null;
				warnings = plugin.getWarningFactory().getWarningNumber(player);
			}
			if(reason == null) {
				reason = " ";
			}
			if(isBan) {
				isBanS = plugin.getConfigManager().getMessages().getString("Yes");
			}else {
				isBanS = plugin.getConfigManager().getMessages().getString("No");
			}

			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Info.Format").replaceAll("%reason%", reason)
					.replaceAll("%warnings%", warnings+ "").replaceAll("%isban%", isBanS).replaceAll("%player%", player.getName())));


		}

		return false;
	}

}

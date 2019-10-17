package ro.deiutzblaxo.Purgatory.Bungee.Factory;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class BanFactory {
	private MainBungee plugin;
	private HashMap<UUID, Integer> tempban = new HashMap<UUID,Integer>();

	public BanFactory(MainBungee main) {
		plugin = main;
		plugin.getProxy().getScheduler().schedule(plugin, new Runnable() {

			@Override
			public void run() {

				if(tempban.isEmpty()) return;
				for(UUID uuid : tempban.keySet()) {
					plugin.getProxy().getConsole().sendMessage(new TextComponent("Test " + tempban.get(uuid)));
					int time = tempban.get(uuid);
					if(1 >= time) {
						tempban.remove(uuid);
						removeBan(uuid);
						if(plugin.getProxy().getPlayer(uuid) != null) {

							if(plugin.getConfigManager().getConfig().getBoolean("UnBan-Disconnect")) {
								plugin.getProxy().getPlayer(uuid).setReconnectServer(plugin.getServerManager().getHubServer());
								plugin.getProxy().getPlayer(uuid).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&'
										, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
										.replaceAll("%admin%",plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "TempBanExpired"))));


							}else {
								plugin.getProxy().getPlayer(uuid).connect(plugin.getServerManager().getHubServer());
								plugin.getProxy().getPlayer(uuid).sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&'
										, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
										.replaceAll("%admin%",plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "TempBanExpired"))));

							}

						}

					}else {
						tempban.replace(uuid, time - 1);
					}
				}

			}

		},1, 1, TimeUnit.SECONDS);

	}

	public void setBan(UUID uuid , String reason , String Name) {
		plugin.getConfigManager().loadBans();
		plugin.getConfigManager().getBans().set(uuid.toString() + ".Reason", reason);
		plugin.getConfigManager().getBans().set(uuid.toString() + ".Name", Name);
		if(plugin.getConfigManager().getConfig().getBoolean("Remove-Warnings-On-Ban")) {
			plugin.getConfigManager().loadWarnings();
			plugin.getConfigManager().getWarnings().set(uuid.toString(), null);
			plugin.getConfigManager().saveWarnings();

			plugin.getConfigManager().saveBans();
			if(plugin.getProxy().getPlayer(uuid)!=null ) {
				plugin.getProxy().getPlayer(uuid).setReconnectServer(plugin.getServerManager().getPurgatoryServer());
			}
			String send = "ban*" + reason + "*" + Name;
			plugin.getSpigotCommunication().send(uuid, send.split("\\*"));
		}




	}
	public boolean isBan(UUID uuid) {
		plugin.getConfigManager().loadBans();
		if(plugin.getConfigManager().getBans().contains(uuid.toString())) {
			return true;
		}
		return false;
	}
	public boolean isBan(String str) {
		plugin.getConfigManager().loadBans();
		for(String string : plugin.getConfigManager().getBans().getKeys()) {
			if(plugin.getConfigManager().getBans().contains(string + ".Name")) {
				if(plugin.getConfigManager().getBans().getString(string + ".Name").equalsIgnoreCase(str)) {
					return true;
				}

			}

		}
		return false;
	}
	public void removeBan(String str) {
		plugin.getConfigManager().loadBans();
		for(String string : plugin.getConfigManager().getBans().getKeys()) {
			if(plugin.getConfigManager().getBans().contains(string + ".Name")) {
				if(plugin.getConfigManager().getBans().getString(string + ".Name").equalsIgnoreCase(str)) {
					removeBan(UUID.fromString(string));
				}

			}

		}

	}
	public void removeBan(UUID uuid) {
		plugin.getConfigManager().getBans().set(uuid.toString(), null);
		plugin.getConfigManager().saveBans();
		plugin.getProxy().getPlayer(uuid).setReconnectServer(plugin.getServerManager().getHubServer());
		String send = "unban";
		plugin.getSpigotCommunication().send(uuid, send.split("\\*"));
		if(getTempBan().containsKey(uuid)) {
			getTempBan().remove(uuid);
		}
	}
	public void setTempBan(UUID uuid , String reason , Integer Time ,String Name) {
		if(!isBan(uuid)) {
			setBan(uuid, reason, Name);
		}
		if(tempban.containsKey(uuid)) {
			Integer old = tempban.get(uuid);
			tempban.replace(uuid, old + Time);
		}else {
			tempban.put(uuid, Time);
		}
	}
	public String getReason(ProxiedPlayer player) {
		if(isBan(player.getUniqueId())) {
			return plugin.getConfigManager().getBans().getString(player.getUniqueId().toString() + ".Reason");
		}
		return null;


	}

	public HashMap<UUID ,Integer> getTempBan(){
		return tempban;
	}
}

package ro.deiutzblaxo.Purgatory.Bungee.Factory;

import java.util.UUID;

import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class BanFactory {
	private MainBungee plugin;
	public BanFactory(MainBungee main) {
		plugin = main;
	}

	public void setBan(UUID uuid , String reason , String Name) {
		if(!isBan(uuid)) {
			plugin.getConfigManager().getBans().set(uuid.toString() + ".Reason", reason);
			if(plugin.getConfigManager().getConfig().getBoolean("Remove-Warnings-On-Ban")) {
				plugin.getConfigManager().loadWarnings();
				plugin.getConfigManager().getWarnings().set(uuid.toString(), null);
				plugin.getConfigManager().saveWarnings();
			}
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
	public void removeBan(UUID uuid) {
		if(isBan(uuid)) {
			plugin.getConfigManager().getBans().set(uuid.toString(), null);
			plugin.getConfigManager().saveBans();
			plugin.getProxy().getPlayer(uuid).setReconnectServer(plugin.getServerManager().getHubServer());
			String send = "unban";
			plugin.getSpigotCommunication().send(uuid, send.split("*"));
		}

	}


}

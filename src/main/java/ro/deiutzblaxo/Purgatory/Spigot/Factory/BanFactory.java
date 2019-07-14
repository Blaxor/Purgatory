package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class BanFactory {
	private MainSpigot plugin = MainSpigot.getInstance();

	public void setBan(Player player, String reason) {
		if(!plugin.isBungeeEnabled()) {
			if(!isBan(player)) {
				plugin.getConfigManager().loadBanDataBase();
				String uuid = player.getUniqueId().toString();
				plugin.getConfigManager().getBanDataBase().set(uuid + ".Reason", reason);
				plugin.getConfigManager().saveBanDataBase();
				plugin.getWarningFactory().removeWarning(player);
			}
		}

	}

	public void removeBan(Player player) {
		if(!plugin.isBungeeEnabled()) {
			if(isBan(player)) {
				plugin.getConfigManager().loadBanDataBase();
				String uuid = player.getUniqueId().toString();
				plugin.getConfigManager().getBanDataBase().set(uuid , null);
				plugin.getConfigManager().saveBanDataBase();
			}

		}

	}
	public boolean isBan(Player player) {
		String uuid = player.getUniqueId().toString();
		if(plugin.getConfigManager().getBanDataBase().contains(uuid)) {
			return true;
		}
		return false;
	}
	public void setTempBan(){

	}
	public void removeTempBan() {

	}
}

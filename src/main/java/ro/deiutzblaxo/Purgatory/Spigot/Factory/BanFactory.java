package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class BanFactory {
	private MainSpigot plugin = MainSpigot.getInstance();

	public void setBan(UUID uuid, String name, String reason) {
		if(!plugin.isBungeeEnabled()) {
			if(!isBan(uuid)) {
				plugin.getConfigManager().loadBanDataBase();
				plugin.getConfigManager().getBanDataBase().set(uuid + ".Name", name);
				plugin.getConfigManager().getBanDataBase().set(uuid + ".Reason", reason);

				plugin.getConfigManager().saveBanDataBase();
				plugin.getWarningFactory().removeWarning(uuid);
				plugin.getTaskFactory().setTasks(uuid);
				if(Bukkit.getPlayer(uuid).isOnline()) {
					plugin.getScoreBoardAPI().createScoreboard(Bukkit.getPlayer(uuid), plugin.getTaskFactory().getTasks());
					Player player = Bukkit.getPlayer(uuid);
					for(PotionEffect p : player.getActivePotionEffects()) {
						if(p.getType().equals(PotionEffectType.INVISIBILITY)) {
							player.removePotionEffect(p.getType());
							player.setAllowFlight(false);
							player.setCanPickupItems(true);

						}
					}
				}
			}
		}

	}

	public void removeBan(UUID uuid) {
		if(!plugin.isBungeeEnabled()) {

			plugin.getConfigManager().loadBanDataBase();
			plugin.getConfigManager().getBanDataBase().set(uuid.toString() , null);
			plugin.getConfigManager().saveBanDataBase();


		}

	}
	public boolean isBan(UUID uuid) {

		plugin.getConfigManager().loadBanDataBase();
		if(plugin.getConfigManager().getBanDataBase().contains(uuid.toString())) {
			return true;
		}
		return false;
	}
	public String reasonBan(UUID uuid) {
		plugin.getConfigManager().loadBanDataBase();
		return plugin.getConfigManager().getBanDataBase().getString(uuid + ".Reason");
	}
	public void setTempBan(UUID uuid, String name, String reason, Integer seconds){
		setBan(uuid , name ,reason);

		plugin.getConfigManager().loadBanDataBase();
		if(!isTempBan(uuid)) {
			plugin.getConfigManager().getBanDataBase().set(uuid + ".Seconds", seconds);
			plugin.TempBan.put(uuid, seconds);
		}else {//TODO ALL THIS ITSN`T GOOD
			plugin.getConfigManager().getBanDataBase().set(uuid + ".Seconds", seconds + getTime(uuid));
			plugin.TempBan.put(uuid, seconds + getTime(uuid));
		}
		plugin.getConfigManager().saveBanDataBase();
	}
	public void removeTempBan(UUID uuid) {
		removeBan(uuid);

	}
	public Boolean isTempBan(UUID uuid) {
		if(isBan(uuid)) {
			if(plugin.TempBan.containsKey(uuid)) {
				return true;
			}
		}
		return false;
	}
	public Integer getTime(UUID uuid) {
		return plugin.getConfigManager().getBanDataBase().getInt(uuid + ".Seconds");
	}

}

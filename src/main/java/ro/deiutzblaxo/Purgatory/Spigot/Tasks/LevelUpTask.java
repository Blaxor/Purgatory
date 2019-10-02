package ro.deiutzblaxo.Purgatory.Spigot.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class LevelUpTask implements Listener {
	private Integer newProgress;
	private MainSpigot plugin;
	public LevelUpTask(MainSpigot mainSpigot) {
		plugin = mainSpigot;
	}
	@EventHandler(ignoreCancelled =  true , priority = EventPriority.HIGHEST)
	public void onLevelUp(PlayerLevelChangeEvent e) {
		if(!(e.getPlayer() instanceof Player)) return;

		Player player = e.getPlayer();
		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			for(String task : plugin.getTaskFactory().getTasks()) {
				if(plugin.getTaskFactory().getType(task).equalsIgnoreCase("level_up")) {

					newProgress = e.getNewLevel();
					if(newProgress >= plugin.getTaskFactory().getCount(task)) {

						plugin.getBanFactory().removeBan(player.getUniqueId());
						plugin.getTaskFactory().removeTasks(player.getUniqueId());
						plugin.getScoreBoardAPI().removeScoreBroad(player);
						if(plugin.isBungeeEnabled()) {
							String[] send = "unban * test".split("*");
							plugin.getBungeeCommunication().send(player.getUniqueId(), send);
						}

					}else {

						plugin.getTaskFactory().setProgress(player.getUniqueId().toString(),task , newProgress);
						plugin.getScoreBoardAPI().updateScoreboard1(player, task, newProgress);

					}

				}
			}
		}
	}


}

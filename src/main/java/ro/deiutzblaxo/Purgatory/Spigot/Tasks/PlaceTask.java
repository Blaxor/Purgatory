package ro.deiutzblaxo.Purgatory.Spigot.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class PlaceTask implements Listener{

	private MainSpigot plugin;
	private Integer Progress , newProgress;

	public PlaceTask(MainSpigot mainSpigot) {
		plugin = mainSpigot;
	}

	@EventHandler(ignoreCancelled =  true , priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent e ) {
		Player player = e.getPlayer();
		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			for(String task : plugin.getTaskFactory().getTasks()) {
				if(plugin.getTaskFactory().getType(task).equalsIgnoreCase("place")) {
					if(plugin.getTaskFactory().isMaterial(task, e.getBlock().getType())) {
						Progress = plugin.getTaskFactory().getProgress(player.getUniqueId().toString(), task);
						newProgress = Progress + 1;
						if(newProgress >= plugin.getTaskFactory().getCount(task)) {

							plugin.getBanFactory().removeBan(player.getUniqueId());
							plugin.getTaskFactory().removeTasks(player.getUniqueId());
							plugin.getScoreBoardAPI().removeScoreBroad(player);

						}else {

							plugin.getTaskFactory().setProgress(player.getUniqueId().toString(),task , newProgress);
							plugin.getScoreBoardAPI().updateScoreboard1(player, task, newProgress);

						}
					}
				}
			}
		}

	}

}
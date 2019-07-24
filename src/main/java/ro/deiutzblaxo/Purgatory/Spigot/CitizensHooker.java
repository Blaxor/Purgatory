package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;

public class CitizensHooker implements Listener{
	private MainSpigot plugin;
	public CitizensHooker (MainSpigot main ) {
		plugin = main;
	}

	@EventHandler(priority = EventPriority.MONITOR , ignoreCancelled =  true)
	public void onTeleportCitisenz(PlayerTeleportEvent e) {

		if(plugin.getServer().getPluginManager().isPluginEnabled("Citizens")) {

			for(NPCRegistry npc :CitizensAPI.getNPCRegistries()) {
				if(npc.isNPC(e.getPlayer())) {
					for(PotionEffect effects :e.getPlayer().getActivePotionEffects()) {
						e.getPlayer().removePotionEffect(effects.getType());
					}

				}
			}


		}

	}




}

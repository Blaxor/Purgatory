//    Purgatory , a ban system for servers of Minecraft
//    Copyright (C) 2020  Deiutz
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
package ro.deiutzblaxo.Purgatory.Spigot.Hoockers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

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

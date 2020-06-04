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
package ro.deiutzblaxo.Purgatory.Spigot.Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class JustBungeeEvents implements Listener {
	private MainSpigot plugin;
	public JustBungeeEvents(MainSpigot main) {
		plugin = main;

	}

	@EventHandler(ignoreCancelled = true )
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		new BukkitRunnable() {

			@Override
			public void run() {
				if(plugin.getBanFactory().isBan(player.getUniqueId())) {

					plugin.getScoreBoardAPI().createScoreboard(player,  plugin.getTaskFactory().getTasks());
					for(PotionEffect p : player.getActivePotionEffects()) {
						if(p.getType().equals(PotionEffectType.INVISIBILITY)) {
							player.removePotionEffect(p.getType());

						}
					}
					player.setAllowFlight(false);
					player.setCanPickupItems(true);
					player.setGameMode(GameMode.SURVIVAL);
				}else {

					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
					player.setAllowFlight(true);
					player.setCanPickupItems(false);
					player.setGameMode(GameMode.CREATIVE);
				}

			}

		}.runTaskLater(plugin, 10);

	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent e) {
		Entity Damager = e.getDamager();
		Entity Damaged = e.getEntity();
		if(plugin.getBanFactory().isBan(Damaged.getUniqueId()) && Damager.getType().equals(EntityType.PLAYER)) {
			e.setCancelled(true);
		}
	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onDrop(PlayerDropItemEvent e) {
		if(!plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);

		}

	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onPickUp(EntityPickupItemEvent e) {
		if(e.getEntity() instanceof Player) {
			if(plugin.getBanFactory().isBan(e.getEntity().getUniqueId()))
				e.setCancelled(true);
		}
	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
			e.setCancelled(true);

		}

	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
			e.setCancelled(true);

		}

	}


}

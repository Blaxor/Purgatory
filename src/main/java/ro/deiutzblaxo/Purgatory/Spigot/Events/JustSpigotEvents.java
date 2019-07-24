package ro.deiutzblaxo.Purgatory.Spigot.Events;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;


@SuppressWarnings("deprecation")
public class JustSpigotEvents implements Listener{
	private	MainSpigot plugin;
	private ArrayList<Player> RespawnArrayList;
	public JustSpigotEvents(MainSpigot main){
		plugin = main;
		RespawnArrayList = new ArrayList<Player>();

		new BukkitRunnable() {

			@Override
			public void run() {
				if(RespawnArrayList.isEmpty()) {
					return;
				}
				try {
					for(Player player : RespawnArrayList) {
						player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
						RespawnArrayList.remove(player);
					}
				}catch(ConcurrentModificationException e){
				}
			}


		}.runTaskTimer(main, 0, 0);
	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			if(!player.getLocation().getWorld().getName().equalsIgnoreCase(plugin.getWorldManager().getPurgatory().getName())) {
				player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
			}else {
				if(plugin.getConfig().getBoolean("Force-Spawn-Purgatory-World")) {
					player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
				}
			}
			plugin.getScoreBoardAPI().createScoreboard(player,  plugin.getTaskFactory().getTasks());
		}else {
			if(plugin.getConfig().getBoolean("Force-Spawn-Default-World")) {
				player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
			}
		}
	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onTeleport(PlayerTeleportEvent e) {
		Player player = e.getPlayer();

		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			if(!e.getTo().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())) {
				e.setCancelled(true);
			}

		}else {
			if(e.getTo().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
				player.setAllowFlight(true);
				player.setCanPickupItems(false);
			}else {
				if(e.getFrom().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())) {
					for(PotionEffect effect: player.getActivePotionEffects()) {
						if(effect.getType().equals(PotionEffectType.INVISIBILITY)) {
							player.removePotionEffect(effect.getType());
						}
					}
					player.setAllowFlight(false);
					player.setCanPickupItems(true);
				}
			}

		}
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

		if(e.getPlayer().getWorld().getName().equalsIgnoreCase(plugin.getWorldManager().getPurgatory().getName())) {
			if(!plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
				e.setCancelled(true);
			}
		}

	}

	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onPickUp(PlayerPickupItemEvent e) {
		if(e.getPlayer().getWorld().getName().equalsIgnoreCase(plugin.getWorldManager().getPurgatory().getName())) {
			if(!plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onRespawn(PlayerRespawnEvent e){

		if(plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
			RespawnArrayList.add(e.getPlayer());

		}

	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if(player.getLocation().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())) {
			if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
				e.setCancelled(true);
			}
		}

	}
	@EventHandler(ignoreCancelled = true , priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if(player.getLocation().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())) {
			if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
				e.setCancelled(true);
			}
		}

	}



}

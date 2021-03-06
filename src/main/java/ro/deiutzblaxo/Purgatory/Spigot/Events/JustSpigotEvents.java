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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
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
			for(PotionEffect p : player.getActivePotionEffects()) {
				if(p.getType().equals(PotionEffectType.INVISIBILITY)) {
					player.removePotionEffect(p.getType());
					player.setAllowFlight(false);
					player.setCanPickupItems(true);

				}
			}
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
	@EventHandler
	public void onTabCompleter(TabCompleteEvent e) {


		String[] args = e.getBuffer().toLowerCase().split(" ");
		if(args.length >= 1) {

			if(args[0].startsWith("/")) {

				if(args[0].equalsIgnoreCase("/" + plugin.getConfig().getString("Command.Ban").toLowerCase())) {

					switch(args.length) {
					case 1 :


						for(Player player : plugin.getServer().getOnlinePlayers()) {
							e.getCompletions().add(player.getName());
						}
						break;
					default :
						e.getCompletions().clear();
						break;
					}
				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfig().getString("Command.Purge"))) {
					switch(args.length) {
					case 1 :


						for(Player player : plugin.getServer().getOnlinePlayers()) {
							e.getCompletions().add(player.getName());
						}
						break;
					default :
						e.getCompletions().clear();
						break;
					}

				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfig().getString("Command.Info"))) {
					switch(args.length) {
					case 1 :


						for(Player player : plugin.getServer().getOnlinePlayers()) {
							e.getCompletions().add(player.getName());
						}
						break;
					default :
						e.getCompletions().clear();
						break;
					}

				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfig().getString("Command.TempBan"))) {
					switch(args.length) {
					case 1 :


						for(Player player : plugin.getServer().getOnlinePlayers()) {
							e.getCompletions().add(player.getName());
						}
						break;
					default :
						e.getCompletions().clear();
						break;
					}

				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfig().getString("Command.Warning"))) {
					switch(args.length) {
					case 1 :


						for(Player player : plugin.getServer().getOnlinePlayers()) {
							e.getCompletions().add(player.getName());
						}
						break;
					default :
						e.getCompletions().clear();
						break;
					}

				}else {
					e.getCompletions().clear();
				}
			}
		}

	}
	@EventHandler
	public void Chat(AsyncPlayerChatEvent e) {
		try {
		Player player = e.getPlayer();
		if (!this.plugin.getBanFactory().isBan(player.getUniqueId())) {
			if(this.plugin.getBanFactory().getPlayerList() != null)
				if(this.plugin.getBanFactory().getPlayerList().size() != 0)
					e.getRecipients().removeAll(this.plugin.getBanFactory().getPlayerList());
		} else {
			e.getRecipients().clear();
			e.getRecipients().addAll(this.plugin.getBanFactory().getPlayerList());
			e.setFormat(ChatColor.translateAlternateColorCodes('&',
					String.valueOf(this.plugin.getConfigManager().getMessages().getString("BanChat.Prefix")
							.replaceAll("%player%", player.getDisplayName())) + e.getMessage()));
		}
	} catch (Exception e2) {
	}
	}
	
}


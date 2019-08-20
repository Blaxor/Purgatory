package ro.deiutzblaxo.Purgatory.Spigot.Troll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class Players implements Listener {
	private MainSpigot plugin;
	public Players(MainSpigot main) {
		plugin = main;

	}
	private ItemStack nothing , skull;
	public String PlayersInventoryName = "Cheaters";


	public Inventory getPlayersInventory() {
		Inventory inv = Bukkit.createInventory(null, 54 , PlayersInventoryName);
		if(Material.getMaterial("PINK_STAINED_GLASS_PANE") != null) {
			nothing = new ItemStack(Material.getMaterial("PINK_STAINED_GLASS_PANE"));
		}else {
			nothing = new ItemStack(Material.BARRIER);
		}
		for(int i=0 ; i <9 ; i++) {
			inv.setItem(i, nothing);
		}
		for (int i=45 ; i<54 ; i++) {
			inv.setItem(i, nothing);
		}
		inv.setItem(9 ,nothing);
		inv.setItem(17, nothing);
		inv.setItem(18 ,nothing);
		inv.setItem(27, nothing);
		inv.setItem(36 ,nothing);
		inv.setItem(26 ,nothing);
		inv.setItem(35, nothing);
		inv.setItem(44 ,nothing);


		for(Player player : plugin.getServer().getOnlinePlayers()) {
			if(plugin.getBanFactory().isBan(player.getUniqueId())) {
				if(Material.getMaterial("PLAYER_HEAD") != null) {
					skull = new ItemStack(Material.getMaterial("PLAYER_HEAD"));
					SkullMeta meta = (SkullMeta)skull.getItemMeta();
					meta.setOwner(player.getName());
					meta.setDisplayName(player.getName());
					skull.setItemMeta(meta);
				}else {
					skull = new ItemStack(Material.BOOK);
					ItemMeta meta = skull.getItemMeta();
					meta.setDisplayName(player.getName());
					skull.setItemMeta(meta);
				}

				inv.addItem(skull);
			}
		}
		return inv;
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Player user = (Player) e.getWhoClicked();

		if (e.getRawSlot() == e.getSlot() && e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', PlayersInventoryName))) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().hasItemMeta()) {
					if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
						Player player = plugin.getServer().getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
						user.teleport(player.getLocation());
						user.closeInventory();
					}
				}
			} else {
				user.closeInventory();

			}
		}

	}

}
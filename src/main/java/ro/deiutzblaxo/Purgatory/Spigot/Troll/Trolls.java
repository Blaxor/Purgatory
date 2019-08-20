package ro.deiutzblaxo.Purgatory.Spigot.Troll;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class Trolls {
	private MainSpigot plugin;
	private ItemStack Item;
	private String ItemTitle;
	private ArrayList<String> Lore;
	private ItemMeta Meta;
	public Trolls(MainSpigot main) {
		plugin = main;
	}

	public Inventory TrollsInventory(Player player) {
		Inventory inv = plugin.getServer().createInventory(null, 9, plugin.getConfigManager().getMessages().getString("Troll.Menu")); //TODO ADD TO MESSAGES

		if(player.hasPermission("purgatory.troll.smokescreen") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Title");
		}else {
			ItemTitle = ChatColor.RED+ plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Title");
		}
		Item = new ItemStack(Material.TNT);
		Meta.setDisplayName(ItemTitle);
		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);
		return inv;
	}



}

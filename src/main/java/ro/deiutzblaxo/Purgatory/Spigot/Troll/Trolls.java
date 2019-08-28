package ro.deiutzblaxo.Purgatory.Spigot.Troll;

import java.util.ArrayList;

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

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class Trolls implements Listener{
	private MainSpigot plugin;
	private ItemStack Item;
	private String ItemTitle ;
	private String cooldown = "%cooldown%";
	private ArrayList<String> Lore;

	private ItemMeta Meta;
	public Trolls(MainSpigot main) {
		plugin = main;
	}

	public Inventory TrollsInventory(Player player) {

		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.Menu")));

		if(player.hasPermission("purgatory.troll.smokescreen") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Title");
		}
		Item = new ItemStack(Material.TNT);
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.SmokeScreen") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.burnitem") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Burn.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Burn.Title");
		}
		Item = new ItemStack(Material.FLINT);
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();

		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Burn.Description").split("/n"))
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Burn") + ""));
		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.flip") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Flip.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Flip.Title");
		}
		Item = new ItemStack(Material.BRICK);
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Flip.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Flip") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.creeper") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Creeper.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Creeper.Title");
		}
		if(Material.getMaterial("CREEPER_SPAWN_EGG")!= null ) {

			Item = new ItemStack(Material.CREEPER_SPAWN_EGG);

		}else {

			Item = new ItemStack(Material.STONE);

		}
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Creeper.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Creeper") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.web") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Web.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Web.Title");
		}
		if(Material.getMaterial("COBWEB") != null) {
			Item = new ItemStack(Material.getMaterial("COBWEB"));
		}else {
			Item = new ItemStack(Material.getMaterial("WEB"));
		}
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Web.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Web") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.lag") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Lag.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Lag.Title");
		}
		Item = new ItemStack(Material.STICK);
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Lag.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Lag") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.mole") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Mole.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Mole.Title");
		}
		if(Material.getMaterial("GOLDEN_SHOVEL") != null) {

			Item = new ItemStack(Material.getMaterial("GOLDEN_SHOVEL"));
		}else {
			Item = new ItemStack(Material.getMaterial("GOLD_SPADE"));
		}
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Mole.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Mole") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.infection") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Infection.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Infection.Title");
		}
		Item = new ItemStack(Material.SPIDER_EYE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Infection.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Infection") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.storm") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Storm.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Storm.Title");
		}
		Item = new ItemStack(Material.HOPPER);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Storm.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Storm") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.miner") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Miner.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Miner.Title");
		}
		Item = new ItemStack(Material.STONE_PICKAXE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Miner.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Miner") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.mobsquad") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Title");
		}
		if(Material.getMaterial("SPAWNER") != null) {
			Item = new ItemStack(Material.getMaterial("SPAWNER"));
		}else {
			Item = new ItemStack(Material.getMaterial("MOB_SPAWNER"));
		}

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.MobSquad") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.paralysis") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Title");
		}
		Item = new ItemStack(Material.STONE_AXE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Paralysis") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.pumpkin") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Title");
		}
		Item = new ItemStack(Material.PUMPKIN);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Pumpkin") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.bouncy") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Title");
		}
		Item = new ItemStack(Material.SPONGE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Bouncy") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.slow") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Slow.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Slow.Title");
		}
		Item = new ItemStack(Material.FERMENTED_SPIDER_EYE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Slow.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Slow") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.jumpboost") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Title");
		}
		Item = new ItemStack(Material.BEACON);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.JumpBoost") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.speed") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Speed.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Speed.Title");
		}
		Item = new ItemStack(Material.SUGAR);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Speed.Description").split("/n")) {
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Speed") + ""));
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		return inv;



		//		ChatColor.valueOf(Item.getItemMeta().getDisplayName());
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player user = (Player) e.getWhoClicked();

		if (e.getRawSlot() == e.getSlot() && e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&',
				plugin.getConfigManager().getMessages().getString("Troll.Menu"))))) {

			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR
					&& e.getCurrentItem().getType() != Material.getMaterial("PINK_STAINED_GLASS_PANE") && e.getCurrentItem().getType() != Material.BARRIER) {
				if (e.getCurrentItem().hasItemMeta()) {
					if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Title"))){

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();


						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Burn.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Flip.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Creeper.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Web.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Lag.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Mole.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Infection.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Storm.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Miner.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Slow.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Speed.Title"))) {

							ItemStack Item = e.getCurrentItem();
							user.getInventory().addItem(Item);
							user.closeInventory();
						}else {
							user.closeInventory();
							user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("NoPermission")));
						}
					}
				}
			}else {
				user.closeInventory();
			}



		}
	}
}

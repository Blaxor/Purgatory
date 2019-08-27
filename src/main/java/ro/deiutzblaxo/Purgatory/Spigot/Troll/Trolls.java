package ro.deiutzblaxo.Purgatory.Spigot.Troll;

import java.util.ArrayList;

import org.bukkit.Bukkit;
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
	private String ItemTitle ;
	private String cooldown = "%cooldown%";
	private ArrayList<String> Lore;

	private ItemMeta Meta;
	public Trolls(MainSpigot main) {
		plugin = main;
	}

	public Inventory TrollsInventory(Player player) {
		player.sendMessage("test");
		Inventory inv = Bukkit.createInventory(null, 9, plugin.getConfigManager().getMessages().getString("Troll.Menu"));

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
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.BurnItem.Title");
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.BurnItem.Title");
		}
		Item = new ItemStack(Material.FLINT);
		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();

		for(String str : plugin.getConfigManager().getMessages().getString("Troll.BurnItem.Description").split("/n"))
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.BurnItem") + ""));
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
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Flip") + "")); //TODO ADD TO CONFIG
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
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Creeper") + "")); //TODO ADD TO CONFIG
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
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Web") + "")); //TODO ADD TO CONFIG
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
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Lag") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.mole") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Mole.Title");//TODO ADD TO MESSAGES
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Mole.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Mole") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.infection") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Infection.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Infection.Title");
		}
		Item = new ItemStack(Material.SPIDER_EYE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Infection.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Infection") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.storm") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Storm.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Storm.Title");
		}
		Item = new ItemStack(Material.HOPPER);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Storm.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Storm") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.miner") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Miner.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Miner.Title");
		}
		Item = new ItemStack(Material.STONE_PICKAXE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Miner.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Miner") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.mobsquad") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Title");//TODO ADD TO MESSAGES
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.MobSquad") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.paralysis") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Title");
		}
		Item = new ItemStack(Material.STONE_AXE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Paralysis") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);


		if(player.hasPermission("purgatory.troll.pumpkin") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Title");
		}
		Item = new ItemStack(Material.PUMPKIN);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Pumpkin") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.bouncy") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Title");
		}
		Item = new ItemStack(Material.PUMPKIN);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Bouncy") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.slow") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Slow.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Slow.Title");
		}
		Item = new ItemStack(Material.FERMENTED_SPIDER_EYE);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Slow.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Slow") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.jumpboost") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Title");
		}
		Item = new ItemStack(Material.BEACON);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.JumpBoost") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		if(player.hasPermission("purgatory.troll.speed") || player.hasPermission("purgatory.troll.*")) {
			ItemTitle = ChatColor.GREEN + plugin.getConfigManager().getMessages().getString("Troll.Speed.Title");//TODO ADD TO MESSAGES
		}else {
			ItemTitle = ChatColor.RED + plugin.getConfigManager().getMessages().getString("Troll.Speed.Title");
		}
		Item = new ItemStack(Material.SUGAR);

		Meta = Item.getItemMeta();
		Meta.setDisplayName(ItemTitle);
		Lore = new ArrayList<String>();
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Speed.Description").split("/n")) {//TODO ADD TO MESSAGES
			Lore.add(ChatColor.translateAlternateColorCodes('&', str).replaceAll(cooldown, plugin.getConfig().getInt("Troll.Cooldown.Speed") + "")); //TODO ADD TO CONFIG
		}

		Meta.setLore(Lore);
		Item.setItemMeta(Meta);
		inv.addItem(Item);

		return inv;



		//		ChatColor.valueOf(Item.getItemMeta().getDisplayName());
	}



}

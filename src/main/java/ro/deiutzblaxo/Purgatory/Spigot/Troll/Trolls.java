package ro.deiutzblaxo.Purgatory.Spigot.Troll;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
		for (String str : plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Description")
				.split("%newline%")) {
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

		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Burn.Description").split("%newline%"))
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Flip.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Creeper.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Web.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Lag.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Mole.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Infection.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Storm.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Miner.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Slow.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Description").split("%newline%")) {
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
		for(String str : plugin.getConfigManager().getMessages().getString("Troll.Speed.Description").split("%newline%")) {
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
	@EventHandler(priority = EventPriority.HIGHEST )
	public void onTrollUssage(EntityDamageByEntityEvent event) {

		if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player)) return;
		Player user = (Player) event.getDamager();
		Player cheater = (Player) event.getEntity();
		if(user.getLocation().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())
				&& cheater.getLocation().getWorld().getName().equals(plugin.getWorldManager().getPurgatory().getName())) {
			event.setDamage(0.00);

			if(user.getInventory().getItemInMainHand() != null) {

				if(user.getInventory().getItemInMainHand().hasItemMeta()) {

					if(user.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {

						if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.SmokeScreen.Title"))) {
							if(!plugin.SmokeScreen.containsKey(user.getUniqueId())) {
								plugin.SmokeScreen.put(user.getUniqueId(), plugin.getConfig().getInt("Troll.Cooldown.SmokeScreen"));
								plugin.SmokeScreen_Effect.put(cheater.getUniqueId() , 100);
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&',
										plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.SmokeScreen.get(user.getUniqueId()) + "")));
							}

						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Burn.Title"))) {

							if(!plugin.Burn.containsKey(user.getUniqueId())) {
								plugin.Burn.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Burn"));
								plugin.Burn_Effect.put(cheater.getLocation() , 1);
								cheater.getLocation().getBlock().setType(Material.FIRE);
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Burn.get(user.getUniqueId()) + "")));
							}



						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Flip.Title"))) {

							if(!plugin.Flip.containsKey(user.getUniqueId())) {
								cheater.teleport(cheater.getLocation().setDirection(cheater.getLocation().getDirection().multiply(-1)));
								plugin.Flip.put(user.getUniqueId(), plugin.getConfig().getInt("Troll.Cooldown.Flip"));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Flip.get(user.getUniqueId()) + "")));
							}

						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Creeper.Title"))) {
							if(!plugin.Creeper.containsKey(user.getUniqueId())) {
								Sound creeper ;
								if(Sound.valueOf("ENTITY_CREEPER_PRIMED") !=null) {
									creeper = Sound.valueOf("ENTITY_CREEPER_PRIMED");
								}else {
									creeper = Sound.valueOf("CREEPER_HISS");
								}
								cheater.getWorld().playSound(user.getLocation().setDirection(user.getLocation().getDirection().multiply(-1)), creeper, 100, 0);
								plugin.Creeper.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Creeper"));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Creeper.get(user.getUniqueId()) + "")));
							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Web.Title"))) {
							if(!plugin.Web.containsKey(user.getUniqueId())) {
								cheater.getLocation().getBlock().setType(Material.COBWEB);
								plugin.Web.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Web"));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Web.get(user.getUniqueId()) + "")));
							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Lag.Title"))) {
							if(!plugin.Lag.containsKey(user.getUniqueId())) {
								plugin.Lag_Effect.put(cheater.getUniqueId(), 3.0);
								plugin.Lag.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Lag"));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Lag.get(user.getUniqueId()) + "")));
							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Mole.Title"))) {
							if(!plugin.Mole.containsKey(user.getUniqueId())) {
								plugin.Mole.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Mole"));
								Location loc =cheater.getLocation();
								loc.setY(loc.getY()-5.0);
								cheater.teleport(loc);

							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Mole.get(user.getUniqueId()) + "")));
							}

						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Infection.Title"))) {
							if(!plugin.Infection.containsKey(user.getUniqueId())) {
								plugin.Infection.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Infection"));
								cheater.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION , (10*20) , 4));

							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Infection.get(user.getUniqueId()) + "")));
							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Storm.Title"))) {
							if(!plugin.Storm.containsKey(user.getUniqueId())) {
								plugin.Storm.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Storm"));
								Location loc =cheater.getLocation().clone();

								cheater.setVelocity(new Vector(loc.getX() , loc.getBlockY(), loc.getZ()));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Storm.get(user.getUniqueId()) + "")));
							}

						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.MobSquad.Title"))) {
							if(!plugin.MobSquad.containsKey(user.getUniqueId())) {
								plugin.MobSquad.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Miner"));
								for(int i = 0 ; i <= 5 ; i++) {
									cheater.getLocation().getWorld().spawnEntity(cheater.getLocation(), spawnRandomMobs());
								}


							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.MobSquad.get(user.getUniqueId()) + "")));
							}

						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Paralysis.Title"))) {
							if(!plugin.Paralysis.containsKey(user.getUniqueId())) {
								plugin.Paralysis.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Paralysis"));
								cheater.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING , 10*20 ,10));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Paralysis.get(user.getUniqueId()) + "")));

							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Pumpkin.Title"))) {
							if(!plugin.Pumpkin.containsKey(user.getUniqueId())) {
								plugin.Pumpkin.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Pumpkin"));
								if(cheater.getInventory().getHelmet() != null) {
									cheater.getInventory().addItem(cheater.getInventory().getHelmet());
								}
								cheater.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Pumpkin.get(user.getUniqueId()) + "")));

							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Bouncy.Title"))) {
							if(!plugin.Bouncy.containsKey(user.getUniqueId())) {
								plugin.Bouncy.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Bouncy"));
								cheater.teleport(cheater.getLocation().add(0, 25, 0));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Bouncy.get(user.getUniqueId()) + "")));

							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Slow.Title"))) {
							if(!plugin.Slow.containsKey(user.getUniqueId())) {
								plugin.Slow.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Slow"));
								cheater.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,5*20,4));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Slow.get(user.getUniqueId()) + "")));

							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.JumpBoost.Title"))) {
							if(!plugin.JumpBoost.containsKey(user.getUniqueId())) {
								plugin.JumpBoost.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.JumpBoost"));
								cheater.addPotionEffect(new PotionEffect(PotionEffectType.JUMP , 10*20 , 10));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.JumpBoost.get(user.getUniqueId()) + "")));

							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Speed.Title"))) {
							if(!plugin.Speed.containsKey(user.getUniqueId())) {
								plugin.Speed.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Speed"));
								cheater.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20 , 100));
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Speed.get(user.getUniqueId()) + "")));

							}
						}else if(user.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN +
								plugin.getConfigManager().getMessages().getString("Troll.Miner.Title"))) {
							if(!plugin.Miner.containsKey(user.getUniqueId())) {
								plugin.Miner.put(user.getUniqueId() , plugin.getConfig().getInt("Troll.Cooldown.Miner"));
								plugin.Miner_Effect.put(cheater.getUniqueId(), 20);
							}else {
								user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Troll.onCooldown")
										.replaceAll("%cooldown%", plugin.Miner.get(user.getUniqueId()) + "")));

							}
						}
					}
				}
			}
		}
	}

	public EntityType spawnRandomMobs() {
		ArrayList<EntityType> e = new ArrayList<>();
		e.add(EntityType.BLAZE);
		e.add(EntityType.CREEPER);
		e.add(EntityType.CHICKEN);
		e.add(EntityType.HORSE);
		e.add(EntityType.ZOMBIE);
		e.add(EntityType.SKELETON);
		e.add(EntityType.PIG);
		e.add(EntityType.SPIDER);
		e.add(EntityType.CAVE_SPIDER);
		e.add(EntityType.SILVERFISH);


		int index = new Random().nextInt(e.size());

		return e.get(index);

	}
}

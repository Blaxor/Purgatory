package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;



public class PlaceHolderHooker extends PlaceholderExpansion {
	private final MainSpigot plugin;

	public PlaceHolderHooker(MainSpigot plugin) {this.plugin = plugin;}

	@Override
	public String onPlaceholderRequest(Player p, String params) {
		for(String task : plugin.getTaskFactory().getTasks()) {
			if(params.equalsIgnoreCase(task + "_progress"))
				return String.valueOf(plugin.getTaskFactory().getProgress(p.getUniqueId().toString(), task));
			if(params.equalsIgnoreCase(task + "_count")) {
				return String.valueOf(plugin.getTaskFactory().getCount(task));
			}
			if(params.equalsIgnoreCase("warnings")) {
				return String.valueOf(plugin.getWarningFactory().getWarningNumber(p));
			}
			if(params.equalsIgnoreCase("warnings_max")) {
				return String.valueOf(plugin.getConfig().getInt("MaxWarnings"));
			}
		}
		return null;
	}
	@Override
	public String getAuthor() {
		return "Deiutz";
	}

	@Override
	public String getIdentifier() {
		return "purgatory";
	}

	@Override
	public String getVersion() {

		return this.plugin.getDescription().getVersion();
	}

}
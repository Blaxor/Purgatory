package ro.deiutzblaxo.Purgatory.Bungee;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;

public class ServerManager {
	protected MainBungee plugin;
	protected ServerManager(MainBungee main){
		plugin = main;
		if(getPurgatoryServer() == null) {
			plugin.getProxy().getConsole().sendMessage(new TextComponent(ChatColor.GRAY + "[" + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.GRAY + "]" + ChatColor.DARK_RED + "ATTENTION !! Purgatory Server don`t exist , please check your config."));
		}
		if(getHubServer() == null) {
			plugin.getProxy().getConsole().sendMessage(new TextComponent(ChatColor.GRAY + "[" + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.GRAY + "]" + ChatColor.DARK_RED + "ATTENTION !! Hub Server don`t exist , please check your config."));
		}
		if(!ServerOnline(getPurgatoryServer())) {
			plugin.getProxy().getConsole().sendMessage(new TextComponent(ChatColor.GRAY + "[" + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.GRAY + "]" + ChatColor.DARK_RED + "ATTENTION !! Purgatory Server is offline , please open the server!"));
		}
		if(!ServerOnline(getHubServer())) {
			plugin.getProxy().getConsole().sendMessage(new TextComponent(ChatColor.GRAY + "[" + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.GRAY + "]" + ChatColor.DARK_RED + "ATTENTION !! Hub Server is offline , please open the server!"));
		}
	}
	public boolean CheckServer(String server) {
		if(plugin.getProxy().getServerInfo(server) != null) {
			return true;
		}
		return false;


	}
	public ServerInfo getPurgatoryServer() {
		if(CheckServer(plugin.getConfigManager().getConfig().getString("Purgatory-Server"))) {
			return plugin.getProxy().getServerInfo(plugin.getConfigManager().getConfig().getString("Purgatory-Server"));
		}
		return null;
	}
	public ServerInfo getHubServer() {
		if(CheckServer(plugin.getConfigManager().getConfig().getString("Hub-Server"))) {
			return plugin.getProxy().getServerInfo(plugin.getConfigManager().getConfig().getString("Hub-Server"));
		}
		return null;
	}

	public boolean ServerOnline(ServerInfo server) {

		if(server == getPurgatoryServer()) {
			try {
				Socket s = new Socket(server.getAddress().getHostString(), server.getAddress().getPort());
				s.close();
				return true;
			} catch (UnknownHostException e) {
				return false;

			} catch (IOException e) {
				return false;
			}catch (NullPointerException e) {}
		}
		if(server == getHubServer()) {
			try {
				Socket s =new Socket(server.getAddress().getHostString(), server.getAddress().getPort());
				s.close();
				return true;
			} catch (UnknownHostException e) {
				return false;

			} catch (IOException e) {
				return false;
			}catch (NullPointerException e) {}
		}
		return false;

	}

}

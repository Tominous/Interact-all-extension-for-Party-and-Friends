package de.simonsator.partyandfriends.interactall;

import de.simonsator.partyandfriends.main.Main;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 18.09.16
 */
public class IAMain extends Plugin {
	private IAConfigLoader config;
	private static IAMain instance;

	public static IAMain getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		try {
			config = new IAConfigLoader(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.getInstance().getFriendsCommand().addCommand(
				new AcceptAll(config.getCreatedConfiguration().getStringList("AcceptAll.Names").toArray(new String[0]),
						config.getCreatedConfiguration().getInt("AcceptAll.Priority"),
						config.getCreatedConfiguration().getString("Messages.AcceptAll.Help")));
	}

	public Configuration getConfig() {
		return config.getCreatedConfiguration();
	}
}

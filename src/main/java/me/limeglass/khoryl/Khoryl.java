package me.limeglass.khoryl;

import java.io.File;
import java.io.IOException;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public final class Khoryl extends JavaPlugin {

	private static Khoryl instance;
	private boolean runtimeErrors;
	private SkriptAddon addon;

	@Override
	public void onEnable() {
		instance = this;
		File configFile = new File(getDataFolder(), "config.yml");
		//If newer version was found, update configuration.
		int version = 1;
		if (version != getConfig().getInt("configuration-version", version)) {
			if (configFile.exists())
				configFile.delete();
		}
		saveDefaultConfig();
		try {
			addon = Skript.registerAddon(this)
					.loadClasses("me.limeglass.khoryl", "elements")
					.setLanguageFileDirectory("lang");
		} catch (IOException e) {
			e.printStackTrace();
		}
		runtimeErrors = getConfig().getBoolean("console-runtime-errors", true);
		Metrics metrics = new Metrics(this, 5676);
		metrics.addCustomChart(new SimplePie("runtime_errors", () -> runtimeErrors + ""));
		Bukkit.getConsoleSender().sendMessage("[Khoryl] Khoryl has been enabled!");
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static Khoryl getInstance() {
		return instance;
	}

	public boolean canRuntimeError() {
		return runtimeErrors;
	}

}

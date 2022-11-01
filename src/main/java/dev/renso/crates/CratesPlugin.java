package dev.renso.crates;

import dev.renso.crates.commands.CratesCommand;
import dev.renso.crates.listener.CratesListener;
import dev.renso.crates.listener.MenuListener;
import dev.renso.crates.manager.CratesManager;
import dev.renso.crates.manager.MenuManager;
import dev.renso.crates.utils.ConfigFile;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CratesPlugin extends JavaPlugin {

    public ConfigFile langFile, cratesFile;
    public CratesManager cratesManager;
    public MenuManager menuManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        langFile = new ConfigFile("language.yml");
        cratesFile = new ConfigFile("crates.yml");

        cratesManager = new CratesManager();
        menuManager = new MenuManager();

        this.getCommand("crates").setExecutor(new CratesCommand());

        Bukkit.getPluginManager().registerEvents(new CratesListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static CratesPlugin getInstance(){
        return JavaPlugin.getPlugin(CratesPlugin.class);
    }

}

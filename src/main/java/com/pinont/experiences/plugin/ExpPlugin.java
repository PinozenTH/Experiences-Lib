package com.pinont.experiences.plugin;

import com.pinont.experiences.api.menu.MenuListener;
import com.pinont.experiences.api.utils.Common;
import com.pinont.experiences.api.utils.texts.Message;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class ExpPlugin extends JavaPlugin {

    public static JavaPlugin plugin;
    public static ExpPlugin instance;

    @Getter
    public static final double pluginConfigVersion = 1.0;
    @Getter
    public static final String apiVersion = "2.03-snapshot";

    @NotNull
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract void onPluginStart();

    public abstract void onPluginStop();

    public static ExpPlugin getInstance() {
        if (instance == null) {
            try {
                instance = JavaPlugin.getPlugin(ExpPlugin.class);

            } catch (final IllegalStateException ex) {
                if (Bukkit.getPluginManager().getPlugin("PlugManX") != null)
                    Bukkit.getLogger().severe("Failed to get instance of the plugin, if you reloaded using PlugManX you need to do a clean restart instead.");

                throw ex;
            }

            Objects.requireNonNull(instance, "Cannot get a new instance! Have you reloaded?");
        }

        return instance;
    }

    @Override
    public final void onEnable() {
        plugin = this;
        setup(this);
        this.onPluginStart();

        // register my Listener
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        try {
            AutoRegisterScanner.scanAndRegister();
        } catch (final Throwable t) {
            Common.sneaky(t);
        }
    }

    @Override
    public final void onDisable() {
        this.onPluginStop();
    }

    private void setup(@NotNull JavaPlugin plugin) {
        ExpPlugin.plugin = plugin;
        new Message().sendConsole(ChatColor.GREEN + "Launching " + plugin.getName() + " with ExperiencesAPI version " + apiVersion);
    }
}



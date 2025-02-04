package com.pinont.experiences.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class SimpleCommand extends Command implements CommandExecutor {


    public SimpleCommand(@NotNull String name) {
        super(name);
    }

    public void register() {
        if (Bukkit.getPluginCommand(getName()) != null) {
            Objects.requireNonNull(Bukkit.getPluginCommand(getName())).setExecutor(this);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cCommand " + getName() + " not found!");
        }
    }

    @Override
    public final boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equals(getName())) {
            return execute(commandSender, s, strings);
        }
        return false;
    }

}


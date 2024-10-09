package com.pinont.piXLib.api.utils.texts;

import com.pinont.piXLib.PiXPlugin;
import com.pinont.piXLib.api.utils.enums.DebugType;
import com.pinont.piXLib.api.utils.enums.LoggerType;
import com.pinont.piXLib.api.utils.enums.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class Message extends PiXPlugin {

    private static final boolean bypass_perm = getPlugin().getConfig().getBoolean("debug.bypass-permission");
    public Logger log = getPlugin().getLogger();
    private String message;
    private String permission = null;
    private LoggerType loggerType = null;
    private MessageType messageType = null;
    public Player p;

    public Message(String message) {
        this.message = message;
    }


    public void send() {
        if (messageType != null) {
            if (MessageType.CONSOLE.equals(messageType)) {
                log.info(message);
                new Debug("Message sent to console: " + message).setDebugType(DebugType.INFO).send();
            } else if (MessageType.PLAYER.equals(messageType)) {
                p.sendMessage(message);
            } else if (MessageType.ALLPLAYER.equals(messageType)) {
                if (!getServer().getOnlinePlayers().isEmpty()) {
                    for (Player player : getServer().getOnlinePlayers()) {
                        player.sendMessage(message);
                        new Debug("Message sent to player: " + player.getName() + " with message: " + message).setDebugType(DebugType.BOTH).send();
                        player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        new Debug("Sound played to player: " + player.getName()).setDebugType(DebugType.BOTH).send();
                    }
                }
            } else if (MessageType.BOTH.equals(messageType)) {
                log.info(message);
                new Debug("Message sent to console: " + message).setDebugType(DebugType.INFO).send();
                if (!getServer().getOnlinePlayers().isEmpty()) {
                    for (Player player : getServer().getOnlinePlayers()) {
                        if (player.hasPermission("pipermission.notify") || bypass_perm) {
                            player.sendMessage(message);
                            new Debug("Message sent to player: " + player.getName() + " with message: " + message).setDebugType(DebugType.BOTH).send();
                            player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                            new Debug("Sound played to player: " + player.getName()).setDebugType(DebugType.BOTH).send();
                        }
                    }
                }
            }
        }
        if (loggerType != null) {
            if (loggerType.equals(LoggerType.INFO)) {
                log.info(message);
            } else if (loggerType.equals(LoggerType.WARNING)) {
                log.warning(message);
            } else if (loggerType.equals(LoggerType.SEVERE)) {
                log.severe(message);
            }
            return;
        }
        if (permission != null) {
            p.sendMessage(message);
            if (permission != "pixLib.denied") {
                p.sendMessage(message);
            }
        } else {
            p.sendMessage(message);
        }
    }

    public void reply(CommandSender sender) {
        if (sender instanceof Player)
            new Message(message).setPlayer((Player) sender).send();
        else
            new Message("You don't have permission to use this command!");
    }

    public Message colorize() {
        this.message = ChatColor.translateAlternateColorCodes('&', message);
        return this;
    }

    public Message setMessageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public Message setLoggerType(LoggerType messageType) {
        this.loggerType = messageType;
        return this;
    }

    public Message setPlayer(Player player) {
        if (player != null) {
            p = player;
        }
        return this;
    }

    public Message requirePermission(String permission) {
        if (p.hasPermission(permission) || bypass_perm) {
            this.permission = permission;
        } else if (!p.hasPermission(permission)) {
            this.permission = "pixLib.denied";
        }
        return this;
    }
}
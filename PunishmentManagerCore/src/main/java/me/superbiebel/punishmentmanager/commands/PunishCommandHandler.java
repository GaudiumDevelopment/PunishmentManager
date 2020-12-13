package me.superbiebel.punishmentmanager.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import org.bukkit.command.CommandSender;

public class PunishCommandHandler {
    @CommandMethod("betterpunish <testinput>")
    public void punishcommand(CommandSender sender, @Argument("testinput") String testinput){
        sender.sendMessage(testinput);
    }
}

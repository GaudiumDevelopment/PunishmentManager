package me.superbiebel.punishmentmanager.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptException;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.PermissionUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SystemCommand{
    @CommandMethod("pmanager <subcommand>")
    @CommandPermission("punishmentmanager.command.system")
    public void systemCommand(@NotNull CommandSender sender, @Argument(value = "subcommand", suggestions = "systemCommandSubCommandArgSuggestion") String subcommand) throws ScriptException {
                if (subcommand.equalsIgnoreCase("help")) {

                    sender.sendMessage("Check out the wiki if you need help");

                } else if (subcommand.equalsIgnoreCase("reloadconfig")) {
                    if (PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.reloadconfig")) {
                        if (PunishmentManager.giveConfig().getBoolean("dev")) {
                            sender.sendMessage("Reloading config...");
                            PunishmentManager.getPlugin().reloadConfig();
                            sender.sendMessage("Config reloaded");
                        } else {
                            sender.sendMessage("");
                        }

                    }

                } else if (subcommand.equalsIgnoreCase("sync")) {
                    if (PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.sync")) {
                        sender.sendMessage("Syncing...");
                        sender.sendMessage("Sync complete (fake)");
                    }

                } else if (subcommand.equalsIgnoreCase("test")) {
                    sender.sendMessage("pong!");
                } else {
                    sender.sendMessage("Arguments not recognised:" + subcommand);
                }
    }
    
    
    @Suggestions("systemCommandSubCommandArgSuggestion")
    public List<String> subcommandArgSuggestion(CommandContext<CommandSender> context, String input) {
        List<String> tabComplete = new ArrayList<>();
        if (context.getSender().hasPermission("punishmentmanager.command.system.help")&&("help".contains(input) || input == "")){
            tabComplete.add("help");
        }
        if (context.getSender().hasPermission("punishmentmanager.command.system.reloadconfig")&&("reloadconfig".contains(input) || input == "")){
            tabComplete.add("reloadconfig");
        }
        if (context.getSender().hasPermission("punishmentmanager.command.system.sync")&&("sync".contains(input) || input == "")){
            tabComplete.add("sync");
        }
        if (context.getSender().hasPermission("punishmentmanager.command.system.test")&&("test".contains(input) || input == "")){
            tabComplete.add("test");
        }
        return tabComplete;
    }
        
        
        
        
        
        
        
        
        
        /*if (args.length == 1) {
            if (commandSender.hasPermission("punishmentmanager.command.system.help") && (args[0].equalsIgnoreCase("")|"help".contains(args[0]))){
                tabComplete.add("help");
            }
            if (commandSender.hasPermission("punishmentmanager.command.system.reloadconfig") && (args[0].equalsIgnoreCase("")|"reloadconfig".contains(args[0]))){
               tabComplete.add("reloadconfig");
            }
            if (commandSender.hasPermission("punishmentmanager.command.system.sync") && (args[0].equalsIgnoreCase("")|"sync".contains(args[0]))){
                tabComplete.add("sync");
            }
            if (commandSender.hasPermission("punishmentmanager.command.system.log") && (args[0].equalsIgnoreCase("")|"log".contains(args[0]))){
                tabComplete.add("log");
            }

        } /*else if (args.length == 2 && args[1].equals("log")){
            if (commandSender.hasPermission("punishmentmanager.command.system.log.debug")) {
                tabComplete.add("debug");
            }
            if (commandSender.hasPermission("punishmentmanager.command.system.log.info")) {
                tabComplete.add("info");
            }
            if (commandSender.hasPermission("punishmentmanager.command.system.log.warning")) {
                tabComplete.add("warning");
            }
            if (commandSender.hasPermission("punishmentmanager.command.system.log.fatalerror")) {
                tabComplete.add("fatalerror");
            }
        }*/
}
//FIXME doesnt work i dunno why (low priority)
                /*} else if (args[0].equalsIgnoreCase("log")) {
                    String logLevel = "info";
                    boolean skipNextCheck = false;
                    StringBuilder builder = new StringBuilder();

                    boolean sendInConsole = true;
                    boolean sendInGame = false;
                    boolean logToFile = true;
                    String executorname = "console";
                    for (int i = 0; i < args.length; i++) {
                        if (skipNextCheck) {
                            skipNextCheck = false;
                            continue;
                        }
                        if (!(i == 0 | i == 1)) {
                            continue;
                        }
                        if (args[i].startsWith("-loglevel:")) {
                            String logLevelArg = args[i];
                            logLevelArg = logLevelArg.replaceAll("-loglevel", "");
                            if (logLevelArg.equalsIgnoreCase("")) {
                                if (args.length == i + 1) {
                                    logLevel = args[i + 1];
                                }
                                skipNextCheck = true;
                            } else {
                                logLevel = logLevelArg;
                            }
                        } else if (args[i].startsWith("-sendinconsole:")) {
                            String sendInConsoleArg = args[1];
                            sendInConsoleArg = sendInConsoleArg.replaceAll("-sendinconsole:", "");
                            if (sendInConsoleArg.equalsIgnoreCase("")) {
                                if (args.length == i + 1) {
                                    sendInConsole = Boolean.parseBoolean(args[i + 1]);
                                }
                                skipNextCheck = true;
                            } else {
                                sendInConsole = Boolean.parseBoolean(sendInConsoleArg);
                            }
                        } else if (args[i].startsWith("-sendingame:")) {
                            String sendInGameArg = args[1];
                            sendInGameArg = sendInGameArg.replaceAll("-sendingame:", "");
                            if (sendInGameArg.equalsIgnoreCase("")) {
                                if (args.length == i + 1) {
                                    sendInGame = Boolean.parseBoolean(args[i + 1]);
                                }
                            } else {
                                sendInGame = Boolean.parseBoolean(sendInGameArg);
                            }
                        } else if (args[i].startsWith("-logtofile:")) {
                            String logToFileArg = args[1];
                            logToFileArg = logToFileArg.replaceAll("-sendingame:", "");
                            if (logToFileArg.equalsIgnoreCase("")) {
                                if (args.length == i + 1) {
                                    sendInGame = Boolean.parseBoolean(args[i + 1]);
                                }
                            } else {
                                sendInGame = Boolean.parseBoolean(logToFileArg);
                            }
                        } else if (args[i].startsWith("-executorname:")) {
                            String executornameArg = args[i];
                            executornameArg = executornameArg.replaceAll("-executorname", "");
                            if (executornameArg.equalsIgnoreCase("")) {
                                if (args.length == i + 1) {
                                    executorname = args[i + 1];
                                }
                                skipNextCheck = true;
                            } else {
                                executorname = executornameArg;
                            }

                        } else {
                            builder.append(args[i] + " ");
                        }

                    }
                    Log.log(builder.toString(),Log.convertToLogLevel(logLevel),sendInGame,sendInConsole,logToFile,executorname);*/
package me.superbiebel.punishmentmanager.commands;

import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.utils.PermissionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.graalvm.polyglot.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SystemCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
                if (args.length == 0) {

                    sender.sendMessage("1 argument is required");

                } else if (args[0].equalsIgnoreCase("help")) {

                    sender.sendMessage("Check out the wiki if you need help");

                } else if (args[0].equalsIgnoreCase("reloadconfig")) {
                    if (PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.reloadconfig")) {
                        sender.sendMessage("Reloading config...");
                        PunishmentManager.getPlugin().reloadConfig();
                        sender.sendMessage("Config reloaded");
                    }

                } else if (args[0].equalsIgnoreCase("sync")) {
                    if (PermissionUtils.checkAndMessage(sender, "punishmentmanager.command.system.sync")) {
                        sender.sendMessage("Syncing...");
                        sender.sendMessage("Sync complete (fake)");
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
                } else if (args[0].equalsIgnoreCase("test")) {

                    try (Context context = Context.create()) {
                        context.eval("js", "print('Hello python');");
                    }

                } else {
                    sender.sendMessage("Arguments not recognised:" + args[0]);
                }
        return true;
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        if (args.length == 1) {
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
        return tabComplete;
    }
}

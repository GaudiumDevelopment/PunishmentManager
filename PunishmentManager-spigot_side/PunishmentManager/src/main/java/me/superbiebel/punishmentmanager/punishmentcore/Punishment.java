package me.superbiebel.punishmentmanager.punishmentcore;

import org.bukkit.entity.Player;

public class Punishment {



    public boolean checkJail(Player p) {return false;}
    public boolean checkMute(Player p) {return false;}
    public boolean checkBan(Player p) {return false;}
    public static void register(){}
    enum Type {
        TEMP_UUID_BAN{

        },
        TEMP_UUID_MUTE{

        },
        TEMP_UUID_JAIL{

        },
        TEMP_UUID_WARN{

        },
        TEMP_IP_BAN{

        },
        TEMP_IP_MUTE{

        },
        TEMP_IP_JAIL{

        },
        TEMP_IP_WARN{

        }
    }
}

package me.superbiebel.punishmentmanager.punishmentcore;

import me.superbiebel.punishmentmanager.utils.Log;
import org.bukkit.entity.Player;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class OffenseExecutor {
    private Player player;
    private int offenseID;
    public OffenseExecutor(Player player, int offenseID) {
        this.player = player;
        this.offenseID = offenseID;
    }
    public boolean execute() {
        String testScript = "var test = \"test\";";
        Context cx = Context.enter();
        Log log = new Log();
        Scriptable scope = cx.initStandardObjects();
        cx.evaluateString(scope,testScript,"test",1,null);
        String result = (String) scope.get("test",scope);
        Log.debug(result);

        Context.exit();
        return false;
    }
}

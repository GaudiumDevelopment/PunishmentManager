package me.superbiebel.punishmentmanager.punishmentcore;

import org.bukkit.entity.Player;

public class OffenseExecutor {
    private Player executor;
    private Player criminal;
    private int offenseID;
    public OffenseExecutor(final Player executor, final int offenseID) {
        this.executor = executor;
        this.offenseID = offenseID;
    }
    public boolean execute() {
       /* this.criminal = Metadata.provideForPlayer(executor).get(DataUtility.CRIMINAL_KEY).get();
        Thread thread = new Thread(()->{String script = "to be filled in";
            PlaceholderAPI.setPlaceholders(executor,script);
            Context cx = Context.enter();
            Scriptable scope = cx.initStandardObjects();
            cx.evaluateString(scope,script,"OffenseExecution_" + offenseID,1,null);
            Context.exit();
            },"OffenseExecution_" + offenseID);
        thread.start();
        return false;*/




        







        return false; }
}

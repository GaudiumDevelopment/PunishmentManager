package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.superbiebel.punishmentmanager.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class OffenseListGUI extends PaginatedGui{

    public OffenseListGUI(Function<PaginatedGui, List<Item>> content, Player player, PaginatedGuiBuilder model) {
        super(content, player, model);
    }
    public static ResultSet getOffenseListGuiData() throws SQLException {
        PreparedStatement pst = MySQL.getDataSource().getConnection().prepareStatement("");
        ResultSet OffenseListResultSet = pst.executeQuery();
        return OffenseListResultSet;
    }
}


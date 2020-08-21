package me.superbiebel.punishmentmanager.menusystem.menu;

import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.lucko.helper.promise.Promise;
import me.superbiebel.punishmentmanager.MySQL.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OffenseListGUI extends Gui {

    List<Item> itemLists = new ArrayList<>();
    PreparedStatement pst;
    ResultSet rst;
    int amountofrows;


    public OffenseListGUI(Player player, int lines, String title) {
        super(player, lines, title);
    }

    @Override
    public void redraw() {

        if (isFirstDraw()) {
            Promise<Void> setupGUI = Promise.start()
                    .thenRunAsync(() ->
                            { try {
                                    PreparedStatement pst = MySQL.getDataSource().getConnection().prepareStatement(getOffenseSql);
                                    rst = pst.executeQuery();
                                    rst.last();
                                    int amountOfRows = rst.getRow();
                                    rst.first();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                            }
                    );
        }

    }
    private static final String getOffenseSql = "";




}


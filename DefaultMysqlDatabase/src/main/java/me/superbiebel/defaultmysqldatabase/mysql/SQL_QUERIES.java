package me.superbiebel.defaultmysqldatabase.mysql;

import lombok.Getter;
import org.intellij.lang.annotations.Language;

public class SQL_QUERIES {
    
    private SQL_QUERIES() {
    }
    public static void init(String db) {
        String replaceable = "{database_name}";
        CategoriesTable = CategoriesTable.replace(replaceable,db);
        Category_data_playersTable = Category_data_playersTable.replace(replaceable,db);
        category_points_addedTable = category_points_addedTable.replace(replaceable,db);
        JailsTable = JailsTable.replace(replaceable,db);
        Offense_category_id_connectionTable = Offense_category_id_connectionTable.replace(replaceable,db);
        Offense_templateTable = Offense_templateTable.replace(replaceable,db);
        Player_joinsTable = Player_joinsTable.replace(replaceable,db);
        Player_leavesTable = Player_leavesTable.replace(replaceable,db);
        Player_IPTable = Player_IPTable.replace(replaceable,db);
        Player_dataTable = Player_dataTable.replace(replaceable,db);
        Player_historyTable = Player_historyTable.replace(replaceable,db);
        Punishment_templatesTable = Punishment_templatesTable.replace(replaceable,db);
        Punishment_usedTable = Punishment_usedTable.replace(replaceable,db);
        ScriptTable = ScriptTable.replace(replaceable,db);
    }
    
    @Language("SQL")
    @Getter
    private static  String CategoriesTable = "CREATE TABLE IF NOT EXISTS {database_name}.categories ( `category_id` int NOT NULL , `category_name` varchar(30) NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Category_data_playersTable = "CREATE TABLE IF NOT EXISTS {database_name}.`category_data_players` ( uuid varchar(50) NOT NULL , `category_id` int NOT NULL , `category_points` int NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String category_points_addedTable = "CREATE TABLE IF NOT EXISTS {database_name}.category_points_added ( uuid varchar(50) , category_id int , history_id int , category_points_added int , offense_id int );";
    @Language("SQL")
    @Getter
    private static  String JailsTable = "CREATE TABLE IF NOT EXISTS {database_name}.jails ( jail_id int NOT NULL , jail_location_x int , jail_location_y int , jail_location_z int , world varchar(200), server varchar(200) );";
    @Language("SQL")
    @Getter
    private static  String Offense_category_id_connectionTable = "CREATE TABLE IF NOT EXISTS {database_name}.`offense_categoryid_connection` ( `category_id` int NOT NULL , `offense_id` int NOT NULL , points int NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Offense_templateTable = "CREATE TABLE IF NOT EXISTS {database_name}.`offense_template` ( `offense_id` int NOT NULL , `offense_name` varchar(30) NOT NULL , `offense_icon` varchar(30) NOT NULL, `offense_lore` TEXT );";
    @Language("SQL")
    @Getter
    private static  String Player_joinsTable = "CREATE TABLE IF NOT EXISTS {database_name}.`player_joins` ( uuid varchar(50) NOT NULL , `join_date` bigint UNSIGNED NOT NULL, result varchar(20) NOT NULL, ip varchar(20) NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Player_leavesTable = "CREATE TABLE IF NOT EXISTS {database_name}.`player_leaves` ( uuid varchar(50) NOT NULL , `leave_date` bigint UNSIGNED NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Player_IPTable = "CREATE TABLE IF NOT EXISTS {database_name}.`player_IP` ( `IP` varchar(18) NOT NULL , uuid varchar(50) NOT NULL , `creation_date` bigint UNSIGNED NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Player_dataTable = "CREATE TABLE IF NOT EXISTS {database_name}.player_data ( uuid varchar(50) NOT NULL , player_name varchar(20) NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Player_historyTable = "CREATE TABLE IF NOT EXISTS {database_name}.player_history ( history_id int NOT NULL , uuid_criminal varchar(50) NOT NULL , uuid_executor varchar(50) NOT NULL , formatted_reason varchar(200) , calculated_ban_duration bigint , calculated_jail_duration bigint , calculated_mute_duration bigint , jail_id int , time_done_mute bigint UNSIGNED , time_done_jail bigint , status varchar(200) NOT NULL );";
    @Language("SQL")
    @Getter
    private static  String Punishment_templatesTable = "CREATE TABLE IF NOT EXISTS {database_name}.punishment_templates ( `punishment_id` int NOT NULL , threshold int NOT NULL , `category_id` int NOT NULL , `mute_calculation` varchar(200) , `IP_mute_calculation` varchar(200) ,`ban_calculation` varchar(200) ,`IP_ban_calculation` varchar(200) , `jail_calculation` varchar(200) , server varchar(200) );";
    @Language("SQL")
    @Getter
    private static  String Punishment_usedTable = "CREATE TABLE IF NOT EXISTS {database_name}.punishment_used ( history_id int , punishment_id int );";
    @Language("SQL")
    @Getter
    private static  String ScriptTable = "CREATE TABLE IF NOT EXISTS {database_name}.scripts (script_id int, script TEXT);";

    
}

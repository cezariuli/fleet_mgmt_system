package fms.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBCustomTypes extends DBConnection {
    private Connection db;

    public DBCustomTypes() {
        this.db = super.getDbConnection();
    }

    public List<String> getCarMakers() {
        List<String> oems = new ArrayList<>();
        try {
            Statement getOems = db.createStatement();
            ResultSet oemsResultSet = getOems.executeQuery("SELECT unnest(enum_range(NULL::type_carmaker))::text");
            while (oemsResultSet.next()) {
                oems.add(oemsResultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return oems;
    }

    public List<String> getFuelTypes() {
        List<String> fTypes = new ArrayList<>();
        try {
            Statement getFTypes = db.createStatement();
            ResultSet ftResultSet = getFTypes.executeQuery("SELECT unnest(enum_range(NULL::etype_fuel))::text");
            while (ftResultSet.next()) {
                fTypes.add(ftResultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fTypes;
    }
}

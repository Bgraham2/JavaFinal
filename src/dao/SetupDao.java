package dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Brian on 4/20/2016.
 */
public class SetupDao extends BaseDao
{

    public SetupDao()
    {
        super();
    }

    public void createDatabaseTable()
    {
        Logger.getLogger(this.getClass()).debug("Inside Book table create");
        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate("Drop TABLE IF EXISTS book;");

            stmt.executeUpdate("CREATE TABLE book (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT NOT NULL, description TEXT NOT NULL, purchase_cost REAL NOT NULL" +
                    ", selling_price REAL NOT NULL, isbn TEXT NOT NULL, qty_on_hand INTEGER NOT NULL);" );

            stmt.close();
            Logger.getLogger(this.getClass()).debug("Book table created");
        }
        catch(Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }
    }

    public void initDatabaseTable()
    {
        Logger.getLogger(this.getClass()).debug("Inside Book table init");
        try
        {
            stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO book (title, description, purchase_cost, selling_price, isbn, qty_on_hand) VALUES ('Java Explained', 'Textbook', 50.00, 150.00, '334-665434-65443', 3);");

            stmt.executeUpdate("INSERT INTO book (title, description, purchase_cost, selling_price, isbn, qty_on_hand) VALUES ('Python Explained', 'Textbook', 60.00, 170.00, '334-634494-67743', 4);");

            stmt.executeUpdate("INSERT INTO book (title, description, purchase_cost, selling_price, isbn, qty_on_hand) VALUES ('C Explained', 'Textbook', 40.00, 120.00, '334-665723-65593', 6);");

            stmt.executeUpdate("INSERT INTO book (title, description, purchase_cost, selling_price, isbn, qty_on_hand) VALUES ('C++ Explained', 'Textbook', 40.00, 160.00, '334-628744-62883', 2);");

            stmt.close();
            Logger.getLogger(this.getClass()).debug("Book table initialized with seed data");
        }
        catch(Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }
    }

    @Override
    public void viewSingleObject(Object object) throws SQLException {

    }

    @Override
    public void viewObjects(Object object) throws SQLException {

    }

    @Override
    public Object getSingleObject(Objects object) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Object> getObjects(Object object) throws SQLException {
        return null;
    }

    @Override
    public Object insertSingleObject(Objects object) throws SQLException {
        return null;
    }

    @Override
    public void insertObjects(ArrayList<Object> objects) throws SQLException {

    }

    @Override
    public void updateSingleObject(Object object) throws SQLException {

    }

    @Override
    public void updateObjects(ArrayList<Object> objects) throws SQLException {

    }

    @Override
    public void deleteSingleObject(Object object) throws SQLException {

    }

    @Override
    public void deleteObjects(ArrayList<Object> objects) throws SQLException {

    }
}

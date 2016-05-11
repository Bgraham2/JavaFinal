package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Brian on 4/20/2016.
 */
public interface ICRUDable
{
    public void viewSingleObject(Object object) throws SQLException;
    public void viewObjects(Object object) throws SQLException;

    public Object getSingleObject(Objects object) throws SQLException;
    public ArrayList<Object> getObjects(Object object) throws SQLException;

    public Object insertSingleObject(Objects object) throws SQLException;
    public void insertObjects(ArrayList<Object> objects) throws SQLException;

    public void updateSingleObject(Object object) throws SQLException;
    public void updateObjects(ArrayList<Object> objects) throws SQLException;

    public void deleteSingleObject(Object object) throws SQLException;
    public void deleteObjects(ArrayList<Object> objects) throws SQLException;
}

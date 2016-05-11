package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.log4j.*;

/**
 * Created by Brian on 4/20/2016.
 */
public class BookDao extends BaseDao implements ICRUDable
{

    public BookDao()
    {
        super();
    }

    public void insertBook(Book book) throws SQLException
    {
        Logger.getLogger(this.getClass()).debug("Inside Book insert");

        try
        {
            String sql = "INSERT INTO book (title, description, purchase_cost, selling_Price, isbn, qty_on_hand)" +
                    " VALUES (?, ? ,? ,? ,?, ?);" ;

            ps = conn.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPurchaseCost());
            ps.setDouble(4, book.getSellingPrice());
            ps.setString(5, book.getIsbn());
            ps.setInt(6, book.getQty_on_hand());

            conn.setAutoCommit(true);

            ps.executeUpdate();
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }

        finally
        {
            ps.close();
        }
    }

    public void updateBook(Book book) throws SQLException
    {
        Logger.getLogger(this.getClass()).debug("Inside Book update");

        try
        {
            String sql = "UPDATE book SET title = ?, description = ?, purchase_cost = ?, selling_price = ?, isbn = ?, qty_on_hand = ? WHERE ID = ?;"; ;

            ps = conn.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPurchaseCost());
            ps.setDouble(4, book.getSellingPrice());
            ps.setString(5, book.getIsbn());
            ps.setInt(6, book.getQty_on_hand());
            ps.setInt(7, book.getId());

            conn.setAutoCommit(true);

            ps.executeUpdate();
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }

        finally
        {
            ps.close();
        }
    }

    public ArrayList<Book> getBooks() throws SQLException
    {
        ArrayList<Book> books = new ArrayList<Book>();

        try
        {

            String sql = "SELECT id, title, description, purchase_cost, selling_Price, isbn, qty_on_hand FROM book;";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while(rs.next())
            {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setPurchaseCost(rs.getDouble("purchase_cost"));
                book.setSellingPrice(rs.getDouble("selling_price"));
                book.setIsbn(rs.getString("isbn"));
                book.setQty_on_hand(rs.getInt("qty_on_hand"));

                Logger.getLogger(this.getClass()).debug("ID = " + book.getId());
                Logger.getLogger(this.getClass()).debug("title = " + book.getTitle());
                Logger.getLogger(this.getClass()).debug("description = " + book.getDescription());
                Logger.getLogger(this.getClass()).debug("purchase_cost = " + book.getPurchaseCost());
                Logger.getLogger(this.getClass()).debug("selling_price = " + book.getSellingPrice());
                Logger.getLogger(this.getClass()).debug("isbn = " + book.getIsbn());
                Logger.getLogger(this.getClass()).debug("qty_on_hand" + book.getQty_on_hand());

                books.add(book);
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }

        finally
        {

            rs.close();
            ps.close();

        }
        return books;
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
    public Object insertSingleObject(Objects object) throws SQLException
    {

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

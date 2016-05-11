package gui;

import dao.BaseDao;
import dao.Book;
import dao.BookDao;
import dao.SetupDao;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Brian on 4/20/2016.
 */
public class PanelContainer extends JPanel
{
    private JTabbedPane tabContainer;
    private PanelDataEntry pnlDataEntry;
    private PanelDataUpdate pnlDataUpdate;
    private PanelPurchase pnlPurchase;
    private BookDao bookDao;
    private SetupDao setupDao;
    private ArrayList<Book> lstBooks;


    public PanelContainer()
    {
        bookDao = new BookDao();
        setupDao = new SetupDao();
        lstBooks = new ArrayList<Book>();

        tabContainer = new JTabbedPane(JTabbedPane.NORTH);
        pnlDataEntry = new PanelDataEntry();
        pnlDataUpdate = new PanelDataUpdate();
        pnlPurchase = new PanelPurchase();

        pnlDataEntry.setSetupDao(setupDao);
        pnlDataEntry.setBookDao(bookDao);
        pnlDataEntry.setPanelDataUpdate(pnlDataUpdate);
        pnlDataEntry.setPanelPurchase(pnlPurchase);
        pnlDataEntry.setListBooks(lstBooks);
        pnlDataUpdate.setBookDao(bookDao);
        pnlDataUpdate.setListBooks(lstBooks);
        pnlDataUpdate.setPanelPurchase(pnlPurchase);
        pnlPurchase.setBookDao(bookDao);
        pnlPurchase.setListBooks(lstBooks);
        pnlPurchase.setPanelDataUpdate(pnlDataUpdate);

        tabContainer.add(pnlDataEntry, "Book Entry");
        tabContainer.add(pnlDataUpdate, "Book Update");
        tabContainer.add(pnlPurchase, "Book Purchase");

        add(tabContainer);
    }
}

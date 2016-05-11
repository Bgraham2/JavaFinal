package gui;

import dao.Book;
import dao.BookDao;
import exception.BookVendorException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Brian on 4/20/2016.
 */
public class PanelPurchase extends JPanel implements ActionListener
{
    private JLabel lblBookList;
    private JComboBox cbBookList;
    private JLabel lblQuantity;
    private JTextField txtQuantity;
    private JButton btnPurchase;
    private JTextArea taMessage;
    private JScrollPane scrMessage;
    private BookDao bookDao;
    private ArrayList<Book> lstBooks;
    private PanelDataUpdate pnlDataUpdate;
    private ArrayList<BookPurchase> lstbookPurchases;
    private int purchased;
    private int id;



    public PanelPurchase()
    {
        lstbookPurchases = new ArrayList<BookPurchase>();
        lblBookList = new JLabel("Book List");
        cbBookList = new JComboBox();
        lblQuantity= new JLabel("Quantity:");
        txtQuantity = new JTextField(20);
        btnPurchase = new JButton("Purchase");
        taMessage = new JTextArea(4, 40);
        scrMessage = new JScrollPane(taMessage);

        cbBookList.setActionCommand("choose");
        btnPurchase.setActionCommand("purchase");
        cbBookList.addActionListener(this);
        btnPurchase.addActionListener(this);


        setLayout(new GridLayout(4, 1));

        JPanel row1 = new JPanel();
        row1.add(lblBookList);
        row1.add(cbBookList);

        JPanel row2 = new JPanel();
        row2.add(lblQuantity);
        row2.add(txtQuantity);

        JPanel row3 = new JPanel();
        row3.add(btnPurchase);

        JPanel row4 = new JPanel();
        row4.add(scrMessage);

        add(row1);
        add(row2);
        add(row3);
        add(row4);

    }

    public void setBookDao(BookDao inputDao)
    {
        bookDao = inputDao;
    }

    public void setListBooks(ArrayList<Book> lstBooks)
    {
        this.lstBooks = lstBooks;
    }

    public void setPanelDataUpdate(PanelDataUpdate pnlDataUpdate)
    {
        this.pnlDataUpdate = pnlDataUpdate;
    }

    private Integer getValidInt(Integer value) throws BookVendorException
    {
        if (value > 0)
        {
            return value;
        }
        else
        {
            throw new BookVendorException("Invalid quantity input");
        }
    }

    public void updateComboBox()
    {
        try
        {
            lstBooks = bookDao.getBooks();
        }
        catch(Exception ex)
        {
            //txtMessage.setText(ex.toString());
        }
        cbBookList.removeAllItems();

        for(Book temp : lstBooks)
        {
            String value = temp.getTitle() + "(" + temp.getQty_on_hand() + ")";
            cbBookList.addItem(value);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().toLowerCase().equals("choose"))
        {
            id = cbBookList.getSelectedIndex() + 1;
        }

        if (e.getActionCommand().toLowerCase().equals("purchase"))
        {

            taMessage.setText("");
            String title = "";
            String description = "";
            Double purchCost = 0.0;
            Double sellPrice = 0.0;
            String isbn = "";
            int onHand = 0;
            double totalSales = 0.0;
            double totalProfit = 0.0;

            try
            {

                purchased = getValidInt(Integer.parseInt(txtQuantity.getText()));

                for(Book temp : lstBooks)
                {
                    if(temp.getId() == id)
                    {

                        title = temp.getTitle();
                        description = temp.getDescription();
                        purchCost = temp.getPurchaseCost();
                        sellPrice = temp.getSellingPrice();
                        isbn = temp.getIsbn();
                        onHand = temp.getQty_on_hand() - purchased;

                    }
                }

                Book book = new Book(id, title, description, purchCost, sellPrice, isbn, onHand);


                bookDao.updateBook(book);
                this.updateComboBox();
                pnlDataUpdate.updateComboBox();

                Book books = new Book(id, title, description, purchCost, sellPrice, isbn, onHand);
                BookPurchase bPurchase = new BookPurchase(purchased, books);
                lstbookPurchases.add(bPurchase);

                for (BookPurchase temp : lstbookPurchases)
                {
                    taMessage.append(temp.display() + "\n");
                    totalSales += temp.getTotalSale();
                    totalProfit += temp.getTotalProfit();

                }

                taMessage.append(String.format("Amount of Sale: $%2.2f\n",  totalSales));
                taMessage.append(String.format("(Our handy profit: $%2.2f)\n",  totalProfit));

            }
            catch (Exception ex)
            {
                taMessage.setText(ex.toString());
            }
            
        }
    }
}

package gui;

import dao.Book;
import dao.BookDao;
import dao.SetupDao;
import exception.BookVendorException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Brian on 4/20/2016.
 */
public class PanelDataEntry extends JPanel implements ActionListener
{

    private JLabel lblTitle;
    private JTextField txtTitle;
    private JLabel lblDescription;
    private JTextField txtDescription;
    private JLabel lblPurchCost;
    private JTextField txtPurchCost;
    private JLabel lblSellPrice;
    private JTextField txtSellPrice;
    private JLabel lblIsbn;
    private JTextField txtIsbn;
    private JLabel lblOnHand;
    private JTextField txtOnHand;
    private JButton btnSave;
    private JTextField txtMessage;
    private JButton btnCreate;
    private JButton btnInit;
    private BookDao bookDao;
    private SetupDao setupDao;
    private ArrayList<Book> lstBooks;
    private PanelDataUpdate pnlDataUpdate;
    private PanelPurchase pnlPurchase;


    public PanelDataEntry()
    {

        lblTitle = new JLabel("Title:");
        txtTitle= new JTextField(20);
        lblDescription = new JLabel("Description:");
        txtDescription = new JTextField(20);
        lblPurchCost = new JLabel("Purchase Cost:");
        txtPurchCost = new JTextField(20);
        lblSellPrice = new JLabel("Selling Price:");
        txtSellPrice = new JTextField(20);
        lblIsbn = new JLabel("Isbn:");
        txtIsbn = new JTextField(20);
        lblOnHand = new JLabel("Qty On Hand:");
        txtOnHand = new JTextField(20);
        btnSave = new JButton("Save");
        txtMessage = new JTextField(40);
        btnCreate = new JButton("CreateDB");
        btnInit = new JButton("InitDB");

        btnSave.setActionCommand("save");
        btnSave.addActionListener(this);
        btnCreate.setActionCommand("create");
        btnCreate.addActionListener(this);
        btnInit.setActionCommand("init");
        btnInit.addActionListener(this);

        setLayout(new GridLayout(9, 1));

        JPanel row1 = new JPanel();
        row1.add(lblTitle);
        row1.add(txtTitle);

        JPanel row2 = new JPanel();
        row2.add(lblDescription);
        row2.add(txtDescription);

        JPanel row3 = new JPanel();
        row3.add(lblPurchCost);
        row3.add(txtPurchCost);

        JPanel row4 = new JPanel();
        row4.add(lblSellPrice);
        row4.add(txtSellPrice);

        JPanel row5 = new JPanel();
        row5.add(lblIsbn);
        row5.add(txtIsbn);

        JPanel row6 = new JPanel();
        row6.add(lblOnHand);
        row6.add(txtOnHand);

        JPanel row7 = new JPanel();
        row7.add(btnSave);

        JPanel row8 = new JPanel();
        row8.add(txtMessage);

        JPanel row9 = new JPanel();
        row9.add(btnCreate);
        row9.add(btnInit);

        add(row1);
        add(row2);
        add(row3);
        add(row4);
        add(row5);
        add(row6);
        add(row7);
        add(row8);
        add(row9);

    }

    public void setBookDao(BookDao inputDao)
    {
        bookDao = inputDao;
    }

    public void setSetupDao(SetupDao inputDao)
    {
        setupDao = inputDao;
    }

    public void setListBooks(ArrayList<Book> lstBooks)
    {
        this.lstBooks = lstBooks;
    }

    public void setPanelDataUpdate(PanelDataUpdate pnlDataUpdate)
    {
        this.pnlDataUpdate = pnlDataUpdate;
    }

    public void setPanelPurchase(PanelPurchase pnlPurchase)
    {
        this.pnlPurchase = pnlPurchase;
    }

    private String getValidString(String strings) throws BookVendorException
    {

        if (strings.length() > 0)
        {
            return strings;
        }
        else
        {
            throw new BookVendorException("Title, Description, and ISBN cannot be blank.");
        }

    }

    private Double getValidDouble(Double value) throws BookVendorException
    {
        if (value > 0.0)
        {
            return value;
        }
        else
        {
            throw new BookVendorException("Invalid cost or price input");
        }
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

    private void clearInputFields()
    {
        txtTitle.setText("");
        txtDescription.setText("");
        txtPurchCost.setText("");
        txtSellPrice.setText("");
        txtIsbn.setText("");
        txtOnHand.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().toLowerCase().equals("create"))
        {
            setupDao.createDatabaseTable();
            txtMessage.setText("CREATE DATABASE successful");
            btnCreate.setEnabled(false);
        }

        if(e.getActionCommand().toLowerCase().equals("init"))
        {
            setupDao.initDatabaseTable();
            txtMessage.setText("Database Initialized");
            pnlDataUpdate.updateComboBox();
            pnlPurchase.updateComboBox();
            btnInit.setEnabled(false);
        }

        if(e.getActionCommand().toLowerCase().equals("save"))
        {
            String title;
            String description;
            double purchase_cost;
            double selling_price;
            String isbn;
            int qty_on_hand;

            try
            {

                title = getValidString(txtTitle.getText());
                description = getValidString(txtDescription.getText());
                purchase_cost = getValidDouble(Double.parseDouble(txtPurchCost.getText()));
                selling_price = getValidDouble(Double.parseDouble(txtSellPrice.getText()));
                isbn = getValidString(txtIsbn.getText());
                qty_on_hand = getValidInt(Integer.parseInt(txtOnHand.getText()));


                Book book = new Book(0, title, description, purchase_cost, selling_price, isbn, qty_on_hand);


                bookDao.insertBook(book);
                clearInputFields();
                txtMessage.setText("Book added to database");
                pnlDataUpdate.updateComboBox();
                pnlPurchase.updateComboBox();

            }
            catch(Exception ex)
            {
                txtMessage.setText(ex.toString());
            }
        }

    }
}

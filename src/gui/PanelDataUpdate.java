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
public class PanelDataUpdate extends JPanel implements ActionListener
{

    private JLabel lblBookList;
    private JComboBox cbBookList;
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
    private BookDao bookDao;
    private ArrayList<Book> lstBooks;
    private PanelPurchase pnlPurchase;
    private int id = 0;

public PanelDataUpdate()
{
    lblBookList = new JLabel("Book List");
    cbBookList = new JComboBox();
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
    lstBooks = new ArrayList<Book>();

    cbBookList.setActionCommand("choose");
    btnSave.setActionCommand("update");
    cbBookList.addActionListener(this);
    btnSave.addActionListener(this);

    setLayout(new GridLayout(9, 1));

    JPanel row1 = new JPanel();
    row1.add(lblBookList);
    row1.add(cbBookList);

    JPanel row2 = new JPanel();
    row2.add(lblTitle);
    row2.add(txtTitle);

    JPanel row3 = new JPanel();
    row3.add(lblDescription);
    row3.add(txtDescription);

    JPanel row4 = new JPanel();
    row4.add(lblPurchCost);
    row4.add(txtPurchCost);

    JPanel row5 = new JPanel();
    row5.add(lblSellPrice);
    row5.add(txtSellPrice);

    JPanel row6 = new JPanel();
    row6.add(lblIsbn);
    row6.add(txtIsbn);

    JPanel row7 = new JPanel();
    row7.add(lblOnHand);
    row7.add(txtOnHand);

    JPanel row8 = new JPanel();
    row8.add(btnSave);

    JPanel row9 = new JPanel();
    row9.add(txtMessage);

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

    public void setListBooks(ArrayList<Book> lstBooks)
    {
        this.lstBooks = lstBooks;
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

    public void updateComboBox()
    {

        try
        {
            lstBooks = bookDao.getBooks();
        }
        catch(Exception ex)
        {
            txtMessage.setText(ex.toString());
        }
        cbBookList.removeAllItems();

        for(Book temp : lstBooks)
        {
            String value = temp.getTitle() + "(" + temp.getQty_on_hand() + ")";
            cbBookList.addItem(value);
        }

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
        if (e.getActionCommand().toLowerCase().equals("choose"))
        {
            int index = cbBookList.getSelectedIndex() + 1;

            for(Book temp : lstBooks)
            {
                if(temp.getId() == index)
                {
                    id = temp.getId();
                    txtTitle.setText(temp.getTitle());
                    txtDescription.setText(temp.getDescription());
                    txtPurchCost.setText(temp.getPurchaseCost().toString());
                    txtSellPrice.setText(temp.getSellingPrice().toString());
                    txtIsbn.setText(temp.getIsbn());
                    txtOnHand.setText(Integer.toString(temp.getQty_on_hand()));
                }
            }
        }

        if (e.getActionCommand().toLowerCase().equals("update"))
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


                Book book = new Book(id, title, description, purchase_cost, selling_price, isbn, qty_on_hand);


                bookDao.updateBook(book);
                txtMessage.setText("Book updated");
                this.updateComboBox();
                pnlPurchase.updateComboBox();

            }
            catch(Exception ex)
            {
                txtMessage.setText(ex.toString());
            }
        }
    }
}

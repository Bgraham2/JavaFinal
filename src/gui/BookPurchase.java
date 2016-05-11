package gui;

import dao.Book;

/**
 * Created by Brian on 4/20/2016.
 */
public class BookPurchase
{
    private int qtyPurchased;
    private Book book;

    public BookPurchase()
    {
        qtyPurchased = 0;
        book = null;
    }

    public BookPurchase(int qtyPurchased, Book book)
    {
        this.qtyPurchased = qtyPurchased;
        this.book = book;
    }

    public void setQtyPurchased(int qtyPurchased)
    {
        this.qtyPurchased = qtyPurchased;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public int getQtyPurchased()
    {
        return qtyPurchased;
    }

    public Double getTotalSale()
    {
        return (book.getSellingPrice() * qtyPurchased);
    }

    public Double getTotalProfit()
    {
        return ((book.getSellingPrice() - book.getPurchaseCost()) * qtyPurchased);
    }

    public Book getBook()
    {
        return book;
    }

    @Override
    public String toString() {
        return "BookPurchase{" +
                "quantity=" + qtyPurchased +
                ", book=" + book.getTitle() + "(" + book.getQty_on_hand() + ")" +
                '}';
    }

    public String display()
    {
        return this.toString();
    }
}

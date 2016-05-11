package dao;

/**
 * Created by Brian on 4/20/2016.
 */
public class Book
{
    private int id;
    private String title;
    private String description;
    private Double purchaseCost;
    private Double sellingPrice;
    private String isbn;
    private int qty_on_hand;

    public Book()
    {
        setId(0);
        setTitle("");
        setDescription("");
        setPurchaseCost(0.0);
        setSellingPrice(0.0);
        setIsbn("");
        setQty_on_hand(0);
    }

    public Book(int id, String title, String description, Double purchaseCost, Double sellingPrice, String isbn, int qty_on_hand)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.purchaseCost = purchaseCost;
        this.sellingPrice = sellingPrice;
        this.isbn = isbn;
        this.qty_on_hand = qty_on_hand;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String decription)
    {
        this.description = decription;
    }

    public void setPurchaseCost(Double purchaseCost)
    {
        this.purchaseCost = purchaseCost;
    }

    public void setSellingPrice(Double sellingPrice)
    {
        this.sellingPrice = sellingPrice;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public void setQty_on_hand(int qty_on_hand)
    {
        this.qty_on_hand = qty_on_hand;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public Double getPurchaseCost()
    {
        return purchaseCost;
    }

    public Double getSellingPrice()
    {
        return sellingPrice;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public int getQty_on_hand()
    {
        return qty_on_hand;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", purchaseCost=" + purchaseCost +
                ", sellingPrice=" + sellingPrice +
                ", isbn='" + isbn + '\'' +
                ", qty_on_hand=" + qty_on_hand +
                '}';
    }

    public void display()
    {
        System.out.print(toString());
    }
}

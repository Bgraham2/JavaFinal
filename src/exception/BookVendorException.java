package exception;

/**
 * Created by Brian on 4/20/2016.
 */
public class BookVendorException extends Exception
{
    public BookVendorException()
    {
        super ("Invalid Operation");
    }

    public BookVendorException(String message)
    {
        super(message);
    }
}

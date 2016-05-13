# JavaFinal
Java Final

Final project for Java development class
The app creates 3 tabbed panes that imiate a simple book purchasing app. The entery pane adds new books to the database and the quantity.
The update pane allows changes to be made to the inventory. The pruchase tab allows for purchase of itmes and displays total price and profit.

The gui container holds the base container for the tabbed panes, the array list of books added or updated, the current book objects passed to and from the database.
It also sets up method calls between tabs.
The dataentry tab has a createDB button that creates or sets the value of the database table and cloumns to a default. The intiDB button addes 4 books as base seed of data.
The labels an textfeilds and save button are set in grid layout and allow new books to be added. Then calls update nethods in the other panels so they have the current data.
The dateupdate panel has a combobox of all availble books. Selecting one populates the rest of the textfields(in grid layout) so changes can be made. Clicking the save button updates the database.
and calls the update function for the purchase panel.
The purchase panel ( in grid layout) has a combobox that allows the selection of the book to purchase. The number purchased is set in the quanty field.
Click the purchase button reudces the number of the book in inventory, updates the change to the database, add the purchase to arraylist held by the bookpurchased class.
Then outputs the purchase information (title, qunatity, total price, total profit).
The bbokpurchase class holds the array list of book purchased and calculates total sale and total profit based on quantity purchased.


The execption container holds the custom error handler for faulty user input. it displays message based on what input was incorrect

The driver container holds the final class whcih runs the app and creates the frame for the panels.
It also hold a config properties files that the basedao uses to determine the infomration needed to connect to the database.

The dao containers holds the basedao class which creates the DB connection. It also holds the setupdao which is used to create the DB.
it is also used to add the seed data to the database. 
The book class creates a book object to be passed to the dao.
The bookdao takes the book object and inserts, updates, or returns information from the batabase.

The ICRUDable class is an interface that is gong to be used with a basemapper and bookmapper class to map datareturns, but it is not currently implemented.

all files can here JavaFinal/src/.

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Shopping Cart
// Files:           N/A
// Course:          CS300 Spring 2019
//
// Author:          Lenea Guse
// Email:           laguse@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
public class ShoppingCart {
	// Define final parameters
	private static final int CART_CAPACITY = 20; // shopping cart max capacity
	private static final double TAX_RATE = 0.05; // sales tax
	  public static void main(String[] args) {
		  String[] cart = new String[25];
		  int count = 0;
		  int numberItems = 0;
		  double subTotal = 0.0;
		  double total = 0.0;
		  double tax = 0.0;
		  //Print the welcome Statement
		  System.out.println("=============   Welcome to the Shopping Cart App   =============\n"); 
		  Scanner sc = new Scanner(System.in);
		  boolean a = true;
		  //Creates a continuous loop for the user to interact with
		  while (a) {
			  //Prints the Command Menu
			  System.out.println(
					    "\nCOMMAND MENU:\n" + 
				  		" [P] print the market catalog\n" + 
				  		" [A <index>] add one occurrence of an item to the cart given its identifier\n" + 
				  		" [C] checkout\n" + 
				  		" [D] display the cart content\n" + 
				  		" [O <index>] number of occurrences of an item in the cart given its identifier\n" + 
				  		" [R <index>] remove one occurrence of an item from the cart given its identifier\n" + 
				  		" [Q]uit the application\n");
			//Prompts the user to enter a command  
			System.out.print("ENTER COMMAND: ");
			String input = sc.next();
			//Print the catalog?
			if (input.charAt(0) == 'p' || input.charAt(0) == 'P') {
				printMarketCatalog();

			}
			//Add an item to their cart?
			if (input.charAt(0) == 'a' || input.charAt(0) == 'A') {
				//Scans for the index of the item desired
				int number = sc.nextInt();
				count = add(number, cart, count);

			}
			//Checkout?
			if (input.charAt(0) == 'c' || input.charAt(0) == 'C') {
				//Get subtotal
				subTotal = getSubTotalPrice(cart, count);
				//calculate tax
				tax = subTotal * TAX_RATE;
				//calculate total
				total = subTotal + tax;
				//print the prices formatted to 2 decimals
				System.out.print("#items: " + (count) + " Subtotal: $" + subTotal + " Tax: $");
				System.out.printf("%.2f", tax);
				System.out.print(" TOTAL: $");
				System.out.printf("%.2f", total);
				System.out.println("");

			}
			//display cart contents?
			if (input.charAt(0) == 'd' || input.charAt(0) == 'D') {
				displayCartContent(cart, count);

			}
			//check the occurrence of an item?
			if (input.charAt(0) == 'o' || input.charAt(0) == 'O') {
				//Scans for the desired index of the item
				int itemIndex = sc.nextInt();
				occurrencesOf(itemIndex, cart, count);

			}
			//remove an item from the cart?
			if (input.charAt(0) == 'r' || input.charAt(0) == 'R') {
				//gets the name of the item at specified index
				String itemRemove = MARKET_ITEMS[sc.nextInt()][0];
				count = remove(itemRemove, cart, count);

			}
			//quit the app?
			if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {
				System.out.println(
						"=============  Thank you for using this App!!!!!  =============");
				//allows for the loop to be exited
				a = false;
			}
		}
	}

	// a perfect-size two-dimensional array that stores the available items in the
	// market
	// MARKET_ITEMS[i][0] refers to a String that represents the description of the
	// item
//	                   identified by index i
	// MARKET_ITEMS[i][1] refers to a String that represents the unit price of the
	// item
//	                   identified by index i in dollars.
	public static final String[][] MARKET_ITEMS = new String[][] { { "Apple", "$1.59" }, { "Avocado", "$0.59" },
			{ "Banana", "$0.49" }, { "Beef", "$3.79" }, { "Blueberry", "$6.89" }, { "Broccoli", "$1.79" },
			{ "Butter", "$4.59" }, { "Carrot", "$1.19" }, { "Cereal", "$3.69" }, { "Cheese", "$3.49" },
			{ "Chicken", "$5.09" }, { "Chocolate", "$3.19" }, { "Cookie", "$9.5" }, { "Cucumber", "$0.79" },
			{ "Eggs", "$3.09" }, { "Grape", "$2.29" }, { "Ice Cream", "$5.39" }, { "Milk", "$2.09" },
			{ "Mushroom", "$1.79" }, { "Onion", "$0.79" }, { "Pepper", "$1.99" }, { "Pizza", "$11.5" },
			{ "Potato", "$0.69" }, { "Spinach", "$3.09" }, { "Tomato", "$1.79" } };

	// adds the item with the given its identifier (index) at the end of the cart
	/**
	 * adds the item with the given identifier index at the end of the cart
	 * 
	 * @param index of the item within the marketItems array
	 * @param cart  shopping cart
	 * @param count number of items present within the cart before this add method
	 *              is called
	 * @return the number of items present in the cart after the item with
	 *         identifier index is added
	 */
	public static int add(int index, String[] cart, int count) {
		//checks if the count is 20 or under
		if (count <= 20) {
			//checks if user is trying to add an item that exists
			if (index < 25) {
				//inc. count
				count += 1;
				//adds the item at the index of count - 1
				cart[count - 1] = MARKET_ITEMS[index][0];
			}
		} else {
			//sends an error message that the cart is full
			System.out.println("\"WARNING: The cart is full. You cannot add any new item.\"");
		}
		return count;
	}

	// Returns how many occurrences of the item with index itemIndex are present in
	// the shopping cart
	/**
	 * Returns how many occurrences of the item with index itemIndex are present in
	 * the shopping cart
	 * 
	 * @param itemIndex identifier of the item to count its occurrences in the cart
	 * @param cart      shopping cart
	 * @param count     number of items present within the cart
	 * @return the number of occurrences of item in the cart
	 */
	public static int occurrencesOf(int itemIndex, String[] cart, int count) {
		int occNum = 0;
		//checks if cart is empty
		if (count == 0) {
			System.out.println("Cart is empty");
			return 0;
		}
		//scans through cart for item
		for (int i = 0; i < count; i++) {
			if (cart[i] == null) {
				break;
			}
			//checks if the desired item in the cart is the item at the specified index
			if (cart[i].equals(MARKET_ITEMS[itemIndex][0])) {
				//inc. the occurrence number
				occNum += 1;
			}

		}
		//prints out the number of occurrences
		System.out.println("The number of occurrences of " + MARKET_ITEMS[itemIndex][0] +
				" (id #" + itemIndex + ") is: " + occNum);
		return occNum;
	}

	// Removes the first (only one) occurrence of itemToRemove if found and returns
	// the number of
	// items in the cart after remove operation is completed either successfully or
	// not
	public static int remove(String itemToRemove, String[] cart, int count) {
		int index;
		index = indexOf(itemToRemove, cart, count);
		//checks if the index exists
		if (index == -1) {
			System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
		} else {
			//changes the last item in the cart to the position of the item being removed
			cart[index] = cart[count - 1];
			//changes the last item in the cart to null
			cart[count - 1] = null;
			//dec. count
			count -= 1;
		}
		return count;
	}

	/**
	 * Returns the index of an item within the shopping cart
	 * 
	 * @param item  description
	 * @param cart  Shopping cart
	 * @param count number of items present in the shopping cart
	 * @return index of the item within the shopping cart, and -1 if the item does
	 *         not exist in the cart
	 */
	private static int indexOf(String item, String[] cart, int count) {
		//scans through cart looking for specified item
		for (int i = 0; i <= count; i++) {
			if (item.equals(cart[i])) {
				//returns the index of the item
				return i;
			}
		}
		//returns -1 if item is not in cart
		return -1;
	}
	/**
	 * Calculates the sub-total of all of the items in a cart
	 * 
	 * @param cart Shopping cart
	 * @param count number of item within the shipping cart
	 */
	public static double getSubTotalPrice(String[] cart, int count) {
		double subTotal = 0.0;
		//checks if there's any items in the cart
		if (count == 0) {
			return 0.0;
		}
		/**
		 * scans through every item in the cart and sends
		 * the item to priceOf to get price
		 */
		for (int i = 0; i < count; i++) {
			subTotal += priceOf(cart[i]);
		}
		//casts the subtotal to an int and saves two decimal places 
		subTotal = (int) (subTotal * 100);
		//casts the int to a double with two decimal places
		subTotal = subTotal / 100.0;

		return subTotal;

	}

	private static double priceOf(String item) {
		String itemPriceBefore;
		double itemPrice = 0.0;
		//scans through the market items
		for (int i = 0; i < MARKET_ITEMS.length; i++) {
		   
		//checks if the name of the market item is the name of the cart item
			if (item.equals(MARKET_ITEMS[i][0])) {
				//gets the price of the item
				itemPriceBefore = MARKET_ITEMS[i][1];
				//removes the $ from the string
				itemPriceBefore = itemPriceBefore.substring(1, itemPriceBefore.length());
				//casts the price as a double
				itemPrice = Double.parseDouble(itemPriceBefore);
			}
		}
		return itemPrice;

	}
	  
	   // prints the Market Catalog (item identifiers, description, and unit prices)
	   public static void printMarketCatalog() {
		   System.out.println(
				"+++++++++++++++++++++++++++++++++++++++++++++\r\n" + 
		   		"Item id 	Description 	 Price\r\n" + 
		   		"+++++++++++++++++++++++++++++++++++++++++++++\r\n" + 
		   		"0		Apple    	 $1.59\r\n" + 
		   		"1		Avocado    	 $0.59\r\n" + 
		   		"2		Banana    	 $0.49\r\n" + 
		   		"3		Beef    	 $3.79\r\n" + 
		   		"4		Blueberry    	 $6.89\r\n" + 
		   		"5		Broccoli    	 $1.79\r\n" + 
		   		"6		Butter    	 $4.59\r\n" + 
		   		"7		Carrot    	 $1.19\r\n" + 
		   		"8		Cereal    	 $3.69\r\n" + 
		   		"9		Cheese    	 $3.49\r\n" + 
		   		"10		Chicken    	 $5.09\r\n" + 
		   		"11		Chocolate    	 $3.19\r\n" + 
		   		"12		Cookie    	 $9.5\r\n" + 
		   		"13		Cucumber    	 $0.79\r\n" + 
		   		"14		Eggs    	 $3.09\r\n" + 
		   		"15		Grape    	 $2.29\r\n" + 
		   		"16		Ice Cream    	 $5.39\r\n" + 
		   		"17		Milk    	 $2.09\r\n" + 
		   		"18		Mushroom    	 $1.79\r\n" + 
		   		"19		Onion    	 $0.79\r\n" + 
		   		"20		Pepper    	 $1.99\r\n" + 
		   		"21		Pizza    	 $11.5\r\n" + 
		   		"22		Potato    	 $0.69\r\n" + 
		   		"23		Spinach    	 $3.09\r\n" + 
		   		"24		Tomato    	 $1.79\r\n" + 
		   		"+++++++++++++++++++++++++++++++++++++++++++++");
	   }
	  
	// Displays the cart content (items separated by commas)
	public static void displayCartContent(String[] cart, int count) {
		System.out.print("Cart Content: ");
		//scans through the items in the cart
		for (int i = 0; i < count; i++) {
			//prints out each item
			System.out.print(cart[i] + ", ");
		}
		System.out.println("");
	}
}

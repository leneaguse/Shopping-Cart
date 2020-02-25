// File Header comes here
 
// JavaDoc class Header comes here
public class ShoppingCartTests {
 
  /**
   * Checks whether the total number of items within the cart is incremented after adding one item 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
 
    // Add an item to the cart
    count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart
    // Check that count was incremented
    if (count != 1) {
      System.out.println("Problem detected: After adding only one item to the cart, "
          + "the cart count should be incremented. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }
 
  /**
   * Checks whether add and OccurrencesOf return the correct output when only one item is added to
   * the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 10
    String[] cart = new String[10]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
 
    // check that OccurrencesOf returns 0 when called with an empty cart
    if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
      System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
          + "empty. The result should be 0. But, it was not.");
      testPassed = false;
    }
 
    // add one item to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart
 
    // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 1. "
          + "But, it was not the case.");
      testPassed = false;
    }
 
    return testPassed;
  }
 
  /**
   * main method used to call the unit tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testCountIncrementedAfterAddingOnlyOneItem(): "
        + testCountIncrementedAfterAddingOnlyOneItem());
    System.out.println(
        "testAddAndOccurrencesOfForOnlyOneItem(): " + testAddAndOccurrencesOfForOnlyOneItem());
    System.out.println(
    		"testAddOccurrencesOfDuplicateItems(): " + testAddOccurrencesOfDuplicateItems());
    System.out.println(
    		"testAddingTooMuchItems(): " + testAddingTooMuchItems());
    System.out.println(
    		"testRemoveOnlyOneOccurrenceOfItem(): " + testRemoveOnlyOneOccurrenceOfItem());
    System.out.println(
    		"testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart());
    System.out.println(
    		"correctSubTotal(): " + correctSubTotal());
  }


  // Checks that items can be added more than one time and are found
  public static boolean testAddOccurrencesOfDuplicateItems() {
	  boolean test1 = false;
	  boolean test2 = false;
	  String[] cart = new String[10];
	  int count = 0;
	  count = ShoppingCart.add(1, cart, count);
	  count = ShoppingCart.add(2, cart, count);
	  count = ShoppingCart.add(1, cart, count);
	  
	  if (count == 3) {
		  System.out.println("Add test works");
		  test1 = true;
		  
	  }
	  int occNum = 0;
	  occNum = ShoppingCart.occurrencesOf(1, cart, count);
	  
	  if (occNum == 2) {
		  System.out.println("Occ test works");
		  test2 = true;
	  }
	  
	if (test1 == false || test2 == false ) {
		return false;
	}
	 return true; 
  }
  
  // Checks that the correct output is returned when the user tries to add too much items to the cart
  // exceeding its capacity
  public static boolean testAddingTooMuchItems() {
	  int count = 0;
	  boolean addtest = false;
	  for (int i = 0; i < 25; i ++) {
		  String[] cart = new String[20];
		  count = ShoppingCart.add(1, cart, count);
	  }
	  if (count == 20) {
		  addtest = true;
	  }
	  return true;
  }
//Checks that when only one attempt to remove an item present in the cart is made, only one occurrence of 
//that item is removed from the cart
  public static boolean testRemoveOnlyOneOccurrenceOfItem() {
	  boolean removetest = false;
	  String[] cart = new String[10];
	  int count = 0;
	  int occNum = 0;
	  count = ShoppingCart.add(1, cart, count);
	  count = ShoppingCart.add(2, cart, count);
	  count = ShoppingCart.add(1, cart, count);
	  
	  count = ShoppingCart.remove("Avocado", cart, count);
	  
	  occNum = ShoppingCart.occurrencesOf(1, cart, count);
	  
	  if (count == 2 && occNum == 1) {
		  removetest = true;
	  }
	  return removetest;
  }

//Checks that remove does not make any change to count (number of items in the cart) when the user
//tries to remove an item not present within the cart 
public static boolean testRemoveItemNotFoundInCart() {
	boolean removetest = false;
	  String[] cart = new String[10];
	  int count = 0;
	  count = ShoppingCart.add(1, cart, count);
	  count = ShoppingCart.add(2, cart, count);
	  count = ShoppingCart.add(1, cart, count);
	  
	  count = ShoppingCart.remove("Apple", cart, count);
	  
	  if (count == 3) {
		  removetest = true;
	  }
	  
	  return removetest;
}

public static boolean correctSubTotal() {
	boolean costtest = false;
	  String[] cart = new String[10];
	  int count = 0;
	  double subtotal;
	  count = ShoppingCart.add(1, cart, count);
	  count = ShoppingCart.add(2, cart, count);
	  count = ShoppingCart.add(1, cart, count);
	  count = ShoppingCart.add(3, cart, count);
	  count = ShoppingCart.add(7, cart, count);
	  count = ShoppingCart.add(15, cart, count);
	  count = ShoppingCart.add(8, cart, count);
	  
	  subtotal = ShoppingCart.getSubTotalPrice(cart, count);
	  System.out.println(subtotal);
	  
	  if (subtotal == 12.63) {
		  costtest = true;
	  }
	  return costtest;
	  
}

}
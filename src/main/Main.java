package main;

import cart.Store;

public class Main
{
    public static void main(String[] args)
    {
        // Creates new Shopping Store where one can purchase items
        Store shoppingStore = new Store();

        // Requests user if they wish to add items to Shopping Cart
        shoppingStore.addToShoppingCart();

        // Check out and calculate costs, if applicable
        shoppingStore.checkOut();
    }
}

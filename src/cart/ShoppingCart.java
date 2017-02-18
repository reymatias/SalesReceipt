package cart;

import java.util.*;

import product.Product;

public class ShoppingCart
{
    private List<Product> shoppingCart;

    public ShoppingCart()
    {
        // Create a Shopping Cart that is an ArrayList holding all the Products
        shoppingCart = new ArrayList<Product>();
    }

    // Add a Product to the Shopping Cart
    public void addProduct(Product newProduct)
    {
        shoppingCart.add(newProduct);
    }

    // Returns all items in the Shopping Cart
    public List<Product> getItemsFromShoppingCart()
    {
        return shoppingCart;
    }
}

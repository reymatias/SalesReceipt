package cart;

import java.util.*;
import java.math.BigDecimal; // Using Big Decimal for currency calculations

import product.Product;
import tax.TaxCalculator;

public class Store
{
    private ShoppingCart shoppingCart;
    private TaxCalculator calculateTax;

    // Constructor
    public Store()
    {
        shoppingCart = new ShoppingCart();
        calculateTax = new TaxCalculator();
    }

    // Asks user if they wish to add Products to their Shopping Cart
    public boolean addToShoppingCart()
    {
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;

        System.out.print("\nWould you like to add a Product? (Y/N) ");

        while(flag)
        {
            switch(scanner.nextLine().trim().toLowerCase())
            {
                case "y":
                    // Displays Store Inventory. Items pulled from assignment instructions.
                    storeInventory();

                    // Select an item from the Inventory, and assign it's quantity, price,
                    // and if it's an imported product
                    selectProduct();

                case "n":
                    // Break out of loop and proceed with Check Out
                    flag = !flag;
                    break;

                default:
                    System.out.print("\nInvalid input. Please try again: ");
            }
        }

        return flag;
    }

    // Displays store inventory
    private void storeInventory()
    {
        System.out.println("\n1. Book");
        System.out.println("2. Music CD");
        System.out.println("3. Chocolate Bar");
        System.out.println("4. Box of Chocolates");
        System.out.println("5. Bottle of Perfume");
        System.out.println("6. Packet of Headache Pills\n");
    }

    // Product selection based on the items in the inventory
    public void selectProduct()
    {
        do {
            int description = getProductDescription(); // Item selection
            boolean isImported = isProductImported(); // Determine if it's imported or not
            int quantity = getProductQuantity(); // Determine how many of the products is purchased
            String price = getProductPrice(); // Set price of product

            // Determine if item is exempted based on the type of product is selected from the inventory
            // All book, food, and medical items are exempted. These are determined by integer representing
            // each product.
            boolean isExempted = isProductExempted(description);

            // Create new Product object
            Product newProduct = new Product(quantity, description, isImported, isExempted, price);

            // Store object inside the user's Shopping Cart
            shoppingCart.addProduct(newProduct);
        } while(addToShoppingCart()); // Break out of loop when user wishes to Check Out
    }

    // Select Product based on corresponding integer listed in Inventory
    private int getProductDescription()
    {
        // NOTE: Inventory has 6 items
        Scanner scanner = new Scanner(System.in);

        int productSelected = 0;
        boolean flag = true;

        System.out.print("Please enter the item being purchased: ");

        while(flag) {
            productSelected = scanner.nextInt();

            if(productSelected >= 7 || productSelected <= 0) {
                System.out.print("There is no product listed with that number. Please try again: ");

            } else if(productSelected <= 6 || productSelected >= 1) {
                flag = false;
                break; // Input entered is acceptable

            }
        }

        return productSelected;
    }

    // Determine if Product is imported
    private boolean isProductImported()
    {
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        boolean imported = true;

        System.out.print("Is this product being imported? (Y/N): ");

        while(flag)
        {
            switch(scanner.nextLine().trim().toLowerCase())
            {
                case "y": // Product is imported
                    flag = false;
                    break;

                case "n": // Product is not imported
                    imported = false;
                    flag = false;
                    break;

                default: // Invalid input
                    System.out.print("Invalid input. Please try again: ");
            }
        }

        return imported;
    }

    // Determine how many of the Product selected are being purchased
    public int getProductQuantity()
    {
        Scanner scanner = new Scanner(System.in);

        int quantityEntered = 0;
        boolean flag = true;

        System.out.print("Quantity of product being purchased: ");

        while(flag) {
            quantityEntered = scanner.nextInt();

            if(quantityEntered <= 0) {
                System.out.print("Invalid input. Please try again: ");

            } else {
                flag = false;
                break;

            }
        }

        return quantityEntered;
    }

    // Set price of the Product being purchased
    public String getProductPrice()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Retail price listed (Format x.xx): ");
        return scanner.nextLine();
    }

    // Determine if the Product is exempted
    public boolean isProductExempted(int description)
    {
        boolean exempted;

        // Check if Product is an exempted item based on the corresponding integer listed in Inventory
        if(description == 1 || description == 3 || description == 4 || description == 6) {
            exempted = true;

        } else {
            exempted = false;

        }

        return exempted;
    }

    // Check Out and calculate taxed price for every product inside Shopping Cart, total sales tax, and total cost
    public void checkOut()
    {
        // Pull items from Shopping Cart
        List<Product> fullShoppingCart = shoppingCart.getItemsFromShoppingCart();

        BigDecimal productPrice; // Price of selected Product
        BigDecimal productTax; // Tax applied to selected Product
        BigDecimal productSubTotal; // Selected Product's taxed price

        BigDecimal salesTax = new BigDecimal("0.00");
        BigDecimal totalCost = new BigDecimal("0.00");

        System.out.println(); // New line create for text format purposes

        for(Product p : fullShoppingCart)
        {
            // Calculate tax for the Product selected
            productTax = calculateTax.performCalculation(p.getImportIdentifier(), p.getExemptIdentifier(), p.getPrice());

            // Pull price for Product selected
            productPrice = new BigDecimal(p.getPrice());

            // Calculate selected Product's taxed price
            productSubTotal = productTax.add(productPrice);

            // Add Product's tax to total sales tax
            salesTax = salesTax.add(productTax);

            // Add Product's taxed price to total cost
            totalCost = totalCost.add(productSubTotal);

            System.out.println(p.getQuantity() + " " + p.importString() + p.getCompleteDescription() + ": " +
                    productSubTotal.toString());
        }

        // Print total sales tax and total cost if those values are not $0.00
        if(salesTax.doubleValue() != 0.00 && totalCost.doubleValue() != 0.00)
        {
            System.out.println("Sales Taxes: " + salesTax.toString());
            System.out.println("Total: " + totalCost.toString());
        }
    }
}

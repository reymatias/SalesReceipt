package product;

public class Product
{
    private int quantity;
    private int description;
    private boolean isImported;
    private boolean isExempted;
    private String price;

    // Create Product instance
    public Product(int quantity, int description, boolean isImported, boolean isExempted, String price)
    {
        this.quantity = quantity;
        this.description = description;
        this.isImported = isImported;
        this.isExempted = isExempted;
        this.price = price;
    }

    // Return quantity of Product purchased
    public int getQuantity()
    {
        return quantity;
    }

    // Return the number identifying the Product
    public int getDescription()
    {
        return description;
    }

    // Return whether the Product is imported
    public boolean getImportIdentifier()
    {
        return isImported;
    }

    // Return whether the Product is exempted
    // Exempted items: Books, Food, and Medicine
    public boolean getExemptIdentifier()
    {
        return isExempted;
    }

    // Return Product's retail price (before tax)
    public String getPrice()
    {
        return price;
    }

    // Based on value of isImported, return the string "imported"
    // This is used during Check Out, and only if the Product is imported
    public String importString() {
        String stringImport;

        if(getImportIdentifier()) {
            stringImport = "imported ";
        } else {
            stringImport = "";
        }

        return stringImport;
    }

    // Return the corresponding name to the Product selected during shopping
    // This is based on the integer and corresponding string listed in Inventory
    public String getCompleteDescription()
    {
        String description = "";

        switch(getDescription())
        {
            case 1 :
                description = "Book";
                break;

            case 2 :
                description = "Music CD";
                break;

            case 3 :
                description = "Chocolate Bar";
                break;

            case 4 :
                description = "Box of Chocolates";
                break;

            case 5 :
                description = "Bottle of Perfume";
                break;

            case 6 :
                description = "Packet of Headache Pills";
                break;
        }

        return description;
    }
}

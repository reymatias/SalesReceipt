package tax;

import java.math.BigDecimal; // Using Big Decimal for currency calculations
import java.math.RoundingMode;

public class TaxCalculator
{
    private BigDecimal importTax;
    private BigDecimal exemptTax;
    private BigDecimal comboTax;
    private BigDecimal productTax;

    // Initialize Tax Calculator with the values of the import and non-exempted tax
    // as well as the tax of imported, non-exempted Products
    public TaxCalculator()
    {
        productTax = new BigDecimal("0.00");
        importTax = new BigDecimal("0.05");
        exemptTax = new BigDecimal("0.10");
        comboTax = new BigDecimal("0.15");
    }

    public BigDecimal performCalculation(boolean isImported, boolean isExempted, String price)
    {
        // Re-initialize the selected Product's tax to 0 (zero)
        productTax = BigDecimal.ZERO;

        BigDecimal productPrice = new BigDecimal(price);

        if(isImported && isExempted) {
            // IMPORTED_BOOK, IMPORTED_FOOD, IMPORTED_MEDICINE
            productTax = productPrice.multiply(importTax);

        } else if(!isImported && isExempted) {
            // BOOK, FOOD, MEDICINE
            productTax = productPrice.multiply(productTax);

        } else if(isImported && !isExempted) {
            // IMPORTED_MISCELLANEOUS
            productTax = productPrice.multiply(comboTax);

        } else if(!isImported && !isExempted) {
            // MISCELLANEOUS
            productTax = productPrice.multiply(exemptTax);
        }

        // Round tax up to the nearest nickel ($0.05)
        productTax = roundTax(productTax);

        return productTax;
    }

    // Round tax up to the nearest nickel ($0.05)
    public BigDecimal roundTax(BigDecimal productTax)
    {
        BigDecimal roundTo = new BigDecimal("0.05");

        return productTax.divide(roundTo, 0, RoundingMode.CEILING).multiply(roundTo);
    }
}
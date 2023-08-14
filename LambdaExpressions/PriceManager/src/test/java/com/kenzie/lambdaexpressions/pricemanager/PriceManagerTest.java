package com.kenzie.lambdaexpressions.pricemanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceManagerTest {

    private PriceManager priceManager;

    @BeforeEach
    public void setup() {
        priceManager = new PriceManager();
    }

    @Test
    public void calculateStateTotalPrices_withSalesTaxOnly_computesNewPrices() {
        //GIVEN
        List<BigDecimal> prices = new ArrayList<>();
        prices.add(new BigDecimal("9.99"));
        prices.add(new BigDecimal(10));
        prices.add(new BigDecimal("12.99"));

        BigDecimal salesTax = new BigDecimal("1.08");

        List<BigDecimal> expectedPrices = computePriceWithSalesTax(prices, salesTax);

        // WHEN
        List<BigDecimal> newPrices = priceManager.calculateStateTotalPrices(prices, salesTax);

        // THEN
        newPrices = roundPriceList(newPrices);
        assertEquals(expectedPrices, newPrices, "Tax calculated incorrectly.");
    }

    @Test
    public void calculateStateTotalPrices_withSetupFeeAndSalesTax_computesNewPrices() {
        //GIVEN
        List<BigDecimal> prices = new ArrayList<>();
        prices.add(new BigDecimal("19.99"));
        prices.add(new BigDecimal(10));
        prices.add(new BigDecimal("29.99"));

        BigDecimal setupFee = new BigDecimal(2);
        BigDecimal salesTax = new BigDecimal("1.08");

        List<BigDecimal> expectedPrices = computePriceWithSalesTaxAndSetupFee(prices, setupFee,
                salesTax);

        // WHEN
        List<BigDecimal> newPrices = priceManager.calculateStateTotalPrices(prices, setupFee, salesTax);

        // THEN
        newPrices = roundPriceList(newPrices);
        assertEquals(expectedPrices, newPrices, "Tax and fee calculated incorrectly.");
    }

    @Test
    public void calculateStateTotalPrices_withFees_computesNewPrices() {
        //GIVEN
        List<BigDecimal> prices = new ArrayList<>();
        prices.add(new BigDecimal("49.99"));
        prices.add(new BigDecimal(50));
        prices.add(new BigDecimal("4.99"));

        BigDecimal setupFee = new BigDecimal(2);
        BigDecimal salesFee = new BigDecimal("2.28");
        BigDecimal countyFee = new BigDecimal(3);

        List<BigDecimal> expectedPrices = computePriceWithSalesTaxCountyTaxAndSetupFee(prices,
                setupFee, salesFee, countyFee);

        // WHEN
        List<BigDecimal> newPrices = priceManager.calculateStateTotalPrices(prices, setupFee, salesFee, countyFee);

        // THEN
        newPrices = roundPriceList(newPrices);
        assertEquals(expectedPrices, newPrices, "Fees calculated incorrectly.");
    }

    /**
     * Helper function to calculate the price of an item with fees and taxes.
     * @param originalPrices List of original prices that fees and taxes will be applied to.
     * @param salesTax Sales tax to be added.
     * @return a new price that includes fees and taxes.
     */
    public List<BigDecimal> computePriceWithSalesTax(List<BigDecimal> originalPrices, BigDecimal salesTax) {
        List<BigDecimal> priceTotals = new ArrayList<>();
        BigDecimal newPrice;
        for (BigDecimal price : originalPrices) {
            newPrice = price.multiply(salesTax);
            newPrice = newPrice.round(new MathContext(4));
            priceTotals.add(newPrice);
        }
        return priceTotals;
    }
    /**
     * Helper function to calculate the price of an item with fees and taxes.
     * @param originalPrices List of original prices that fees and taxes will be applied to.
     * @param setupFee Setup fee to be added.
     * @param salesTax Sales tax to be added.
     * @return a new price that includes fees and taxes.
     */
    public List<BigDecimal> computePriceWithSalesTaxAndSetupFee(List<BigDecimal> originalPrices, BigDecimal setupFee,
                                                                BigDecimal salesTax) {
        List<BigDecimal> priceTotals = new ArrayList<>();
        BigDecimal newPrice;
        for (BigDecimal price : originalPrices) {
            newPrice = price.add(setupFee);
            newPrice = newPrice.multiply(salesTax);
            newPrice = newPrice.round(new MathContext(4));
            priceTotals.add(newPrice);
        }
        return priceTotals;
    }

    /**
     * Helper function to calculate the price of an item with fees and taxes.
     * @param originalPrices List of original prices that fees will be applied to.
     * @param setupFee Setup fee to be added.
     * @param salesFee Sales fee to be added.
     * @param countyFee County fee to be added.
     * @return a new price that includes fees and taxes.
     */
    public List<BigDecimal> computePriceWithSalesTaxCountyTaxAndSetupFee(List<BigDecimal> originalPrices, BigDecimal
            setupFee, BigDecimal salesFee, BigDecimal countyFee) {
        List<BigDecimal> priceTotals = new ArrayList<>();
        BigDecimal newPrice;
        for (BigDecimal price : originalPrices) {
            newPrice = price.add(setupFee);
            newPrice = newPrice.add(salesFee);
            newPrice = newPrice.add(countyFee);
            newPrice = newPrice.round(new MathContext(4));
            priceTotals.add(newPrice);
        }
        return priceTotals;
    }

    /**
     * Rounds a list of prices to be used for comparison to another list.
     * @param prices List of prices
     * @return List of prices that is rounded
     */
    public List<BigDecimal> roundPriceList(List<BigDecimal> prices) {
        List<BigDecimal> roundedPrices = new ArrayList<>(prices);
        for (int index = 0; index < roundedPrices.size(); index++) {
            roundedPrices.set(index, roundedPrices.get(index).round(new MathContext(4)));
        }
        return roundedPrices;
    }
}

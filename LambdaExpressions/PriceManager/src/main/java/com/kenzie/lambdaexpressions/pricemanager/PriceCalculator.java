package com.kenzie.lambdaexpressions.pricemanager;

import java.math.BigDecimal;

import java.util.ArrayList;

import java.util.List;
import java.util.function.Function;

/**
 * Provides logic to apply pricing rules to a list of prices. The
 * pricing rules are provided as an argument to the {@code calculateTotalPrices}
 * method, as a {@code Function<BigDecimal, BigDecimal>}
 */
public class PriceCalculator {

    /**
     * Applies taxes and fees to a List of prices.
     * @param prices A list of starting prices.
     * @param priceComputer A Lambda expression that implements the Function interface that computes the total price
     *                       when given a starting price.
     * @return An ArrayList of new prices.
     */
    public List<BigDecimal> calculateTotalPrices(List<BigDecimal> prices,
                                                 Function<BigDecimal, BigDecimal> priceComputer) {
        List<BigDecimal> newPrices = new ArrayList<>();
        // TODO Iterate through prices, use priceComputer to return a new price and add it to newPrices
        for(BigDecimal price: prices){
            BigDecimal newPrice = priceComputer.apply(price);
            newPrices.add(newPrice);
        }
        return newPrices;
    }
}

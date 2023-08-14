package com.kenzie.lambdaexpressions.numbergenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Utility providing random numbers, demonstrating lambda expression usage.
 */
public class NumberGenerator {

    /**
     * Main method of project. When completed it should print to standard output a list of five random integers
     * between 0 and 500.
     * @param args Main method parameter
     */
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator();
        List<Integer> randomNumbers = numberGenerator.generateNumbers();
        System.out.println(randomNumbers);
    }

    /**
     * Populates a list with five random numbers between 0 and 499.
     * @return a new list of 5 random integers, where 0 <= value <= 499.
     */
    public List<Integer> generateNumbers() {
        // TODO: replace second argument with a lambda expression that implements Supplier<Integer>
        return populateNumbers(5, null);
    }

    private List<Integer> populateNumbers(int size, Supplier<Integer> numberGenerator) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            // TODO: Replace argument to add() with a call to numberGenerator's method
            numbers.add(-1);
        }

        return numbers;
    }
}

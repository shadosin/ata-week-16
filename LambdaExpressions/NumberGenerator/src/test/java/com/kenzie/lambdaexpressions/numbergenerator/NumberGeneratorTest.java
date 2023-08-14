package com.kenzie.lambdaexpressions.numbergenerator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberGeneratorTest {

    @Test
    public void generateNumbers_withFiveNumbers_successfullyGeneratesNumbers() {
        // GIVEN
        NumberGenerator numberGenerator = new NumberGenerator();

        // WHEN
        List<Integer> randomNumbers = numberGenerator.generateNumbers();

        // THEN
        assertEquals(5, randomNumbers.size(), "Incorrect List size. Expected a list of five " +
                "numbers, received: " + randomNumbers);
        for (int number : randomNumbers) {
            assertTrue(number >= 0 && number < 500, "Encountered number out of valid range. " +
                    "Expected a number between 0 and 500, received: " + number);
        }
    }
}

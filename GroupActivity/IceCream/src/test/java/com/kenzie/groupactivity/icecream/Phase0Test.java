package com.kenzie.groupactivity.icecream;

import com.kenzie.groupactivity.icecream.dependency.DaggerIceCreamParlorServiceComponent;
import com.kenzie.groupactivity.icecream.dependency.IceCreamParlorServiceComponent;
import com.kenzie.groupactivity.icecream.exception.CartonCreationFailedException;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Phase0Test {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();
    private IceCreamParlorService service;

    @BeforeEach
    private void setup() {
        service = DAGGER.provideIceCreamParlorService();
    }

    @Test
    void prepareFlavors_withAnInvalidFlavor_throwsException() {
        // GIVEN
        // Valid flavor + invalid flavor
        List<String> flavors = ImmutableList.of("Vanilla", "Garlic");

        // WHEN + THEN - exception thrown
        assertThrows(
            CartonCreationFailedException.class,
            () -> service.prepareFlavors(flavors),
            "Expected exception when preparing a list with invalid flavor: " + flavors);
    }
}

package com.kenzie.groupactivity.icecream;

import com.kenzie.groupactivity.icecream.dependency.DaggerIceCreamParlorServiceComponent;
import com.kenzie.groupactivity.icecream.dependency.IceCreamParlorServiceComponent;
import com.kenzie.groupactivity.icecream.model.Ingredient;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Spy;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class Phase3Test {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();

    @Spy
    private IceCreamParlorService serviceSpy;

    @Captor
    private ArgumentCaptor<List<Queue<Ingredient>>> makeIceCreamCartonsCaptor;

    @BeforeEach
    private void setup() {
        serviceSpy = DAGGER.provideIceCreamParlorService();
        initMocks(this);
    }

    @Test
    void prepareFlavors_withOneFlavor_makeIceCreamCartonsReceivesCorrectIngredients() {
        // GIVEN
        Queue<Ingredient> q = new PriorityQueue<>();
        q.add(Ingredient.VANILLA);
        List<Queue<Ingredient>> ingredientQ = ImmutableList.of(q);
        List<String> flavors = ImmutableList.of("Vanilla");
        when(serviceSpy.makeIceCreamCartons(Matchers.anyList())).thenReturn(flavors.size());

        // WHEN
        serviceSpy.prepareFlavors(flavors);

        // THEN
        // we receive the right number of ingredients queues
        verify(serviceSpy)
            .makeIceCreamCartons(makeIceCreamCartonsCaptor.capture());
        List<Queue<Ingredient>> ingredientsQueues = makeIceCreamCartonsCaptor.getValue();
        assertEquals(
            flavors.size(),
            ingredientsQueues.size(),
            String.format("Expected ingredient queue size to be %d but got: %s", flavors.size(), ingredientsQueues)
        );

        // The ingredients are the ones we expect
        Queue<Ingredient> ingredients = makeIceCreamCartonsCaptor.getValue().get(0);
        assertEquals(
            Ingredient.CREAM,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.CREAM);
        assertEquals(
            Ingredient.SUGAR,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.SUGAR);
        assertEquals(
            Ingredient.EGGS,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.EGGS);
        assertEquals(
            Ingredient.VANILLA,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.VANILLA);
        assertTrue(ingredients.isEmpty(), "Expected ingredients queue to be empty at this point");
    }

    @Test
    void prepareFlavors_withFlavors_makeIceCreamCartonsReceivesCorrectIngredients() {
        // GIVEN
        Queue<Ingredient> q = new PriorityQueue<>();
        q.add(Ingredient.VANILLA);
        q.add(Ingredient.STRAWBERRIES);
        List<Queue<Ingredient>> ingredientQ = ImmutableList.of(q);
        List<String> flavors = ImmutableList.of("Vanilla", "Strawberry");
        when(serviceSpy.makeIceCreamCartons(Matchers.anyList())).thenReturn(flavors.size());

        // WHEN
        serviceSpy.prepareFlavors(flavors);

        // THEN
        // we receive the right number of ingredients queues
        verify(serviceSpy).makeIceCreamCartons(makeIceCreamCartonsCaptor.capture());
        List<Queue<Ingredient>> ingredientsQueues = makeIceCreamCartonsCaptor.getValue();
        assertEquals(
            flavors.size(),
            ingredientsQueues.size(),
            String.format("Expected ingredient queue size to be %d but got: %s", flavors.size(), ingredientsQueues)
        );

        // The strawberry ingredients are the ones we expect
        Queue<Ingredient> ingredients = makeIceCreamCartonsCaptor.getValue().get(1);
        assertEquals(
            Ingredient.CREAM,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.CREAM);
        assertEquals(
            Ingredient.SUGAR,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.SUGAR);
        assertEquals(
            Ingredient.EGGS,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.EGGS);
        assertEquals(
            Ingredient.STRAWBERRIES,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.STRAWBERRIES);
        assertTrue(ingredients.isEmpty(), "Expected ingredients queue to be empty at this point");
    }
}

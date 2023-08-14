package com.kenzie.groupactivity.icecream.converter;

import com.kenzie.groupactivity.icecream.model.Ingredient;
import com.kenzie.groupactivity.icecream.model.Recipe;

import java.util.LinkedList;
import java.util.Queue;

public final class RecipeConverter {
    private RecipeConverter() {
    }

    public static Queue<Ingredient> fromRecipeToIngredientQueue(Recipe recipe) {
        return new LinkedList<>(recipe.getIngredients());
    }
}

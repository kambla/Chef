package com.example.chef.search;

import com.example.chef.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JSONResultParser {

    private final static String JSON_KEY_RECIPE_ARRAY = "hits";
    private final static String JSON_KEY_RECIPE_OBJECT = "recipe";
    private final static String JSON_KEY_LABEL = "label";
    private final static String JSON_KEY_IMAGE_URL = "image";
    private final static String JSON_KEY_SOURCE_URL = "url";
    private final static String JSON_KEY_INGREDIENTS_ARRAY = "ingredientLines";

    static List<Recipe> parseResultJSON(String resultJSON){
        List<Recipe> recipeList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(resultJSON);
            JSONArray recipeArray = jsonObject.getJSONArray(JSON_KEY_RECIPE_ARRAY);
            for(int i = 0; i < recipeArray.length(); i++){
                JSONObject recipeJSON = recipeArray.getJSONObject(i).getJSONObject(JSON_KEY_RECIPE_OBJECT);
                String label = recipeJSON.getString(JSON_KEY_LABEL);
                String imageURL = recipeJSON.getString(JSON_KEY_IMAGE_URL);
                String sourceURL = recipeJSON.getString(JSON_KEY_SOURCE_URL);
                JSONArray ingredientsArray = recipeJSON.getJSONArray(JSON_KEY_INGREDIENTS_ARRAY);
                List<String> ingredientsList = new ArrayList<>();
                for(int j = 0; j < ingredientsArray.length(); j++)
                    ingredientsList.add(ingredientsArray.getString(j));
                Recipe recipe = new Recipe(label, imageURL, sourceURL, ingredientsList);
                recipeList.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
}

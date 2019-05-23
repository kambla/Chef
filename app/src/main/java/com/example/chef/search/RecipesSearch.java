package com.example.chef.search;

import android.net.Uri;

import com.example.chef.Recipe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class RecipesSearch {

    private String mQueryIngredients = "";
    private String mQueryRecipeCount;
    private String mQueryPreparationTime;
    private String mErrorMessage = "";
    private String mBuiltURL;

    private final static String BASE_URI = "https://api.edamam.com/search?";
    private final static String PARAM_Q = "q";
    private final static String PARAM_TO = "to";
    private final static String PARAM_TIME = "time";
    private final static String PARAM_APP_ID = "app_id";
    private final static String PARAM_APP_KEY = "app_key";
    private final static String APP_ID = "a22ced66";
    private final static String APP_KEY = "d62f0c2724608c401efdf240599ee9bb";



    RecipesSearch(List<String> ingredients, int recipeCount, int preparationTime){
        for(String ingredient : ingredients)
            mQueryIngredients += ingredient + ",";
        mQueryRecipeCount = String.valueOf(recipeCount);
        mQueryPreparationTime = String.valueOf(preparationTime);
    }

    private String buildSearchURL(){
        Uri searchURL = Uri.parse(BASE_URI).buildUpon()
                .appendQueryParameter(PARAM_Q,mQueryIngredients)
                .appendQueryParameter(PARAM_TO,mQueryRecipeCount)
                .appendQueryParameter(PARAM_TIME, mQueryPreparationTime)
                .appendQueryParameter(PARAM_APP_ID, APP_ID)
                .appendQueryParameter(PARAM_APP_KEY, APP_KEY)
                .build();
        mBuiltURL = searchURL.toString();
        return searchURL.toString();
    }

    private String getResultJSON(){
        mErrorMessage = "";
        StringBuilder resultJSONBuilder = new StringBuilder();
        try {
            URL url = new URL(this.buildSearchURL());
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedReader responseBodyReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            if(urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                String line;
                while ((line = responseBodyReader.readLine()) != null)
                    resultJSONBuilder.append(line).append("\n");
            }
            else{
                mErrorMessage = urlConnection.getResponseCode() + urlConnection.getResponseMessage();
            }
            urlConnection.disconnect();
            responseBodyReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultJSONBuilder.toString();
    }

    List<Recipe> getResult(){
        return JSONResultParser.parseResultJSON(getResultJSON());
    }

    String getBuiltURL() {
        return mBuiltURL;
    }

    String getErrorMessage() {
        return mErrorMessage;
    }
}

package com.example.chef.search;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;

import com.example.chef.Recipe;
import com.example.chef.search_result.RecipesListActivity;

import java.util.ArrayList;
import java.util.List;

public class RecipesSearchTask extends AsyncTask<RecipesSearch,Integer, List<Recipe>> {

    private Context mContext;

    public static final String RECIPES_LIST = "com.example.chef.search.RecipesSearchTask.lRecipesList";

    RecipesSearchTask(Context context){
        super();
        this.mContext = context;
    }

    //Only one arg allowed in current implementation
    @Override
    protected List<Recipe> doInBackground(RecipesSearch... recipesSearches) {
        return recipesSearches[0].getResult();
    }

    @Override
    protected void onPostExecute(List<Recipe> result){
        super.onPostExecute(result);
        Intent launchRecipesList = new Intent(mContext, RecipesListActivity.class);
        launchRecipesList.putParcelableArrayListExtra(RECIPES_LIST, (ArrayList<? extends Parcelable>) result)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(launchRecipesList);
    }

}

package com.example.chef.search_result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.chef.R;
import com.example.chef.Recipe;
import com.example.chef.search.RecipesSearchTask;

import java.util.List;

public class RecipesListActivity extends AppCompatActivity {

    private List<Recipe> mRecipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecipesList = getIntent().getParcelableArrayListExtra(RecipesSearchTask.RECIPES_LIST);
        RecipesAdapter recipesAdapter = new RecipesAdapter(this, mRecipesList);
        RecyclerView recipesListView = findViewById(R.id.recipes_list);
        recipesListView.setLayoutManager(new LinearLayoutManager(this));
        recipesListView.setAdapter(recipesAdapter);

    }

}

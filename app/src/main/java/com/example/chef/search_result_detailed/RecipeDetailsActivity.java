package com.example.chef.search_result_detailed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chef.R;
import com.example.chef.Recipe;
import com.example.chef.search_result.RecipesAdapter;

public class RecipeDetailsActivity extends AppCompatActivity {

    private ImageView mRecipeDetailsImage;
    private TextView mRecipeDetailsLabel;
    private TextView mRecipeDetailsIngredients;
    private TextView mRecipeDetailsPreparation ;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecipeDetailsImage = findViewById(R.id.recipe_details_image);
        mRecipeDetailsLabel = findViewById(R.id.recipe_details_label);
        mRecipeDetailsIngredients = findViewById(R.id.recipe_details_ingredients);
        mRecipeDetailsPreparation = findViewById(R.id.recipe_details_preparation);

        mRecipe = getIntent().getParcelableExtra(RecipesAdapter.RECIPE_DETAILS);

        mRecipeDetailsImage.setImageBitmap(mRecipe.getImageBitmap());
        mRecipeDetailsLabel.setText(mRecipe.getLabel());
        mRecipeDetailsIngredients.setText(mRecipe.ingredientsListToString());
        mRecipeDetailsPreparation.setText(getString(R.string.preparation_details_disclaimer) + mRecipe.getSourceURL());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

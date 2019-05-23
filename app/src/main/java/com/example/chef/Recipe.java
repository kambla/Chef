package com.example.chef;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private String mLabel;
    private String mImageURL;
    private String mSourceURL;
    private List<String> mIngredientsList;
    private Bitmap mImageBitmap;

    public Recipe(String label, String imageURL, String sourceURL, List<String> ingredientsList){
        this.mLabel = label;
        this.mImageURL = imageURL;
        this.mSourceURL = sourceURL;
        this.mIngredientsList = ingredientsList;
        this.mImageBitmap = null;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public Bitmap getImageBitmap() { return  mImageBitmap;}

    public String getSourceURL() {
        return mSourceURL;
    }

    public String getLabel(){
        return mLabel;
    }

    public void setImageBitmap(Bitmap imageBitmap) {this.mImageBitmap = imageBitmap;}

    protected Recipe(Parcel in) {
        mLabel = in.readString();
        mImageURL = in.readString();
        mSourceURL = in.readString();
        if(mIngredientsList == null)
            mIngredientsList = new ArrayList<>();
        in.readStringList(mIngredientsList);
        mImageBitmap = in.readParcelable(null);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLabel);
        dest.writeString(mImageURL);
        dest.writeString(mSourceURL);
        dest.writeStringList(mIngredientsList);
        dest.writeParcelable(mImageBitmap,0);
    }

    public String ingredientsListToString(){
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < mIngredientsList.size(); i++) {
            if(i < (mIngredientsList.size() - 1))
                resultBuilder.append(mIngredientsList.get(i)).append(",\n");
            else
                resultBuilder.append(mIngredientsList.get(i));
        }
        return resultBuilder.toString();
    }

    @Override
    @NonNull
    public String toString(){
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Recipe name: ").append(mLabel).append("\n")
                .append("Image url: ").append(mImageURL).append("\n")
                .append("Source url: ").append(mSourceURL).append("\n")
                .append("Ingredients: ").append(ingredientsListToString());
        return resultBuilder.toString();
    }
}

package com.hfad.recipesretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchRecipies();
}

    private void searchRecipies()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://recipepuppy.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipePuppyService service = retrofit.create(RecipePuppyService.class);
        Call<RecipeResponse> recipeResponseCall = service.searchByIngredient("bacon",
                "chocolate");
        recipeResponseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response)
            {
                Log.d("ENQUEUE", "onResponse: response" + (response == null));
                Log.d("ENQUEUE", "onResponse: response body" + (response.body()   == null));

                List<Recipe> recipes = response.body().getResults();
                Log.d("ENQUEUE", "onResponse: " + recipes.toString());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d("ENQUEUE", "onFailure" + t.getMessage());
            }
        });
    }
}

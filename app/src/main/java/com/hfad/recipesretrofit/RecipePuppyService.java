package com.hfad.recipesretrofit;



import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface RecipePuppyService
{
    @GET("api/")
    Call<RecipeResponse> searchByIngredient(@Query("i") String ingredients, @Query("q") String recipeKeywords);

}

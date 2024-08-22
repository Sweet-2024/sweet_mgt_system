package main_entities;

public class Recipe {
    private String recipeName;
    private String recipeDescription;
    private String recipeCate;
    private String publisherEmail;

    public Recipe(String recipeName, String recipeDescription, String recipeCate, String publisherEmail) {
        this.publisherEmail = publisherEmail;
        this.recipeDescription = recipeDescription;
        this.recipeCate = recipeCate;
        this.recipeName = recipeName;
    }
    public String getRecipeName() {
        return recipeName;
    }


    public String getRecipeDescription() {
        return recipeDescription;
    }

    public String getRecipeCate() {
        return recipeCate;
    }


    public String getPublisherEmail() {
        return publisherEmail;
    }

}

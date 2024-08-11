package Entities;

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

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeCate() {
        return recipeCate;
    }

    public void setRecipeCate(String recipeCate) {
        this.recipeCate = recipeCate;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

}

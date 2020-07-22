package food;

import inventory.Inventory;

import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable {
    private String foodName;
    private ArrayList<FoodIngredient> ingredients = new ArrayList<>();
    private Double foodPrice;

    /**
     * empty constructor
     */
    public Food() {
    }

    /**
     * Constructor
     * @param ingredients ingredients for the food
     * @param foodName name of the food
     * @param foodPrice price of the food
     */
    public Food(ArrayList<FoodIngredient> ingredients, String foodName, Double foodPrice) {
        this.ingredients = ingredients;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    /**
     * @return returns the name of the Food
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * @param foodName sets the name of the Food
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * @return returns the ingredients the Food requires
     */
    public ArrayList<FoodIngredient> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients returns the ingredients the Food requires
     */
    public void setFoodIngredients(ArrayList<FoodIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return the price of the food
     */
    public Double getFoodPrice() {
        return foodPrice;
    }

    /**
     * @param foodPrice sets the price of the food
     */
    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    /**
     * orders the Foods ... has to intract with the ingredient class
     * @param foodName name of the food ordered
     * @return the food
     */
    public Food orderFoods(String foodName){
        ArrayList<Food> foodList = Inventory.getInstance().getFood();

        boolean dishExists = false;
        Food food = new Food();

        for (Food value : foodList) {

            if ((value.getFoodName()).compareTo(foodName) == 0) {
                dishExists = true;

                food.setFoodIngredients(value.getIngredients());// = d;
                food.setFoodName(value.getFoodName());
                food.setFoodPrice(value.getFoodPrice());
                System.out.println(foodName);
                break;
            }
        }
        //check if it broke out of the loop from prior, if it did, Dish was found and can continue
        if(!dishExists) {
            System.out.println("Here!");
            return null;
        }

        return food;
    }


    /**
     * @return a string with the food name ingredients and foodprice
     */
    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", ingredients=" + ingredients +
                ", foodPrice=" + foodPrice +
                '}';
    }
}

import food.Food;
import food.FoodIngredient;
import food.FoodOrder;
import inventory.Ingredient;
import inventory.Inventory;

import java.io.Serializable;
import java.util.ArrayList;

public class POS implements Serializable {
    private Food food = new Food();
    private FoodIngredient foodIngredient = new FoodIngredient();
    private FoodOrder foodOrder = new FoodOrder();
    private Ingredient ingredient = new Ingredient();
    private ArrayList<FoodOrder> foodOrderList = new ArrayList<>();





    public POS() {
    }

    /**
     * This will receive a foods name
     * Depending on what the user entered and then pass the food name to the ordering system
     * @param foodName food name
     * @return returns the food made
     */
    public boolean orderFood(String foodName) {

        food = food.orderFoods(foodName);
        if (food == null) {
            System.out.println("Dish does not exist");
            return false;
        }

        return foodOrder.getIngredientForFood( food);
    }


    /**
     *  gets the total cost of the ingredient
     * @return teh total cost of the ingredient
     */
    public double getTotalIngredientCost(){
    ArrayList<Ingredient> inventory = Inventory.getInstance().getIngredients();
    double cost;
    double totalCost = 0;

        for (Ingredient value : inventory) {
            cost = value.getItemQuantity() * value.getItemPrice();
            totalCost = totalCost + cost;
        }
    return totalCost;
    }

    /**
     * refills the Inventory completely
     * @param quantity the amount of qunataity that takes to fill up the inventory
     */
    public void refillInventory(int quantity){
        ArrayList<Ingredient> inventory = Inventory.getInstance().getIngredients();
        for(int i = 0; i < inventory.size(); i ++){

            Ingredient ingredientStaged = new Ingredient(inventory.get(i).getItemName(), inventory.get(i).getItemQuantity(), inventory.get(i).getItemPrice());
            ingredientStaged.setItemQuantity(10);
            inventory.set(i, ingredientStaged);
            Inventory.getInstance().WriteIngredients(inventory);
            System.out.println("heenen");
        }
    }


    /**
     * @return the to string with bunch of thing
     */
    @Override
    public String toString() {
        ArrayList<Ingredient> inventory = Inventory.getInstance().getIngredients();
        double totalIngredientCost = 0;
        return "POS{" +
                "totalIngredientCost=" + totalIngredientCost +
                ", food=" + food +
                ", foodIngredient=" + foodIngredient +
                ", foodOrder=" + foodOrder +
                ", ingredient=" + ingredient +
                ", foodOrderList=" + foodOrderList +
                ", inventory=" + inventory +
                '}';
    }
}



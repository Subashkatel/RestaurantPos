package food;


import inventory.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class FoodIngredient implements Serializable {
    private Ingredient ingredient;

    private ArrayList<Food> orderedFood = new ArrayList<>();
    private int quantity;

    /**
     * empty constructor
     */
    public FoodIngredient() {
    }

    /**
     * @param ingredient food ingredients
     * @param quantity of the each ingredient need to make the food
     */
    public FoodIngredient(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    /**
     * @return the ingredinet required for the food
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * @return get the quantit for the each food
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param ingredient set the ingredients
     */
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    /**
     * @param quantity sets the ingredient qunantity for the food
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * @param o object
     * @return if two ingredient are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodIngredient)) return false;
        FoodIngredient that = (FoodIngredient) o;
        return Objects.equals(getIngredient(), that.getIngredient());
    }

    /**
     * @return the has code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIngredient(), getQuantity());
    }

    /**
     * @return the string withe ingredient and the quantity
     */
    @Override
    public String toString() {
        return "FoodIngredient{" +
                "ingredient=" + ingredient +
                ", quantiy=" + quantity +
                '}';
    }
}

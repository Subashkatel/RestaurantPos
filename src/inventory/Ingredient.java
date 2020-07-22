package inventory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Ingredient implements Serializable {
    private String itemName;
    private int itemQuantity;
    private double itemPrice;


    /**
     * empty constructor
     */
    public Ingredient() {
    }

    /**
     *
     * @param itemName name of the item
     * @param itemQuantity quantity of the item
     * @param itemPrice price of the item
     */
    public Ingredient(String itemName, int itemQuantity, double itemPrice) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    /**
     * @param s item name
     */
    public Ingredient(String s) {
        this.itemName = s;
    }

    /**
     * @return the name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @param itemName sets the name of the item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return  the item Quantity
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * @param itemQuantity sets the item quantity
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * @return the item price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice set the price for the item
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Gets the cost of total quantiy of the ingredient
     * @return the cost of the ingredient
     */
    public double getTotalIngredientCost(){
        ArrayList<Ingredient> list = Inventory.getInstance().getIngredients();
        double cost;
        double totalCost = 0;

        for (Ingredient ingredient : list) {
            cost = ingredient.getItemQuantity() * ingredient.getItemPrice();
            totalCost = totalCost + cost;
        }

        return totalCost;
    }


    /**
     * get the total quantiy for the all of the ingredient
     * @return the total quantiy of the ingredient
     */
    public int getTotalQuantityOfIngredient() {
        ArrayList<Ingredient> list = Inventory.getInstance().getIngredients();
        int total = 0;
        for (Ingredient ingredient : list) {
            total = total + ingredient.getItemQuantity();
        }
        return total;
    }

    /**
     * Check if the ingredient is avaliable or not
     * @param count count fo the ingredient
     * @return if its avaliavle or not
     */
    public boolean isIngredientAvail(int count) {
        boolean isAvailable = false;

        if(this.getItemPrice() >= count) {
            isAvailable = true;
        }

        return isAvailable;
    }

    /**
     *  check if two item are equal to each other or not
     * @param o object
     * @return if the two items name are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(itemName, that.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }

    /**
     * @return returns the string with name qunatity and the price of the item
     */
    @Override
    public String toString() {
        return "Inventory.Ingredient{" +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemPrice=" + itemPrice +
                '}';
    }
}

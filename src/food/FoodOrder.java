package food;

import inventory.Ingredient;
import inventory.Inventory;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FoodOrder implements Serializable {
    private String CostumerID;
    private double totalPrice;
    private ArrayList<Food> orderedFood = new ArrayList<>();


    /**
     * empty constructor
     */
    public FoodOrder() {
    }

    /**
     * @return returns the name of the costumer
     */
    public String getCostumerID() {
        return CostumerID;
    }

    /**
     * sets the name for the Order
     * @param costumerID  name of the costumer
     */
    public void setCostumerID(String costumerID) {
        CostumerID = costumerID;
    }

    /**
     * Retrieves and removes ingredients in the system needed to make a food. Does not
     * remove any ingredients if there are not enough ingredients to make a food.
     * @param food user desired food
     * @return true if food could be made. Returns false if could not make food
     */
    public boolean getIngredientForFood(Food food) {
        ArrayList<Ingredient> ingList = Inventory.getInstance().getIngredients();

        var foodMade = false;
        for (int j = 0; j < food.getIngredients().size(); j++) {
            for (int i = 0; i < ingList.size(); i++) {
                if (food.getIngredients().get(j).getIngredient().getItemName().equals(ingList.get(i).getItemName())) {

                    if (food.getIngredients().get(j).getQuantity() <= ingList.get(i).getItemQuantity()) {

                        var newQuatity = ingList.get(i).getItemQuantity() - food.getIngredients().get(j).getQuantity();

                        Ingredient ingredientStaged = new Ingredient(ingList.get(i).getItemName(),
                                ingList.get(i).getItemQuantity(), ingList.get(i).getItemPrice());
                        ingredientStaged.setItemQuantity(newQuatity);
                        ingList.set(i, ingredientStaged);

                        orderedFood.add(food);

                        foodMade = true;
                    }
                    else {
                        return false;
                    }
                }
            }
            Inventory.getInstance().WriteIngredients(ingList);
        }
        return foodMade;
    }


    /**
     * Gets price for each food that costumer orders and adds them up
     * @return the total price for the food that costumer ordered
     */
    public double totalPrice (){

        for (Food food : orderedFood) {
            totalPrice = totalPrice + food.getFoodPrice();
        }

        return totalPrice;
    }

    /**
     * Gets the date range and prints out the report for the date range
     * @param input Gets the Date Range form the admin to print the income report
     */
     public void incomeReport(Scanner input){
        System.out.print("Entrer a date range to get food orders form (mm/dd/yy) (mm/dd/yy)");
        String date = input.next();
         SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
         Date start = null;
         Date end = null;
         try{
             start = format.parse(date);
         }catch (ParseException e){
             e.printStackTrace();
         }
         date = input.next();
         try{
             end = format.parse(date);
         }catch (ParseException e){
             e.printStackTrace();
         }
         int total = 0;
         for (Food i: orderedFood){
//             if (i.getOrderDate.compareTO(start) >= 0 && i.getOrderDate().compare(end) <=) {
//
//                 i.display();
//                 total += i.getFoodName()();
//             }
         }
         System.out.println("TOTAL EARNED BETWEEN\n"+ start + "\nAND\n" + end + "\nEQUALS: $" + total + "\n");
     }

    /**
     * @return the to string withe the name of the ordered the foods ordered
     * and the total for the order
     */
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append(getCostumerID());
        for (Food food : orderedFood) {
            sb.append(food.getFoodName());
            sb.append(food.getFoodPrice());
            sb.append(totalPrice);
            sb.append("I am in this for loop");
        }
        return sb.toString();
    }


}

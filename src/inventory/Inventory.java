package inventory;


import food.Food;
import food.FoodIngredient;

import java.io.*;
import java.util.ArrayList;

public class Inventory {

    private static Inventory uniqueInstance = null;
    private ArrayList<Ingredient> ingreds = new ArrayList<>();

    //constructor
    private Inventory() {
    }

    private final String ingredientFilePath = "inventory.csv";

    public static synchronized Inventory getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Inventory();
        return uniqueInstance;
    }

    /**
     * This will determine if a ingredient is in the larger list of ingredients, identified by name.
     * @param haystack - The list of all ingredients
     * @param needle - The string you are looking for, identifying the ingredient
     * @return - true if the ingredient is found, false if not.
     */
    public static boolean ingredientExists(ArrayList<Ingredient> haystack, String needle) {
        for (Ingredient item : haystack) {
            if (item.getItemName().equalsIgnoreCase(needle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This will return the Ingredient object that is in the larger list of ingredients, identified by name.
     * @param haystack - The list of all ingredients.
     * @param needle - The string you are looking for, identifying the ingredient.
     * @return - Returns the Ingredient instance that was found in the haystack.
     */
    public static Ingredient getIng(ArrayList<Ingredient> haystack, String needle) {
        for (Ingredient item : haystack) {
            if (item.getItemName().equalsIgnoreCase(needle)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Loads a inventory file to be added into the running database.
     * @return - the filename / filepath from the current working directory. (one directory above the src folder)
     */
    public ArrayList<Ingredient> getIngredients() {

        BufferedReader br2 = null;
        String line2;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        try {

            br2 = new BufferedReader(new FileReader(ingredientFilePath));

                while ((line2 = br2.readLine()) != null) {
                String[] elements = line2.split(",");
                String ingredientName = elements[0];

                if (ingredientExists(ingreds, ingredientName)) {
                    Ingredient ingredientToAdd2 = new Ingredient(ingredientName, Integer.parseInt(elements[1]),Double.parseDouble(elements[2]));
                    ingredients.add(ingredientToAdd2);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.  Skipped inventory loading.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ingredients;
    }


    /**
     * Writes to the file if any change made to the inventory
     * @param inventory arraylist of the inventory item
     */
    public void WriteIngredients(ArrayList<Ingredient> inventory)
    {

        //saves databaseItem list -> database.txt file (to implement)
        //overwrites file and reinserts all items (with amount updated)
        try
        {
            File file = new File(ingredientFilePath);
            FileWriter fileR = new FileWriter(file.getAbsoluteFile(),false);
            BufferedWriter bWriter = new BufferedWriter(fileR);
            PrintWriter writer = new PrintWriter(bWriter);

            for (Ingredient ingredient : inventory) {

                writer.println(ingredient.getItemName() + "," + ingredient.getItemQuantity() + "," +
                        ingredient.getItemPrice());
            }

            bWriter.close(); //closes writer
        }
        catch(IOException ignored){}
        {
        }
    }


    /**
     * Loads a food file to be added into the running database.
     * @return foods - the filename / filepath from the current working directory. (one directory above the src folder)
     */
    public ArrayList<Food> getFood() {
        ArrayList<Food> foods = new ArrayList<>();
        BufferedReader br = null;
        String line;
        try {

            String foodFilePath = "data.csv";
            br = new BufferedReader(new FileReader(foodFilePath));
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue; // Comment detection
                }
                String[] elements = line.split(",");
                ArrayList<FoodIngredient> foodRecipe = new ArrayList<>();

                for (int i = 2; i < elements.length; i++) {
                    String[] elemquant = elements[i].split(":");
                    Ingredient currentIngredient = new Ingredient(elemquant[0]);
                    if(!ingredientExists(ingreds, elements[0])) {
                        ingreds.add(currentIngredient);
                    }
                    FoodIngredient currentFoodIngredient = new FoodIngredient(getIng(ingreds, elemquant[0]), Integer.parseInt(elemquant[1]));
                    foodRecipe.add(currentFoodIngredient);
                }
                Food currentFood = new Food(foodRecipe, elements[0], Double.parseDouble(elements[1]));
                foods.add(currentFood);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.  Skipped foods loading.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return foods;
    }

}

import food.Food;
import food.FoodIngredient;
import food.FoodOrder;
import inventory.Ingredient;
import inventory.Inventory;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    static GregorianCalendar cal = new GregorianCalendar();

    /**
     * This function will print out the menu for the user to select from.
     * There are no arguments for this function.
     */
    private static void printMenu() {
        printHeader("Admin");
        System.out.println("Current Date: " + cal.get(GregorianCalendar.YEAR) + "/" + ((cal.get(GregorianCalendar.MONTH)) + 1) + "/" + cal.get(GregorianCalendar.DAY_OF_MONTH));
        System.out.println("1. Add and Remove Items to Inventory");
        System.out.println("2. Print Order Range");
        System.out.println("3. Current Inventory");
        System.out.println("4. Profit Loss Report");
        System.out.println("5. Total Expenses and Net Income");
        System.out.println("B. Back");
    }

    /**
     * This function will print out the menu for the user to select from.
     * There are no arguments for this function.
     */
    private static void printMenu1() {

        printHeader("Menu");
        System.out.println("Current Date: " + cal.get(GregorianCalendar.YEAR) + "/" + ((cal.get(GregorianCalendar.MONTH)) + 1) + "/" + cal.get(GregorianCalendar.DAY_OF_MONTH));
        System.out.println("1. Order Food");
        System.out.println("2. Administrator");
        System.out.println("q. Quit");
    }

    /**
     * This function wll print out a header for a given text string.
     *
     * @param text - A string to place within the header flag.
     */
    public static void printHeader(String text) {
        int count = text.length();
        String dash = "-";
        for (int i = 0; i < count - 1; i++) {
            dash = dash.concat("-");
        }
        System.out.println("+" + dash + "+");
        System.out.println("+" + text + "+");
        System.out.println("+" + dash + "+");
    }

    /**
     * Determines if a string is a number
     *
     * @param str - A string to be determined to be a number or not.
     * @return - True if the string is parsable into a number. False if not a string parsable into a number.
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }


    /**
     * persist data so that when the app is shut down, the current state of all data is saved.
     * @param register - object of the Pos class
     */
    public static void save(POS register)
    {
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("register.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(register);

            out.close();
            file.close();

            System.out.println("Register has been saved");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        ArrayList<Ingredient> inventory = Inventory.getInstance().getIngredients();
        POS connection = new POS();
        ArrayList<Food> foodList = Inventory.getInstance().getFood();
        FoodOrder foodOrder = new FoodOrder();
        POS register = new POS();
        POS pos = new POS();

//    // Deserialization
//        try {
//        // Reading the object from a file
//        FileInputStream file = new FileInputStream("register.txt");
//        ObjectInputStream in = new ObjectInputStream(file);
//
//        // Method for deserialization of object
//        register = (POS) in.readObject();
//
//        in.close();
//        file.close();
//    }
//        catch (IOException ex) {
//        System.out.println("IOException is caught");
//        System.exit(0);
//    }
//        catch (ClassNotFoundException ex) {
//        System.out.println("ClassNotFoundException is caught");
//        System.exit(0);
//    }


        String version = "1.10";
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.format("+                       Restaurant Management                            v%s +\n", version);
        System.out.println("+       Authors:            Subash Katel                                       +");
        System.out.println("+------------------------------------------------------------------------------+");
        printMenu1();

        System.out.println("\nSelect a menu option: ");
        int count;
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!("q".equalsIgnoreCase(input))) {
            while (!isNumeric(input)) {
                input = s.nextLine(); // Grab any stay lines.
            }
            switch (Integer.parseInt(input)) {
                case 1 -> {
                    printHeader("Order Food");
                    count = 0;
                    for (Food food : foodList) {
                        System.out.println(count + ") " + food.getFoodName() + "  " + food.getFoodPrice());
                        count++;
                    }
                    System.out.println("Please Enter the Name for the Order");
                    input = s.nextLine();
                    foodOrder.setCostumerID(input);
                    do {
                        System.out.println("Please select the food you would like to order:");
                        input = s.nextLine();
                        while ((input.length() >= 1) && (Integer.parseInt(input) > foodList.size())) {
                            System.out.println("Invalid option. Try again:");
                            input = s.nextLine();
                        }

                        int getFood = Integer.parseInt(input);
                        System.out.println(connection.orderFood(foodList.get(getFood).getFoodName()));
                        boolean result = connection.orderFood(foodList.get(getFood).getFoodName());

                        if (result) {
                            System.out.println(foodList.get(getFood).getFoodName() + " ordered successfully.");

                        } else {
                            System.out.println((foodList.get(getFood).getFoodName() + " order failed. System does not have enough ingredients to fulfill the order."));
                            System.out.println("A " + foodList.get(getFood).getFoodName() + "' requires the following ingredients: ");
                            Food curFood = foodList.get(getFood);
                            for (FoodIngredient item : curFood.getIngredients()) {

                                System.out.format("Item: %s, Quantity: %d\n", item.getIngredient().getItemName(), item.getQuantity());
                            }
                        }
                        System.out.println("Would you like to order anything else");
                        input = s.nextLine();
                        while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
                            System.out.println("Invalid input. Try again.:");
                            input = s.nextLine();
                        }

                    } while (input.equalsIgnoreCase("Y"));
                    System.out.println(foodOrder.toString());
                }
                case 2 -> {
                    printMenu();
                    input = s.nextLine();
                    while (!("B".equalsIgnoreCase(input))) {
                        while (!isNumeric(input)) {
                            input = s.nextLine(); // Grab any stay lines.
                        }
                        switch (Integer.parseInt(input)) {
                            case 1 -> {
                                System.out.println("Would you like to add or remove item from inventory");
                                System.out.println("1. Add" + "\n2.Remove");
                                input = s.nextLine();

                                switch (Integer.parseInt(input)) {
                                    case 1 -> {
                                        printHeader("Add Items to Inventory Quantity");
                                        count = 0;
                                        for (Ingredient item : inventory) {
                                            System.out.println(count + ") " + item.getItemName());
                                            count++;
                                        }

                                        System.out.println("Please select the ingredient you would like to add quantity to:");
                                        int ingredientToAdd = s.nextInt();
                                        while (ingredientToAdd >= inventory.size() || ingredientToAdd < 0) {
                                            System.out.println("Invalid option, try again:");
                                            ingredientToAdd = s.nextInt();
                                        }
                                        System.out.println("How many would you like to add?:");
                                        int quantityToAdd = s.nextInt();
                                        while (quantityToAdd < 0) {
                                            System.out.println("Invalid quantity, try again:");
                                            quantityToAdd = s.nextInt();
                                        }
                                        input = s.nextLine();

                                        var newQuantity = quantityToAdd + inventory.get(ingredientToAdd).getItemQuantity();


                                        Ingredient ingredientStaged = new Ingredient(inventory.get(ingredientToAdd).getItemName(), inventory.get(ingredientToAdd).getItemQuantity(), inventory.get(ingredientToAdd).getItemPrice());
                                        ingredientStaged.setItemQuantity(newQuantity);
                                        inventory.set(ingredientToAdd, ingredientStaged);
                                        Inventory.getInstance().WriteIngredients(inventory);
                                        System.out.println(quantityToAdd + " " + inventory.get(ingredientToAdd).getItemName() + " have been added to the inventory!");
                                    }
                                    case 2 -> {
                                        printHeader("Remove Items to Inventory Quantity");
                                        count = 0;
                                        for (Ingredient item : inventory) {
                                            System.out.println(count + ") " + item.getItemName());
                                            count++;
                                        }

                                        System.out.println("Please select the ingredient you would like to remove quantity to:");
                                        int ingredientToAdd = s.nextInt();
                                        while (ingredientToAdd >= inventory.size() || ingredientToAdd < 0) {
                                            System.out.println("Invalid option, try again:");
                                            ingredientToAdd = s.nextInt();
                                        }
                                        System.out.println("How many would you like to remove?:");
                                        int quantityRemoved = s.nextInt();
                                        while (quantityRemoved < 0) {
                                            System.out.println("Invalid quantity, try again:");
                                            quantityRemoved = s.nextInt();
                                        }
                                        input = s.nextLine();

                                        var newQuantity = inventory.get(ingredientToAdd).getItemQuantity() - quantityRemoved;
                                        Ingredient ingredientStaged = new Ingredient(inventory.get(ingredientToAdd).getItemName(), inventory.get(ingredientToAdd).getItemQuantity(), inventory.get(ingredientToAdd).getItemPrice());
                                        ingredientStaged.setItemQuantity(newQuantity);
                                        inventory.set(ingredientToAdd, ingredientStaged);
                                        Inventory.getInstance().WriteIngredients(inventory);
                                        System.out.println(quantityRemoved + " " + inventory.get(ingredientToAdd).getItemName() + " have been removed to the inventory!");

                                    }
                                }
                            }
                            case 2 -> {
                                printHeader("Print Order Range");
//                                foodOrder.incomeReport(String);
                            }
                            case 3 -> {
                                printHeader("Current Inventory Listing");

                                System.out.format("%15s | %8s\n---------------------------\n", "Ingredient", "Quantity");
                                for (int i = 0; i < inventory.size(); i++) {
                                    System.out.format("%15s | %7d\n", inventory.get(i).getItemName(), inventory.get(i).getItemQuantity());
                                    System.out.println(inventory.get(i).getItemPrice());
                                }
                                System.out.println("The total for all the inventory item is " + pos.getTotalIngredientCost());
                            }
                            case 4 -> {
                                printHeader("Profit Loss Report");

                            }
                            case 5 -> {
                                printHeader("Total Expenses and Net Income");

                            }
                            default -> System.out.println("***Incorrect input, try again.***");
                        }
                        printMenu();
                        input = s.nextLine();
                    }
                }
                default -> System.out.println("***Incorrect input, try again.***");
            }
            printMenu1();
            System.out.println("\nSelect a menu option: ");
            input = s.nextLine();
        }
        save(register);
    }

}







import java.util.*;
// MealPlan interface and implementations
interface MealPlan {
    String getPlanName();
    void displayMeals();
}

class VegetarianMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Vegetarian";
    }

    @Override
    public void displayMeals() {
        System.out.println("Meals: Vegetable Stir Fry, Lentil Soup, Tofu Salad");
    }
}

class KetoMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Keto";
    }

    @Override
    public void displayMeals() {
        System.out.println("Meals: Avocado Salad, Grilled Salmon, Cheese Omelet");
    }
}

// Generic meal class
class Meal<T extends MealPlan> {
    private T mealPlan;
    private int calories;

    public Meal(T mealPlan, int calories) {
        this.mealPlan = mealPlan;
        this.calories = calories;
    }

    public void displayPlan() {
        System.out.println("Meal Plan: " + mealPlan.getPlanName() + ", Calories: " + calories);
        mealPlan.displayMeals();
    }
}

// Meal generator with validation
class MealGenerator {
    // Generic method to validate and generate meal plan
    public static <T extends MealPlan> Meal<T> generateMealPlan(T mealPlan, int calories) {
        if (calories < 1200 || calories > 3000) {
            throw new IllegalArgumentException("Invalid calorie range");
        }
        return new Meal<>(mealPlan, calories);
    }
}

// Usage example
public class MealPlanSystem {
    public static void main(String[] args) {
        try {
            Meal<VegetarianMeal> vegMeal = MealGenerator.generateMealPlan(new VegetarianMeal(), 1800);
            Meal<KetoMeal> ketoMeal = MealGenerator.generateMealPlan(new KetoMeal(), 2000);
            
            vegMeal.displayPlan();
            System.out.println("-----");
            ketoMeal.displayPlan();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
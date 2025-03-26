// Category interfaces
import java.util.*;
interface BookCategory {}
interface ClothingCategory {}
interface GadgetCategory {}

// Product base class with bounded type parameter
class Product<T> {
    private String name;
    private double price;
    private T category;

    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {
        System.out.println(name + " - $" + price + " (" + category.getClass().getSimpleName() + ")");
    }
}

// Catalog class
class ProductCatalog {
    private List<Product<?>> products = new ArrayList<>();

    // Generic method to add any product
    public <T> void addProduct(Product<T> product) {
        products.add(product);
    }

    // Generic method to apply discount with bounded type parameter
    public <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double newPrice = product.getPrice() * (1 - percentage / 100);
        product.setPrice(newPrice);
    }

    public void displayAll() {
        for (Product<?> product : products) {
            product.display();
        }
    }
}

// Usage example
public class Marketplace {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        
        // Create category instances
        BookCategory bookCat = new BookCategory() {};
        ClothingCategory clothingCat = new ClothingCategory() {};
        
        // Add products
        catalog.addProduct(new Product<>("Java Programming", 49.99, bookCat));
        catalog.addProduct(new Product<>("T-Shirt", 19.99, clothingCat));
        
        // Apply discount
        Product<BookCategory> book = new Product<>("Python Guide", 39.99, bookCat);
        catalog.applyDiscount(book, 10); // 10% discount
        
        catalog.addProduct(book);
        catalog.displayAll();
    }
}
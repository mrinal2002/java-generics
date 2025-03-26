// Abstract base class for all warehouse items
abstract class WarehouseItem {
    protected String name;
    protected int quantity;

    public WarehouseItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public abstract void displayDetails();
}

// Concrete item classes
class Electronics extends WarehouseItem {
    private String voltage;

    public Electronics(String name, int quantity, String voltage) {
        super(name, quantity);
        this.voltage = voltage;
    }

    @Override
    public void displayDetails() {
        System.out.println("Electronics: " + name + ", Qty: " + quantity + ", Voltage: " + voltage);
    }
}

class Groceries extends WarehouseItem {
    private String expiryDate;

    public Groceries(String name, int quantity, String expiryDate) {
        super(name, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Groceries: " + name + ", Qty: " + quantity + ", Expires: " + expiryDate);
    }
}

// Generic storage class with bounded type parameter
class Storage<T extends WarehouseItem> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public T getItem(int index) {
        return items.get(index);
    }

    // Wildcard method to display all items
    public static void displayAllItems(List<? extends WarehouseItem> items) {
        for (WarehouseItem item : items) {
            item.displayDetails();
        }
    }
}

// Usage example
public class WarehouseSystem {
    public static void main(String[] args) {
        Storage<Electronics> electronicsStorage = new Storage<>();
        electronicsStorage.addItem(new Electronics("Laptop", 10, "220V"));
        
        Storage<Groceries> groceriesStorage = new Storage<>();
        groceriesStorage.addItem(new Groceries("Apples", 50, "2023-12-31"));
        
        List<WarehouseItem> allItems = new ArrayList<>();
        allItems.addAll(electronicsStorage.items);
        allItems.addAll(groceriesStorage.items);
        
        Storage.displayAllItems(allItems);
    }
}
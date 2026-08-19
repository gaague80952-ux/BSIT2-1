public class Truck extends Vehicle {

    // Bonus: field only trucks have
    private double loadCapacity; // in tons

    public Truck(String brand, int year, double loadCapacity) {
        super(brand, year);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayInfo() {
        System.out.println("Truck: " + brand + " (" + year + ") - " + loadCapacity + " tons capacity");
    }
}
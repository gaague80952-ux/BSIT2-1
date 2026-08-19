public class Motorcycle extends Vehicle {

    // TODO 4: Add a field only motorcycles have
    private boolean hasSidecar;

    // TODO 5: Create the constructor
    public Motorcycle(String brand, int year, boolean hasSidecar) {
        super(brand, year);
        this.hasSidecar = hasSidecar;
    }

    // TODO 6: Override displayInfo()
    @Override
    public void displayInfo() {
        String sidecarText = hasSidecar ? "with sidecar" : "no sidecar";
        System.out.println("Motorcycle: " + brand + " (" + year + ") - " + sidecarText);
    }
}
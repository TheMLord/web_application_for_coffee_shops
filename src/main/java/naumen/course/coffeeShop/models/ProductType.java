package naumen.course.coffeeShop.models;

public enum ProductType {
    CHICKEN_SANDWICH(180), MACAROON(90), CAESAR_SALAD(220);

    private final int cost;

    ProductType(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }
}

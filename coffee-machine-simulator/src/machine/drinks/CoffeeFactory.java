package machine.drinks;

public abstract class CoffeeFactory {

    private static final String ESPRESSO = "1";
    private static final String LATTE = "2";
    private static final String CAPPUCCINO = "3";

    public static Coffee makeCoffee(String input) {
        return switch (input) {
            case ESPRESSO -> new Espresso();
            case LATTE -> new Latte();
            case CAPPUCCINO -> new Cappuccino();
            default -> null;
        };
    }
}

package machine.drinks;

class Cappuccino implements Coffee {

    private static final int WATER_PR_CUP = 200; // ml.
    private static final int MILK_PR_CUP = 100; // ml.
    private static final int GRAMS_BEANS_PR_CUP = 12;
    private static final int PRICE = 6;

    @Override
    public int getWaterPrServing() {
        return WATER_PR_CUP;
    }

    @Override
    public int getMilkPrServing() {
        return MILK_PR_CUP;
    }

    @Override
    public int getBeansPrServing() {
        return GRAMS_BEANS_PR_CUP;
    }

    @Override
    public int getPrice() {
        return PRICE;
    }
}

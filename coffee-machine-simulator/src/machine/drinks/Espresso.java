package machine.drinks;

class Espresso implements Coffee {

    private static final int WATER_PR_CUP = 250; // ml.
    private static final int MILK_PR_CUP = 0; // ml.
    private static final int GRAMS_BEANS_PR_CUP = 16; // grams.
    private static final int PRICE = 4;

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

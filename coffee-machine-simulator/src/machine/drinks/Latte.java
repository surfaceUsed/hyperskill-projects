package machine.drinks;

class Latte implements Coffee {

    private static final int WATER_PR_CUP = 350; // ml.
    private static final int MILK_PR_CUP = 75; // ml.
    private static final int GRAMS_BEANS_PR_CUP = 20;
    private static final int PRICE = 7;

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

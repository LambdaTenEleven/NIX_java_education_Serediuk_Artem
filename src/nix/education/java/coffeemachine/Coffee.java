package nix.education.java.coffeemachine;

public enum Coffee {
    ESPRESSO (4, 250, 0, 16),
    LATTE(7, 350, 75, 20),
    CAPPUCCINO(6, 200, 100, 12);

    public final int money;
    public final int water;
    public final int milk;
    public final int beans;

    Coffee(int money, int water, int milk, int beans) {
        this.money = money;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
    }
}

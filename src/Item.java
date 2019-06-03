public enum Item {
    MARS(25, "Mars"),
    TWIX(35, "Twix"),
    BOUNTY(45, "Bounty");

    private int price;
    private String name;

    Item(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

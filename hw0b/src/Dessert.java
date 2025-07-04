public class Dessert {
    int flavor;
    int price;
    static int numDesserts = 0;

    public Dessert(int flavor1, int price1) {
        flavor = flavor1;
        price = price1;
        numDesserts++;
    }

    public void printDessert() {
        System.out.printf("%d %d %d", flavor, price, numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}

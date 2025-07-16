public class BankAccount {
    private int card1Balance;
    private int card2Balance;

    public void changeCard(int amount) {
        synchronized (this) {
            card1Balance += amount;
            card2Balance -= amount;
        }
    }

}

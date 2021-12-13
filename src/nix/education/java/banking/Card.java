package nix.education.java.banking;

import java.util.Random;

public class Card {
    private final int PIN_SIZE = 4;

    private String cardNumber;
    private String PIN;
    private int balance;
    public Card(String number, String PIN, int balance) {
        this.cardNumber = number;
        this.PIN = PIN;
        this.balance = balance;
    }
    public Card(String IIN, int size) {
        var number = IIN + generateStringNumber(size);
        this.cardNumber = number + luhnGenerate(number);
        this.PIN = generateStringNumber(PIN_SIZE);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        if(balance < 0) this.balance = 0;
        else this.balance = balance;
    }

    public String getPIN() {
        return PIN;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Card)) {
            return false;
        }
        // typecast o to Complex so that we can compare data members
        Card c = (Card) o;
        // Compare the data members and return accordingly
        return ((Card) o).cardNumber.equals(this.cardNumber);
    }

    public static String generateStringNumber(int size) {
        int max = (int) Math.pow(10, size); // bound is exclusive
        Random random = new Random();
        int res = random.nextInt(max);
        return String.format("%0" + size + "d", res);
    }
    public static boolean luhnAlgorithm(String ccNumber)
    {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
    private static int luhnGenerate(String cardNumber) {
        for(int i = 0; i < 10; i++) {
            if(luhnAlgorithm(cardNumber + i)) {
                return i;
            }
        }
        return -1;
    }
}

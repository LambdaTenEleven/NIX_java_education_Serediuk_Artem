package nix.education.java.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDB {
    static Connection con = null;
    public static void connect() {
        String url = "jdbc:sqlite:card.sqlite";
        try {
            con = DriverManager.getConnection(url);
            //System.out.println("Success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void disconnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addCard(Card card) {
        connect();
        String query = "INSERT INTO card(number, pin, balance) VALUES ('" + card.getCardNumber() + "', '" +
                card.getPIN() + "', " + card.getBalance() + ");";
        try {
            Statement statement = con.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
    }
    public static Card getCard(String cardNumber) {
        connect();
        String query = "SELECT * FROM card WHERE number = " + cardNumber;
        try {
            Statement statement = con.createStatement();
            var result = statement.executeQuery(query);
            if(result.next()) {
                var number = result.getString("number");
                var pin = result.getString("pin");
                var balance = result.getInt("balance");
                return new Card(number, pin, balance);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return null;
    }

    public static void updateCard(Card card) {
        connect();
        String query = "UPDATE card SET balance = " + card.getBalance() + " WHERE number = " + card.getCardNumber();
        try {
            Statement statement = con.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }

    }

    public static void deleteCard(Card card) {
        connect();
        String query = "DELETE FROM card WHERE number = " + card.getCardNumber();
        try {
            Statement statement = con.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
    }
}

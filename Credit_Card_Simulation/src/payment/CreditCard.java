package payment;

import client.Person;
import goods.Item;
import java.util.ArrayList;
import java.util.Date;

public class CreditCard {
    private Person cardHolder;
    private String type;
    private String cardNumber;
    private double creditLimit;
    private double currentBalance;
    private double nextPaymentAmount;
    private ArrayList<Item> transactions;
    private ArrayList<Date> transactionsTimeStamps;

    public CreditCard(Person cardHolder, String type, double creditLimit) {
        this.cardHolder = cardHolder;
        this.type = type;
        this.creditLimit = creditLimit;

        currentBalance = 0.0;

        transactions = new ArrayList<Item>();
        transactionsTimeStamps = new ArrayList<Date>();

    }

    public Person getCardHolder() {
        return cardHolder;
    }

    public String getType() {
        return type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getNextPaymentAmount() {
        return nextPaymentAmount;
    }

    public void makeCharge(Item item){
        double availableCredit = creditLimit - currentBalance;

        if(item.getPrice() <= availableCredit){ //check if item price is < than available credit
            transactions.add(item);
            Date date = new Date();
            transactionsTimeStamps.add(date);
            currentBalance += item.getPrice();

            System.out.println("");
            System.out.println("Charging: " + item.getName());
            System.out.println("Transaction completed successfully");
            System.out.println("Please remove your " + type );
            System.out.println("");
        }else{ //credit card does not have enough credit
            System.out.println("");
            System.out.println("Charging: " + item.getName());
            System.out.println("Transaction was not successful");
            System.out.println("Credit Limit Issue");
            System.out.println("Please remove your " + type );
            System.out.println("");
        }//end if

    }

    public void transactionsReport(){
        double availableCredit = creditLimit - currentBalance;

        System.out.println("");
        System.out.println("============================================================================");
        System.out.println(type + " Transaction Report" );
        System.out.println("============================================================================");
        System.out.printf("%-20s $%-10.2f\n", "Credit Limit:", creditLimit);
        System.out.printf("%-20s $%-10.2f\n", "Available Credit:", availableCredit);
        System.out.printf("%-20s $%-10.2f\n", "Current Balance:", currentBalance);
        System.out.println("------------------------------------------------------------------------");

        double totalCharges = 0.0;

        for(int i = 0; i < transactions.size(); i++){
            Item item = transactions.get(i);
            Date date = transactionsTimeStamps.get(i);
            System.out.printf("%-20s %-10s $%-10.2f %-10s\n",item.getName(),
                    item.getCatagory(), item.getPrice(), date.toString() );
            totalCharges += item.getPrice();
        }//end for

        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%-15s Total Charges:  $%-10.2f\n","", totalCharges);

    }

}//end CreditCard

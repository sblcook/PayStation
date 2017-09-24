package paystation.domain;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main (String args[]){

        PayStationImpl payStation = new PayStationImpl();
        Scanner keyboard = new Scanner(System.in);
        boolean continueBool = true;

        while(continueBool){
            System.out.println("Welcome to PayStation! Please select an option to continue");
            System.out.println("1) Deposit coins");
            System.out.println("2) Display");
            System.out.println("3) Buy ticket");
            System.out.println("4) Cancel");
            System.out.println("5) Change rate strategy");
            System.out.println();
            System.out.print("Your choice: ");

            int actionInput = keyboard.nextInt();

            switch (actionInput){
                case 1:
                    System.out.println("We accept 5, 10, and 25 cent coins.");
                    while(true) {
                        System.out.print("Please enter the amount or 0 to finish inserting coins: ");
                        int coinValue = keyboard.nextInt();
                        if(coinValue == 0)
                            break;
                        try {
                            payStation.addPayment(coinValue);
                        } catch (IllegalCoinException e) {
                            System.out.println("Error:invalid coin. Exiting to main menu.");
                            System.out.println();
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Your time (in minutes): " + payStation.readDisplay());
                    System.out.println();
                    break;
                case 3:
                    Receipt receipt = payStation.buy();

                    System.out.println("-----------------------------------");
                    System.out.println("Thank you for using PayStation!");
                    System.out.println("Your parking receipt:");
                    System.out.println("Time bought: " + receipt.value() + " minutes");
                    System.out.println("Have a nice day!");
                    System.out.println("-----------------------------------");

                    continueBool = false;
                    break;
                case 4:
                    Map<Integer, Integer> returnCoinValues = payStation.cancel();
                    int nickelAmount;
                    int dimeAmount;
                    int quarterAmount;

                    try{
                        nickelAmount = returnCoinValues.get(5);
                    } catch (NullPointerException e){
                        nickelAmount = 0; //no nickels in map
                    }
                    try{
                        dimeAmount = returnCoinValues.get(10);
                    } catch(NullPointerException e){
                        dimeAmount = 0; //no dimes in map
                    }
                    try{
                        quarterAmount = returnCoinValues.get(25);
                    } catch(NullPointerException e){
                        quarterAmount = 0; //no quarters in map
                    }

                    System.out.println("Amount of nickels returned: " + nickelAmount);
                    System.out.println("Amount of dimes returned: " + dimeAmount);
                    System.out.println("Amount of quarters returned: " + quarterAmount);
                    int totalCoinValue = (nickelAmount*5) + (dimeAmount*10) + (quarterAmount*25);
                    System.out.println("Total value of coins returned : $" +totalCoinValue / 100.0);

                    System.out.println("Have a nice day!");
                    continueBool = false;
                    break;

            }
        }
    }
}

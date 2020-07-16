import java.text.DecimalFormat;
import java.util.Scanner;

class TransitCalculator {
    int numberOfDaysUsingTransitSystem;
    int numberOfIndividualRides;

    private final static DecimalFormat df = new DecimalFormat("0.00");

    double payPerRide = 2.75;
    double unlimitedRides7Days = 33.00;
    double unlimitedRides30Days = 127.00;

    public TransitCalculator(int numberOfDaysUsingTransitSystem, int numberOfIndividualRides) {
        this.numberOfDaysUsingTransitSystem = numberOfDaysUsingTransitSystem;
        this.numberOfIndividualRides = numberOfIndividualRides;
    }

    public double unlimited7Price() {
        double totalPricePayPerRide = 00.00;

        if (numberOfDaysUsingTransitSystem <= 7) {
            totalPricePayPerRide = unlimitedRides7Days / numberOfIndividualRides;
        } else if (numberOfDaysUsingTransitSystem <= 14) {
            totalPricePayPerRide = (unlimitedRides7Days * 2) / numberOfIndividualRides;
        } else if (numberOfDaysUsingTransitSystem <= 21) {
            totalPricePayPerRide = (unlimitedRides7Days * 3) / numberOfIndividualRides;
        } else if (numberOfDaysUsingTransitSystem <= 28) {
            totalPricePayPerRide = (unlimitedRides7Days * 4) / numberOfIndividualRides;
        } else if (numberOfDaysUsingTransitSystem <= 30) {
            totalPricePayPerRide = (unlimitedRides7Days * 5) / numberOfIndividualRides;
        }
        return totalPricePayPerRide;
    }

    public double[] getRidePrices() {
        double[] listGetRidePrices = new double[3];

        // PricePayPerRide
        listGetRidePrices[0] = payPerRide;
        // UnlimitedRides7Days
        listGetRidePrices[1] = unlimited7Price();
        // UnlimitedRides30Days
        listGetRidePrices[2] = unlimitedRides30Days / numberOfIndividualRides;

        return listGetRidePrices;
    }

    public String getBestFare() {
        double[] prices = getRidePrices();
        double lowestPrice = prices[0];
        int amountOfTickets = 0;
        String ticketName = "";


        for (int index = 0; index < prices.length; index++) {
            if (prices[index] < lowestPrice) {
                lowestPrice = prices[index];
            }
        }
        if (lowestPrice == prices[0]) {
            ticketName = "Pay Per Day-ticket";
            amountOfTickets = numberOfIndividualRides;
        } else if (lowestPrice == prices[1]) {
            ticketName = "7 Days Unlimited Rides-ticket";
                if (numberOfDaysUsingTransitSystem <= 7) {
                    amountOfTickets = 1;
                } else if (numberOfDaysUsingTransitSystem <= 14) {
                    amountOfTickets = 2;
                } else if (numberOfDaysUsingTransitSystem <= 21) {
                    amountOfTickets = 3;
                }
        } else if (lowestPrice == prices[2]) {
            ticketName = "30 Days Unlimited Rides-ticket";
            amountOfTickets = 1;
        }
        return "You should get " + amountOfTickets + "x the " + ticketName + " of $ " + df.format(lowestPrice) + " per ride.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransitCalculator transitCalculator = new TransitCalculator(scanner.nextInt(), scanner.nextInt());
        if (transitCalculator.numberOfDaysUsingTransitSystem > 30) {
            System.out.println("This is not possible. Please select amount of days with a max of 30 days.");
        } else {
            String result = transitCalculator.getBestFare();
            System.out.println(result);
        }
    }
}
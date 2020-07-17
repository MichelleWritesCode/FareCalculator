import java.text.DecimalFormat;
import java.util.Scanner;

class TransitCalculator {
    int numberOfDaysUsingTransitSystem, numberOfIndividualRides;
    double payPerRide = 2.75, unlimitedRides7Days = 33.00, unlimitedRides30Days = 127.00, amountOfTickets;

    private final static DecimalFormat df = new DecimalFormat("0.00");

    public TransitCalculator(int numberOfDaysUsingTransitSystem, int numberOfIndividualRides, int age, boolean disabilities) {
        this.numberOfDaysUsingTransitSystem = numberOfDaysUsingTransitSystem;
        this.numberOfIndividualRides = numberOfIndividualRides;

        if (age >= 65 || disabilities) {
            payPerRide = 1.35;
            unlimitedRides7Days = 16.50;
            unlimitedRides30Days = 63.50;
        }
    }

    public double unlimited7PricePerRide() {
        amountOfTickets = Math.ceil(numberOfDaysUsingTransitSystem / 7.0);
        return (unlimitedRides7Days * amountOfTickets) / numberOfIndividualRides;
    }

    public double[] getRidePrices() {
        double[] listGetRidePrices = new double[3];

        listGetRidePrices[0] = payPerRide;
        listGetRidePrices[1] = unlimited7PricePerRide();
        listGetRidePrices[2] = unlimitedRides30Days / numberOfIndividualRides;

        return listGetRidePrices;
    }

    public String getBestFare() {
        double[] prices = getRidePrices();
        double lowestPrice = prices[0];
        String ticketName = "";

        for (double price : prices) {
            if (price <= lowestPrice) {
                lowestPrice = price;
            }
        }

        if (lowestPrice == prices[1]) {
            ticketName = "'7 Days Unlimited Rides-ticket'";
        } else if (lowestPrice == prices[2]) {
            ticketName = "'30 Days Unlimited Rides-ticket'";
            amountOfTickets = 1;
        } else if (lowestPrice == prices[0]) {
            ticketName = "'Pay Per Ride-ticket'";
            amountOfTickets = numberOfIndividualRides;
        }
        return "You should get " + amountOfTickets + "x the " + ticketName + " with a price of $ " + df.format(lowestPrice) + " per ride.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfDaysUsingTransitSystem = scanner.nextInt();
        int numberOfIndividualRides = scanner.nextInt();
        int age = scanner.nextInt();
        boolean disabilities = scanner.next().equalsIgnoreCase("yes");

        TransitCalculator transitCalculator = new TransitCalculator(numberOfDaysUsingTransitSystem, numberOfIndividualRides, age, disabilities);

        if (transitCalculator.numberOfDaysUsingTransitSystem > 30) {
            System.out.println("This is not possible. Please select amount of days with a max of 30 days.");
        } else {
            System.out.println(transitCalculator.getBestFare());
        }
    }
}

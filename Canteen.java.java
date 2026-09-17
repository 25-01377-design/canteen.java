import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Canteen menu
        String[] items = {
            "Fried Chicken",
            "Spaghetti",
            "Siomai",
            "French Fries",
            "Iced Tea"
        };

        double[] prices = {
            95.00,
            85.00,
            50.00,
            60.00,
            40.00
        };

        int totalItems = 0;
        double totalBeforeDiscount = 0.00;
        double totalDiscount = 0.00;

        char orderAgain = 'Y';

        System.out.println("=================================");
        System.out.println("       SCHOOL CANTEEN SYSTEM");
        System.out.println("=================================");

        while (orderAgain == 'Y') {

            // Display menu
            System.out.println("\n========== MENU ==========");

            for (int i = 0; i < items.length; i++) {
                System.out.printf("%d. %-15s - ₱%.2f%n",
                        i + 1, items[i], prices[i]);
            }

            // Get item number
            System.out.print("\nEnter item number (1-5): ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                input.next();
                continue;
            }

            int itemNumber = input.nextInt();

            // Validate item number
            if (itemNumber < 1 || itemNumber > items.length) {
                System.out.println("Invalid item number! Please choose 1-5.");
                continue;
            }

            // Get quantity
            System.out.print("Enter quantity (1-10): ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                input.next();
                continue;
            }

            int quantity = input.nextInt();

            // Validate quantity
            if (quantity < 1 || quantity > 10) {
                System.out.println("Invalid quantity! Please enter 1-10.");
                continue;
            }

            // Student status
            char student;

            while (true) {
                System.out.print("Are you a student? (Y/N): ");
                String studentInput = input.next().toUpperCase();

                if (studentInput.equals("Y") || studentInput.equals("N")) {
                    student = studentInput.charAt(0);
                    break;
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
            }

            // Calculate subtotal
            double subtotal = prices[itemNumber - 1] * quantity;

            double discount = 0.00;

            // Calculate discount
            if (student == 'Y' && subtotal >= 500) {
                discount = subtotal * 0.15;
            } 
            else if (student == 'Y') {
                discount = subtotal * 0.10;
            } 
            else if (subtotal >= 500) {
                discount = subtotal * 0.05;
            }

            double orderTotal = subtotal - discount;

            // Display order details
            System.out.println("\n========== ORDER DETAILS ==========");
            System.out.println("Item: " + items[itemNumber - 1]);
            System.out.println("Quantity: " + quantity);

            System.out.printf("Price: ₱%.2f%n",
                    prices[itemNumber - 1]);

            System.out.printf("Subtotal: ₱%.2f%n",
                    subtotal);

            System.out.printf("Discount: ₱%.2f%n",
                    discount);

            System.out.printf("Total: ₱%.2f%n",
                    orderTotal);

            // Update totals
            totalItems += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            // Ask for another order
            while (true) {
                System.out.print("\nDo you want to order another item? (Y/N): ");
                String answer = input.next().toUpperCase();

                if (answer.equals("Y") || answer.equals("N")) {
                    orderAgain = answer.charAt(0);
                    break;
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
            }
        }

        // Calculate final amount
        double finalAmount = totalBeforeDiscount - totalDiscount;

        // Order summary
        System.out.println("\n=================================");
        System.out.println("          ORDER SUMMARY");
        System.out.println("=================================");

        System.out.println("Total items: " + totalItems);

        System.out.printf("Total before discount: ₱%.2f%n",
                totalBeforeDiscount);

        System.out.printf("Total discount: ₱%.2f%n",
                totalDiscount);

        System.out.printf("Final amount: ₱%.2f%n",
                finalAmount);

        // Payment
        double payment;

        while (true) {
            System.out.print("\nEnter payment amount: ₱");

            if (!input.hasNextDouble()) {
                System.out.println("Invalid payment! Please enter a valid amount.");
                input.next();
                continue;
            }

            payment = input.nextDouble();

            if (payment < 0) {
                System.out.println("Payment cannot be negative.");
                continue;
            }

            break;
        }

        // Check payment
        if (payment >= finalAmount) {

            double change = payment - finalAmount;

            System.out.printf("Payment: ₱%.2f%n", payment);
            System.out.printf("Change: ₱%.2f%n", change);

            System.out.println("\nThank you for ordering!");
            System.out.println("Please come again!");

        } else {

            double lacking = finalAmount - payment;

            System.out.printf("Payment: ₱%.2f%n", payment);

            System.out.printf(
                    "Insufficient payment! You need ₱%.2f more.%n",
                    lacking
            );
        }

        input.close();
    }
}

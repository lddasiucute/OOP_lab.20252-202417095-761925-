package hust.soict.globalict.aims;

import java.util.Collections;
import java.util.Scanner;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.*;
import hust.soict.globalict.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Sample data
        store.addMedia(new DigitalVideoDisc("Lion King", "Animation", "Disney", 90, 20f));
        store.addMedia(new DigitalVideoDisc("Avengers", "Action", "Marvel", 120, 25f));
        store.addMedia(new Book("Java Basics", "Education", 15f));

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: seeCurrentCart(); break;
                case 0: System.out.println("Bye!"); break;
                default: System.out.println("Invalid!");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\nAIMS");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See cart");
        System.out.println("0. Exit");
    }

    public static void viewStore() {
        store.print();
        int choice;

        do {
            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    Media m = findInStore(title);
                    if (m != null) {
                        System.out.println(m);
                        mediaDetails(m);
                    } else System.out.println("Not found!");
                    break;

                case 2:
                    System.out.print("Enter title to add: ");
                    title = scanner.nextLine();
                    m = findInStore(title);
                    if (m != null) {
                        cart.addMedia(m);
                        System.out.println("Added!");
                    }
                    break;

                case 3:
                    System.out.print("Enter title to play: ");
                    title = scanner.nextLine();
                    m = findInStore(title);
                    if (m instanceof Playable) {
                        ((Playable) m).play();
                    } else System.out.println("Cannot play!");
                    break;

                case 4:
                    seeCurrentCart();
                    break;
            }
        } while (choice != 0);
    }

    private static Media findInStore(String title) {
        for (Media m : store.getItemsInStore())
            if (m.isMatch(title)) return m;
        return null;
    }

    private static void mediaDetails(Media m) {
        mediaDetailsMenu();
        int c = scanner.nextInt();
        scanner.nextLine();

        if (c == 1) cart.addMedia(m);
        else if (c == 2 && m instanceof Playable)
            ((Playable) m).play();
    }

    public static void updateStore() {
        System.out.println("1. Add");
        System.out.println("2. Remove");

        int c = scanner.nextInt();
        scanner.nextLine();

        if (c == 1) {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Category: ");
            String cat = scanner.nextLine();
            System.out.print("Cost: ");
            float cost = scanner.nextFloat();
            scanner.nextLine();

            store.addMedia(new Book(title, cat, cost));
            System.out.println("Added!");

        } else if (c == 2) {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            Media m = findInStore(title);

            if (m != null) {
                store.removeMedia(m);
                System.out.println("Removed!");
            }
        }
    }

    public static void seeCurrentCart() {
        cart.print();
        int choice;

        do {
            cartMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: filterCart(); break;
                case 2: sortCart(); break;
                case 3: removeFromCart(); break;
                case 4: playInCart(); break;
                case 5:
                    System.out.println("Order placed!");
                    cart = new Cart();
                    break;
            }
        } while (choice != 0);
    }

    private static void filterCart() {
        System.out.println("1. ID");
        System.out.println("2. Title");

        int c = scanner.nextInt();
        scanner.nextLine();

        if (c == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (Media m : cart.getItemsOrdered())
                if (m.getId() == id) System.out.println(m);

        } else {
            System.out.print("Title: ");
            String title = scanner.nextLine();

            for (Media m : cart.getItemsOrdered())
                if (m.isMatch(title)) System.out.println(m);
        }
    }

    private static void sortCart() {
        System.out.println("1. Title");
        System.out.println("2. Cost");

        int c = scanner.nextInt();
        scanner.nextLine();

        if (c == 1)
            Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_TITLE_COST);
        else
            Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_COST_TITLE);

        cart.print();
    }

    private static void removeFromCart() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        Media m = null;
        for (Media x : cart.getItemsOrdered())
            if (x.isMatch(title)) m = x;

        if (m != null) {
            cart.removeMedia(m);
            System.out.println("Removed!");
        }
    }

    private static void playInCart() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        for (Media m : cart.getItemsOrdered())
            if (m.isMatch(title) && m instanceof Playable)
                ((Playable) m).play();
    }

    public static void storeMenu() {
        System.out.println("\n1. Details");
        System.out.println("2. Add to cart");
        System.out.println("3. Play");
        System.out.println("4. View cart");
        System.out.println("0. Back");
    }

    public static void mediaDetailsMenu() {
        System.out.println("\n1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
    }

    public static void cartMenu() {
        System.out.println("\n1. Filter");
        System.out.println("2. Sort");
        System.out.println("3. Remove");
        System.out.println("4. Play");
        System.out.println("5. Order");
        System.out.println("0. Back");
    }
}
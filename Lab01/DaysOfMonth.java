import java.util.Scanner;

public class DaysOfMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = -1;
        int month = -1;

        while (true) {
            System.out.print("Enter a year (e.g., 1999): ");
            String yearStr = scanner.nextLine().trim();
            
            try {
                year = Integer.parseInt(yearStr);
                if (year >= 0) {
                    break;
                } else {
                    System.out.println("Invalid year. Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid digits only.");
            }
        }

        while (true) {
            System.out.print("Enter a month (full name, abbreviation, 3 letters, or number): ");
            String monthStr = scanner.nextLine().trim();
            month = getMonthNumber(monthStr);
            
            if (month != -1) {
                break;
            } else {
                System.out.println("Invalid month format. Please try again.");
            }
        }

        int days = getDaysInMonth(month, year);
        System.out.println("Number of days: " + days);
        
        scanner.close();
    }

    private static int getMonthNumber(String input) {
        input = input.toLowerCase();
        switch (input) {
            case "1": case "january": case "jan.": case "jan": return 1;
            case "2": case "february": case "feb.": case "feb": return 2;
            case "3": case "march": case "mar.": case "mar": return 3;
            case "4": case "april": case "apr.": case "apr": return 4;
            case "5": case "may": return 5;
            case "6": case "june": case "jun": return 6;
            case "7": case "july": case "jul": return 7;
            case "8": case "august": case "aug.": case "aug": return 8;
            case "9": case "september": case "sept.": case "sep": return 9;
            case "10": case "october": case "oct.": case "oct": return 10;
            case "11": case "november": case "nov.": case "nov": return 11;
            case "12": case "december": case "dec.": case "dec": return 12;
            default: return -1;
        }
    }

    private static int getDaysInMonth(int month, int year) {
        if (month == 2) {
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            return isLeapYear ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
}
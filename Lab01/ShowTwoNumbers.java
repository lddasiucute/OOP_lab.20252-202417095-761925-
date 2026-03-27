import javax.swing.JOptionPane;

public class ShowTwoNumbers {
    public static void main(String[] args) {
        String str1 = JOptionPane.showInputDialog("Enter first number:");
        String str2 = JOptionPane.showInputDialog("Enter second number");

        double num1 = Double.parseDouble(str1);
        double num2 = Double.parseDouble(str2);

        JOptionPane.showMessageDialog(null,
                "Sum: " + (num1 + num2));
        System.exit(0);
    }
}
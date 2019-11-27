import java.util.Scanner;

public class Main {
     public void mainmenu() {
         Scanner input = new Scanner(System.in);
         while (true) {
            printMainMenu();
            int choice = printMainMenu();
            switch (choice) {
                case 0: exit();
                case 1: foo();
                case 2: bar();
                default: print("Wrong choice");
            }
        }
    }

    private int printMainMenu() {
        int optionn = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Main Menu:");
        System.out.println("--------------");
        System.out.println("1.Add the numbers");
        System.out.println("2.Multiply the numbers");
        System.out.println("3.Quit");
        System.out.println("--------------");
        System.out.println("Enter your choice:");
        optionn = keyboard.nextInt();
        System.out.println("Hello World!");
        return optionn;
    }

    public static void main(String[] args)
    {

    }
}

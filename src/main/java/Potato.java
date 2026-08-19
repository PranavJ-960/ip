import java.util.Scanner;

public class Potato {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____   ___ _____  _  _____ ___  \n"
                + "|  _ \\ / _ \\_   _|/ \\|_   _/ _ \\ \n"
                + "| |_) | | | || | / _ \\ | || | | |\n"
                + "|  __/| |_| || |/ ___ \\| || |_| |\n"
                + "|_|    \\___/ |_|/_/   \\_\\_| \\___/\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Potato.");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }

        scanner.close();
    }
}
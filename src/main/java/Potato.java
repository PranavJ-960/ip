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
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            System.out.println(line);

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5).trim()) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                }
                System.out.println(line);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7).trim()) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].unmarkDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                }
                System.out.println(line);
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5).trim();
                Task t = new Todo(desc);
                tasks[taskCount] = t;
                taskCount++;
                printTaskAdded(t, taskCount, line);
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                Task t = new Deadline(parts[0].trim(), parts[1].trim());
                tasks[taskCount] = t;
                taskCount++;
                printTaskAdded(t, taskCount, line);
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ", 2);
                String desc = parts[0].trim();
                String[] timeParts = parts[1].split(" /to ", 2);
                Task t = new Event(desc, timeParts[0].trim(), timeParts[1].trim());
                tasks[taskCount] = t;
                taskCount++;
                printTaskAdded(t, taskCount, line);
            } else {
                Task t = new Task(input);
                tasks[taskCount] = t;
                taskCount++;
                printTaskAdded(t, taskCount, line);
            }
        }

        scanner.close();
    }

    private static void printTaskAdded(Task task, int count, String line) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(line);
    }
}
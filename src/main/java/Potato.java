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
            if (input.isEmpty()) {
                continue;
            }

            System.out.println(line);

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                } else if (input.startsWith("mark")) {
                    int index = parseIndex(input, "mark", taskCount);
                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                } else if (input.startsWith("unmark")) {
                    int index = parseIndex(input, "unmark", taskCount);
                    tasks[index].unmarkDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                } else if (input.startsWith("todo")) {
                    String desc = input.substring(4).trim();
                    if (desc.isEmpty()) {
                        throw new PotatoException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    tasks[taskCount] = t;
                    taskCount++;
                    printTaskAdded(t, taskCount);
                } else if (input.startsWith("deadline")) {
                    String body = input.substring(8).trim();
                    if (body.isEmpty()) {
                        throw new PotatoException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] parts = body.split(" /by ", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new PotatoException("OOPS!!! Please specify a deadline using '/by <date/time>'.");
                    }
                    Task t = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks[taskCount] = t;
                    taskCount++;
                    printTaskAdded(t, taskCount);
                } else if (input.startsWith("event")) {
                    String body = input.substring(5).trim();
                    if (body.isEmpty()) {
                        throw new PotatoException("OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] parts = body.split(" /from ", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new PotatoException("OOPS!!! Please specify event timing using '/from <start> /to <end>'.");
                    }
                    String[] timeParts = parts[1].split(" /to ", 2);
                    if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                        throw new PotatoException("OOPS!!! Please specify event end time using '/to <end>'.");
                    }
                    Task t = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
                    tasks[taskCount] = t;
                    taskCount++;
                    printTaskAdded(t, taskCount);
                } else {
                    throw new PotatoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (PotatoException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(line);
        }

        scanner.close();
    }

    private static int parseIndex(String input, String command, int taskCount) throws PotatoException {
        String arg = input.substring(command.length()).trim();
        if (arg.isEmpty()) {
            throw new PotatoException("OOPS!!! Please provide a task number to " + command + ".");
        }
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index < 0 || index >= taskCount) {
                throw new PotatoException("OOPS!!! Task number " + (index + 1) + " does not exist.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new PotatoException("OOPS!!! Please specify a valid integer task number.");
        }
    }

    private static void printTaskAdded(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
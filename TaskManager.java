import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private int id;
    private String name;
    private String description;
    private boolean isCompleted;

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCompleted = false;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", description=" + description + ", completed=" + isCompleted + "]";
    }
}

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void createTask(String name, String description) {
        Task task = new Task(nextId++, name, description);
        tasks.add(task);
        System.out.println("Task created: " + task);
    }

    public void readTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void updateTask(int id, String name, String description, boolean isCompleted) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setName(name);
                task.setDescription(description);
                task.setCompleted(isCompleted);
                System.out.println("Task updated: " + task);
                return;
            }
        }
        System.out.println("Task not found with ID: " + id);
    }

    public void deleteTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                System.out.println("Task deleted with ID: " + id);
                return;
            }
        }
        System.out.println("Task not found with ID: " + id);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        boolean running = true;

        while (running) {
            System.out.println("\nTask Manager:");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.createTask(name, description);
                    break;
                case 2:
                    taskManager.readTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new task name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new task description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Is the task completed? (true/false): ");
                    boolean isCompleted = scanner.nextBoolean();
                    taskManager.updateTask(updateId, newName, newDescription, isCompleted);
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    taskManager.deleteTask(deleteId);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting Task Manager...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
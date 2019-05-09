import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
    static ArrayList<String> toDoList = new ArrayList<>();

    public static final String LIST_COM = "LIST";
    public static final String ADD_COM = "ADD ";
    public static final String DELETE_COM = "DELETE ";
    public static final String EDIT_COM = "EDIT ";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String allCommands = "Возможные команды: \"" + ADD_COM + " *текст*\", \"" + ADD_COM + " *индекс* *текст*\", \"" + LIST_COM +
                "\", \"" + DELETE_COM + " *индекс*\", \"" + EDIT_COM + " *индекс* *новый текст*\"";
        System.out.println(allCommands);

        while (true) {
            String input = scanner.nextLine();
            String command = input;
            if (input.contains(" ")) {
                command = input.substring(0, input.indexOf(" ") + 1);
            }
            switch (command) {
                case LIST_COM:
                    list();
                    break;
                case ADD_COM:
                    add(input.substring(ADD_COM.length()));
                    break;
                case DELETE_COM:
                    delete(input.substring(DELETE_COM.length()));
                    break;
                case EDIT_COM:
                    edit(input.substring(EDIT_COM.length()));
                    break;
                default:
                    System.out.println("Команда написана некорректно. " + allCommands);
            }
        }
    }

    public static void list() {
        if (toDoList.isEmpty()) {
            System.out.println("Список дел пока что пуст.");
        }
        for (String task : toDoList) {
            System.out.println(toDoList.indexOf(task) + " - " + task);
        }
    }

    public static void add(String input) {
        int index = toDoList.size();
        String incorrectIndex = "";

        if (input.matches("\\d+\\s+.+")) {
            int parse = Integer.parseInt(input.substring(0, input.indexOf(" ")));
            input = input.substring(input.indexOf(" ") + 1);
            if (0 <= parse && parse < toDoList.size()) {
                index = parse;
            } else {
                incorrectIndex = "Некорректный индекс. ";
            }
        }
        toDoList.add(index, input);
        System.out.println(incorrectIndex + "Задание \"" + cutTaskForPrint(input) + "\" добавлено под индексом " + index);
    }

    public static void delete(String input) {
        if (toDoList.isEmpty()) {
            System.out.println("В списке нет дел, удалять нечего!");
            return;
        }
        if (input.matches("\\d+")) {
            int index = Integer.parseInt(input);
            if (0 <= index && index < toDoList.size()) {
                System.out.println("Задание \"" + cutTaskForPrint(toDoList.get(index)) + "\" удалено.");
                toDoList.remove(index);
            } else {
                System.out.println("Некорректный индекс.");
            }
        } else {
            System.out.println("Некорректно введена команда. Для удаления задания введите \"" + DELETE_COM + " *индекс*\". Например: \""
                    + DELETE_COM + " " + (int) (Math.random() * toDoList.size()) + "\"");
        }
    }

    public static void edit(String input) {
        if (toDoList.isEmpty()) {
            System.out.println("В списке нет дел, редактировать нечего!");
            return;
        }
        if (input.matches("\\d+\\s+.+")) {
            int index = Integer.parseInt(input.substring(0, input.indexOf(" ")));
            input = input.substring(input.indexOf(" ") + 1);
            if (0 <= index && index < toDoList.size()) {
                System.out.println("Задание \"" + cutTaskForPrint(toDoList.get(index)) + "\" заменено на \"" + cutTaskForPrint(input) + "\"");
                toDoList.set(index, input);
            } else {
                System.out.println("Некорректный индекс.");
            }
        } else {
            System.out.println("Некорректно введена команда. Для удаления задания введите \"" + EDIT_COM + " *индекс* *новый текст*\". Например: \""
                    + EDIT_COM + " " + (int) (Math.random() * toDoList.size()) + " составить список дел\"");
        }

    }

    public static String cutTaskForPrint(String task) {
        if (task.length() > 30) {
            return task.substring(0, 25) + "...";
        } else {
            return task;
        }
    }
}

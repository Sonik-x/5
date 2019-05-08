import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
    static ArrayList<String> toDoList = new ArrayList<>();

    public static final String LIST_COM = "LIST";
    public static final String ADD_COM = "ADD";
    public static final String DELETE_COM = "DELETE";
    public static final String EDIT_COM = "EDIT";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Возможные команды: \"ADD *текст*\", \"ADD *индекс* *текст*\" \"LIST\", \"DELETE *индекс*\", \"EDIT *индекс* *новый текст*\"");

        while (true) {
            String input = scanner.nextLine();
            String command = input;
            if (input.contains(" ")) {
                command = input.substring(0, input.indexOf(" "));
            }
            switch (command) {
                case LIST_COM:
                    list();
                    break;
                case ADD_COM:
                    add(input.substring(ADD_COM.length() + 1));
                    break;
                case DELETE_COM:
                    delete(input.substring(DELETE_COM.length() + 1));
                    break;
                case EDIT_COM:
                    edit(input.substring(EDIT_COM.length() + 1));
                    break;
                    default:
                        System.out.println("Команда написана некорректно. Команда должна начинаться с одного из ключевых слов: \"ADD \", \"LIST\", \"DELETE \", \"EDIT \"");

            }


        }
    }

    public static void list() {
        for (String task : toDoList){
            System.out.println(toDoList.indexOf(task) + " - " + task);
        }
    }

    public static void add(String input) {
        int index = -1;

        try {
            String tryparse = input.substring(0, input.indexOf(" "));
            index = Integer.parseInt(tryparse);
            input = input.substring(input.indexOf(" ") + 1);

        } catch (Exception ex) {

        }

        String taskForPrint = input;
        if (input.length() > 30){
            taskForPrint = input.substring(0, 25) + "...";
        }

        if (index > 0 && index < toDoList.size()){
            toDoList.add(index, input);
        } else {
            index = toDoList.size();
            toDoList.add(input);
        }

        System.out.println("Задание \"" + taskForPrint + "\" добавлено под индексом " + index);
    }

    public static void delete(String input) {

    }

    public static void edit(String input) {

    }
}

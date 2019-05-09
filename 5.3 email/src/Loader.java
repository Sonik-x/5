import java.util.HashSet;
import java.util.Scanner;

public class Loader {
    //зачем изобретать велосипед, когда уже всё изобрели (https://emailregex.com/)
    public static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static HashSet<String> emailList = new HashSet<>();

    public static final String LIST_COM = "LIST";
    public static final String ADD_COM = "ADD ";

    public static void main(String[] args) {
        String allCommands = "Возможные команды: \"" + ADD_COM + "*email*\", \"" + LIST_COM + "\"";
        System.out.println(allCommands);
        Scanner scanner = new Scanner(System.in);

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
                default:
                    System.out.println("Команда написана некорректно. " + allCommands);
            }
        }
    }

    public static void list() {
        if (emailList.isEmpty()) {
            System.out.println("Список имейлов пока что пуст.");
        }
        for (String mail : emailList) {
            System.out.println(mail);
        }
    }

    public static void add(String input){
        if(input.matches(emailRegex)){
            emailList.add(input);
            System.out.println("Email добавлен в перечень.");
        } else {
            System.out.println("Некорректный email.");
        }
    }
}

import java.util.HashMap;
import java.util.Scanner;


public class Loader {
    public static final String PHONE_REGEX = "^([7|8])?(\\d{3})(\\d{3})(\\d{2})(\\d{2}$)";
    public static final String PHONE_PATTERN = "+7 $2 $3-$4-$5";

    public static HashMap<String, String> phoneList = new HashMap<>();

    public static final String LIST_COM = "LIST";
    public static final String allCommands = "Возможные команды: \"" + LIST_COM + "\", номер или имя существующего контакта, либо имя или номер контакта, который вы хотите создать.";

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(allCommands);

        while (true) {
            String input = scanner.nextLine();
            input = input.trim();

            if (input.equals(LIST_COM)) {
                list();
            } else if (input.matches(PHONE_REGEX)) {
                input = input.replaceFirst(PHONE_REGEX, PHONE_PATTERN);
                if (phoneList.containsKey(input)) {
                    printContact(input);
                } else {
                    addByPhone(input);
                }
            } else {
                if(phoneList.containsValue(input)){
                    printContact(getKeyFromValue(phoneList, input));
                } else {
                    addByName(input);
                }
            }
        }
    }


    public static void list() {
        if (phoneList.isEmpty()) {
            System.out.println("Телефонная книга пока что пуста.");
        }
        for (String number : phoneList.keySet()) {
            System.out.println(phoneList.get(number) + ": " + number);
        }
    }

    public static void addByPhone(String input) {
        System.out.println("Номер " + input + " не найден. Хотите создать новый контакт?");
        if (answerYes()) {
            System.out.println("Введите имя для нового контакта.");
            String name = scanner.nextLine();
            addContact(input, name);
        } else {
            System.out.println(allCommands);
        }

    }

    public static void addByName(String name){
        System.out.println("Имя " + name + " не найдено. Хотите создать новый контакт?");
        if (answerYes()) {
            System.out.println("Введите номер для нового контакта.");
            String input = scanner.nextLine();
            if(!input.matches(PHONE_REGEX)){
                System.out.println("Некорректный номер.");
            } else {
                input = input.replaceFirst(PHONE_REGEX, "+7 $2 $3-$4-$5");
                if(phoneList.containsKey(input)){
                    System.out.println("Номер уже существует.");
                    printContact(input);
                } else {
                    addContact(input, name);
                }
            }
        } else {
            System.out.println(allCommands);
        }

    }

    public static void printContact(String number) {
        System.out.println(phoneList.get(number) + ": " + number);
    }

    public static boolean answerYes() {
        System.out.println("Если да, введите \"ДА\".");
        String input = scanner.nextLine();
        input = input.toLowerCase();
        if (input.startsWith("да")) {
            return true;
        }
        return false;
    }

    public static void addContact(String number, String name) {
        phoneList.put(number, name);
        System.out.println("Контакт \"" + name + "\" добавлен в телефонную книгу.");
    }

    public static String getKeyFromValue(HashMap<String, String> hm, Object value) {
        for (String o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }


}




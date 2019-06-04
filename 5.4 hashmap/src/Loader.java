import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
                input = input.toLowerCase();
                if (phoneList.containsValue(input)) {
                    printContact(getKeyFromValue(phoneList, input));
                } else {
                    String guess = checkedSpelling(input);
                    if (guess != null) {
                        System.out.println("Did you mean: \"" + firstUpperCase(guess) + "\"?");
                        if (answerYes()) {
                            printContact(getKeyFromValue(phoneList, guess));
                        } else {
                            addByName(input);
                        }
                    } else {
                        addByName(input);
                    }


                }
            }
        }
    }

    private static String checkedSpelling(String input) {

        ArrayList<String> edits = edits(input);
        HashSet<String> canditates = new HashSet<>();

        for (String edit : edits) {
            if (phoneList.containsValue(edit)) {
                canditates.add(edit);
            }
        }
        if (canditates.size() == 1) {
            return (String) canditates.toArray()[0];
        }

        return null;
    }


    public static void list() {
        if (phoneList.isEmpty()) {
            System.out.println("Телефонная книга пока что пуста.");
        }
        for (String number : phoneList.keySet()) {
            System.out.println(firstUpperCase(phoneList.get(number)) + ": " + number);
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

    public static void addByName(String name) {
        System.out.println("Имя " + firstUpperCase(name) + " не найдено. Хотите создать новый контакт?");
        if (answerYes()) {
            System.out.println("Введите номер для нового контакта.");
            String input = scanner.nextLine();
            if (!input.matches(PHONE_REGEX)) {
                System.out.println("Некорректный номер.");
            } else {
                input = input.replaceFirst(PHONE_REGEX, "+7 $2 $3-$4-$5");
                if (phoneList.containsKey(input)) {
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
        System.out.println(firstUpperCase(phoneList.get(number)) + ": " + number);
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
        System.out.println("Контакт \"" + firstUpperCase(name) + "\" добавлен в телефонную книгу.");
    }

    public static String getKeyFromValue(HashMap<String, String> hm, Object value) {
        for (String o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }


    private static ArrayList<String> edits(String word) {         //зачем изобретать велосипед, если можно укр.. одолжить..?  https://raelcunha.com/spell-correct/
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i + 1));
        for (int i = 0; i < word.length() - 1; ++i)
            result.add(word.substring(0, i) + word.substring(i + 1, i + 2) + word.substring(i, i + 1) + word.substring(i + 2));
        for (int i = 0; i < word.length(); ++i)
            for (char c = 'a'; c <= 'z'; ++c)
                result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i + 1));
        for (int i = 0; i <= word.length(); ++i)
            for (char c = 'a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
        return result;
    }

    public static String firstUpperCase(String word) {                 //ещё чуть докрученный велосипед с https://javalearnweb.wordpress.com/
        if (word == null || word.isEmpty()){
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }


}




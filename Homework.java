import java.lang.ref.PhantomReference;
import java.util.*;

public class Homework {

    /**
     * Реализовать консольное приложение - телефонный справочник.
     * У одной фамилии может быть несколько номеров.
     * Пользователь может вводить команды:
     * 1. ADD Ivanov 88005553535 - добавить в справочник новое значение. Первый
     * аргумент - фамилия, второй - номер телефона
     * 2. GET Ivanov - получить список всех номеров по фамилии
     * 3. REMOVE Ivanov - удалить все номера по фамилии
     * 4. LIST - Посмотреть все записи
     * 5. EXIT - Завершить программу
     * Если при GET или REMOVE нужной фамилии нет, вывести информацию об этом
     *
     * Пример взаимодействия (=> - это вывод на консоль):
     * ADD Ivanov 8 800 555 35 35
     * ADD Ivanov 8 800 100 10 10
     * GET Ivanov => [8 800 555 35 35, 8 800 100 10 10]
     * ADD Petrov 8 555 12 34
     * LIST => Ivanov = [8 800 5553535, 8 800 100 10 10], Petrov = [8 555 12 34]
     * REMOVE Ivanov
     * LIST => Petrov = [8 555 12 34]
     * GET NoName => Не найдена запись с фамилией "NoName"
     * REMOVE NoName => Не найдена запись с фамилией "NoName"
     */
    public static void main(String[] args) {
        HashMap<String, List<String>> phoneBook = new HashMap<>();
        String command = "";

        while (!command.equals("exit")) {
            command = inputCommand(phoneBook);
        }
    }

    private static String inputCommand(HashMap phoneBook) {
        Scanner scanner = new Scanner(System.in);
        String consoleCommand = scanner.nextLine();

        String command = getCommand(consoleCommand, phoneBook);
        return command;
    }

    private static String getCommand(String consoleCommand, HashMap phoneBookInput) {
        HashMap<String, List<String>> phoneBook = phoneBookInput;
        String[] line = consoleCommand.split("\\s++");
        String command = line[0].toLowerCase();

        if (command.equals("add")) {
            String name = line[1];
            String phone = line[2];

            if (!phoneBook.containsKey(name)) {
                phoneBook.put(name, new ArrayList<>(Arrays.asList(phone)));
            } else {
                List<String> phones = phoneBook.get(name);
                phones.add(phone);
                phoneBook.put(name, phones);
            }
            System.out.println(phoneBook);
        } else {
            if (command.equals("get")) {
                String name = line[1];
                System.out.println(phoneBook.get(name));
            } else {
                if (command.equals("remove")) {
                    String name = line[1];
                    phoneBook.remove(name);
                } else {
                    if (command.equals("list")) {
                        System.out.println(phoneBook);
                    } else {
                        if (command.equals("exit")) {
                            System.out.println("Работа программы завершена");
                        } else {
                            System.out.println("Команда введена неверно, повторите ввод");
                        }
                    }

                }
            }
        }

        return command;
    }
}
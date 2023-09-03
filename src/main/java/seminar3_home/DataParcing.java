/*
Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке,
разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол

Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым,
вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных,
чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно
использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано,
пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него
в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно
обработано, пользователь должен увидеть стектрейс ошибки.
 */
package seminar3_home;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DataParcing {
    public static void main(String[] args) {
        boolean continueFlag = true;
        String[] people = {
                "Сулименко Никита Сергеевич 02.01.1996 7978123456789 m", // добавление 1
                "Сулименко Женевьева Марковна Ж 15.02.1935 80507452632", // добавление 2
                "Ж 05.12.1984 Сулименко Виктория Даниловна 215463215",    // добавление 3
                "Иванов Ив Ген М 01.12.1970",                           // мало данных
                "Иванов Ив Ген М 01.01.1970 Ж Москва 2213412341",       // много данных
                "Иванов Ив Ген М 01.01.1970 2213412341",                // добавление 4
                "Иванов Иван Ген 8498494454 М 15.13.1940",              // кривая дата - неполный массив
                "Иванов Иван Ген 849О523444 М 01.10.1900",              // кривой телефон (0 != О) - неполный массив
                "32423 234234 234 234234 М 01.08.1990",                 // кривая фамилия и всё остальное - неполный массив
                "",                                                     // ИСКЛ - пустая строка
                "0"
        };


        int counter = 0;

        while (continueFlag) {
            String newPerson;

            try {
                // newPerson = userInput(); // БЛОК ВВОДА ДАННЫХ ВРУЧНУЮ
                newPerson = userInput(people[counter]); // ВВОД ДАННЫХ ИЗ тестового массива
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                counter += 1;
                continue; // прерываем выполнение WHILE и переходим на следующую итерацию
            }

            if (newPerson.strip().equals("0")) {
                continueFlag = false;
                System.out.println("Уже уходите? Ну что ж, прощайте =)");
                continue; // прерываем выполнение WHILE и переходим на следующую итерацию
            }

            switch (verifyData(newPerson)) {
                case 0:
                    try {
                        String[] arrPerson = parseData(newPerson);
                        saveFile(arrPerson);
                        System.out.println("Запись успешно добавлена в базу!");
                    } catch (NullPointerException e) {
                        System.out.println("ОШИБКА: Введённые данные не соответствуют запрошенному формату (" +
                                e.getMessage() + ").");
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    System.out.println("ОШИБКА 1: Введенных данных НЕДОСТАТОЧНО. Повторите ввод.");
                    break;
                case 2:
                    System.out.println("ОШИБКА 2: Введенные данные ИЗБЫТОЧНЫ. Повторите ввод.");
                    break;
            }

            counter += 1;
        } // конец WHILE с флагом
    }

    static String userInput(String strLine) throws IllegalArgumentException {
        String result;
        Scanner input = new Scanner(System.in);
        System.out.println("""
                \n>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
                Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
                >>> Для выхода введите 0(ноль)""");
        System.out.print("> ");

        if (strLine == null) {
            result = input.nextLine();
        } else {
            result = strLine;
            System.out.println(result);
        }
        if (result.isEmpty()) {
            throw new IllegalArgumentException("ОШИБКА: Некорректный ввод (пустая строка)");
        }
        return result;
    }


    static String userInput() {
        return userInput(null);
    }

    static int verifyData(String strLine) {
        String[] arr = strLine.split(" ");
        if (arr.length == 6) {
            return 0;
        } else if (arr.length < 6) {
            return 1;
        } else {
            return 2;
        }
    }

    static String[] parseData(String strLine) throws NullPointerException {
        String[] personInfo = strLine.split(" ");
        String[] result = new String[6];
        int nameCounter = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru","RU"));

        for (int i = 0; i < 6; i++) {
            if (personInfo[i].matches("[ЖМжмFMfm]")) {
                result[5] = personInfo[i];
            } else if (personInfo[i].matches("\\d+")) {
                result[4] = personInfo[i];
            } else if (personInfo[i].matches("[А-ЯЁа-яё]+") && nameCounter == 0) {
                nameCounter++;
                result[0] = personInfo[i];
            } else if (personInfo[i].matches("[А-ЯЁа-яё]+") && nameCounter == 1) {
                nameCounter++;
                result[1] = personInfo[i];
            } else if (personInfo[i].matches("[А-ЯЁа-яё]+") && nameCounter == 2) {
                nameCounter++;
                result[2] = personInfo[i];
            }

            try {
                LocalDate.parse(personInfo[i], formatter);
                result[3] = personInfo[i];
            } catch (DateTimeParseException e) {}
        }

        for (int i = 0; i < 6; i++) {
            if (result[i] == null) {
                String cause = switch (i) {
                    case 0 -> "ФАМИЛИИ";
                    case 1 -> "ИМЕНИ";
                    case 2 -> "ОТЧЕСТВА";
                    case 3 -> "ДАТЫ РОЖДЕНИЯ";
                    case 4 -> "ТЕЛЕФОНА";
                    case 5 -> "ПОЛА ";
                    default -> throw new IllegalStateException("Unexpected value: " + i);
                };
                throw new NullPointerException("некорректный ввод или отсутствие " + cause);
            }
        }

        return result;
    }


    private static void saveFile(String[] data) {
        File dataFile = new File(data[0] + ".txt");
        StringBuilder personInfo = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            personInfo.append("<");
            personInfo.append(data[i]);
            personInfo.append(">");
        }
        personInfo.append("\n");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("ОШИБКА " + e.getClass().getSimpleName()
                        + ": Не получается создать файл (>_<).");
                e.printStackTrace();
            }
        }

        if (dataFile.canWrite()) {
            try (FileWriter fileWriter = new FileWriter(dataFile, true)) {
                fileWriter.append(personInfo);
                fileWriter.flush();
            } catch (IOException e) {
                System.out.println("ОШИБКА " + e.getClass().getSimpleName()
                        + ": Не получается произвести запись в файл (>_<).");
                e.printStackTrace();
            }
        }
    }

}


/*
>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Сулименко Никита Сергеевич 02.01.1996 7978123456789 m
Запись успешно добавлена в базу!

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Сулименко Женевьева Марковна Ж 15.02.1935 80507452632
Запись успешно добавлена в базу!

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Ж 05.12.1984 Сулименко Виктория Даниловна 215463215
Запись успешно добавлена в базу!

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Иванов Ив Ген М 01.12.1970
ОШИБКА 1: Введенных данных НЕДОСТАТОЧНО. Повторите ввод.

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Иванов Ив Ген М 01.01.1970 Ж Москва 2213412341
ОШИБКА 2: Введенные данные ИЗБЫТОЧНЫ. Повторите ввод.

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Иванов Ив Ген М 01.01.1970 2213412341
Запись успешно добавлена в базу!

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Иванов Иван Ген 8498494454 М 15.13.1940
ОШИБКА: Введённые данные не соответствуют запрошенному формату (некорректный ввод или отсутствие ДАТЫ РОЖДЕНИЯ).

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> Иванов Иван Ген 849О523444 М 01.10.1900
ОШИБКА: Введённые данные не соответствуют запрошенному формату (некорректный ввод или отсутствие ТЕЛЕФОНА).

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> 32423 234234 234 234234 М 01.08.1990
ОШИБКА: Введённые данные не соответствуют запрошенному формату (некорректный ввод или отсутствие ФАМИЛИИ).

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
>
ОШИБКА: Некорректный ввод (пустая строка)

>>> Введите в произвольном порядке, разделяя пробелом, следующие данные:
Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) НомерТелефона(только цифры) Пол(m/f или м/ж)
>>> Для выхода введите 0(ноль)
> 0
Уже уходите? Ну что ж, прощайте =)
java.lang.NullPointerException: некорректный ввод или отсутствие ДАТЫ РОЖДЕНИЯ
	at seminar3_home.DataParcing.parseData(DataParcing.java:181)
	at seminar3_home.DataParcing.main(DataParcing.java:85)
java.lang.NullPointerException: некорректный ввод или отсутствие ТЕЛЕФОНА
	at seminar3_home.DataParcing.parseData(DataParcing.java:181)
	at seminar3_home.DataParcing.main(DataParcing.java:85)
java.lang.NullPointerException: некорректный ввод или отсутствие ФАМИЛИИ
	at seminar3_home.DataParcing.parseData(DataParcing.java:181)
	at seminar3_home.DataParcing.main(DataParcing.java:85)
java.lang.IllegalArgumentException: ОШИБКА: Некорректный ввод (пустая строка)
	at seminar3_home.DataParcing.userInput(DataParcing.java:122)
	at seminar3_home.DataParcing.main(DataParcing.java:68)
 */
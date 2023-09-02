/*
Запишите в файл следующие строки:
Анна=4
Елена=5
Марина=6
Владимир=?
Константин=?
Иван=4
Реализуйте метод, который считывает данные из файла и сохраняет в двумерный массив
(либо HashMap, если студенты с ним знакомы). В отдельном методе нужно будет пройти по структуре данных,
если сохранено значение ?, заменить его на соответствующее число.Если на каком-то месте встречается символ,
отличный от числа или ?, бросить подходящее исключение.Записать в тот же файл данные с замененными символами ?.

*/

package seminar2;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        String newData = "Анна=4\nЕлена=5\nМарина=dfg\nВладимир=?\nКонстантин=?\nИван=414\nЕлизавета=12В";
        File dataBase = new File("names.txt");

        saveFile(dataBase, newData);
        System.out.println("Файл " + dataBase.getName() + " создан. Данные сохранены в файл.");
        System.out.println("Содержимое файла: \n" + newData);

        LinkedHashMap<String, String> names = loadFile(dataBase);
        System.out.println("Загрузка содержимого файла...");
        System.out.println("Коррекция данных...");
        redactData(names);

        saveFile(dataBase, mapToString(names));
        System.out.println("Скорректированные данные записаны в файл " + dataBase.getName() + ".");
        System.out.println("Содержимое файла: \n" + mapToString(names));

    }

    private static void redactData(LinkedHashMap<String, String> data) {
        for(Map.Entry<String, String> item: data.entrySet()){
            if (item.getValue().equals("?")){
                item.setValue(String.valueOf(item.getKey().length()));
            }
            if (! item.getValue().matches("\\d+")){ // ЕСЛИ В СТРОКЕ НЕ ЧИСЛО (\\d - цифра, + - любое их количество)
                System.out.print(">>> ИСКЛЮЧЕНИЕ: в файле исправлена битая строка: " + item + " => ");
                item.setValue(String.valueOf(item.getKey().length()));
                System.out.println(item);
            }
        }
    }

    public static String mapToString(LinkedHashMap<String,String> map){
        String line = map.toString().replaceAll("[\\{\\}]",""); // удаление фигурных скобок
        return line.replaceAll(", ", "\n"); // замена запятой на перевод каретки
    }

    public static LinkedHashMap<String, String> loadFile(File dataFile) {
        LinkedHashMap<String, String> names = new LinkedHashMap<>();

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if(dataFile.canWrite()) {
            try {
                Scanner fileScanner = new Scanner(dataFile);

                while (fileScanner.hasNextLine()) {
                    String[] line = fileScanner.nextLine().split("=");
                    names.put(line[0], line[1]);
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("ОШИБКА: не получается прочитать файл (>_<)");
                throw new RuntimeException(e);
            }
        }
        return names;
    }

    private static void saveFile(File dataFile, String data) {

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }

        if(dataFile.canWrite()) {
            try (FileWriter fileWriter = new FileWriter(dataFile)) {
                fileWriter.write(data);
                fileWriter.flush();
            } catch (IOException e) {
                System.out.println("ОШИБКА: Не получается создать файл (>_<)");
            }
        }
    }

}



/*
//========== ВАРИАНТ С СЕМИНАРА====================
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationExceptions01 {
    public static void main(String[] args) {
        String fileName = initFile();
        writeFile(parseFile(readFile(fileName)), fileName);
    }

    private static void writeFile(HashMap<String, String> hashMap, String fileName) {
        File file = new File(fileName);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (Map.Entry<String, String> line : hashMap.entrySet()) {
                fr.write(line.getKey() + "=" + line.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Can't write file!");
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                System.out.println("Can't close file!");
            }
        }

    }

    private static HashMap<String, String> parseFile(HashMap<String, String> hashMap) {
        for (Map.Entry<String, String> line : hashMap.entrySet()) {
            if (line.getValue().equals("?")) {
                line.setValue(String.valueOf(line.getKey().length()));
            }
        }
        return hashMap;
    }

    private static HashMap<String, String> readFile(String fileName) {
        HashMap<String, String> hmNames = new HashMap<String, String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {

                String[] lineParts = line.split("=");
                hmNames.put(lineParts[0], lineParts[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
        }

        return hmNames;
    }

    private static String initFile() {
        File file = new File("Names.txt");
        FileWriter fr = null;
        try {
            file.createNewFile();
            fr = new FileWriter(file);
            fr.write("Анна=4\n" +
                    "Елена=5\n" +
                    "Марина=6\n" +
                    "Владимир=?\n" +
                    "Константин=?\n" +
                    "Иван=4");
        } catch (IOException e) {
            System.out.println("Can't create file!");
        } finally {
            try {
                fr.flush();
                fr.close();
            } catch (IOException e) {
                System.out.println("Can't close file!");
            }
        }
        return file.getName();
    }

}
 */
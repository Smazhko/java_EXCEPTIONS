/*
ПРОГРАММА С ОШИБКАМИ

package java1.lessonsforqa.lesson1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class RandomExceptionsClass {
    public static void main(String[] args) throws IOException {
        callMethod6("");
        ArrayList<String> stringArraysList = (ArrayList<String>) callMethod1();
        // ошибка приведения типов - callMethod1() возвращает LinkedList
        int a=10, b=0;
        callMethod6("");
        int div = callMethod2(a, b);
        System.out.println(div);
        callMethod6("");
        callMethod3(stringArraysList);
    }

    private static void callMethod6(String s) {
        System.out.println("Hello World, it's me!");
    }

    private static void callMethod3(ArrayList<String> stringArraysList) {
        callMethod6("");
        callMethod3(stringArraysList); // метод вызывает сам себя метод3-метод3
    }

    private static Collection<String> callMethod1() throws IOException {
        callMethod2(100000000, 10-10);
        return new LinkedList<>();
    }

    public static int callMethod2(int a, int b) throws IOException {
        callMethod6("");
        FileInputStream fis = new FileInputStream("1.txt"); // файла 1.тхт не существует - создали файл
        fis.read();

        if(fis.available() > 0) throw new RuntimeException();
        int num = callMethod4("124O"); // стоит буква О
        return a/b;  // не предусмотрена обработка деления на ноль
    }

    private static int callMethod4(String s) {
        callMethod5(s);
        return Integer.parseInt(s); // ошибка преобразования 124О, вместо 1240
    }

    private static void callMethod5(String s) {
        callMethod6("");
        String[] strings = new String[5];
        for (int i = 1; i <= strings.length; i++){  //выход за пределы массива: <= длины массива
            strings[i] = s;
        }
    }
}
 */

package seminar1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class RandomExceptionsClass {
    public static void main(String[] args) throws IOException {
        callMethod6("");


        Collection<String> newCollect = callMethod1();

        ArrayList<String> stringArraysList;

        if (newCollect instanceof ArrayList<String>){
            stringArraysList = (ArrayList<String>) callMethod1();
        } else{
            stringArraysList = new ArrayList<>(callMethod1());
        }

        int a=10, b=0;
        callMethod6("");
        int div = callMethod2(a, b);
        System.out.println(div);
        callMethod6("");
        callMethod3(stringArraysList);
    }


    private static void callMethod3(ArrayList<String> stringArraysList) {
        callMethod6("");
        //callMethod3(stringArraysList);
    }

    private static Collection<String> callMethod1() throws IOException {
        callMethod2(100000000, 10-10);
        return new LinkedList<>();
    }

    public static int callMethod2(int a, int b) throws IOException {
        callMethod6("");
        FileInputStream fis = new FileInputStream("1.txt");
        fis.read();

        if(fis.available() > 0) throw new RuntimeException();
        int num = callMethod4("1240");

        return b!=0 ? a/b: a;
    }

    private static int callMethod4(String s) {
        callMethod5(s);
        return Integer.parseInt(s);
    }

    private static void callMethod5(String s) {
        callMethod6("");
        String[] strings = new String[5];
        for (int i = 1; i < strings.length; i++){
            strings[i] = s;
        }
    }

    private static void callMethod6(String s) {
        System.out.println("Hello World, it's me!");
    }
}

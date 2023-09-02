/*
Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
 */

package seminar2_home;

import java.util.Scanner;


// ========== С П О С О Б  1 ============================
public class Task4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите непустую строку:\n> ");
        String result = input.nextLine();
        try {
            if (result.isEmpty()){
                throw new IllegalArgumentException();}
            System.out.println("Подтверждаю ввод: " + result);
        } catch (Exception e){
            System.out.println("Пустые строки вводить нельзя! " + e);
        }
    }
}

/*
//============== С П О С О Б 2 ===================
// создать метод, которые умеет бросать исключение в нужный момент
// поймать и обработать исключение в MAIN

public class Task4 {
    public static void main(String[] args) {
        try{
            System.out.println("Подтверждаю ввод: " + userInput("Введите непустую строку"));
        } catch (IllegalArgumentException e){
            System.out.println("Пустые строки вводить нельзя");
        }
    }

    public static String userInput(String message) throws IllegalArgumentException{
        Scanner input = new Scanner(System.in);
        System.out.print(message + ":\n> ");
        String result = input.nextLine();

        if (result.isEmpty()){
            throw new IllegalArgumentException();
        }

        return result;
    }
}
 */
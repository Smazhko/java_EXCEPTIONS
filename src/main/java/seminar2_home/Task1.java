/*
Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает
введенное значение. Ввод текста вместо числа не должно приводить к падению приложения, вместо этого,
необходимо повторно запросить у пользователя ввод данных.
 */

package seminar2_home;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
//        System.out.println("Введённое Вами число: " + floatRequest() + " имеет тип Float.");
        System.out.println("Введённое Вами число: " + floatRequest1() + " имеет тип Float.");

    }

    //Способ через регулярное выражение
    public static Float floatRequest() {
        Float floatNum = 0f;
        String userInput;
        boolean continueFlag = true;
        Scanner input = new Scanner(System.in);

        while (continueFlag) {
            System.out.print("Введите число - десятичную дробь:\n> ");
            userInput = input.nextLine();
            if (userInput.matches("^[-+]?[0-9]+[.,]?[0-9]*$")) { //проверка на соответствие строки шаблону
                // ^[-+]*[0-9]+[.,]?[0-9]*$ или ^[-+]*\\d+[.,]?\\d*$:
                // ^      - начало строки
                // []     - в квадратных скобках указывается список символов
                // * ? +  - знак после каждых скобок указывает на количество принимаемых повторений
                // [-+]*  - "+" или "-" в начале строки, ? - 1 раз или отсутствует
                // [0-9]+ - цифры (можно заменить на \\d), + - 1 или более раз
                // [.,]?  - "." или "," в качестве разделителя, ? - 1 раз или отсутствует
                // [0-9]* - цифры(можно заменить на \\d), * ноль или более раз
                // $      - конец строки
                // подойдут строки вида:
                // 1 или 1,0 или 1.0 или -1 или -1, или +23,23

                userInput = userInput.replace(",", ".");
                floatNum = Float.parseFloat(userInput);
                continueFlag = false;
            } else {
                System.out.println("Неверный ввод!");
            }
        }

        return floatNum;
    }


    // способ через try-catch
    public static Float floatRequest1(){
        Float floatNum = 0f;
        boolean continueFlag = true;
        Scanner input = new Scanner(System.in);

        while (continueFlag) {
            System.out.print("Введите число - десятичную дробь:\n> ");
            try{
                return Float.parseFloat(input.nextLine());
            } catch (Exception e){
                System.out.println("Неверный ввод! ");
                System.out.println("ОШИБКА " + e.getClass().getSimpleName() + ": " + e.getMessage());
                floatNum = 0f;
                // Без возврата к значению по умолчанию запускается бесконечный цикл:
                // Введите число - десятичную дробь:
                //> Неверный ввод!
                // Введите число - десятичную дробь:
                //> Неверный ввод!
            }

        }
        return floatNum;
    }

}

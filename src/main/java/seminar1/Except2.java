package seminar1;

import java.util.Random;

/*
Реализуйте метод, принимающий в качестве аргумента целочисленный двумерный массив.
Необходимо посчитать и вернуть сумму элементов этого массива.
При этом накладываем на метод 2 ограничения: метод может работать только с
квадратными массивами (кол-во строк = кол-ву столбцов), и в каждой ячейке может
лежать только значение 0 или 1.
Если нарушается одно из условий, метод должен бросить RuntimeException с сообщением об ошибке.
 */
public class Except2 {

    public static void main(String[] args) {

        int size = 5;
        int[][] intsSquare = new int[5][6];

        for (int i = 0; i < intsSquare.length; i++) {

            for (int j = 0; j < intsSquare[i].length; j++) {
                intsSquare[i][j] = new Random().nextInt(0, 2);
                System.out.print(intsSquare[i][j] + " ");
            }

            System.out.println(" ");
        }


        checkArray(intsSquare);
        System.out.println(countSumm(intsSquare));

    }

    public static int countSumm(int[][] array){
        int summ = 0;
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {
                summ = summ + array[i][j];
            }
        }
        return summ;
    }

    public static void checkArray(int[][] array){

        if (array.length != array[0].length){
            throw new RuntimeException("Массив не квадратный: " + array.length + "x"+ array[0].length);
        }

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {
                if (!(array[i][j] == 0 || array[i][j] == 1)){
                    throw new RuntimeException("В массиве не только 0 или 1");
                }
            }
        }

    }
}

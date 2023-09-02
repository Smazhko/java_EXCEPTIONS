/*
Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
При подаче массива другого размера необходимо бросить исключение MyArraySizeException.

Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
в какой именно ячейке лежат неверные данные.

В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException
и MyArrayDataException и вывести результат расчета (сумму элементов, при условии, что подали
на вход корректный массив).
 */
package seminar3;

public class Task4 {

    public static void main(String[] args) {
        String[][] myArray = {
                {"0", "1", "12", "6"},
                {"13", "123", "456", "zxu"},
                {"xcv", "dfg", "ghg", "zyu"},
                {"456", "shf", "123", "zxv"}};

        String[][] myArray1 = {
                {"23", "567", "12", "90"},
                {"13", "123", "456", "100"},
                {"12", "16", "89", "56"},
                {"456", "45", "123", "89"}};

        String[][] myArray2 = {
                {"wer", "sdf", "12"},
                {"13", "123", "456"},
                {"xcv", "dfg", "ghg"},
                {"456", "shf", "123"}};

        System.out.println(arraySum(myArray));
        //System.out.println(arraySum(myArray1));
        //System.out.println(arraySum(myArray2));
    }

    public static int arraySum (String[][] arr){
        if(arr.length != 4 || arr[0].length != 4){
            throw new MyArraySizeException(arr.length + " x " + arr[0].length);
        }

        int summ = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try{
                    summ += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e){
                        throw new MyArrayDataException(i + ", " + j);
                }
            }
        }
        return summ;
    }
}

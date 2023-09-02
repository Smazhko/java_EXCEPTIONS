package seminar1;/*
Реализуйте метод checkArray(Integer[] arr), принимающий в качестве аргумента целочисленный одномерный массив.
Метод должен пройтись по каждому элементу и проверить что он не равен null.
А теперь реализуйте следующую логику:
Если в какой-то ячейке встретился null, то необходимо “оповестить” об этом пользователя
Если null’ы встретились в нескольких ячейках, то идеально было бы все их “подсветить”
 */
import java.util.ArrayList;

public class Except3 {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {2, 0, 5, 0, 5, 5, 0, 7};
        checkArray(arr);
    }

    public static void checkArray(Integer[] array){
        ArrayList<Integer> nullLoc = new ArrayList<>();

        boolean nullFlag = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null){
                nullLoc.add(i);
                nullFlag = true;
            }
        }

        if (nullFlag){
            throw new RuntimeException("Массив содержит значения NULL: " + nullLoc);
        }
    }
}

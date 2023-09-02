/*
Создайте класс Счетчик, у которого есть метод add(), увеличивающий значение
внутренней int переменной на 1. Сделайте так, чтобы с объектом такого типа
можно было работать в блоке try-with-resources. Подумайте, что должно происходить
при закрытии этого ресурса? Напишите метод для проверки, закрыт ли ресурс.
При попытке вызвать add() у закрытого ресурса, должен выброситься IOException
 */
package seminar3;

import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {

        try (Counter cntr = new Counter()){
            cntr.add();
            cntr.close();
            cntr.add();

        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Counter implements AutoCloseable{
    private int num = 0;
    private boolean closed = false;


    public void add () throws IOException {
        if (closed){
           throw new IOException("Счётчик закрыт");
        }
        num += 1;
    }

    @Override
    public void close() throws Exception {
        num = 0;
        closed = true;
    }

}

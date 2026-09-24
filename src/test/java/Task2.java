//Задача 2: добавить в автотесты разработанные в задаче 2 темы «Gradle и JUnit» информативные ассерты (заменить проверки через if на ассерты).
// При падении ассерты должны дать информацию, что ожидалось и что было получено в результате падения. Дополнить задачу запуска автотестов фильтрацией,
// или по аннотации @Tag, или по пакету. Запустить каждый тестовый метод не менее 10 раз.

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2 {

    public static boolean isEven(int n) {
        return (n % 2 == 0);
    }

    @RepeatedTest(10)
    @Tag("Smoke")
    @Order(1)
    void test1() {
        Random random = new Random();
        int n = random.nextInt(1, 101);
        boolean actual = isEven(n);
        boolean expected = (n % 2 == 0);
        assertThat(actual)
                .isEqualTo(expected);
    }

    public static String checkAccess(int age) {
        return (age > 18) ? "Allowed" : "Denied";
    }

    @RepeatedTest(10)
    @Tag("Smoke")
    @Order(2)
    void test2() {
        Random random = new Random();
        int age = random.nextInt(0, 100);
        String actual = checkAccess(age);
        String expected = (age > 18) ? "Allowed" : "Denied";
        assertThat(actual)
                .as("Неверно определены разрешенные границы")
                .isEqualTo(expected);
    }

    //Задача 3: разработать метод с сигнатурой public static boolean isPositive(int n).
// Метод должен возвращать true, если переданное число больше или равно нулю, и false, если переданное число меньше нуля.
// Проверка внутри метода должна происходить с помощью тернарного оператора.
    public static boolean isPositive(int n) {
        return (n >= 0) ? true : false;
    }

    @RepeatedTest(10)
    @Tag("Regression")
    @Order(3)
    void Test3() {
        Random random = new Random();
        int n = random.nextInt(-50, 51);
        boolean actual = isPositive(n);
        boolean expected = n >= 0;
        assertThat(actual)
                .isEqualTo(expected);
    }

    public static String getGrade(int score) {
        String grade;
        if (score >= 0 && score <= 20) {
            grade = "E";
        } else if (score >= 21 && score <= 40) {
            grade = "D";
        } else if (score >= 41 && score <= 60) {
            grade = "C";
        } else if (score >= 61 && score <= 80) {
            grade = "B";
        } else if (score >= 81 && score <= 100) {
            grade = "A";
        } else {
            grade = "Error";
        }
        return grade;
    }


    @ParameterizedTest
    @Tag("Regression")
    @Order(4)
    @MethodSource("numbers")
    void Test4(int score) {
        String actual = getGrade(score);
        String expected;
        if (score <= 20) expected = "E";
        else if (score <= 40) expected = "D";
        else if (score <= 60) expected = "C";
        else if (score <= 80) expected = "B";
        else if (score <= 100) expected = "A";
        else expected = "Error";

        assertThat(actual)
                .as("Неверная оценка")
                .isEqualTo(expected);
    }


    static Integer[] numbers() {
        Random random = new Random();
        Integer[] arrayScore = new Integer[10];
        for (int i = 0; i < arrayScore.length; i++) {
            arrayScore[i] = random.nextInt(0, 101);
        }
        return arrayScore;
    }

    //Задача 5: разработать метод с сигнатурой public static String blastOff(int start). Метод принимает стартовое число (например, 5)
// и возвращает строку со всеми числами до 1 и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).
    public static String blastOff(int start) {
        int i;
        String text = "";
        for (i = start; i > 0; i--) {
            text = text + i + " ";
        }
        String finishText = text + "Поехали!";
        return finishText;
    }


    @RepeatedTest(10)
    @Tag("Regression")
    @Order(5)
    void Test5() {
        Random random = new Random();
        int start = random.nextInt(1, 101);
        String actual = blastOff(start);
        String text = "";
        for (int j = start; j > 0; j--) {
            text = text + j + " ";
        }
        String expected = text + "Поехали!";
        assertThat(actual)
                .isEqualTo(expected);
    }


    //Задача 6: разработать метод с сигнатурой publiс static int sumToN(int n). Метод возвращает сумму всех целых чисел от 1 до n.
    public static int sumToN(int n) {
        int i;
        int result = 0;
        for (i = 1; i <= n; i++) {
            result = result + i;
        }
        return result;
    }

    @RepeatedTest(10)
    @Tag("Smoke")
    @Order(6)
    void test6() {
        Random random = new Random();
        int n = random.nextInt(0, 101);
        int actual = sumToN(n);
        int expected = (n * (n + 1)) / 2;
        assertThat(actual)
                .isEqualTo(expected);
    }


//Задача 7: разработать метод с сигнатурой publiс static boolean hasBug(String[] messages).
// Метод принимает массив строк и возвращает true, если хотя бы одна строка в массиве равна Bug.
// Сравнение можно выполнять без учёта регистра.

    public static boolean hasBug(String[] messages) {
        for (String message : messages) {
            if (message.equals("Bug")) {
                return true;
            }
        }
        return false;
    }

    @ParameterizedTest
    @Tag("Smoke")
    @Order(7)
    @CsvFileSource(resources = "/test_data.csv")
    void test7(String rawMessages) {
        String[] messages = rawMessages.split(";");
        boolean actual = hasBug(messages);
        boolean expected = false;
        for (String m : messages) {
            if (m.equals("Bug")) {
                expected = true;
                break;
            }
        }

        assertThat(actual)
                .as("Неверно определили местонахождение Bug")
                .isEqualTo(expected);
    }


}

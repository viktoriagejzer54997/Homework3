package homework.task2;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2_package {
    //Задача 8: разработать метод с сигнатурой publiс static getEvenInRange(int start, int end).
// Метод принимает границы диапазона и возвращает строку, состоящую только из чётных чисел внутри этого промежутка (включая границы),
// разделённых пробелом. Перед первым и после последнего числа пробел не ставится. Например: (2, 5) -> “2 4”

    public static String getEvenInRange(int start, int end) {
        int i;
        String numbers = "";
        for (i = start; i <= end; i++) {
            if (i % 2 == 0) {
                numbers = numbers + i + " ";
            }
        }
        return numbers.trim();
    }

    @RepeatedTest(10)
    @Order(8)
    void test8() {
        Random random = new Random();
        int start = random.nextInt(0, 20);
        int end = random.nextInt(20, 51);
        String actual = getEvenInRange(start,end);
        String expected = "";
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                expected = expected + i + " ";
            }
        }
        expected = expected.trim();

        assertThat(actual)
                .isEqualTo(expected);
    }


    //Задача 9: разработать метод с сигнатурой publiс static public int findMax(int[] arr).
// Метод находит и возвращает самое большое число в переданном массиве.
    public static int findMax(int[] arr) {
        int i;
        int max = arr[0];
        for (i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;

    }

    @RepeatedTest(10)
    @Order(9)
    void test9() {
        Random random = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(0, 1001);
        }

        int actual = findMax(arr);
        int expected = Arrays.stream(arr).max().getAsInt();
        assertThat(actual)
                .as("Не максимальное значение из массива!")
                .isEqualTo(expected);
    }


    //Задача 10: разработать метод с сигнатурой publiс static String[] reverse(String[] arr).
// Метод возвращает новый массив, в котором элементы исходного массива расположены в обратном порядке. Например, {“One”, “Two”, “Zero”} -> {“Zero”, “Two”, “One}.
    public static String[] reverse(String[] arr) {
        int i;
        int j = 0;
        String[] reverseArr = new String[arr.length];

        for (i = arr.length - 1; i >= 0; i--) {
            reverseArr[j] = arr[i];
            j++;
        }
        return reverseArr;
    }

    @ParameterizedTest
    @Order(10)
    @CsvFileSource(resources = "/test_data.csv")
    void test10(String rawArr) {
        String[] arr = rawArr.split(";");
        String[] actual = reverse(arr);
        String[] expected = new String[arr.length];
        int j = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            expected[j] = arr[i];
            j++;
        }

        assertThat(actual)
                .isEqualTo(expected);
    }


    //Задача 11: разработать метод с сигнатурой publiс static calcAverage(List<Integer> list).
// Метод вычисляет и возвращает среднее арифметическое всех чисел в списке.
    public static double calcAverage(List<Integer> list) {
        int i;
        double sum = 0;
        for (i = 0; i < list.size(); i++) {
            sum = sum + list.get(i);
        }
        return sum / list.size();
    }

    @RepeatedTest(10)
    @Order(11)
    void test11() {
        Random random = new Random();
        int size = 10;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(0, 100));
        }
        double actual = calcAverage(list);
        double expected = list.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        assertThat(actual)
                .isEqualTo(expected);
    }

//Задача 12: разработать метод с сигнатурой publiс static List<String> removeSpecificName(List<String> list, String nameToRemove).
// Метод принимает список и имя, которое нужно исключить. Возвращает новый список, не содержащий указанного имени.

    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {
        int i;
        List<String> names = new ArrayList<>();
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).equals(nameToRemove)) {
                continue;
            }
            names.add(list.get(i));
        }
        return names;
    }

    @ParameterizedTest
    @Order(12)
    @CsvFileSource(resources = "/test_data_12.csv")
    void test12(String rawList, String nameToRemove) {
        List<String> list = Arrays.asList(rawList.split(";"));
        List<String> actual = removeSpecificName(list, nameToRemove);
        List<String> expected = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String currentName = list.get(i);
            if (!currentName.equals(nameToRemove)) {
                expected.add(currentName);
            }
        }
        assertThat(actual)
                .isEqualTo(expected);



    }}


//Задача 1: написать 4 автотеста, использующих в работе ассерты. Методы, которые будут проверяться автотестами,
// можно написать самостоятельно или взять из предыдущих заданий.Требования к методам — хотя бы один метод:
//
//возвращает булево значение;
//должен возвращать список;
//должен вернуть неверное значение (ассерт должен падать).
//При падении ассерты должны дать информацию, что ожидалось и что было получено в результате падения.

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class Task1 {
    public static boolean isEven(int n) {
        return (n % 2 == 0);
    }

    @Test
    void test1() {
        Random random = new Random();
        int n = random.nextInt(1, 101);
        boolean actual = isEven(n);
        boolean expected = (n % 2 == 0);
        assertThat(isEven(n))
                .isEqualTo(expected);
    }

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
@CsvFileSource(resources = "/test_data_12.csv")
void test2(String rawList, String nameToRemove) {
    List<String> list = Arrays.asList(rawList.split(";"));
    List<String> actual = removeSpecificName(list, nameToRemove);
    List<String> expected = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
        String currentName = list.get(i);
        if (!currentName.equals(nameToRemove)) {
            expected.add(currentName);
        }
    }
    assertThat(removeSpecificName(list, nameToRemove))
            .isEqualTo(expected);

}

    //Задача 9: разработать метод с сигнатурой publiс static public int findMax(int[] arr).
// Метод находит и возвращает самое большое число в переданном массиве.
    public static int findMax(int[] arr) {
        int i;
        int max = arr[0];
        for (i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                max = arr[i];
            }
        }
        return max;

    }

    @Test
    void test3() {
        Random random = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(0, 1001);
        }

        int actual = findMax(arr);
        int expected = Arrays.stream(arr).max().getAsInt();
        assertThat(findMax(arr))
                .as("Не максимальное значение из массива!")
                .isEqualTo(expected);
    }

    public static String getGrade(int score) {
        String grade;
        if (score >= 0 && score <= 20) {
            grade = "E";
        } else if (score >= 21 && score <= 50) {
            grade = "D";
        } else if (score >= 51 && score <= 70) {
            grade = "C";
        } else if (score >= 71 && score <= 80) {
            grade = "B";
        } else if (score >= 81 && score <= 100) {
            grade = "A";
        } else {
            grade = "Error";
        }
        return grade;
    }


    @ParameterizedTest
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

        assertThat(getGrade(score))
                .as("Неверная оценка")
                .isEqualTo(expected);
    }

    static Integer[] numbers() {
        Random random = new Random();
        Integer[] arrayScore = new Integer[5];
        for (int i = 0; i < arrayScore.length; i++) {
            arrayScore[i] = random.nextInt(0, 101);
        }
        return arrayScore;
    }
}

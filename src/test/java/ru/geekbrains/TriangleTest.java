package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static ru.geekbrains.Triangle.areaTriangle;

public class TriangleTest  {

    @Test
    @DisplayName("Позитивная проверка метода вычисления площади треугольника со сторонами 3, 4, 5")
    void positiveTest1 () throws TriangleIsNotAvailable {
        Assertions.assertEquals(6, areaTriangle (3, 4, 5));
    }

    @Test
    @DisplayName("Позитивная проверка метода вычисления площади треугольника со сторонами 12, 10, 10")
    void positiveTest2 () throws TriangleIsNotAvailable {
        Assertions.assertEquals(48, areaTriangle (12, 10, 10));
    }

    @Test
    @DisplayName("Негативная проверка метода вычисления площади несуществующего треугольника")
    void negativeTest1 () throws TriangleIsNotAvailable {
        Assertions.assertThrows(TriangleIsNotAvailable.class, () -> areaTriangle (3, 5, 9));
    }

    @Test
    @DisplayName("Негативная проверка метода вычисления площади треугольника c отрицательной длиной стороны")
    void negativeTest2 () throws TriangleIsNotAvailable {
        Assertions.assertThrows(TriangleIsNotAvailable.class, () -> areaTriangle (-5, 9, 9));
    }
}

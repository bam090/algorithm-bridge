package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

/** JUnit Jupiter로 ArraySolution02를 검증한다. */
public final class ArraySolution02Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("rangeCases")
    @DisplayName("범위 안의 값 개수를 센다")
    void countsValuesInRange(String name, int expected, int[] values, int minimum, int maximum) {
        assertEquals(expected, ArraySolution02.solve(values, minimum, maximum),
                () -> name + ": input=" + Arrays.toString(values));
    }

    @Test
    @DisplayName("길이 상한과 일반 중간값 940")
    void test06() {
        int[] values = new int[1_000];
        Arrays.fill(values, 940);
        assertEquals(1_000, ArraySolution02.solve(values, 940, 940));
    }

    private static Stream<Arguments> rangeCases() {
        return Stream.of(
                Arguments.of("범위 안팎이 섞인 값", 3, new int[]{-2, 0, 5, 7, 10}, 0, 7),
                Arguments.of("양쪽 경계값 포함", 4, new int[]{0, 1, 6, 7}, 0, 7),
                Arguments.of("빈 배열", 0, new int[]{}, -1, 1),
                Arguments.of("음수 범위에서 모두 범위 밖", 0, new int[]{-10, 0, 10}, -5, -1),
                Arguments.of("최솟값과 최댓값이 같은 범위", 2,
                        new int[]{-10_000, -3, -3, 10_000}, -3, -3)
        );
    }
}

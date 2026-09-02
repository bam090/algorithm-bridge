package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution03을 검증한다. */
public final class ArraySolution03Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("differenceCases")
    @DisplayName("이웃한 값의 변화량을 계산한다")
    void calculatesDifferences(String name, int[] expected, int[] values) {
        assertArrayEquals(expected, ArraySolution03.solve(values), name);
    }

    @Test
    @DisplayName("길이 상한과 940을 포함한 연속 증가")
    void test06() {
        int[] values = new int[1_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        int[] expected = new int[999];
        Arrays.fill(expected, 1);
        assertArrayEquals(expected, ArraySolution03.solve(values));
    }

    private static Stream<Arguments> differenceCases() {
        return Stream.of(
                Arguments.of("증가·감소·같음이 섞인 값", new int[]{3, -1, 0},
                        new int[]{10, 13, 12, 12}),
                Arguments.of("원소가 하나인 배열", new int[]{}, new int[]{7}),
                Arguments.of("현재 값에서 이전 값 빼기", new int[]{-5, -5, 5},
                        new int[]{5, 0, -5, 0}),
                Arguments.of("원소가 두 개인 배열", new int[]{2}, new int[]{-1, 1}),
                Arguments.of("가장 큰 양수·음수 변화량", new int[]{20_000, -20_000},
                        new int[]{-10_000, 10_000, -10_000})
        );
    }

}

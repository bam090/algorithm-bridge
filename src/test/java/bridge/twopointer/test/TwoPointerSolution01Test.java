package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution01;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class TwoPointerSolution01Test {

    /*
     * 매개변수 | 허용 범위
     * first.length, second.length | 0 이상 100,000 이하
     * first[i], second[i] | -1,000,000,000 이상 1,000,000,000 이하
     * 배열 값 | 같은 값이 반복될 수 있는 오름차순
     * 대표 오답 | 큰 값 쪽 이동, 같은 값에서 한쪽만 이동, 한 흐름 종료 뒤 계속 읽기, 원본 변경
     */
    @DisplayName("두 정렬 배열의 공통 번호를 찾는다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testCommonValues(String name, int[] first, int[] second, int[] expected) {
        check(name, first, second, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("중복 횟수만큼 공통 번호와 원본 보존",
                        new int[]{1, 4, 4, 7, 10},
                        new int[]{2, 4, 4, 4, 7, 9},
                        new int[]{4, 4, 7}),
                arguments("양쪽의 서로 다른 중복 횟수",
                        new int[]{1, 1, 1, 2, 2},
                        new int[]{1, 1, 2, 2, 2},
                        new int[]{1, 1, 2, 2}),
                arguments("빈 첫 배열", new int[0], new int[]{0, 940}, new int[0]),
                arguments("공통 번호 없음", new int[]{1, 3, 5}, new int[]{2, 4, 6}, new int[0]),
                arguments("작은 쪽을 번갈아 이동",
                        new int[]{1, 5, 9}, new int[]{2, 3, 5, 8, 9}, new int[]{5, 9}),
                arguments("값 하한·0·940·상한",
                        new int[]{-1_000_000_000, 0, 940, 1_000_000_000},
                        new int[]{-1_000_000_000, -1, 0, 940, 1_000_000_000},
                        new int[]{-1_000_000_000, 0, 940, 1_000_000_000})
        );
    }

    @Test
    @DisplayName("두 배열의 길이 상한을 처리한다")
    void testMaximumLengths() {
        int[] first = new int[100_000];
        int[] second = new int[100_000];
        int[] expected = new int[100_000];
        for (int index = 0; index < 100_000; index++) {
            int value = index * 2;
            first[index] = value;
            second[index] = value;
            expected[index] = value;
        }
        check("두 배열 길이 상한", first, second, expected);
    }

    private static void check(String name, int[] first, int[] second, int[] expected) {
        int[] originalFirst = first.clone();
        int[] originalSecond = second.clone();
        int[] actual = TwoPointerSolution01.solve(first, second);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalFirst, first, "첫 배열 원본을 보존해야 한다"),
                () -> assertArrayEquals(originalSecond, second, "둘째 배열 원본을 보존해야 한다"),
                () -> assertNotSame(first, actual, "첫 배열과 다른 결과 배열을 반환해야 한다"),
                () -> assertNotSame(second, actual, "둘째 배열과 다른 결과 배열을 반환해야 한다")
        );
    }
}

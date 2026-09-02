package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution01;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("백트래킹 01 - 모든 선택의 합 만들기")
final class BacktrackingSolution01Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * points.length | 0 이상 15 이하 | 0, 1, 3, 15
     * points[i] | -1,000 이상 1,000 이하 | -1,000, 0, 940, 1,000
     * 결과 길이 | 2^points.length | 1, 2, 8, 32,768
     * 추가 계약 | 위치별 선택, 선택 가지 우선, 원본 보존 | 중복 합, 순서, 원본 배열
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("selectionCases")
    @DisplayName("선택 순서와 값 경계")
    void createsAllSelectionSums(String name, int[] points, int[] expected) {
        int[] original = points.clone();

        int[] actual = BacktrackingSolution01.solve(points);

        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, points),
                () -> assertNotSame(points, actual, "결과는 입력과 다른 배열이어야 한다.")
        );
    }

    static Stream<Arguments> selectionCases() {
        return Stream.of(
                Arguments.of("두 위치의 선택 순서", new int[]{2, 5}, new int[]{7, 2, 5, 0}),
                Arguments.of("빈 입력은 합 0 한 가지", new int[]{}, new int[]{0}),
                Arguments.of("원소 하나와 값 하한", new int[]{-1_000}, new int[]{-1_000, 0}),
                Arguments.of(
                        "0·중간값·상한과 위치별 중복 합",
                        new int[]{0, 940, 1_000},
                        new int[]{1_940, 940, 1_000, 0, 1_940, 940, 1_000, 0}
                )
        );
    }

    @Test
    @DisplayName("최대 길이의 모든 선택 수")
    void handlesMaximumLength() {
        int[] points = new int[15];
        Arrays.fill(points, 1);
        int[] original = points.clone();

        int[] actual = BacktrackingSolution01.solve(points);

        int[] expectedFrequency = {
                1, 15, 105, 455, 1_365, 3_003, 5_005, 6_435,
                6_435, 5_005, 3_003, 1_365, 455, 105, 15, 1
        };
        int[] actualFrequency = new int[16];
        for (int sum : actual) {
            if (0 <= sum && sum <= 15) {
                actualFrequency[sum]++;
            }
        }

        assertAll(
                () -> {
                    for (int sum : actual) {
                        assertTrue(0 <= sum && sum <= 15, "최대 길이 결과 합의 범위가 잘못됐다: " + sum);
                    }
                },
                () -> assertEquals(32_768, actual.length),
                () -> assertEquals(15, actual[0]),
                () -> assertEquals(0, actual[actual.length - 1]),
                () -> assertArrayEquals(expectedFrequency, actualFrequency),
                () -> assertArrayEquals(original, points)
        );
    }
}

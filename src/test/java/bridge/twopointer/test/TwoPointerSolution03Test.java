package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class TwoPointerSolution03Test {

    /*
     * 매개변수 | 허용 범위
     * amounts.length | 0 이상 100,000 이하
     * amounts[i] | 0 이상 1,000,000,000 이하
     * target | 1 이상 100,000,000,000,000 이하
     * 대표 오답 | 왼쪽 한 번만 축소, 마지막 값 누락, 같은 길이의 뒤 구간 선택, 0·1 기반 혼동, int 넘침
     */
    @DisplayName("목표 합 이상인 가장 짧은 구간을 찾는다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testShortestRange(String name, int[] amounts, long target, int[] expected) {
        check(name, amounts, target, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("여러 번 줄여 가장 짧은 구간",
                        new int[]{2, 1, 5, 2, 3, 2}, 7L, new int[]{3, 4}),
                arguments("빈 배열", new int[0], 1L, new int[0]),
                arguments("조건을 만족하는 구간 없음", new int[]{1, 2}, 10L, new int[0]),
                arguments("원소 하나와 940", new int[]{940}, 940L, new int[]{1, 1}),
                arguments("0과 같은 길이의 앞선 구간",
                        new int[]{0, 5, 0, 5}, 5L, new int[]{2, 2}),
                arguments("같은 오른쪽 끝에서 여러 번 축소",
                        new int[]{1, 2, 3, 4}, 6L, new int[]{3, 4}),
                arguments("값 상한·0·940과 long 합",
                        new int[]{1_000_000_000, 0, 940, 1_000_000_000},
                        2_000_000_940L, new int[]{1, 4})
        );
    }

    @Test
    @DisplayName("길이와 목표 상한을 처리한다")
    void testMaximumLengthAndTarget() {
        int[] amounts = new int[100_000];
        Arrays.fill(amounts, 1_000_000_000);
        check(
                "길이와 target 상한",
                amounts,
                100_000_000_000_000L,
                new int[]{1, 100_000}
        );
    }

    private static void check(String name, int[] amounts, long target, int[] expected) {
        int[] original = amounts.clone();
        int[] actual = TwoPointerSolution03.solve(amounts, target);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, amounts, "입력 배열 원본을 보존해야 한다"),
                () -> assertNotSame(amounts, actual, "입력 배열과 다른 결과 배열을 반환해야 한다")
        );
    }
}

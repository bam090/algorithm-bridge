package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution04;
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
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class GreedySolution04Test {

    /*
     * 범위표
     * - weights 길이: 0, 1, 홀수·짝수 일반 길이, 최대 100,000
     * - 무게: 0, 940, 상한 1,000,000,000
     * - 합 범위: 하한·상한과 같은 합, 하한 미만, 상한 초과, 상한 2,000,000,000
     * - 대표 오답: 낮은 합에서 오른쪽 이동, 높은 합에서 왼쪽 이동, 추 재사용, 원본 정렬
     */
    @DisplayName("하한과 상한 사이의 합을 만드는 최대 쌍을 센다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testMaximumPairs(String name, int[] weights, int lowerBound, int upperBound, int expected) {
        check(name, weights, lowerBound, upperBound, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("하한·상한 사이 최대 쌍과 원본 보존",
                        new int[]{10, 20, 40, 50, 70}, 60, 80, 2),
                arguments("빈 무게 배열", new int[]{}, 0, 0, 0),
                arguments("추 하나와 940 무게", new int[]{940}, 940, 1_880, 0),
                arguments("0·940과 하한·상한 포함",
                        new int[]{0, 940, 60, 1_000}, 940, 1_000, 2),
                arguments("합이 낮으면 가벼운 쪽 이동", new int[]{1, 2, 3, 9}, 10, 10, 1),
                arguments("합이 높으면 무거운 쪽 이동", new int[]{1, 8, 9, 10}, 10, 10, 1),
                arguments("같은 무게의 추는 한 번씩만 사용",
                        new int[]{5, 5, 5, 5, 5}, 10, 10, 2),
                arguments("무게와 합 범위 상한",
                        new int[]{0, 940, 1_000_000_000, 1_000_000_000},
                        1_000_000_000, 2_000_000_000, 2)
        );
    }

    @Test
    @DisplayName("최대 100000개 무게를 처리하고 원본을 보존한다")
    void testMaximumWeights() {
        int[] input = new int[100_000];
        Arrays.fill(input, 940);
        check("최대 100000개와 원본 보존", input, 1_880, 1_880, 50_000);
    }

    private static void check(String name, int[] weights, int lowerBound, int upperBound, int expected) {
        int[] before = weights.clone();
        int actual = GreedySolution04.solve(weights, lowerBound, upperBound);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(before, weights, "입력 무게 배열을 보존해야 한다")
        );
    }
}

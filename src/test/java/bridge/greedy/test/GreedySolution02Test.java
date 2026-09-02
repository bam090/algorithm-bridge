package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution02;
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

public final class GreedySolution02Test {

    /*
     * 범위표
     * - kitCosts 길이: 0, 1, 일반 길이, 최대 100,000
     * - 가격: 0, 940, 상한 1,000,000,000
     * - requiredCount: 0, 1, 일반 중간값, kitCosts.length 전체
     * - 대표 오답: 배열 전체 합산, 비싼 값 우선, requiredCount 0 처리, int 누적, 원본 정렬
     */
    @DisplayName("필요한 개수만큼 가장 싼 키트를 선택한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testMinimumCost(String name, int[] kitCosts, int requiredCount, long expected) {
        check(name, kitCosts, requiredCount, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("필요한 개수의 싼 키트와 원본 보존",
                        new int[]{7_000, 2_000, 4_000, 1_000}, 3, 7_000L),
                arguments("빈 배열에서 0개 선택", new int[]{}, 0, 0L),
                arguments("값이 있어도 0개 선택", new int[]{0, 940, 1_000_000_000}, 0, 0L),
                arguments("0·940·가격 상한을 모두 선택",
                        new int[]{1_000_000_000, 940, 0}, 3, 1_000_000_940L),
                arguments("한 개만 고르면 최솟값", new int[]{6, 4, 5}, 1, 4L)
        );
    }

    @Test
    @DisplayName("최대 100000개 비용을 long으로 누적한다")
    void testMaximumCosts() {
        int[] input = new int[100_000];
        Arrays.fill(input, 1_000_000_000);
        check("최대 100000개에서 long 누적", input, 100_000, 100_000_000_000_000L);
    }

    private static void check(String name, int[] kitCosts, int requiredCount, long expected) {
        int[] before = kitCosts.clone();
        long actual = GreedySolution02.solve(kitCosts, requiredCount);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(before, kitCosts, "입력 비용 배열을 보존해야 한다")
        );
    }
}

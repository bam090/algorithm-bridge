package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution01;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class GreedySolution01Test {

    /*
     * 범위표
     * - reservations 길이: 0, 1, 일반 길이, 최대 100,000
     * - 예약 번호: -1,000,000, 940, 1,000,000
     * - 시작·종료: 0, 맞닿는 경계, 940을 포함한 중간값, 종료 상한 1,000,000,000
     * - 대표 오답: 시작 시각 우선, 가장 짧은 길이 우선, start > lastEnd, 동점 누락, 원본 정렬
     */
    @DisplayName("종료 시각을 기준으로 예약을 선택한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testReservations(String name, int[][] reservations, int[] expected) {
        check(name, reservations, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("종료 시각 우선 선택과 원본 보존", new int[][]{
                        {101, 0, 4}, {102, 1, 2}, {103, 2, 3}, {104, 3, 5}
                }, new int[]{102, 103, 104}),
                arguments("빈 예약 목록", new int[][]{}, new int[]{}),
                arguments("원소 하나와 시각 상한",
                        new int[][]{{940, 999_999_999, 1_000_000_000}}, new int[]{940}),
                arguments("가장 짧은 예약 우선의 반례", new int[][]{
                        {1, 0, 4}, {2, 4, 8}, {3, 3, 5}
                }, new int[]{1, 2}),
                arguments("종료와 시작이 같으면 연속 선택", new int[][]{
                        {30, 940, 1_000}, {10, 0, 100}, {20, 100, 940}
                }, new int[]{10, 20, 30}),
                arguments("종료·시작 동점에서 번호 오름차순", new int[][]{
                        {940, 0, 2}, {-1_000_000, 0, 2}, {1_000_000, 2, 3}
                }, new int[]{-1_000_000, 1_000_000}),
                arguments("긴 첫 예약을 건너뛰고 짧은 예약 여러 개 선택", new int[][]{
                        {1, 0, 1_000}, {2, 100, 200}, {3, 200, 300}, {4, 300, 400}
                }, new int[]{2, 3, 4})
        );
    }

    @Test
    @DisplayName("최대 100000개의 연속 예약을 선택한다")
    void testMaximumReservations() {
        int size = 100_000;
        int[][] input = new int[size][3];
        int[] expected = new int[size];
        for (int index = 0; index < size; index++) {
            input[index][0] = index;
            input[index][1] = index * 2;
            input[index][2] = index * 2 + 1;
            expected[index] = index;
        }
        check("최대 100000개 연속 예약", input, expected);
    }

    private static void check(String name, int[][] reservations, int[] expected) {
        int[][] before = deepCopy(reservations);
        int[] actual = GreedySolution01.solve(reservations);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(before, reservations, "입력 예약 배열을 보존해야 한다")
        );
    }

    private static int[][] deepCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int index = 0; index < source.length; index++) {
            copy[index] = source[index].clone();
        }
        return copy;
    }
}

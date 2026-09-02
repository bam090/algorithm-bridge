package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution06;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class GreedySolution06Test {

    /*
     * 범위표
     * - roadLength: 0, 940을 경계로 둔 일반값, 상한 1,000,000,000
     * - coveredIntervals 길이: 0, 1, 겹치고 정렬되지 않은 일반 길이, 최대 100,000
     * - patchWidth: 1, 일반값, 상한 1,000,000,000
     * - 대표 오답: 포함 구간 end+1 누락, 겹친 구간에서 뒤로 이동, 한 칸 틈, 위치별 배열 생성, 원본 정렬
     */
    @DisplayName("이미 덮인 구간을 건너뛰며 빈 위치를 덮는다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testPatchStarts(
            String name,
            int roadLength,
            int[][] coveredIntervals,
            int patchWidth,
            int[] expected
    ) {
        check(name, roadLength, coveredIntervals, patchWidth, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("기존 구간 사이의 빈 위치 덮기와 원본 보존",
                        15, new int[][]{{4, 6}, {11, 12}}, 3, new int[]{0, 3, 7, 10, 13}),
                arguments("길이 0 산책로", 0, new int[][]{}, 1, new int[]{}),
                arguments("기존 구간이 없으면 폭만큼 전진",
                        10, new int[][]{}, 3, new int[]{0, 3, 6, 9}),
                arguments("길이 상한 전체가 이미 덮인 경우",
                        1_000_000_000, new int[][]{{0, 999_999_999}}, 1_000_000_000, new int[]{}),
                arguments("정렬되지 않고 겹친 기존 구간",
                        20, new int[][]{{10, 12}, {2, 5}, {4, 8}}, 3,
                        new int[]{0, 9, 13, 16, 19}),
                arguments("첫 위치와 마지막 위치의 포함 경계",
                        5, new int[][]{{0, 0}, {4, 4}}, 2, new int[]{1, 3}),
                arguments("폭 1은 빈 위치마다 설치",
                        4, new int[][]{{1, 1}}, 1, new int[]{0, 2, 3}),
                arguments("940 다음의 마지막 구간을 한 번에 덮기",
                        1_000, new int[][]{{0, 939}}, 60, new int[]{940}),
                arguments("덮개 폭 상한은 산책로 전체를 한 번에 덮음",
                        940, new int[][]{}, 1_000_000_000, new int[]{0})
        );
    }

    @Test
    @DisplayName("최대 100000개의 기존 구간 사이를 덮는다")
    void testMaximumIntervals() {
        int size = 100_000;
        int[][] intervals = new int[size][2];
        int[] expected = new int[size];
        for (int index = 0; index < size; index++) {
            intervals[index][0] = index * 10;
            intervals[index][1] = index * 10 + 4;
            expected[index] = index * 10 + 5;
        }
        check("최대 100000개 기존 구간과 새 덮개", 1_000_000, intervals, 5, expected);
    }

    private static void check(
            String name,
            int roadLength,
            int[][] coveredIntervals,
            int patchWidth,
            int[] expected
    ) {
        int[][] before = deepCopy(coveredIntervals);
        int[] actual = GreedySolution06.solve(roadLength, coveredIntervals, patchWidth);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(before, coveredIntervals, "입력 구간 배열을 보존해야 한다")
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

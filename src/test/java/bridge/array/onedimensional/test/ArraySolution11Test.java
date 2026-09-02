package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 ArraySolution11을 검증한다. */
public final class ArraySolution11Test {

    /*
     * 매개변수 | 허용 범위
     * observations | null이 아니며 길이는 0 이상 1,000 이하
     * cycles | null이 아니며 점검표 개수는 1 이상 50 이하
     * cycles의 각 점검표 | null이 아니며 길이는 1 이상 50 이하
     * observations와 cycles의 각 코드 | -1,000 이상 1,000 이하
     * minimumMatches | 0 이상 observations.length 이하
     * 원본 보존 | solve() 실행 뒤 observations와 cycles의 내용이 같아야 함
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("cycleCases")
    @DisplayName("기준 이상 일치한 점검표 번호를 반환한다")
    void findsMatchingCycles(String name, int[] expected, int[] observations,
            int[][] cycles, int minimumMatches) {
        verify(expected, observations, cycles, minimumMatches);
    }

    @Test
    @DisplayName("관찰·점검표 개수·점검표 길이 상한과 940")
    void test06() {
        int[] observations = new int[1_000];
        Arrays.fill(observations, 940);

        int[][] cycles = new int[50][50];
        for (int[] cycle : cycles) {
            Arrays.fill(cycle, 940);
        }

        int[] expected = new int[50];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i + 1;
        }
        verify(expected, observations, cycles, 1_000);
    }

    private static Stream<Arguments> cycleCases() {
        return Stream.of(
                Arguments.of("서로 다른 길이의 반복 점검표", new int[]{1},
                        new int[]{2, 4, 2, 4, 2, 5}, new int[][]{{2, 4}, {2, 5, 2}, {4}}, 4),
                Arguments.of("빈 관찰과 기준 0이면 모든 점검표 통과", new int[]{1, 2},
                        new int[]{}, new int[][]{{0}, {940, -1_000}}, 0),
                Arguments.of("기준과 같은 개수도 통과", new int[]{1, 2, 3},
                        new int[]{1, 2, 1, 2, 1, 2}, new int[][]{{1, 2}, {1}, {2}}, 3),
                Arguments.of("하한·0·940·상한과 반복 시작점", new int[]{1, 3},
                        new int[]{-1_000, 0, 940, 1_000, -1_000},
                        new int[][]{{-1_000, 0, 940, 1_000}, {940}, {-1_000, 0, 940, 1_000, -1_000}}, 4),
                Arguments.of("원소 하나와 점검표 개수 하한에서 미통과", new int[]{},
                        new int[]{940}, new int[][]{{0}}, 1)
        );
    }

    private static void verify(int[] expected, int[] observations, int[][] cycles, int minimumMatches) {
        int[] originalObservations = observations.clone();
        int[][] originalCycles = copyOf(cycles);
        int[] actual = ArraySolution11.solve(observations, cycles, minimumMatches);

        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalObservations, observations,
                        "원본 observations가 변경되었습니다."),
                () -> assertRowsEqual(originalCycles, cycles, "원본 cycles가 변경되었습니다.")
        );
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual, String message) {
        assertEquals(expected.length, actual.length, message);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], message + " row=" + row);
        }
    }

    private static int[][] copyOf(int[][] input) {
        int[][] copy = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            copy[i] = input[i].clone();
        }
        return copy;
    }
}

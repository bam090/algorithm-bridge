package bridge.stack.test;

import bridge.stack.solution.StackSolution05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 StackSolution05를 검증한다. */
public final class StackSolution05Test {

    /*
     * 테스트 범위
     * - sourceStacks 행: 하한 1, 상한 50, 빈 행, 카드 합 하한 0·상한 100,000
     * - 카드 값: 하한 -1,000, 0, 중간 940, 상한 1,000
     * - picks 길이: 하한 0, 빈 원본 선택, 상한 100,000
     * - cancelSum: 하한 -2,000, 일반값 10·1,940, 상한 2,000
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("stackCases")
    @DisplayName("원본 스택에서 꺼낸 카드의 상쇄 결과를 구한다")
    void processesCards(String name, int[] expected, int[][] sources, int[] picks, int cancelSum) {
        assertArrayEquals(expected, StackSolution05.solve(sources, picks, cancelSum), name);
    }

    @Test
    @DisplayName("카드 하한·0·중간·상한과 원본 보존")
    void test03() {
        int[][] sources = {{-1_000, 0, 940}, {1_000}};
        int[] picks = {1, 2, 1, 1};
        int[][] sourceBefore = cloneRows(sources);
        int[] picksBefore = picks.clone();

        int[] actual = StackSolution05.solve(sources, picks, 1_940);
        assertAll(
                () -> assertArrayEquals(new int[]{0, -1_000}, actual),
                () -> assertRowsEqual(sourceBefore, sources,
                        "원본 sourceStacks가 변경되었습니다."),
                () -> assertArrayEquals(picksBefore, picks));
    }

    @Test
    @DisplayName("행 50개와 전체 카드 수 0")
    void test07() {
        int[][] emptySources = new int[50][];
        for (int row = 0; row < emptySources.length; row++) {
            emptySources[row] = new int[]{};
        }
        assertArrayEquals(
                new int[]{},
                StackSolution05.solve(emptySources, new int[]{1, 50, 1, 50}, 0)
        );
    }

    @Test
    @DisplayName("행·행 길이·카드 합·지시 길이와 상쇄 합 상한")
    void test08() {
        int[][] sources = new int[50][2_000];
        int[] picks = new int[100_000];

        for (int row = 0; row < sources.length; row++) {
            Arrays.fill(sources[row], 1_000);
            for (int count = 0; count < sources[row].length; count++) {
                picks[row * 2_000 + count] = row + 1;
            }
        }

        // 각 행의 2,000장은 1,000 두 장씩 상쇄되므로 모든 지시 뒤 결과는 빈 배열이다.
        assertArrayEquals(new int[]{}, StackSolution05.solve(sources, picks, 2_000));
    }

    private static Stream<Arguments> stackCases() {
        return Stream.of(
                Arguments.of("여러 원본에서 연속 상쇄 후 한 장 남음", new int[]{7},
                        new int[][]{{4, -3}, {2, 3}, {-4, -2}, {7}},
                        new int[]{1, 2, 1, 2, 3, 3, 4}, 0),
                Arguments.of("0이 아닌 상쇄 합", new int[]{},
                        new int[][]{{1, 4}, {6}, {9}}, new int[]{1, 2, 1, 3}, 10),
                Arguments.of("지시가 없으면 빈 결과", new int[]{},
                        new int[][]{{1, 2, 3}}, new int[]{}, 0),
                Arguments.of("빈 원본 건너뛰기와 아래에서 위 순서", new int[]{3, 5, 2},
                        new int[][]{{1, 2, 3}, {}, {4, 5}}, new int[]{2, 1, 3, 1}, 100),
                Arguments.of("상쇄 합 하한", new int[]{},
                        new int[][]{{-1_000, -1_000}}, new int[]{1, 1}, -2_000)
        );
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual, String message) {
        assertEquals(expected.length, actual.length, message);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], message + " row=" + row);
        }
    }

    private static int[][] cloneRows(int[][] values) {
        int[][] copy = new int[values.length][];
        for (int row = 0; row < values.length; row++) {
            copy[row] = values[row].clone();
        }
        return copy;
    }

}

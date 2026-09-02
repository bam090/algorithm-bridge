package bridge.tree.test;

import bridge.tree.solution.TreeSolution02;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("트리 02 - 방문 시점에 따른 순번 찾기")
final class TreeSolution02Test {

    /*
     * 테스트 범위
     * - values 길이: 하한 1, 불완전한 마지막 층, 상한 100,000
     * - 값: 하한 -1,000,000, 0, 중간 940, 상한 1,000,000
     * - visitMoment: BEFORE, BETWEEN, AFTER
     * - target 위치: 뿌리, 왼쪽·오른쪽 자식, 마지막 기록과 원본 보존
     */
    @Test
    @DisplayName("BEFORE 방문 순번과 원본 보존")
    void findsBeforeOrderWithoutChangingInput() {
        int[] values = {10, 20, 30, 40, 50, 60, 70};
        int[] before = values.clone();

        int actual = TreeSolution02.solve(values, 50, "BEFORE");

        assertAll(
                () -> assertEquals(4, actual),
                () -> assertArrayEquals(before, values)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("visitCases")
    @DisplayName("방문 시점과 입력 경계")
    void findsVisitOrder(String name, int[] values, int target, String visitMoment, int expected) {
        assertEquals(expected, TreeSolution02.solve(values, target, visitMoment));
    }

    static Stream<Arguments> visitCases() {
        int[] sample = {10, 20, 30, 40, 50, 60, 70};
        return Stream.of(
                Arguments.of("BETWEEN 방문 순번", sample, 50, "BETWEEN", 3),
                Arguments.of("AFTER 방문 순번", sample, 50, "AFTER", 2),
                Arguments.of("길이 하한과 값 0", new int[]{0}, 0, "BETWEEN", 1),
                Arguments.of(
                        "값 하한·0·중간·상한",
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        940,
                        "BEFORE",
                        4
                ),
                Arguments.of(
                        "마지막 층에 오른쪽 자식이 없는 트리",
                        new int[]{1, 2, 3, 4, 5, 6},
                        6,
                        "AFTER",
                        4
                )
        );
    }

    @Test
    @DisplayName("최대 길이에서 AFTER의 뿌리는 마지막")
    void placesRootLastAtMaximumLengthForAfter() {
        int[] values = new int[100_000];
        for (int index = 0; index < values.length; index++) {
            values[index] = index - 50_000;
        }

        assertEquals(100_000, TreeSolution02.solve(values, values[0], "AFTER"));
    }
}

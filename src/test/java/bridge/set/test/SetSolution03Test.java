package bridge.set.test;

import bridge.set.solution.SetSolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("집합 03 - 연결 구역 수 세기")
final class SetSolution03Test {

    /*
     * 검증 범위
     * 항목        | 제약                  | 실제 확인값
     * itemCount   | 0~100,000             | 0·1·4·6·1,000·100,000
     * connections | 길이 0~100,000        | 0·1·3·4·5·100,000개
     * 작업대 번호 | 0~itemCount-1         | 0·940·999·99,999
     * 대표 오답   | 자기·중복 연결에서 중복 감소, 최종 대표 미확인, 고립 구역 누락, 원본 변경
     */
    @Test
    @DisplayName("세 연결로 세 구역 남기기와 원본 보존")
    void countsAreasWithoutChangingConnections() {
        int[][] connections = {{4, 2}, {3, 1}, {2, 1}};
        int[][] before = copy(connections);

        int actual = SetSolution03.solve(6, connections);

        assertAll(
                () -> assertEquals(3, actual),
                () -> assertRowsEqual(before, connections)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("connectionCases")
    @DisplayName("연결 모양과 개수 경계")
    void countsAreas(String name, int itemCount, int[][] connections, int expected) {
        assertEquals(expected, SetSolution03.solve(itemCount, connections));
    }

    static Stream<Arguments> connectionCases() {
        return Stream.of(
                Arguments.of("작업대와 연결이 모두 없음", 0, new int[][]{}, 0),
                Arguments.of("작업대 하나의 자기 연결", 1, new int[][]{{0, 0}}, 1),
                Arguments.of(
                        "중복·반대 방향·자기 연결은 다시 줄이지 않음",
                        4,
                        new int[][]{{0, 1}, {1, 0}, {1, 1}, {2, 3}, {0, 1}},
                        2
                ),
                Arguments.of(
                        "바로 위 부모가 달라도 같은 구역인 중복 연결",
                        4,
                        new int[][]{{0, 1}, {2, 3}, {1, 3}, {0, 3}},
                        1
                )
        );
    }

    @Test
    @DisplayName("작업대 0·940·999를 한 구역으로 연결")
    void handlesIntermediateItemNumbersWithoutChangingInput() {
        int[][] connections = {{0, 940}, {940, 999}};
        int[][] before = copy(connections);

        int actual = SetSolution03.solve(1_000, connections);

        assertAll(
                () -> assertEquals(998, actual),
                () -> assertRowsEqual(before, connections)
        );
    }

    @Test
    @DisplayName("최대 작업대와 연결 수의 긴 연결")
    void handlesMaximumCounts() {
        int itemCount = 100_000;
        int[][] connections = new int[100_000][2];
        for (int i = 0; i < itemCount - 1; i++) {
            connections[i][0] = i;
            connections[i][1] = i + 1;
        }
        connections[connections.length - 1][0] = itemCount - 1;
        connections[connections.length - 1][1] = itemCount - 1;

        assertEquals(1, SetSolution03.solve(itemCount, connections));
    }

    private static int[][] copy(int[][] original) {
        int[][] copied = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copied[i] = original[i].clone();
        }
        return copied;
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row=" + row);
        }
    }
}

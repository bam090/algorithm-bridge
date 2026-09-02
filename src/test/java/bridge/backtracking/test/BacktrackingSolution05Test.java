package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution05;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@DisplayName("백트래킹 05 - 시간 한도 안에서 최선의 배정 찾기")
final class BacktrackingSolution05Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 프로젝트 수 | 1 이상 8 이하 | 1, 2, 8
     * 프로젝트별 시간 후보 수 | 1 이상 6 이하 | 1, 2, 3, 6
     * pointsByHours 값 | 0 이상 1,000 이하 | 0, 940, 1,000
     * hourLimit | 0 이상 12 이하 | 0, 1, 2, 12
     * 추가 계약 | 최고 점수, 적은 사용 시간, 앞에서 작은 배정, 결과 복사, 원본 보존 | 각 동점과 최대 탐색
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("assignmentCases")
    @DisplayName("점수·시간 경계와 동점 규칙")
    void findsBestAssignment(String name, int[][] pointsByHours, int hourLimit, int[] expected) {
        int[][] original = cloneMatrix(pointsByHours);

        int[] actual = BacktrackingSolution05.solve(pointsByHours, hourLimit);

        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertRowsEqual(original, pointsByHours),
                () -> {
                    for (int[] row : pointsByHours) {
                        assertNotSame(row, actual, "결과는 입력 행과 다른 배열이어야 한다.");
                    }
                }
        );
    }

    static Stream<Arguments> assignmentCases() {
        return Stream.of(
                Arguments.of(
                        "두 프로젝트의 최고 점수",
                        new int[][]{{0, 4, 7}, {0, 5, 6}}, 2, new int[]{1, 1}
                ),
                Arguments.of(
                        "시간 한도 0과 점수 중간값·상한",
                        new int[][]{{0, 940}, {0, 1_000}}, 0, new int[]{0, 0}
                ),
                Arguments.of("점수가 같으면 더 적은 시간 사용", new int[][]{{0, 10, 10}}, 2, new int[]{1}),
                Arguments.of(
                        "점수와 시간도 같으면 앞 프로젝트 시간을 작게",
                        new int[][]{{0, 10}, {0, 10}}, 1, new int[]{0, 1}
                ),
                Arguments.of("배정할 수 없는 남은 시간은 사용하지 않음", new int[][]{{0}, {0}}, 12, new int[]{0, 0}),
                Arguments.of(
                        "점수 상한 동률에서 배열 순서 적용",
                        new int[][]{{0, 940, 1_000}, {0, 0, 1_000}}, 2, new int[]{0, 2}
                )
        );
    }

    @Test
    @DisplayName("최대 프로젝트·후보·시간 한도")
    void handlesMaximumDimensions() {
        int[][] points = new int[8][6];
        for (int project = 0; project < points.length; project++) {
            for (int hours = 0; hours < points[project].length; hours++) {
                points[project][hours] = hours;
            }
        }

        assertArrayEquals(
                new int[]{0, 0, 0, 0, 0, 2, 5, 5},
                BacktrackingSolution05.solve(points, 12)
        );
    }

    private static int[][] cloneMatrix(int[][] matrix) {
        int[][] clone = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            clone[row] = matrix[row].clone();
        }
        return clone;
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row=" + row);
        }
    }
}

package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution04;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("백트래킹 04 - 담당자와 도구가 겹치지 않는 일정 찾기")
final class BacktrackingSolution04Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 날짜 수 | 1 이상 7 이하 | 1, 2, 3, 7
     * 날짜별 후보 수 | 1 이상 7 이하 | 1, 2, 7
     * 담당자·도구 ID | 0 이상 1,000 이하 | 0, 940, 1,000
     * 추가 계약 | 날짜마다 하나, 담당자·도구 각각 중복 금지, 원본 보존 | 한쪽 충돌, 선택 취소, 최대 크기
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("scheduleCases")
    @DisplayName("충돌 조건과 ID 경계")
    void determinesWhetherScheduleExists(
            String name,
            int[][] workerIds,
            int[][] toolIds,
            boolean expected
    ) {
        int[][] originalWorkers = cloneMatrix(workerIds);
        int[][] originalTools = cloneMatrix(toolIds);

        boolean actual = BacktrackingSolution04.solve(workerIds, toolIds);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertRowsEqual(originalWorkers, workerIds),
                () -> assertRowsEqual(originalTools, toolIds)
        );
    }

    static Stream<Arguments> scheduleCases() {
        return Stream.of(
                Arguments.of("날짜 1개·후보 1개의 실제 하한", new int[][]{{0}}, new int[][]{{1_000}}, true),
                Arguments.of(
                        "두 조건을 지키는 일정",
                        new int[][]{{1, 2}, {1, 3}},
                        new int[][]{{10, 10}, {11, 10}},
                        true
                ),
                Arguments.of("담당자만 겹치는 일정", new int[][]{{940}, {940}}, new int[][]{{0}, {1_000}}, false),
                Arguments.of("도구만 겹치는 일정", new int[][]{{0}, {1_000}}, new int[][]{{940}, {940}}, false),
                Arguments.of(
                        "첫 후보 실패 뒤 표시를 지우고 성공",
                        new int[][]{{0, 1}, {0}},
                        new int[][]{{0, 1}, {2}},
                        true
                ),
                Arguments.of(
                        "후보보다 날짜가 많아 완성 불가",
                        new int[][]{{1, 2}, {1, 2}, {1, 2}},
                        new int[][]{{1, 2}, {2, 1}, {1, 2}},
                        false
                ),
                Arguments.of(
                        "ID 하한·중간값·상한",
                        new int[][]{{0}, {940}, {1_000}},
                        new int[][]{{1_000}, {940}, {0}},
                        true
                )
        );
    }

    @Test
    @DisplayName("최대 날짜와 후보 수")
    void handlesMaximumDimensions() {
        int size = 7;
        int[][] workers = new int[size][size];
        int[][] tools = new int[size][size];
        for (int day = 0; day < size; day++) {
            for (int candidate = 0; candidate < size; candidate++) {
                workers[day][candidate] = candidate;
                tools[day][candidate] = (candidate + day) % size;
            }
        }

        assertEquals(true, BacktrackingSolution04.solve(workers, tools));
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

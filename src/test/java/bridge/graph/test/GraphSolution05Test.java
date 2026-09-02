package bridge.graph.test;

import bridge.graph.solution.GraphSolution05;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("그래프 05 - 점검소를 거친 목적지 거리 구하기")
final class GraphSolution05Test {

    /*
     * 테스트 범위
     * - nodeCount: 하한 1, 중간 1,000, 상한 100,000
     * - directedRoutes 길이: 하한 0, 중복·자기 간선, 상한 200,000
     * - destinations 길이: 하한 0, 중복 목적지, 상한 100,000
     * - 거리: 0, 중간 940, 최대 일렬 경로 99,999, 도달 불가 -1
     * - 구간: start=checkpoint, 점검소 미도달, 점검소 이후 일부 목적지만 미도달
     * - 결과: 목적지 입력 순서와 모든 입력 배열 보존
     *
     * 대표 오답
     * - 점검소를 거치지 않은 출발점→목적지 거리를 사용한다.
     * - 첫 BFS의 방문 상태를 둘째 BFS에서 재사용한다.
     * - 목적지마다 BFS를 다시 하거나 단방향 길을 양방향으로 넣는다.
     * - 점검소 전에 지난 목적지를 점검소 뒤에도 갈 수 있다고 판단한다.
     */
    @Test
    @DisplayName("점검소 이후 여러 목적지와 원본 보존")
    void calculatesRoutesViaCheckpointWithoutChangingInputs() {
        int[][] routes = {{1, 2}, {2, 3}, {1, 4}, {4, 3}, {3, 5}, {5, 6}, {3, 7}};
        int[] destinations = {3, 5, 6, 2, 7};
        int[][] routesBefore = cloneRows(routes);
        int[] destinationsBefore = destinations.clone();

        int[] actual = GraphSolution05.solve(7, routes, 1, 3, destinations);

        assertAll(
                () -> assertArrayEquals(new int[]{2, 3, 4, -1, 3}, actual),
                () -> assertRowsEqual(routesBefore, routes),
                () -> assertArrayEquals(destinationsBefore, destinations)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("routeCases")
    @DisplayName("경유지와 목적지 경계")
    void calculatesRoutesViaCheckpoint(
            String name,
            int nodeCount,
            int[][] routes,
            int start,
            int checkpoint,
            int[] destinations,
            int[] expected
    ) {
        assertArrayEquals(
                expected,
                GraphSolution05.solve(nodeCount, routes, start, checkpoint, destinations)
        );
    }

    static Stream<Arguments> routeCases() {
        return Stream.of(
                Arguments.of(
                        "정점 하나·간선 하한과 두 구간 거리 0",
                        1, new int[][]{}, 1, 1, new int[]{1, 1}, new int[]{0, 0}
                ),
                Arguments.of(
                        "출발점에서 점검소에 도달 불가",
                        4,
                        new int[][]{{1, 2}, {3, 4}},
                        1,
                        3,
                        new int[]{3, 4, 1},
                        new int[]{-1, -1, -1}
                ),
                Arguments.of(
                        "점검소 전에 지난 목적지로 돌아갈 수 없음",
                        4,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}},
                        1,
                        3,
                        new int[]{2, 3, 4},
                        new int[]{-1, 2, 3}
                ),
                Arguments.of(
                        "목적지 길이 하한 0",
                        2, new int[][]{{1, 2}}, 1, 2, new int[]{}, new int[]{}
                )
        );
    }

    @Test
    @DisplayName("거리 940·중복 목적지와 자기 간선")
    void handlesIntermediateDistanceAndDuplicateDestinations() {
        int nodeCount = 1_000;
        int[][] routes = makeDirectedChain(nodeCount, nodeCount + 1);

        assertArrayEquals(
                new int[]{0, 940, 940, 999},
                GraphSolution05.solve(nodeCount, routes, 1, 1, new int[]{1, 941, 941, 1_000})
        );
    }

    @Test
    @DisplayName("정점·간선 상한과 최대 일렬 거리")
    void handlesMaximumCountsAndDistance() {
        int nodeCount = 100_000;
        int[][] routes = makeDirectedChain(nodeCount, 200_000);
        int[] destinations = new int[100_000];
        int[] expected = new int[100_000];
        int[] repeatedDestinations = {50_000, 100_000, 940, 50_000};
        int[] repeatedExpected = {49_999, 99_999, -1, 49_999};
        for (int index = 0; index < destinations.length; index++) {
            destinations[index] = repeatedDestinations[index % repeatedDestinations.length];
            expected[index] = repeatedExpected[index % repeatedExpected.length];
        }

        assertArrayEquals(
                expected,
                GraphSolution05.solve(nodeCount, routes, 1, 50_000, destinations)
        );
    }

    private static int[][] makeDirectedChain(int nodeCount, int edgeCount) {
        int[][] routes = new int[edgeCount][2];
        int index = 0;
        for (int node = 1; node < nodeCount; node++) {
            routes[index++] = new int[]{node, node + 1};
        }
        while (index < routes.length) {
            routes[index++] = new int[]{1, 1};
        }
        return routes;
    }

    private static int[][] cloneRows(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static void assertRowsEqual(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row=" + row);
        }
    }
}

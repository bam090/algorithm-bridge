package bridge.graph.test;

import bridge.graph.solution.GraphSolution01;
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

@DisplayName("그래프 01 - 단방향으로 도달한 값 합치기")
final class GraphSolution01Test {

    /*
     * 테스트 범위
     * - nodeValues 길이: 하한 1, 일반 길이, 상한 100,000
     * - 값: 하한 -1,000,000, 0, 중간 940, 상한 1,000,000
     * - directedEdges 길이: 하한 0, 중복·자기 간선, 상한 200,000
     * - 시작 번호: 첫 번호와 마지막 번호
     * - 방향: 반대 방향으로는 도달하지 못함, 연결되지 않은 정점
     * - 결과: 출발점 포함, 중복 방문 방지, long 합계와 원본 보존
     *
     * 대표 오답
     * - 단방향 간선을 양방향으로 넣거나 지점 번호에서 1을 빼지 않는다.
     * - 중복·자기 간선에서 같은 값을 여러 번 더한다.
     * - 재귀 DFS로 최대 일렬 연결에서 StackOverflowError가 난다.
     * - 합을 int로 계산하거나 입력 배열을 바꾼다.
     */
    @Test
    @DisplayName("일반 단방향 도달·고립 정점과 원본 보존")
    void sumsReachableNodesWithoutChangingInputs() {
        int[] values = {10, 20, -5, 40, 7};
        int[][] edges = {{1, 2}, {1, 3}, {3, 4}};
        int[] valuesBefore = values.clone();
        int[][] edgesBefore = cloneRows(edges);

        long[] actual = GraphSolution01.solve(values, edges, 1);

        assertAll(
                () -> assertArrayEquals(new long[]{4, 65}, actual),
                () -> assertArrayEquals(valuesBefore, values),
                () -> assertRowsEqual(edgesBefore, edges)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("graphCases")
    @DisplayName("값·간선·방향 경계")
    void sumsReachableNodes(String name, int[] values, int[][] edges, int start, long[] expected) {
        assertArrayEquals(expected, GraphSolution01.solve(values, edges, start));
    }

    static Stream<Arguments> graphCases() {
        return Stream.of(
                Arguments.of(
                        "정점 하나·간선 하한과 중간값 940",
                        new int[]{940}, new int[][]{}, 1, new long[]{1, 940}
                ),
                Arguments.of(
                        "값 하한·0·중간·상한과 중복·자기 간선",
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        new int[][]{{1, 2}, {1, 2}, {2, 2}, {2, 3}, {4, 1}},
                        1,
                        new long[]{3, -999_060}
                ),
                Arguments.of(
                        "마지막 번호에서 시작해 정방향만 이동",
                        new int[]{1, 20, -1_000, 940},
                        new int[][]{{4, 3}, {3, 2}, {1, 4}},
                        4,
                        new long[]{3, -40}
                )
        );
    }

    @Test
    @DisplayName("정점·간선 상한과 long 누적")
    void handlesMaximumCountsAndLongSum() {
        int nodeCount = 100_000;
        int[] values = new int[nodeCount];
        Arrays.fill(values, 1_000_000);
        int[][] edges = new int[200_000][2];
        int edgeIndex = 0;
        for (int node = 1; node < nodeCount; node++) {
            edges[edgeIndex++] = new int[]{node, node + 1};
        }
        while (edgeIndex < edges.length) {
            edges[edgeIndex++] = new int[]{1, 1};
        }

        assertArrayEquals(
                new long[]{100_000, 100_000_000_000L},
                GraphSolution01.solve(values, edges, 1)
        );
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

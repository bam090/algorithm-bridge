package bridge.graph.test;

import bridge.graph.solution.GraphSolution02;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("그래프 02 - 무방향 그래프의 최단거리 구하기")
final class GraphSolution02Test {

    /*
     * 테스트 범위
     * - nodeCount: 하한 1, 중간 941, 상한 100,000
     * - undirectedEdges 길이: 하한 0, 중복·자기 간선, 상한 200,000
     * - 거리: 출발점 0, 일반 거리, 중간 940, 최대 일렬 거리 99,999, 미도달 -1
     * - 시작 번호: 첫 번호와 마지막 번호
     * - 결과: 직접 지름길과 긴 우회 경로의 구분, 간선 순서 독립성, 양방향 이동, 입력 원본 보존
     *
     * 대표 오답
     * - 무방향 간선을 한쪽에만 넣거나 출발 거리를 1로 시작한다.
     * - 큐에서 꺼낼 때 방문 표시해 중복 간선에서 같은 정점을 여러 번 넣는다.
     * - 긴 우회 경로를 먼저 방문한 DFS의 첫 거리를 최단거리로 확정한다.
     * - 간선 입력 순서에 따라 거리가 달라지거나 도달 불가능을 0으로 반환한다.
     */
    @Test
    @DisplayName("여러 경로 중 최소 거리와 원본 보존")
    void findsShortestDistancesWithoutChangingEdges() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 5}};
        int[][] before = cloneRows(edges);

        int[] actual = GraphSolution02.solve(6, edges, 1);

        assertAll(
                () -> assertArrayEquals(new int[]{0, 1, 1, 2, 3, -1}, actual),
                () -> assertRowsEqual(before, edges)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("distanceCases")
    @DisplayName("간선 모양과 시작점 경계")
    void findsShortestDistances(String name, int nodeCount, int[][] edges, int start, int[] expected) {
        assertArrayEquals(expected, GraphSolution02.solve(nodeCount, edges, start));
    }

    static Stream<Arguments> distanceCases() {
        return Stream.of(
                Arguments.of("정점 하나·간선 하한과 거리 0", 1, new int[][]{}, 1, new int[]{0}),
                Arguments.of(
                        "중복·자기 간선과 고립 정점",
                        4,
                        new int[][]{{1, 1}, {1, 2}, {1, 2}, {2, 3}, {3, 3}},
                        1,
                        new int[]{0, 1, 2, -1}
                ),
                Arguments.of(
                        "긴 우회보다 나중에 보이는 직접 지름길 선택",
                        6,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}},
                        1,
                        new int[]{0, 1, 2, 2, 1, -1}
                ),
                Arguments.of(
                        "간선 순서를 바꿔도 같은 최단거리",
                        6,
                        new int[][]{{1, 5}, {4, 5}, {3, 4}, {2, 3}, {1, 2}},
                        1,
                        new int[]{0, 1, 2, 2, 1, -1}
                ),
                Arguments.of(
                        "마지막 번호에서 양방향으로 이동",
                        4,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}},
                        4,
                        new int[]{3, 2, 1, 0}
                )
        );
    }

    @Test
    @DisplayName("중간 거리 940")
    void handlesIntermediateDistance() {
        int nodeCount = 941;
        int[][] edges = makeChainEdges(nodeCount, nodeCount - 1);
        int[] expected = new int[nodeCount];
        for (int index = 0; index < nodeCount; index++) {
            expected[index] = index;
        }

        assertArrayEquals(expected, GraphSolution02.solve(nodeCount, edges, 1));
    }

    @Test
    @DisplayName("정점·간선 상한과 최대 일렬 거리")
    void handlesMaximumCountsAndDistance() {
        int nodeCount = 100_000;
        int[][] edges = makeChainEdges(nodeCount, 200_000);
        int[] expected = new int[nodeCount];
        for (int index = 0; index < nodeCount; index++) {
            expected[index] = index;
        }

        assertArrayEquals(expected, GraphSolution02.solve(nodeCount, edges, 1));
    }

    private static int[][] makeChainEdges(int nodeCount, int edgeCount) {
        int[][] edges = new int[edgeCount][2];
        int index = 0;
        for (int node = 1; node < nodeCount; node++) {
            edges[index++] = new int[]{node, node + 1};
        }
        while (index < edges.length) {
            edges[index++] = new int[]{1, 1};
        }
        return edges;
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

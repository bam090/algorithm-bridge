package bridge.graph.test;

import bridge.graph.solution.GraphSolution04;
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

@DisplayName("그래프 04 - 연결 묶음 크기 기록하기")
final class GraphSolution04Test {

    /*
     * 테스트 범위
     * - nodeCount: 하한 1, 중간 940, 상한 100,000
     * - undirectedEdges 길이: 하한 0, 중복·자기 간선, 상한 200,000
     * - 묶음 모양: 모두 고립, 여러 묶음, 하나의 큰 묶음
     * - 묶음 크기: 하한 1, 일반 크기, 상한 100,000
     * - 결과: 모든 번호의 크기 기록, 입력 원본 보존
     *
     * 대표 오답
     * - 첫 시작점에서 탐색 한 번만 하고 다른 묶음을 빠뜨린다.
     * - 중복·자기 간선을 새 장치로 세거나 고립 장치의 결과를 0으로 둔다.
     * - 묶음 크기를 다 알기 전에 구성원에게 중간값을 쓴다.
     * - 재귀 DFS로 최대 일렬 연결에서 StackOverflowError가 난다.
     */
    @Test
    @DisplayName("여러 연결 묶음과 원본 보존")
    void recordsGroupSizesWithoutChangingEdges() {
        int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {5, 5}, {4, 5}};
        int[][] before = cloneRows(edges);

        int[] actual = GraphSolution04.solve(7, edges);

        assertAll(
                () -> assertArrayEquals(new int[]{3, 3, 3, 2, 2, 1, 1}, actual),
                () -> assertRowsEqual(before, edges)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("groupCases")
    @DisplayName("고립·중복·자기 간선")
    void recordsGroupSizes(String name, int nodeCount, int[][] edges, int[] expected) {
        assertArrayEquals(expected, GraphSolution04.solve(nodeCount, edges));
    }

    static Stream<Arguments> groupCases() {
        return Stream.of(
                Arguments.of("정점 하나와 간선 하한", 1, new int[][]{}, new int[]{1}),
                Arguments.of("모든 장치가 고립된 묶음", 5, new int[][]{}, new int[]{1, 1, 1, 1, 1}),
                Arguments.of(
                        "중복·반대 순서·자기 간선",
                        6,
                        new int[][]{{1, 2}, {2, 1}, {2, 2}, {3, 4}, {4, 3}},
                        new int[]{2, 2, 2, 2, 1, 1}
                )
        );
    }

    @Test
    @DisplayName("중간 정점 수 940과 간선 없음")
    void handlesIntermediateNodeCount() {
        int[] expected = new int[940];
        Arrays.fill(expected, 1);

        assertArrayEquals(expected, GraphSolution04.solve(940, new int[][]{}));
    }

    @Test
    @DisplayName("정점·간선 상한의 하나로 이어진 묶음")
    void handlesMaximumConnectedGroup() {
        int nodeCount = 100_000;
        int[][] edges = makeConnectedEdges(nodeCount, 200_000);
        int[] expected = new int[nodeCount];
        Arrays.fill(expected, nodeCount);

        assertArrayEquals(expected, GraphSolution04.solve(nodeCount, edges));
    }

    private static int[][] makeConnectedEdges(int nodeCount, int edgeCount) {
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

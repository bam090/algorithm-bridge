package bridge.graph.test;

import bridge.graph.solution.GraphSolution06;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("그래프 06 - 상자가 양쪽에 있는 통로 세기")
final class GraphSolution06Test {

    /*
     * 테스트 범위
     * - nodeCount: 하한 1, 중간 1,000, 상한 100,000
     * - undirectedLinks 길이: 하한 0, 일반 분기, 상한 99,999
     * - supplyNodes 길이: 하한 0, 원소 하나, 여러 개, 상한 100,000
     * - 구역 번호: 첫 번호, 중간 940, 마지막 번호 100,000
     * - 트리 모양: 정점 하나, 일렬 연결, 여러 자식 분기, 섞인 간선 순서
     * - 결과: 한쪽 상자 없음, 양쪽 상자 있음, 원본 보존, 최대 깊이
     *
     * 대표 오답
     * - 자식 결과가 완성되기 전에 부모로 더한다.
     * - 자식 쪽만 확인하고 반대쪽 상자 수를 확인하지 않는다.
     * - 통로마다 탐색해 O(n²)이 되거나 깊은 트리를 재귀로 처리한다.
     * - 임시 뿌리나 간선 입력 순서에 따라 결과가 달라진다.
     */
    @Test
    @DisplayName("여러 분기의 양쪽 상자와 원본 보존")
    void countsBoundariesWithoutChangingInputs() {
        int[][] links = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}};
        int[] supplies = {4, 5, 7};
        int[][] linksBefore = cloneRows(links);
        int[] suppliesBefore = supplies.clone();

        int actual = GraphSolution06.solve(7, links, supplies);

        assertAll(
                () -> assertEquals(5, actual),
                () -> assertRowsEqual(linksBefore, links),
                () -> assertArrayEquals(suppliesBefore, supplies)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("boundaryCases")
    @DisplayName("트리 모양과 상자 위치 경계")
    void countsBoundaries(String name, int nodeCount, int[][] links, int[] supplies, int expected) {
        assertEquals(expected, GraphSolution06.solve(nodeCount, links, supplies));
    }

    static Stream<Arguments> boundaryCases() {
        return Stream.of(
                Arguments.of("정점 하나·통로와 상자 수 하한", 1, new int[][]{}, new int[]{}, 0),
                Arguments.of("정점 하나에 상자 하나", 1, new int[][]{}, new int[]{1}, 0),
                Arguments.of(
                        "일렬 트리 양끝에 상자가 있음",
                        5,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}},
                        new int[]{1, 5},
                        4
                ),
                Arguments.of(
                        "분기 트리에 상자가 없음",
                        6,
                        new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}},
                        new int[]{},
                        0
                ),
                Arguments.of("중간 번호 940에 상자 하나만 있음", 1_000, makePathLinks(1_000), new int[]{940}, 0),
                Arguments.of(
                        "한쪽 가지에 모인 상자 사이 경계",
                        6,
                        new int[][]{{3, 6}, {2, 5}, {1, 3}, {2, 4}, {1, 2}},
                        new int[]{4, 5},
                        2
                ),
                Arguments.of(
                        "섞인 간선 순서와 모든 정점의 상자",
                        4,
                        new int[][]{{3, 4}, {1, 3}, {1, 2}},
                        new int[]{4, 2, 1, 3},
                        3
                )
        );
    }

    @Test
    @DisplayName("정점·깊이·상자 수 상한")
    void handlesMaximumCountsAndDepth() {
        int nodeCount = 100_000;
        int[][] links = makePathLinks(nodeCount);
        int[] supplies = new int[nodeCount];
        for (int index = 0; index < nodeCount; index++) {
            supplies[index] = index + 1;
        }

        assertEquals(99_999, GraphSolution06.solve(nodeCount, links, supplies));
    }

    private static int[][] makePathLinks(int nodeCount) {
        int[][] links = new int[nodeCount - 1][2];
        for (int node = 1; node < nodeCount; node++) {
            links[node - 1] = new int[]{node, node + 1};
        }
        return links;
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

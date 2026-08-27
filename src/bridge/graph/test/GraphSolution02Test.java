package bridge.graph.test;

import bridge.graph.solution.GraphSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GraphSolution02를 검증한다. */
public final class GraphSolution02Test {

    private GraphSolution02Test() {
    }

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
    public static void main(String[] args) {
        int total = 8;
        int passed = 0;

        passed += runCase(1, "여러 경로 중 최소 거리와 원본 보존", () -> {
            int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 5}};
            int[][] before = cloneRows(edges);
            assertArrayEquals(
                    new int[]{0, 1, 1, 2, 3, -1},
                    GraphSolution02.solve(6, edges, 1)
            );
            assertDeepArrayEquals(before, edges);
        });
        passed += runCase(2, "정점 하나·간선 하한과 거리 0", () -> assertArrayEquals(
                new int[]{0},
                GraphSolution02.solve(1, new int[][]{}, 1)
        ));
        passed += runCase(3, "중복·자기 간선과 고립 정점", () -> assertArrayEquals(
                new int[]{0, 1, 2, -1},
                GraphSolution02.solve(
                        4,
                        new int[][]{{1, 1}, {1, 2}, {1, 2}, {2, 3}, {3, 3}},
                        1
                )
        ));
        passed += runCase(4, "긴 우회보다 나중에 보이는 직접 지름길 선택", () -> assertArrayEquals(
                new int[]{0, 1, 2, 2, 1, -1},
                GraphSolution02.solve(
                        6,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}},
                        1
                )
        ));
        passed += runCase(5, "간선 순서를 바꿔도 같은 최단거리", () -> assertArrayEquals(
                new int[]{0, 1, 2, 2, 1, -1},
                GraphSolution02.solve(
                        6,
                        new int[][]{{1, 5}, {4, 5}, {3, 4}, {2, 3}, {1, 2}},
                        1
                )
        ));
        passed += runCase(6, "마지막 번호에서 양방향으로 이동", () -> assertArrayEquals(
                new int[]{3, 2, 1, 0},
                GraphSolution02.solve(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}, 4)
        ));
        passed += runCase(7, "중간 거리 940", () -> {
            int nodeCount = 941;
            int[][] edges = makeChainEdges(nodeCount, nodeCount - 1);
            int[] expected = new int[nodeCount];
            for (int index = 0; index < nodeCount; index++) {
                expected[index] = index;
            }
            assertArrayEquals(expected, GraphSolution02.solve(nodeCount, edges, 1));
        });
        passed += runCase(8, "정점·간선 상한과 최대 일렬 거리", () -> {
            int nodeCount = 100_000;
            int[][] edges = makeChainEdges(nodeCount, 200_000);
            int[] expected = new int[nodeCount];
            for (int index = 0; index < nodeCount; index++) {
                expected[index] = index;
            }
            assertArrayEquals(expected, GraphSolution02.solve(nodeCount, edges, 1));
        });

        finish("GraphSolution02", passed, total);
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

    private static int runCase(int number, String name, Runnable test) {
        try {
            test.run();
            System.out.printf("[PASS] 테스트 %d: %s%n", number, name);
            return 1;
        } catch (AssertionError | RuntimeException error) {
            System.out.printf("[FAIL] 테스트 %d: %s | %s%n", number, name, error.getMessage());
            return 0;
        }
    }

    private static void finish(String solutionName, int passed, int total) {
        System.out.printf("[RESULT] %s: %d/%d 통과%n", solutionName, passed, total);
        if (passed != total) {
            throw new AssertionError(solutionName + ": 통과하지 못한 테스트가 있습니다.");
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }

    private static int[][] cloneRows(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static void assertDeepArrayEquals(int[][] expected, int[][] actual) {
        if (!Arrays.deepEquals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual));
        }
    }
}

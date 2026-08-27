package bridge.graph.test;

import bridge.graph.solution.GraphSolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GraphSolution04를 검증한다. */
public final class GraphSolution04Test {

    private GraphSolution04Test() {
    }

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
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "여러 연결 묶음과 원본 보존", () -> {
            int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {5, 5}, {4, 5}};
            int[][] before = cloneRows(edges);
            assertArrayEquals(
                    new int[]{3, 3, 3, 2, 2, 1, 1},
                    GraphSolution04.solve(7, edges)
            );
            assertDeepArrayEquals(before, edges);
        });
        passed += runCase(2, "정점 하나와 간선 하한", () -> assertArrayEquals(
                new int[]{1},
                GraphSolution04.solve(1, new int[][]{})
        ));
        passed += runCase(3, "모든 장치가 고립된 묶음", () -> assertArrayEquals(
                new int[]{1, 1, 1, 1, 1},
                GraphSolution04.solve(5, new int[][]{})
        ));
        passed += runCase(4, "중복·반대 순서·자기 간선", () -> assertArrayEquals(
                new int[]{2, 2, 2, 2, 1, 1},
                GraphSolution04.solve(
                        6,
                        new int[][]{{1, 2}, {2, 1}, {2, 2}, {3, 4}, {4, 3}}
                )
        ));
        passed += runCase(5, "중간 정점 수 940과 간선 없음", () -> {
            int[] expected = new int[940];
            Arrays.fill(expected, 1);
            assertArrayEquals(expected, GraphSolution04.solve(940, new int[][]{}));
        });
        passed += runCase(6, "정점·간선 상한의 하나로 이어진 묶음", () -> {
            int nodeCount = 100_000;
            int[][] edges = makeConnectedEdges(nodeCount, 200_000);
            int[] expected = new int[nodeCount];
            Arrays.fill(expected, nodeCount);
            assertArrayEquals(expected, GraphSolution04.solve(nodeCount, edges));
        });

        finish("GraphSolution04", passed, total);
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

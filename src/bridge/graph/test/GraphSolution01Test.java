package bridge.graph.test;

import bridge.graph.solution.GraphSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GraphSolution01을 검증한다. */
public final class GraphSolution01Test {

    private GraphSolution01Test() {
    }

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
    public static void main(String[] args) {
        int total = 5;
        int passed = 0;

        passed += runCase(1, "일반 단방향 도달·고립 정점과 원본 보존", () -> {
            int[] values = {10, 20, -5, 40, 7};
            int[][] edges = {{1, 2}, {1, 3}, {3, 4}};
            int[] valuesBefore = values.clone();
            int[][] edgesBefore = cloneRows(edges);

            assertArrayEquals(new long[]{4, 65}, GraphSolution01.solve(values, edges, 1));
            assertArrayEquals(valuesBefore, values);
            assertDeepArrayEquals(edgesBefore, edges);
        });
        passed += runCase(2, "정점 하나·간선 하한과 중간값 940", () -> assertArrayEquals(
                new long[]{1, 940},
                GraphSolution01.solve(new int[]{940}, new int[][]{}, 1)
        ));
        passed += runCase(3, "값 하한·0·중간·상한과 중복·자기 간선", () -> assertArrayEquals(
                new long[]{3, -999_060},
                GraphSolution01.solve(
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        new int[][]{{1, 2}, {1, 2}, {2, 2}, {2, 3}, {4, 1}},
                        1
                )
        ));
        passed += runCase(4, "마지막 번호에서 시작해 정방향만 이동", () -> assertArrayEquals(
                new long[]{3, -40},
                GraphSolution01.solve(
                        new int[]{1, 20, -1_000, 940},
                        new int[][]{{4, 3}, {3, 2}, {1, 4}},
                        4
                )
        ));
        passed += runCase(5, "정점·간선 상한과 long 누적", () -> {
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
        });

        finish("GraphSolution01", passed, total);
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

    private static void assertArrayEquals(long[] expected, long[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
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

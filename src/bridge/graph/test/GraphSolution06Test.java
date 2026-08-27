package bridge.graph.test;

import bridge.graph.solution.GraphSolution06;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GraphSolution06을 검증한다. */
public final class GraphSolution06Test {

    private GraphSolution06Test() {
    }

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
    public static void main(String[] args) {
        int total = 9;
        int passed = 0;

        passed += runCase(1, "여러 분기의 양쪽 상자와 원본 보존", () -> {
            int[][] links = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}};
            int[] supplies = {4, 5, 7};
            int[][] linksBefore = cloneRows(links);
            int[] suppliesBefore = supplies.clone();
            assertEquals(5, GraphSolution06.solve(7, links, supplies));
            assertDeepArrayEquals(linksBefore, links);
            assertArrayEquals(suppliesBefore, supplies);
        });
        passed += runCase(2, "정점 하나·통로와 상자 수 하한", () -> assertEquals(
                0,
                GraphSolution06.solve(1, new int[][]{}, new int[]{})
        ));
        passed += runCase(3, "정점 하나에 상자 하나", () -> assertEquals(
                0,
                GraphSolution06.solve(1, new int[][]{}, new int[]{1})
        ));
        passed += runCase(4, "일렬 트리 양끝에 상자가 있음", () -> assertEquals(
                4,
                GraphSolution06.solve(
                        5,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}},
                        new int[]{1, 5}
                )
        ));
        passed += runCase(5, "분기 트리에 상자가 없음", () -> assertEquals(
                0,
                GraphSolution06.solve(
                        6,
                        new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}},
                        new int[]{}
                )
        ));
        passed += runCase(6, "중간 번호 940에 상자 하나만 있음", () -> assertEquals(
                0,
                GraphSolution06.solve(1_000, makePathLinks(1_000), new int[]{940})
        ));
        passed += runCase(7, "한쪽 가지에 모인 상자 사이 경계", () -> assertEquals(
                2,
                GraphSolution06.solve(
                        6,
                        new int[][]{{3, 6}, {2, 5}, {1, 3}, {2, 4}, {1, 2}},
                        new int[]{4, 5}
                )
        ));
        passed += runCase(8, "섞인 간선 순서와 모든 정점의 상자", () -> assertEquals(
                3,
                GraphSolution06.solve(
                        4,
                        new int[][]{{3, 4}, {1, 3}, {1, 2}},
                        new int[]{4, 2, 1, 3}
                )
        ));
        passed += runCase(9, "정점·깊이·상자 수 상한", () -> {
            int nodeCount = 100_000;
            int[][] links = makePathLinks(nodeCount);
            int[] supplies = new int[nodeCount];
            for (int index = 0; index < nodeCount; index++) {
                supplies[index] = index + 1;
            }
            assertEquals(99_999, GraphSolution06.solve(nodeCount, links, supplies));
        });

        finish("GraphSolution06", passed, total);
    }

    private static int[][] makePathLinks(int nodeCount) {
        int[][] links = new int[nodeCount - 1][2];
        for (int node = 1; node < nodeCount; node++) {
            links[node - 1] = new int[]{node, node + 1};
        }
        return links;
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

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
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

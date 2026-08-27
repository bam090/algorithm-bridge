package bridge.graph.test;

import bridge.graph.solution.GraphSolution05;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GraphSolution05를 검증한다. */
public final class GraphSolution05Test {

    private GraphSolution05Test() {
    }

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
    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "점검소 이후 여러 목적지와 원본 보존", () -> {
            int[][] routes = {
                    {1, 2}, {2, 3}, {1, 4}, {4, 3}, {3, 5}, {5, 6}, {3, 7}
            };
            int[] destinations = {3, 5, 6, 2, 7};
            int[][] routesBefore = cloneRows(routes);
            int[] destinationsBefore = destinations.clone();
            assertArrayEquals(
                    new int[]{2, 3, 4, -1, 3},
                    GraphSolution05.solve(7, routes, 1, 3, destinations)
            );
            assertDeepArrayEquals(routesBefore, routes);
            assertArrayEquals(destinationsBefore, destinations);
        });
        passed += runCase(2, "정점 하나·간선 하한과 두 구간 거리 0", () -> assertArrayEquals(
                new int[]{0, 0},
                GraphSolution05.solve(1, new int[][]{}, 1, 1, new int[]{1, 1})
        ));
        passed += runCase(3, "출발점에서 점검소에 도달 불가", () -> assertArrayEquals(
                new int[]{-1, -1, -1},
                GraphSolution05.solve(
                        4,
                        new int[][]{{1, 2}, {3, 4}},
                        1,
                        3,
                        new int[]{3, 4, 1}
                )
        ));
        passed += runCase(4, "점검소 전에 지난 목적지로 돌아갈 수 없음", () -> assertArrayEquals(
                new int[]{-1, 2, 3},
                GraphSolution05.solve(
                        4,
                        new int[][]{{1, 2}, {2, 3}, {3, 4}},
                        1,
                        3,
                        new int[]{2, 3, 4}
                )
        ));
        passed += runCase(5, "목적지 길이 하한 0", () -> assertArrayEquals(
                new int[]{},
                GraphSolution05.solve(2, new int[][]{{1, 2}}, 1, 2, new int[]{})
        ));
        passed += runCase(6, "거리 940·중복 목적지와 자기 간선", () -> {
            int nodeCount = 1_000;
            int[][] routes = makeDirectedChain(nodeCount, nodeCount + 1);
            assertArrayEquals(
                    new int[]{0, 940, 940, 999},
                    GraphSolution05.solve(
                            nodeCount,
                            routes,
                            1,
                            1,
                            new int[]{1, 941, 941, 1_000}
                    )
            );
        });
        passed += runCase(7, "정점·간선 상한과 최대 일렬 거리", () -> {
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
        });

        finish("GraphSolution05", passed, total);
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

package bridge.stack.test;

import bridge.stack.solution.StackSolution05;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 StackSolution05를 검증한다. */
public final class StackSolution05Test {

    private StackSolution05Test() {
    }

    /*
     * 테스트 범위
     * - sourceStacks 행: 하한 1, 상한 50, 빈 행, 카드 합 하한 0·상한 100,000
     * - 카드 값: 하한 -1,000, 0, 중간 940, 상한 1,000
     * - picks 길이: 하한 0, 빈 원본 선택, 상한 100,000
     * - cancelSum: 하한 -2,000, 일반값 10·1,940, 상한 2,000
     */
    public static void main(String[] args) {
        int total = 8;
        int passed = 0;

        passed += runCase(1, "여러 원본에서 연속 상쇄 후 한 장 남음", () -> assertArrayEquals(
                new int[]{7},
                StackSolution05.solve(
                        new int[][]{{4, -3}, {2, 3}, {-4, -2}, {7}},
                        new int[]{1, 2, 1, 2, 3, 3, 4},
                        0
                )
        ));
        passed += runCase(2, "0이 아닌 상쇄 합", () -> assertArrayEquals(
                new int[]{},
                StackSolution05.solve(
                        new int[][]{{1, 4}, {6}, {9}},
                        new int[]{1, 2, 1, 3},
                        10
                )
        ));
        passed += runCase(3, "카드 하한·0·중간·상한과 원본 보존", () -> {
            int[][] sources = {{-1_000, 0, 940}, {1_000}};
            int[] picks = {1, 2, 1, 1};
            int[][] sourceBefore = cloneRows(sources);
            int[] picksBefore = picks.clone();

            assertArrayEquals(new int[]{0, -1_000}, StackSolution05.solve(sources, picks, 1_940));
            assertDeepArrayEquals(sourceBefore, sources);
            assertArrayEquals(picksBefore, picks);
        });
        passed += runCase(4, "지시가 없으면 빈 결과", () -> assertArrayEquals(
                new int[]{},
                StackSolution05.solve(new int[][]{{1, 2, 3}}, new int[]{}, 0)
        ));
        passed += runCase(5, "빈 원본 건너뛰기와 아래에서 위 순서", () -> assertArrayEquals(
                new int[]{3, 5, 2},
                StackSolution05.solve(
                        new int[][]{{1, 2, 3}, {}, {4, 5}},
                        new int[]{2, 1, 3, 1},
                        100
                )
        ));
        passed += runCase(6, "상쇄 합 하한", () -> assertArrayEquals(
                new int[]{},
                StackSolution05.solve(new int[][]{{-1_000, -1_000}}, new int[]{1, 1}, -2_000)
        ));
        passed += runCase(7, "행 50개와 전체 카드 수 0", () -> {
            int[][] emptySources = new int[50][];
            for (int row = 0; row < emptySources.length; row++) {
                emptySources[row] = new int[]{};
            }
            assertArrayEquals(
                    new int[]{},
                    StackSolution05.solve(emptySources, new int[]{1, 50, 1, 50}, 0)
            );
        });
        passed += runCase(8, "행·행 길이·카드 합·지시 길이와 상쇄 합 상한", () -> {
            int[][] sources = new int[50][2_000];
            int[] picks = new int[100_000];

            for (int row = 0; row < sources.length; row++) {
                Arrays.fill(sources[row], 1_000);
                for (int count = 0; count < sources[row].length; count++) {
                    picks[row * 2_000 + count] = row + 1;
                }
            }

            // 각 행의 2,000장은 1,000 두 장씩 상쇄되므로 모든 지시 뒤 결과는 빈 배열이다.
            assertArrayEquals(new int[]{}, StackSolution05.solve(sources, picks, 2_000));
        });

        finish("StackSolution05", passed, total);
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

    private static int[][] cloneRows(int[][] values) {
        int[][] copy = new int[values.length][];
        for (int row = 0; row < values.length; row++) {
            copy[row] = values[row].clone();
        }
        return copy;
    }

    private static void assertDeepArrayEquals(int[][] expected, int[][] actual) {
        if (!Arrays.deepEquals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual));
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}

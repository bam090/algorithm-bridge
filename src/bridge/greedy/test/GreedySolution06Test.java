package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution06;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GreedySolution06을 검증한다. */
public final class GreedySolution06Test {

    private GreedySolution06Test() {
    }

    /*
     * 범위표
     * - roadLength: 0, 940을 경계로 둔 일반값, 상한 1,000,000,000
     * - coveredIntervals 길이: 0, 1, 겹치고 정렬되지 않은 일반 길이, 최대 100,000
     * - patchWidth: 1, 일반값, 상한 1,000,000,000
     * - 대표 오답: 포함 구간 end+1 누락, 겹친 구간에서 뒤로 이동, 한 칸 틈, 위치별 배열 생성, 원본 정렬
     */
    public static void main(String[] args) {
        int total = 10;
        int passed = 0;

        passed += runCase(1, "기존 구간 사이의 빈 위치 덮기와 원본 보존", () -> {
            int[][] input = {{4, 6}, {11, 12}};
            int[][] before = deepCopy(input);
            assertArrayEquals(new int[]{0, 3, 7, 10, 13}, GreedySolution06.solve(15, input, 3));
            assertMatrixEquals(before, input);
        });
        passed += runCase(2, "길이 0 산책로", () -> assertArrayEquals(
                new int[]{},
                GreedySolution06.solve(0, new int[][]{}, 1)
        ));
        passed += runCase(3, "기존 구간이 없으면 폭만큼 전진", () -> assertArrayEquals(
                new int[]{0, 3, 6, 9},
                GreedySolution06.solve(10, new int[][]{}, 3)
        ));
        passed += runCase(4, "길이 상한 전체가 이미 덮인 경우", () -> assertArrayEquals(
                new int[]{},
                GreedySolution06.solve(
                        1_000_000_000,
                        new int[][]{{0, 999_999_999}},
                        1_000_000_000
                )
        ));
        passed += runCase(5, "정렬되지 않고 겹친 기존 구간", () -> assertArrayEquals(
                new int[]{0, 9, 13, 16, 19},
                GreedySolution06.solve(
                        20,
                        new int[][]{{10, 12}, {2, 5}, {4, 8}},
                        3
                )
        ));
        passed += runCase(6, "첫 위치와 마지막 위치의 포함 경계", () -> assertArrayEquals(
                new int[]{1, 3},
                GreedySolution06.solve(5, new int[][]{{0, 0}, {4, 4}}, 2)
        ));
        passed += runCase(7, "폭 1은 빈 위치마다 설치", () -> assertArrayEquals(
                new int[]{0, 2, 3},
                GreedySolution06.solve(4, new int[][]{{1, 1}}, 1)
        ));
        passed += runCase(8, "940 다음의 마지막 구간을 한 번에 덮기", () -> assertArrayEquals(
                new int[]{940},
                GreedySolution06.solve(1_000, new int[][]{{0, 939}}, 60)
        ));
        passed += runCase(9, "최대 100000개 기존 구간과 새 덮개", () -> {
            int size = 100_000;
            int[][] intervals = new int[size][2];
            int[] expected = new int[size];
            for (int i = 0; i < size; i++) {
                intervals[i][0] = i * 10;
                intervals[i][1] = i * 10 + 4;
                expected[i] = i * 10 + 5;
            }
            int[][] before = deepCopy(intervals);
            assertArrayEquals(expected, GreedySolution06.solve(1_000_000, intervals, 5));
            assertMatrixEquals(before, intervals);
        });
        passed += runCase(10, "덮개 폭 상한은 산책로 전체를 한 번에 덮음", () -> assertArrayEquals(
                new int[]{0},
                GreedySolution06.solve(940, new int[][]{}, 1_000_000_000)
        ));

        finish("GreedySolution06", passed, total);
    }

    private static int[][] deepCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }
        return copy;
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

    private static void assertMatrixEquals(int[][] expected, int[][] actual) {
        if (!Arrays.deepEquals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual));
        }
    }
}

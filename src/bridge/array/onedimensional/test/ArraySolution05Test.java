package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution05;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution05를 검증한다. */
public final class ArraySolution05Test {

    private ArraySolution05Test() {
    }

    public static void main(String[] args) {
        int total = 5;
        int passed = 0;

        passed += runCase(1, "겹치는 여러 구간", () -> assertArrayEquals(
                new long[]{8, 11, 2},
                ArraySolution05.solve(
                        new int[]{4, 1, 3, 2, 5},
                        new int[][]{{1, 3}, {2, 5}, {4, 4}}
                )
        ));
        passed += runCase(2, "원소 하나와 한 칸 구간", () -> assertArrayEquals(
                new long[]{-7},
                ArraySolution05.solve(new int[]{-7}, new int[][]{{1, 1}})
        ));
        passed += runCase(3, "값 범위의 하한·0·중간·상한", () -> assertArrayEquals(
                new long[]{-1_000_000, 0, 940, 1_000_000, 940},
                ArraySolution05.solve(
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {1, 4}}
                )
        ));
        passed += runCase(4, "합이 0인 구간과 질문 순서 유지", () -> assertArrayEquals(
                new long[]{0, 0, 3, -5},
                ArraySolution05.solve(
                        new int[]{2, -2, 5, -5},
                        new int[][]{{1, 2}, {1, 4}, {2, 3}, {4, 4}}
                )
        ));
        passed += runCase(5, "최대 길이의 값·질문과 int 범위를 넘는 구간 합", () -> {
            int[] values = new int[100_000];
            Arrays.fill(values, 1_000_000);

            int[][] ranges = new int[100_000][2];
            for (int[] range : ranges) {
                range[0] = 1;
                range[1] = 100_000;
            }
            ranges[0][0] = 100_000;

            long[] expected = new long[100_000];
            Arrays.fill(expected, 100_000_000_000L);
            expected[0] = 1_000_000L;
            assertArrayEquals(expected, ArraySolution05.solve(values, ranges));
        });

        finish("ArraySolution05", passed, total);
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
        if (expected.length != actual.length) {
            throw new AssertionError("expected length=" + expected.length
                    + ", actual length=" + actual.length);
        }
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                throw new AssertionError("index=" + i
                        + ", expected=" + expected[i]
                        + ", actual=" + actual[i]);
            }
        }
    }
}

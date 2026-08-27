package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution06;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution06을 검증한다. */
public final class ArraySolution06Test {

    private ArraySolution06Test() {
    }

    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "여러 상승 구간", () -> assertEquals(
                3,
                ArraySolution06.solve(new int[]{3, 4, 6, 2, 5, 7, 1}),
                new int[]{3, 4, 6, 2, 5, 7, 1}
        ));
        passed += runCase(2, "빈 배열", () -> assertEquals(
                0, ArraySolution06.solve(new int[]{}), new int[]{}
        ));
        passed += runCase(3, "원소 하나", () -> assertEquals(
                1, ArraySolution06.solve(new int[]{9}), new int[]{9}
        ));
        passed += runCase(4, "같은 값은 상승을 끊음", () -> assertEquals(
                2,
                ArraySolution06.solve(new int[]{1, 2, 2, 3}),
                new int[]{1, 2, 2, 3}
        ));
        passed += runCase(5, "계속 감소", () -> assertEquals(
                1,
                ArraySolution06.solve(new int[]{5, 4, 3, 2}),
                new int[]{5, 4, 3, 2}
        ));
        passed += runCase(6, "최대 길이에서 값 하한부터 상한까지 계속 증가", () -> {
            int[] values = new int[100_000];
            for (int i = 0; i < values.length; i++) {
                values[i] = -1_000_000 + i * 20;
            }
            values[99_999] = 1_000_000;
            assertEquals(100_000, ArraySolution06.solve(values), values);
        });
        passed += runCase(7, "앞에서 찾은 최장 길이를 이후에도 유지", () -> assertEquals(
                5,
                ArraySolution06.solve(new int[]{9, -3, -2, -1, 0, 5, 4, 5, 1}),
                new int[]{9, -3, -2, -1, 0, 5, 4, 5, 1}
        ));

        finish("ArraySolution06", passed, total);
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

    private static void assertEquals(int expected, int actual, int[] input) {
        if (expected != actual) {
            throw new AssertionError("input=" + Arrays.toString(input)
                    + ", expected=" + expected
                    + ", actual=" + actual);
        }
    }
}

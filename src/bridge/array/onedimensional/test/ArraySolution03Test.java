package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution03을 검증한다. */
public final class ArraySolution03Test {

    private ArraySolution03Test() {
    }

    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "증가·감소·같음이 섞인 값", () -> assertArrayEquals(
                new int[]{3, -1, 0},
                ArraySolution03.solve(new int[]{10, 13, 12, 12})
        ));
        passed += runCase(2, "원소가 하나인 배열", () -> assertArrayEquals(
                new int[]{},
                ArraySolution03.solve(new int[]{7})
        ));
        passed += runCase(3, "현재 값에서 이전 값 빼기", () -> assertArrayEquals(
                new int[]{-5, -5, 5},
                ArraySolution03.solve(new int[]{5, 0, -5, 0})
        ));
        passed += runCase(4, "원소가 두 개인 배열", () -> assertArrayEquals(
                new int[]{2},
                ArraySolution03.solve(new int[]{-1, 1})
        ));
        passed += runCase(5, "가장 큰 양수·음수 변화량", () -> assertArrayEquals(
                new int[]{20_000, -20_000},
                ArraySolution03.solve(new int[]{-10_000, 10_000, -10_000})
        ));
        passed += runCase(6, "길이 상한과 940을 포함한 연속 증가", () -> {
            int[] values = new int[1_000];
            for (int i = 0; i < values.length; i++) {
                values[i] = i;
            }
            int[] expected = new int[999];
            Arrays.fill(expected, 1);
            assertArrayEquals(expected, ArraySolution03.solve(values));
        });

        finish("ArraySolution03", passed, total);
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
}

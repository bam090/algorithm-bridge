package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution01을 검증한다. */
public final class ArraySolution01Test {

    private ArraySolution01Test() {
    }

    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "가운데 칸 수정과 원본 보존", () -> {
            int[] original = {18, 20, 19};
            int[] corrected = ArraySolution01.solve(original, 2, 21);
            assertArrayEquals(new int[]{18, 21, 19}, corrected);
            assertArrayEquals(new int[]{18, 20, 19}, original);
            assertDifferentReference(original, corrected);
        });
        passed += runCase(2, "첫 칸 수정", () -> assertArrayEquals(
                new int[]{9, 2, 3},
                ArraySolution01.solve(new int[]{1, 2, 3}, 1, 9)
        ));
        passed += runCase(3, "마지막 칸 수정", () -> assertArrayEquals(
                new int[]{1, 2, -5},
                ArraySolution01.solve(new int[]{1, 2, 3}, 3, -5)
        ));
        passed += runCase(4, "원소가 하나이고 수정값이 같아도 새 배열 반환", () -> {
            int[] original = {1_000};
            int[] corrected = ArraySolution01.solve(original, 1, 1_000);
            assertArrayEquals(new int[]{1_000}, corrected);
            assertArrayEquals(new int[]{1_000}, original);
            assertDifferentReference(original, corrected);
        });
        passed += runCase(5, "같은 값 중 지정한 한 칸만 수정", () -> {
            int[] original = {4, 4, 4};
            int[] corrected = ArraySolution01.solve(original, 2, -1_000);
            assertArrayEquals(new int[]{4, -1_000, 4}, corrected);
            assertArrayEquals(new int[]{4, 4, 4}, original);
            assertDifferentReference(original, corrected);
        });
        passed += runCase(6, "최대 길이에서 값 범위의 하한·0·940·상한 확인", () -> {
            int[] original = new int[100];
            original[0] = -1_000;
            original[50] = 940;
            original[99] = 1_000;
            int[] before = original.clone();

            int[] corrected = ArraySolution01.solve(original, 100, 0);
            int[] expected = before.clone();
            expected[99] = 0;

            assertArrayEquals(expected, corrected);
            assertArrayEquals(before, original);
            assertDifferentReference(original, corrected);
        });

        finish("ArraySolution01", passed, total);
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

    private static void assertDifferentReference(int[] original, int[] actual) {
        if (original == actual) {
            throw new AssertionError("결과가 원본과 같은 배열을 가리킵니다.");
        }
    }
}

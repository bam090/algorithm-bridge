package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution04를 검증한다. */
public final class ArraySolution04Test {

    private ArraySolution04Test() {
    }

    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "순서와 중복을 유지하며 값 고르기", () -> {
            int[] original = {12, 7, 15, 9, 15};
            int[] filtered = ArraySolution04.solve(original, 10);
            assertArrayEquals(new int[]{12, 15, 15}, filtered);
            assertArrayEquals(new int[]{12, 7, 15, 9, 15}, original);
            assertDifferentReference(original, filtered);
        });
        passed += runCase(2, "조건을 통과하는 값이 없음", () -> {
            int[] original = {-3, -2, -1};
            int[] filtered = ArraySolution04.solve(original, 0);
            assertArrayEquals(new int[]{}, filtered);
            assertDifferentReference(original, filtered);
        });
        passed += runCase(3, "빈 배열도 새 배열로 반환", () -> {
            int[] original = {};
            int[] filtered = ArraySolution04.solve(original, 0);
            assertArrayEquals(new int[]{}, filtered);
            assertDifferentReference(original, filtered);
        });
        passed += runCase(4, "최대 길이에서 값 범위의 하한·0·940·상한 확인", () -> {
            int[] original = new int[10_000];
            Arrays.fill(original, 940);
            original[0] = -10_000;
            original[5_000] = 0;
            original[9_999] = 10_000;

            int[] filtered = ArraySolution04.solve(original, -10_000);
            assertArrayEquals(original, filtered);
            assertDifferentReference(original, filtered);
        });
        passed += runCase(5, "정렬하지 않고 원래 순서 유지", () -> assertArrayEquals(
                new int[]{8, 3, 6},
                ArraySolution04.solve(new int[]{8, 3, 6, 2}, 3)
        ));
        passed += runCase(6, "기준값의 상한도 포함", () -> assertArrayEquals(
                new int[]{10_000},
                ArraySolution04.solve(new int[]{-10_000, 0, 940, 10_000}, 10_000)
        ));

        finish("ArraySolution04", passed, total);
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

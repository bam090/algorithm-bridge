package bridge.queue.test;

import bridge.queue.solution.QueueSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 QueueSolution01을 검증한다. */
public final class QueueSolution01Test {

    private QueueSolution01Test() {
    }

    public static void main(String[] args) {
        int total = 5;
        int passed = 0;

        passed += runCase(1, "네 값 회전과 원본 보존", () -> {
            int[] original = {10, 20, 30, 40};
            int[] before = original.clone();
            int[] actual = QueueSolution01.solve(original);
            assertArrayEquals(new int[]{20, 30, 40, 10}, actual);
            assertArrayEquals(before, original);
            assertDifferentReference(original, actual);
        });
        passed += runCase(2, "빈 순서는 빈 새 배열", () -> {
            int[] original = {};
            int[] actual = QueueSolution01.solve(original);
            assertArrayEquals(new int[]{}, actual);
            assertDifferentReference(original, actual);
        });
        passed += runCase(3, "원소 하나와 중간값 940", () -> {
            int[] original = {940};
            int[] actual = QueueSolution01.solve(original);
            assertArrayEquals(new int[]{940}, actual);
            assertArrayEquals(new int[]{940}, original);
            assertDifferentReference(original, actual);
        });
        passed += runCase(4, "값 하한·0·중간·상한의 순서", () -> assertArrayEquals(
                new int[]{0, 940, 1_000_000, -1_000_000},
                QueueSolution01.solve(new int[]{-1_000_000, 0, 940, 1_000_000})
        ));
        passed += runCase(5, "최대 길이에서 한 칸만 회전", () -> {
            int size = 100_000;
            int[] original = new int[size];
            int[] expected = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = i;
            }
            System.arraycopy(original, 1, expected, 0, size - 1);
            expected[size - 1] = 0;
            int[] before = original.clone();

            int[] actual = QueueSolution01.solve(original);
            assertArrayEquals(expected, actual);
            assertArrayEquals(before, original);
            assertDifferentReference(original, actual);
        });

        finish("QueueSolution01", passed, total);
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

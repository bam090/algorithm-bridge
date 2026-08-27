package bridge.queue.test;

import bridge.queue.solution.QueueSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 QueueSolution03을 검증한다. */
public final class QueueSolution03Test {

    private QueueSolution03Test() {
    }

    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "두 수령 회차와 원본 보존", () -> {
            int[] requested = {0, 2, 4, 6, 8};
            int[] preparation = {10, 3, 8, 2, 1};
            int[] requestedBefore = requested.clone();
            int[] preparationBefore = preparation.clone();

            assertArrayEquals(
                    new int[]{1, 1, 2, 2, 2},
                    QueueSolution03.solve(requested, preparation)
            );
            assertArrayEquals(requestedBefore, requested);
            assertArrayEquals(preparationBefore, preparation);
        });
        passed += runCase(2, "주문이 없으면 빈 결과", () -> assertArrayEquals(
                new int[]{},
                QueueSolution03.solve(new int[]{}, new int[]{})
        ));
        passed += runCase(3, "요청과 준비 시간의 하한 0", () -> assertArrayEquals(
                new int[]{1},
                QueueSolution03.solve(new int[]{0}, new int[]{0})
        ));
        passed += runCase(4, "0·940·상한을 포함한 같은 회차", () -> assertArrayEquals(
                new int[]{1, 1, 1, 1},
                QueueSolution03.solve(
                        new int[]{0, 0, 940, 940},
                        new int[]{1_000, 940, 0, 60}
                )
        ));
        passed += runCase(5, "준비 시각이 계속 늦어져 모두 다른 회차", () -> assertArrayEquals(
                new int[]{1, 2, 3},
                QueueSolution03.solve(
                        new int[]{0, 940, 1_000_000},
                        new int[]{0, 0, 1_000_000}
                )
        ));
        passed += runCase(6, "앞 주문 장벽 뒤에 일찍 준비된 주문 묶기", () -> assertArrayEquals(
                new int[]{1, 2, 2, 3},
                QueueSolution03.solve(
                        new int[]{0, 0, 0, 0},
                        new int[]{5, 6, 1, 7}
                )
        ));
        passed += runCase(7, "최대 주문 수를 한 회차로 처리", () -> {
            int size = 100_000;
            int[] requested = new int[size];
            int[] preparation = new int[size];
            preparation[0] = 1_000_000;
            int[] expected = new int[size];
            Arrays.fill(expected, 1);
            int[] requestedBefore = requested.clone();
            int[] preparationBefore = preparation.clone();

            assertArrayEquals(expected, QueueSolution03.solve(requested, preparation));
            assertArrayEquals(requestedBefore, requested);
            assertArrayEquals(preparationBefore, preparation);
        });

        finish("QueueSolution03", passed, total);
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

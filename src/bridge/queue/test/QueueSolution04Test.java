package bridge.queue.test;

import bridge.queue.solution.QueueSolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 QueueSolution04를 검증한다. */
public final class QueueSolution04Test {

    private QueueSolution04Test() {
    }

    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "두 검사대를 번갈아 사용하고 원본 보존", () -> {
            int[] first = {11, 13, 17};
            int[] second = {20, 22};
            int[] plan = {11, 20, 22, 13};
            int[] firstBefore = first.clone();
            int[] secondBefore = second.clone();
            int[] planBefore = plan.clone();

            assertArrayEquals(new int[]{1, 2, 2, 1}, QueueSolution04.solve(first, second, plan));
            assertArrayEquals(firstBefore, first);
            assertArrayEquals(secondBefore, second);
            assertArrayEquals(planBefore, plan);
        });
        passed += runCase(2, "빈 검사 계획은 성공한 빈 결과", () -> assertArrayEquals(
                new int[]{},
                QueueSolution04.solve(new int[]{1}, new int[]{2}, new int[]{})
        ));
        passed += runCase(3, "첫 검사대가 비어도 둘째 검사대로 계획 완료", () -> assertArrayEquals(
                new int[]{2, 2},
                QueueSolution04.solve(
                        new int[]{},
                        new int[]{940, 1_000_000},
                        new int[]{940, 1_000_000}
                )
        ));
        passed += runCase(4, "줄 안쪽 시료를 건너뛸 수 없어 실패", () -> assertArrayEquals(
                new int[]{-1},
                QueueSolution04.solve(new int[]{1, 2}, new int[]{3}, new int[]{2})
        ));
        passed += runCase(5, "두 검사대에 없는 시료에서 실패", () -> assertArrayEquals(
                new int[]{-1},
                QueueSolution04.solve(new int[]{}, new int[]{940}, new int[]{-1_000_000})
        ));
        passed += runCase(6, "시료 번호 하한·0·940·상한", () -> assertArrayEquals(
                new int[]{1, 2, 1, 2},
                QueueSolution04.solve(
                        new int[]{-1_000_000, 0},
                        new int[]{940, 1_000_000},
                        new int[]{-1_000_000, 940, 0, 1_000_000}
                )
        ));
        passed += runCase(7, "최대 시료 수를 두 검사대에서 교대로 꺼내기", () -> {
            int half = 50_000;
            int[] first = new int[half];
            int[] second = new int[half];
            int[] plan = new int[half * 2];
            int[] expected = new int[half * 2];

            for (int i = 0; i < half; i++) {
                first[i] = i;
                second[i] = i + 100_000;
                plan[i * 2] = first[i];
                plan[i * 2 + 1] = second[i];
                expected[i * 2] = 1;
                expected[i * 2 + 1] = 2;
            }

            int[] firstBefore = first.clone();
            int[] secondBefore = second.clone();
            assertArrayEquals(expected, QueueSolution04.solve(first, second, plan));
            assertArrayEquals(firstBefore, first);
            assertArrayEquals(secondBefore, second);
        });

        finish("QueueSolution04", passed, total);
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

package bridge.stack.test;

import bridge.stack.solution.StackSolution01;

/** 외부 테스트 라이브러리 없이 StackSolution01을 검증한다. */
public final class StackSolution01Test {

    private StackSolution01Test() {
    }

    /*
     * 테스트 범위
     * - events 길이: 1, 일반 길이, 최대 100,000
     * - 번호 절댓값: 하한 1, 중간 940, 상한 1,000
     * - 결과: 정상 0, 첫 위치 오류, 중간 오류, 마지막 뒤 잔여 오류
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "겹쳐 쌓고 정확히 꺼내기", () -> assertEquals(
                0,
                StackSolution01.solve(new int[]{1, 2, -2, -1})
        ));
        passed += runCase(2, "빈 곳에서 처음부터 꺼내기", () -> assertEquals(
                1,
                StackSolution01.solve(new int[]{-1})
        ));
        passed += runCase(3, "최근 번호와 다른 중간 꺼내기", () -> assertEquals(
                3,
                StackSolution01.solve(new int[]{1, 2, -1, -2})
        ));
        passed += runCase(4, "끝까지 처리한 뒤 상자가 남음", () -> assertEquals(
                4,
                StackSolution01.solve(new int[]{1, -1, 940})
        ));
        passed += runCase(5, "번호 하한·중간·상한과 같은 번호 중첩", () -> assertEquals(
                0,
                StackSolution01.solve(new int[]{1, 940, 1_000, 1_000, -1_000, -1_000, -940, -1})
        ));
        passed += runCase(6, "최대 길이 기록", () -> {
            int[] events = new int[100_000];
            for (int index = 0; index < events.length / 2; index++) {
                events[index] = 1_000;
                events[events.length - 1 - index] = -1_000;
            }
            assertEquals(0, StackSolution01.solve(events));
        });

        finish("StackSolution01", passed, total);
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

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }
}

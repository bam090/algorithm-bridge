package bridge.stack.test;

import bridge.stack.solution.StackSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 StackSolution02를 검증한다. */
public final class StackSolution02Test {

    private StackSolution02Test() {
    }

    /*
     * 테스트 범위
     * - previousCheckpoint 길이: 하한 1, 중간 940, 상한 100,000
     * - 이전 번호: 하한 0과 가장 가까운 직전 번호
     * - destination: 첫 체크포인트, 가지 끝, 중간 940, 마지막 100,000
     */
    public static void main(String[] args) {
        int total = 5;
        int passed = 0;

        passed += runCase(1, "가지가 있는 경로와 원본 보존", () -> {
            int[] previous = {0, 1, 2, 2, 4};
            int[] before = previous.clone();
            assertArrayEquals(new int[]{1, 2, 4, 5}, StackSolution02.solve(previous, 5));
            assertArrayEquals(before, previous);
        });
        passed += runCase(2, "길이 하한과 출발점이 목적지", () -> assertArrayEquals(
                new int[]{1},
                StackSolution02.solve(new int[]{0}, 1)
        ));
        passed += runCase(3, "출발점에서 마지막 지점으로 바로 이동", () -> assertArrayEquals(
                new int[]{1, 4},
                StackSolution02.solve(new int[]{0, 1, 1, 1}, 4)
        ));
        passed += runCase(4, "중간 번호 940", () -> {
            int[] previous = new int[940];
            for (int index = 1; index < previous.length; index++) {
                previous[index] = 1;
            }
            assertArrayEquals(new int[]{1, 940}, StackSolution02.solve(previous, 940));
        });
        passed += runCase(5, "최대 길이의 긴 경로", () -> {
            int[] previous = new int[100_000];
            for (int index = 1; index < previous.length; index++) {
                previous[index] = index;
            }

            int[] route = StackSolution02.solve(previous, 100_000);
            if (route.length != 100_000) {
                throw new AssertionError("expected length=100000, actual length=" + route.length);
            }
            for (int index = 0; index < route.length; index++) {
                if (route[index] != index + 1) {
                    throw new AssertionError("index=" + index + ", actual=" + route[index]);
                }
            }
        });

        finish("StackSolution02", passed, total);
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

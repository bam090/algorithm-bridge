package bridge.stack.test;

import bridge.stack.solution.StackSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 StackSolution03을 검증한다. */
public final class StackSolution03Test {

    private StackSolution03Test() {
    }

    /*
     * 테스트 범위
     * - operations 길이: 하한 1, 일반 길이, 상한 200
     * - 필름 번호: 하한 1, 중간 940, 상한 1,000
     * - 결과: 시작점별 성공 높이 1 이상, 같은 번호 중첩, 실패 시작점 -1
     */
    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "시작점별 최대 높이와 실패 표시", () -> assertArrayEquals(
                new int[]{2, -1, 2, -1, -1, -1},
                StackSolution03.solve(new int[]{1, -1, 2, 3, -3, -2})
        ));
        passed += runCase(2, "독립 작업 두 묶음의 성공 시작점", () -> assertArrayEquals(
                new int[]{1, -1, 1, -1},
                StackSolution03.solve(new int[]{1, -1, 2, -2})
        ));
        passed += runCase(3, "성공한 시작점 없음", () -> assertArrayEquals(
                new int[]{-1, -1, -1, -1},
                StackSolution03.solve(new int[]{1, 2, -1, -2})
        ));
        passed += runCase(4, "같은 번호 필름 중첩", () -> assertArrayEquals(
                new int[]{2, -1, -1, -1},
                StackSolution03.solve(new int[]{1, 1, -1, -1})
        ));
        passed += runCase(5, "길이 하한에서 마지막 잔여 확인", () -> assertArrayEquals(
                new int[]{-1},
                StackSolution03.solve(new int[]{1})
        ));
        passed += runCase(6, "번호 하한·중간·상한과 원본 보존", () -> {
            int[] operations = {1, 940, 1_000, -1_000, -940, -1};
            int[] before = operations.clone();
            assertArrayEquals(
                    new int[]{3, -1, -1, -1, -1, -1},
                    StackSolution03.solve(operations)
            );
            if (!Arrays.equals(before, operations)) {
                throw new AssertionError("operations 원본이 바뀌었습니다.");
            }
        });
        passed += runCase(7, "최대 길이와 최대 높이", () -> {
            int[] operations = new int[200];
            for (int index = 0; index < 100; index++) {
                operations[index] = 1_000;
                operations[operations.length - 1 - index] = -1_000;
            }
            int[] expected = new int[200];
            Arrays.fill(expected, -1);
            expected[0] = 100;
            assertArrayEquals(expected, StackSolution03.solve(operations));
        });

        finish("StackSolution03", passed, total);
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

package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution01;

import java.util.Arrays;

public final class SimulationSolution01Test {

    private static int passed;
    private static int failed;

    private SimulationSolution01Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * minimumValue | -1_000 이상 1_000 이하
     * initialValue | minimumValue 이상 maximumValue 이하
     * maximumValue | -1_000 이상 1_000 이하
     * commands.length | 0 이상 2_000 이하
     * commands[i] | "UP" 또는 "DOWN"
     */
    public static void main(String[] args) {
        testExampleAndRejectedCommand();
        testNoCommandsAndOrdinaryMiddleValue();
        testMinimumBoundary();
        testZeroAndMaximumBoundary();
        testOrdinaryValueWithoutRejection();
        testMaximumCommandCount();
        finish();
    }

    private static void testExampleAndRejectedCommand() {
        check("정상 입력과 거절된 명령", 0, new String[]{"UP", "UP", "DOWN"}, -1, 1,
                new int[]{0, 1});
    }

    private static void testNoCommandsAndOrdinaryMiddleValue() {
        check("빈 명령 배열과 일반 중간값 940", 940, new String[0], 0, 1_000,
                new int[]{940, 0});
    }

    private static void testMinimumBoundary() {
        check("최솟값 하한에서 아래 명령 거절", -1_000, new String[]{"DOWN", "UP"}, -1_000, 1_000,
                new int[]{-999, 1});
    }

    private static void testZeroAndMaximumBoundary() {
        check("0과 최댓값 상한에서 후보 확인", 1_000,
                new String[]{"UP", "DOWN", "UP", "UP"}, 0, 1_000,
                new int[]{1_000, 2});
    }

    private static void testOrdinaryValueWithoutRejection() {
        check("일반값의 연속 변화", 940, new String[]{"DOWN", "DOWN", "UP"}, -1_000, 1_000,
                new int[]{939, 0});
    }

    private static void testMaximumCommandCount() {
        String[] commands = new String[2_000];
        Arrays.fill(commands, "UP");
        check("명령 최대 길이와 누적 거절", 0, commands, 0, 1_000,
                new int[]{1_000, 1_000});
    }

    private static void check(String name, int initialValue, String[] commands,
                              int minimumValue, int maximumValue, int[] expected) {
        String[] original = commands.clone();
        int[] actual;
        try {
            actual = SimulationSolution01.solve(initialValue, commands, minimumValue, maximumValue);
        } catch (AssertionError | RuntimeException exception) {
            failed++;
            System.out.println("[FAIL] " + name + ": "
                    + exception.getClass().getSimpleName() + " - " + exception.getMessage());
            return;
        }
        boolean originalPreserved = Arrays.equals(commands, original);
        boolean success = Arrays.equals(actual, expected) && originalPreserved;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalPreserved=" + originalPreserved);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] SimulationSolution01: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SimulationSolution01 실패: " + failed + "건");
        }
    }
}

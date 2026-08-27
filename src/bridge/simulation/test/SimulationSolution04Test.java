package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution04;

import java.util.Arrays;

public final class SimulationSolution04Test {

    private static int passed;
    private static int failed;

    private SimulationSolution04Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * number | 0 이상 9_999_999_999_999_999 이하
     */
    public static void main(String[] args) {
        testExampleAndOrdinaryMiddleValue();
        testLowerBoundZero();
        testOneDigitWithoutTransform();
        testZeroDigitInOneTransform();
        testZerosAcrossMultipleTransforms();
        testUpperBound();
        finish();
    }

    private static void testExampleAndOrdinaryMiddleValue() {
        check("일반 중간값 940", 940, new int[]{4, 2, 1});
    }

    private static void testLowerBoundZero() {
        check("하한 0은 변환하지 않음", 0, new int[]{0, 0, 0});
    }

    private static void testOneDigitWithoutTransform() {
        check("한 자리 상한 9", 9, new int[]{9, 0, 0});
    }

    private static void testZeroDigitInOneTransform() {
        check("두 자리 수의 0 세기", 10, new int[]{1, 1, 1});
    }

    private static void testZerosAcrossMultipleTransforms() {
        check("여러 변환에서 0 누적", 1_000_000_009L, new int[]{1, 2, 9});
    }

    private static void testUpperBound() {
        check("long 입력 상한", 9_999_999_999_999_999L, new int[]{9, 2, 0});
    }

    private static void check(String name, long number, int[] expected) {
        int[] actual;
        try {
            actual = SimulationSolution04.solve(number);
        } catch (AssertionError | RuntimeException exception) {
            failed++;
            System.out.println("[FAIL] " + name + ": "
                    + exception.getClass().getSimpleName() + " - " + exception.getMessage());
            return;
        }
        boolean success = Arrays.equals(actual, expected);

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }

    private static void finish() {
        System.out.println("[RESULT] SimulationSolution04: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SimulationSolution04 실패: " + failed + "건");
        }
    }
}

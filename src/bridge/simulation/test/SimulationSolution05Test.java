package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution05;

import java.util.Arrays;

public final class SimulationSolution05Test {

    private static int passed;
    private static int failed;

    private SimulationSolution05Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * weights.length | 0 이상 100_000 이하
     * weights[i] | 0 이상 1_000_000_000 이하
     * leftCapacity | 0 이상 100_000_000_000_000 이하
     * rightCapacity | 0 이상 100_000_000_000_000 이하
     */
    public static void main(String[] args) {
        testExample();
        testSeveralValidBoundaries();
        testEmptyAndOneItemInputs();
        testZeroWeightsAndCapacityLowerBounds();
        testOrdinaryAndMaximumWeightValues();
        testAsymmetricCapacities();
        testCapacityUpperBounds();
        testMaximumLengthAndLongAccumulation();
        finish();
    }

    private static void testExample() {
        check("정상 입력", new int[]{2, 4, 3, 1}, 6, 5, 1);
    }

    private static void testSeveralValidBoundaries() {
        check("여러 경계가 조건을 만족함", new int[]{1, 1, 1, 1}, 3, 3, 3);
    }

    private static void testEmptyAndOneItemInputs() {
        check("빈 배열에는 경계가 없음", new int[0], 0, 0, 0);
        check("원소 하나에는 경계가 없음", new int[]{940}, 940, 0, 0);
    }

    private static void testZeroWeightsAndCapacityLowerBounds() {
        check("무게와 용량 하한 0", new int[]{0, 0, 0}, 0, 0, 2);
    }

    private static void testOrdinaryAndMaximumWeightValues() {
        check("0·940·무게 상한", new int[]{0, 940, 1_000_000_000}, 940, 1_000_000_000L, 1);
    }

    private static void testAsymmetricCapacities() {
        check("왼쪽과 오른쪽 한도가 다름", new int[]{5, 5, 5}, 5, 10, 1);
    }

    private static void testCapacityUpperBounds() {
        check("용량 상한", new int[]{1_000_000_000, 1_000_000_000},
                100_000_000_000_000L, 100_000_000_000_000L, 1);
    }

    private static void testMaximumLengthAndLongAccumulation() {
        int[] weights = new int[100_000];
        Arrays.fill(weights, 1_000_000_000);
        check("최대 길이와 int를 넘는 누적합", weights,
                50_000_000_000_000L, 50_000_000_000_000L, 1);
    }

    private static void check(String name, int[] weights, long leftCapacity,
                              long rightCapacity, int expected) {
        int[] original = weights.clone();
        int actual;
        try {
            actual = SimulationSolution05.solve(weights, leftCapacity, rightCapacity);
        } catch (AssertionError | RuntimeException exception) {
            failed++;
            System.out.println("[FAIL] " + name + ": "
                    + exception.getClass().getSimpleName() + " - " + exception.getMessage());
            return;
        }
        boolean originalPreserved = Arrays.equals(weights, original);
        boolean success = actual == expected && originalPreserved;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + expected
                    + ", actual=" + actual
                    + ", originalPreserved=" + originalPreserved);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] SimulationSolution05: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SimulationSolution05 실패: " + failed + "건");
        }
    }
}

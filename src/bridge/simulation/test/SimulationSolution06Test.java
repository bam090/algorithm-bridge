package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution06;

public final class SimulationSolution06Test {

    private static int passed;
    private static int failed;

    private SimulationSolution06Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * totalActions | 1 이상 1_000_000_000_000 이하
     * reportInterval | 1 이상 1_000 이하
     */
    public static void main(String[] args) {
        testNearerFactorsCanFailExtraCondition();
        testTotalActionsLowerBound();
        testOrdinaryMiddleValue();
        testNoValidPair();
        testReportIntervalUpperBound();
        testPrimeNumber();
        testTotalActionsUpperBound();
        finish();
    }

    private static void testNearerFactorsCanFailExtraCondition() {
        check("여러 약수 쌍이 추가 조건을 만족함", 36, 5, 2);
    }

    private static void testTotalActionsLowerBound() {
        check("전체 작업 수 하한", 1, 2, 1);
    }

    private static void testOrdinaryMiddleValue() {
        check("일반 중간값 940", 940, 4, 2);
    }

    private static void testNoValidPair() {
        check("추가 조건을 만족하는 쌍이 없음", 6, 4, 0);
    }

    private static void testReportIntervalUpperBound() {
        check("보고 간격 상한까지 후보 확인", 999, 1_000, 1);
    }

    private static void testPrimeNumber() {
        check("소수의 유일한 약수 쌍", 997, 499, 1);
    }

    private static void testTotalActionsUpperBound() {
        check("전체 작업 수 상한·보고 간격 하한·제곱 쌍", 1_000_000_000_000L, 1, 85);
    }

    private static void check(String name, long totalActions, int reportInterval, int expected) {
        int actual;
        try {
            actual = SimulationSolution06.solve(totalActions, reportInterval);
        } catch (AssertionError | RuntimeException exception) {
            failed++;
            System.out.println("[FAIL] " + name + ": "
                    + exception.getClass().getSimpleName() + " - " + exception.getMessage());
            return;
        }
        boolean success = actual == expected;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + expected
                    + ", actual=" + actual);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] SimulationSolution06: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SimulationSolution06 실패: " + failed + "건");
        }
    }
}

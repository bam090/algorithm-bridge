package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GreedySolution03을 검증한다. */
public final class GreedySolution03Test {

    private static final double TOLERANCE = 1.0e-9;

    private GreedySolution03Test() {
    }

    /*
     * 범위표
     * - 배열 길이: 0, 1, 일반 길이, 최대 100,000
     * - 사용 가능량: 0보다 가까운 소수, 940.0, 상한 1,000.0
     * - 전체 가격: 0.0, 940.0, 상한 1,000,000.0
     * - 필요량: 0.0, 일부 구매가 필요한 소수, 전체 사용 가능량
     * - 대표 오답: 전체 가격만 비교, 마지막 공급처 전부 구매, 비율 동점, 원래 번호 분실, 원본 변경
     */
    public static void main(String[] args) {
        int total = 9;
        int passed = 0;

        passed += runCase(1, "단위 비용순 선택과 원본 보존", () -> {
            double[] available = {2.0, 4.0, 3.0};
            double[] costs = {10.0, 36.0, 21.0};
            double[] availableBefore = available.clone();
            double[] costsBefore = costs.clone();
            assertArrayClose(
                    new double[]{2.0, 0.0, 2.0},
                    GreedySolution03.solve(available, costs, 4.0)
            );
            assertArrayClose(availableBefore, available);
            assertArrayClose(costsBefore, costs);
        });
        passed += runCase(2, "빈 공급처와 필요량 0", () -> assertArrayClose(
                new double[]{},
                GreedySolution03.solve(new double[]{}, new double[]{}, 0.0)
        ));
        passed += runCase(3, "공급처가 있어도 필요량 0", () -> assertArrayClose(
                new double[]{0.0, 0.0},
                GreedySolution03.solve(new double[]{1.0, 2.0}, new double[]{10.0, 20.0}, 0.0)
        ));
        passed += runCase(4, "단위 비용 동점은 원래 번호 우선", () -> assertArrayClose(
                new double[]{1.0, 1.0, 0.0},
                GreedySolution03.solve(
                        new double[]{1.0, 2.0, 3.0},
                        new double[]{5.0, 10.0, 30.0},
                        2.0
                )
        ));
        passed += runCase(5, "전체 가격보다 단위 비용을 우선", () -> assertArrayClose(
                new double[]{10.0, 0.0},
                GreedySolution03.solve(
                        new double[]{100.0, 10.0},
                        new double[]{100.0, 20.0},
                        10.0
                )
        ));
        passed += runCase(6, "가격 0·상한과 사용 가능량 940·1000", () -> assertArrayClose(
                new double[]{940.0, 0.5},
                GreedySolution03.solve(
                        new double[]{940.0, 1_000.0},
                        new double[]{0.0, 1_000_000.0},
                        940.5
                )
        ));
        passed += runCase(7, "소수 구매량은 허용 오차로 비교", () -> assertArrayClose(
                new double[]{0.1, 0.2},
                GreedySolution03.solve(
                        new double[]{0.1, 0.2},
                        new double[]{0.2, 0.6},
                        0.3
                )
        ));
        passed += runCase(8, "공급처 하나의 양과 가격 상한", () -> assertArrayClose(
                new double[]{1_000.0},
                GreedySolution03.solve(new double[]{1_000.0}, new double[]{1_000_000.0}, 1_000.0)
        ));
        passed += runCase(9, "최대 100000개와 마지막 절반 구매", () -> {
            int size = 100_000;
            double[] available = new double[size];
            double[] costs = new double[size];
            double[] expected = new double[size];
            Arrays.fill(available, 1.0);
            Arrays.fill(costs, 940.0);
            Arrays.fill(expected, 1.0);
            expected[size - 1] = 0.5;

            double[] availableBefore = available.clone();
            double[] costsBefore = costs.clone();
            assertArrayClose(expected, GreedySolution03.solve(available, costs, 99_999.5));
            assertArrayClose(availableBefore, available);
            assertArrayClose(costsBefore, costs);
        });

        finish("GreedySolution03", passed, total);
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

    private static void assertArrayClose(double[] expected, double[] actual) {
        if (expected.length != actual.length) {
            throw new AssertionError("expected length=" + expected.length + ", actual length=" + actual.length);
        }
        for (int i = 0; i < expected.length; i++) {
            if (!Double.isFinite(actual[i]) || Math.abs(expected[i] - actual[i]) > TOLERANCE) {
                throw new AssertionError("index=" + i + ", expected=" + expected[i] + ", actual=" + actual[i]);
            }
        }
    }
}

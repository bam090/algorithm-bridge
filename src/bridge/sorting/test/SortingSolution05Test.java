package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution05;

import java.util.Arrays;

public final class SortingSolution05Test {

    private static int passed;
    private static int failed;

    private SortingSolution05Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * inspections.length | 0 이상 100_000 이하
     * inspections[i] | -1_000 이상 1_000 이하
     */
    public static void main(String[] args) {
        runCase("정상 입력", SortingSolution05Test::testExample);
        runCase("빈 입력", SortingSolution05Test::testEmptyInput);
        runCase("원소 하나", SortingSolution05Test::testOneInspection);
        runCase("같은 시각 두 개", SortingSolution05Test::testDuplicateTimes);
        runCase("값 하한·0·940·상한", SortingSolution05Test::testValueBoundsZeroAndOrdinaryMiddle);
        runCase("두 원소와 가능한 최대 간격", SortingSolution05Test::testTwoExtremeValues);
        runCase("최대 길이", SortingSolution05Test::testMaximumLength);
        finish();
    }

    private static void runCase(String name, Runnable test) {
        try {
            test.run();
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error);
        }
    }

    private static void testExample() {
        check("정상 입력", new int[]{40, 5, 17, 20}, 3);
    }

    private static void testEmptyInput() {
        check("빈 입력", new int[0], -1);
    }

    private static void testOneInspection() {
        check("원소 하나", new int[]{940}, -1);
    }

    private static void testDuplicateTimes() {
        check("같은 시각 두 개", new int[]{8, 0, 8, 1_000}, 0);
    }

    private static void testValueBoundsZeroAndOrdinaryMiddle() {
        check("값 하한·0·940·상한", new int[]{1_000, -1_000, 940, 0}, 60);
    }

    private static void testTwoExtremeValues() {
        check("두 원소와 가능한 최대 간격", new int[]{1_000, -1_000}, 2_000);
    }

    private static void testMaximumLength() {
        int[] inspections = new int[100_000];
        Arrays.fill(inspections, 940);
        check("최대 길이", inspections, 0);
    }

    private static void check(String name, int[] inspections, int expected) {
        int[] original = inspections.clone();
        int actual = SortingSolution05.solve(inspections);
        boolean originalPreserved = Arrays.equals(inspections, original);
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
        System.out.println("[RESULT] SortingSolution05: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SortingSolution05 실패: " + failed + "건");
        }
    }
}

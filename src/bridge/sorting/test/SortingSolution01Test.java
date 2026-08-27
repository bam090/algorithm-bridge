package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution01;

import java.util.Arrays;

public final class SortingSolution01Test {

    private static int passed;
    private static int failed;

    private SortingSolution01Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * readings.length | 0 이상 10_000 이하
     * readings[i] | -1_000 이상 1_000 이하
     * minimumCount | 1 이상 10_000 이하
     */
    public static void main(String[] args) {
        runCase("정상 입력과 횟수 조건", SortingSolution01Test::testExampleAndFrequencyFilter);
        runCase("빈 입력", SortingSolution01Test::testEmptyInput);
        runCase("원소 하나와 최소 횟수 하한", SortingSolution01Test::testOneValueAndMinimumCountLowerBound);
        runCase("조건을 만족하는 값이 없음", SortingSolution01Test::testNoValueMeetsCondition);
        runCase("값 하한·0·940·상한과 정렬", SortingSolution01Test::testValueBoundsZeroAndOrdinaryMiddle);
        runCase("최대 길이와 최소 횟수 상한", SortingSolution01Test::testMaximumLengthAndMinimumCountUpperBound);
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

    private static void testExampleAndFrequencyFilter() {
        check("정상 입력과 횟수 조건", new int[]{3, -1, 3, 2, -1, 3, 4}, 2,
                new int[]{-1, -1, 3, 3, 3});
    }

    private static void testEmptyInput() {
        check("빈 입력", new int[0], 1, new int[0]);
    }

    private static void testOneValueAndMinimumCountLowerBound() {
        check("원소 하나와 최소 횟수 하한", new int[]{940}, 1, new int[]{940});
    }

    private static void testNoValueMeetsCondition() {
        check("조건을 만족하는 값이 없음", new int[]{0, 940, 1_000}, 2, new int[0]);
    }

    private static void testValueBoundsZeroAndOrdinaryMiddle() {
        check("값 하한·0·940·상한과 정렬", new int[]{1_000, -1_000, 940, 0, -1_000, 940}, 2,
                new int[]{-1_000, -1_000, 940, 940});
    }

    private static void testMaximumLengthAndMinimumCountUpperBound() {
        int[] readings = new int[10_000];
        Arrays.fill(readings, 0);
        int[] expected = new int[10_000];
        check("최대 길이와 최소 횟수 상한", readings, 10_000, expected);
    }

    private static void check(String name, int[] readings, int minimumCount, int[] expected) {
        int[] original = readings.clone();
        int[] actual = SortingSolution01.solve(readings, minimumCount);
        boolean originalPreserved = Arrays.equals(readings, original);
        boolean newArrayReturned = actual != readings;
        boolean success = Arrays.equals(actual, expected) && originalPreserved && newArrayReturned;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalPreserved=" + originalPreserved
                    + ", newArrayReturned=" + newArrayReturned);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] SortingSolution01: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SortingSolution01 실패: " + failed + "건");
        }
    }
}

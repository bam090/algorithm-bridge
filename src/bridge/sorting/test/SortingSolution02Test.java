package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution02;

import java.util.Arrays;

public final class SortingSolution02Test {

    private static int passed;
    private static int failed;

    private SortingSolution02Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * first.length | 0 이상 10_000 이하
     * second.length | 0 이상 10_000 이하
     * first.length + second.length | 1 이상 20_000 이하
     * first[i], second[i] | -1_000 이상 1_000 이하
     * rank | 1 이상 first.length + second.length 이하
     */
    public static void main(String[] args) {
        runCase("정상 입력과 같은 값", SortingSolution02Test::testExampleAndDuplicateValues);
        runCase("첫 배열이 비었을 때", SortingSolution02Test::testFirstArrayEmpty);
        runCase("둘째 배열이 비고 마지막 순번", SortingSolution02Test::testSecondArrayEmptyAndLastRank);
        runCase("순번 하한", SortingSolution02Test::testFirstRank);
        runCase("값 하한·0·940·상한", SortingSolution02Test::testValueBoundsZeroAndOrdinaryMiddle);
        runCase("두 배열 최대 길이와 한쪽 잔여", SortingSolution02Test::testMaximumLengthsAndRemainingValues);
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

    private static void testExampleAndDuplicateValues() {
        check("정상 입력과 같은 값", new int[]{1, 4, 8}, new int[]{2, 4, 10}, 4, 4);
    }

    private static void testFirstArrayEmpty() {
        check("첫 배열이 비었을 때", new int[0], new int[]{-1_000, 0, 940, 1_000}, 3, 940);
    }

    private static void testSecondArrayEmptyAndLastRank() {
        check("둘째 배열이 비고 마지막 순번", new int[]{-1_000, 0, 940, 1_000}, new int[0], 4, 1_000);
    }

    private static void testFirstRank() {
        check("순번 하한", new int[]{0, 940}, new int[]{-1_000, 1_000}, 1, -1_000);
    }

    private static void testValueBoundsZeroAndOrdinaryMiddle() {
        check("값 하한·0·940·상한", new int[]{-1_000, 940}, new int[]{0, 1_000}, 3, 940);
    }

    private static void testMaximumLengthsAndRemainingValues() {
        int[] first = new int[10_000];
        int[] second = new int[10_000];
        Arrays.fill(first, -1_000);
        Arrays.fill(second, 1_000);
        check("두 배열 최대 길이와 한쪽 잔여", first, second, 10_001, 1_000);
    }

    private static void check(String name, int[] first, int[] second, int rank, int expected) {
        int[] originalFirst = first.clone();
        int[] originalSecond = second.clone();
        int actual = SortingSolution02.solve(first, second, rank);
        boolean originalsPreserved = Arrays.equals(first, originalFirst) && Arrays.equals(second, originalSecond);
        boolean success = actual == expected && originalsPreserved;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + expected
                    + ", actual=" + actual
                    + ", originalsPreserved=" + originalsPreserved);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] SortingSolution02: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SortingSolution02 실패: " + failed + "건");
        }
    }
}

package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution04;

import java.util.Arrays;

public final class SortingSolution04Test {

    private static int passed;
    private static int failed;

    private SortingSolution04Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * measurements.length | 1 이상 10_000 이하
     * measurements[i] | -1_000 이상 1_000 이하
     * startIndex | 0 이상 endIndex 이하
     * endIndex | startIndex 이상 measurements.length - 1 이하
     */
    public static void main(String[] args) {
        runCase("정상 구간과 중복 간격", SortingSolution04Test::testExampleWithDuplicateValues);
        runCase("원소 하나 선택", SortingSolution04Test::testOneSelectedValue);
        runCase("전체 구간과 값 하한·0·940·상한", SortingSolution04Test::testFullRangeAndValueBounds);
        runCase("선택하지 않은 양끝 제외", SortingSolution04Test::testMiddleRangeOnly);
        runCase("첫 위치부터 마지막 위치까지 포함", SortingSolution04Test::testFirstAndLastIndexes);
        runCase("최대 길이", SortingSolution04Test::testMaximumLength);
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

    private static void testExampleWithDuplicateValues() {
        check("정상 구간과 중복 간격", new int[]{8, 3, 12, 3, 7}, 1, 4, new int[]{0, 4, 5});
    }

    private static void testOneSelectedValue() {
        check("원소 하나 선택", new int[]{940}, 0, 0, new int[0]);
    }

    private static void testFullRangeAndValueBounds() {
        check("전체 구간과 값 하한·0·940·상한", new int[]{1_000, 0, -1_000, 940}, 0, 3,
                new int[]{1_000, 940, 60});
    }

    private static void testMiddleRangeOnly() {
        check("선택하지 않은 양끝 제외", new int[]{-1_000, 9, 2, 5, 1_000}, 1, 3,
                new int[]{3, 4});
    }

    private static void testFirstAndLastIndexes() {
        check("첫 위치부터 마지막 위치까지 포함", new int[]{4, 4}, 0, 1, new int[]{0});
    }

    private static void testMaximumLength() {
        int[] measurements = new int[10_000];
        Arrays.fill(measurements, 940);
        int[] expected = new int[9_999];
        check("최대 길이", measurements, 0, measurements.length - 1, expected);
    }

    private static void check(
            String name,
            int[] measurements,
            int startIndex,
            int endIndex,
            int[] expected
    ) {
        int[] original = measurements.clone();
        int[] actual = SortingSolution04.solve(measurements, startIndex, endIndex);
        boolean originalPreserved = Arrays.equals(measurements, original);
        boolean newArrayReturned = actual != measurements;
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
        System.out.println("[RESULT] SortingSolution04: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SortingSolution04 실패: " + failed + "건");
        }
    }
}

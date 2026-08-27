package bridge.hash.test;

import bridge.hash.solution.HashSolution04;

import java.util.Arrays;

public final class HashSolution04Test {

    private static int passed;
    private static int failed;

    private HashSolution04Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * deviceIds.length | 0 이상 100_000 이하
     * factors.length, readings.length | deviceIds.length와 같음
     * deviceIds[i].length() | 1 이상 20 이하
     * factors[i] | 0 이상 1_000_000 이하
     * readings[i] | -1_000_000 이상 1_000_000 이하
     */
    public static void main(String[] args) {
        runCase("장치별 마지막 계수 적용", HashSolution04Test::testFinalFactorForEveryRecord);
        runCase("빈 기록", HashSolution04Test::testEmptyRecords);
        runCase("원소 하나와 계수 하한", HashSolution04Test::testOneRecordWithZeroFactor);
        runCase("읽는 즉시 계산하는 오답 방지", HashSolution04Test::testLaterFactorChangesEarlierResult);
        runCase("ID 길이와 숫자 하한·0·중간값·상한", HashSolution04Test::testIdentifierAndNumberBounds);
        runCase("int 곱셈 범위를 넘는 양수 결과", HashSolution04Test::testLongResultPreventsOverflow);
        runCase("기록 길이 상한", HashSolution04Test::testMaximumRecordLength);

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

    private static void testFinalFactorForEveryRecord() {
        String[] ids = {"A", "B", "A"};
        int[] factors = {2, 3, 4};
        int[] readings = {10, 5, -2};
        check("장치별 마지막 계수 적용", ids, factors, readings, new long[]{40, 15, -8});
    }

    private static void testEmptyRecords() {
        check("빈 기록", new String[0], new int[0], new int[0], new long[0]);
    }

    private static void testOneRecordWithZeroFactor() {
        check("원소 하나와 계수 하한", new String[]{"A"}, new int[]{0}, new int[]{940}, new long[]{0});
    }

    private static void testLaterFactorChangesEarlierResult() {
        String[] ids = {"A", "A"};
        int[] factors = {2, 5};
        int[] readings = {10, 20};
        check("읽는 즉시 계산하는 오답 방지", ids, factors, readings, new long[]{50, 100});
    }

    private static void testIdentifierAndNumberBounds() {
        String longId = "abcdefghijklmnopqrst";
        String[] ids = {"x", longId, "x", longId};
        int[] factors = {0, 940, 1_000_000, 940};
        int[] readings = {-1_000_000, 0, 940, 1_000_000};
        long[] expected = {-1_000_000_000_000L, 0, 940_000_000L, 940_000_000L};
        check("ID 길이와 숫자 하한·0·중간값·상한", ids, factors, readings, expected);
    }

    private static void testLongResultPreventsOverflow() {
        check(
                "int 곱셈 범위를 넘는 양수 결과",
                new String[]{"A"},
                new int[]{1_000_000},
                new int[]{1_000_000},
                new long[]{1_000_000_000_000L}
        );
    }

    private static void testMaximumRecordLength() {
        int length = 100_000;
        String[] ids = new String[length];
        int[] factors = new int[length];
        int[] readings = new int[length];
        long[] expected = new long[length];

        for (int index = 0; index < length; index++) {
            boolean even = index % 2 == 0;
            ids[index] = even ? "A" : "B";
            factors[index] = even ? 940 : 1_000;
            readings[index] = 940;
            expected[index] = even ? 883_600L : 940_000L;
        }
        check("기록 길이 상한", ids, factors, readings, expected);
    }

    private static void check(
            String name,
            String[] deviceIds,
            int[] factors,
            int[] readings,
            long[] expected
    ) {
        String[] originalDeviceIds = deviceIds.clone();
        int[] originalFactors = factors.clone();
        int[] originalReadings = readings.clone();
        long[] actual = HashSolution04.solve(deviceIds, factors, readings);
        boolean originalsPreserved = Arrays.equals(deviceIds, originalDeviceIds)
                && Arrays.equals(factors, originalFactors)
                && Arrays.equals(readings, originalReadings);
        boolean success = Arrays.equals(actual, expected) && originalsPreserved;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalsPreserved=" + originalsPreserved);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] HashSolution04: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("HashSolution04 실패: " + failed + "건");
        }
    }
}

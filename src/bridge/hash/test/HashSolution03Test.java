package bridge.hash.test;

import bridge.hash.solution.HashSolution03;

import java.util.Arrays;

public final class HashSolution03Test {

    private static int passed;
    private static int failed;

    private HashSolution03Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * stream.length | 0 이상 100_000 이하
     * pattern.length | 1 이상 1_000 이하
     * stream[i], pattern[i] | -1_000 이상 1_000 이하
     */
    public static void main(String[] args) {
        runCase("순서가 달라도 같은 두 창", HashSolution03Test::testMatchingWindowsInDifferentOrders);
        runCase("빈 전체 배열", HashSolution03Test::testEmptyStream);
        runCase("pattern 길이 하한", HashSolution03Test::testPatternLengthOne);
        runCase("중복 횟수와 개수 0인 key 제거", HashSolution03Test::testDuplicateCountsAndZeroCountRemoval);
        runCase("pattern이 더 긴 입력", HashSolution03Test::testPatternLongerThanStream);
        runCase("값 하한·0·중간값·상한", HashSolution03Test::testValueBoundsAndMiddleValue);
        runCase("길이 상한과 서로 다른 값 1,000개", HashSolution03Test::testMaximumLengths);

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

    private static void testMatchingWindowsInDifferentOrders() {
        int[] stream = {2, 1, 2, 3, 2, 2, 1};
        int[] pattern = {1, 2, 2};
        check("순서가 달라도 같은 두 창", stream, pattern, new int[]{1, 5});
    }

    private static void testEmptyStream() {
        check("빈 전체 배열", new int[0], new int[]{0}, new int[0]);
    }

    private static void testPatternLengthOne() {
        int[] stream = {-1_000, 940, 0, 940, 1_000};
        check("pattern 길이 하한", stream, new int[]{940}, new int[]{2, 4});
    }

    private static void testDuplicateCountsAndZeroCountRemoval() {
        int[] stream = {1, 1, 2, 1, 1};
        int[] pattern = {1, 1};
        check("중복 횟수와 개수 0인 key 제거", stream, pattern, new int[]{1, 4});
    }

    private static void testPatternLongerThanStream() {
        check("pattern이 더 긴 입력", new int[]{1}, new int[]{1, 2}, new int[0]);
    }

    private static void testValueBoundsAndMiddleValue() {
        int[] stream = {-1_000, 0, 940, 1_000, 0, -1_000};
        int[] pattern = {-1_000, 0};
        check("값 하한·0·중간값·상한", stream, pattern, new int[]{1, 5});
    }

    private static void testMaximumLengths() {
        int[] stream = new int[100_000];
        int[] pattern = new int[1_000];
        for (int index = 0; index < pattern.length; index++) {
            pattern[index] = index - 1_000;
        }
        for (int index = 0; index < stream.length; index++) {
            stream[index] = pattern[index % pattern.length];
        }

        int[] expected = new int[99_001];
        for (int index = 0; index < expected.length; index++) {
            expected[index] = index + 1;
        }
        check("길이 상한과 서로 다른 값 1,000개", stream, pattern, expected);
    }

    private static void check(String name, int[] stream, int[] pattern, int[] expected) {
        int[] originalStream = stream.clone();
        int[] originalPattern = pattern.clone();
        int[] actual = HashSolution03.solve(stream, pattern);
        boolean originalsPreserved = Arrays.equals(stream, originalStream)
                && Arrays.equals(pattern, originalPattern);
        boolean newArrayReturned = actual != stream && actual != pattern;
        boolean success = Arrays.equals(actual, expected) && originalsPreserved && newArrayReturned;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalsPreserved=" + originalsPreserved
                    + ", newArrayReturned=" + newArrayReturned);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] HashSolution03: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("HashSolution03 실패: " + failed + "건");
        }
    }
}

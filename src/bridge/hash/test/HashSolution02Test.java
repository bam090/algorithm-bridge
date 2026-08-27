package bridge.hash.test;

import bridge.hash.solution.HashSolution02;

import java.util.Arrays;

public final class HashSolution02Test {

    private static int passed;
    private static int failed;

    private HashSolution02Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * expectedCodes.length | 0 이상 100_000 이하
     * actualCodes.length | 0 이상 100_000 이하
     * expectedCodes[i].length(), actualCodes[i].length() | 1 이상 20 이하
     */
    public static void main(String[] args) {
        runCase("양쪽에 남은 차이를 모두 계산", HashSolution02Test::testDifferencesRemainOnBothSides);
        runCase("두 빈 목록", HashSolution02Test::testBothListsEmpty);
        runCase("원소 하나가 정확히 일치", HashSolution02Test::testOneMatchingItem);
        runCase("Set으로 중복 횟수를 잃는 오답 방지", HashSolution02Test::testDuplicateCountsMatter);
        runCase("한쪽만 빈 목록", HashSolution02Test::testOneSideEmpty);
        runCase("코드 길이 하한·상한과 숫자 모양 코드", HashSolution02Test::testCodeLengthBoundsAndNumberLikeCodes);
        runCase("두 배열 길이 상한", HashSolution02Test::testMaximumLengths);

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

    private static void testDifferencesRemainOnBothSides() {
        String[] expected = {"A", "A", "B"};
        String[] actual = {"A", "C", "C"};
        check("양쪽에 남은 차이를 모두 계산", expected, actual, 4);
    }

    private static void testBothListsEmpty() {
        check("두 빈 목록", new String[0], new String[0], 0);
    }

    private static void testOneMatchingItem() {
        check("원소 하나가 정확히 일치", new String[]{"940"}, new String[]{"940"}, 0);
    }

    private static void testDuplicateCountsMatter() {
        String[] expected = {"a", "a", "b"};
        String[] actual = {"a", "b", "b"};
        check("Set으로 중복 횟수를 잃는 오답 방지", expected, actual, 2);
    }

    private static void testOneSideEmpty() {
        check("한쪽만 빈 목록", new String[]{"940"}, new String[0], 1);
    }

    private static void testCodeLengthBoundsAndNumberLikeCodes() {
        String longCode = "abcdefghijklmnopqrst";
        String[] expected = {"x", longCode, "0", "940"};
        String[] actual = {"x", longCode, "1000", "940"};
        check("코드 길이 하한·상한과 숫자 모양 코드", expected, actual, 2);
    }

    private static void testMaximumLengths() {
        String[] expected = new String[100_000];
        String[] actual = new String[100_000];
        Arrays.fill(expected, "940");
        Arrays.fill(actual, 0, 50_000, "940");
        Arrays.fill(actual, 50_000, actual.length, "0");
        check("두 배열 길이 상한", expected, actual, 100_000);
    }

    private static void check(String name, String[] expectedCodes, String[] actualCodes, int expected) {
        String[] originalExpectedCodes = expectedCodes.clone();
        String[] originalActualCodes = actualCodes.clone();
        int actual = HashSolution02.solve(expectedCodes, actualCodes);
        boolean originalsPreserved = Arrays.equals(expectedCodes, originalExpectedCodes)
                && Arrays.equals(actualCodes, originalActualCodes);
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
        System.out.println("[RESULT] HashSolution02: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("HashSolution02 실패: " + failed + "건");
        }
    }
}

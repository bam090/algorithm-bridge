package bridge.hash.test;

import bridge.hash.solution.HashSolution01;

import java.util.Arrays;

public final class HashSolution01Test {

    private static int passed;
    private static int failed;

    private HashSolution01Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * codes.length | 0 이상 100_000 이하
     * codes[i] | -1_000 이상 1_000 이하
     */
    public static void main(String[] args) {
        runCase("처음 완성된 짝", HashSolution01Test::testFirstCompletedPair);
        runCase("빈 입력", HashSolution01Test::testEmptyInput);
        runCase("원소 하나는 짝이 없음", HashSolution01Test::testOneNonZeroCode);
        runCase("현재 값을 먼저 저장하는 오답 방지", HashSolution01Test::testOneZeroDoesNotPairWithItself);
        runCase("서로 다른 두 0은 짝", HashSolution01Test::testTwoZerosMakeAPair);
        runCase("값 하한·중간값·상한", HashSolution01Test::testValueBoundsAndMiddleValue);
        runCase("뒤의 짝보다 처음 짝을 반환", HashSolution01Test::testEarliestPairWins);
        runCase("배열 길이 상한", HashSolution01Test::testMaximumLength);

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

    private static void testFirstCompletedPair() {
        int[] codes = {7, 3, -7, 4};
        check("처음 완성된 짝", codes, 3);
    }

    private static void testEmptyInput() {
        int[] codes = {};
        check("빈 입력", codes, -1);
    }

    private static void testOneNonZeroCode() {
        int[] codes = {940};
        check("원소 하나는 짝이 없음", codes, -1);
    }

    private static void testOneZeroDoesNotPairWithItself() {
        int[] codes = {0};
        check("현재 값을 먼저 저장하는 오답 방지", codes, -1);
    }

    private static void testTwoZerosMakeAPair() {
        int[] codes = {0, 0};
        check("서로 다른 두 0은 짝", codes, 2);
    }

    private static void testValueBoundsAndMiddleValue() {
        int[] codes = {-1_000, 940, 1_000};
        check("값 하한·중간값·상한", codes, 3);
    }

    private static void testEarliestPairWins() {
        int[] codes = {5, 2, -2, -5};
        check("뒤의 짝보다 처음 짝을 반환", codes, 3);
    }

    private static void testMaximumLength() {
        int[] codes = new int[100_000];
        Arrays.fill(codes, 940);
        codes[codes.length - 1] = -940;
        check("배열 길이 상한", codes, 100_000);
    }

    private static void check(String name, int[] codes, int expected) {
        int[] original = codes.clone();
        int actual = HashSolution01.solve(codes);
        boolean success = actual == expected && Arrays.equals(codes, original);

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + expected
                    + ", actual=" + actual
                    + ", originalPreserved=" + Arrays.equals(codes, original));
        }
    }

    private static void finish() {
        System.out.println("[RESULT] HashSolution01: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("HashSolution01 실패: " + failed + "건");
        }
    }
}

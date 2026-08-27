package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 BacktrackingSolution01을 검증한다. */
public final class BacktrackingSolution01Test {

    private static int passed;
    private static int failed;

    private BacktrackingSolution01Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * points.length | 0 이상 15 이하 | 0, 1, 3, 15
     * points[i] | -1,000 이상 1,000 이하 | -1,000, 0, 940, 1,000
     * 결과 길이 | 2^points.length | 1, 2, 8, 32,768
     * 추가 계약 | 위치별 선택, 선택 가지 우선, 원본 보존 | 중복 합, 순서, 원본 배열
     */
    public static void main(String[] args) {
        check("두 위치의 선택 순서", new int[]{2, 5}, new int[]{7, 2, 5, 0});
        check("빈 입력은 합 0 한 가지", new int[0], new int[]{0});
        check("원소 하나와 값 하한", new int[]{-1_000}, new int[]{-1_000, 0});
        check(
                "0·중간값·상한과 위치별 중복 합",
                new int[]{0, 940, 1_000},
                new int[]{1_940, 940, 1_000, 0, 1_940, 940, 1_000, 0}
        );
        runCase("최대 길이의 모든 선택 수", BacktrackingSolution01Test::testMaximumLength);

        finish();
    }

    private static void check(String name, int[] points, int[] expected) {
        runCase(name, () -> {
            int[] original = points.clone();
            int[] actual = BacktrackingSolution01.solve(points);
            assertArrayEquals(expected, actual);
            assertArrayEquals(original, points);
            assertTrue(actual != points, "결과는 입력과 다른 배열이어야 한다.");
        });
    }

    private static void testMaximumLength() {
        int[] points = new int[15];
        Arrays.fill(points, 1);
        int[] original = points.clone();
        int[] actual = BacktrackingSolution01.solve(points);

        int[] expectedFrequency = {
                1, 15, 105, 455, 1_365, 3_003, 5_005, 6_435,
                6_435, 5_005, 3_003, 1_365, 455, 105, 15, 1
        };
        int[] actualFrequency = new int[16];
        for (int sum : actual) {
            assertTrue(0 <= sum && sum <= 15, "최대 길이 결과 합의 범위가 잘못됐다: " + sum);
            actualFrequency[sum]++;
        }

        assertEquals(32_768, actual.length);
        assertEquals(15, actual[0]);
        assertEquals(0, actual[actual.length - 1]);
        assertArrayEquals(expectedFrequency, actualFrequency);
        assertArrayEquals(original, points);
    }

    private static void runCase(String name, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("[PASS] " + name);
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error.getMessage());
        }
    }

    private static void finish() {
        System.out.println("[RESULT] BacktrackingSolution01: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("BacktrackingSolution01 실패: " + failed + "건");
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    "expected=" + Arrays.toString(expected) + ", actual=" + Arrays.toString(actual)
            );
        }
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}

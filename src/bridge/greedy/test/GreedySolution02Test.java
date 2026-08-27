package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GreedySolution02를 검증한다. */
public final class GreedySolution02Test {

    private GreedySolution02Test() {
    }

    /*
     * 범위표
     * - kitCosts 길이: 0, 1, 일반 길이, 최대 100,000
     * - 가격: 0, 940, 상한 1,000,000,000
     * - requiredCount: 0, 1, 일반 중간값, kitCosts.length 전체
     * - 대표 오답: 배열 전체 합산, 비싼 값 우선, requiredCount 0 처리, int 누적, 원본 정렬
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "필요한 개수의 싼 키트와 원본 보존", () -> {
            int[] input = {7_000, 2_000, 4_000, 1_000};
            int[] before = input.clone();
            assertEquals(7_000L, GreedySolution02.solve(input, 3));
            assertIntArrayEquals(before, input);
        });
        passed += runCase(2, "빈 배열에서 0개 선택", () -> assertEquals(
                0L,
                GreedySolution02.solve(new int[]{}, 0)
        ));
        passed += runCase(3, "값이 있어도 0개 선택", () -> assertEquals(
                0L,
                GreedySolution02.solve(new int[]{0, 940, 1_000_000_000}, 0)
        ));
        passed += runCase(4, "0·940·가격 상한을 모두 선택", () -> assertEquals(
                1_000_000_940L,
                GreedySolution02.solve(new int[]{1_000_000_000, 940, 0}, 3)
        ));
        passed += runCase(5, "한 개만 고르면 최솟값", () -> assertEquals(
                4L,
                GreedySolution02.solve(new int[]{6, 4, 5}, 1)
        ));
        passed += runCase(6, "최대 100000개에서 long 누적", () -> {
            int[] input = new int[100_000];
            Arrays.fill(input, 1_000_000_000);
            int[] before = input.clone();
            assertEquals(100_000_000_000_000L, GreedySolution02.solve(input, 100_000));
            assertIntArrayEquals(before, input);
        });

        finish("GreedySolution02", passed, total);
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

    private static void assertEquals(long expected, long actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertIntArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}

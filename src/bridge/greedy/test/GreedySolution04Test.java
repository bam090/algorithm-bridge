package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GreedySolution04를 검증한다. */
public final class GreedySolution04Test {

    private GreedySolution04Test() {
    }

    /*
     * 범위표
     * - weights 길이: 0, 1, 홀수·짝수 일반 길이, 최대 100,000
     * - 무게: 0, 940, 상한 1,000,000,000
     * - 합 범위: 하한·상한과 같은 합, 하한 미만, 상한 초과, 상한 2,000,000,000
     * - 대표 오답: 낮은 합에서 오른쪽 이동, 높은 합에서 왼쪽 이동, 추 재사용, 원본 정렬
     */
    public static void main(String[] args) {
        int total = 9;
        int passed = 0;

        passed += runCase(1, "하한·상한 사이 최대 쌍과 원본 보존", () -> {
            int[] input = {10, 20, 40, 50, 70};
            int[] before = input.clone();
            assertEquals(2, GreedySolution04.solve(input, 60, 80));
            assertArrayEquals(before, input);
        });
        passed += runCase(2, "빈 무게 배열", () -> assertEquals(
                0,
                GreedySolution04.solve(new int[]{}, 0, 0)
        ));
        passed += runCase(3, "추 하나와 940 무게", () -> assertEquals(
                0,
                GreedySolution04.solve(new int[]{940}, 940, 1_880)
        ));
        passed += runCase(4, "0·940과 하한·상한 포함", () -> assertEquals(
                2,
                GreedySolution04.solve(
                        new int[]{0, 940, 60, 1_000},
                        940,
                        1_000
                )
        ));
        passed += runCase(5, "합이 낮으면 가벼운 쪽 이동", () -> assertEquals(
                1,
                GreedySolution04.solve(new int[]{1, 2, 3, 9}, 10, 10)
        ));
        passed += runCase(6, "합이 높으면 무거운 쪽 이동", () -> assertEquals(
                1,
                GreedySolution04.solve(new int[]{1, 8, 9, 10}, 10, 10)
        ));
        passed += runCase(7, "같은 무게의 추는 한 번씩만 사용", () -> assertEquals(
                2,
                GreedySolution04.solve(new int[]{5, 5, 5, 5, 5}, 10, 10)
        ));
        passed += runCase(8, "무게와 합 범위 상한", () -> assertEquals(
                2,
                GreedySolution04.solve(
                        new int[]{0, 940, 1_000_000_000, 1_000_000_000},
                        1_000_000_000,
                        2_000_000_000
                )
        ));
        passed += runCase(9, "최대 100000개와 원본 보존", () -> {
            int[] input = new int[100_000];
            Arrays.fill(input, 940);
            int[] before = input.clone();
            assertEquals(50_000, GreedySolution04.solve(input, 1_880, 1_880));
            assertArrayEquals(before, input);
        });

        finish("GreedySolution04", passed, total);
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

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}

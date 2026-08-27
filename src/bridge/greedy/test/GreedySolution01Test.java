package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GreedySolution01을 검증한다. */
public final class GreedySolution01Test {

    private GreedySolution01Test() {
    }

    /*
     * 범위표
     * - reservations 길이: 0, 1, 일반 길이, 최대 100,000
     * - 예약 번호: -1,000,000, 940, 1,000,000
     * - 시작·종료: 0, 맞닿는 경계, 940을 포함한 중간값, 종료 상한 1,000,000,000
     * - 대표 오답: 시작 시각 우선, 가장 짧은 길이 우선, start > lastEnd, 동점 누락, 원본 정렬
     */
    public static void main(String[] args) {
        int total = 8;
        int passed = 0;

        passed += runCase(1, "종료 시각 우선 선택과 원본 보존", () -> {
            int[][] input = {
                    {101, 0, 4},
                    {102, 1, 2},
                    {103, 2, 3},
                    {104, 3, 5}
            };
            int[][] before = deepCopy(input);
            assertArrayEquals(new int[]{102, 103, 104}, GreedySolution01.solve(input));
            assertMatrixEquals(before, input);
        });
        passed += runCase(2, "빈 예약 목록", () -> assertArrayEquals(
                new int[]{},
                GreedySolution01.solve(new int[][]{})
        ));
        passed += runCase(3, "원소 하나와 시각 상한", () -> assertArrayEquals(
                new int[]{940},
                GreedySolution01.solve(new int[][]{{940, 999_999_999, 1_000_000_000}})
        ));
        passed += runCase(4, "가장 짧은 예약 우선의 반례", () -> assertArrayEquals(
                new int[]{1, 2},
                GreedySolution01.solve(new int[][]{
                        {1, 0, 4},
                        {2, 4, 8},
                        {3, 3, 5}
                })
        ));
        passed += runCase(5, "종료와 시작이 같으면 연속 선택", () -> assertArrayEquals(
                new int[]{10, 20, 30},
                GreedySolution01.solve(new int[][]{
                        {30, 940, 1_000},
                        {10, 0, 100},
                        {20, 100, 940}
                })
        ));
        passed += runCase(6, "종료·시작 동점에서 번호 오름차순", () -> assertArrayEquals(
                new int[]{-1_000_000, 1_000_000},
                GreedySolution01.solve(new int[][]{
                        {940, 0, 2},
                        {-1_000_000, 0, 2},
                        {1_000_000, 2, 3}
                })
        ));
        passed += runCase(7, "긴 첫 예약을 건너뛰고 짧은 예약 여러 개 선택", () -> assertArrayEquals(
                new int[]{2, 3, 4},
                GreedySolution01.solve(new int[][]{
                        {1, 0, 1_000},
                        {2, 100, 200},
                        {3, 200, 300},
                        {4, 300, 400}
                })
        ));
        passed += runCase(8, "최대 100000개 연속 예약", () -> {
            int size = 100_000;
            int[][] input = new int[size][3];
            int[] expected = new int[size];
            for (int i = 0; i < size; i++) {
                input[i][0] = i;
                input[i][1] = i * 2;
                input[i][2] = i * 2 + 1;
                expected[i] = i;
            }
            int[][] before = deepCopy(input);
            assertArrayEquals(expected, GreedySolution01.solve(input));
            assertMatrixEquals(before, input);
        });

        finish("GreedySolution01", passed, total);
    }

    private static int[][] deepCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }
        return copy;
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

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }

    private static void assertMatrixEquals(int[][] expected, int[][] actual) {
        if (!Arrays.deepEquals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual));
        }
    }
}

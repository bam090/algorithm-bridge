package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TwoPointerSolution01을 검증한다. */
public final class TwoPointerSolution01Test {

    private static int passed;
    private static int failed;

    private TwoPointerSolution01Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * first.length, second.length | 0 이상 100,000 이하
     * first[i], second[i] | -1,000,000,000 이상 1,000,000,000 이하
     * 배열 값 | 같은 값이 반복될 수 있는 오름차순
     * 대표 오답 | 큰 값 쪽 이동, 같은 값에서 한쪽만 이동, 한 흐름 종료 뒤 계속 읽기, 원본 변경
     */
    public static void main(String[] args) {
        runCase("중복 횟수만큼 공통 번호와 원본 보존", TwoPointerSolution01Test::testSeveralCommonValues);
        runCase("양쪽의 서로 다른 중복 횟수", TwoPointerSolution01Test::testDifferentDuplicateCounts);
        runCase("빈 첫 배열", TwoPointerSolution01Test::testEmptyFirstArray);
        runCase("공통 번호 없음", TwoPointerSolution01Test::testNoCommonValues);
        runCase("작은 쪽을 번갈아 이동", TwoPointerSolution01Test::testAlternatingSmallerValues);
        runCase("값 하한·0·940·상한", TwoPointerSolution01Test::testValueBoundsZeroAndMiddle);
        runCase("두 배열 길이 상한", TwoPointerSolution01Test::testMaximumLengths);
        finish();
    }

    private static void runCase(String name, Runnable test) {
        try {
            test.run();
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error.getMessage());
        }
    }

    private static void testSeveralCommonValues() {
        check(
                "중복 횟수만큼 공통 번호와 원본 보존",
                new int[]{1, 4, 4, 7, 10},
                new int[]{2, 4, 4, 4, 7, 9},
                new int[]{4, 4, 7}
        );
    }

    private static void testDifferentDuplicateCounts() {
        check(
                "양쪽의 서로 다른 중복 횟수",
                new int[]{1, 1, 1, 2, 2},
                new int[]{1, 1, 2, 2, 2},
                new int[]{1, 1, 2, 2}
        );
    }

    private static void testEmptyFirstArray() {
        check("빈 첫 배열", new int[0], new int[]{0, 940}, new int[0]);
    }

    private static void testNoCommonValues() {
        check("공통 번호 없음", new int[]{1, 3, 5}, new int[]{2, 4, 6}, new int[0]);
    }

    private static void testAlternatingSmallerValues() {
        check(
                "작은 쪽을 번갈아 이동",
                new int[]{1, 5, 9},
                new int[]{2, 3, 5, 8, 9},
                new int[]{5, 9}
        );
    }

    private static void testValueBoundsZeroAndMiddle() {
        check(
                "값 하한·0·940·상한",
                new int[]{-1_000_000_000, 0, 940, 1_000_000_000},
                new int[]{-1_000_000_000, -1, 0, 940, 1_000_000_000},
                new int[]{-1_000_000_000, 0, 940, 1_000_000_000}
        );
    }

    private static void testMaximumLengths() {
        int[] first = new int[100_000];
        int[] second = new int[100_000];
        int[] expected = new int[100_000];
        for (int index = 0; index < 100_000; index++) {
            int value = index * 2;
            first[index] = value;
            second[index] = value;
            expected[index] = value;
        }
        check("두 배열 길이 상한", first, second, expected);
    }

    private static void check(String name, int[] first, int[] second, int[] expected) {
        int[] originalFirst = first.clone();
        int[] originalSecond = second.clone();
        int[] actual = TwoPointerSolution01.solve(first, second);
        boolean originalsPreserved = Arrays.equals(first, originalFirst)
                && Arrays.equals(second, originalSecond);
        boolean newArrayReturned = actual != first && actual != second;

        if (Arrays.equals(actual, expected) && originalsPreserved && newArrayReturned) {
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
        System.out.println("[RESULT] TwoPointerSolution01: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("TwoPointerSolution01 실패: " + failed + "건");
        }
    }
}

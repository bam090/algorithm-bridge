package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TwoPointerSolution04를 검증한다. */
public final class TwoPointerSolution04Test {

    private static int passed;
    private static int failed;

    private TwoPointerSolution04Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * values.length | 0 이상 100,000 이하
     * values[i] | 0 이상 1,000,000,000 이하
     * limit | 0 이상 2,000,000,000 이하
     * 대표 오답 | 만족할 때 한 쌍만 증가, 초과할 때 왼쪽 이동, 같은 위치 사용, 원본 정렬, int 개수 넘침
     */
    public static void main(String[] args) {
        runCase("정상 입력과 원본 보존", TwoPointerSolution04Test::testExample);
        runCase("빈 배열", TwoPointerSolution04Test::testEmptyArray);
        runCase("원소 하나와 940", TwoPointerSolution04Test::testSingleValue);
        runCase("한 번에 여러 쌍 계산", TwoPointerSolution04Test::testSeveralPairsAtOnce);
        runCase("0과 중복 위치 쌍", TwoPointerSolution04Test::testZerosAndDuplicatePositions);
        runCase("값과 limit 상한", TwoPointerSolution04Test::testUpperBounds);
        runCase("모든 합이 한계 초과", TwoPointerSolution04Test::testNoValidPairs);
        runCase("길이 상한과 long 개수", TwoPointerSolution04Test::testMaximumLengthAndLongCount);
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

    private static void testExample() {
        check("정상 입력과 원본 보존", new int[]{7, 1, 4, 2}, 6, 3);
    }

    private static void testEmptyArray() {
        check("빈 배열", new int[0], 0, 0);
    }

    private static void testSingleValue() {
        check("원소 하나와 940", new int[]{940}, 1_880, 0);
    }

    private static void testSeveralPairsAtOnce() {
        check("한 번에 여러 쌍 계산", new int[]{1, 2, 7, 8}, 9, 4);
    }

    private static void testZerosAndDuplicatePositions() {
        check("0과 중복 위치 쌍", new int[]{0, 0, 0, 940}, 0, 3);
    }

    private static void testUpperBounds() {
        check(
                "값과 limit 상한",
                new int[]{0, 940, 1_000_000_000, 1_000_000_000},
                2_000_000_000L,
                6
        );
    }

    private static void testNoValidPairs() {
        check("모든 합이 한계 초과", new int[]{5, 6, 7}, 0, 0);
    }

    private static void testMaximumLengthAndLongCount() {
        int[] values = new int[100_000];
        check("길이 상한과 long 개수", values, 0, 4_999_950_000L);
    }

    private static void check(String name, int[] values, long limit, long expected) {
        int[] original = values.clone();
        long actual = TwoPointerSolution04.solve(values, limit);
        boolean originalPreserved = Arrays.equals(values, original);

        if (actual == expected && originalPreserved) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + expected
                    + ", actual=" + actual
                    + ", originalPreserved=" + originalPreserved);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] TwoPointerSolution04: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("TwoPointerSolution04 실패: " + failed + "건");
        }
    }
}

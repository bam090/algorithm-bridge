package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TwoPointerSolution03을 검증한다. */
public final class TwoPointerSolution03Test {

    private static int passed;
    private static int failed;

    private TwoPointerSolution03Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * amounts.length | 0 이상 100,000 이하
     * amounts[i] | 0 이상 1,000,000,000 이하
     * target | 1 이상 100,000,000,000,000 이하
     * 대표 오답 | 왼쪽 한 번만 축소, 마지막 값 누락, 같은 길이의 뒤 구간 선택, 0·1 기반 혼동, int 넘침
     */
    public static void main(String[] args) {
        runCase("여러 번 줄여 가장 짧은 구간", TwoPointerSolution03Test::testExample);
        runCase("빈 배열", TwoPointerSolution03Test::testEmptyArray);
        runCase("조건을 만족하는 구간 없음", TwoPointerSolution03Test::testNoMatchingRange);
        runCase("원소 하나와 940", TwoPointerSolution03Test::testSingleValue);
        runCase("0과 같은 길이의 앞선 구간", TwoPointerSolution03Test::testZeroAndEarlierTie);
        runCase("같은 오른쪽 끝에서 여러 번 축소", TwoPointerSolution03Test::testRepeatedShrinking);
        runCase("값 상한·0·940과 long 합", TwoPointerSolution03Test::testValueBoundsAndLongSum);
        runCase("길이와 target 상한", TwoPointerSolution03Test::testMaximumLengthAndTarget);
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
        check("여러 번 줄여 가장 짧은 구간", new int[]{2, 1, 5, 2, 3, 2}, 7, new int[]{3, 4});
    }

    private static void testEmptyArray() {
        check("빈 배열", new int[0], 1, new int[0]);
    }

    private static void testNoMatchingRange() {
        check("조건을 만족하는 구간 없음", new int[]{1, 2}, 10, new int[0]);
    }

    private static void testSingleValue() {
        check("원소 하나와 940", new int[]{940}, 940, new int[]{1, 1});
    }

    private static void testZeroAndEarlierTie() {
        check("0과 같은 길이의 앞선 구간", new int[]{0, 5, 0, 5}, 5, new int[]{2, 2});
    }

    private static void testRepeatedShrinking() {
        check("같은 오른쪽 끝에서 여러 번 축소", new int[]{1, 2, 3, 4}, 6, new int[]{3, 4});
    }

    private static void testValueBoundsAndLongSum() {
        check(
                "값 상한·0·940과 long 합",
                new int[]{1_000_000_000, 0, 940, 1_000_000_000},
                2_000_000_940L,
                new int[]{1, 4}
        );
    }

    private static void testMaximumLengthAndTarget() {
        int[] amounts = new int[100_000];
        Arrays.fill(amounts, 1_000_000_000);
        check(
                "길이와 target 상한",
                amounts,
                100_000_000_000_000L,
                new int[]{1, 100_000}
        );
    }

    private static void check(String name, int[] amounts, long target, int[] expected) {
        int[] original = amounts.clone();
        int[] actual = TwoPointerSolution03.solve(amounts, target);
        boolean originalPreserved = Arrays.equals(amounts, original);
        boolean newArrayReturned = actual != amounts;

        if (Arrays.equals(actual, expected) && originalPreserved && newArrayReturned) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalPreserved=" + originalPreserved
                    + ", newArrayReturned=" + newArrayReturned);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] TwoPointerSolution03: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("TwoPointerSolution03 실패: " + failed + "건");
        }
    }
}

package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TwoPointerSolution02를 검증한다. */
public final class TwoPointerSolution02Test {

    private static int passed;
    private static int failed;

    private TwoPointerSolution02Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * values.length | 2 이상 100,000 이하
     * values[i] | -1,000,000,000 이상 1,000,000,000 이하
     * target | -2,000,000,000 이상 2,000,000,000 이하
     * 대표 오답 | 합에 반대되는 끝 이동, 같은 위치 재사용, 거리 동점 누락, int 넘침, 원본 변경
     */
    public static void main(String[] args) {
        runCase("음수와 양수가 섞인 정상 입력", TwoPointerSolution02Test::testMixedValues);
        runCase("target과 정확히 같은 합", TwoPointerSolution02Test::testExactTarget);
        runCase("원소 두 개와 값·target 경계", TwoPointerSolution02Test::testTwoValuesAndBounds);
        runCase("target 하한과 같은 최솟값 합", TwoPointerSolution02Test::testMinimumTarget);
        runCase("target과의 거리 int 범위 초과", TwoPointerSolution02Test::testDistanceBeyondIntRange);
        runCase("같은 거리이면 더 작은 합", TwoPointerSolution02Test::testTieChoosesSmallerSum);
        runCase("음수 target에서 양끝 이동", TwoPointerSolution02Test::testNegativeTarget);
        runCase("중복 값과 940", TwoPointerSolution02Test::testDuplicateValuesAndMiddle);
        runCase("길이 상한과 마지막 두 값", TwoPointerSolution02Test::testMaximumLength);
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

    private static void testMixedValues() {
        check("음수와 양수가 섞인 정상 입력", new int[]{-6, -1, 4, 9}, 6, 8);
    }

    private static void testExactTarget() {
        check("target과 정확히 같은 합", new int[]{-5, 0, 5}, 0, 0);
    }

    private static void testTwoValuesAndBounds() {
        check(
                "원소 두 개와 값·target 경계",
                new int[]{-1_000_000_000, 1_000_000_000},
                2_000_000_000L,
                0
        );
    }

    private static void testMinimumTarget() {
        check(
                "target 하한과 같은 최솟값 합",
                new int[]{-1_000_000_000, -1_000_000_000, 940},
                -2_000_000_000L,
                -2_000_000_000L
        );
    }

    private static void testDistanceBeyondIntRange() {
        check(
                "target과의 거리 int 범위 초과",
                new int[]{-1_000_000_000, 0, 852_516_352},
                2_000_000_000L,
                852_516_352L
        );
    }

    private static void testTieChoosesSmallerSum() {
        check("같은 거리이면 더 작은 합", new int[]{1, 4, 8, 10}, 10, 9);
    }

    private static void testNegativeTarget() {
        check("음수 target에서 양끝 이동", new int[]{-10, -4, 2, 9}, -7, -8);
    }

    private static void testDuplicateValuesAndMiddle() {
        check("중복 값과 940", new int[]{5, 5, 5, 940}, 11, 10);
    }

    private static void testMaximumLength() {
        int[] values = new int[100_000];
        for (int index = 0; index < values.length; index++) {
            values[index] = index - 50_000;
        }
        check("길이 상한과 마지막 두 값", values, 99_997, 99_997);
    }

    private static void check(String name, int[] values, long target, long expected) {
        int[] original = values.clone();
        long actual = TwoPointerSolution02.solve(values, target);
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
        System.out.println("[RESULT] TwoPointerSolution02: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("TwoPointerSolution02 실패: " + failed + "건");
        }
    }
}

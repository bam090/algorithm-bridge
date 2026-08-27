package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution03;

import java.util.Arrays;

public final class SortingSolution03Test {

    private static int passed;
    private static int failed;

    private SortingSolution03Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * requestIds.length | 0 이상 10_000 이하
     * priorityScores.length, effortScores.length | requestIds.length와 같음
     * requestIds[i].length() | 1 이상 20 이하
     * priorityScores[i], effortScores[i] | Integer.MIN_VALUE 이상 Integer.MAX_VALUE 이하
     */
    public static void main(String[] args) {
        runCase("주 기준·보조 기준·입력 순서", SortingSolution03Test::testPrimarySecondaryAndStableOrder);
        runCase("빈 입력", SortingSolution03Test::testEmptyInput);
        runCase("원소 하나와 ID 길이 상한", SortingSolution03Test::testOneRequestAndIdentifierLengthBounds);
        runCase("정수 하한·0·940·상한과 넘침 방지", SortingSolution03Test::testIntegerBoundsZeroAndOrdinaryMiddle);
        runCase("같은 우선 점수에서 작업량 오름차순", SortingSolution03Test::testSecondaryDirection);
        runCase("최대 길이에서 완전 동점의 안정 정렬", SortingSolution03Test::testMaximumLengthAndStableOrder);
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

    private static void testPrimarySecondaryAndStableOrder() {
        check(
                "주 기준·보조 기준·입력 순서",
                new String[]{"A", "B", "C", "D"},
                new int[]{2, 3, 3, 3},
                new int[]{10, 20, 10, 10},
                new String[]{"C", "D", "B", "A"}
        );
    }

    private static void testEmptyInput() {
        check("빈 입력", new String[0], new int[0], new int[0], new String[0]);
    }

    private static void testOneRequestAndIdentifierLengthBounds() {
        check(
                "원소 하나와 ID 길이 상한",
                new String[]{"abcdefghijklmnopqrst"},
                new int[]{940},
                new int[]{0},
                new String[]{"abcdefghijklmnopqrst"}
        );
    }

    private static void testIntegerBoundsZeroAndOrdinaryMiddle() {
        check(
                "정수 하한·0·940·상한과 넘침 방지",
                new String[]{"minimum", "zero", "middle", "maximum"},
                new int[]{Integer.MIN_VALUE, 0, 940, Integer.MAX_VALUE},
                new int[]{Integer.MAX_VALUE, 0, 940, Integer.MIN_VALUE},
                new String[]{"maximum", "middle", "zero", "minimum"}
        );
    }

    private static void testSecondaryDirection() {
        check(
                "같은 우선 점수에서 작업량 오름차순",
                new String[]{"large", "small", "middle"},
                new int[]{5, 5, 5},
                new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 940},
                new String[]{"small", "middle", "large"}
        );
    }

    private static void testMaximumLengthAndStableOrder() {
        int length = 10_000;
        String[] requestIds = new String[length];
        int[] priorityScores = new int[length];
        int[] effortScores = new int[length];
        for (int index = 0; index < length; index++) {
            requestIds[index] = String.format("request%05d", index);
            priorityScores[index] = 940;
            effortScores[index] = 940;
        }
        check("최대 길이에서 완전 동점의 안정 정렬", requestIds, priorityScores, effortScores,
                requestIds.clone());
    }

    private static void check(
            String name,
            String[] requestIds,
            int[] priorityScores,
            int[] effortScores,
            String[] expected
    ) {
        String[] originalIds = requestIds.clone();
        int[] originalPriorities = priorityScores.clone();
        int[] originalEfforts = effortScores.clone();
        String[] actual = SortingSolution03.solve(requestIds, priorityScores, effortScores);
        boolean originalsPreserved = Arrays.equals(requestIds, originalIds)
                && Arrays.equals(priorityScores, originalPriorities)
                && Arrays.equals(effortScores, originalEfforts);
        boolean newArrayReturned = actual != requestIds;
        boolean success = Arrays.equals(actual, expected) && originalsPreserved && newArrayReturned;

        if (success) {
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
        System.out.println("[RESULT] SortingSolution03: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SortingSolution03 실패: " + failed + "건");
        }
    }
}

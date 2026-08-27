package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution06;

import java.util.Arrays;

public final class SortingSolution06Test {

    private static int passed;
    private static int failed;

    private SortingSolution06Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * groupRecords.length | 0 이상 100 이하
     * 그룹 이름 길이 | 1 이상 20 이하
     * 항목 이름 길이 | 1 이상 20 이하
     * 한 그룹의 항목 수 | 1 이상 100 이하
     * 모든 그룹의 항목 수 합계 | 0 이상 10_000 이하
     * 숫자 매개변수는 없으므로 값 0과 940의 숫자 범위 검사는 적용되지 않는다.
     */
    public static void main(String[] args) {
        runCase("비중첩 그룹과 새 항목 0개·여러 개", SortingSolution06Test::testNonNestedGroupsAndNewItemCountRange);
        runCase("빈 입력", SortingSolution06Test::testEmptyInput);
        runCase("그룹 하나와 이름 길이 하한", SortingSolution06Test::testOneGroupAndNameLengthLowerBound);
        runCase("같은 크기에서 그룹 이름 사전순", SortingSolution06Test::testSameSizeUsesLabelOrder);
        runCase("이미 본 항목과 새 항목이 섞임", SortingSolution06Test::testNoNewItemsAndMixedSeenItems);
        runCase("유효한 구분 형식과 이름 길이 상한", SortingSolution06Test::testValidParsingAndNameLengthUpperBounds);
        runCase("최대 그룹 수·그룹별 항목 수·전체 항목 수", SortingSolution06Test::testMaximumGroupsAndItems);
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

    private static void testNonNestedGroupsAndNewItemCountRange() {
        check(
                "비중첩 그룹과 새 항목 0개·여러 개",
                new String[]{
                        "green:apple,pear",
                        "red:apple",
                        "blue:pear,plum",
                        "gold:apple,plum,kiwi"
                },
                new String[]{"red=1", "blue=2", "green=0", "gold=1"}
        );
    }

    private static void testEmptyInput() {
        check("빈 입력", new String[0], new String[0]);
    }

    private static void testOneGroupAndNameLengthLowerBound() {
        check("그룹 하나와 이름 길이 하한", new String[]{"a:z"}, new String[]{"a=1"});
    }

    private static void testSameSizeUsesLabelOrder() {
        check(
                "같은 크기에서 그룹 이름 사전순",
                new String[]{"z:b,c", "a:a,b"},
                new String[]{"a=2", "z=1"}
        );
    }

    private static void testNoNewItemsAndMixedSeenItems() {
        check(
                "이미 본 항목과 새 항목이 섞임",
                new String[]{"third:a,b,c", "first:a", "second:a,b", "repeat:b,a"},
                new String[]{"first=1", "repeat=1", "second=0", "third=1"}
        );
    }

    private static void testValidParsingAndNameLengthUpperBounds() {
        String label = "abcdefghijklmnopqrst";
        String item = "uvwxyzabcdefghijklmn";
        check(
                "유효한 구분 형식과 이름 길이 상한",
                new String[]{"g940:item0,item940", label + ":" + item},
                new String[]{label + "=1", "g940=2"}
        );
    }

    private static void testMaximumGroupsAndItems() {
        int groupCount = 100;
        int itemCount = 100;
        String[] records = new String[groupCount];
        String[] expected = new String[groupCount];

        StringBuilder items = new StringBuilder();
        for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
            if (itemNumber > 0) {
                items.append(',');
            }
            items.append(String.format("v%03d", itemNumber));
        }

        for (int groupNumber = 0; groupNumber < groupCount; groupNumber++) {
            String groupName = String.format("g%03d", groupNumber);
            records[groupCount - 1 - groupNumber] = groupName + ":" + items;
            expected[groupNumber] = groupName + "=" + (groupNumber == 0 ? itemCount : 0);
        }

        check("최대 그룹 수·그룹별 항목 수·전체 항목 수", records, expected);
    }

    private static void check(String name, String[] records, String[] expected) {
        String[] original = records.clone();
        String[] actual = SortingSolution06.solve(records);
        boolean originalPreserved = Arrays.equals(records, original);
        boolean newArrayReturned = actual != records;
        boolean success = Arrays.equals(actual, expected) && originalPreserved && newArrayReturned;

        if (success) {
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
        System.out.println("[RESULT] SortingSolution06: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("SortingSolution06 실패: " + failed + "건");
        }
    }
}

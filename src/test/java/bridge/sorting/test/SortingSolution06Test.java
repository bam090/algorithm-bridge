package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution06;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class SortingSolution06Test {

    /*
     * 매개변수 | 허용 범위
     * groupRecords.length | 0 이상 100 이하
     * 그룹 이름 길이 | 1 이상 20 이하
     * 항목 이름 길이 | 1 이상 20 이하
     * 한 그룹의 항목 수 | 1 이상 100 이하
     * 모든 그룹의 항목 수 합계 | 0 이상 10_000 이하
     * 숫자 매개변수는 없으므로 값 0과 940의 숫자 범위 검사는 적용되지 않는다.
     */
    @DisplayName("그룹을 크기순으로 처리해 새 항목 수를 계산한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testNewItemCounts(String name, String[] records, String[] expected) {
        check(name, records, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("비중첩 그룹과 새 항목 0개·여러 개", new String[]{
                        "green:apple,pear", "red:apple", "blue:pear,plum", "gold:apple,plum,kiwi"
                }, new String[]{"red=1", "blue=2", "green=0", "gold=1"}),
                arguments("빈 입력", new String[0], new String[0]),
                arguments("그룹 하나와 이름 길이 하한", new String[]{"a:z"}, new String[]{"a=1"}),
                arguments("같은 크기에서 그룹 이름 사전순",
                        new String[]{"z:b,c", "a:a,b"}, new String[]{"a=2", "z=1"}),
                arguments("이미 본 항목과 새 항목이 섞임",
                        new String[]{"third:a,b,c", "first:a", "second:a,b", "repeat:b,a"},
                        new String[]{"first=1", "repeat=1", "second=0", "third=1"}),
                arguments("유효한 구분 형식과 이름 길이 상한",
                        new String[]{"g940:item0,item940", "abcdefghijklmnopqrst:uvwxyzabcdefghijklmn"},
                        new String[]{"abcdefghijklmnopqrst=1", "g940=2"})
        );
    }

    @Test
    @DisplayName("최대 그룹 수와 항목 수를 처리한다")
    void testMaximumGroupsAndItems() {
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
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, records, "입력 배열 원본을 보존해야 한다"),
                () -> assertNotSame(records, actual, "입력 배열과 다른 결과 배열을 반환해야 한다")
        );
    }
}

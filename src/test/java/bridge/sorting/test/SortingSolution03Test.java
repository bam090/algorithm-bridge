package bridge.sorting.test;

import bridge.sorting.solution.SortingSolution03;
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

public final class SortingSolution03Test {

    /*
     * 매개변수 | 허용 범위
     * requestIds.length | 0 이상 10_000 이하
     * priorityScores.length, effortScores.length | requestIds.length와 같음
     * requestIds[i].length() | 1 이상 20 이하
     * priorityScores[i], effortScores[i] | Integer.MIN_VALUE 이상 Integer.MAX_VALUE 이하
     */
    @DisplayName("주 기준과 보조 기준으로 요청을 정렬한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testRequestOrder(
            String name,
            String[] requestIds,
            int[] priorityScores,
            int[] effortScores,
            String[] expected
    ) {
        check(name, requestIds, priorityScores, effortScores, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("주 기준·보조 기준·입력 순서",
                        new String[]{"A", "B", "C", "D"},
                        new int[]{2, 3, 3, 3},
                        new int[]{10, 20, 10, 10},
                        new String[]{"C", "D", "B", "A"}),
                arguments("빈 입력", new String[0], new int[0], new int[0], new String[0]),
                arguments("원소 하나와 ID 길이 상한",
                        new String[]{"abcdefghijklmnopqrst"},
                        new int[]{940}, new int[]{0}, new String[]{"abcdefghijklmnopqrst"}),
                arguments("정수 하한·0·940·상한과 넘침 방지",
                        new String[]{"minimum", "zero", "middle", "maximum"},
                        new int[]{Integer.MIN_VALUE, 0, 940, Integer.MAX_VALUE},
                        new int[]{Integer.MAX_VALUE, 0, 940, Integer.MIN_VALUE},
                        new String[]{"maximum", "middle", "zero", "minimum"}),
                arguments("같은 우선 점수에서 작업량 오름차순",
                        new String[]{"large", "small", "middle"},
                        new int[]{5, 5, 5},
                        new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 940},
                        new String[]{"small", "middle", "large"})
        );
    }

    @Test
    @DisplayName("최대 길이의 완전 동점에서 입력 순서를 유지한다")
    void testMaximumLengthAndStableOrder() {
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
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalIds, requestIds, "ID 배열 원본을 보존해야 한다"),
                () -> assertArrayEquals(originalPriorities, priorityScores, "우선 점수 원본을 보존해야 한다"),
                () -> assertArrayEquals(originalEfforts, effortScores, "작업량 원본을 보존해야 한다"),
                () -> assertNotSame(requestIds, actual, "입력 배열과 다른 결과 배열을 반환해야 한다")
        );
    }
}

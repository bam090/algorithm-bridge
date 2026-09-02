package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution04;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class TwoPointerSolution04Test {

    /*
     * 매개변수 | 허용 범위
     * values.length | 0 이상 100,000 이하
     * values[i] | 0 이상 1,000,000,000 이하
     * limit | 0 이상 2,000,000,000 이하
     * 대표 오답 | 만족할 때 한 쌍만 증가, 초과할 때 왼쪽 이동, 같은 위치 사용, 원본 정렬, int 개수 넘침
     */
    @DisplayName("한계 이하인 서로 다른 위치의 쌍을 센다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testPairCount(String name, int[] values, long limit, long expected) {
        check(name, values, limit, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 입력과 원본 보존", new int[]{7, 1, 4, 2}, 6L, 3L),
                arguments("빈 배열", new int[0], 0L, 0L),
                arguments("원소 하나와 940", new int[]{940}, 1_880L, 0L),
                arguments("한 번에 여러 쌍 계산", new int[]{1, 2, 7, 8}, 9L, 4L),
                arguments("0과 중복 위치 쌍", new int[]{0, 0, 0, 940}, 0L, 3L),
                arguments("값과 limit 상한",
                        new int[]{0, 940, 1_000_000_000, 1_000_000_000}, 2_000_000_000L, 6L),
                arguments("모든 합이 한계 초과", new int[]{5, 6, 7}, 0L, 0L)
        );
    }

    @Test
    @DisplayName("길이 상한에서 long 범위의 쌍 개수를 센다")
    void testMaximumLengthAndLongCount() {
        int[] values = new int[100_000];
        check("길이 상한과 long 개수", values, 0, 4_999_950_000L);
    }

    private static void check(String name, int[] values, long limit, long expected) {
        int[] original = values.clone();
        long actual = TwoPointerSolution04.solve(values, limit);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(original, values, "입력 배열 원본을 보존해야 한다")
        );
    }
}

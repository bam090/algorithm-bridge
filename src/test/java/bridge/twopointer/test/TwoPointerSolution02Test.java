package bridge.twopointer.test;

import bridge.twopointer.solution.TwoPointerSolution02;
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

public final class TwoPointerSolution02Test {

    /*
     * 매개변수 | 허용 범위
     * values.length | 2 이상 100,000 이하
     * values[i] | -1,000,000,000 이상 1,000,000,000 이하
     * target | -2,000,000,000 이상 2,000,000,000 이하
     * 대표 오답 | 합에 반대되는 끝 이동, 같은 위치 재사용, 거리 동점 누락, int 넘침, 원본 변경
     */
    @DisplayName("정렬 배열에서 목표에 가장 가까운 합을 찾는다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testClosestSum(String name, int[] values, long target, long expected) {
        check(name, values, target, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("음수와 양수가 섞인 정상 입력", new int[]{-6, -1, 4, 9}, 6L, 8L),
                arguments("target과 정확히 같은 합", new int[]{-5, 0, 5}, 0L, 0L),
                arguments("원소 두 개와 값·target 경계",
                        new int[]{-1_000_000_000, 1_000_000_000}, 2_000_000_000L, 0L),
                arguments("target 하한과 같은 최솟값 합",
                        new int[]{-1_000_000_000, -1_000_000_000, 940},
                        -2_000_000_000L, -2_000_000_000L),
                arguments("target과의 거리 int 범위 초과",
                        new int[]{-1_000_000_000, 0, 852_516_352}, 2_000_000_000L, 852_516_352L),
                arguments("같은 거리이면 더 작은 합", new int[]{1, 4, 8, 10}, 10L, 9L),
                arguments("음수 target에서 양끝 이동", new int[]{-10, -4, 2, 9}, -7L, -8L),
                arguments("중복 값과 940", new int[]{5, 5, 5, 940}, 11L, 10L)
        );
    }

    @Test
    @DisplayName("길이 상한에서 마지막 두 값의 합을 찾는다")
    void testMaximumLength() {
        int[] values = new int[100_000];
        for (int index = 0; index < values.length; index++) {
            values[index] = index - 50_000;
        }
        check("길이 상한과 마지막 두 값", values, 99_997, 99_997);
    }

    private static void check(String name, int[] values, long target, long expected) {
        int[] original = values.clone();
        long actual = TwoPointerSolution02.solve(values, target);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(original, values, "입력 배열 원본을 보존해야 한다")
        );
    }
}

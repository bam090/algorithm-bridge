package bridge.hash.test;

import bridge.hash.solution.HashSolution01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

public final class HashSolution01Test {

    /*
     * 매개변수 | 허용 범위
     * codes.length | 0 이상 100_000 이하
     * codes[i] | -1_000 이상 1_000 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("codeCases")
    @DisplayName("처음 완성된 반대 부호 짝의 위치를 구한다")
    void findsFirstPair(String name, int[] codes, int expected) {
        check(name, codes, expected);
    }

    @Test
    @DisplayName("배열 길이 상한")
    void testMaximumLength() {
        int[] codes = new int[100_000];
        Arrays.fill(codes, 940);
        codes[codes.length - 1] = -940;
        check("배열 길이 상한", codes, 100_000);
    }

    private static Stream<Arguments> codeCases() {
        return Stream.of(
                Arguments.of("처음 완성된 짝", new int[]{7, 3, -7, 4}, 3),
                Arguments.of("빈 입력", new int[]{}, -1),
                Arguments.of("원소 하나는 짝이 없음", new int[]{940}, -1),
                Arguments.of("현재 값을 먼저 저장하는 오답 방지", new int[]{0}, -1),
                Arguments.of("서로 다른 두 0은 짝", new int[]{0, 0}, 2),
                Arguments.of("값 하한·중간값·상한", new int[]{-1_000, 940, 1_000}, 3),
                Arguments.of("뒤의 짝보다 처음 짝을 반환", new int[]{5, 2, -2, -5}, 3)
        );
    }

    private static void check(String name, int[] codes, int expected) {
        int[] original = codes.clone();
        int actual = HashSolution01.solve(codes);

        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(original, codes, "원본 codes가 변경되었습니다."));
    }

}

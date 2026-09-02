package bridge.hash.test;

import bridge.hash.solution.HashSolution03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

public final class HashSolution03Test {

    /*
     * 매개변수 | 허용 범위
     * stream.length | 0 이상 100_000 이하
     * pattern.length | 1 이상 1_000 이하
     * stream[i], pattern[i] | -1_000 이상 1_000 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("windowCases")
    @DisplayName("패턴과 빈도가 같은 고정 구간의 위치를 구한다")
    void findsMatchingWindows(String name, int[] stream, int[] pattern, int[] expected) {
        check(name, stream, pattern, expected);
    }

    @Test
    @DisplayName("길이 상한과 서로 다른 값 1,000개")
    void testMaximumLengths() {
        int[] stream = new int[100_000];
        int[] pattern = new int[1_000];
        for (int index = 0; index < pattern.length; index++) {
            pattern[index] = index - 1_000;
        }
        for (int index = 0; index < stream.length; index++) {
            stream[index] = pattern[index % pattern.length];
        }

        int[] expected = new int[99_001];
        for (int index = 0; index < expected.length; index++) {
            expected[index] = index + 1;
        }
        check("길이 상한과 서로 다른 값 1,000개", stream, pattern, expected);
    }

    private static Stream<Arguments> windowCases() {
        return Stream.of(
                Arguments.of("순서가 달라도 같은 두 창", new int[]{2, 1, 2, 3, 2, 2, 1},
                        new int[]{1, 2, 2}, new int[]{1, 5}),
                Arguments.of("빈 전체 배열", new int[0], new int[]{0}, new int[0]),
                Arguments.of("pattern 길이 하한", new int[]{-1_000, 940, 0, 940, 1_000},
                        new int[]{940}, new int[]{2, 4}),
                Arguments.of("중복 횟수와 개수 0인 key 제거", new int[]{1, 1, 2, 1, 1},
                        new int[]{1, 1}, new int[]{1, 4}),
                Arguments.of("pattern이 더 긴 입력", new int[]{1}, new int[]{1, 2}, new int[0]),
                Arguments.of("값 하한·0·중간값·상한", new int[]{-1_000, 0, 940, 1_000, 0, -1_000},
                        new int[]{-1_000, 0}, new int[]{1, 5})
        );
    }

    private static void check(String name, int[] stream, int[] pattern, int[] expected) {
        int[] originalStream = stream.clone();
        int[] originalPattern = pattern.clone();
        int[] actual = HashSolution03.solve(stream, pattern);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalStream, stream, "원본 stream이 변경되었습니다."),
                () -> assertArrayEquals(originalPattern, pattern, "원본 pattern이 변경되었습니다."),
                () -> assertNotSame(stream, actual, "결과가 원본 stream과 같은 배열입니다."),
                () -> assertNotSame(pattern, actual, "결과가 원본 pattern과 같은 배열입니다."));
    }

}

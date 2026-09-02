package bridge.hash.test;

import bridge.hash.solution.HashSolution02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

public final class HashSolution02Test {

    /*
     * 매개변수 | 허용 범위
     * expectedCodes.length | 0 이상 100_000 이하
     * actualCodes.length | 0 이상 100_000 이하
     * expectedCodes[i].length(), actualCodes[i].length() | 1 이상 20 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("differenceCases")
    @DisplayName("두 목록의 항목별 개수 차이를 더한다")
    void countsDifferences(String name, String[] expectedCodes, String[] actualCodes, int expected) {
        check(name, expectedCodes, actualCodes, expected);
    }

    @Test
    @DisplayName("두 배열 길이 상한")
    void testMaximumLengths() {
        String[] expected = new String[100_000];
        String[] actual = new String[100_000];
        Arrays.fill(expected, "940");
        Arrays.fill(actual, 0, 50_000, "940");
        Arrays.fill(actual, 50_000, actual.length, "0");
        check("두 배열 길이 상한", expected, actual, 100_000);
    }

    private static Stream<Arguments> differenceCases() {
        String longCode = "abcdefghijklmnopqrst";
        return Stream.of(
                Arguments.of("양쪽에 남은 차이를 모두 계산", new String[]{"A", "A", "B"},
                        new String[]{"A", "C", "C"}, 4),
                Arguments.of("두 빈 목록", new String[0], new String[0], 0),
                Arguments.of("원소 하나가 정확히 일치", new String[]{"940"}, new String[]{"940"}, 0),
                Arguments.of("Set으로 중복 횟수를 잃는 오답 방지", new String[]{"a", "a", "b"},
                        new String[]{"a", "b", "b"}, 2),
                Arguments.of("한쪽만 빈 목록", new String[]{"940"}, new String[0], 1),
                Arguments.of("코드 길이 하한·상한과 숫자 모양 코드",
                        new String[]{"x", longCode, "0", "940"},
                        new String[]{"x", longCode, "1000", "940"}, 2)
        );
    }

    private static void check(String name, String[] expectedCodes, String[] actualCodes, int expected) {
        String[] originalExpectedCodes = expectedCodes.clone();
        String[] originalActualCodes = actualCodes.clone();
        int actual = HashSolution02.solve(expectedCodes, actualCodes);

        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(originalExpectedCodes, expectedCodes,
                        "원본 expectedCodes가 변경되었습니다."),
                () -> assertArrayEquals(originalActualCodes, actualCodes,
                        "원본 actualCodes가 변경되었습니다."));
    }

}

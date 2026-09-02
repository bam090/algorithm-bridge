package bridge.hash.test;

import bridge.hash.solution.HashSolution04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

public final class HashSolution04Test {

    /*
     * 매개변수 | 허용 범위
     * deviceIds.length | 0 이상 100_000 이하
     * factors.length, readings.length | deviceIds.length와 같음
     * deviceIds[i].length() | 1 이상 20 이하
     * factors[i] | 0 이상 1_000_000 이하
     * readings[i] | -1_000_000 이상 1_000_000 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("recordCases")
    @DisplayName("장치 기록에 마지막 계수를 적용한다")
    void testRecords(
            String name,
            String[] deviceIds,
            int[] factors,
            int[] readings,
            long[] expected
    ) {
        check(name, deviceIds, factors, readings, expected);
    }

    @Test
    @DisplayName("기록 길이 상한")
    void testMaximumRecordLength() {
        int length = 100_000;
        String[] ids = new String[length];
        int[] factors = new int[length];
        int[] readings = new int[length];
        long[] expected = new long[length];

        for (int index = 0; index < length; index++) {
            boolean even = index % 2 == 0;
            ids[index] = even ? "A" : "B";
            factors[index] = even ? 940 : 1_000;
            readings[index] = 940;
            expected[index] = even ? 883_600L : 940_000L;
        }
        check("기록 길이 상한", ids, factors, readings, expected);
    }

    private static Stream<Arguments> recordCases() {
        String longId = "abcdefghijklmnopqrst";
        return Stream.of(
                Arguments.of(
                        "장치별 마지막 계수 적용",
                        new String[]{"A", "B", "A"},
                        new int[]{2, 3, 4},
                        new int[]{10, 5, -2},
                        new long[]{40, 15, -8}),
                Arguments.of("빈 기록", new String[0], new int[0], new int[0], new long[0]),
                Arguments.of(
                        "원소 하나와 계수 하한",
                        new String[]{"A"},
                        new int[]{0},
                        new int[]{940},
                        new long[]{0}),
                Arguments.of(
                        "읽는 즉시 계산하는 오답 방지",
                        new String[]{"A", "A"},
                        new int[]{2, 5},
                        new int[]{10, 20},
                        new long[]{50, 100}),
                Arguments.of(
                        "ID 길이와 숫자 하한·0·중간값·상한",
                        new String[]{"x", longId, "x", longId},
                        new int[]{0, 940, 1_000_000, 940},
                        new int[]{-1_000_000, 0, 940, 1_000_000},
                        new long[]{-1_000_000_000_000L, 0, 940_000_000L, 940_000_000L}),
                Arguments.of(
                        "int 곱셈 범위를 넘는 양수 결과",
                        new String[]{"A"},
                        new int[]{1_000_000},
                        new int[]{1_000_000},
                        new long[]{1_000_000_000_000L})
        );
    }

    private static void check(
            String name,
            String[] deviceIds,
            int[] factors,
            int[] readings,
            long[] expected
    ) {
        String[] originalDeviceIds = deviceIds.clone();
        int[] originalFactors = factors.clone();
        int[] originalReadings = readings.clone();
        long[] actual = HashSolution04.solve(deviceIds, factors, readings);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalDeviceIds, deviceIds,
                        "원본 deviceIds가 변경되었습니다."),
                () -> assertArrayEquals(originalFactors, factors, "원본 factors가 변경되었습니다."),
                () -> assertArrayEquals(originalReadings, readings, "원본 readings가 변경되었습니다."));
    }

}

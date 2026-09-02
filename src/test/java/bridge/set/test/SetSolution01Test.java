package bridge.set.test;

import bridge.set.solution.SetSolution01;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@DisplayName("집합 01 - 고유 배지로 보드 채우기")
final class SetSolution01Test {

    /*
     * 검증 범위
     * 항목       | 제약                              | 실제 확인값
     * badgeCodes | 길이 0~100,000, 값 -1,000,000~1,000,000 | 0·1·100,000개, 하한·0·940·상한
     * slotLimit  | 0~100,000                         | 0·2·5·10·940·100,000
     * 대표 오답  | 배열 길이를 고유 개수로 사용, 슬롯 상한 무시, 원본 변경
     */
    @Test
    @DisplayName("중복 배지와 남는 칸 및 원본 보존")
    void countsUniqueBadgesWithoutChangingInput() {
        int[] badgeCodes = {10, 10, 20, 30};
        int[] before = badgeCodes.clone();

        int[] actual = SetSolution01.solve(badgeCodes, 5);

        assertAll(
                () -> assertArrayEquals(new int[]{3, 2}, actual),
                () -> assertArrayEquals(before, badgeCodes),
                () -> assertNotSame(badgeCodes, actual)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("basicCases")
    @DisplayName("배지와 슬롯 경계")
    void handlesBadgeAndSlotBoundaries(String name, int[] badgeCodes, int slotLimit, int[] expected) {
        assertArrayEquals(expected, SetSolution01.solve(badgeCodes, slotLimit));
    }

    static Stream<Arguments> basicCases() {
        return Stream.of(
                Arguments.of(
                        "배지 코드 하한·0·940·상한",
                        new int[]{-1_000_000, 0, 940, 1_000_000, -1_000_000},
                        10,
                        new int[]{4, 6}
                ),
                Arguments.of("보드 칸 수가 0", new int[]{1, 2, 3}, 0, new int[]{0, 0}),
                Arguments.of("배지가 없고 칸 수가 940", new int[]{}, 940, new int[]{0, 940}),
                Arguments.of("고유 배지가 칸보다 많음", new int[]{1, 2, 3, 4, 5}, 2, new int[]{2, 0})
        );
    }

    @Test
    @DisplayName("길이 1과 슬롯 상한")
    void handlesSingleBadgeAndMaximumSlotLimit() {
        int[] badgeCodes = {940};
        int[] before = badgeCodes.clone();

        int[] actual = SetSolution01.solve(badgeCodes, 100_000);

        assertAll(
                () -> assertArrayEquals(new int[]{1, 99_999}, actual),
                () -> assertArrayEquals(before, badgeCodes),
                () -> assertNotSame(badgeCodes, actual)
        );
    }

    @Test
    @DisplayName("최대 길이에서 1,000가지 중 940칸 채우기")
    void handlesMaximumLength() {
        int[] badgeCodes = new int[100_000];
        for (int i = 0; i < badgeCodes.length; i++) {
            badgeCodes[i] = i % 1_000;
        }
        int[] before = badgeCodes.clone();

        int[] actual = SetSolution01.solve(badgeCodes, 940);

        assertAll(
                () -> assertArrayEquals(new int[]{940, 0}, actual),
                () -> assertArrayEquals(before, badgeCodes)
        );
    }
}

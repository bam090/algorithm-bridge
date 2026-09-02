package bridge.set.test;

import bridge.set.solution.SetSolution02;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("집합 02 - 첫 점검 오류 찾기")
final class SetSolution02Test {

    /*
     * 검증 범위
     * 항목       | 제약                                  | 실제 확인값
     * checkCodes | 길이 0~100,000, 값 -1,000,000~1,000,000 | 0·1·942·100,000개, 하한·0·940·상한
     * inspectors | 1~1,000                               | 1·2·3·940·1,000
     * allowedGap | 0~2,000,000                           | 0·1·2·5·10·940·2,000,000
     * 대표 오답  | 중복 누락, 간격 경계 오류, 마지막 오류 반환, 담당 번호 계산 오류, 원본 변경
     */
    @Test
    @DisplayName("모든 기록이 올바르고 원본 보존")
    void acceptsValidRecordsWithoutChangingInput() {
        int[] checkCodes = {10, 12, 14, 16};
        int[] before = checkCodes.clone();

        int[] actual = SetSolution02.solve(checkCodes, 3, 2);

        assertAll(
                () -> assertArrayEquals(new int[]{}, actual),
                () -> assertArrayEquals(before, checkCodes)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("recordCases")
    @DisplayName("첫 오류와 입력 경계")
    void findsFirstError(
            String name,
            int[] checkCodes,
            int inspectors,
            int allowedGap,
            int[] expected
    ) {
        assertArrayEquals(expected, SetSolution02.solve(checkCodes, inspectors, allowedGap));
    }

    static Stream<Arguments> recordCases() {
        return Stream.of(
                Arguments.of("빈 기록", new int[]{}, 1, 0, new int[]{}),
                Arguments.of("기록 하나와 점검자 상한", new int[]{940}, 1_000, 0, new int[]{}),
                Arguments.of(
                        "간격은 맞지만 처음 반복된 코드",
                        new int[]{5, 6, 5, 100}, 3, 10, new int[]{3, 5}
                ),
                Arguments.of(
                        "코드 940에서 처음 간격 초과",
                        new int[]{0, 1, 2, 3, 940}, 3, 10, new int[]{2, 940}
                ),
                Arguments.of(
                        "더 늦은 중복보다 앞선 간격 오류 반환",
                        new int[]{1, 5, 1}, 2, 2, new int[]{2, 5}
                ),
                Arguments.of(
                        "코드 하한과 상한의 허용 간격 경계",
                        new int[]{-1_000_000, 1_000_000}, 1_000, 2_000_000, new int[]{}
                )
        );
    }

    @Test
    @DisplayName("점검자 940명이 한 바퀴 돈 뒤 담당 번호")
    void calculatesInspectorAfterFullRound() {
        int[] checkCodes = new int[942];
        for (int i = 0; i <= 940; i++) {
            checkCodes[i] = i;
        }
        checkCodes[941] = 0;
        int[] before = checkCodes.clone();

        int[] actual = SetSolution02.solve(checkCodes, 940, 940);

        assertAll(
                () -> assertArrayEquals(new int[]{2, 0}, actual),
                () -> assertArrayEquals(before, checkCodes)
        );
    }

    @Test
    @DisplayName("최대 길이의 고유 연속 기록")
    void handlesMaximumLength() {
        int[] checkCodes = new int[100_000];
        for (int i = 0; i < checkCodes.length; i++) {
            checkCodes[i] = i - 50_000;
        }
        int[] before = checkCodes.clone();

        int[] actual = SetSolution02.solve(checkCodes, 1_000, 1);

        assertAll(
                () -> assertArrayEquals(new int[]{}, actual),
                () -> assertArrayEquals(before, checkCodes)
        );
    }
}

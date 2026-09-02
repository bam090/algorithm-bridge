package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@DisplayName("백트래킹 03 - 모든 장치를 실행하는 첫 순서 찾기")
final class BacktrackingSolution03Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 장치 수 | 1 이상 8 이하 | 1, 2, 3, 4, 8
     * initialEnergy | 0 이상 1,000 이하 | 0, 4, 5, 940, 1,000
     * requiredEnergy[i] | 0 이상 1,000 이하 | 0, 2, 4, 6, 940, 1,000
     * signedEnergyChange[i] | -1,000 이상 1,000 이하 | -1,000, -940, -3, 0, 4, 940, 1,000
     * 추가 계약 | 모든 장치 한 번, 사전식 첫 1기반 순서, 원본 보존 | 충전·소모, 불가능, 선택 취소, 최대 순열
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("deviceCases")
    @DisplayName("에너지 경계와 첫 실행 순서")
    void findsFirstDeviceOrder(
            String name,
            int initialEnergy,
            int[] requiredEnergy,
            int[] signedEnergyChange,
            int[] expected
    ) {
        int[] originalRequired = requiredEnergy.clone();
        int[] originalChange = signedEnergyChange.clone();

        int[] actual = BacktrackingSolution03.solve(initialEnergy, requiredEnergy, signedEnergyChange);

        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalRequired, requiredEnergy),
                () -> assertArrayEquals(originalChange, signedEnergyChange),
                () -> assertNotSame(requiredEnergy, actual, "결과는 필요 에너지 배열과 달라야 한다."),
                () -> assertNotSame(signedEnergyChange, actual, "결과는 변화량 배열과 달라야 한다.")
        );
    }

    static Stream<Arguments> deviceCases() {
        return Stream.of(
                Arguments.of(
                        "낮은 번호에서 충전 장치로 이어지는 첫 순서",
                        5, new int[]{4, 6, 2}, new int[]{-3, 4, 4}, new int[]{1, 3, 2}
                ),
                Arguments.of(
                        "장치 하나와 에너지 중간값",
                        940, new int[]{940}, new int[]{0}, new int[]{1}
                ),
                Arguments.of(
                        "변화량과 필요 에너지의 하한·중간값·상한",
                        0,
                        new int[]{0, 1_000, 0, 940},
                        new int[]{1_000, -1_000, 940, -940},
                        new int[]{1, 2, 3, 4}
                ),
                Arguments.of("필요 에너지를 만족할 수 없어 불가능", 0, new int[]{1}, new int[]{1_000}, new int[]{}),
                Arguments.of("변화 뒤 에너지가 음수라 불가능", 0, new int[]{0}, new int[]{-1}, new int[]{}),
                Arguments.of(
                        "첫 후보 실패 뒤 다음 시작 후보로 성공",
                        4, new int[]{0, 4}, new int[]{-4, 4}, new int[]{2, 1}
                )
        );
    }

    @Test
    @DisplayName("최대 장치 수의 사전식 첫 순서")
    void handlesMaximumDeviceCount() {
        int length = 8;
        int[] requiredEnergy = new int[length];
        int[] signedEnergyChange = new int[length];

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                BacktrackingSolution03.solve(1_000, requiredEnergy, signedEnergyChange)
        );
    }
}

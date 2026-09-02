package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution02;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("백트래킹 02 - 목표 곱을 만드는 조합 세기")
final class BacktrackingSolution02Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * factorCards.length | 0 이상 15 이하 | 0, 1, 4, 5, 15
     * factorCards[i] | 2 이상 1,000 이하 | 2, 940, 1,000
     * target | 2 이상 1,000,000 이하 | 2, 12, 64, 940, 1,000,000
     * 추가 계약 | 한 장 한 번, 순서 중복 제외, 원본 보존 | 세 조합, 시작 위치, 초과 가지치기
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("factorCases")
    @DisplayName("카드·목표 경계와 중복 없는 조합")
    void countsFactorCombinations(String name, int[] factorCards, int target, int expected) {
        int[] original = factorCards.clone();

        int actual = BacktrackingSolution02.solve(factorCards, target);

        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(original, factorCards)
        );
    }

    static Stream<Arguments> factorCases() {
        return Stream.of(
                Arguments.of("서로 다른 세 조합", new int[]{2, 3, 4, 6, 12}, 12, 3),
                Arguments.of("빈 카드와 target 하한", new int[]{}, 2, 0),
                Arguments.of("카드 한 장으로 target 하한", new int[]{2}, 2, 1),
                Arguments.of("중간값 카드 한 장", new int[]{940, 2, 5}, 940, 1),
                Arguments.of("순서만 다른 조합을 한 번만 계산", new int[]{2, 4, 8, 16}, 64, 2),
                Arguments.of(
                        "factor와 target 상한에서 만들 수 없음",
                        new int[]{1_000, 2, 4, 8, 16},
                        1_000_000,
                        0
                ),
                Arguments.of(
                        "최대 카드 수와 여러 초과 가지",
                        new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47},
                        30,
                        1
                )
        );
    }
}

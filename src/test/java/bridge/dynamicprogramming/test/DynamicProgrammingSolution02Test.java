package bridge.dynamicprogramming.test;

import bridge.dynamicprogramming.solution.DynamicProgrammingSolution02;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class DynamicProgrammingSolution02Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 사진 수 | 0 이상 1,000 이하 | 0, 1, 3, 4, 940, 1,000
     * 한 장 비용 | 0 이상 1,000,000,000 이하 | 0, 7, 940, 1,000,000,000
     * 묶음 비용 | 0 이상 1,000,000,000 이하 | 0, 1, 6, 1,000, 1,000,000,000
     * 결과 자료형 | 최대 1,000장 비용의 합 | 500,000,000,000L
     * 추가 계약 | 빈 입력, 마지막 행동 두 경우, 겹치는 묶음, 원본 보존 | 각각 실행
     *
     * 대표 오답
     * - 사진이 0장 또는 1장일 때 존재하지 않는 이전 상태를 읽는다.
     * - 묶음 비용을 두 장의 한 장 비용에 추가한다.
     * - 가장 싼 묶음을 먼저 골라 서로 겹치는 선택을 한다.
     * - 비용 합을 int로 저장하거나 입력 배열을 정렬한다.
     */
    @DisplayName("사진 한 장과 두 장 묶음의 최소 비용을 계산한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testMinimumCost(String name, int[] singleCosts, int[] pairCosts, long expected) {
        check(name, singleCosts, pairCosts, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("빈 사진 목록", new int[]{}, new int[]{}, 0L),
                arguments("사진 한 장과 비용 상한", new int[]{1_000_000_000}, new int[]{}, 1_000_000_000L),
                arguments("마지막 한 장과 마지막 묶음 비교",
                        new int[]{6, 5, 7, 4}, new int[]{8, 10, 6}, 14L),
                arguments("서로 겹치는 싼 묶음을 탐욕으로 고르면 실패",
                        new int[]{8, 100, 8, 100}, new int[]{9, 1, 9}, 18L),
                arguments("비용 0·940·상한",
                        new int[]{0, 940, 1_000_000_000}, new int[]{940, 0}, 0L)
        );
    }

    @Test
    @DisplayName("사진 수 940과 일반 중간 비용을 처리한다")
    void testOrdinaryMiddleSize() {
        int[] singleCosts = new int[940];
        int[] pairCosts = new int[939];
        Arrays.fill(singleCosts, 940);
        Arrays.fill(pairCosts, 1_000);
        check("사진 수 940과 일반 중간 비용", singleCosts, pairCosts, 470_000L);
    }

    @Test
    @DisplayName("사진 수 상한에서 long 범위 결과를 계산한다")
    void testMaximumSizeAndLongResult() {
        int[] singleCosts = new int[1_000];
        int[] pairCosts = new int[999];
        Arrays.fill(singleCosts, 1_000_000_000);
        Arrays.fill(pairCosts, 1_000_000_000);
        check("사진 수 상한과 long 결과", singleCosts, pairCosts, 500_000_000_000L);
    }

    private static void check(String name, int[] singleCosts, int[] pairCosts, long expected) {
        int[] singleBefore = singleCosts.clone();
        int[] pairBefore = pairCosts.clone();
        long actual = DynamicProgrammingSolution02.solve(singleCosts, pairCosts);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(singleBefore, singleCosts, "한 장 비용 원본을 보존해야 한다"),
                () -> assertArrayEquals(pairBefore, pairCosts, "묶음 비용 원본을 보존해야 한다")
        );
    }
}

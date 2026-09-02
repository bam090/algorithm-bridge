package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class GreedySolution03Test {

    private static final double TOLERANCE = 1.0e-9;

    /*
     * 범위표
     * - 배열 길이: 0, 1, 일반 길이, 최대 100,000
     * - 사용 가능량: 0보다 가까운 소수, 940.0, 상한 1,000.0
     * - 전체 가격: 0.0, 940.0, 상한 1,000,000.0
     * - 필요량: 0.0, 일부 구매가 필요한 소수, 전체 사용 가능량
     * - 대표 오답: 전체 가격만 비교, 마지막 공급처 전부 구매, 비율 동점, 원래 번호 분실, 원본 변경
     */
    @DisplayName("단위 비용이 낮은 공급처부터 필요한 양을 구매한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testPurchaseAmounts(
            String name,
            double[] available,
            double[] costs,
            double requiredAmount,
            double[] expected
    ) {
        check(name, available, costs, requiredAmount, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("단위 비용순 선택과 원본 보존",
                        new double[]{2.0, 4.0, 3.0}, new double[]{10.0, 36.0, 21.0}, 4.0,
                        new double[]{2.0, 0.0, 2.0}),
                arguments("빈 공급처와 필요량 0",
                        new double[]{}, new double[]{}, 0.0, new double[]{}),
                arguments("공급처가 있어도 필요량 0",
                        new double[]{1.0, 2.0}, new double[]{10.0, 20.0}, 0.0,
                        new double[]{0.0, 0.0}),
                arguments("단위 비용 동점은 원래 번호 우선",
                        new double[]{1.0, 2.0, 3.0}, new double[]{5.0, 10.0, 30.0}, 2.0,
                        new double[]{1.0, 1.0, 0.0}),
                arguments("전체 가격보다 단위 비용을 우선",
                        new double[]{100.0, 10.0}, new double[]{100.0, 20.0}, 10.0,
                        new double[]{10.0, 0.0}),
                arguments("가격 0·상한과 사용 가능량 940·1000",
                        new double[]{940.0, 1_000.0}, new double[]{0.0, 1_000_000.0}, 940.5,
                        new double[]{940.0, 0.5}),
                arguments("소수 구매량은 허용 오차로 비교",
                        new double[]{0.1, 0.2}, new double[]{0.2, 0.6}, 0.3,
                        new double[]{0.1, 0.2}),
                arguments("공급처 하나의 양과 가격 상한",
                        new double[]{1_000.0}, new double[]{1_000_000.0}, 1_000.0,
                        new double[]{1_000.0})
        );
    }

    @Test
    @DisplayName("최대 100000개 공급처에서 마지막 공급처의 절반을 구매한다")
    void testMaximumSuppliers() {
        int size = 100_000;
        double[] available = new double[size];
        double[] costs = new double[size];
        double[] expected = new double[size];
        Arrays.fill(available, 1.0);
        Arrays.fill(costs, 940.0);
        Arrays.fill(expected, 1.0);
        expected[size - 1] = 0.5;
        check("최대 100000개와 마지막 절반 구매", available, costs, 99_999.5, expected);
    }

    private static void check(
            String name,
            double[] available,
            double[] costs,
            double requiredAmount,
            double[] expected
    ) {
        double[] availableBefore = available.clone();
        double[] costsBefore = costs.clone();
        double[] actual = GreedySolution03.solve(available, costs, requiredAmount);
        assertAll(name,
                () -> assertArrayEquals(expected, actual, TOLERANCE),
                () -> assertArrayEquals(availableBefore, available, TOLERANCE,
                        "사용 가능량 원본을 보존해야 한다"),
                () -> assertArrayEquals(costsBefore, costs, TOLERANCE,
                        "가격 원본을 보존해야 한다")
        );
    }
}

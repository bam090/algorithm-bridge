package bridge.queue.test;

import bridge.queue.solution.QueueSolution03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 QueueSolution03을 검증한다. */
public final class QueueSolution03Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("orderCases")
    @DisplayName("앞 주문을 기준으로 수령 회차를 묶는다")
    void groupsPickupRounds(String name, int[] expected, int[] requested, int[] preparation) {
        int[] requestedBefore = requested.clone();
        int[] preparationBefore = preparation.clone();
        int[] actual = QueueSolution03.solve(requested, preparation);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(requestedBefore, requested),
                () -> assertArrayEquals(preparationBefore, preparation));
    }

    @Test
    @DisplayName("최대 주문 수를 한 회차로 처리")
    void test07() {
        int size = 100_000;
        int[] requested = new int[size];
        int[] preparation = new int[size];
        preparation[0] = 1_000_000;
        int[] expected = new int[size];
        Arrays.fill(expected, 1);
        int[] requestedBefore = requested.clone();
        int[] preparationBefore = preparation.clone();

        int[] actual = QueueSolution03.solve(requested, preparation);
        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(requestedBefore, requested),
                () -> assertArrayEquals(preparationBefore, preparation));
    }

    private static Stream<Arguments> orderCases() {
        return Stream.of(
                Arguments.of("두 수령 회차와 원본 보존", new int[]{1, 1, 2, 2, 2},
                        new int[]{0, 2, 4, 6, 8}, new int[]{10, 3, 8, 2, 1}),
                Arguments.of("주문이 없으면 빈 결과", new int[]{}, new int[]{}, new int[]{}),
                Arguments.of("요청과 준비 시간의 하한 0", new int[]{1}, new int[]{0}, new int[]{0}),
                Arguments.of("0·940·상한을 포함한 같은 회차", new int[]{1, 1, 1, 1},
                        new int[]{0, 0, 940, 940}, new int[]{1_000, 940, 0, 60}),
                Arguments.of("준비 시각이 계속 늦어져 모두 다른 회차", new int[]{1, 2, 3},
                        new int[]{0, 940, 1_000_000}, new int[]{0, 0, 1_000_000}),
                Arguments.of("앞 주문 장벽 뒤에 일찍 준비된 주문 묶기", new int[]{1, 2, 2, 3},
                        new int[]{0, 0, 0, 0}, new int[]{5, 6, 1, 7})
        );
    }

}

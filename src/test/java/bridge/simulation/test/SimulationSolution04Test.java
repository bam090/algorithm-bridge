package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution04;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class SimulationSolution04Test {

    /*
     * 매개변수 | 허용 범위
     * number | 0 이상 9_999_999_999_999_999 이하
     */
    @DisplayName("숫자를 한 자리로 줄이는 과정을 검증한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("numberCases")
    void testTransform(String name, long number, int[] expected) {
        assertArrayEquals(expected, SimulationSolution04.solve(number), name);
    }

    private static Stream<Arguments> numberCases() {
        return Stream.of(
                arguments("일반 중간값 940", 940L, new int[]{4, 2, 1}),
                arguments("하한 0은 변환하지 않음", 0L, new int[]{0, 0, 0}),
                arguments("한 자리 상한 9", 9L, new int[]{9, 0, 0}),
                arguments("두 자리 수의 0 세기", 10L, new int[]{1, 1, 1}),
                arguments("여러 변환에서 0 누적", 1_000_000_009L, new int[]{1, 2, 9}),
                arguments("long 입력 상한", 9_999_999_999_999_999L, new int[]{9, 2, 0})
        );
    }
}

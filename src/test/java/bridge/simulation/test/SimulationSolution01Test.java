package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution01;
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

public final class SimulationSolution01Test {

    /*
     * 매개변수 | 허용 범위
     * minimumValue | -1_000 이상 1_000 이하
     * initialValue | minimumValue 이상 maximumValue 이하
     * maximumValue | -1_000 이상 1_000 이하
     * commands.length | 0 이상 2_000 이하
     * commands[i] | "UP" 또는 "DOWN"
     */
    @DisplayName("명령을 범위 안에서만 적용한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testCommands(
            String name,
            int initialValue,
            String[] commands,
            int minimumValue,
            int maximumValue,
            int[] expected
    ) {
        check(name, initialValue, commands, minimumValue, maximumValue, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("정상 입력과 거절된 명령",
                        0, new String[]{"UP", "UP", "DOWN"}, -1, 1, new int[]{0, 1}),
                arguments("빈 명령 배열과 일반 중간값 940",
                        940, new String[0], 0, 1_000, new int[]{940, 0}),
                arguments("최솟값 하한에서 아래 명령 거절",
                        -1_000, new String[]{"DOWN", "UP"}, -1_000, 1_000, new int[]{-999, 1}),
                arguments("0과 최댓값 상한에서 후보 확인",
                        1_000, new String[]{"UP", "DOWN", "UP", "UP"}, 0, 1_000,
                        new int[]{1_000, 2}),
                arguments("일반값의 연속 변화",
                        940, new String[]{"DOWN", "DOWN", "UP"}, -1_000, 1_000,
                        new int[]{939, 0})
        );
    }

    @Test
    @DisplayName("명령 최대 길이에서 거절 횟수를 누적한다")
    void testMaximumCommandCount() {
        String[] commands = new String[2_000];
        Arrays.fill(commands, "UP");
        check("명령 최대 길이와 누적 거절", 0, commands, 0, 1_000,
                new int[]{1_000, 1_000});
    }

    private static void check(String name, int initialValue, String[] commands,
                              int minimumValue, int maximumValue, int[] expected) {
        String[] original = commands.clone();
        int[] actual = SimulationSolution01.solve(initialValue, commands, minimumValue, maximumValue);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(original, commands, "명령 배열 원본을 보존해야 한다")
        );
    }
}

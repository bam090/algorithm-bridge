package bridge.stack.test;

import bridge.stack.solution.StackSolution02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 StackSolution02를 검증한다. */
public final class StackSolution02Test {

    /*
     * 테스트 범위
     * - previousCheckpoint 길이: 하한 1, 중간 940, 상한 100,000
     * - 이전 번호: 하한 0과 가장 가까운 직전 번호
     * - destination: 첫 체크포인트, 가지 끝, 중간 940, 마지막 100,000
     */
    @Test
    @DisplayName("가지가 있는 경로와 원본 보존")
    void test01() {
        int[] previous = {0, 1, 2, 2, 4};
        int[] before = previous.clone();
        int[] actual = StackSolution02.solve(previous, 5);
        assertAll(
                () -> assertArrayEquals(new int[]{1, 2, 4, 5}, actual),
                () -> assertArrayEquals(before, previous));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("routeCases")
    @DisplayName("이전 체크포인트를 따라 경로를 복원한다")
    void restoresRoute(String name, int[] expected, int[] previous, int destination) {
        assertArrayEquals(expected, StackSolution02.solve(previous, destination), name);
    }

    @Test
    @DisplayName("중간 번호 940")
    void test04() {
        int[] previous = new int[940];
        for (int index = 1; index < previous.length; index++) {
            previous[index] = 1;
        }
        assertArrayEquals(new int[]{1, 940}, StackSolution02.solve(previous, 940));
    }

    @Test
    @DisplayName("최대 길이의 긴 경로")
    void test05() {
        int[] previous = new int[100_000];
        for (int index = 1; index < previous.length; index++) {
            previous[index] = index;
        }

        int[] expected = new int[100_000];
        for (int index = 0; index < expected.length; index++) {
            expected[index] = index + 1;
        }
        assertArrayEquals(expected, StackSolution02.solve(previous, 100_000));
    }

    private static Stream<Arguments> routeCases() {
        return Stream.of(
                Arguments.of("길이 하한과 출발점이 목적지", new int[]{1}, new int[]{0}, 1),
                Arguments.of("출발점에서 마지막 지점으로 바로 이동", new int[]{1, 4},
                        new int[]{0, 1, 1, 1}, 4)
        );
    }
}

package bridge.queue.test;

import bridge.queue.solution.QueueSolution01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 QueueSolution01을 검증한다. */
public final class QueueSolution01Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("rotationCases")
    @DisplayName("맨 앞 값을 맨 뒤로 한 칸 회전한다")
    void rotatesOnce(String name, int[] expected, int[] original) {
        int[] before = original.clone();
        int[] actual = QueueSolution01.solve(original);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(before, original),
                () -> assertNotSame(original, actual));
    }

    @Test
    @DisplayName("최대 길이에서 한 칸만 회전")
    void test05() {
        int size = 100_000;
        int[] original = new int[size];
        int[] expected = new int[size];
        for (int i = 0; i < size; i++) {
            original[i] = i;
        }
        System.arraycopy(original, 1, expected, 0, size - 1);
        expected[size - 1] = 0;
        int[] before = original.clone();

        int[] actual = QueueSolution01.solve(original);
        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(before, original),
                () -> assertNotSame(original, actual));
    }

    private static Stream<Arguments> rotationCases() {
        return Stream.of(
                Arguments.of("네 값 회전과 원본 보존", new int[]{20, 30, 40, 10},
                        new int[]{10, 20, 30, 40}),
                Arguments.of("빈 순서는 빈 새 배열", new int[]{}, new int[]{}),
                Arguments.of("원소 하나와 중간값 940", new int[]{940}, new int[]{940}),
                Arguments.of("값 하한·0·중간·상한의 순서",
                        new int[]{0, 940, 1_000_000, -1_000_000},
                        new int[]{-1_000_000, 0, 940, 1_000_000})
        );
    }

}

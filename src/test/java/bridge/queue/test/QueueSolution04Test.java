package bridge.queue.test;

import bridge.queue.solution.QueueSolution04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 QueueSolution04를 검증한다. */
public final class QueueSolution04Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("planCases")
    @DisplayName("두 검사대의 맨 앞 시료로 계획을 처리한다")
    void followsPlan(String name, int[] expected, int[] first, int[] second, int[] plan) {
        int[] firstBefore = first.clone();
        int[] secondBefore = second.clone();
        int[] planBefore = plan.clone();
        int[] actual = QueueSolution04.solve(first, second, plan);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(firstBefore, first),
                () -> assertArrayEquals(secondBefore, second),
                () -> assertArrayEquals(planBefore, plan));
    }

    @Test
    @DisplayName("최대 시료 수를 두 검사대에서 교대로 꺼내기")
    void test07() {
        int half = 50_000;
        int[] first = new int[half];
        int[] second = new int[half];
        int[] plan = new int[half * 2];
        int[] expected = new int[half * 2];

        for (int i = 0; i < half; i++) {
            first[i] = i;
            second[i] = i + 100_000;
            plan[i * 2] = first[i];
            plan[i * 2 + 1] = second[i];
            expected[i * 2] = 1;
            expected[i * 2 + 1] = 2;
        }

        int[] firstBefore = first.clone();
        int[] secondBefore = second.clone();
        int[] planBefore = plan.clone();
        int[] actual = QueueSolution04.solve(first, second, plan);
        assertAll(
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(firstBefore, first),
                () -> assertArrayEquals(secondBefore, second),
                () -> assertArrayEquals(planBefore, plan));
    }

    private static Stream<Arguments> planCases() {
        return Stream.of(
                Arguments.of("두 검사대를 번갈아 사용하고 원본 보존", new int[]{1, 2, 2, 1},
                        new int[]{11, 13, 17}, new int[]{20, 22}, new int[]{11, 20, 22, 13}),
                Arguments.of("빈 검사 계획은 성공한 빈 결과", new int[]{},
                        new int[]{1}, new int[]{2}, new int[]{}),
                Arguments.of("첫 검사대가 비어도 둘째 검사대로 계획 완료", new int[]{2, 2},
                        new int[]{}, new int[]{940, 1_000_000}, new int[]{940, 1_000_000}),
                Arguments.of("줄 안쪽 시료를 건너뛸 수 없어 실패", new int[]{-1},
                        new int[]{1, 2}, new int[]{3}, new int[]{2}),
                Arguments.of("두 검사대에 없는 시료에서 실패", new int[]{-1},
                        new int[]{}, new int[]{940}, new int[]{-1_000_000}),
                Arguments.of("시료 번호 하한·0·940·상한", new int[]{1, 2, 1, 2},
                        new int[]{-1_000_000, 0}, new int[]{940, 1_000_000},
                        new int[]{-1_000_000, 940, 0, 1_000_000})
        );
    }

}

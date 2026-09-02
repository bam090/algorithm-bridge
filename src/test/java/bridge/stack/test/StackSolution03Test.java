package bridge.stack.test;

import bridge.stack.solution.StackSolution03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 StackSolution03을 검증한다. */
public final class StackSolution03Test {

    /*
     * 테스트 범위
     * - operations 길이: 하한 1, 일반 길이, 상한 200
     * - 필름 번호: 하한 1, 중간 940, 상한 1,000
     * - 결과: 시작점별 성공 높이 1 이상, 같은 번호 중첩, 실패 시작점 -1
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("operationCases")
    @DisplayName("각 시작점의 독립 작업 결과를 구한다")
    void evaluatesStartPositions(String name, int[] expected, int[] operations) {
        assertArrayEquals(expected, StackSolution03.solve(operations), name);
    }

    @Test
    @DisplayName("번호 하한·중간·상한과 원본 보존")
    void test06() {
        int[] operations = {1, 940, 1_000, -1_000, -940, -1};
        int[] before = operations.clone();
        int[] actual = StackSolution03.solve(operations);
        assertAll(
                () -> assertArrayEquals(new int[]{3, -1, -1, -1, -1, -1}, actual),
                () -> assertArrayEquals(before, operations, "operations 원본이 바뀌었습니다."));
    }

    @Test
    @DisplayName("최대 길이와 최대 높이")
    void test07() {
        int[] operations = new int[200];
        for (int index = 0; index < 100; index++) {
            operations[index] = 1_000;
            operations[operations.length - 1 - index] = -1_000;
        }
        int[] expected = new int[200];
        Arrays.fill(expected, -1);
        expected[0] = 100;
        assertArrayEquals(expected, StackSolution03.solve(operations));
    }

    private static Stream<Arguments> operationCases() {
        return Stream.of(
                Arguments.of("시작점별 최대 높이와 실패 표시", new int[]{2, -1, 2, -1, -1, -1},
                        new int[]{1, -1, 2, 3, -3, -2}),
                Arguments.of("독립 작업 두 묶음의 성공 시작점", new int[]{1, -1, 1, -1},
                        new int[]{1, -1, 2, -2}),
                Arguments.of("성공한 시작점 없음", new int[]{-1, -1, -1, -1},
                        new int[]{1, 2, -1, -2}),
                Arguments.of("같은 번호 필름 중첩", new int[]{2, -1, -1, -1},
                        new int[]{1, 1, -1, -1}),
                Arguments.of("길이 하한에서 마지막 잔여 확인", new int[]{-1}, new int[]{1})
        );
    }

}

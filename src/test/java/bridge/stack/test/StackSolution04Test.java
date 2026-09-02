package bridge.stack.test;

import bridge.stack.solution.StackSolution04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 StackSolution04를 검증한다. */
public final class StackSolution04Test {

    /*
     * 테스트 범위
     * - heights 길이: 하한 0, 원소 하나, 일반 길이, 상한 200,000
     * - 높이: 하한 -1,000, 0, 중간 940, 상한 1,000
     * - 관계: 더 높은 값 없음, 같은 값, 한 현재 값이 여러 이전 위치 해결
     */
    @Test
    @DisplayName("여러 위치의 첫 더 높은 표지와 원본 보존")
    void test01() {
        int[] heights = {5, 2, 1, 4, 6};
        int[] before = heights.clone();
        int actual = StackSolution04.solve(heights);
        assertAll(
                () -> assertEquals(4, actual),
                () -> assertArrayEquals(before, heights, "heights 원본이 바뀌었습니다."));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("heightCases")
    @DisplayName("더 높은 값을 처음 만나는 위치까지 거리를 구한다")
    void findsFirstHigherDistance(String name, int expected, int[] heights) {
        assertEquals(expected, StackSolution04.solve(heights), name);
    }

    @Test
    @DisplayName("최대 길이의 같은 높이")
    void test08() {
        int[] heights = new int[200_000];
        Arrays.fill(heights, 940);
        assertEquals(0, StackSolution04.solve(heights));
    }

    private static Stream<Arguments> heightCases() {
        return Stream.of(
                Arguments.of("계속 낮아져 더 높은 표지가 없음", 0, new int[]{5, 4, 3}),
                Arguments.of("같은 높이는 답이 아님", 2, new int[]{4, 4, 5}),
                Arguments.of("한 현재 값이 여러 이전 위치 해결", 4, new int[]{5, 1, 2, 3, 6}),
                Arguments.of("높이 하한·0·중간·상한", 2,
                        new int[]{-1_000, 0, -5, 940, 1_000}),
                Arguments.of("길이 하한인 빈 배열", 0, new int[]{}),
                Arguments.of("원소 하나", 0, new int[]{1_000})
        );
    }

}

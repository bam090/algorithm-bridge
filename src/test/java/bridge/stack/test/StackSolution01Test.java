package bridge.stack.test;

import bridge.stack.solution.StackSolution01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/** JUnit Jupiter로 StackSolution01을 검증한다. */
public final class StackSolution01Test {

    /*
     * 테스트 범위
     * - events 길이: 1, 일반 길이, 최대 100,000
     * - 번호 절댓값: 하한 1, 중간 940, 상한 1,000
     * - 결과: 정상 0, 첫 위치 오류, 중간 오류, 마지막 뒤 잔여 오류
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("eventCases")
    @DisplayName("쌓기와 꺼내기 순서를 검증한다")
    void checksEventOrder(String name, int expected, int[] events) {
        assertEquals(expected, StackSolution01.solve(events), name);
    }

    @Test
    @DisplayName("최대 길이 기록")
    void test06() {
        int[] events = new int[100_000];
        for (int index = 0; index < events.length / 2; index++) {
            events[index] = 1_000;
            events[events.length - 1 - index] = -1_000;
        }
        assertEquals(0, StackSolution01.solve(events));
    }

    private static Stream<Arguments> eventCases() {
        return Stream.of(
                Arguments.of("겹쳐 쌓고 정확히 꺼내기", 0, new int[]{1, 2, -2, -1}),
                Arguments.of("빈 곳에서 처음부터 꺼내기", 1, new int[]{-1}),
                Arguments.of("최근 번호와 다른 중간 꺼내기", 3, new int[]{1, 2, -1, -2}),
                Arguments.of("끝까지 처리한 뒤 상자가 남음", 4, new int[]{1, -1, 940}),
                Arguments.of("번호 하한·중간·상한과 같은 번호 중첩", 0,
                        new int[]{1, 940, 1_000, 1_000, -1_000, -1_000, -940, -1})
        );
    }
}

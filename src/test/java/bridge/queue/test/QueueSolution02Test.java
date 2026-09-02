package bridge.queue.test;

import bridge.queue.solution.QueueSolution02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

/** JUnit Jupiter로 QueueSolution02를 검증한다. */
public final class QueueSolution02Test {

    /*
     * 검증 범위
     * - scores 길이 0·1·1,000, 점수 -1,000·0·940·1,000
     * - rotations 길이 0·1·1,000, 회전 횟수 0·940·1,000
     * - removalLimit -1,000·0·2·4·940·1,000
     * - 제거로 현재 큐 길이가 바뀐 뒤의 회전과 두 입력 배열의 원본 보존
     */

    @ParameterizedTest(name = "{0}")
    @MethodSource("commandCases")
    @DisplayName("회전 명령 뒤 조건에 맞는 앞 값을 제거한다")
    void executesCommands(String name, int[] expected, int[] scores,
            int[] rotations, int removalLimit) {
        int[] scoresBefore = scores.clone();
        int[] rotationsBefore = rotations.clone();
        int[] actual = QueueSolution02.solve(scores, rotations, removalLimit);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(scoresBefore, scores),
                () -> assertArrayEquals(rotationsBefore, rotations),
                () -> assertNotSame(scores, actual));
    }

    @Test
    @DisplayName("최대 길이와 명령 수에서 모두 제거")
    void test08() {
        int[] scores = new int[1_000];
        Arrays.fill(scores, -1_000);
        int[] rotations = new int[1_000];
        int[] before = scores.clone();

        int[] actual = QueueSolution02.solve(scores, rotations, -1_000);
        assertAll(
                () -> assertArrayEquals(new int[]{}, actual),
                () -> assertArrayEquals(before, scores),
                () -> assertNotSame(scores, actual));
    }

    private static Stream<Arguments> commandCases() {
        return Stream.of(
                Arguments.of("회전 뒤 조건부 제거와 두 원본 보존", new int[]{9, 7},
                        new int[]{4, 9, 2, 7}, new int[]{2, 1, 0}, 4),
                Arguments.of("명령이 없으면 같은 순서의 새 배열", new int[]{3, 1, 2},
                        new int[]{3, 1, 2}, new int[]{}, 0),
                Arguments.of("빈 큐는 남은 명령 건너뛰기", new int[]{},
                        new int[]{}, new int[]{0, 940, 1_000}, 0),
                Arguments.of("기준과 같은 값까지 연속 제거", new int[]{1_000},
                        new int[]{-1_000, 0, 940, 1_000}, new int[]{0, 0, 0, 0}, 940),
                Arguments.of("첫 제거 뒤 줄어든 현재 길이로 940회 회전", new int[]{3, 4, 2},
                        new int[]{1, 2, 3, 4}, new int[]{0, 940}, 2),
                Arguments.of("제거 기준 상한 1,000과 같은 값 제거", new int[]{},
                        new int[]{1_000}, new int[]{0}, 1_000),
                Arguments.of("기준보다 큰 앞 값은 남기고 다음 명령 처리", new int[]{9, 5},
                        new int[]{5, 1, 9}, new int[]{0, 1}, 4)
        );
    }

}

package bridge.set.test;

import bridge.set.solution.SetSolution04;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("집합 04 - 연결 요청과 감사 처리하기")
final class SetSolution04Test {

    /*
     * 검증 범위
     * 항목        | 제약                    | 실제 확인값
     * deviceCount | 0~100,000               | 0·2·4·6·1,000·100,000
     * 요청 수     | 0~200,000               | 0·1·3·4·5·7·200,000개
     * 장비 번호   | 0~deviceCount-1         | 0·940·999·99,999
     * 요청 종류   | LINK 또는 AUDIT         | 선행·반복 LINK, 성공·실패·자기 AUDIT
     * 대표 오답   | LINK 선처리, 최종 대표 누락, 요청 번호 오프바이원, 반복 연결 손상, 원본 변경
     */
    @Test
    @DisplayName("요청 시점에 따른 실패 1·5와 원본 보존")
    void respectsRequestOrderWithoutChangingInputs() {
        String[] actions = {"AUDIT", "LINK", "LINK", "AUDIT", "AUDIT", "LINK", "AUDIT"};
        int[] firstIds = {0, 0, 1, 0, 0, 2, 0};
        int[] secondIds = {1, 1, 2, 2, 5, 5, 5};
        String[] actionsBefore = actions.clone();
        int[] firstBefore = firstIds.clone();
        int[] secondBefore = secondIds.clone();

        int[] actual = SetSolution04.solve(6, actions, firstIds, secondIds);

        assertAll(
                () -> assertArrayEquals(new int[]{1, 5}, actual),
                () -> assertArrayEquals(actionsBefore, actions),
                () -> assertArrayEquals(firstBefore, firstIds),
                () -> assertArrayEquals(secondBefore, secondIds)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("requestCases")
    @DisplayName("요청 종류와 번호 경계")
    void returnsFailedRequestNumbers(
            String name,
            int deviceCount,
            String[] actions,
            int[] firstIds,
            int[] secondIds,
            int[] expected
    ) {
        assertArrayEquals(expected, SetSolution04.solve(deviceCount, actions, firstIds, secondIds));
    }

    static Stream<Arguments> requestCases() {
        return Stream.of(
                Arguments.of(
                        "장비와 요청이 모두 없음",
                        0, new String[]{}, new int[]{}, new int[]{}, new int[]{}
                ),
                Arguments.of(
                        "AUDIT 요청 한 건의 실패 번호 1",
                        2, new String[]{"AUDIT"}, new int[]{0}, new int[]{1}, new int[]{1}
                ),
                Arguments.of(
                        "바로 위 부모가 달라도 최종 대표가 같은 연결",
                        4,
                        new String[]{"LINK", "LINK", "LINK", "AUDIT"},
                        new int[]{0, 2, 1, 0},
                        new int[]{1, 3, 3, 3},
                        new int[]{}
                ),
                Arguments.of(
                        "전체 요청 번호 4에서 실패",
                        4,
                        new String[]{"LINK", "AUDIT", "LINK", "AUDIT"},
                        new int[]{0, 0, 2, 0},
                        new int[]{1, 1, 3, 3},
                        new int[]{4}
                ),
                Arguments.of(
                        "자기 연결·반복 연결 뒤 감사 성공",
                        2,
                        new String[]{"LINK", "LINK", "LINK", "AUDIT", "AUDIT"},
                        new int[]{0, 0, 1, 1, 0},
                        new int[]{0, 1, 0, 1, 1},
                        new int[]{}
                ),
                Arguments.of(
                        "여러 실패의 전체 요청 번호만 반환",
                        4,
                        new String[]{"AUDIT", "LINK", "AUDIT", "AUDIT"},
                        new int[]{0, 0, 2, 0},
                        new int[]{1, 1, 3, 1},
                        new int[]{1, 3}
                )
        );
    }

    @Test
    @DisplayName("장비 0·940 연결 뒤 999 감사 실패와 원본 보존")
    void handlesIntermediateIdsWithoutChangingInputs() {
        String[] actions = {"LINK", "AUDIT", "AUDIT"};
        int[] firstIds = {0, 0, 940};
        int[] secondIds = {940, 940, 999};
        String[] actionsBefore = actions.clone();
        int[] firstBefore = firstIds.clone();
        int[] secondBefore = secondIds.clone();

        int[] actual = SetSolution04.solve(1_000, actions, firstIds, secondIds);

        assertAll(
                () -> assertArrayEquals(new int[]{3}, actual),
                () -> assertArrayEquals(actionsBefore, actions),
                () -> assertArrayEquals(firstBefore, firstIds),
                () -> assertArrayEquals(secondBefore, secondIds)
        );
    }

    @Test
    @DisplayName("최대 장비와 요청 수에서 압축된 연결 감사")
    void handlesMaximumCounts() {
        int deviceCount = 100_000;
        int requestCount = 200_000;
        int linkCount = deviceCount - 1;
        String[] actions = new String[requestCount];
        int[] firstIds = new int[requestCount];
        int[] secondIds = new int[requestCount];

        for (int i = 0; i < linkCount; i++) {
            actions[i] = "LINK";
            firstIds[i] = i;
            secondIds[i] = i + 1;
        }
        for (int i = linkCount; i < requestCount; i++) {
            actions[i] = "AUDIT";
            firstIds[i] = 0;
            secondIds[i] = (i - linkCount) % deviceCount;
        }

        assertArrayEquals(new int[]{}, SetSolution04.solve(deviceCount, actions, firstIds, secondIds));
    }
}

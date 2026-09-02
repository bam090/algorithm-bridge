package bridge.hash.test;

import bridge.hash.solution.HashSolution05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

public final class HashSolution05Test {

    /*
     * 매개변수 | 허용 범위
     * taskIds.length | 0 이상 10_000 이하
     * teams.length, minutes.length, urgency.length | taskIds.length와 같음
     * taskIds[i].length(), teams[i].length() | 1 이상 20 이하
     * minutes[i] | 0 이상 1_000 이하
     * urgency[i] | 0 이상 1_000 이하
     * perTeamLimit | 1 이상 10 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("taskCases")
    @DisplayName("팀과 작업을 정해진 순서로 정렬한다")
    void testTasks(
            String name,
            String[] taskIds,
            String[] teams,
            int[] minutes,
            int[] urgency,
            int perTeamLimit,
            String[] expected
    ) {
        check(name, taskIds, teams, minutes, urgency, perTeamLimit, expected);
    }

    @Test
    @DisplayName("작업 길이와 팀별 제한 상한")
    void testMaximumTaskLength() {
        int length = 10_000;
        String[] taskIds = new String[length];
        String[] teams = new String[length];
        int[] minutes = new int[length];
        int[] urgency = new int[length];

        for (int index = 0; index < length; index++) {
            int number = length - 1 - index;
            taskIds[index] = String.format("task%05d", number);
            teams[index] = "team";
            minutes[index] = 940;
            urgency[index] = 940;
        }

        String[] expected = new String[10];
        for (int index = 0; index < expected.length; index++) {
            expected[index] = String.format("task%05d", index);
        }
        check("작업 길이와 팀별 제한 상한", taskIds, teams, minutes, urgency, 10, expected);
    }

    private static Stream<Arguments> taskCases() {
        String longTeam = "abcdefghijklmnopqrst";
        String longTaskId = "abcdefghijklmnopqrst";
        return Stream.of(
                Arguments.of(
                        "팀 순서와 팀 안 순서",
                        new String[]{"R1", "B1", "R2", "B2", "R3"},
                        new String[]{"red", "blue", "red", "blue", "red"},
                        new int[]{30, 10, 20, 15, 10},
                        new int[]{5, 3, 5, 4, 5},
                        2,
                        new String[]{"B2", "B1", "R3", "R2"}),
                Arguments.of(
                        "빈 작업과 제한 하한",
                        new String[0], new String[0], new int[0], new int[0], 1, new String[0]),
                Arguments.of(
                        "작업 하나와 문자열 길이 하한·상한",
                        new String[]{"x"},
                        new String[]{longTeam},
                        new int[]{0},
                        new int[]{940},
                        1,
                        new String[]{"x"}),
                Arguments.of(
                        "고정 두 개만 고르는 오답 방지",
                        new String[]{"t3", "t1", "t2"},
                        new String[]{"A", "A", "A"},
                        new int[]{30, 10, 20},
                        new int[]{1, 1, 1},
                        10,
                        new String[]{"t1", "t2", "t3"}),
                Arguments.of(
                        "팀 합계 동률과 작업 시간 기준",
                        new String[]{"z2", "a2", "z1", "a1"},
                        new String[]{"z", "a", "z", "a"},
                        new int[]{10, 20, 20, 10},
                        new int[]{5, 5, 5, 5},
                        2,
                        new String[]{"a1", "a2", "z2", "z1"}),
                Arguments.of(
                        "모든 수치가 같을 때 작업 ID 기준",
                        new String[]{"c", "a", "b"},
                        new String[]{"team", "team", "team"},
                        new int[]{940, 940, 940},
                        new int[]{940, 940, 940},
                        2,
                        new String[]{"a", "b"}),
                Arguments.of(
                        "시간·긴급도 하한·중간값·상한",
                        new String[]{"low", "mid", longTaskId},
                        new String[]{"a", "b", "b"},
                        new int[]{0, 940, 1_000},
                        new int[]{0, 940, 1_000},
                        10,
                        new String[]{"low", longTaskId, "mid"})
        );
    }

    private static void check(
            String name,
            String[] taskIds,
            String[] teams,
            int[] minutes,
            int[] urgency,
            int perTeamLimit,
            String[] expected
    ) {
        String[] originalTaskIds = taskIds.clone();
        String[] originalTeams = teams.clone();
        int[] originalMinutes = minutes.clone();
        int[] originalUrgency = urgency.clone();
        String[] actual = HashSolution05.solve(taskIds, teams, minutes, urgency, perTeamLimit);

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalTaskIds, taskIds, "원본 taskIds가 변경되었습니다."),
                () -> assertArrayEquals(originalTeams, teams, "원본 teams가 변경되었습니다."),
                () -> assertArrayEquals(originalMinutes, minutes, "원본 minutes가 변경되었습니다."),
                () -> assertArrayEquals(originalUrgency, urgency, "원본 urgency가 변경되었습니다."),
                () -> assertNotSame(taskIds, actual, "결과가 원본 taskIds와 같은 배열입니다."),
                () -> assertNotSame(teams, actual, "결과가 원본 teams와 같은 배열입니다."));
    }

}

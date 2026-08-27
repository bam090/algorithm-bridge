package bridge.hash.test;

import bridge.hash.solution.HashSolution05;

import java.util.Arrays;

public final class HashSolution05Test {

    private static int passed;
    private static int failed;

    private HashSolution05Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * taskIds.length | 0 이상 10_000 이하
     * teams.length, minutes.length, urgency.length | taskIds.length와 같음
     * taskIds[i].length(), teams[i].length() | 1 이상 20 이하
     * minutes[i] | 0 이상 1_000 이하
     * urgency[i] | 0 이상 1_000 이하
     * perTeamLimit | 1 이상 10 이하
     */
    public static void main(String[] args) {
        runCase("팀 순서와 팀 안 순서", HashSolution05Test::testTeamAndTaskRanking);
        runCase("빈 작업과 제한 하한", HashSolution05Test::testEmptyTasks);
        runCase("작업 하나와 문자열 길이 하한·상한", HashSolution05Test::testOneTaskAndStringLengthBounds);
        runCase("고정 두 개만 고르는 오답 방지", HashSolution05Test::testVariableLimitLargerThanTeam);
        runCase("팀 합계 동률과 작업 시간 기준", HashSolution05Test::testTeamTotalTieAndMinuteTieBreaker);
        runCase("모든 수치가 같을 때 작업 ID 기준", HashSolution05Test::testTaskIdentifierTieBreaker);
        runCase("시간·긴급도 하한·중간값·상한", HashSolution05Test::testNumberBounds);
        runCase("작업 길이와 팀별 제한 상한", HashSolution05Test::testMaximumTaskLength);

        finish();
    }

    private static void runCase(String name, Runnable test) {
        try {
            test.run();
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error);
        }
    }

    private static void testTeamAndTaskRanking() {
        String[] taskIds = {"R1", "B1", "R2", "B2", "R3"};
        String[] teams = {"red", "blue", "red", "blue", "red"};
        int[] minutes = {30, 10, 20, 15, 10};
        int[] urgency = {5, 3, 5, 4, 5};
        check("팀 순서와 팀 안 순서", taskIds, teams, minutes, urgency, 2,
                new String[]{"B2", "B1", "R3", "R2"});
    }

    private static void testEmptyTasks() {
        check("빈 작업과 제한 하한", new String[0], new String[0], new int[0], new int[0], 1,
                new String[0]);
    }

    private static void testOneTaskAndStringLengthBounds() {
        String longTeam = "abcdefghijklmnopqrst";
        check("작업 하나와 문자열 길이 하한·상한", new String[]{"x"}, new String[]{longTeam},
                new int[]{0}, new int[]{940}, 1, new String[]{"x"});
    }

    private static void testVariableLimitLargerThanTeam() {
        String[] taskIds = {"t3", "t1", "t2"};
        String[] teams = {"A", "A", "A"};
        int[] minutes = {30, 10, 20};
        int[] urgency = {1, 1, 1};
        check("고정 두 개만 고르는 오답 방지", taskIds, teams, minutes, urgency, 10,
                new String[]{"t1", "t2", "t3"});
    }

    private static void testTeamTotalTieAndMinuteTieBreaker() {
        String[] taskIds = {"z2", "a2", "z1", "a1"};
        String[] teams = {"z", "a", "z", "a"};
        int[] minutes = {10, 20, 20, 10};
        int[] urgency = {5, 5, 5, 5};
        check("팀 합계 동률과 작업 시간 기준", taskIds, teams, minutes, urgency, 2,
                new String[]{"a1", "a2", "z2", "z1"});
    }

    private static void testTaskIdentifierTieBreaker() {
        String[] taskIds = {"c", "a", "b"};
        String[] teams = {"team", "team", "team"};
        int[] minutes = {940, 940, 940};
        int[] urgency = {940, 940, 940};
        check("모든 수치가 같을 때 작업 ID 기준", taskIds, teams, minutes, urgency, 2,
                new String[]{"a", "b"});
    }

    private static void testNumberBounds() {
        String longTaskId = "abcdefghijklmnopqrst";
        String[] taskIds = {"low", "mid", longTaskId};
        String[] teams = {"a", "b", "b"};
        int[] minutes = {0, 940, 1_000};
        int[] urgency = {0, 940, 1_000};
        check("시간·긴급도 하한·중간값·상한", taskIds, teams, minutes, urgency, 10,
                new String[]{"low", longTaskId, "mid"});
    }

    private static void testMaximumTaskLength() {
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
        boolean originalsPreserved = Arrays.equals(taskIds, originalTaskIds)
                && Arrays.equals(teams, originalTeams)
                && Arrays.equals(minutes, originalMinutes)
                && Arrays.equals(urgency, originalUrgency);
        boolean newArrayReturned = actual != taskIds && actual != teams;
        boolean success = Arrays.equals(actual, expected) && originalsPreserved && newArrayReturned;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalsPreserved=" + originalsPreserved
                    + ", newArrayReturned=" + newArrayReturned);
        }
    }

    private static void finish() {
        System.out.println("[RESULT] HashSolution05: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("HashSolution05 실패: " + failed + "건");
        }
    }
}

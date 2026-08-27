package bridge.backtracking.solution;

/** 담당자와 도구가 겹치지 않는 일정 문제 정답과 풀이 설명이다. */
public final class BacktrackingSolution04 {

    private BacktrackingSolution04() {
    }

    public static boolean solve(int[][] workerIds, int[][] toolIds) {
        boolean[] usedWorkers = new boolean[1_001];
        boolean[] usedTools = new boolean[1_001];
        return canAssignDay(0, workerIds, toolIds, usedWorkers, usedTools);
    }

    private static boolean canAssignDay(
            int day,
            int[][] workerIds,
            int[][] toolIds,
            boolean[] usedWorkers,
            boolean[] usedTools
    ) {
        if (day == workerIds.length) {
            return true;
        }

        for (int candidate = 0; candidate < workerIds[day].length; candidate++) {
            int worker = workerIds[day][candidate];
            int tool = toolIds[day][candidate];
            if (usedWorkers[worker] || usedTools[tool]) {
                continue;
            }

            usedWorkers[worker] = true;
            usedTools[tool] = true;
            boolean completed = canAssignDay(day + 1, workerIds, toolIds, usedWorkers, usedTools);
            usedWorkers[worker] = false;
            usedTools[tool] = false;

            if (completed) {
                return true;
            }
        }
        return false;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 날짜마다 후보 하나를 골라야 하므로 재귀 깊이를 날짜로 둘 수 있다.
     * - 담당자와 도구 중 하나라도 이미 사용했다면 그 후보를 고를 수 없다.
     * - 가능한 일정 하나만 찾으면 되므로 완성 즉시 true를 반환할 수 있다.
     *
     * 이 개념을 선택한 이유
     * - 담당자와 도구의 사용 여부를 각각 표시하면 두 중복 조건을 바로 확인할 수 있다.
     * - 한 후보가 실패했을 때 두 표시를 지우고 다음 후보를 보면 가능한 모든 일정을 확인할 수 있다.
     *
     * 풀이 순서
     * 1. day가 전체 날짜 수와 같으면 모든 배정이 끝났으므로 true를 반환한다.
     * 2. 현재 날짜의 후보를 앞에서부터 확인한다.
     * 3. 담당자나 도구가 이미 사용 중이면 건너뛴다.
     * 4. 두 값을 사용 중이라고 표시하고 다음 날짜를 배정한다.
     * 5. 돌아오면 두 표시를 지우고, 완성에 성공했다면 true를 반환한다.
     * 6. 모든 후보가 실패하면 false를 반환한다.
     *
     * 예시 데이터 흐름
     * - 0일에 담당자 2와 도구 10을 고른다.
     * - 1일에는 담당자 1과 도구 11을 고를 수 있다.
     * - 두 날짜가 끝났고 담당자와 도구가 겹치지 않으므로 true를 반환한다.
     *
     * 복잡도
     * - 시간 O(c^d): 날짜 d개마다 후보가 최대 c개라면 모든 후보 조합을 확인할 수 있다.
     * - 공간 O(d + 1,001): 재귀 깊이 d와 담당자·도구 사용 배열을 사용한다.
     *
     * 자주 하는 실수
     * - 담당자만 확인하고 도구 중복을 놓친다.
     * - 실패해 돌아온 뒤 한쪽 표시만 지워 가능한 다음 후보를 막는다.
     * - 성공한 재귀에서 바로 반환하면서 표시를 지우지 않아 메서드의 작업 상태를 남긴다.
     */
}

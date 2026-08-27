package bridge.backtracking.problem;

/*
 * 문제 | 담당자와 도구가 겹치지 않는 일정
 *
 * 여러 날짜에 작업을 하나씩 배정해야 한다.
 * workerIds[day][candidate]와 toolIds[day][candidate]는 같은 후보 배정을 나타낸다.
 * 모든 날짜에서 후보를 정확히 하나씩 고르되, 같은 담당자와 같은 도구를 두 날짜에 다시 쓸 수 없다.
 * 이런 전체 일정이 하나라도 있으면 true, 없으면 false를 반환하라.
 *
 * 입력과 출력
 * - 입력: 날짜별 후보 담당자 workerIds와 같은 위치의 후보 도구 toolIds
 * - 출력: 모든 날짜를 배정할 수 있는지 여부
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 재귀 깊이 하나를 날짜 하나로 보면 언제 전체 일정이 완성되는가?
 * - 현재 후보의 담당자와 도구를 각각 어디에 사용 중이라고 표시할 수 있는가?
 * - 담당자는 비어 있지만 도구가 이미 사용 중이면 이 후보를 고를 수 있는가?
 * - 한 후보로 남은 날짜를 배정하지 못하고 돌아오면 어떤 두 표시를 지워야 하는가?
 */
public final class BacktrackingProblem04 {

    private BacktrackingProblem04() {
        solve(
                new int[][]{{1, 2}, {1, 3}},
                new int[][]{{10, 10}, {11, 10}}
        );
        // 예상 출력: true
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 날짜마다 담당자와 도구가 모두 비어 있는 후보 하나를 표시해 다음 날짜로 이동하고, 실패해 돌아오면 두 표시를 지워 전체 일정 가능 여부를 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - workerIds와 toolIds는 null이 아니다.
     * - 1 <= workerIds.length <= 7
     * - workerIds.length == toolIds.length
     * - 각 day에서 1 <= workerIds[day].length <= 7
     * - 각 day에서 workerIds[day].length == toolIds[day].length
     * - 0 <= workerIds[day][candidate], toolIds[day][candidate] <= 1,000
     * - 같은 날짜 안에 담당자와 도구가 모두 같은 후보 쌍은 중복되지 않는다.
     * - 입력 배열을 바꾸지 않는다.
     * - 최대 후보 확인량은 가지치기 전 7^7이므로 제한 안에서 실행할 수 있다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static boolean solve(int[][] workerIds, int[][] toolIds) {
        boolean answer = false;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 담당자 번호와 도구 번호를 서로 다른 boolean[]에 표시한다.
     * - 현재 날짜의 배정이 끝나면 다음 날짜로 이동하고, 돌아오면 두 표시를 모두 지운다.
     */
    //endregion
}

package bridge.tree.problem;

/*
 * 문제 | 목표 값의 방문 순번
 * values에는 서로 다른 값이 완전 이진 트리 순서로 들어 있다.
 * 재귀로 왼쪽과 오른쪽 자식을 방문할 때 현재 값을 기록할 순간은 visitMoment가 정한다.
 * BEFORE는 두 자식보다 전, BETWEEN은 왼쪽 자식 뒤와 오른쪽 자식 전,
 * AFTER는 두 자식 뒤에 현재 값을 기록한다.
 * 선택한 기록 순서에서 target이 몇 번째인지 1부터 세어 반환하라.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 왼쪽과 오른쪽 자식을 방문하는 재귀 호출은 어떤 부분이 항상 같은가?
 * - visitMoment에 따라 현재 값을 목록에 넣는 한 줄은 어디로 이동해야 하는가?
 * - 배열 밖 인덱스에 도착한 재귀 호출은 언제 끝나야 하는가?
 * - 목표 값의 목록 인덱스를 문제에서 1부터 세는 순번으로 어떻게 바꾸는가?
 */
public final class TreeProblem02 {

    private TreeProblem02() {
        solve(new int[]{10, 20, 30, 40, 50, 60, 70}, 50, "BETWEEN");
        // 예상 출력: 3 (기록 순서는 40, 20, 50, 10, 60, 30, 70)
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 같은 왼쪽·오른쪽 재귀 사이에서 visitMoment가 정한 때에 현재 값을 목록에 기록한 뒤, target의 목록 위치에 1을 더해 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - values는 null이 아니며 길이는 1 이상 100,000 이하이다.
     * - 각 값은 -1,000,000 이상 1,000,000 이하이며 모든 값은 서로 다르다.
     * - target은 values에 정확히 한 번 들어 있다.
     * - visitMoment는 "BEFORE", "BETWEEN", "AFTER" 중 하나이다.
     * - values는 완전 이진 트리를 나타내며 입력 배열을 바꾸면 안 된다.
     * - 길이가 최대일 때 유효한 노드는 뿌리부터 최대 17단계이며,
     *   배열 밖 종료 호출까지 포함하면 동시에 쌓이는 재귀 호출은 최대 18단계이다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[] values, int target, String visitMoment) {
        int answer = 0;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 재귀 함수에 values, 현재 인덱스, visitMoment와 기록 목록을 전달한다.
     * - BEFORE는 왼쪽 호출 전, BETWEEN은 두 호출 사이, AFTER는 오른쪽 호출 뒤에 현재 값을 기록한다.
     * - 기록 목록에서 target을 찾은 인덱스에 1을 더한다.
     */
    //endregion
}

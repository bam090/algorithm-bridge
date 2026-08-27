package bridge.set.problem;

/*
 * 문제 | 연결 뒤 남은 작업 구역 수
 * 0번부터 itemCount-1번까지의 작업대가 처음에는 각각 혼자 떨어진 구역에 있다.
 * connections의 각 행은 new int[]{a, b}이며, a 작업대가 속한 구역과 b 작업대가 속한 구역을 하나로 잇는다.
 * 같은 연결이 반복되거나 자기 자신과 연결되어도 이미 같은 구역이면 구역 수는 줄지 않는다.
 * 모든 연결을 처리한 뒤 서로 떨어진 작업 구역이 몇 개인지 반환하라.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 처음에는 작업대마다 대표가 누구이며 구역은 몇 개인가?
 * - 작업대 두 개가 이미 같은 구역인지 무엇을 비교해 알 수 있는가?
 * - 연결할 때 작업대 자체와 최종 대표 중 무엇을 이어야 하는가?
 * - 같은 구역을 다시 연결할 때 구역 수를 줄여도 되는가?
 */
public final class SetProblem03 {

    private SetProblem03() {
        solve(6, new int[][]{{4, 2}, {3, 1}, {2, 1}});
        // 예상 출력: 3
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 각 작업대를 자기 대표로 시작하고, 연결마다 두 최종 대표가 다를 때만 합쳐 구역 수를 줄인 뒤 남은 구역 수를 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - itemCount는 0 이상 100,000 이하이다.
     * - connections는 null이 아니며 길이는 0 이상 100,000 이하이다.
     * - 각 연결은 길이가 2인 int[]이다.
     * - itemCount가 0이면 connections는 비어 있다.
     * - 각 작업대 번호는 0 이상 itemCount-1 이하이다.
     * - 원본 connections는 바꾸지 않는다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int itemCount, int[][] connections) {
        int answer = 0;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - parent[i] = i로 모든 작업대를 자기 구역에서 시작한다.
     * - 부모를 따라가 자기 자신을 부모로 가진 최종 대표를 찾는다.
     * - 두 대표가 다를 때만 한 대표를 다른 대표에 연결하고 구역 수를 1 줄인다.
     */
    //endregion
}

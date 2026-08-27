package bridge.dynamicprogramming.problem;

/*
 * 문제 | 사진 묶음 인쇄 최소 비용
 *
 * 사진을 0번부터 마지막 사진까지 순서대로 모두 인쇄해야 한다.
 * singleCosts[index]를 내면 index번 사진 한 장을 인쇄할 수 있다.
 * pairCosts[index]를 내면 index번과 index + 1번 사진을 한 묶음으로 인쇄할 수 있다.
 * 한 사진을 두 번 인쇄하거나 빼놓을 수 없을 때 필요한 최소 비용을 반환하라.
 *
 * 입력과 출력
 * - 입력: 사진 한 장씩의 비용 singleCosts와 이웃한 두 장 묶음의 비용 pairCosts
 * - 출력: 모든 사진을 정확히 한 번 인쇄하는 최소 비용
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 앞에서부터 count장의 사진을 모두 인쇄한 최소 비용을 어떤 칸에 저장할 것인가?
 * - 마지막 사진을 한 장으로 인쇄했다면 그전에 몇 장이 끝나 있어야 하는가?
 * - 마지막 두 사진을 묶음으로 인쇄했다면 그전에 몇 장이 끝나 있어야 하는가?
 * - 사진이 0장 또는 1장일 때 바로 알 수 있는 답은 무엇인가?
 */
public final class DynamicProgrammingProblem02 {

    private DynamicProgrammingProblem02() {
        solve(
                new int[]{6, 5, 7, 4},
                new int[]{8, 10, 6}
        );
        // 예상 출력: 14L
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 마지막 인쇄를 한 장으로 끝내는 비용과 두 장 묶음으로 끝내는 비용을 각각 이전의 최소 비용에 더한 뒤, 더 작은 값을 현재 사진 수의 답으로 저장해 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - singleCosts와 pairCosts는 null이 아니다.
     * - 0 <= singleCosts.length <= 1,000
     * - pairCosts.length는 Math.max(0, singleCosts.length - 1)이다.
     * - 0 <= singleCosts[index] <= 1,000,000,000
     * - 0 <= pairCosts[index] <= 1,000,000,000
     * - 최소 비용은 int 범위를 넘을 수 있으므로 long으로 반환한다.
     * - 입력 배열은 바꾸면 안 된다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static long solve(int[] singleCosts, int[] pairCosts) {
        long answer = 0L;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - cost[count]를 앞의 count장을 모두 인쇄한 최소 비용으로 정한다.
     * - 사진이 0장이면 cost[0]은 0이고, 1장이면 한 장 비용만 선택할 수 있다.
     * - 마지막 한 장을 인쇄하는 경우에는 cost[count - 1]을 읽는다.
     * - 마지막 두 장을 묶는 경우에는 cost[count - 2]를 읽고 두 값 중 작은 쪽을 저장한다.
     */
    //endregion
}

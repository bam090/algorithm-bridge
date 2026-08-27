package bridge.greedy.problem;

/*
 * 문제 | 실습 키트 싸게 준비하기
 * kitCosts[i]는 i번 실습 키트 한 개의 가격이다. 모든 키트의 구성과 품질은 같다.
 * 정확히 requiredCount개의 키트를 사는 데 필요한 최소 총비용을 반환하라.
 * requiredCount가 0이면 0을 반환하며 원본 배열은 바꾸면 안 된다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 모든 키트가 같다면 어떤 가격의 키트부터 골라야 총비용이 작아지는가?
 * - requiredCount가 0이면 배열을 정렬하거나 값을 더할 필요가 있는가?
 * - 원본 가격 순서를 보존하려면 무엇을 정렬해야 하는가?
 * - 가격 100,000개를 더할 때 int만 사용해도 안전한가?
 */
public final class GreedyProblem02 {

    private GreedyProblem02() {
        solve(new int[]{7_000, 2_000, 4_000, 1_000}, 3);
        // 예상 출력: 7000L
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 가격 배열을 복사해 오름차순으로 정렬하고, 앞에서 requiredCount개의 가격을 long으로 더해 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - kitCosts는 null이 아니다.
     * - kitCosts의 길이는 0 이상 100,000 이하이다.
     * - 각 가격은 0 이상 1,000,000,000 이하이다.
     * - requiredCount는 0 이상 kitCosts.length 이하이다.
     * - 모든 키트의 구성과 품질은 같다.
     * - 원본 배열은 바꾸지 않는다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static long solve(int[] kitCosts, int requiredCount) {
        long answer = 0L;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 같은 키트라면 비싼 키트를 고를 이유가 없다.
     * - 정렬할 배열은 kitCosts의 복사본으로 만든다.
     * - 정렬된 앞부분에서 requiredCount개만 long 변수에 더한다.
     */
    //endregion
}

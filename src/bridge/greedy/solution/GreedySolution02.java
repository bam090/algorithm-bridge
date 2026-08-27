package bridge.greedy.solution;

import java.util.Arrays;

/** 실습 키트 싸게 준비하기 문제의 정답과 풀이 설명이다. */
public final class GreedySolution02 {

    private GreedySolution02() {
    }

    public static long solve(int[] kitCosts, int requiredCount) {
        int[] orderedCosts = kitCosts.clone();
        Arrays.sort(orderedCosts);

        long totalCost = 0L;
        for (int i = 0; i < requiredCount; i++) {
            totalCost += orderedCosts[i];
        }
        return totalCost;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 정확히 requiredCount개를 골라야 한다.
     * - 모든 키트의 구성과 품질은 같으므로 가격만 비교하면 된다.
     * - 가격과 개수가 커서 합계는 long으로 계산해야 한다.
     *
     * 이 선택이 안전한 이유
     * - 고른 키트보다 싼 키트를 빼놓았다면 둘을 바꾸어 키트 수는 유지하면서 총비용을 줄일 수 있다.
     * - 따라서 싼 키트부터 requiredCount개를 고른 결과가 최소 비용이다.
     *
     * 풀이 순서
     * 1. 원본을 보존하도록 가격 배열을 복사해 오름차순 정렬한다.
     * 2. 정렬된 앞부분에서 requiredCount개의 가격을 long 변수에 더한다.
     * 3. 최소 총비용을 반환한다.
     *
     * 예시 데이터 흐름
     * - 가격 복사본 [7000,2000,4000,1000]은 [1000,2000,4000,7000]이 된다.
     * - 필요한 3개의 가격 1000+2000+4000을 더한다.
     * - 7000을 반환한다.
     *
     * 복잡도
     * - 시간 O(n log n): n개 가격을 정렬하고 필요한 앞부분을 더한다.
     * - 공간 O(n): 원본을 보존할 복사본이 필요하다.
     *
     * 자주 하는 실수
     * - 배열 전체를 더해 requiredCount 조건을 무시한다.
     * - 비싼 키트부터 고른다.
     * - 총비용을 int로 계산해 큰 입력에서 값이 넘친다.
     * - 원본 배열을 직접 정렬한다.
     */
}

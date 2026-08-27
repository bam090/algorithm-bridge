package bridge.backtracking.solution;

/** 곱으로 잠금 번호 만들기 문제 정답과 풀이 설명이다. */
public final class BacktrackingSolution02 {

    private BacktrackingSolution02() {
    }

    public static int solve(int[] factorCards, int target) {
        return countCombinations(factorCards, target, 0, 1L);
    }

    private static int countCombinations(
            int[] factorCards,
            int target,
            int startIndex,
            long currentProduct
    ) {
        if (currentProduct == target) {
            return 1;
        }

        int count = 0;
        for (int index = startIndex; index < factorCards.length; index++) {
            int factor = factorCards[index];
            if (currentProduct > target / factor) {
                continue;
            }
            count += countCombinations(factorCards, target, index + 1, currentProduct * factor);
        }
        return count;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 카드 순서가 아니라 어떤 카드를 골랐는지가 조합을 구분한다.
     * - 모든 카드 값이 2 이상이므로 카드를 더 고르면 곱은 작아지지 않는다.
     * - target을 넘은 곱은 뒤에 어떤 카드를 골라도 다시 target이 될 수 없다.
     *
     * 이 개념을 선택한 이유
     * - 다음 시작 위치를 넘기면 앞 카드를 다시 골라 순서만 다른 중복을 만들지 않는다.
     * - 곱이 target을 넘기 전에 가지를 건너뛰면 필요 없는 선택을 줄일 수 있다.
     *
     * 풀이 순서
     * 1. 현재 곱이 target이면 조합 하나를 찾았으므로 1을 반환한다.
     * 2. startIndex부터 고를 카드 후보를 확인한다.
     * 3. 현재 곱이 target / 카드 값보다 크면 곱한 결과가 target을 넘으므로 건너뛴다.
     * 4. 카드를 고르면 다음 재귀는 index + 1부터 확인한다.
     * 5. 각 가지에서 찾은 조합 수를 더해 반환한다.
     *
     * 예시 데이터 흐름
     * - factorCards=[2, 3, 4, 6, 12], target=12
     * - 2를 고른 가지에서는 뒤의 6을 골라 12를 만든다.
     * - 3을 고른 가지에서는 뒤의 4를 골라 12를 만든다.
     * - 앞 카드를 고르지 않은 가지에서는 12 한 장을 골라 12를 만든다.
     * - 조합은 {2, 6}, {3, 4}, {12}이므로 3을 반환한다.
     *
     * 복잡도
     * - 시간 O(2^n): 카드 n장을 고르거나 고르지 않는 조합을 최대 한 번씩 확인한다.
     * - 공간 O(n): 재귀 깊이는 고른 카드 수를 넘지 않는다.
     *
     * 자주 하는 실수
     * - 재귀마다 0번 카드부터 다시 확인해 같은 조합을 여러 순서로 센다.
     * - target과 같은 뒤에도 카드를 더 골라 이미 완성된 조합을 중복으로 센다.
     * - currentProduct * factor를 int로 먼저 계산해 범위를 넘긴 뒤 비교한다.
     */
}

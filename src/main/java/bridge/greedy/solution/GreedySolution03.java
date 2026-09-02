package bridge.greedy.solution;

import java.util.Arrays;

/** 세정액 필요한 만큼 싸게 사기 문제의 정답과 풀이 설명이다. */
public final class GreedySolution03 {

    private GreedySolution03() {
    }

    public static double[] solve(
            double[] availableAmounts,
            double[] wholeBatchCosts,
            double requiredAmount
    ) {
        // 그리디를 선택한 이유:
        // 세정액을 나눠 살 수 있으므로, 단위 비용이 싼 공급처부터 필요한 양만 고르면 된다.

        // [1] 공급처마다 wholeBatchCosts / availableAmounts로 단위 비용을 계산한다.
        double[] unitCosts = new double[availableAmounts.length];
        Integer[] order = new Integer[availableAmounts.length];
        for (int i = 0; i < order.length; i++) {
            unitCosts[i] = wholeBatchCosts[i] / availableAmounts[i];
            order[i] = i;
        }

        // [2] 공급처 번호를 단위 비용 오름차순, 원래 번호 오름차순으로 정렬한다.
        Arrays.sort(order, (left, right) -> {
            int byUnitCost = Double.compare(unitCosts[left], unitCosts[right]);
            if (byUnitCost != 0) {
                return byUnitCost;
            }
            return Integer.compare(left, right);
        });

        double[] selectedAmounts = new double[availableAmounts.length];
        double remaining = requiredAmount;
        for (int index : order) {
            if (remaining <= 0.0) {
                break;
            }

            // [3] 싼 공급처부터 availableAmounts와 남은 필요량 중 작은 양을 선택한다.
            double selected = Math.min(availableAmounts[index], remaining);

            // [4] 구매량을 원래 공급처 번호 위치에 기록한다.
            selectedAmounts[index] = selected;
            remaining -= selected;
        }

        // [5] 공급처별 구매량을 원래 번호 순서로 반환한다.
        return selectedAmounts;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 공급처마다 전체 양이 달라 전체 가격만으로는 어느 쪽이 싼지 알 수 없다.
     * - 세정액은 필요한 만큼 나눌 수 있고 requiredAmount를 정확히 채워야 한다.
     * - 결과는 비용순이 아니라 원래 공급처 번호 순서다.
     *
     * 이 선택이 안전한 이유
     * - 비싼 단위의 양을 샀는데 더 싼 단위가 남아 있다면 같은 양을 바꾸어 전체 비용을 줄일 수 있다.
     * - 세정액을 나눌 수 있으므로 필요한 양만 정확히 바꿀 수 있다.
     * - 나눌 수 없는 통 단위라면 이 교환이 불가능하므로 같은 기준이 항상 안전하지 않다.
     *
     * 풀이 순서
     * 1. 공급처마다 wholeBatchCosts / availableAmounts로 단위 비용을 계산한다.
     * 2. 공급처 번호를 단위 비용 오름차순, 원래 번호 오름차순으로 정렬한다.
     * 3. 싼 공급처부터 availableAmounts와 남은 필요량 중 작은 양을 선택한다.
     * 4. 구매량을 원래 공급처 번호 위치에 기록한다.
     * 5. 공급처별 구매량을 원래 번호 순서로 반환한다.
     *
     * 예시 데이터 흐름
     * - 단위 비용은 0번 5, 1번 9, 2번 7이다.
     * - 0번에서 2만큼 사면 필요량 4 중 2가 남는다.
     * - 다음으로 싼 2번에서 2만큼만 산다.
     * - 원래 번호 순서로 [2.0,0.0,2.0]을 반환한다.
     *
     * 복잡도
     * - 시간 O(n log n): n개 공급처 번호를 단위 비용순으로 정렬한다.
     * - 공간 O(n): 단위 비용, 정렬할 번호와 원래 순서의 구매량 배열이 필요하다.
     *
     * 자주 하는 실수
     * - 전체 가격만 비교하고 판매하는 전체 양을 나누지 않는다.
     * - 마지막 공급처의 전부를 사서 requiredAmount를 넘긴다.
     * - 정렬 순서 그대로 구매량을 반환해 원래 번호를 잃는다.
     * - double 결과를 테스트할 때 오차 없이 완전히 같은지만 비교한다.
     */
}

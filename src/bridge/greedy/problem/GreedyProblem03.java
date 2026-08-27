package bridge.greedy.problem;

//region 문제: 세정액 필요한 만큼 싸게 사기
/*
 availableAmounts[i]는 i번 공급처가 판매할 수 있는 세정액의 전체 양이고,
 wholeBatchCosts[i]는 그 전체 양을 모두 샀을 때의 가격이다.
 세정액은 필요한 만큼 나누어 살 수 있고, 가격도 사용한 양에 비례한다.

 requiredAmount를 정확히 채우는 최소 비용 조합을 찾아, 공급처별 구매량을 원래 순서로 반환하라.
 단위 비용이 같으면 번호가 작은 공급처부터 사용하며 원본 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 공급처마다 양과 전체 가격이 다르면 한 단위의 가격은 어떻게 계산하는가?
 - 마지막 공급처에서는 availableAmounts의 전부가 아니라 일부만 살 수 있는가?
 - 단위 비용이 같은 공급처는 어떤 순서로 골라야 결과가 하나로 정해지는가?
 - 비용순으로 골라도 결과 배열은 어떤 순서로 반환해야 하는가?
 */
//endregion

public final class GreedyProblem03 {

    private GreedyProblem03() {
        solve(
                new double[]{2.0, 4.0, 3.0},
                new double[]{10.0, 36.0, 21.0},
                4.0
        );
        // 예상 출력: new double[]{2.0, 0.0, 2.0}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     공급처별 단위 비용을 계산해 낮은 순서로 고르고, 마지막에 필요한 양만 나누어 원래 공급처 순서로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - availableAmounts와 wholeBatchCosts는 null이 아니고 길이가 같다.
     - 두 배열의 길이는 0 이상 100,000 이하이다.
     - 각 availableAmounts 값은 0.0보다 크고 1,000.0 이하인 유한한 수이다.
     - 각 wholeBatchCosts 값은 0.0 이상 1,000,000.0 이하인 유한한 수이다.
     - requiredAmount는 0.0 이상 availableAmounts 전체 합 이하인 유한한 수이다.
     - 세정액은 0.0부터 availableAmounts[i]까지 실수 단위로 나누어 살 수 있다.
     - 구매 비용은 wholeBatchCosts[i] / availableAmounts[i]인 단위 비용에 구매량을 곱한다.
     - 단위 비용이 같으면 원래 번호가 작은 공급처부터 사용한다.
     - 원본 배열은 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static double[] solve(
            double[] availableAmounts,
            double[] wholeBatchCosts,
            double requiredAmount
    ) {
        double[] answer = {};
        return answer;
    }
}

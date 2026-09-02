package bridge.greedy.problem;

//region 문제: 균형 범위의 추 두 개 고르기
/*
 weights의 각 값은 추 하나의 무게이며 각 추는 한 번만 사용할 수 있다.
 두 추의 합이 minimumPairWeight 이상 maximumPairWeight 이하인 쌍을 가장 많이 만들고,
 만들 수 있는 최대 쌍의 개수를 반환하라.
 빈 입력이나 추가 하나뿐인 입력은 0을 반환하며 원본 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 가장 가벼운 추와 가장 무거운 추의 합이 하한보다 작으면 어느 쪽을 바꿔야 하는가?
 - 그 합이 상한보다 크면 어느 쪽을 바꿔야 하는가?
 - 합이 허용 범위 안이면 두 추를 다시 사용할 수 있는가?
 - 추 하나가 남으면 쌍의 개수를 늘릴 수 있는가?
 */
//endregion

public final class GreedyProblem04 {

    private GreedyProblem04() {
        solve(new int[]{10, 20, 40, 50, 70}, 60, 80);
        // 예상 출력: 2
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     무게를 정렬해 양끝 합을 확인하고, 낮으면 왼쪽, 높으면 오른쪽을 옮기며 범위 안일 때 두 추를 쌍으로 센다.
     */
    //endregion

    //region 제약 조건
    /*
     - weights는 null이 아니다.
     - weights의 길이는 0 이상 100,000 이하이다.
     - 각 무게는 0 이상 1,000,000,000 이하이다.
     - minimumPairWeight는 0 이상 2,000,000,000 이하이다.
     - maximumPairWeight는 minimumPairWeight 이상 2,000,000,000 이하이다.
     - 각 추는 최대 한 쌍에만 사용할 수 있다.
     - 합이 하한 또는 상한과 같으면 허용 범위에 포함한다.
     - 원본 배열은 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[] weights, int minimumPairWeight, int maximumPairWeight) {
        int answer = 0;
        return answer;
    }
}

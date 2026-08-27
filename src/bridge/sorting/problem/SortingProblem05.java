package bridge.sorting.problem;

//region 문제: 가장 가까운 점검 시각 간격 찾기
/*
 inspections에는 여러 점검이 진행된 시각이 정수로 들어 있다.
 서로 다른 두 점검을 골랐을 때 만들 수 있는 가장 작은 시각 차이를 반환한다.
 같은 시각에 점검이 두 번 있었다면 차이는 0이다.
 점검이 두 개보다 적으면 비교할 쌍이 없으므로 -1을 반환한다.
 입력 배열의 순서는 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 점검 시각 배열 inspections
 - 출력: 서로 다른 두 점검 사이의 가장 작은 시각 차이, 비교할 쌍이 없으면 -1
 */
//endregion

//region 입출력 예시
/*
 inspections = [40, 5, 17, 20]
 결과 = 3
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 점검이 최대 100_000개라면 모든 두 점검을 직접 확인하는 방식은 얼마나 많은 비교를 하는가?
 - 두 시각의 차이가 작다는 조건에서 숫자들이 가진 어떤 관계를 살펴봐야 하는가?
 - 같은 시각이 두 번 나오면 가능한 가장 작은 차이는 얼마인가?
 - 점검이 없거나 하나뿐이면 문제에서 어떤 값을 반환하라고 했는가?
 */
//endregion

public final class SortingProblem05 {

    private SortingProblem05() {
        solve(new int[]{40, 5, 17, 20});
        // 예상 출력: 3
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     입력을 복사해 정렬하고, 정렬된 배열의 이웃한 값만 비교해 가장 작은 차이를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 0 <= inspections.length <= 100_000
     - -1_000 <= inspections[i] <= 1_000
     - 점검이 두 개보다 적으면 -1을 반환한다.
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion
    public static int solve(int[] inspections) {
        int answer = -1;
        // 여기에 직접 구현한다.
        return answer;
    }
}

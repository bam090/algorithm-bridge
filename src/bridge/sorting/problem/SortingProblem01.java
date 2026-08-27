package bridge.sorting.problem;


//region 문제: 반복 측정값만 정리하기
/*
 기계가 남긴 정수 측정값 중 minimumCount번 이상 나온 값만 다시 확인하려고 한다.
 조건을 만족한 값은 나온 횟수만큼 모두 남기고, 작은 값부터 정렬한 새 배열로 반환한다.
 입력 readings의 순서는 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 정수 측정값 readings, 결과에 남길 최소 등장 횟수 minimumCount
 - 출력: minimumCount번 이상 나온 값을 모두 오름차순으로 담은 새 배열
 */
//endregion

//region 입출력 예시
/*
 readings = [3, -1, 3, 2, -1, 3, 4], minimumCount = 2
 결과 = [-1, -1, 3, 3, 3]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 측정값의 범위가 작고 정해져 있을 때 값마다 나온 횟수를 어디에 저장할 수 있는가?
 - 음수 측정값을 배열의 위치로 바꾸려면 어떤 값을 더해야 하는가?
 - 결과 배열의 길이는 언제 알 수 있는가?
 - 작은 값부터 결과에 담으려면 횟수 배열을 어느 방향으로 확인해야 하는가?
 */
//endregion

public final class SortingProblem01 {

    private SortingProblem01() {
        solve(new int[]{3, -1, 3, 2, -1, 3, 4}, 2);
        // 예상 출력: [-1, -1, 3, 3, 3]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     값마다 나온 횟수를 저장하고, 최소 횟수를 만족하는 값의 결과 길이를 구한 뒤,
     작은 값부터 나온 횟수만큼 새 배열에 담아 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 0 <= readings.length <= 10_000
     - -1_000 <= readings[i] <= 1_000
     - 1 <= minimumCount <= 10_000
     - 조건을 만족하는 값이 없으면 빈 배열을 반환한다.
     - 입력 배열은 바꾸지 않고 항상 새 배열을 반환한다.
     */
    //endregion

    public static int[] solve(int[] readings, int minimumCount) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 값 -1_000을 횟수 배열의 첫 칸에 놓으려면 모든 값에 1_000을 더해 위치로 바꿀 수 있다.
     - 먼저 조건을 만족한 값들의 횟수를 더하면 결과 배열의 길이를 알 수 있다.
     - 횟수 배열을 앞에서부터 확인하면 실제 값도 작은 값부터 복원할 수 있다.
     */
    //endregion
}

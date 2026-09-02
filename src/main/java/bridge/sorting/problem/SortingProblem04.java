package bridge.sorting.problem;

//region 문제: 선택 구간의 값 간격표 만들기
/*
 measurements에서 startIndex부터 endIndex까지의 값만 고른다.
 고른 값을 오름차순으로 정렬한 뒤, 이웃한 두 값의 차이를 차례대로 담은 새 배열을 반환한다.
 고른 값이 하나면 이웃한 쌍이 없으므로 빈 배열을 반환한다.
 입력 measurements의 순서는 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 정수 배열 measurements, 포함할 첫 위치 startIndex, 포함할 마지막 위치 endIndex
 - 출력: 선택한 값을 정렬한 뒤 이웃한 값의 차이를 담은 배열
 */
//endregion

//region 입출력 예시
/*
 measurements = [8, 3, 12, 3, 7], startIndex = 1, endIndex = 4
 선택 구간 = [3, 12, 3, 7], 정렬 = [3, 3, 7, 12]
 결과 = [0, 4, 5]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - Java에서 endIndex까지 포함해 복사하려면 복사의 끝 위치를 얼마로 정해야 하는가?
 - 입력 배열을 보존하면서 선택 구간만 정렬하려면 무엇을 따로 만들어야 하는가?
 - 정렬된 값이 m개라면 이웃한 두 값의 차이는 몇 개인가?
 - 결과의 index번째 값은 정렬된 배열의 어느 두 값을 빼야 하는가?
 */
//endregion

public final class SortingProblem04 {

    private SortingProblem04() {
        solve(new int[]{8, 3, 12, 3, 7}, 1, 4);
        // 예상 출력: [0, 4, 5]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     지정 구간을 새 배열로 복사하고, 복사한 값을 정렬한 뒤, 이웃한 값의 차이를 순서대로 담아 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 1 <= measurements.length <= 10_000
     - -1_000 <= measurements[i] <= 1_000
     - 0 <= startIndex <= endIndex < measurements.length
     - startIndex와 endIndex는 0부터 세는 배열 위치이며 양끝을 모두 포함한다.
     - 입력 배열은 바꾸지 않고 항상 새 배열을 반환한다.
     */
    //endregion
    public static int[] solve(int[] measurements, int startIndex, int endIndex) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - Arrays.copyOfRange의 마지막 위치는 포함되지 않으므로 endIndex 다음 위치까지 복사해야 한다.
     */
    //endregion
}

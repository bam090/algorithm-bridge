package bridge.sorting.problem;

//region 문제: 두 기록에서 전체 순번의 값 찾기
/*
 first와 second에는 각각 작은 값부터 정렬된 정수 기록이 들어 있다.
 두 기록을 한 줄로 합쳐 정렬했다고 생각했을 때, 문제에서 1부터 세는 rank번째 값을 반환한다.
 합친 전체 배열은 만들지 않고 두 입력 배열도 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 오름차순 배열 first와 second, 1부터 세는 순번 rank
 - 출력: 두 배열 전체에서 rank번째로 작은 값
 */
//endregion

//region 입출력 예시
/*
 first = [1, 4, 8], second = [2, 4, 10], rank = 4
 결과 = 4
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 두 배열의 아직 확인하지 않은 값 중 가장 작은 값은 어디에 있는가?
 - 한 값을 선택한 뒤 어느 배열의 위치만 한 칸 옮겨야 하는가?
 - 한쪽 배열을 모두 확인했으면 다음 값은 어느 배열에서 골라야 하는가?
 - 합친 배열을 만들지 않고 몇 번째 값을 선택했는지 어떻게 기억할 수 있는가?
 */
//endregion

public final class SortingProblem02 {

    private SortingProblem02() {
        solve(new int[]{1, 4, 8}, new int[]{2, 4, 10}, 4);
        // 예상 출력: 4
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     두 배열의 현재 값을 비교해 작은 쪽을 한 칸씩 이동하고, 한쪽이 끝나면 다른 쪽에서 계속 선택해 rank번째 값을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 0 <= first.length <= 10_000
     - 0 <= second.length <= 10_000
     - 1 <= first.length + second.length <= 20_000
     - -1_000 <= first[i], second[i] <= 1_000
     - first와 second는 각각 오름차순으로 정렬되어 있다.
     - 1 <= rank <= first.length + second.length
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion
    public static int solve(int[] first, int[] second, int rank) {
        int answer = 0;
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 결과 배열 대신 지금까지 선택한 값의 개수만 기억해도 된다.
     - 한쪽 배열이 끝난 뒤에는 남아 있는 배열의 현재 값을 선택한다.
     */
    //endregion
}

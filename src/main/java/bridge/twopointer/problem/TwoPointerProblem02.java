package bridge.twopointer.problem;

//region 문제: 목표에 가장 가까운 두 값의 합 찾기
/*
 values에는 작은 값부터 정렬된 정수가 들어 있다.
 서로 다른 두 위치의 값을 더해 target과 가장 가까운 합을 반환한다.
 거리가 같은 합이 여러 개면 더 작은 합을 반환하며 입력 배열은 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 오름차순 정수 배열 values, 목표 합 target
 - 출력: 서로 다른 두 위치로 만들 수 있는 target에 가장 가까운 합
 */
//endregion

//region 입출력 예시
/*
 values = [-6, -1, 4, 9], target = 6
 결과 = 8
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 정렬된 배열의 가장 작은 값과 가장 큰 값은 어디에 있는가?
 - 현재 합이 target보다 작으면 어느 쪽을 옮겨야 합을 키울 수 있는가?
 - 현재 합이 target보다 크면 어느 쪽을 옮겨야 합을 줄일 수 있는가?
 - 같은 거리에 있는 두 합 가운데 어떤 합을 남겨야 하는가?
 */
//endregion

public final class TwoPointerProblem02 {

    private TwoPointerProblem02() {
        solve(new int[]{-6, -1, 4, 9}, 6);
        // 예상 출력: 8
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     정렬 배열의 양끝 합을 목표와 비교해 더 가까운 합을 기억하고, 합이 작으면 왼쪽을 오른쪽으로 옮기고 크면 오른쪽을 왼쪽으로 옮겨 가장 가까운 합을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - values는 null이 아니다.
     - 2 <= values.length <= 100_000
     - -1_000_000_000 <= values[i] <= 1_000_000_000
     - values는 오름차순으로 정렬되어 있다.
     - -2_000_000_000 <= target <= 2_000_000_000
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion
    public static long solve(int[] values, long target) {
        long answer = 0;
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 현재 합과 target의 거리는 long으로 계산한다.
     - target과 정확히 같은 합을 찾으면 더 가까운 합은 없으므로 바로 반환할 수 있다.
     */
    //endregion
}

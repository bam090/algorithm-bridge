package bridge.twopointer.problem;

//region 문제: 한계 이하인 위치 쌍 세기
/*
 values의 서로 다른 두 위치를 한 쌍으로 고른다.
 두 값의 합이 limit 이하인 모든 위치 쌍의 개수를 반환한다.
 같은 값이 여러 위치에 있으면 위치가 다른 쌍을 각각 세며 입력 배열은 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 0 이상의 정수 배열 values, 허용되는 합의 한계 limit
 - 출력: 합이 limit 이하인 서로 다른 위치 쌍의 개수
 */
//endregion

//region 입출력 예시
/*
 values = [7, 1, 4, 2], limit = 6
 결과 = 3
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 모든 위치 쌍을 하나씩 확인하면 최대 입력에서 비교 횟수는 얼마나 되는가?
 - 값의 크기 순서를 이용하면 한 번의 비교로 여러 쌍을 판단할 수 있는가?
 - 현재 확인한 값을 바꿀 때 더 확인할 쌍과 버려도 되는 쌍을 어떻게 구분할 수 있는가?
 - 최대 입력에서 위치 쌍의 개수는 int 범위를 넘는가?
 */
//endregion

public final class TwoPointerProblem04 {

    private TwoPointerProblem04() {
        solve(new int[]{7, 1, 4, 2}, 6);
        // 예상 출력: 3
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     원본을 복사해 정렬한 뒤 양끝 합이 한계 이하면 가장 작은 값과 짝이 되는 남은 위치 수를 더하고, 초과하면 가장 큰 값의 위치를 줄여 모든 조건 만족 쌍을 센다.
     */
    //endregion

    //region 제약 조건
    /*
     - values는 null이 아니다.
     - 0 <= values.length <= 100_000
     - 0 <= values[i] <= 1_000_000_000
     - 0 <= limit <= 2_000_000_000
     - 서로 다른 두 위치 i, j에서 i < j인 쌍만 한 번 센다.
     - 한 위치는 다른 여러 위치와 만든 서로 다른 쌍에 포함될 수 있다.
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion
    public static long solve(int[] values, long limit) {
        long answer = 0;
        // 여기에 직접 구현한다.
        return answer;
    }
}

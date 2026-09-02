package bridge.twopointer.problem;

//region 문제: 기준 합을 채우는 가장 짧은 구간 찾기
/*
 amounts에는 0 이상의 양이 순서대로 들어 있다.
 합이 target 이상인 연속 구간 가운데 길이가 가장 짧은 구간의 시작과 끝 위치를 반환한다.
 위치는 문제에서 1부터 세며, 길이가 같으면 시작 위치가 앞선 구간을 선택한다.
 조건을 만족하는 구간이 없으면 빈 배열을 반환하고 입력 배열은 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 0 이상의 정수 배열 amounts, 기준 합 target
 - 출력: 가장 짧은 연속 구간의 1부터 센 시작과 끝 위치를 담은 새 int 배열
 */
//endregion

//region 입출력 예시
/*
 amounts = [2, 1, 5, 2, 3, 2], target = 7
 결과 = [3, 4]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 오른쪽에 새 값을 포함하면 현재 구간의 합은 어떻게 달라지는가?
 - 합이 target 이상일 때 어느 쪽을 옮겨야 더 짧은 구간을 확인할 수 있는가?
 - 왼쪽 값을 빼도 합이 target 이상이면 같은 오른쪽 끝에서 무엇을 더 확인해야 하는가?
 - 값이 모두 0 이상이라는 조건이 위치를 되돌리지 않아도 되는 이유는 무엇인가?
 */
//endregion

public final class TwoPointerProblem03 {

    private TwoPointerProblem03() {
        solve(new int[]{2, 1, 5, 2, 3, 2}, 7);
        // 예상 출력: [3, 4]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     오른쪽 위치를 옮기며 값을 합에 더하고, 합이 기준 이상인 동안 왼쪽 값을 빼며 구간을 줄여 가장 짧고 앞선 구간의 위치를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - amounts는 null이 아니다.
     - 0 <= amounts.length <= 100_000
     - 0 <= amounts[i] <= 1_000_000_000
     - 1 <= target <= 100_000_000_000_000
     - 입력 배열은 바꾸지 않는다.
     - 결과는 항상 새 배열로 반환한다.
     */
    //endregion
    public static int[] solve(int[] amounts, long target) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }
}

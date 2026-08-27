package bridge.backtracking.problem;

//region 문제: 곱으로 잠금 번호 만들기
/*
 서로 다른 양의 정수가 적힌 factorCards가 있다.
 카드 한 장 이상을 골라 카드 값의 곱이 target이 되는 조합의 수를 반환하라.
 한 카드는 한 번만 고를 수 있고, 고른 순서만 다른 경우는 같은 조합으로 센다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 카드 값 factorCards, 만들 목표 값 target
 - 출력: 곱이 target인 서로 다른 카드 조합의 수
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 같은 카드 조합의 순서만 바뀐 결과를 다시 세지 않으려면 다음 확인을 어디서 시작해야 하는가?
 - 현재 곱이 target과 같아지면 무엇을 반환해야 하는가?
 - 모든 카드 값이 2 이상일 때 현재 곱이 target을 넘으면 뒤를 더 볼 필요가 있는가?
 - 다음 카드를 곱하기 전에 int 범위를 넘지 않으면서 target 초과를 어떻게 확인할 수 있는가?
 */
//endregion

public final class BacktrackingProblem02 {

    private BacktrackingProblem02() {
        solve(new int[]{2, 3, 4, 6, 12}, 12);
        // 예상 출력: 3
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     현재 카드 다음 위치부터 후보를 골라 곱하고, target과 같으면 세며, target을 넘는 가지는 중단해 조합 수를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - factorCards는 null이 아니다.
     - 0 <= factorCards.length <= 15
     - 2 <= factorCards[i] <= 1,000
     - factorCards의 값은 서로 다르다.
     - 2 <= target <= 1,000,000
     - factorCards를 바꾸지 않는다.
     - 최대 2^15개의 선택 조합을 확인하므로 제한 안에서 실행할 수 있다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[] factorCards, int target) {
        int answer = 0;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 재귀 메서드에 다음에 확인할 시작 위치와 현재 곱을 전달한다.
     - index를 고른 다음 재귀에서는 index + 1부터 확인한다.
     - currentProduct > target / factorCards[index]이면 그 카드를 곱한 값은 target을 넘는다.
     */
    //endregion
}

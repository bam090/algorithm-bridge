package bridge.array.onedimensional.problem;

//region 문제: 기준을 통과한 기록만 모으기
/*
 정수 기록에서 minimum 이상인 값만 골라 새 배열로 반환하라.
 값의 원래 순서와 중복을 그대로 유지하고, 원본 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 새 배열을 만들기 전에 결과 길이를 어떻게 알 수 있는가?
 - 첫 번째 순회와 두 번째 순회에서는 각각 무엇을 해야 하는가?
 - 결과에 값을 넣을 위치는 입력 인덱스와 항상 같은가?
 - 조건을 만족하는 값이 하나도 없으면 어떤 배열을 반환해야 하는가?
 */
//endregion

public final class ArrayProblem04 {

    private ArrayProblem04() {
        solve(new int[]{12, 7, 15, 9, 15}, 10);
        // 예상 출력: new int[]{12, 15, 15} (15의 중복을 유지한다)
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     조건에 맞는 값의 개수를 먼저 센 뒤 새 배열을 만들고, 해당 값들을 원래 순서대로 담아 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - values는 null이 아니며 길이는 0 이상 10,000 이하이다.
     - 각 값과 minimum은 -10,000 이상 10,000 이하이다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] values, int minimum) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 첫 번째 순회에서는 minimum 이상인 값의 개수만 센다.
     - 센 개수와 같은 길이의 결과 배열을 만든다.
     - 두 번째 순회에서는 결과 배열에 값을 넣을 인덱스를 0부터 따로 관리한다.
     - 조건을 통과한 값을 결과 배열의 다음 빈칸에 넣는다.
     */
    //endregion
}

package bridge.array.onedimensional.problem;

/*
 * 문제 | 허용 범위 안의 기록 수
 * 정수 기록에서 minimum 이상이고 maximum 이하인 값의 개수를 반환하라.
 * 경계값인 minimum과 maximum도 허용 범위에 포함한다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 범위 안에 있는 값의 개수를 세려면 무엇을 계속 기억해야 하는가?
 * - 두 경계 조건은 둘 다 만족해야 하는가, 하나만 만족해도 되는가?
 * - 빈 배열이면 반복문은 몇 번 실행되며 무엇을 반환해야 하는가?
 */
public final class ArrayProblem02 {

    private ArrayProblem02() {
        solve(new int[]{-2, 0, 5, 7, 10}, 0, 7);
        // 예상 출력: 3 (범위 안 값은 0, 5, 7)
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 값을 앞에서부터 하나씩 확인하고, 범위 안에 있으면 개수를 1씩 늘려 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - values는 null이 아니며 길이는 0 이상 1,000 이하이다.
     * - 각 값은 -10,000 이상 10,000 이하이다.
     * - minimum은 maximum보다 작거나 같다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[] values, int minimum, int maximum) {
        int answer = 0;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 개수를 0으로 시작하고 각 값을 한 번씩 읽는다.
     * - value가 minimum 이상이면서 maximum 이하인지 확인한다.
     * - 조건을 만족할 때만 개수를 1 늘린다.
     */
    //endregion
}

package bridge.array.onedimensional.problem;

/*
 * 문제 | 가까운 기록 쌍 세기
 * 정수 기록 values에서 서로 다른 두 위치를 한 쌍으로 고른다.
 * 두 값의 차이의 절댓값이 maxDifference 이하인 위치 쌍의 개수를 반환하라.
 * 같은 값이 여러 위치에 있으면 각 위치 쌍을 따로 센다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 같은 두 위치를 순서만 바꾸어 두 번 세지 않으려면 두 번째 인덱스는 어디서 시작해야 하는가?
 * - 두 값 중 어느 값이 더 큰지 모르는데 차이의 크기는 어떻게 구할 수 있는가?
 * - 두 값이 같아도 서로 다른 위치라면 한 쌍이 될 수 있는가?
 * - maxDifference와 정확히 같은 차이도 포함해야 하는가?
 */
public final class ArrayProblem10 {

    private ArrayProblem10() {
        solve(new int[]{-2, 0, 1, 4}, 2);
        // 예상 출력: 2 (인덱스 쌍 (0, 1), (1, 2))
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 첫 위치보다 뒤에 있는 두 번째 위치만 골라 모든 쌍을 확인하고, 두 값의 차이가 기준 이하면 개수를 늘려 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - values는 null이 아니며 길이는 0 이상 300 이하이다.
     * - 각 값은 -1,000 이상 1,000 이하이다.
     * - maxDifference는 0 이상 2,000 이하이다.
     * - 결과는 위치 쌍의 개수이며, 같은 값으로 만든 쌍도 위치가 다르면 따로 센다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[] values, int maxDifference) {
        int answer = 0;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 첫 번째 인덱스를 i로 골랐다면 두 번째 인덱스 j는 i + 1부터 확인한다.
     * - Math.abs(values[i] - values[j])로 차이의 크기를 구할 수 있다.
     */
    //endregion
}

package bridge.array.twodimensional.problem;

/*
 * 문제 | 날짜별 기록 합계
 * 날짜마다 모은 측정값이 2차원 배열 records에 들어 있다.
 * records의 각 행은 하루의 측정값이며, 날짜마다 측정 횟수가 다를 수 있다.
 * 각 행의 값을 모두 더한 결과를 날짜 순서대로 반환하라. 빈 행의 합은 0이다.
 * 원본 records는 바꾸면 안 된다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 결과 배열의 길이는 records의 무엇과 같아야 하는가?
 * - records.length와 records[row].length는 각각 무엇을 뜻하는가?
 * - 모든 행의 길이가 같다고 가정해도 되는가?
 * - 바깥 반복문과 안쪽 반복문은 각각 무엇을 확인해야 하는가?
 * - 한 행의 합을 저장하는 변수는 언제 0으로 다시 시작해야 하는가?
 */
public final class ArrayProblem07 {

    private ArrayProblem07() {
        solve(new int[][]{{3, 1, 2}, {10}, {-2, 2, 5}, {}});
        // 예상 출력: new int[]{6, 10, 5, 0}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 행의 개수만큼 결과 배열을 만든 뒤, 각 행의 값을 모두 더해 같은 행 번호의 결과 칸에 저장하고 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - records는 null이 아니며 행의 개수는 0 이상 1,000 이하이다.
     * - 각 행은 null이 아니며 길이는 0 이상 1,000 이하이고, 서로 달라도 된다.
     * - 모든 행의 원소 수를 더하면 100,000 이하이다.
     * - 각 측정값은 -1,000 이상 1,000 이하이다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[][] records) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 결과 배열의 길이는 records.length와 같다.
     * - 바깥 반복문에서 행을 하나 고를 때마다 그 행의 합을 0으로 시작한다.
     * - 안쪽 반복문은 column < records[row].length인 동안 현재 행의 값을 더한다.
     * - 한 행을 모두 더한 뒤 answer[row]에 저장한다.
     */
    //endregion
}

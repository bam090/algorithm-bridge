package bridge.array.twodimensional.problem;

/*
 * 문제 | 같은 번호의 행과 열 합계
 * 정사각형 정수 표 table이 주어진다.
 * 각 번호마다 같은 번호의 행과 열에 있는 값을 모두 더해 결과 배열로 반환하라.
 * 행과 열이 만나는 table[index][index] 값은 한 번만 더한다.
 * 원본 table은 바꾸면 안 된다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 결과 배열의 길이는 table의 무엇과 같아야 하는가?
 * - index번째 행의 offset번째 값과 index번째 열의 offset번째 값은 각각 어떻게 읽는가?
 * - 행과 열이 만나는 값은 왜 두 번 더해질 수 있는가?
 * - 행이 없는 표와 한 칸짜리 표의 결과는 각각 무엇인가?
 */
public final class ArrayProblem12 {

    private ArrayProblem12() {
        solve(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        // 예상 출력: new int[]{17, 25, 33}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 표의 크기만큼 결과 배열을 만든 뒤, 같은 번호의 행과 열을 함께 더하되 교차점은 한 번만 포함해 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - table은 null이 아니며 행의 개수는 0 이상 500 이하이다.
     * - table의 모든 행은 null이 아니며 길이는 table.length와 같다.
     * - 각 값은 -1,000 이상 1,000 이하이다.
     * - 원본 table은 바꾸면 안 된다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[][] table) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - index번째 결과를 구할 때 table[index][offset]과 table[offset][index]를 함께 확인하고, offset == index인 값은 한 번만 더한다.
     */
    //endregion
}

package bridge.dynamicprogramming.problem;

/*
 * 문제 | 날짜별 누적 연습 점수표
 *
 * 0일부터 lastDay일까지의 누적 연습 점수를 모두 담은 배열을 반환하라.
 * 0일의 점수는 1이다. day가 1 이상이면 그날의 누적 점수는
 * (전날 누적 점수 + day × day)를 10,007로 나눈 나머지다.
 *
 * 입력과 출력
 * - 입력: 마지막 날짜 lastDay
 * - 출력: 0일부터 lastDay일까지의 누적 점수를 차례대로 담은 배열
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - answer[day]는 어떤 값을 뜻해야 하는가?
 * - 계산하지 않아도 바로 적을 수 있는 첫 번째 값은 무엇인가?
 * - day번째 값을 만들기 전에 어느 위치의 값이 준비되어 있어야 하는가?
 * - 0일부터 lastDay일까지 담으려면 배열 길이는 얼마여야 하는가?
 */
public final class DynamicProgrammingProblem01 {

    private DynamicProgrammingProblem01() {
        solve(3);
        // 예상 출력: new int[]{1, 2, 6, 15}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * answer[day]를 day일까지의 누적 점수로 정하고 0일의 값을 저장한 뒤, 앞 날짜부터 주어진 계산식으로 다음 값을 채워 전체 배열을 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - 0 <= lastDay <= 100,000
     * - 0일도 결과에 포함한다.
     * - 각 날짜의 결과는 10,007로 나눈 나머지다.
     * - day × day는 int 범위를 넘을 수 있으므로 long으로 계산한 뒤 나머지를 구한다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int lastDay) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 0일을 포함하므로 길이가 lastDay + 1인 배열을 만든다.
     * - answer[0]에 직접 알 수 있는 값 1을 먼저 저장한다.
     * - day를 1부터 늘리며 answer[day - 1]로 answer[day]를 만든다.
     * - day를 long으로 바꾼 뒤 곱하고, 값을 저장할 때마다 10,007로 나눈다.
     */
    //endregion
}

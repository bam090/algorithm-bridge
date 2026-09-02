package bridge.dynamicprogramming.solution;

/** 날짜별 누적 연습 점수표 문제 정답과 풀이 설명이다. */
public final class DynamicProgrammingSolution01 {

    private static final int MODULO = 10_007;

    private DynamicProgrammingSolution01() {
    }

    public static int[] solve(int lastDay) {
        // 동적 계획법을 선택한 이유:
        // 오늘 값은 바로 전날 값으로 만들므로, 날짜별 결과를 저장해 앞에서부터 계산하면 된다.

        // [1] 0일부터 lastDay일까지 담을 배열을 만든다.
        int[] scoreByDay = new int[lastDay + 1];

        // [2] 가장 작은 상태인 0일의 값 1을 저장한다.
        scoreByDay[0] = 1;

        for (int day = 1; day <= lastDay; day++) {
            // [3] day를 1부터 늘리며 day의 제곱을 long으로 계산한다.
            long addedScore = (long) day * day;

            // [4] 전날 값과 day의 제곱을 더하고 10,007로 나눈 값을 현재 칸에 저장한다.
            scoreByDay[day] = (int) ((scoreByDay[day - 1] + addedScore) % MODULO);
        }

        // [5] 완성한 날짜별 배열을 반환한다.
        return scoreByDay;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - day번째 값은 바로 앞 day - 1번째 값으로 만든다.
     * - 0일부터 마지막 날까지의 모든 값이 결과에 필요하다.
     * - 곱셈 결과가 int 범위를 넘을 수 있고 매 단계에서 나머지를 구해야 한다.
     *
     * 이 개념을 선택한 이유
     * - answer[day]를 day일까지의 누적 점수로 정하면, 이미 계산한 앞 칸 하나로 다음 칸을 만들 수 있다.
     * - 작은 날짜부터 차례로 계산하면 필요한 앞 칸이 항상 준비되어 있다.
     *
     * 풀이 순서
     * 1. 0일부터 lastDay일까지 담을 배열을 만든다.
     * 2. 가장 작은 상태인 0일의 값 1을 저장한다.
     * 3. day를 1부터 늘리며 day의 제곱을 long으로 계산한다.
     * 4. 전날 값과 day의 제곱을 더하고 10,007로 나눈 값을 현재 칸에 저장한다.
     * 5. 완성한 날짜별 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - 0일은 1이다.
     * - 1일은 (1 + 1²) % 10,007 = 2다.
     * - 2일은 (2 + 2²) % 10,007 = 6이다.
     * - 3일은 (6 + 3²) % 10,007 = 15다.
     *
     * 복잡도
     * - 날짜를 한 번씩 계산하므로 시간 O(lastDay)다.
     * - 반환할 배열에 O(lastDay) 공간이 필요하다.
     *
     * 자주 하는 실수
     * - 0일을 빼고 길이가 lastDay인 배열을 만든다.
     * - answer[0]을 저장하기 전에 반복문을 시작한다.
     * - day × day를 int끼리 계산해 lastDay가 클 때 값이 넘친다.
     * - 나머지를 마지막에 한 번만 구한다.
     */
}

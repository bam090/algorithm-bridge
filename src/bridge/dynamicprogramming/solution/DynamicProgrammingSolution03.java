package bridge.dynamicprogramming.solution;

/** 장애물을 피해 가는 배송 경로 수 문제 정답과 풀이 설명이다. */
public final class DynamicProgrammingSolution03 {

    private static final int MODULO = 1_000_000_007;

    private DynamicProgrammingSolution03() {
    }

    public static int solve(int[][] warehouse) {
        if (warehouse.length == 0 || warehouse[0].length == 0) {
            return 0;
        }

        int rowCount = warehouse.length;
        int columnCount = warehouse[0].length;
        if (warehouse[0][0] == 1 || warehouse[rowCount - 1][columnCount - 1] == 1) {
            return 0;
        }

        long[][] ways = new long[rowCount][columnCount];
        ways[0][0] = 1L;

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (warehouse[row][column] == 1 || row == 0 && column == 0) {
                    continue;
                }

                long fromTop = row > 0 ? ways[row - 1][column] : 0L;
                long fromLeft = column > 0 ? ways[row][column - 1] : 0L;
                ways[row][column] = (fromTop + fromLeft) % MODULO;
            }
        }
        return (int) ways[rowCount - 1][columnCount - 1];
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 현재 칸으로 오는 마지막 이동은 위에서 내려오거나 왼쪽에서 오는 두 경우뿐이다.
     * - 장애물 칸으로는 들어갈 수 없고, 빈 배열과 막힌 출발·도착 칸을 따로 처리해야 한다.
     * - 현재 칸은 위쪽과 왼쪽 칸의 계산이 끝난 뒤에 계산해야 한다.
     *
     * 이 개념을 선택한 이유
     * - ways[row][column]을 출발 칸에서 현재 칸까지 오는 경로 수로 정하면 두 이전 칸의 값을 재사용할 수 있다.
     * - 위에서 아래, 왼쪽에서 오른쪽으로 채우면 필요한 두 이전 값이 항상 먼저 준비된다.
     *
     * 풀이 순서
     * 1. 행이나 열이 없으면 0을 반환한다.
     * 2. 출발 칸이나 도착 칸이 장애물이면 0을 반환한다.
     * 3. 출발 칸까지 오는 한 가지 경로를 ways[0][0]에 저장한다.
     * 4. 각 열린 칸에서 위쪽 경로 수와 왼쪽 경로 수를 더한다.
     * 5. 값을 저장할 때 1,000,000,007로 나누고 도착 칸의 값을 반환한다.
     *
     * 예시 데이터 흐름
     * - 첫 행은 오른쪽으로만 갈 수 있어 장애물 전까지 각 칸의 값이 1이다.
     * - 가운데 장애물의 값은 0으로 남는다.
     * - 장애물 오른쪽 아래의 열린 칸은 위와 왼쪽에서 온 경로 수를 더한다.
     * - 오른쪽 아래 칸에는 모두 4개의 경로가 모인다.
     *
     * 복잡도
     * - 행 수를 r, 열 수를 c라 할 때 모든 칸을 한 번 확인하므로 시간 O(r × c)다.
     * - 경로 수 표에 공간 O(r × c)가 필요하다.
     *
     * 자주 하는 실수
     * - 빈 배열에서 warehouse[0]을 먼저 읽는다.
     * - 장애물 칸에도 위와 왼쪽 값을 더한다.
     * - 아래쪽이나 오른쪽부터 계산해 아직 준비되지 않은 값을 읽는다.
     * - 출발 칸이 장애물인데 경로 수를 1로 시작한다.
     * - 큰 경로 수를 int로 먼저 더해 값이 넘친다.
     */
}

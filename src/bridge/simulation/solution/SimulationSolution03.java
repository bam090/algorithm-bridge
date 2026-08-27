package bridge.simulation.solution;

/*
 * 정답 풀이: 겹별 테두리 합
 *
 * 문제에서 발견해야 했던 단서
 * - 바깥 테두리를 끝내면 위·아래·왼쪽·오른쪽 경계가 모두 한 칸 안으로 이동한다.
 * - 한 줄이나 한 열만 남을 때 같은 칸을 두 번 더하면 안 된다.
 * - 큰 값이 많은 격자의 합은 int 범위를 넘는다.
 *
 * 네 경계를 선택한 이유
 * top, bottom, left, right를 기억하면 현재 겹에 포함된 네 방향의 시작과 끝을 바로 알 수 있다.
 * 한 겹을 마친 뒤 네 값을 줄이면 같은 코드로 다음 안쪽 겹도 처리할 수 있다.
 *
 * 풀이 순서
 * 1. 격자의 짧은 쪽 길이로 전체 겹 수를 계산한다.
 * 2. 현재 윗줄을 왼쪽부터 오른쪽까지 더한다.
 * 3. 오른쪽 줄은 윗줄 다음 행부터 아래까지 더한다.
 * 4. 위와 아래가 다를 때만 아랫줄을 오른쪽에서 왼쪽으로 더한다.
 * 5. 왼쪽과 오른쪽이 다를 때만 왼쪽 줄의 남은 칸을 아래에서 위로 더한다.
 * 6. 합을 저장하고 네 경계를 한 칸 안으로 이동한다.
 *
 * 예시 데이터 흐름
 * 3×4 격자의 바깥 칸 1, 2, 3, 4, 8, 12, 11, 10, 9, 5의 합은 65이다.
 * 경계를 안으로 줄이면 6과 7만 남아 다음 겹의 합은 13이다.
 *
 * 시간 복잡도: O(rows × columns), 모든 칸을 정확히 한 번 더한다.
 * 공간 복잡도: O(min(rows, columns)), 겹 수만큼의 결과 배열을 사용한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 네 방향 모두 모서리를 포함하면 모서리가 두 번 더해진다.
 * - 한 줄이나 한 열이 남은 경우를 구분하지 않으면 같은 칸을 다시 더한다.
 * - 합을 int로 계산하면 최대 입력에서 넘친다.
 */
public final class SimulationSolution03 {

    private SimulationSolution03() {
    }

    public static long[] solve(int[][] grid) {
        if (grid.length == 0) {
            return new long[0];
        }

        int rowCount = grid.length;
        int columnCount = grid[0].length;
        int layerCount = (Math.min(rowCount, columnCount) + 1) / 2;
        long[] answer = new long[layerCount];

        int top = 0;
        int bottom = rowCount - 1;
        int left = 0;
        int right = columnCount - 1;
        int layer = 0;

        while (top <= bottom && left <= right) {
            long sum = 0;

            for (int column = left; column <= right; column++) {
                sum += grid[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                sum += grid[row][right];
            }
            if (top < bottom) {
                for (int column = right - 1; column >= left; column--) {
                    sum += grid[bottom][column];
                }
            }
            if (left < right) {
                for (int row = bottom - 1; row > top; row--) {
                    sum += grid[row][left];
                }
            }

            answer[layer++] = sum;
            top++;
            bottom--;
            left++;
            right--;
        }

        return answer;
    }
}

package bridge.simulation.solution;

/*
 * 정답 풀이: 전광판 줄 밀기
 *
 * 문제에서 발견해야 했던 단서
 * - 행마다 이동할 칸 수가 다르다.
 * - 오른쪽 끝을 지나면 같은 행의 왼쪽으로 이어진다.
 * - 변환을 여러 번 반복하면서도 원본 배열은 보존해야 한다.
 *
 * 새 2차원 배열을 선택한 이유
 * 원본 배열에 바로 쓰면 아직 이동하지 않은 값이 덮일 수 있다.
 * 한 번의 변환마다 새 배열에만 쓰면 모든 값의 원래 위치를 안전하게 읽을 수 있다.
 *
 * 풀이 순서
 * 1. rounds가 0이어도 원본을 보존하도록 board를 깊은 복사한다.
 * 2. 반복 한 번마다 같은 행과 열 크기의 새 배열을 만든다.
 * 3. 원본의 각 열에 row + 1을 더하고 열 수로 나눈 나머지를 새 열로 삼는다.
 * 4. 현재 값을 새 배열의 row, newColumn에 저장한다.
 * 5. 완성한 배열을 다음 반복의 현재 배열로 바꾼다.
 * 6. 모든 반복이 끝난 새 배열을 반환한다.
 *
 * 예시 데이터 흐름
 * 첫 행은 한 번에 한 칸 이동하므로 두 번 뒤에는 [3, 4, 1, 2]가 된다.
 * 둘째 행은 한 번에 두 칸 이동하므로 두 번 뒤에는 네 칸을 돌아 [5, 6, 7, 8]이 된다.
 *
 * 시간 복잡도: O(rounds × rows × columns), 매 반복마다 모든 칸을 한 번 옮긴다.
 * 공간 복잡도: O(rows × columns), 현재 배열과 한 번의 결과 배열을 사용한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - row 대신 column만 이동량으로 사용하면 모든 행이 똑같이 움직인다.
 * - 직사각형인데 행 수를 열 수처럼 사용하면 위치가 틀어진다.
 * - rounds가 0일 때 board 자체를 반환하면 원본과 다른 배열이라는 계약을 어긴다.
 */
public final class SimulationSolution02 {

    private SimulationSolution02() {
    }

    public static int[][] solve(int[][] board, int rounds) {
        // 각 값은 행마다 다른 새 위치로 여러 번 이동하며, 원본 배열은 보존해야 한다.
        // 반복마다 새 배열에 옮기면 아직 읽지 않은 값을 덮지 않고 다음 상태도 이어 갈 수 있다.
        int[][] current = copyOf(board);
        int rowCount = board.length;
        int columnCount = rowCount == 0 ? 0 : board[0].length;

        for (int round = 0; round < rounds; round++) {
            // [2] 반복 한 번마다 같은 행과 열 크기의 새 배열을 만든다.
            int[][] next = new int[rowCount][columnCount];
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    // [3] 원본의 각 열에 row + 1을 더하고 열 수로 나눈 나머지를 새 열로 삼는다.
                    int newColumn = (column + row + 1) % columnCount;
                    // [4] 현재 값을 새 배열의 row, newColumn에 저장한다.
                    next[row][newColumn] = current[row][column];
                }
            }
            // [5] 완성한 배열을 다음 반복의 현재 배열로 바꾼다.
            current = next;
        }

        // [6] 모든 반복이 끝난 새 배열을 반환한다.
        return current;
    }

    private static int[][] copyOf(int[][] source) {
        // [1] rounds가 0이어도 원본을 보존하도록 board를 깊은 복사한다.
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }
}

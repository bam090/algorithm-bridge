package bridge.graph.solution;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/** 창고 칸별 최소 이동표 문제 정답과 풀이 설명이다. */
public final class GraphSolution03 {

    private GraphSolution03() {
    }

    public static int[][] solve(int[][] grid, int startRow, int startColumn) {
        // 열린 칸의 상하좌우 이동 비용이 모두 1이므로 가까운 칸부터 보는 BFS가 최소 거리에 맞다.
        // 별도 거리표를 방문 표시로 함께 쓰면 원본을 지키면서 벽과 미도달 칸도 구분할 수 있다.

        // [1] 모든 거리 칸을 -1로 시작하고 입력의 벽 위치만 -2로 바꾼다.
        int rowCount = grid.length;
        int columnCount = grid[0].length;
        int[][] distance = new int[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            Arrays.fill(distance[row], -1);
            for (int column = 0; column < columnCount; column++) {
                if (grid[row][column] == 1) {
                    distance[row][column] = -2;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // [2] 시작 칸의 거리를 0으로 기록하고 큐에 넣는다.
        Deque<Integer> queue = new ArrayDeque<>();
        distance[startRow][startColumn] = 0;
        queue.addLast(startRow * columnCount + startColumn);

        while (!queue.isEmpty()) {
            int position = queue.removeFirst();
            int row = position / columnCount;
            int column = position % columnCount;

            // [3] 현재 칸에서 네 방향의 다음 행과 열을 만든다.
            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                boolean inside = 0 <= nextRow && nextRow < rowCount
                        && 0 <= nextColumn && nextColumn < columnCount;

                // [4] 범위 안이고 거리값이 -1인 칸에 현재 거리 + 1을 기록해 큐에 넣는다.
                if (inside && distance[nextRow][nextColumn] == -1) {
                    distance[nextRow][nextColumn] = distance[row][column] + 1;
                    queue.addLast(nextRow * columnCount + nextColumn);
                }
            }
        }

        // [5] 큐가 비면 벽·미도달·최소 거리가 모두 표시된 표를 반환한다.
        return distance;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 한 칸을 정점으로 보면 상하좌우 칸이 직접 이어진 이웃이다.
     * - 모든 이동 비용이 1이므로 가까운 칸부터 확인해야 최소 거리를 얻는다.
     * - 벽 -2와 아직 도달하지 못한 칸 -1을 결과에서 구분해야 한다.
     * - 입력 grid를 바꾸면 안 되므로 별도 거리표가 필요하다.
     *
     * 이 개념을 선택한 이유
     * - BFS는 시작 칸에서 같은 이동 횟수만큼 떨어진 칸을 묶어 확인한다.
     * - 거리표의 -1은 방문 여부도 함께 나타내므로 별도 boolean 배열이 필요 없다.
     *
     * 풀이 순서
     * 1. 모든 거리 칸을 -1로 시작하고 입력의 벽 위치만 -2로 바꾼다.
     * 2. 시작 칸의 거리를 0으로 기록하고 큐에 넣는다.
     * 3. 현재 칸에서 네 방향의 다음 행과 열을 만든다.
     * 4. 범위 안이고 거리값이 -1인 칸에 현재 거리 + 1을 기록해 큐에 넣는다.
     * 5. 큐가 비면 벽·미도달·최소 거리가 모두 표시된 표를 반환한다.
     *
     * 예시 데이터 흐름
     * - 시작 (0, 0)은 0이고 오른쪽 (0, 1)은 1이다.
     * - (0, 2)와 (1, 0)은 벽이어서 -2로 남는다.
     * - (0, 1)에서 (1, 1)로 내려가면 거리는 2다.
     * - 이후 같은 방식으로 열린 칸을 가까운 순서대로 채운다.
     *
     * 복잡도
     * - 전체 칸 수를 r * c라 할 때 시간 O(r * c)다.
     * - 거리표와 큐에 공간 O(r * c)가 필요하다.
     *
     * 자주 하는 실수
     * - 범위를 확인하기 전에 grid의 다음 칸을 읽는다.
     * - 벽과 미도달 칸을 모두 -1로 만들어 구분하지 못한다.
     * - 입력 grid에 거리를 덮어써 원본을 바꾼다.
     * - 거리표에 처음 기록한 칸을 다시 큐에 넣어 불필요하게 반복한다.
     */
}

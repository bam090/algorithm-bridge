package bridge.graph.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/** 양방향 통로의 최소 이동 횟수 문제 정답과 풀이 설명이다. */
public final class GraphSolution02 {

    private GraphSolution02() {
    }

    public static int[] solve(int nodeCount, int[][] undirectedEdges, int startNode) {
        // 모든 통로의 비용이 1이므로 가까운 지점부터 확인해야 첫 기록이 최소 거리가 된다.
        // Queue를 쓰는 BFS는 거리가 같은 지점을 차례로 처리해 이 이동 방식과 바로 맞는다.

        // [1] 각 양방향 간선을 두 정점의 인접 리스트에 넣는다.
        List<List<Integer>> graph = new ArrayList<>(nodeCount);
        for (int node = 0; node < nodeCount; node++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : undirectedEdges) {
            int first = edge[0] - 1;
            int second = edge[1] - 1;
            graph.get(first).add(second);
            graph.get(second).add(first);
        }

        // [2] 거리 배열을 -1로 채우고 출발 지점만 0으로 바꿔 큐에 넣는다.
        int[] distance = new int[nodeCount];
        Arrays.fill(distance, -1);

        Deque<Integer> queue = new ArrayDeque<>();
        int startIndex = startNode - 1;
        distance[startIndex] = 0;
        queue.addLast(startIndex);

        while (!queue.isEmpty()) {
            // [3] 큐 앞 지점을 꺼내 아직 거리가 없는 이웃을 찾는다.
            int current = queue.removeFirst();
            for (int next : graph.get(current)) {
                // [4] 이웃에 현재 거리 + 1을 기록한 즉시 큐 뒤에 넣는다.
                if (distance[next] == -1) {
                    distance[next] = distance[current] + 1;
                    queue.addLast(next);
                }
            }
        }

        // [5] 큐가 비면 지점 번호 순서의 거리 배열을 반환한다.
        return distance;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 모든 통로의 이동 비용은 똑같이 1이다.
     * - 통로는 양방향이므로 한 간선을 두 지점의 이웃 목록에 모두 넣어야 한다.
     * - 출발 지점은 거리 0이고 도달할 수 없는 지점은 -1이어야 한다.
     * - 방문 순서가 아니라 각 지점까지의 최소 이동 횟수가 결과다.
     *
     * 이 개념을 선택한 이유
     * - BFS는 출발점에서 0칸, 1칸, 2칸 떨어진 지점 순서로 확인한다.
     * - 따라서 한 지점의 거리를 처음 기록할 때가 그 지점까지의 최소 거리다.
     *
     * 풀이 순서
     * 1. 각 양방향 간선을 두 정점의 인접 리스트에 넣는다.
     * 2. 거리 배열을 -1로 채우고 출발 지점만 0으로 바꿔 큐에 넣는다.
     * 3. 큐 앞 지점을 꺼내 아직 거리가 없는 이웃을 찾는다.
     * 4. 이웃에 현재 거리 + 1을 기록한 즉시 큐 뒤에 넣는다.
     * 5. 큐가 비면 지점 번호 순서의 거리 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - 1번의 거리는 0이다.
     * - 1번과 바로 이어진 2번과 3번의 거리는 1이다.
     * - 2번 또는 3번을 거쳐 처음 만난 4번의 거리는 2다.
     * - 4번 다음 5번은 3이고, 연결되지 않은 6번은 -1로 남는다.
     *
     * 복잡도
     * - 정점 수를 n, 간선 수를 m이라 할 때 시간 O(n + m)이다.
     * - 인접 리스트, 거리 배열과 큐에 공간 O(n + m)이 필요하다.
     *
     * 자주 하는 실수
     * - 양방향 간선을 한쪽 목록에만 넣는다.
     * - 큐에서 꺼낼 때 방문 표시해 같은 지점이 여러 번 큐에 들어가게 한다.
     * - 출발 지점의 거리를 1로 시작한다.
     * - 도달 불가능한 -1을 0으로 바꿔 출발 지점과 구분하지 못한다.
     */
}

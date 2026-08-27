package bridge.graph.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/** 필수 점검소를 거친 목적지별 거리 문제 정답과 풀이 설명이다. */
public final class GraphSolution05 {

    private GraphSolution05() {
    }

    public static int[] solve(
            int nodeCount,
            int[][] directedRoutes,
            int startNode,
            int checkpointNode,
            int[] destinations
    ) {
        List<List<Integer>> graph = new ArrayList<>(nodeCount);
        for (int node = 0; node < nodeCount; node++) {
            graph.add(new ArrayList<>());
        }
        for (int[] route : directedRoutes) {
            graph.get(route[0] - 1).add(route[1] - 1);
        }

        int[] distanceFromStart = findDistances(graph, startNode - 1);
        int[] distanceFromCheckpoint = findDistances(graph, checkpointNode - 1);
        int startToCheckpoint = distanceFromStart[checkpointNode - 1];

        int[] result = new int[destinations.length];
        for (int index = 0; index < destinations.length; index++) {
            int checkpointToDestination = distanceFromCheckpoint[destinations[index] - 1];
            result[index] = startToCheckpoint == -1 || checkpointToDestination == -1
                    ? -1
                    : startToCheckpoint + checkpointToDestination;
        }
        return result;
    }

    private static int[] findDistances(List<List<Integer>> graph, int start) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, -1);

        Deque<Integer> queue = new ArrayDeque<>();
        distance[start] = 0;
        queue.addLast(start);

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();
            for (int next : graph.get(current)) {
                if (distance[next] == -1) {
                    distance[next] = distance[current] + 1;
                    queue.addLast(next);
                }
            }
        }
        return distance;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 모든 경로는 checkpointNode를 반드시 지나야 한다.
     * - 길은 단방향이므로 출발점에서 먼저 지나온 목적지로 점검소 뒤에 돌아갈 수 없을 수 있다.
     * - 목적지가 여러 개이므로 점검소 이후의 거리를 한 번에 구해 다시 사용해야 한다.
     * - 첫 탐색의 방문 배열을 둘째 탐색에서 그대로 사용하면 갈 수 있는 지점을 막는다.
     *
     * 이 개념을 선택한 이유
     * - 전체 이동은 출발점→점검소와 점검소→목적지 두 구간으로 정확히 나뉜다.
     * - 각 길의 비용이 1이므로 두 구간의 최소 거리는 각각 BFS로 구할 수 있다.
     * - 점검소에서 한 번 BFS하면 모든 목적지의 둘째 구간 거리를 바로 읽을 수 있다.
     *
     * 풀이 순서
     * 1. 단방향 인접 리스트를 만든다.
     * 2. 출발점에서 BFS해 점검소까지의 최소 거리를 구한다.
     * 3. 새 거리 배열과 큐로 점검소에서 다시 BFS한다.
     * 4. 목적지마다 두 구간 중 하나라도 -1이면 -1을 저장한다.
     * 5. 두 구간이 모두 있으면 거리를 더해 destinations 순서로 반환한다.
     *
     * 예시 데이터 흐름
     * - 1번에서 3번 점검소까지 최소 거리는 2다.
     * - 점검소에서 3, 5, 6, 7번까지 거리는 각각 0, 1, 2, 1이다.
     * - 점검소에서 2번으로 가는 길은 없어 2번 목적지 결과는 -1이다.
     * - 두 구간을 더하면 [2, 3, 4, -1, 3]이다.
     *
     * 복잡도
     * - 정점 수를 n, 간선 수를 m, 목적지 수를 d라 할 때 시간 O(n + m + d)다.
     * - 인접 리스트, 거리 배열 두 개, 큐와 결과에 공간 O(n + m + d)가 필요하다.
     *
     * 자주 하는 실수
     * - 첫 BFS의 방문 상태를 재사용해 점검소에서 탐색하지 못한다.
     * - 목적지마다 BFS를 다시 실행해 O(d * (n + m)) 시간이 든다.
     * - 단방향 간선을 양방향으로 넣는다.
     * - 출발점→목적지 거리만 구해 점검소를 거치지 않은 더 짧은 길을 답으로 쓴다.
     */
}

package bridge.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 그래프는 여러 지점과 지점 사이의 연결을 함께 나타내는 방법이다.
 * <p>
 * 지점은 정점, 두 정점을 잇는 연결은 간선이라고 부른다.
 * 길처럼 양쪽으로 갈 수 있는 간선도 있고, 일방통행처럼 한쪽으로만 갈 수 있는 간선도 있다.
 */
public final class GraphGuide {

    private GraphGuide() {
    }

    public static void main(String[] args) {
        // 1. 인접 리스트는 각 정점에서 바로 갈 수 있는 이웃만 모아 둔다.
        List<List<Integer>> directedGraph = newGraph(5);
        addDirectedEdge(directedGraph, 0, 1);
        addDirectedEdge(directedGraph, 0, 2);
        addDirectedEdge(directedGraph, 2, 3);
        addDirectedEdge(directedGraph, 3, 3);

        System.out.println("[1] 단방향 인접 리스트");
        for (int node = 0; node < directedGraph.size(); node++) {
            System.out.println(node + "에서 갈 수 있는 곳: " + directedGraph.get(node));
        }

        // 2. 깊이 우선 탐색은 갈 수 있는 한쪽 길을 먼저 따라간다.
        // 재귀 대신 스택을 쓰면 연결이 아주 길어도 호출 스택이 넘치지 않는다.
        boolean[] reachable = findReachableNodes(directedGraph, 0);
        System.out.println("\n[2] 0번에서 도달 여부: " + Arrays.toString(reachable));

        // 3. 너비 우선 탐색은 출발점에서 같은 거리인 정점부터 큐로 확인한다.
        List<List<Integer>> undirectedGraph = newGraph(6);
        addUndirectedEdge(undirectedGraph, 0, 1);
        addUndirectedEdge(undirectedGraph, 0, 2);
        addUndirectedEdge(undirectedGraph, 1, 3);
        addUndirectedEdge(undirectedGraph, 2, 4);

        int[] distances = findDistances(undirectedGraph, 0);
        System.out.println("\n[3] 0번에서 최소 간선 수: " + Arrays.toString(distances));

        // 4. 격자는 각 칸을 정점으로 보고 위, 아래, 왼쪽, 오른쪽 칸을 이웃으로 본다.
        int[][] room = {
                {0, 0, 1},
                {1, 0, 0}
        };
        System.out.println("\n[4] 격자에서 (0, 1)의 이웃 후보");
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int nextRow = 0 + direction[0];
            int nextColumn = 1 + direction[1];
            boolean inside = 0 <= nextRow && nextRow < room.length
                    && 0 <= nextColumn && nextColumn < room[0].length;
            if (inside) {
                System.out.println("후보 (" + nextRow + ", " + nextColumn + ")"
                        + " | 벽 여부 " + (room[nextRow][nextColumn] == 1));
            }
        }

        // 5. 탐색할 때 부모를 먼저 visitOrder에 기록하면, 그 순서를 거꾸로 읽어 자식부터 계산할 수 있다.
        List<List<Integer>> tree = newGraph(4);
        addUndirectedEdge(tree, 0, 2);
        addUndirectedEdge(tree, 0, 1);
        addUndirectedEdge(tree, 1, 3);

        int[] parent = new int[tree.size()];
        Arrays.fill(parent, -2);
        int[] visitOrder = new int[tree.size()];
        int orderSize = 0;

        Deque<Integer> treeStack = new ArrayDeque<>();
        parent[0] = -1;
        treeStack.push(0);
        while (!treeStack.isEmpty()) {
            int current = treeStack.pop();
            visitOrder[orderSize++] = current;
            for (int next : tree.get(current)) {
                if (parent[next] == -2) {
                    parent[next] = current;
                    treeStack.push(next);
                }
            }
        }

        int[] marked = {0, 0, 1, 1};
        int[] subtreeMarks = marked.clone();
        // 정점 번호를 거꾸로 읽는 것이 아니라, 실제 visitOrder를 거꾸로 읽는다.
        for (int index = orderSize - 1; index >= 1; index--) {
            int child = visitOrder[index];
            subtreeMarks[parent[child]] += subtreeMarks[child];
        }
        System.out.println("\n[5] 실제 방문 순서: " + Arrays.toString(visitOrder));
        System.out.println("방문 순서를 거꾸로 읽어 더한 값: " + Arrays.toString(subtreeMarks));
    }

    private static List<List<Integer>> newGraph(int nodeCount) {
        List<List<Integer>> graph = new ArrayList<>(nodeCount);
        for (int node = 0; node < nodeCount; node++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    private static void addDirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
    }

    private static void addUndirectedEdge(List<List<Integer>> graph, int first, int second) {
        graph.get(first).add(second);
        graph.get(second).add(first);
    }

    private static boolean[] findReachableNodes(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        return visited;
    }

    private static int[] findDistances(List<List<Integer>> graph, int start) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, -1);

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);
        distance[start] = 0;

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
     * 6. 문제에서 그래프를 떠올릴 단서
     *
     * - 여러 지점과 지점 사이의 연결이 주어진다.
     * - 한 시작점에서 갈 수 있는 모든 지점이나 가장 적은 이동 횟수를 구한다.
     * - 2차원 칸에서 상하좌우로 움직이며 벽을 피해야 한다.
     * - 서로 이어진 묶음이 몇 개인지 또는 각 묶음의 크기를 구한다.
     * - 반드시 거쳐야 하는 지점 앞뒤를 나누어 탐색해야 한다.
     * - 자식 쪽의 계산 결과를 부모 쪽으로 모아야 한다.
     */

    /*
     * 7. 자주 사용하는 도구와 실제 행동
     *
     * - List<List<Integer>>: 정점마다 바로 이어진 이웃을 저장한다.
     * - ArrayDeque를 스택으로 사용: push, pop으로 깊은 길을 먼저 따라간다.
     * - ArrayDeque를 큐로 사용: addLast, removeFirst로 가까운 지점부터 확인한다.
     * - boolean[] visited: 이미 확인한 정점을 다시 처리하지 않게 기억한다.
     * - int[] distance: -1이면 아직 도달하지 못했고, 0 이상이면 출발점에서의 거리다.
     * - 네 방향 배열: 격자의 다음 행과 열 후보를 같은 반복문으로 만든다.
     */

    /*
     * 8. 자주 하는 실수와 확인 방법
     *
     * - 단방향 간선을 양쪽에 넣거나, 무방향 간선을 한쪽에만 넣지 않았는지 확인한다.
     * - 문제의 정점 번호가 1부터 시작하면 배열 인덱스로 바꿀 때 1을 뺀다.
     * - 중복 간선과 자기 간선이 있어도 visited로 정점을 한 번만 처리한다.
     * - BFS는 큐에서 꺼낼 때가 아니라 큐에 넣을 때 방문을 표시한다.
     * - 격자는 배열을 읽기 전에 행과 열이 범위 안인지 먼저 확인한다.
     * - 도달할 수 없는 결과를 0과 구분하기 위해 -1 같은 값을 계약에서 정한다.
     * - 연결이 일렬로 매우 길면 재귀 DFS 대신 반복 DFS를 사용한다.
     * - 탐색 순서가 결과에 필요하지 않다면 이웃 입력 순서에 기대지 않는다.
     */

    /*
     * 9. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 정점과 간선은 각각 무엇인가?
     * 2) 간선은 단방향인가, 양방향인가?
     * 3) 정점 번호는 0부터인가, 1부터인가?
     * 4) 중복 간선과 자기 간선이 들어올 수 있는가?
     * 5) 필요한 결과는 도달 여부, 거리, 연결 묶음, 경로 중 무엇인가?
     * 6) DFS와 BFS 중 어느 탐색의 성질이 결과에 필요한가?
     * 7) 방문 표시는 언제 해야 같은 정점이 여러 번 쌓이지 않는가?
     * 8) 도달 불가능, 빈 간선, 정점 하나와 최대 깊이를 어떻게 처리할 것인가?
     * 9) 입력 순서가 바뀌어도 결과가 같아야 하는가?
     */
}

package bridge.graph.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** 각 장치가 속한 연결 묶음 크기 문제 정답과 풀이 설명이다. */
public final class GraphSolution04 {

    private GraphSolution04() {
    }

    public static int[] solve(int nodeCount, int[][] undirectedEdges) {
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

        boolean[] visited = new boolean[nodeCount];
        int[] componentSizeByNode = new int[nodeCount];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int start = 0; start < nodeCount; start++) {
            if (visited[start]) {
                continue;
            }

            List<Integer> members = new ArrayList<>();
            stack.push(start);
            visited[start] = true;
            while (!stack.isEmpty()) {
                int current = stack.pop();
                members.add(current);
                for (int next : graph.get(current)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        stack.push(next);
                    }
                }
            }

            int componentSize = members.size();
            for (int member : members) {
                componentSizeByNode[member] = componentSize;
            }
        }
        return componentSizeByNode;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 직접 이어지지 않아도 다른 장치를 거쳐 오갈 수 있으면 같은 묶음이다.
     * - 한 번의 탐색은 시작 장치가 속한 묶음 하나만 찾는다.
     * - 연결이 없는 장치도 크기 1인 묶음이므로 모든 번호를 확인해야 한다.
     * - 결과는 묶음 수가 아니라 각 장치가 속한 묶음 크기다.
     *
     * 이 개념을 선택한 이유
     * - 바깥 반복으로 미방문 장치를 찾고 DFS를 시작하면 모든 연결 묶음을 빠짐없이 찾는다.
     * - 탐색 중 구성원을 모아 두면 크기를 안 뒤 각 구성원의 결과 칸에 같은 값을 쓸 수 있다.
     *
     * 풀이 순서
     * 1. 양방향 인접 리스트를 만든다.
     * 2. 1번부터 모든 장치를 확인하며 이미 방문한 번호는 건너뛴다.
     * 3. 미방문 장치에서 반복 DFS를 시작해 같은 묶음의 구성원을 모은다.
     * 4. 모은 구성원 수를 각 구성원의 결과 인덱스에 저장한다.
     * 5. 모든 장치를 확인한 뒤 번호 순서의 결과를 반환한다.
     *
     * 예시 데이터 흐름
     * - 1, 2, 3은 서로 이어져 구성원 목록의 크기가 3이 된다.
     * - 결과 1~3번 칸에 각각 3을 쓴다.
     * - 4, 5의 묶음은 크기 2이고, 6과 7은 각각 크기 1이다.
     *
     * 복잡도
     * - 정점 수를 n, 간선 수를 m이라 할 때 시간 O(n + m)이다.
     * - 인접 리스트, 방문·결과 배열, 스택과 구성원 목록에 공간 O(n + m)이 필요하다.
     *
     * 자주 하는 실수
     * - 첫 정점에서 탐색 한 번만 하고 다른 연결 묶음을 빠뜨린다.
     * - 자기 간선이나 중복 간선을 새 구성원으로 센다.
     * - 묶음 크기를 알기 전에 일부 구성원에게 중간 크기를 저장한다.
     * - 연결이 없는 장치의 결과를 0으로 남긴다.
     */
}

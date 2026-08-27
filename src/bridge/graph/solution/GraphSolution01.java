package bridge.graph.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** 단방향 연락망의 도달 요약 문제 정답과 풀이 설명이다. */
public final class GraphSolution01 {

    private GraphSolution01() {
    }

    public static long[] solve(int[] nodeValues, int[][] directedEdges, int startNode) {
        List<List<Integer>> graph = new ArrayList<>(nodeValues.length);
        for (int node = 0; node < nodeValues.length; node++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : directedEdges) {
            graph.get(edge[0] - 1).add(edge[1] - 1);
        }

        boolean[] visited = new boolean[nodeValues.length];
        Deque<Integer> stack = new ArrayDeque<>();
        int startIndex = startNode - 1;
        stack.push(startIndex);
        visited[startIndex] = true;

        long reachedCount = 0L;
        long valueSum = 0L;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            reachedCount++;
            valueSum += nodeValues[current];

            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        return new long[]{reachedCount, valueSum};
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 간선은 from에서 to로만 갈 수 있으므로 한쪽 이웃 목록에만 넣어야 한다.
     * - 필요한 결과는 방문 순서가 아니라 도달한 지점의 수와 값의 합이다.
     * - 중복 간선과 자기 간선이 있어도 같은 지점의 값은 한 번만 더해야 한다.
     * - 지점이 일렬로 100,000개 이어질 수 있어 재귀 깊이에 기대면 위험하다.
     *
     * 이 개념을 선택한 이유
     * - 인접 리스트는 현재 지점에서 갈 수 있는 이웃만 바로 확인하게 해 준다.
     * - 반복 DFS는 도달 가능한 지점을 모두 찾으면서 Java 호출 스택을 사용하지 않는다.
     * - 스택에 넣을 때 방문을 표시하면 같은 지점이 중복으로 쌓이지 않는다.
     *
     * 풀이 순서
     * 1. 지점 수만큼 이웃 목록을 만들고 각 단방향 간선을 from 목록에 넣는다.
     * 2. 출발 번호를 0부터 시작하는 인덱스로 바꾸고 방문 표시한 뒤 스택에 넣는다.
     * 3. 스택에서 지점을 꺼낼 때 개수와 그 지점의 값을 더한다.
     * 4. 아직 방문하지 않은 이웃을 방문 표시하고 스택에 넣는다.
     * 5. 스택이 비면 개수와 합을 long 배열로 반환한다.
     *
     * 예시 데이터 흐름
     * - 1번에서 2번과 3번으로 갈 수 있고, 3번에서 4번으로 갈 수 있다.
     * - 도달하는 번호는 1, 2, 3, 4이고 방문 순서가 달라도 이 집합은 같다.
     * - 도달 수는 4, 값의 합은 10 + 20 - 5 + 40 = 65다.
     *
     * 복잡도
     * - 정점 수를 n, 간선 수를 m이라 할 때 시간 O(n + m)이다.
     * - 인접 리스트, 방문 배열과 스택에 공간 O(n + m)이 필요하다.
     *
     * 자주 하는 실수
     * - 단방향 간선을 양쪽 목록에 넣어 원래 갈 수 없는 지점까지 방문한다.
     * - 지점 번호에서 1을 빼지 않아 잘못된 배열 칸을 읽는다.
     * - 방문 표시를 하지 않아 중복·자기 간선에서 값을 여러 번 더한다.
     * - 값의 합을 int로 계산해 큰 입력에서 넘치게 한다.
     */
}

package bridge.graph.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/** 비상 상자가 양쪽에 남는 통로 수 문제 정답과 풀이 설명이다. */
public final class GraphSolution06 {

    private GraphSolution06() {
    }

    public static int solve(int nodeCount, int[][] undirectedLinks, int[] supplyNodes) {
        // 트리의 통로 하나를 막으면 자식 쪽과 나머지 쪽 두 묶음으로 정확히 나뉜다.
        // DFS 순서를 거꾸로 읽어 상자 수를 부모로 올리면 통로를 하나씩 실제로 끊어 볼 필요가 없다.

        // [1] 양방향 인접 리스트를 만들고 1번을 임시 뿌리로 정한다.
        List<List<Integer>> graph = new ArrayList<>(nodeCount);
        for (int node = 0; node < nodeCount; node++) {
            graph.add(new ArrayList<>());
        }
        for (int[] link : undirectedLinks) {
            int first = link[0] - 1;
            int second = link[1] - 1;
            graph.get(first).add(second);
            graph.get(second).add(first);
        }

        // [2] 반복 DFS로 각 정점의 부모와 방문 순서를 기록한다.
        int[] parent = new int[nodeCount];
        Arrays.fill(parent, -2);
        int[] visitOrder = new int[nodeCount];
        int orderSize = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        parent[0] = -1;
        stack.push(0);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            visitOrder[orderSize++] = current;
            for (int next : graph.get(current)) {
                if (parent[next] == -2) {
                    parent[next] = current;
                    stack.push(next);
                }
            }
        }

        // [3] 비상 상자가 있는 정점의 아래쪽 상자 수를 1로 시작한다.
        int[] subtreeSupplyCount = new int[nodeCount];
        for (int supplyNode : supplyNodes) {
            subtreeSupplyCount[supplyNode - 1] = 1;
        }

        int totalSupplyCount = supplyNodes.length;
        int validBoundaryCount = 0;

        // [4] 방문 순서를 거꾸로 확인하며 자식 쪽 수와 전체에서 뺀 반대쪽 수를 구한다.
        for (int index = orderSize - 1; index >= 1; index--) {
            int child = visitOrder[index];
            int childSide = subtreeSupplyCount[child];
            int otherSide = totalSupplyCount - childSide;

            // [5] 두 쪽에 상자가 모두 있으면 유효한 경계 수를 1 늘린다.
            if (childSide > 0 && otherSide > 0) {
                validBoundaryCount++;
            }

            // [6] 경계 조건과 관계없이 자식 쪽 상자 수를 부모에게 더한다.
            subtreeSupplyCount[parent[child]] += childSide;
        }
        return validBoundaryCount;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 연결은 트리라서 통로 하나를 막으면 정확히 두 묶음으로 나뉜다.
     * - 뿌리를 하나 정하면 모든 통로를 부모와 자식 사이의 경계로 볼 수 있다.
     * - 자식 아래의 상자 수를 알면 반대쪽은 전체 상자 수에서 빼서 구할 수 있다.
     * - 일렬 트리의 깊이가 100,000일 수 있어 재귀 DFS는 호출 스택을 넘길 수 있다.
     *
     * 이 개념을 선택한 이유
     * - 반복 DFS의 방문 순서를 거꾸로 읽으면 자식 계산이 부모 계산보다 먼저 끝난다.
     * - 자식 아래의 상자 수를 부모에게 더하면 통로를 실제로 끊지 않고 모든 경계를 한 번씩 판단한다.
     *
     * 풀이 순서
     * 1. 양방향 인접 리스트를 만들고 1번을 임시 뿌리로 정한다.
     * 2. 반복 DFS로 각 정점의 부모와 방문 순서를 기록한다.
     * 3. 비상 상자가 있는 정점의 아래쪽 상자 수를 1로 시작한다.
     * 4. 방문 순서를 거꾸로 확인하며 자식 쪽 수와 전체에서 뺀 반대쪽 수를 구한다.
     * 5. 두 쪽에 상자가 모두 있으면 유효한 경계 수를 1 늘린다.
     * 6. 경계 조건과 관계없이 자식 쪽 상자 수를 부모에게 더한다.
     *
     * 예시 데이터 흐름
     * - 전체 비상 상자는 4, 5, 7번의 3개다.
     * - 2번 아래에는 4번과 5번 상자 2개가 있어 1-2 통로 반대쪽에는 1개가 남는다.
     * - 3-6 통로의 6번 쪽에는 상자가 없어 세지 않는다.
     * - 나머지 조건을 만족하는 경계를 합하면 5개다.
     *
     * 복잡도
     * - 정점 수를 n이라 할 때 트리 간선은 n - 1개이므로 시간 O(n)이다.
     * - 인접 리스트, 부모·순서·상자 수 배열과 스택에 공간 O(n)이 필요하다.
     *
     * 자주 하는 실수
     * - 부모보다 먼저 자식 수가 완성되지 않은 순서로 누적한다.
     * - 자식 쪽 상자 수만 보고 반대쪽에 상자가 있는지 확인하지 않는다.
     * - 통로마다 새 탐색을 실행해 O(n²) 시간이 든다.
     * - 깊은 트리를 재귀로 탐색해 StackOverflowError가 난다.
     */
}

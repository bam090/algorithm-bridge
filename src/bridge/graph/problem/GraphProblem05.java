package bridge.graph.problem;

/*
 * 문제 | 필수 점검소를 거친 목적지별 거리
 * nodeCount개의 장소는 1번부터 nodeCount번까지 번호가 붙어 있다.
 * directedRoutes의 각 행 {from, to}는 from번에서 to번으로만 갈 수 있는 길이다.
 * startNode에서 출발해 checkpointNode를 반드시 거친 뒤 destinations의 각 장소로 가는 데
 * 필요한 최소 길 개수를 destinations와 같은 순서로 반환하라. 그런 이동이 불가능하면 -1을 반환한다.
 * destinations에는 같은 장소가 여러 번 들어올 수 있다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 점검소를 지나지 않은 더 짧은 길을 답으로 사용할 수 있는가?
 * - 길이 단방향이면 먼저 지나온 장소를 점검소 뒤에도 다시 갈 수 있다고 가정해도 되는가?
 * - 목적지가 여러 개일 때 결과는 어떤 순서를 따라야 하는가?
 * - 출발점과 점검소가 같은 번호라면 점검소까지 지나는 길은 몇 개인가?
 * - 조건에 맞는 이동이 불가능한 목적지에는 어떤 값을 반환해야 하는가?
 */
public final class GraphProblem05 {

    private GraphProblem05() {
        solve(
                7,
                new int[][]{{1, 2}, {2, 3}, {1, 4}, {4, 3}, {3, 5}, {5, 6}, {3, 7}},
                1,
                3,
                new int[]{3, 5, 6, 2, 7}
        );
        // 예상 출력: new int[]{2, 3, 4, -1, 3}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 출발점에서 모든 거리와 점검소에서 모든 거리를 서로 다른 BFS로 구한 뒤, 출발점에서 점검소까지와 점검소에서 각 목적지까지의 거리를 더해 입력된 목적지 순서로 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - nodeCount는 1 이상 100,000 이하이다.
     * - directedRoutes는 null이 아니며 길이는 0 이상 200,000 이하이다.
     * - 각 행은 길이 2인 {from, to}이고 두 번호는 1 이상 nodeCount 이하이다.
     * - 간선은 단방향이며 같은 간선과 {node, node} 자기 간선이 여러 번 들어올 수 있다.
     * - startNode와 checkpointNode는 1 이상 nodeCount 이하이다.
     * - destinations는 null이 아니며 길이는 0 이상 100,000 이하이다.
     * - 모든 목적지 번호는 1 이상 nodeCount 이하이고 같은 번호가 여러 번 들어올 수 있다.
     * - 각 길의 이동 비용은 1이며 연결 입력 순서와 방문 순서는 결과에 영향을 주지 않는다.
     * - 모든 입력 배열은 바꾸면 안 된다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(
            int nodeCount,
            int[][] directedRoutes,
            int startNode,
            int checkpointNode,
            int[] destinations
    ) {
        int[] answer = {};
        return answer;
    }
}

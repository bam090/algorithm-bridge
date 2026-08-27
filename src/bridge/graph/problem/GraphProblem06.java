package bridge.graph.problem;

//region 문제: 비상 상자가 양쪽에 남는 통로 수
/*
 nodeCount개의 구역은 1번부터 nodeCount번까지 번호가 붙어 있다.
 undirectedLinks의 각 행 {a, b}는 두 구역을 잇는 통로이며 전체 연결은 하나의 트리다.
 supplyNodes에는 비상 상자가 있는 구역 번호가 서로 다르게 들어 있다.
 통로 하나를 막아 두 구역 묶음으로 나눴을 때 양쪽 묶음에 비상 상자가 하나 이상 남는
 통로의 수를 반환하라. 실제 입력 배열이나 연결을 끊어 보며 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 통로 하나를 막으면 트리는 몇 개의 연결 묶음으로 나뉘는가?
 - 조건을 만족하려면 나뉜 두 묶음에 각각 무엇이 하나 이상 있어야 하는가?
 - 비상 상자가 없는 구역도 두 구역을 잇는 연결 경로의 일부가 될 수 있는가?
 - 비상 상자가 0개이거나 1개뿐이면 조건을 만족하는 통로가 있을 수 있는가?
 - 구역이 100,000개일 때 확인해야 할 통로는 몇 개인가?
 */
//endregion

public final class GraphProblem06 {

    private GraphProblem06() {
        solve(
                7,
                new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}},
                new int[]{4, 5, 7}
        );
        // 예상 출력: 5
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     반복 DFS로 부모와 자식 순서를 만든 뒤, 자식부터 비상 상자 수를 부모로 올리고 각 통로의 자식 쪽 수와 전체에서 뺀 반대쪽 수가 모두 1 이상인 경계를 세어 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - nodeCount는 1 이상 100,000 이하이다.
     - undirectedLinks는 null이 아니며 길이는 nodeCount - 1이다.
     - 각 행은 길이 2인 {a, b}이고 두 번호는 1 이상 nodeCount 이하이다.
     - 통로는 양방향이고 자기 간선과 중복 간선이 없으며 전체 구역은 하나로 연결된 트리다.
     - supplyNodes는 null이 아니며 길이는 0 이상 nodeCount 이하이다.
     - 모든 구역 번호는 1 이상 nodeCount 이하이고 서로 다르다.
     - 통로 입력 순서와 탐색에서 정한 부모 방향은 결과에 영향을 주지 않는다.
     - 모든 입력 배열은 바꾸면 안 된다.
     - 구역이 일렬로 이어진 최대 깊이에서도 계산을 안전하게 완료해야 한다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int nodeCount, int[][] undirectedLinks, int[] supplyNodes) {
        int answer = 0;
        return answer;
    }
}

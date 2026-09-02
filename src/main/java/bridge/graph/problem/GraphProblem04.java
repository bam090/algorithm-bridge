package bridge.graph.problem;

//region 문제: 각 장치가 속한 연결 묶음 크기
/*
 nodeCount개의 장치는 1번부터 nodeCount번까지 번호가 붙어 있다.
 undirectedEdges의 각 행 {a, b}는 a번과 b번 장치가 양쪽으로 연결되어 있다는 뜻이다.
 직접 또는 다른 장치를 거쳐 서로 오갈 수 있으면 같은 연결 묶음이다.
 각 장치가 속한 연결 묶음의 전체 장치 수를 장치 번호 순서로 반환하라.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 한 번의 탐색으로 찾은 장치들을 같은 묶음으로 기억하려면 무엇을 모아야 하는가?
 - 아직 어떤 묶음에도 들어가지 않은 장치를 빠뜨리지 않으려면 탐색 밖에서 무엇을 반복해야 하는가?
 - 묶음의 장치를 모두 찾기 전에도 각 장치에 최종 크기를 쓸 수 있는가?
 - 연결이 하나도 없는 장치의 묶음 크기는 얼마인가?
 */
//endregion

public final class GraphProblem04 {

    private GraphProblem04() {
        solve(
                7,
                new int[][]{{1, 2}, {2, 3}, {4, 5}, {5, 5}, {4, 5}}
        );
        // 예상 출력: new int[]{3, 3, 3, 2, 2, 1, 1}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     모든 장치를 번호순으로 확인하고, 아직 방문하지 않은 장치에서 탐색해 같은 묶음의 장치를 모은 뒤 그 장치들의 결과 칸에 묶음 크기를 저장해 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - nodeCount는 1 이상 100,000 이하이다.
     - undirectedEdges는 null이 아니며 길이는 0 이상 200,000 이하이다.
     - 각 행은 길이 2인 {a, b}이고 두 번호는 1 이상 nodeCount 이하이다.
     - 간선은 양방향이며 같은 간선과 {node, node} 자기 간선이 여러 번 들어올 수 있다.
     - 연결 입력 순서와 탐색 순서는 결과에 영향을 주지 않는다.
     - 입력 배열은 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int nodeCount, int[][] undirectedEdges) {
        int[] answer = {};
        return answer;
    }
}

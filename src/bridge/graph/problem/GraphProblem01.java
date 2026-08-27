package bridge.graph.problem;

//region 문제: 단방향 연락망의 도달 요약
/*
 nodeValues[i]는 i + 1번 지점의 값이다.
 directedEdges의 각 행 {from, to}는 from번에서 to번으로만 갈 수 있는 연결이다.
 startNode에서 출발해 도달할 수 있는 지점의 수와 그 지점 값의 합을
 new long[]{도달한 지점 수, 값의 합}으로 반환하라. 출발 지점도 결과에 포함한다.
 연결을 어떤 순서로 방문했는지는 결과에 영향을 주지 않는다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 각 지점에서 바로 갈 수 있는 지점을 빠르게 찾으려면 간선을 어떤 모양으로 저장해야 하는가?
 - 같은 지점으로 돌아오는 연결이나 중복 연결이 있을 때 값을 여러 번 더하지 않으려면 무엇을 기억해야 하는가?
 - 지점 번호가 1부터 시작할 때 nodeValues의 인덱스로는 어떻게 바꾸는가?
 - 지점이 일렬로 100,000개 이어져도 안전하게 탐색하려면 재귀 대신 무엇을 사용할 수 있는가?
 */
//endregion

public final class GraphProblem01 {

    private GraphProblem01() {
        solve(
                new int[]{10, 20, -5, 40, 7},
                new int[][]{{1, 2}, {1, 3}, {3, 4}},
                1
        );
        // 예상 출력: new long[]{4, 65}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     단방향 간선을 인접 리스트에 담은 뒤, 출발 지점을 방문 표시하고 스택으로 도달한 지점을 한 번씩 확인해 개수와 값의 합을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - nodeValues는 null이 아니며 길이는 1 이상 100,000 이하이다.
     - 각 값은 -1,000,000 이상 1,000,000 이하이다.
     - directedEdges는 null이 아니며 길이는 0 이상 200,000 이하이다.
     - 각 행은 길이 2인 {from, to}이고 두 번호는 1 이상 nodeValues.length 이하이다.
     - 간선은 단방향이며 같은 간선과 {node, node} 자기 간선이 여러 번 들어올 수 있다.
     - startNode는 1 이상 nodeValues.length 이하이다.
     - 연결 입력 순서와 탐색 순서는 결과에 영향을 주지 않는다.
     - 모든 입력 배열은 바꾸면 안 되고 값의 합은 long으로 계산한다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static long[] solve(int[] nodeValues, int[][] directedEdges, int startNode) {
        long[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 지점 수만큼 빈 이웃 목록을 만들고 {from, to}를 from의 목록에만 넣는다.
     - startNode - 1을 스택에 넣는 순간 방문 표시한다.
     - 스택에서 꺼낸 지점의 개수와 값을 더하고, 아직 방문하지 않은 이웃만 스택에 넣는다.
     */
    //endregion
}

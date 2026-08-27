package bridge.tree.problem;

/*
 * 문제 | 두 상자의 가장 가까운 공통 보관 칸
 * 보관 칸 번호는 1부터 시작한다. 1번은 뿌리이고, n번 칸의 자식은 2 * n과 2 * n + 1,
 * 부모는 n / 2번 칸이다.
 * firstNode와 secondNode에서 각각 부모를 따라 올라가 처음 함께 도착하는 가장 가까운 칸을 찾는다.
 * 공통 칸 번호, firstNode가 이동한 횟수, secondNode가 이동한 횟수를 차례대로 반환하라.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 현재 칸 번호에서 부모 번호는 어떤 계산으로 구하는가?
 * - 두 번호가 다를 때 더 큰 번호가 더 작은 번호의 조상이 될 수 있는가?
 * - 한쪽만 부모로 이동했을 때 어느 이동 횟수를 늘려야 하는가?
 * - 두 시작 번호가 처음부터 같다면 결과는 무엇인가?
 */
public final class TreeProblem03 {

    private TreeProblem03() {
        solve(10, 11);
        // 예상 출력: new int[]{5, 1, 1}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 두 번호가 다르면 더 큰 번호를 2로 나눠 부모로 올리고 그쪽 이동 횟수를 늘린 뒤, 같아진 번호와 두 이동 횟수를 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - firstNode와 secondNode는 1 이상 1,000,000,000 이하이다.
     * - 부모로 이동할 때마다 번호가 작아지며, 최대 29번 이동하면 1번 뿌리에 도착한다.
     * - 전체 트리나 노드 객체를 만들지 않고 번호 계산만으로 처리한다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int firstNode, int secondNode) {
        int[] answer = {};
        return answer;
    }
}

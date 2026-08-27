package bridge.tree.solution;

/** 두 상자의 가장 가까운 공통 보관 칸 문제 정답과 풀이 설명이다. */
public final class TreeSolution03 {

    private TreeSolution03() {
    }

    public static int[] solve(int firstNode, int secondNode) {
        int first = firstNode;
        int second = secondNode;
        int firstMoves = 0;
        int secondMoves = 0;

        while (first != second) {
            if (first > second) {
                first /= 2;
                firstMoves++;
            } else {
                second /= 2;
                secondMoves++;
            }
        }

        return new int[]{first, firstMoves, secondMoves};
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 번호 n의 부모는 n / 2라서 전체 트리를 만들 필요가 없다.
     * - 부모 번호는 자식 번호보다 항상 작다.
     * - 두 시작점의 깊이가 다를 수 있으므로 이동 횟수를 따로 세어야 한다.
     *
     * 이 개념을 선택한 이유
     * - 더 큰 번호는 더 작은 현재 번호의 조상이 될 수 없다.
     * - 두 번호 중 큰 쪽만 부모로 올리면 건너뛴 공통 조상 없이 두 번호가 가까워진다.
     *
     * 풀이 순서
     * 1. 시작 번호를 first와 second에 복사하고 이동 횟수를 0으로 둔다.
     * 2. 두 번호가 다르면 더 큰 번호를 2로 나눠 부모 번호로 바꾼다.
     * 3. 부모로 이동한 쪽의 횟수를 1 늘린다.
     * 4. 두 번호가 같아지면 그 번호와 양쪽 이동 횟수를 반환한다.
     *
     * 예시 데이터 흐름
     * - firstNode=10, secondNode=11
     * - 11이 더 크므로 11 -> 5, secondMoves=1
     * - 10이 더 크므로 10 -> 5, firstMoves=1
     * - 두 번호가 5로 같아져 [5, 1, 1]을 반환한다.
     *
     * 복잡도
     * - 시간 O(log m): 큰 시작 번호 m을 부모로 올릴 때마다 절반이 된다.
     * - 추가 공간 O(1): 두 현재 번호와 두 이동 횟수만 기억한다.
     *
     * 자주 하는 실수
     * - 두 번호의 깊이가 다른데 항상 양쪽을 동시에 부모로 올린다.
     * - 공통 번호가 된 뒤에도 한 번 더 부모로 이동한다.
     * - 첫 번째와 두 번째 이동 횟수를 반대로 저장한다.
     */
}

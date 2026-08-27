package bridge.set.solution;

/** 연결 뒤 남은 작업 구역 수 문제의 정답과 풀이 설명이다. */
public final class SetSolution03 {

    private SetSolution03() {
    }

    public static int solve(int itemCount, int[][] connections) {
        int[] parent = new int[itemCount];
        int[] groupSize = new int[itemCount];
        for (int i = 0; i < itemCount; i++) {
            parent[i] = i;
            groupSize[i] = 1;
        }

        int groupCount = itemCount;
        for (int[] connection : connections) {
            if (union(parent, groupSize, connection[0], connection[1])) {
                groupCount--;
            }
        }
        return groupCount;
    }

    private static int find(int[] parent, int value) {
        if (parent[value] != value) {
            parent[value] = find(parent, parent[value]);
        }
        return parent[value];
    }

    private static boolean union(int[] parent, int[] groupSize, int first, int second) {
        int firstRoot = find(parent, first);
        int secondRoot = find(parent, second);
        if (firstRoot == secondRoot) {
            return false;
        }

        if (groupSize[firstRoot] < groupSize[secondRoot]) {
            int temporary = firstRoot;
            firstRoot = secondRoot;
            secondRoot = temporary;
        }
        parent[secondRoot] = firstRoot;
        groupSize[firstRoot] += groupSize[secondRoot];
        return true;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 처음에는 작업대마다 서로 다른 구역이므로 구역 수는 itemCount다.
     * - 작업대 두 개를 연결하면 두 작업대가 속한 전체 구역이 하나가 된다.
     * - 이미 같은 구역인 연결은 구역 수를 다시 줄이지 않는다.
     *
     * 이 개념을 선택한 이유
     * - 유니온-파인드는 각 작업대의 최종 대표를 찾아 연결된 그룹을 관리한다.
     * - 경로 압축과 큰 그룹 아래 붙이기를 사용하면 많은 연결도 빠르게 처리할 수 있다.
     *
     * 풀이 순서
     * 1. parent[i]=i, groupSize[i]=1로 각 작업대를 자기 구역에서 시작한다.
     * 2. 연결의 두 작업대에서 부모를 따라가 최종 대표를 찾는다.
     * 3. 두 대표가 다르면 작은 그룹의 대표를 큰 그룹의 대표 아래에 붙인다.
     * 4. 실제로 두 그룹을 합친 경우에만 groupCount를 1 줄인다.
     * 5. 모든 연결 뒤 groupCount를 반환한다.
     *
     * 예시 데이터 흐름
     * - itemCount=6이므로 처음 구역은 6개다.
     * - [4,2]를 연결하면 구역은 5개다.
     * - [3,1]을 연결하면 구역은 4개다.
     * - [2,1]은 {2,4}와 {1,3}을 합쳐 구역은 3개다.
     * - 0과 5는 각각 혼자 남고, 반환값은 3이다.
     *
     * 복잡도
     * - 초기화 시간 O(n), 연결 m개를 처리하는 시간은 O(m α(n))이다.
     * - α(n)은 실제 입력 범위에서 매우 작아 대표 찾기 한 번이 거의 일정한 시간처럼 동작한다.
     * - 공간 O(n): parent와 groupSize 배열이 필요하다.
     *
     * 자주 하는 실수
     * - 작업대 번호끼리 바로 연결해 기존 그룹의 일부를 끊는다.
     * - 같은 그룹을 다시 연결할 때 groupCount를 또 줄인다.
     * - 자기 자신 연결에서도 groupCount를 줄인다.
     */
}

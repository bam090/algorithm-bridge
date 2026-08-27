package bridge.set.solution;

import java.util.Arrays;

/** 장비 연결 감사의 실패 요청 찾기 문제의 정답과 풀이 설명이다. */
public final class SetSolution04 {

    private SetSolution04() {
    }

    public static int[] solve(
            int deviceCount,
            String[] actions,
            int[] firstIds,
            int[] secondIds
    ) {
        int[] parent = new int[deviceCount];
        int[] groupSize = new int[deviceCount];
        for (int i = 0; i < deviceCount; i++) {
            parent[i] = i;
            groupSize[i] = 1;
        }

        int[] failedRequests = new int[actions.length];
        int failedCount = 0;

        for (int i = 0; i < actions.length; i++) {
            if ("LINK".equals(actions[i])) {
                union(parent, groupSize, firstIds[i], secondIds[i]);
            } else if (find(parent, firstIds[i]) != find(parent, secondIds[i])) {
                failedRequests[failedCount] = i + 1;
                failedCount++;
            }
        }
        return Arrays.copyOf(failedRequests, failedCount);
    }

    private static int find(int[] parent, int value) {
        if (parent[value] != value) {
            parent[value] = find(parent, parent[value]);
        }
        return parent[value];
    }

    private static void union(int[] parent, int[] groupSize, int first, int second) {
        int firstRoot = find(parent, first);
        int secondRoot = find(parent, second);
        if (firstRoot == secondRoot) {
            return;
        }

        if (groupSize[firstRoot] < groupSize[secondRoot]) {
            int temporary = firstRoot;
            firstRoot = secondRoot;
            secondRoot = temporary;
        }
        parent[secondRoot] = firstRoot;
        groupSize[firstRoot] += groupSize[secondRoot];
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - LINK와 AUDIT은 입력 순서대로 섞여 있으므로 그 시점의 연결 상태를 사용해야 한다.
     * - 직접 연결되지 않은 장비도 여러 연결을 따라가면 같은 그룹일 수 있다.
     * - 실패 결과에는 AUDIT만 센 순번이 아니라 모든 요청을 1부터 센 번호를 기록한다.
     *
     * 이 개념을 선택한 이유
     * - 유니온-파인드는 연결 요청과 같은 그룹 확인이 반복될 때 전체 그룹을 매번 다시 찾지 않는다.
     * - find()에서 지나온 부모를 최종 대표로 바꾸면 같은 경로를 다시 확인하는 시간이 줄어든다.
     *
     * 풀이 순서
     * 1. 각 장비를 자기 대표와 크기 1인 그룹으로 시작한다.
     * 2. LINK 요청이면 두 최종 대표를 찾아 서로 다를 때 합친다.
     * 3. AUDIT 요청이면 두 최종 대표를 비교한다.
     * 4. 대표가 다르면 현재 인덱스에 1을 더한 요청 번호를 임시 결과에 기록한다.
     * 5. 실제 실패 개수만큼 복사한 새 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - 요청 1 AUDIT(0,1): 아직 달라 실패 번호 1을 기록한다.
     * - 요청 2 LINK(0,1), 요청 3 LINK(1,2): 0, 1, 2가 같은 그룹이 된다.
     * - 요청 4 AUDIT(0,2): 같은 그룹이라 기록하지 않는다.
     * - 요청 5 AUDIT(0,5): 달라 실패 번호 5를 기록한다.
     * - 요청 6 LINK(2,5) 뒤 요청 7 AUDIT(0,5)는 성공한다.
     * - 반환: [1, 5]
     *
     * 복잡도
     * - 장비 d개 초기화와 요청 r개 처리에 O(d + r α(d)) 시간이 든다.
     * - α(d)은 실제 입력 범위에서 매우 작은 값이다.
     * - 공간 O(d+r): 부모·크기 배열과 최대 요청 수 크기의 임시 실패 배열이 필요하다.
     *
     * 자주 하는 실수
     * - 모든 LINK를 먼저 처리해 과거 AUDIT 결과까지 바꾼다.
     * - parent[a]와 parent[b]만 비교해 최종 대표가 같은 연결을 놓친다.
     * - 실패 번호를 i로 기록해 0부터 세는 번호를 반환한다.
     * - 같은 그룹을 다시 LINK할 때 크기를 두 번 더한다.
     */
}

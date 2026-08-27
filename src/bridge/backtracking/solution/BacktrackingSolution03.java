package bridge.backtracking.solution;

/** 에너지 조절 장치 점검 순서 문제 정답과 풀이 설명이다. */
public final class BacktrackingSolution03 {

    private BacktrackingSolution03() {
    }

    public static int[] solve(
            int initialEnergy,
            int[] requiredEnergy,
            int[] signedEnergyChange
    ) {
        boolean[] visited = new boolean[requiredEnergy.length];
        int[] currentOrder = new int[requiredEnergy.length];
        return findFirstOrder(
                initialEnergy,
                0,
                requiredEnergy,
                signedEnergyChange,
                visited,
                currentOrder
        );
    }

    private static int[] findFirstOrder(
            int currentEnergy,
            int depth,
            int[] requiredEnergy,
            int[] signedEnergyChange,
            boolean[] visited,
            int[] currentOrder
    ) {
        if (depth == requiredEnergy.length) {
            return currentOrder.clone();
        }

        for (int device = 0; device < requiredEnergy.length; device++) {
            int nextEnergy = currentEnergy + signedEnergyChange[device];
            if (visited[device] || currentEnergy < requiredEnergy[device] || nextEnergy < 0) {
                continue;
            }

            visited[device] = true;
            currentOrder[depth] = device + 1;
            int[] completedOrder = findFirstOrder(
                    nextEnergy,
                    depth + 1,
                    requiredEnergy,
                    signedEnergyChange,
                    visited,
                    currentOrder
            );
            visited[device] = false;

            if (completedOrder.length > 0) {
                return completedOrder;
            }
        }
        return new int[0];
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 장치는 한 번씩만 점검할 수 있지만 점검 순서는 자유다.
     * - 점검 뒤 에너지가 늘거나 줄어 다음에 점검할 수 있는 장치가 달라진다.
     * - 가능한 순서 중 사전식으로 가장 앞선 하나만 반환한다.
     *
     * 이 개념을 선택한 이유
     * - visited 배열은 현재 순서에서 이미 점검한 장치를 막는다.
     * - 낮은 번호부터 재귀로 확인하고 첫 완성에서 멈추면 사전식 첫 순서가 된다.
     *
     * 풀이 순서
     * 1. 모든 장치를 점검했다면 현재 1기반 순서를 복사하고 성공을 반환한다.
     * 2. 아직 점검하지 않은 장치를 낮은 번호부터 확인한다.
     * 3. 현재 에너지가 필요 에너지 이상이고 변화 뒤 에너지가 0 이상인 장치만 고른다.
     * 4. 장치 번호를 순서에 적고 점검 표시한 뒤, 바뀐 에너지로 다음 깊이를 확인한다.
     * 5. 돌아오면 점검 표시를 지우고, 완성에 성공했다면 즉시 탐색을 끝낸다.
     *
     * 예시 데이터 흐름
     * - 시작 에너지는 5다.
     * - 1번 장치 점검: 5 + (-3) = 2
     * - 2번 장치는 필요 에너지 6을 만족하지 못하므로 건너뛴다.
     * - 3번 장치 점검: 2 + 4 = 6
     * - 2번 장치 점검: 6 + 4 = 10
     * - 낮은 번호부터 확인해 처음 완성한 순서 [1, 3, 2]를 반환한다.
     *
     * 복잡도
     * - 시간 O(n × n!): 각 점검 순서에서 다음 장치 후보를 최대 n개 확인한다.
     * - n=8이면 상태는 약 109,601개, 후보 확인은 88만 회 이하라 제한 안에서 실행할 수 있다.
     * - 공간 O(n): visited 배열과 최대 깊이 n의 재귀 호출을 사용한다.
     *
     * 자주 하는 실수
     * - 필요 에너지만 확인하고 변화 뒤 에너지가 음수가 되는지 확인하지 않는다.
     * - 재귀 뒤 visited 표시를 지우지 않아 다른 점검 순서를 막는다.
     * - 완성 순서를 currentOrder 참조 그대로 저장해 이후 탐색이 답을 바꾼다.
     * - 큰 번호부터 확인하거나 완성 뒤에도 탐색해 사전식 첫 순서를 잃는다.
     */
}

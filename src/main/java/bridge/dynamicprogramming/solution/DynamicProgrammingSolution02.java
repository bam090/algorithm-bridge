package bridge.dynamicprogramming.solution;

/** 사진 묶음 인쇄 최소 비용 문제 정답과 풀이 설명이다. */
public final class DynamicProgrammingSolution02 {

    private DynamicProgrammingSolution02() {
    }

    public static long solve(int[] singleCosts, int[] pairCosts) {
        // 동적 계획법을 선택한 이유:
        // 마지막 인쇄는 한 장 또는 두 장뿐이므로, 앞부분의 최소 비용을 저장해 두 경우에 다시 쓴다.

        int photoCount = singleCosts.length;

        // [1] 사진이 없으면 비용 0을 반환한다.
        if (photoCount == 0) {
            return 0L;
        }

        // [2] 0장을 끝낸 비용은 0, 1장을 끝낸 비용은 첫 사진의 한 장 비용으로 저장한다.
        long[] minimumCost = new long[photoCount + 1];
        minimumCost[0] = 0L;
        minimumCost[1] = singleCosts[0];

        for (int count = 2; count <= photoCount; count++) {
            // [3] 마지막 한 장을 따로 인쇄하는 비용을 계산한다.
            long lastSingle = minimumCost[count - 1] + singleCosts[count - 1];

            // [4] 마지막 두 장을 묶어 인쇄하는 비용을 계산한다.
            long lastPair = minimumCost[count - 2] + pairCosts[count - 2];

            // [5] 두 비용 중 작은 값을 현재 사진 수의 최소 비용으로 저장한다.
            minimumCost[count] = Math.min(lastSingle, lastPair);
        }

        // [6] 모든 사진을 끝낸 최소 비용을 반환한다.
        return minimumCost[photoCount];
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 사진은 순서대로 빠짐없이 인쇄해야 한다.
     * - 마지막 인쇄는 마지막 사진 한 장이거나 마지막 두 사진의 묶음이다.
     * - 두 경우 앞부분의 최소 비용은 여러 계산에서 다시 필요하다.
     *
     * 이 개념을 선택한 이유
     * - minimumCost[count]를 앞의 count장을 끝낸 최소 비용으로 정하면 마지막 행동을 두 경우로 나눌 수 있다.
     * - 각 경우는 이미 계산한 count - 1장 또는 count - 2장의 최소 비용과 연결된다.
     *
     * 풀이 순서
     * 1. 사진이 없으면 비용 0을 반환한다.
     * 2. 0장을 끝낸 비용은 0, 1장을 끝낸 비용은 첫 사진의 한 장 비용으로 저장한다.
     * 3. 마지막 한 장을 따로 인쇄하는 비용을 계산한다.
     * 4. 마지막 두 장을 묶어 인쇄하는 비용을 계산한다.
     * 5. 두 비용 중 작은 값을 현재 사진 수의 최소 비용으로 저장한다.
     * 6. 모든 사진을 끝낸 최소 비용을 반환한다.
     *
     * 예시 데이터 흐름
     * - 첫 사진 한 장의 최소 비용은 6이다.
     * - 앞의 두 장은 따로 인쇄하면 11, 묶으면 8이므로 최소 비용은 8이다.
     * - 세 장은 마지막 한 장을 더하면 15, 마지막 두 장을 묶으면 16이므로 15다.
     * - 네 장은 마지막 한 장을 더하면 19, 마지막 두 장을 묶으면 14이므로 14를 반환한다.
     *
     * 복잡도
     * - 사진 수를 n이라 할 때 시간 O(n)이다.
     * - 사진 수별 최소 비용을 저장하는 데 공간 O(n)이 필요하다.
     *
     * 자주 하는 실수
     * - 사진이 0장이나 1장인데 minimumCost[2]를 먼저 사용한다.
     * - 묶음 비용을 두 장의 한 장 비용에 추가로 더한다.
     * - 가장 싼 묶음부터 고르는 탐욕 방법을 사용해 서로 겹치는 묶음을 잘못 선택한다.
     * - 최대 합이 int를 넘는데 int 배열에 저장한다.
     */
}

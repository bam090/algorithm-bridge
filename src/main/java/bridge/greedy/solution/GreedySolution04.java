package bridge.greedy.solution;

import java.util.Arrays;

/** 균형 범위의 추 두 개 고르기 문제의 정답과 풀이 설명이다. */
public final class GreedySolution04 {

    private GreedySolution04() {
    }

    public static int solve(int[] weights, int minimumPairWeight, int maximumPairWeight) {
        // 정렬과 양끝 포인터를 선택한 이유:
        // 양끝 합을 보면 너무 가볍거나 무거워 다시 쓸 수 없는 추를 바로 제외할 수 있다.

        // [1] 원본을 복사해 무게 오름차순으로 정렬한다.
        int[] ordered = weights.clone();
        Arrays.sort(ordered);

        int pairCount = 0;
        int light = 0;
        int heavy = ordered.length - 1;

        while (light < heavy) {
            // [2] 가장 가벼운 추와 가장 무거운 추의 합을 계산한다.
            long combinedWeight = (long) ordered[light] + ordered[heavy];

            // [3] 합이 하한보다 작으면 가벼운 쪽만, 상한보다 크면 무거운 쪽만 옮긴다.
            if (combinedWeight < minimumPairWeight) {
                light++;
            } else if (combinedWeight > maximumPairWeight) {
                heavy--;
            } else {
                // [4] 합이 범위 안이면 쌍을 하나 늘리고 양쪽을 모두 옮긴다.
                pairCount++;
                light++;
                heavy--;
            }
        }

        // [5] 두 위치가 만나거나 엇갈리면 쌍의 개수를 반환한다.
        return pairCount;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 두 추의 합에는 하한과 상한이 모두 있다.
     * - 각 추는 한 번만 사용할 수 있고 쌍의 개수를 가장 크게 만들어야 한다.
     * - 정렬하면 합이 너무 작거나 클 때 바꿀 쪽을 정할 수 있다.
     *
     * 이 선택이 안전한 이유
     * - 가장 가벼운 추와 가장 무거운 추의 합이 하한보다 작으면, 가장 가벼운 추는 다른 어떤 추와도 하한에 닿지 못한다.
     * - 그 합이 상한보다 크면, 가장 무거운 추는 다른 어떤 추와도 상한을 넘는다.
     * - 합이 범위 안이면 두 추를 짝지어도 남은 추로 만들 수 있는 최대 쌍 수가 줄지 않는다.
     *
     * 풀이 순서
     * 1. 원본을 복사해 무게 오름차순으로 정렬한다.
     * 2. 가장 가벼운 추와 가장 무거운 추의 합을 계산한다.
     * 3. 합이 하한보다 작으면 가벼운 쪽만, 상한보다 크면 무거운 쪽만 옮긴다.
     * 4. 합이 범위 안이면 쌍을 하나 늘리고 양쪽을 모두 옮긴다.
     * 5. 두 위치가 만나거나 엇갈리면 쌍의 개수를 반환한다.
     *
     * 예시 데이터 흐름
     * - 정렬된 무게는 [10,20,40,50,70]이고 허용 합은 60부터 80이다.
     * - 10+70=80이므로 한 쌍을 만들고 양쪽을 옮긴다.
     * - 20+50=70이므로 두 번째 쌍을 만들고 2를 반환한다.
     *
     * 복잡도
     * - 시간 O(n log n): n개 무게를 정렬한 뒤 양끝을 한 번씩 이동한다.
     * - 공간 O(n): 원본을 보존할 무게 복사본이 필요하다.
     *
     * 자주 하는 실수
     * - 합이 너무 작을 때 무거운 쪽을 줄여 더 작은 합을 만든다.
     * - 합이 너무 클 때 가벼운 쪽을 키워 더 큰 합을 만든다.
     * - 범위 안인 쌍을 세고도 한쪽 추를 다시 사용한다.
     * - 원본 배열을 직접 정렬한다.
     */
}

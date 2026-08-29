package bridge.set.solution;

import java.util.HashSet;
import java.util.Set;

/** 체험 보드의 서로 다른 배지 채우기 문제의 정답과 풀이 설명이다. */
public final class SetSolution01 {

    private SetSolution01() {
    }

    public static int[] solve(int[] badgeCodes, int slotLimit) {
        // 같은 코드는 한 칸만 채우므로 전체 개수가 아니라 서로 다른 코드 수가 필요하다.
        // HashSet은 중복을 한 번만 남겨 보드에 놓을 수 있는 종류 수를 바로 알려 준다.

        // [1] badgeCodes의 모든 값을 HashSet에 넣는다.
        Set<Integer> uniqueCodes = new HashSet<>();
        for (int code : badgeCodes) {
            uniqueCodes.add(code);
        }

        // [2] 고유 코드 수와 slotLimit 중 작은 값을 채운 칸 수로 정한다.
        int filledSlots = Math.min(uniqueCodes.size(), slotLimit);

        // [3] slotLimit에서 채운 칸 수를 빼 빈 칸 수를 구한다.
        int emptySlots = slotLimit - filledSlots;

        // [4] 두 값을 순서대로 담은 배열을 반환한다.
        return new int[]{filledSlots, emptySlots};
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 같은 코드의 배지는 보드에 하나만 놓을 수 있다.
     * - 놓을 수 있는 배지 종류가 많아도 보드 칸 수를 넘을 수 없다.
     * - 채운 칸 수와 빈 칸 수를 모두 반환해야 한다.
     *
     * 이 개념을 선택한 이유
     * - HashSet은 같은 코드를 여러 번 넣어도 한 번만 보관한다.
     * - set.size()로 서로 다른 배지 코드의 개수를 바로 알 수 있다.
     *
     * 풀이 순서
     * 1. badgeCodes의 모든 값을 HashSet에 넣는다.
     * 2. 고유 코드 수와 slotLimit 중 작은 값을 채운 칸 수로 정한다.
     * 3. slotLimit에서 채운 칸 수를 빼 빈 칸 수를 구한다.
     * 4. 두 값을 순서대로 담은 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - badgeCodes=[10, 10, 20, 30], slotLimit=5
     * - 10을 두 번 넣어도 집합은 [10]처럼 한 번만 보관한다.
     * - 모든 값을 넣은 뒤 고유 코드 수는 3이다.
     * - 채운 칸은 min(3, 5)=3, 빈 칸은 5-3=2다.
     * - 반환: [3, 2]
     *
     * 복잡도
     * - 평균 시간 O(n): 배지 코드 n개를 HashSet에 넣는다.
     * - 공간 O(n): 최악에는 모든 코드가 달라 n개를 저장한다.
     *
     * 자주 하는 실수
     * - badgeCodes.length를 서로 다른 코드 수로 사용한다.
     * - 고유 코드 수가 slotLimit보다 큰데 모든 코드를 놓았다고 계산한다.
     * - 빈 칸 수를 고유 코드 수에서 slotLimit를 빼서 구한다.
     */
}

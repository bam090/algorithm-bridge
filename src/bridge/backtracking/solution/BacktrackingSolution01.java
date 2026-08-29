package bridge.backtracking.solution;

import java.util.ArrayList;
import java.util.List;

/** 선택 점수의 모든 합 문제 정답과 풀이 설명이다. */
public final class BacktrackingSolution01 {

    private BacktrackingSolution01() {
    }

    public static int[] solve(int[] points) {
        // 각 위치마다 점수를 선택하거나 지나가는 두 길을 모두 확인해야 한다.
        // 재귀 뒤 선택을 지우면 같은 목록으로 다른 길을 이어서 확인할 수 있다.
        int[] result = new int[1 << points.length];
        collectSums(points, 0, new ArrayList<>(), result, 0);
        return result;
    }

    private static int collectSums(
            int[] points,
            int index,
            List<Integer> selectedPoints,
            int[] result,
            int resultIndex
    ) {
        // [5] index가 points.length이면 현재 선택 목록의 합을 결과에 담는다.
        if (index == points.length) {
            int sum = 0;
            for (int point : selectedPoints) {
                sum += point;
            }
            result[resultIndex] = sum;
            return resultIndex + 1;
        }

        // [1] 현재 점수를 선택 목록에 넣는다.
        selectedPoints.add(points[index]);
        // [2] 다음 위치의 선택을 확인한다.
        int nextResultIndex = collectSums(points, index + 1, selectedPoints, result, resultIndex);
        // [3] 돌아오면 방금 넣은 점수를 지운다.
        selectedPoints.removeLast();

        // [4] 현재 점수를 선택하지 않은 채 다음 위치를 확인한다.
        return collectSums(points, index + 1, selectedPoints, result, nextResultIndex);
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 각 위치에서는 현재 점수를 선택하거나 선택하지 않는 두 가지 길이 있다.
     * - 모든 위치를 확인할 때마다 선택 목록의 합 하나가 완성된다.
     * - 결과 순서는 선택한 길을 먼저 확인하라고 정해져 있다.
     *
     * 이 개념을 선택한 이유
     * - 한 선택 뒤에 다음 위치에서도 같은 두 선택을 반복하므로 재귀로 깊이를 옮길 수 있다.
     * - 선택 목록에 값을 넣고 재귀한 뒤 지우면 같은 목록을 다음 가지에서도 다시 쓸 수 있다.
     *
     * 풀이 순서
     * 1. 현재 점수를 선택 목록에 넣는다.
     * 2. 다음 위치의 선택을 확인한다.
     * 3. 돌아오면 방금 넣은 점수를 지운다.
     * 4. 현재 점수를 선택하지 않은 채 다음 위치를 확인한다.
     * 5. index가 points.length이면 현재 선택 목록의 합을 결과에 담는다.
     *
     * 예시 데이터 흐름
     * - points=[2, 5]
     * - 2 선택 → 5 선택 → 합 7 기록
     * - 5 취소 → 합 2 기록
     * - 2 취소 → 5 선택 → 합 5 기록
     * - 5 취소 → 아무것도 선택하지 않은 합 0 기록
     * - 결과는 [7, 2, 5, 0]이다.
     *
     * 복잡도
     * - 시간 O(n × 2^n): 2^n개 선택 결과마다 최대 n개 값을 더한다.
     * - 공간 O(2^n + n): 결과 배열과 깊이 n의 재귀·선택 목록을 사용한다.
     *
     * 자주 하는 실수
     * - 첫 재귀 뒤에 선택을 지우지 않아 선택하지 않은 가지에도 값이 남는다.
     * - points가 비었을 때 결과를 빈 배열로 만들지만, 실제 선택 경우는 합 0 하나다.
     * - 같은 값이 여러 위치에 있을 때 값이 같다는 이유로 선택 경우를 합친다.
     */
}

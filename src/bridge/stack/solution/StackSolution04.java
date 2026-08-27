package bridge.stack.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/** 처음 더 높은 표지까지 가장 긴 거리 문제 정답과 풀이 설명이다. */
public final class StackSolution04 {

    private StackSolution04() {
    }

    public static int solve(int[] heights) {
        Deque<Integer> unresolvedIndexes = new ArrayDeque<>();
        int longestDistance = 0;

        for (int index = 0; index < heights.length; index++) {
            while (!unresolvedIndexes.isEmpty()
                    && heights[unresolvedIndexes.peek()] < heights[index]) {
                int previousIndex = unresolvedIndexes.pop();
                longestDistance = Math.max(longestDistance, index - previousIndex);
            }
            unresolvedIndexes.push(index);
        }

        return longestDistance;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 각 위치에는 오른쪽에서 처음 만나는 더 높은 값이 필요하다.
     * - 현재 값 하나가 이전의 낮은 여러 위치에 대한 답이 될 수 있다.
     * - 거리를 계산하려면 높이뿐 아니라 이전 인덱스를 기억해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 답을 못 찾은 인덱스를 맨 아래에서 위로 갈수록 높이가 커지지 않는 순서로 스택에 둔다.
     * - 더 높은 현재 값이 나오면 조건을 만족하는 이전 인덱스를 연속해서 꺼낼 수 있다.
     * - 각 인덱스는 한 번 들어가고 한 번만 나오므로 모든 오른쪽 값을 다시 찾지 않는다.
     *
     * 풀이 순서
     * 1. 왼쪽부터 현재 index를 확인한다.
     * 2. 현재 높이가 스택 맨 위 인덱스의 높이보다 큰 동안 인덱스를 꺼낸다.
     * 3. 현재 index와 꺼낸 인덱스의 차이로 거리를 계산해 최댓값을 갱신한다.
     * 4. 현재 index를 아직 답이 없는 위치로 스택에 넣는다.
     * 5. 끝에 남은 인덱스는 오른쪽에 더 높은 값이 없으므로 거리 계산에서 제외한다.
     *
     * 예시 데이터 흐름
     * - heights=[5, 2, 1, 4, 6]
     * - 인덱스 3의 높이 4가 1과 2의 첫 더 높은 값이 되어 거리 1과 2를 만든다.
     * - 인덱스 4의 높이 6이 4와 5의 첫 더 높은 값이 되어 거리 1과 4를 만든다.
     * - 가장 긴 거리 4를 반환한다.
     *
     * 복잡도
     * - 시간 O(n): 각 인덱스를 한 번 push하고 최대 한 번 pop한다.
     * - 공간 O(n): 더 높은 값이 없으면 모든 인덱스가 스택에 남을 수 있다.
     *
     * 자주 하는 실수
     * - 값만 저장해 두 인덱스의 차이를 계산하지 못한다.
     * - 같은 높이도 더 높은 값으로 처리한다.
     * - 각 위치마다 오른쪽 끝까지 다시 확인해 O(n²)이 된다.
     */
}

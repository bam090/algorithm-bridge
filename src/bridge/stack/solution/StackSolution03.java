package bridge.stack.solution;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/** 원형 필름 작업의 시작점별 받침대 높이 문제 정답과 풀이 설명이다. */
public final class StackSolution03 {

    private StackSolution03() {
    }

    public static int[] solve(int[] operations) {
        // 나머지 연산과 Stack을 선택한 이유:
        // 모든 시작점에서 나머지 연산으로 한 바퀴를 읽을 수 있고,
        // 최근에 올린 필름부터 치워야 하므로 시작점마다 새 Stack으로 검증한다.

        int[] requiredHeights = new int[operations.length];
        Arrays.fill(requiredHeights, -1);

        // [1] start를 0부터 마지막 인덱스까지 고른다.
        for (int start = 0; start < operations.length; start++) {
            // [2] 각 start마다 빈 스택과 최대 높이 0으로 시작한다.
            Deque<Integer> films = new ArrayDeque<>();
            int maximumHeight = 0;
            boolean valid = true;

            // [3] (start + offset) % length 위치의 기록을 정확히 length개 확인한다.
            for (int offset = 0; offset < operations.length; offset++) {
                // [4] 양수는 올리고 최대 높이를 갱신하며, 음수는 최근 번호와 맞는지 확인한다.
                int operation = operations[(start + offset) % operations.length];
                if (operation > 0) {
                    films.push(operation);
                    maximumHeight = Math.max(maximumHeight, films.size());
                    continue;
                }

                int filmNumber = -operation;
                if (films.isEmpty() || films.peek() != filmNumber) {
                    valid = false;
                    break;
                }
                films.pop();
            }

            // [5] 실패하지 않았고 스택도 비었으면 requiredHeights[start]를 최대 높이로 바꾼다.
            if (valid && films.isEmpty()) {
                requiredHeights[start] = maximumHeight;
            }
        }

        return requiredHeights;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 작업표는 원형이므로 모든 위치가 시작점 후보다.
     * - 필름은 가장 최근에 올린 것부터 치울 수 있다.
     * - 시작점마다 작업 성공 여부와 동시에 쌓인 최대 높이를 같은 인덱스에 기록해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 나머지 연산으로 원형 인덱스를 만들 수 있다.
     * - 시작점마다 새 스택을 사용하면 이전 검사의 필름이 섞이지 않는다.
     * - 스택 크기는 현재 동시에 쌓인 필름 수와 같다.
     *
     * 풀이 순서
     * 1. start를 0부터 마지막 인덱스까지 고른다.
     * 2. 각 start마다 빈 스택과 최대 높이 0으로 시작한다.
     * 3. (start + offset) % length 위치의 기록을 정확히 length개 확인한다.
     * 4. 양수는 올리고 최대 높이를 갱신하며, 음수는 최근 번호와 맞는지 확인한다.
     * 5. 실패하지 않았고 스택도 비었으면 requiredHeights[start]를 최대 높이로 바꾼다.
     *
     * 예시 데이터 흐름
     * - operations=[1, -1, 2, -2]
     * - start=0: 1을 올리고 치운 뒤 2를 올리고 치운다. 최대 높이는 1이다.
     * - start=1: 첫 기록이 -1이므로 빈 스택에서 치우려다 실패한다.
     * - start=2도 최대 높이 1로 성공한다.
     * - start=1과 3은 실패하므로 [1, -1, 1, -1]을 반환한다.
     *
     * 복잡도
     * - 시간 O(n²): 시작점 n개에서 기록 n개를 확인한다.
     * - 공간 O(n): 한 시작점에서 필름 n개가 동시에 쌓일 수 있다.
     * - n은 최대 200이므로 모든 시작점을 직접 확인할 수 있다.
     *
     * 자주 하는 실수
     * - 시작점이 바뀌어도 같은 스택을 비우지 않고 사용한다.
     * - 한 시작점에서 최대 높이가 아니라 마지막 높이만 기록한다.
     * - 기록은 맞았지만 끝에 필름이 남은 시작점을 성공으로 기록한다.
     * - 실패한 시작점의 기본값을 0으로 두어 높이 0인 성공처럼 보이게 한다.
     */
}

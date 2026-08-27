package bridge.stack.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/** 겹쳐 쌓은 상자 기록의 첫 오류 문제 정답과 풀이 설명이다. */
public final class StackSolution01 {

    private StackSolution01() {
    }

    public static int solve(int[] events) {
        Deque<Integer> stackedBoxes = new ArrayDeque<>();

        for (int index = 0; index < events.length; index++) {
            int event = events[index];
            if (event > 0) {
                stackedBoxes.push(event);
                continue;
            }

            int boxNumber = -event;
            if (stackedBoxes.isEmpty() || stackedBoxes.peek() != boxNumber) {
                return index + 1;
            }
            stackedBoxes.pop();
        }

        return stackedBoxes.isEmpty() ? 0 : events.length + 1;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 상자를 맨 위에 올리고 맨 위에서만 꺼낼 수 있다.
     * - 꺼내는 기록에서는 가장 최근에 올린 상자 번호가 필요하다.
     * - 잘못 꺼낸 순간과 모든 기록 뒤에 상자가 남은 경우를 모두 확인해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 스택은 가장 최근에 넣은 번호를 peek()로 확인하고 pop()으로 바로 꺼낼 수 있다.
     * - ArrayDeque는 Java에서 스택 동작을 구현할 때 사용하는 표준 선택이다.
     *
     * 풀이 순서
     * 1. 양수 번호를 만나면 스택에 올린다.
     * 2. 음수 번호를 만나면 스택이 비었는지 먼저 확인한다.
     * 3. 맨 위 번호가 꺼낼 번호와 다르면 현재 위치를 반환한다.
     * 4. 번호가 같으면 맨 위 번호를 꺼낸다.
     * 5. 끝에서 스택이 비면 0, 남아 있으면 events.length + 1을 반환한다.
     *
     * 예시 데이터 흐름
     * - events=[1, 2, -2, -1]
     * - 1을 올림: [1]
     * - 2를 올림: 맨 위부터 [2, 1]
     * - -2를 처리: 최근 번호 2를 꺼내 [1]
     * - -1을 처리: 최근 번호 1을 꺼내 []
     * - 오류도 남은 상자도 없으므로 0을 반환한다.
     *
     * 복잡도
     * - 시간 O(n): 기록 n개를 한 번씩 확인한다.
     * - 공간 O(n): 모든 기록이 올리는 작업이면 번호 n개가 스택에 남을 수 있다.
     *
     * 자주 하는 실수
     * - 비어 있는 스택에서 peek()나 pop()을 먼저 호출한다.
     * - 음수 기록이면 번호가 같은지 확인하지 않고 무조건 하나를 꺼낸다.
     * - 반복이 끝난 뒤 남은 상자를 확인하지 않는다.
     */
}

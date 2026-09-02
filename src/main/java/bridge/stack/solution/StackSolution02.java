package bridge.stack.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/** 거꾸로 찾은 체크포인트 경로 문제 정답과 풀이 설명이다. */
public final class StackSolution02 {

    private StackSolution02() {
    }

    public static int[] solve(int[] previousCheckpoint, int destination) {
        // Stack을 선택한 이유:
        // 이전 번호를 따라가면 목적지부터 출발점까지 거꾸로 만나므로,
        // 찾은 번호를 Stack에 넣었다가 꺼내면 출발점부터 목적지까지의 순서로 뒤집을 수 있다.

        // [1] 빈 스택을 만들고 current를 destination으로 시작한다.
        Deque<Integer> route = new ArrayDeque<>();
        int current = destination;

        // [2] current가 0이 될 때까지 반복한다.
        while (current != 0) {
            // [3] current를 스택에 넣고 previousCheckpoint[current - 1]로 이동한다.
            route.push(current);
            current = previousCheckpoint[current - 1];
        }

        // [4] 스택 크기만큼 결과 배열을 만든다.
        int[] answer = new int[route.size()];

        // [5] 스택에서 꺼낸 번호를 결과 배열 앞에서부터 저장해 반환한다.
        for (int index = 0; index < answer.length; index++) {
            answer[index] = route.pop();
        }
        return answer;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - destination에서는 바로 이전 체크포인트만 알 수 있다.
     * - 이전 번호를 따라가면 목적지에서 출발점으로 거꾸로 이동한다.
     * - 반환 순서는 출발점에서 목적지 방향이다.
     *
     * 이 개념을 선택한 이유
     * - 거꾸로 만난 번호를 스택에 넣으면 마지막에 넣은 출발점부터 꺼낼 수 있다.
     * - 경로 길이를 미리 몰라도 스택에 모두 넣은 뒤 size()로 알 수 있다.
     *
     * 풀이 순서
     * 1. 빈 스택을 만들고 current를 destination으로 시작한다.
     * 2. current가 0이 될 때까지 반복한다.
     * 3. current를 스택에 넣고 previousCheckpoint[current - 1]로 이동한다.
     * 4. 스택 크기만큼 결과 배열을 만든다.
     * 5. 스택에서 꺼낸 번호를 결과 배열 앞에서부터 저장해 반환한다.
     *
     * 예시 데이터 흐름
     * - previousCheckpoint=[0, 1, 2, 2, 4], destination=5
     * - 거꾸로 이동: 5 -> 4 -> 2 -> 1 -> 0
     * - push 뒤 맨 위부터: [1, 2, 4, 5]
     * - pop 순서로 저장: [1, 2, 4, 5]
     *
     * 복잡도
     * - 시간 O(k): 경로에 포함된 체크포인트 k개를 넣고 꺼낸다.
     * - 공간 O(k): 스택과 반환 배열에 경로 k개를 저장한다.
     *
     * 자주 하는 실수
     * - 체크포인트 번호를 그대로 배열 인덱스로 사용한다.
     * - 스택을 쓰지 않고 거꾸로 찾은 [5, 4, 2, 1]을 그대로 반환한다.
     * - previousCheckpoint를 결과 저장 공간처럼 바꾼다.
     */
}

package bridge.queue.solution;

import java.util.ArrayDeque;
import java.util.Queue;

/** 맨 앞 순번을 한 번 미루기 문제의 정답과 풀이 설명이다. */
public final class QueueSolution01 {

    private QueueSolution01() {
    }

    public static int[] solve(int[] order) {
        Queue<Integer> queue = new ArrayDeque<>(order.length);
        for (int number : order) {
            queue.offer(number);
        }

        if (!queue.isEmpty()) {
            int first = queue.poll();
            queue.offer(first);
        }

        int[] result = new int[queue.size()];
        int index = 0;
        for (int number : queue) {
            result[index] = number;
            index++;
        }
        return result;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 맨 앞 값을 꺼내 맨 뒤로 보낸다.
     * - 먼저 들어온 값을 먼저 꺼내는 자료구조가 필요하다.
     * - 원본 order를 바꾸지 말고 새 배열을 반환해야 한다.
     *
     * 이 개념을 선택한 이유
     * - Queue는 poll()로 맨 앞 값을 꺼내고 offer()로 맨 뒤에 값을 넣는다.
     * - 문제의 한 번 회전을 두 큐 동작 그대로 표현할 수 있다.
     *
     * 풀이 순서
     * 1. order의 값을 앞에서부터 큐에 넣는다.
     * 2. 큐가 비어 있지 않으면 맨 앞 값을 꺼내 맨 뒤에 넣는다.
     * 3. 회전한 큐를 앞에서부터 읽어 새 결과 배열에 담는다.
     * 4. 결과 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - 입력 order=[10, 20, 30, 40]
     * - 큐에 넣은 뒤: [10, 20, 30, 40]
     * - 10을 poll()한 뒤: [20, 30, 40]
     * - 10을 offer()한 뒤: [20, 30, 40, 10]
     * - 반환: [20, 30, 40, 10]
     *
     * 복잡도
     * - 시간 O(n): 값 n개를 큐에 넣고 결과 배열로 한 번 옮긴다.
     * - 공간 O(n): 원본과 별개인 큐와 결과 배열이 필요하다.
     *
     * 자주 하는 실수
     * - poll()로 꺼낸 값을 다시 offer()하지 않아 값 하나가 사라진다.
     * - 빈 큐에서 바로 poll()한 결과를 int에 담으려 한다.
     * - 원본 배열의 값을 직접 옮겨 원본 순서를 바꾼다.
     */
}

package bridge.queue.solution;

import java.util.ArrayDeque;
import java.util.Queue;

/** 검수 순서에서 낮은 점수 빼기 문제의 정답과 풀이 설명이다. */
public final class QueueSolution02 {

    private QueueSolution02() {
    }

    public static int[] solve(int[] scores, int[] rotations, int removalLimit) {
        Queue<Integer> queue = new ArrayDeque<>(scores.length);
        for (int score : scores) {
            queue.offer(score);
        }

        for (int rotation : rotations) {
            if (queue.isEmpty()) {
                break;
            }

            int moves = rotation % queue.size();
            for (int count = 0; count < moves; count++) {
                queue.offer(queue.poll());
            }

            if (queue.peek() <= removalLimit) {
                queue.poll();
            }
        }

        int[] result = new int[queue.size()];
        int index = 0;
        for (int score : queue) {
            result[index] = score;
            index++;
        }
        return result;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 명령마다 맨 앞 값을 맨 뒤로 정해진 횟수만큼 옮긴다.
     * - 이동이 끝난 뒤에는 맨 앞 점수만 제거 조건을 확인한다.
     * - 제거 여부에 따라 다음 명령이 시작할 맨 앞 값이 달라진다.
     *
     * 이 개념을 선택한 이유
     * - Queue의 poll()과 offer()를 이어 쓰면 앞 값을 뒤로 한 번 옮길 수 있다.
     * - 큐 길이만큼 옮기면 같은 순서로 돌아오므로 나머지만큼만 옮기면 된다.
     *
     * 풀이 순서
     * 1. scores를 큐에 순서대로 넣는다.
     * 2. 큐가 비지 않았다면 이동 횟수를 현재 큐 길이로 나눈 나머지를 구한다.
     * 3. 나머지 횟수만큼 맨 앞 값을 꺼내 맨 뒤에 넣는다.
     * 4. 맨 앞 점수가 removalLimit 이하이면 그 값만 제거한다.
     * 5. 모든 명령 뒤에 남은 값을 새 배열에 담아 반환한다.
     *
     * 예시 데이터 흐름
     * - 시작: [4, 9, 2, 7], rotations=[2, 1, 0], removalLimit=4
     * - 2번 이동: [2, 7, 4, 9], 2를 제거해 [7, 4, 9]
     * - 1번 이동: [4, 9, 7], 4를 제거해 [9, 7]
     * - 0번 이동: [9, 7], 9는 기준보다 커서 그대로 둔다.
     * - 반환: [9, 7]
     *
     * 복잡도
     * - 시간 O(n + m + r): n개를 큐에 넣고, m개 명령과 실제 이동 r번을 처리한다.
     * - r은 각 이동 횟수를 그때의 큐 길이로 나눈 나머지의 합이다.
     * - 공간 O(n): 큐와 반환 배열에 남은 값을 보관한다.
     *
     * 자주 하는 실수
     * - 점수가 기준과 같을 때 제거하지 않는다.
     * - 큐가 빈 뒤에도 queue.size()로 나머지를 계산해 0으로 나눈다.
     * - 이동할 때 poll()만 하고 offer()하지 않아 값이 사라진다.
     */
}

package bridge.queue.solution;

import java.util.ArrayDeque;
import java.util.Queue;

/** 주문의 수령 회차 정하기 문제의 정답과 풀이 설명이다. */
public final class QueueSolution03 {

    private QueueSolution03() {
    }

    public static int[] solve(int[] requestedMinutes, int[] preparationMinutes) {
        // Queue를 선택한 이유:
        // 주문 순서를 바꿀 수 없고 아직 건네지 않은 맨 앞 주문이 회차 기준을 정하므로,
        // 준비 시각을 Queue에 넣으면 앞에서부터 연속한 주문만 차례로 묶을 수 있다.

        // [1] 각 주문의 요청 시각과 준비 시간을 더해 준비 시각을 큐에 넣는다.
        Queue<Integer> readyTimes = new ArrayDeque<>(requestedMinutes.length);
        for (int i = 0; i < requestedMinutes.length; i++) {
            readyTimes.offer(requestedMinutes[i] + preparationMinutes[i]);
        }

        int[] batchNumbers = new int[requestedMinutes.length];
        int resultIndex = 0;
        int batchNumber = 1;

        while (!readyTimes.isEmpty()) {
            // [2] 맨 앞 준비 시각을 꺼내 새 수령 회차의 기준 시각으로 정한다.
            int pickupMinute = readyTimes.poll();
            batchNumbers[resultIndex] = batchNumber;
            resultIndex++;

            // [3] 뒤에 연속한 준비 시각이 기준 이하인 동안 꺼내 같은 회차 번호를 기록한다.
            while (!readyTimes.isEmpty() && readyTimes.peek() <= pickupMinute) {
                readyTimes.poll();
                batchNumbers[resultIndex] = batchNumber;
                resultIndex++;
            }

            // [4] 현재 회차가 끝나면 다음 회차 번호를 준비한다.
            batchNumber++;
        }

        // [5] 모든 주문의 회차 번호를 반환한다.
        return batchNumbers;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 준비 시각은 요청 시각과 준비 시간을 더해서 먼저 계산할 수 있다.
     * - 뒤 주문이 준비되어도 앞 주문을 건너뛰어 먼저 건넬 수 없다.
     * - 아직 건네지 않은 맨 앞 주문의 준비 시각이 이번 회차의 기준이다.
     *
     * 이 개념을 선택한 이유
     * - Queue는 주문 순서를 지키면서 아직 처리하지 않은 맨 앞 준비 시각을 바로 볼 수 있다.
     * - 앞 주문의 준비 시각 이하인 연속 주문만 같은 회차로 꺼내면 순서 장벽을 지킬 수 있다.
     *
     * 풀이 순서
     * 1. 각 주문의 요청 시각과 준비 시간을 더해 준비 시각을 큐에 넣는다.
     * 2. 맨 앞 준비 시각을 꺼내 새 수령 회차의 기준 시각으로 정한다.
     * 3. 뒤에 연속한 준비 시각이 기준 이하인 동안 꺼내 같은 회차 번호를 기록한다.
     * 4. 기준보다 늦은 맨 앞 주문이 나오면 다음 회차를 시작한다.
     * 5. 모든 주문의 회차 번호를 반환한다.
     *
     * 예시 데이터 흐름
     * - 요청 시각: [0, 2, 4, 6, 8]
     * - 준비 시간: [10, 3, 8, 2, 1]
     * - 준비 시각 큐: [10, 5, 12, 8, 9]
     * - 1회차 기준은 10이다. 10과 5를 꺼내 결과는 [1, 1, _, _, _]가 된다.
     * - 다음 맨 앞 12는 10보다 늦으므로 2회차를 시작한다.
     * - 2회차 기준 12 이하인 12, 8, 9를 꺼낸다.
     * - 반환: [1, 1, 2, 2, 2]
     *
     * 복잡도
     * - 시간 O(n): 준비 시각 n개를 각각 한 번 넣고 한 번 꺼낸다.
     * - 공간 O(n): 준비 시각 큐와 반환 배열이 필요하다.
     *
     * 자주 하는 실수
     * - 모든 준비 시각을 정렬해 원래 주문 순서를 잃는다.
     * - 뒤 주문의 준비 시각을 직전 주문이 아니라 회차를 시작한 기준 시각과 비교해야 함을 놓친다.
     * - 새 회차를 시작할 때 회차 번호를 증가시키지 않는다.
     */
}

package bridge.queue.solution;

import java.util.ArrayDeque;
import java.util.Queue;

/** 두 검사대에서 시료 꺼내기 문제의 정답과 풀이 설명이다. */
public final class QueueSolution04 {

    private QueueSolution04() {
    }

    public static int[] solve(int[] firstLine, int[] secondLine, int[] inspectionPlan) {
        Queue<Integer> firstQueue = new ArrayDeque<>(firstLine.length);
        Queue<Integer> secondQueue = new ArrayDeque<>(secondLine.length);

        for (int sampleId : firstLine) {
            firstQueue.offer(sampleId);
        }
        for (int sampleId : secondLine) {
            secondQueue.offer(sampleId);
        }

        int[] sources = new int[inspectionPlan.length];
        for (int i = 0; i < inspectionPlan.length; i++) {
            int neededId = inspectionPlan[i];
            if (!firstQueue.isEmpty() && firstQueue.peek() == neededId) {
                firstQueue.poll();
                sources[i] = 1;
            } else if (!secondQueue.isEmpty() && secondQueue.peek() == neededId) {
                secondQueue.poll();
                sources[i] = 2;
            } else {
                return new int[]{-1};
            }
        }
        return sources;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 각 검사대에서는 맨 앞 시료만 꺼낼 수 있다.
     * - 검사 계획의 순서는 바꿀 수 없다.
     * - 성공하면 가능 여부가 아니라 시료를 꺼낸 검사대 번호를 모두 반환한다.
     *
     * 이 개념을 선택한 이유
     * - Queue 두 개를 사용하면 각 검사대의 내부 순서를 그대로 지킬 수 있다.
     * - peek()로 두 맨 앞을 확인하고 일치하는 큐에서만 poll()하면 된다.
     *
     * 풀이 순서
     * 1. firstLine과 secondLine을 각각 별도의 큐에 넣는다.
     * 2. inspectionPlan의 다음 시료 번호를 확인한다.
     * 3. 첫 번째 큐의 맨 앞과 같으면 꺼내고 결과에 1을 기록한다.
     * 4. 그렇지 않고 두 번째 큐의 맨 앞과 같으면 꺼내고 결과에 2를 기록한다.
     * 5. 둘 다 아니면 new int[]{-1}을 반환하고, 계획을 모두 처리하면 출처 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - 첫 번째 큐: [11, 13, 17], 두 번째 큐: [20, 22]
     * - 계획 11: 첫 번째 큐에서 꺼내 sources[0]=1
     * - 계획 20, 22: 두 번째 큐에서 차례로 꺼내 sources[1]=2, sources[2]=2
     * - 계획 13: 첫 번째 큐에서 꺼내 sources[3]=1
     * - 반환: [1, 2, 2, 1]
     *
     * 복잡도
     * - 시간 O(a+b+p): 두 큐에 a+b개를 넣고 계획 p개를 한 번씩 확인한다.
     * - 공간 O(a+b+p): 두 큐와 출처 결과 배열이 필요하다.
     *
     * 자주 하는 실수
     * - 필요한 시료를 찾기 위해 큐 중간 값을 꺼내 순서를 깨뜨린다.
     * - 첫 번째 큐만 확인하고 두 번째 큐의 맨 앞을 확인하지 않는다.
     * - 실패했는데 지금까지 기록한 부분 결과를 반환한다.
     */
}

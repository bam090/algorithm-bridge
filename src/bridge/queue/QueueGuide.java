package bridge.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 큐는 줄을 선 순서대로 처리하는 보관함이다.
 * 먼저 들어온 값이 먼저 나가므로 FIFO라고도 부른다.
 *<p>
 * 앞: 10, 20, 30 :뒤
 * 10을 꺼내면 앞: 20, 30 :뒤가 된다.
 *<p>
 * Java에서는 필요한 동작을 Queue로 적고, 실제 보관함은 ArrayDeque로 만든다.
 * Queue는 앞에서 꺼내고 뒤에 넣는 규칙을 코드에 분명하게 보여 준다.
 */
public final class QueueGuide {

    private QueueGuide() {
    }

    public static void main(String[] args) {
        // 1. offer()는 뒤에 넣고, poll()은 앞에서 꺼낸다.
        Queue<Integer> waiting = new ArrayDeque<>();
        waiting.offer(10);
        waiting.offer(20);
        waiting.offer(30);

        System.out.println("[1] 먼저 들어온 값부터 꺼내기");
        System.out.println("처음 줄: " + waiting);
        System.out.println("꺼낸 값: " + waiting.poll());
        System.out.println("남은 줄: " + waiting);

        // 2. peek()는 맨 앞 값을 꺼내지 않고 확인한다.
        System.out.println("\n[2] 맨 앞 값 확인");
        System.out.println("맨 앞: " + waiting.peek());
        System.out.println("확인 뒤에도 같은 줄: " + waiting);

        // 3. 앞에서 꺼낸 값을 뒤에 다시 넣으면 줄이 한 칸 회전한다.
        int moved = waiting.poll();
        waiting.offer(moved);

        System.out.println("\n[3] 앞 값을 뒤로 보내기");
        System.out.println("뒤로 보낸 값: " + moved);
        System.out.println("회전한 줄: " + waiting);

        // 4. 양쪽 끝을 모두 써야 할 때만 Deque로 선언한다.
        Deque<Integer> bothEnds = new ArrayDeque<>();
        bothEnds.offerFirst(20);
        bothEnds.offerFirst(10);
        bothEnds.offerLast(30);

        System.out.println("\n[4] 양쪽 끝이 모두 필요한 경우");
        System.out.println("양쪽에서 넣은 결과: " + bothEnds);
        System.out.println("맨 뒤에서 꺼낸 값: " + bothEnds.pollLast());
    }

    /*
     * 5. Queue와 Deque를 고르는 기준
     *
     * - 뒤에 넣고 앞에서 꺼내기만 하면 Queue를 사용한다.
     * - 앞과 뒤 양쪽에서 넣거나 꺼내야 하면 Deque를 사용한다.
     * - ArrayDeque는 Queue와 Deque를 모두 구현한 Java 표준 보관함이다.
     * - 이 과정의 큐 문제는 대부분 Queue<Integer> queue = new ArrayDeque<>();로 시작한다.
     * - ArrayDeque에는 null을 넣을 수 없다.
     */

    /*
     * 6. 자주 쓰는 동작
     *
     * - queue.offer(value) : 값을 맨 뒤에 넣는다.
     * - queue.poll()       : 맨 앞 값을 꺼낸다. 비어 있으면 null을 돌려준다.
     * - queue.peek()       : 맨 앞 값을 꺼내지 않고 본다. 비어 있으면 null을 돌려준다.
     * - queue.isEmpty()    : 큐가 비었는지 확인한다.
     * - queue.size()       : 현재 들어 있는 값의 개수를 센다.
     *
     * remove()와 element()는 빈 큐에서 예외를 낸다.
     * 처음에는 poll()과 peek()를 사용하고, 꺼내기 전에 비었는지 확인하는 편이 안전하다.
     */

    /*
     * 7. 문제에서 큐를 떠올릴 단서
     *
     * - 먼저 들어온 값부터 처리해야 한다.
     * - 맨 앞 값 때문에 뒤의 값이 기다려야 한다.
     * - 앞 값을 꺼내 맨 뒤로 보내며 순서를 바꾼다.
     * - 두 줄의 맨 앞 값 중 하나만 골라야 한다.
     *
     * 중간 위치의 값을 바로 찾아야 한다면 큐보다 배열이나 맵이 더 알맞을 수 있다.
     */

    /*
     * 8. 작은 데이터가 움직이는 순서
     *
     * 처음: [10, 20, 30]
     * 1) poll()로 10을 꺼낸다. 큐는 [20, 30]이 된다.
     * 2) offer(10)으로 10을 뒤에 넣는다. 큐는 [20, 30, 10]이 된다.
     * 3) 다음 poll()은 20을 꺼낸다.
     */

    /*
     * 9. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 어떤 값이 먼저 들어왔고, 어떤 값부터 처리해야 하는가?
     * 2) 맨 앞 값을 보기만 하는가, 실제로 꺼내는가?
     * 3) 꺼낸 값을 버리는가, 맨 뒤에 다시 넣는가?
     * 4) 큐가 비었을 때 반복을 멈추거나 실패로 처리해야 하는가?
     * 5) 큐가 여러 개라면 어느 큐의 맨 앞을 고르는가?
     * 6) 입력 배열을 그대로 두어야 하는가?
     */
}

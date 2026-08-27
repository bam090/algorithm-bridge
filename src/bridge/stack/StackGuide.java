package bridge.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 스택은 값을 위에 하나씩 올리고, 가장 최근에 올린 값부터 꺼내는 보관 방법이다.
 * <p>
 * 접시를 아래에서 꺼내려면 위의 접시를 먼저 치워야 하는 모습과 같다.
 * Java에서는 오래된 Stack 클래스보다 Deque 인터페이스와 ArrayDeque 구현을 사용한다.
 */
public final class StackGuide {

    private StackGuide() {
    }

    public static void main(String[] args) {
        // 1. push -> peek -> pop 순서로 가장 최근 값을 확인한다.
        Deque<Integer> plates = new ArrayDeque<>();
        plates.push(10);
        plates.push(20);
        plates.push(30);

        System.out.println("[1] 스택의 기본 사용");
        System.out.println("맨 위 값: " + plates.peek());
        System.out.println("꺼낸 값: " + plates.pop());
        System.out.println("다음 맨 위 값: " + plates.peek());
        System.out.println("남은 개수: " + plates.size());

        // 2. 뒤에서부터 알게 된 값을 스택에 넣으면 원래 순서로 되돌릴 수 있다.
        int[] previousCheckpoint = {0, 1, 2, 2, 4};
        int current = 5;
        Deque<Integer> route = new ArrayDeque<>();

        while (current != 0) {
            route.push(current);
            current = previousCheckpoint[current - 1];
        }

        System.out.println("\n[2] 거꾸로 찾은 경로 복원");
        while (!route.isEmpty()) {
            System.out.println("다음 체크포인트: " + route.pop());
        }

        // 3. 값이 아니라 아직 답을 찾지 못한 위치를 저장할 수도 있다.
        int[] heights = {5, 2, 1, 4, 6};
        Deque<Integer> unresolvedIndexes = new ArrayDeque<>();

        System.out.println("\n[3] 처음 더 높은 값까지의 거리");
        for (int index = 0; index < heights.length; index++) {
            while (!unresolvedIndexes.isEmpty()
                    && heights[unresolvedIndexes.peek()] < heights[index]) {
                int previousIndex = unresolvedIndexes.pop();
                System.out.println(previousIndex + " -> " + index
                        + ", 거리=" + (index - previousIndex));
            }
            unresolvedIndexes.push(index);
        }
        System.out.println("끝까지 더 높은 값을 못 찾은 위치: " + unresolvedIndexes);
    }

    /*
     * 4. 문제에서 스택을 떠올릴 단서
     *
     * - 가장 최근에 넣은 값을 먼저 꺼내야 한다.
     * - 여는 작업과 닫는 작업의 순서가 맞는지 확인한다.
     * - 거꾸로 만들어지는 결과를 원래 순서로 되돌린다.
     * - 아직 답을 찾지 못한 여러 위치를 기억했다가 한꺼번에 해결한다.
     * - 여러 더미의 맨 위 값과 결과 더미의 맨 위 값을 비교한다.
     */

    /*
     * 5. 자주 쓰는 연산과 실수
     *
     * - stack.push(value) : 값을 맨 위에 올린다.
     * - stack.peek()      : 맨 위 값을 꺼내지 않고 본다.
     * - stack.pop()       : 맨 위 값을 꺼낸다.
     * - stack.isEmpty()   : 비어 있는지 확인한다.
     * - stack.size()      : 현재 값의 개수를 확인한다.
     *
     * peek()나 pop()을 호출하기 전에는 비어 있는지 먼저 확인한다.
     * ArrayDeque에는 null을 넣을 수 없다.
     * 값을 저장해야 하는지, 그 값이 있던 인덱스를 저장해야 하는지 먼저 결정한다.
     * 원형 시작점을 바꿔 검사할 때는 시작점마다 새 스택을 만들어야 한다.
     */

    /*
     * 6. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 최근 값이 다음 판단에 필요한가?
     * 2) 스택에 값과 인덱스 중 무엇을 저장해야 하는가?
     * 3) 어떤 조건에서 push하고, 어떤 조건에서 peek하거나 pop하는가?
     * 4) 비어 있는 스택에서 꺼내려는 경우를 언제 막아야 하는가?
     * 5) 반복이 끝난 뒤 스택에 남은 값은 어떤 뜻인가?
     * 6) 결과 순서는 pop되는 순서와 같은가, 반대인가?
     */
}

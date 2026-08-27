package bridge.stack.solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** 여러 카드 더미의 합 상쇄 문제 정답과 풀이 설명이다. */
public final class StackSolution05 {

    private StackSolution05() {
    }

    public static int[] solve(int[][] sourceStacks, int[] picks, int cancelSum) {
        List<Deque<Integer>> sources = new ArrayList<>(sourceStacks.length);
        for (int[] sourceStack : sourceStacks) {
            Deque<Integer> source = new ArrayDeque<>();
            for (int card : sourceStack) {
                source.push(card);
            }
            sources.add(source);
        }

        Deque<Integer> resultStack = new ArrayDeque<>();
        for (int pick : picks) {
            Deque<Integer> source = sources.get(pick - 1);
            if (source.isEmpty()) {
                continue;
            }

            int card = source.pop();
            if (!resultStack.isEmpty() && resultStack.peek() + card == cancelSum) {
                resultStack.pop();
            } else {
                resultStack.push(card);
            }
        }

        int[] answer = new int[resultStack.size()];
        for (int index = answer.length - 1; index >= 0; index--) {
            answer[index] = resultStack.pop();
        }
        return answer;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 각 행의 마지막 카드가 원본 더미의 맨 위다.
     * - 지시 하나는 선택한 원본 더미의 맨 위 카드만 꺼낸다.
     * - 꺼낸 카드는 결과 더미의 가장 최근 카드와만 상쇄될 수 있다.
     * - 반환 순서는 결과 스택에서 pop되는 순서와 반대다.
     *
     * 이 개념을 선택한 이유
     * - 원본 더미와 결과 더미 모두 맨 위 값을 반복해서 확인하고 꺼내므로 스택이 맞다.
     * - List에 각 Deque를 보관하면 지시된 더미를 번호로 바로 찾을 수 있다.
     *
     * 풀이 순서
     * 1. 각 행을 왼쪽부터 push해 행의 마지막 카드를 맨 위에 둔다.
     * 2. pick에서 1을 빼 선택할 원본 스택을 찾는다.
     * 3. 빈 원본은 건너뛰고, 아니면 맨 위 카드를 꺼낸다.
     * 4. 결과 스택의 맨 위 카드와 합이 cancelSum이면 이전 카드를 꺼낸다.
     * 5. 상쇄되지 않으면 새 카드를 결과 스택에 올린다.
     * 6. 결과 스택을 pop하며 결과 배열의 뒤에서부터 채운다.
     *
     * 예시 데이터 흐름
     * - 첫 두 지시로 -3과 3을 꺼내 합 0으로 상쇄한다.
     * - 다음 지시들로 4와 2를 결과에 올린다.
     * - -2가 2와, 이어서 -4가 4와 상쇄된다.
     * - 마지막 7만 남으므로 [7]을 반환한다.
     *
     * 복잡도
     * - 시간 O(c + p): 카드 c개로 원본 스택을 만들고 지시 p개를 한 번씩 처리한다.
     * - 공간 O(c): 원본 카드와 결과에 남을 수 있는 카드를 스택에 저장한다.
     *
     * 자주 하는 실수
     * - 행의 첫 값을 맨 위로 만들어 원본 카드 순서를 거꾸로 처리한다.
     * - 빈 원본 스택에서 pop한다.
     * - 결과 스택의 pop 순서를 그대로 반환해 아래에서 위 순서가 뒤집힌다.
     */
}

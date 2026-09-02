package bridge.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 백트래킹은 선택할 수 있는 길을 하나씩 가 본 뒤, 막히거나 확인을 마치면
 * 바로 전 선택을 지우고 다른 길을 가 보는 방법이다.
 * <p>
 * 종이에 후보를 하나 적고 다음 칸으로 넘어간 다음, 돌아오면 방금 적은 후보를
 * 지우는 장면을 떠올리면 된다. 이때 "선택 → 재귀 → 선택 취소" 순서가 한 묶음이다.
 */
public final class BacktrackingGuide {

    private BacktrackingGuide() {
    }

    public static void main(String[] args) {
        int[] numbers = {2, 4, 7};
        List<List<Integer>> pairs = new ArrayList<>();
        collectPairs(numbers, 0, new ArrayList<>(), pairs);

        System.out.println("[1] 두 수를 고르는 모든 경우");
        System.out.println(pairs);

        String[] tasks = {"A", "B", "C"};
        List<List<String>> orders = new ArrayList<>();
        collectOrders(tasks, new boolean[tasks.length], new ArrayList<>(), 2, orders);

        System.out.println("\n[2] 서로 다른 두 작업을 배치하는 모든 순서");
        System.out.println(orders);

        int[] currentAllocation = {0, 2, 1};
        int[] savedAnswer = currentAllocation.clone();
        currentAllocation[1] = 0;

        System.out.println("\n[3] 완성된 답은 복사해서 보관");
        System.out.println("탐색 중 배열: " + java.util.Arrays.toString(currentAllocation));
        System.out.println("저장한 답: " + java.util.Arrays.toString(savedAnswer));
    }

    private static void collectPairs(
            int[] numbers,
            int startIndex,
            List<Integer> selected,
            List<List<Integer>> result
    ) {
        if (selected.size() == 2) {
            result.add(List.copyOf(selected));
            return;
        }

        for (int index = startIndex; index < numbers.length; index++) {
            selected.add(numbers[index]);
            collectPairs(numbers, index + 1, selected, result);
            selected.removeLast();
        }
    }

    private static void collectOrders(
            String[] tasks,
            boolean[] used,
            List<String> selected,
            int targetLength,
            List<List<String>> result
    ) {
        if (selected.size() == targetLength) {
            result.add(List.copyOf(selected));
            return;
        }

        for (int index = 0; index < tasks.length; index++) {
            if (used[index]) {
                continue;
            }

            used[index] = true;
            selected.add(tasks[index]);
            collectOrders(tasks, used, selected, targetLength, result);
            selected.removeLast();
            used[index] = false;
        }
    }

    /*
     * 문제에서 백트래킹을 떠올릴 단서
     *
     * - 여러 후보 가운데 하나를 고르는 일을 여러 번 반복한다.
     * - 한 번 고른 후보는 같은 과정에서 다시 고를 수 없다.
     * - 고르는 순서에 따라 다음에 가능한 후보나 남은 자원이 달라진다.
     * - 모든 배치, 조합 또는 순서를 확인해야 한다.
     * - 가능한 순서 중 번호가 가장 앞선 하나를 찾아야 한다.
     * - 지금까지의 선택이 조건을 이미 어겼다면 뒤를 더 볼 필요가 없다.
     * - 완성된 여러 결과 가운데 가장 좋은 하나를 골라야 한다.
     */

    /*
     * 자주 사용하는 Java 도구와 실제 행동
     *
     * - List.add(value): 현재 선택에 후보를 하나 넣는다.
     * - List.removeLast(): 재귀가 끝난 뒤 방금 넣은 후보를 지운다.
     * - boolean[] used: 이미 고른 후보의 위치를 기억한다.
     * - startIndex: 앞에서 고른 후보를 다시 보지 않아 같은 조합의 순서만 바뀐 중복을 막는다.
     * - boolean[] occupied: 이미 사용한 열, 담당자 또는 도구를 다시 쓰지 않게 표시한다.
     * - array.clone(): 탐색 배열이 나중에 바뀌어도 완성된 최선의 답을 보존한다.
     * - 후보를 확인하는 순서는 결과의 순서에도 영향을 줄 수 있다.
     */

    /*
     * 입력 데이터가 처리되는 흐름
     *
     * 1) 현재 깊이에서 고를 수 있는 후보를 확인한다.
     * 2) 후보 하나를 현재 선택에 넣거나 사용했다고 표시한다.
     * 3) 남은 후보를 확인하도록 다음 깊이로 이동한다.
     * 4) 완성 조건이면 결과를 기록하고, 더 갈 수 없으면 바로 돌아온다.
     * 5) 돌아온 뒤 방금 넣은 값과 표시를 원래대로 되돌린다.
     * 6) 같은 깊이의 다음 후보를 확인한다.
     */

    /*
     * 가지치기
     *
     * - 현재 값이 목표를 이미 넘었고 이후 선택이 값을 줄일 수 없다면 돌아간다.
     * - 남은 후보를 모두 골라도 목표 개수에 닿지 못하면 돌아간다.
     * - 필요한 담당자나 도구를 이미 다른 선택이 사용했다면 그 후보를 건너뛴다.
     * - 가지치기는 정답이 될 수 없는 이유가 분명할 때만 사용한다.
     */

    /*
     * 자주 하는 실수와 확인 방법
     *
     * - 재귀 뒤에 선택을 지우지 않아 다음 가지에 이전 값이 남아 있지 않은지 확인한다.
     * - used 표시를 해제하지 않거나, 선택하지 않았는데 해제하지 않는지 확인한다.
     * - 조합인데 매번 0부터 확인해 순서만 다른 결과가 중복되지 않는지 확인한다.
     * - 종료 조건이 없어 배열 끝을 지나거나 재귀가 끝나지 않는지 확인한다.
     * - 합이나 곱이 int 범위를 넘을 수 있다면 long으로 계산한다.
     * - 최선의 배열을 현재 배열 참조 그대로 저장하지 않고 clone()으로 복사했는지 확인한다.
     * - 가지치기가 아직 정답이 될 수 있는 길까지 없애지 않는지 작은 입력으로 추적한다.
     */

    /*
     * 코딩 테스트 문제를 읽는 순서
     *
     * 1) 한 깊이에서 무엇을 하나 고르는가?
     * 2) 같은 후보를 다시 고를 수 있는가?
     * 3) 순서가 다른 선택을 서로 다른 결과로 세는가?
     * 4) 다음 깊이로 넘겨야 할 현재 값, 남은 자원과 사용 표시는 무엇인가?
     * 5) 언제 하나의 결과가 완성되는가?
     * 6) 어떤 조건이면 뒤를 보지 않아도 정답이 될 수 없는가?
     * 7) 재귀가 돌아온 뒤 무엇을 원래대로 되돌려야 하는가?
     * 8) 최선이 같은 결과가 여러 개라면 어느 결과를 먼저 고르는가?
     * 9) 후보 수로 계산한 최악의 탐색량을 제한 안에서 실행할 수 있는가?
     */
}

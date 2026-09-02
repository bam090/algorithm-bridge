package bridge.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 트리는 한 값 아래에 다른 값들이 가지처럼 이어지는 모양이다.
 * <p>
 * 맨 위 값은 뿌리, 바로 위에 연결된 값은 부모, 바로 아래 값은 자식이라고 부른다.
 * Java에는 모든 문제에 맞는 하나의 Tree 클래스가 없으므로 입력 모양에 맞춰 배열이나 Map을 사용한다.
 */
public final class TreeGuide {

    private TreeGuide() {
    }

    public static void main(String[] args) {
        // 1. 완전 이진 트리는 위에서 아래로, 같은 줄에서는 왼쪽부터 배열에 넣을 수 있다.
        int[] treeValues = {10, 20, 30, 40, 50, 60};

        System.out.println("[1] 배열에 담긴 트리의 자식 위치");
        for (int index = 0; index < treeValues.length; index++) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            String leftValue = leftChild < treeValues.length
                    ? String.valueOf(treeValues[leftChild]) : "없음";
            String rightValue = rightChild < treeValues.length
                    ? String.valueOf(treeValues[rightChild]) : "없음";
            System.out.println("값 " + treeValues[index]
                    + " | 왼쪽 " + leftValue + " | 오른쪽 " + rightValue);
        }

        // 재귀 호출은 먼저 인덱스가 배열 밖인지 확인해야 끝난다.
        System.out.println("끝에 있는 값의 합: " + sumLeafValues(treeValues, 0));

        // 2. 현재 값을 적는 위치를 옮기면 방문 순서가 달라진다.
        List<Integer> betweenChildren = new ArrayList<>();
        recordBetweenChildren(treeValues, 0, betweenChildren);
        System.out.println("\n[2] 왼쪽 자식 뒤, 오른쪽 자식 전 기록: " + betweenChildren);

        // 3. 번호가 1부터 시작하는 이진 트리에서는 node / 2가 부모 번호다.
        System.out.println("\n[3] 11번에서 부모를 따라가기");
        int node = 11;
        while (node >= 1) {
            System.out.println("현재 번호: " + node);
            node /= 2;
        }

        // 4. 이름으로 부모를 찾을 때는 Map에 자식 이름과 부모 이름을 연결해 둔다.
        Map<String, String> parentByName = new HashMap<>();
        parentByName.put("photo", "root");
        parentByName.put("summer", "photo");

        System.out.println("\n[4] summer에서 부모 이름 따라가기");
        String current = "summer";
        while (current != null) {
            System.out.println("현재 이름: " + current);
            current = parentByName.get(current);
        }
    }

    private static long sumLeafValues(int[] values, int index) {
        if (index >= values.length) {
            return 0L;
        }

        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        if (leftChild >= values.length && rightChild >= values.length) {
            return values[index];
        }

        return sumLeafValues(values, leftChild) + sumLeafValues(values, rightChild);
    }

    private static void recordBetweenChildren(int[] values, int index, List<Integer> order) {
        if (index >= values.length) {
            return;
        }

        recordBetweenChildren(values, index * 2 + 1, order);
        order.add(values[index]);
        recordBetweenChildren(values, index * 2 + 2, order);
    }

    /*
     * 5. 문제에서 트리를 떠올릴 단서
     *
     * - 한 값에서 아래의 여러 값으로 관계가 갈라진다.
     * - 모든 값은 뿌리까지 이어지는 부모 관계를 가진다.
     * - 왼쪽 자식과 오른쪽 자식을 같은 규칙으로 반복해서 확인한다.
     * - 두 위치에서 부모를 따라 올라가 공통 위치를 찾아야 한다.
     * - 이름으로 부모를 찾고, 한 사건의 값을 부모들에게 차례로 전달해야 한다.
     */

    /*
     * 6. 자주 하는 실수와 확인 방법
     *
     * - 0부터 시작하는 배열: 왼쪽은 2 * index + 1, 오른쪽은 2 * index + 2다.
     * - 1부터 시작하는 번호: 부모는 node / 2다.
     * - 재귀 호출은 배열 밖에 도착했을 때 바로 끝내야 한다.
     * - 현재 값을 자식보다 전, 사이, 뒤 중 언제 기록하는지 확인한다.
     * - Map에서 현재 이름의 부모를 찾을 수 없으면 뿌리에 도착한 것이므로 반복을 끝낸다.
     * - 큰 값을 여러 번 더하면 int가 넘칠 수 있으므로 long이 필요한지 확인한다.
     */

    /*
     * 7. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 뿌리, 부모와 자식은 무엇인가?
     * 2) 트리가 배열, 부모 배열, 이름 관계 중 어떤 모양으로 주어지는가?
     * 3) 현재 위치에서 자식이나 부모를 어떻게 찾는가?
     * 4) 재귀나 반복은 어느 값에서 반드시 끝나는가?
     * 5) 현재 값을 자식보다 언제 처리해야 하는가?
     * 6) 결과를 입력 순서로 돌려줘야 하는가?
     * 7) 가장 깊은 입력과 가장 큰 누적값이 Java 자료형 안에서 안전한가?
     */
}

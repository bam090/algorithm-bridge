package bridge.tree.solution;

/** 나무 끝 상자의 값 합계 문제 정답과 풀이 설명이다. */
public final class TreeSolution01 {

    private TreeSolution01() {
    }

    public static long solve(int[] treeValues) {
        // 완전 이진 트리 배열에서는 인덱스 계산만으로 두 자식의 위치를 알 수 있다.
        // 같은 확인을 두 자식에 반복하므로 재귀로 끝 노드의 값만 모으는 방법이 문제 동작과 맞다.

        return sumLeafValues(treeValues, 0);
    }

    private static long sumLeafValues(int[] treeValues, int index) {
        // [1] 현재 인덱스가 배열 길이 이상이면 0을 반환해 재귀를 끝낸다.
        if (index >= treeValues.length) {
            return 0L;
        }

        // [2] 왼쪽은 2 * index + 1, 오른쪽은 2 * index + 2로 계산한다.
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;

        // [3] 두 자식이 모두 배열 밖이면 현재 값을 long으로 반환한다.
        if (leftChild >= treeValues.length && rightChild >= treeValues.length) {
            return treeValues[index];
        }

        // [4] 자식이 있으면 왼쪽과 오른쪽 재귀 결과를 더해 반환한다.
        return sumLeafValues(treeValues, leftChild)
                + sumLeafValues(treeValues, rightChild);
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 완전 이진 트리의 값이 배열의 0번 인덱스부터 순서대로 들어 있다.
     * - 필요한 값은 모든 노드가 아니라 자식이 없는 끝 노드의 값이다.
     * - 배열 길이 밖에 도착하면 더 방문할 노드가 없다.
     *
     * 이 개념을 선택한 이유
     * - 인덱스 i에서 두 자식 인덱스를 계산하면 노드 객체 없이 트리를 따라갈 수 있다.
     * - 같은 일을 왼쪽과 오른쪽에 반복하므로 재귀 함수로 표현하기 쉽다.
     *
     * 풀이 순서
     * 1. 현재 인덱스가 배열 길이 이상이면 0을 반환해 재귀를 끝낸다.
     * 2. 왼쪽은 2 * index + 1, 오른쪽은 2 * index + 2로 계산한다.
     * 3. 두 자식이 모두 배열 밖이면 현재 값을 long으로 반환한다.
     * 4. 자식이 있으면 왼쪽과 오른쪽 재귀 결과를 더해 반환한다.
     *
     * 예시 데이터 흐름
     * - treeValues=[10, 20, 30, 40, 50, 60]
     * - 인덱스 0의 자식은 1과 2다.
     * - 인덱스 1의 자식 3과 4는 끝 노드라서 40 + 50을 반환한다.
     * - 인덱스 2의 왼쪽 자식 5는 끝 노드라서 60을 반환한다.
     * - 뿌리에서 90 + 60 = 150을 반환한다.
     *
     * 복잡도
     * - 시간 O(n): 노드 n개를 최대 한 번씩 확인한다.
     * - 추가 공간 O(log n): 완전 이진 트리의 높이만큼 재귀 호출이 쌓인다.
     * - 반환값 외에 노드 수만큼의 새 배열은 만들지 않는다.
     *
     * 자주 하는 실수
     * - 왼쪽 자식을 2 * index로 계산해 현재 노드를 다시 방문한다.
     * - 배열 밖인지 확인하지 않아 존재하지 않는 자식을 읽는다.
     * - 자식이 하나 있는 노드를 끝 노드로 잘못 판단한다.
     * - 합을 int로 계산해 큰 입력에서 값이 넘친다.
     */
}

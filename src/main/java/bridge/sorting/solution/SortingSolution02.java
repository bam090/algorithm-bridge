package bridge.sorting.solution;

/*
 * 정답 풀이: 두 기록에서 전체 순번의 값 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - 두 배열은 이미 오름차순으로 정렬되어 있다.
 * - 전체 결과가 아니라 rank번째 값 하나만 필요하다.
 * - 한쪽이 먼저 끝날 수 있다.
 *
 * 두 포인터를 선택한 이유
 * 각 배열의 아직 선택하지 않은 첫 값만 비교하면 다음으로 작은 값을 알 수 있다.
 * 선택한 쪽의 위치만 한 칸 옮기면 같은 값을 다시 확인하지 않는다.
 *
 * 풀이 순서
 * 1. first와 second의 현재 위치를 0으로 둔다.
 * 2. 한쪽 배열이 끝났으면 다른 배열에서 선택한다.
 * 3. 두 배열에 값이 남아 있으면 두 현재 값을 비교해 작은 값을 선택하고 그 배열의 위치를 한 칸 옮긴다.
 * 4. 선택 횟수가 rank가 되면 마지막으로 선택한 값을 반환한다.
 *
 * 예시 데이터 흐름
 * 1과 2를 비교해 1 선택 → first 위치 이동
 * 4와 2를 비교해 2 선택 → second 위치 이동
 * 4와 4를 비교해 first의 4 선택 → first 위치 이동
 * 8과 4를 비교해 second의 4 선택 → 네 번째 값 4 반환
 *
 * 시간 복잡도: O(rank), rank번째 값을 고르면 바로 끝낸다.
 * 공간 복잡도: O(1), 합친 배열을 만들지 않고 위치와 횟수만 기억한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - rank를 0부터 세어 한 칸 빠른 값을 반환한다.
 * - 값을 선택하고 두 배열의 위치를 모두 옮긴다.
 * - 한쪽 배열이 끝난 뒤에도 그 배열의 값을 읽으려 한다.
 * - 두 값을 빼서 비교하면 큰 정수 범위에서 넘침이 생길 수 있다.
 */
public final class SortingSolution02 {

    private SortingSolution02() {
    }

    public static int solve(int[] first, int[] second, int rank) {
        // 두 배열이 이미 정렬되어 있어 아직 고르지 않은 첫 값끼리만 비교하면 된다.
        // 두 위치를 따로 움직이면 합친 배열을 만들지 않고도 rank번째 값을 찾을 수 있다.
        // [1] first와 second의 현재 위치를 0으로 둔다.
        int firstIndex = 0;
        int secondIndex = 0;
        int selectedCount = 0;
        int selectedValue = 0;

        while (selectedCount < rank) {
            // [2] 한쪽 배열이 끝났으면 다른 배열에서 선택한다.
            // [3] 두 배열에 값이 남아 있으면 두 현재 값을 비교해 작은 값을 선택하고 그 배열의 위치를 한 칸 옮긴다.
            if (firstIndex == first.length) {
                selectedValue = second[secondIndex++];
            } else if (secondIndex == second.length) {
                selectedValue = first[firstIndex++];
            } else if (first[firstIndex] <= second[secondIndex]) {
                selectedValue = first[firstIndex++];
            } else {
                selectedValue = second[secondIndex++];
            }
            selectedCount++;
        }

        // [4] 선택 횟수가 rank가 되면 마지막으로 선택한 값을 반환한다.
        return selectedValue;
    }
}

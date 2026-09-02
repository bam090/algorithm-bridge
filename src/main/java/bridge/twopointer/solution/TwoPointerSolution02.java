package bridge.twopointer.solution;

/*
 * 정답 풀이: 목표에 가장 가까운 두 값의 합 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - values는 이미 오름차순으로 정렬되어 있다.
 * - 서로 다른 두 위치의 합을 target과 비교한다.
 * - 가장 가까운 합을 계속 기억해야 한다.
 *
 * 양끝 포인터를 선택한 이유
 * 현재 합이 작으면 왼쪽 값을 키워야 합이 커지고, 현재 합이 크면 오른쪽 값을 줄여야 합이 작아진다.
 * 움직인 반대 방향에서는 target에 더 가까워질 수 없으므로 양끝을 한 방향으로만 옮길 수 있다.
 *
 * 풀이 순서
 * 1. 왼쪽과 오른쪽을 양끝에 두고 첫 합을 가장 가까운 합으로 기억한다.
 * 2. 현재 양끝 합과 target 사이의 거리를 계산한다.
 * 3. 더 가깝거나, 거리가 같으면서 더 작은 합이면 기억한 합을 바꾼다.
 * 4. target과 같으면 바로 반환한다.
 * 5. 합이 작으면 왼쪽을 옮기고, 크면 오른쪽을 옮긴다.
 *
 * 예시 데이터 흐름
 * [-6, -1, 4, 9]에서 -6+9=3은 target 6과 거리가 3이다.
 * 합이 작아 왼쪽을 옮기면 -1+9=8이고 거리는 2이므로 8을 기억한다.
 * 합이 커 오른쪽을 옮기면 -1+4=3이므로 더 가까워지지 않아 8을 반환한다.
 *
 * 시간 복잡도: O(n), 왼쪽과 오른쪽 위치가 각각 한 방향으로만 움직인다.
 * 공간 복잡도: O(1), 두 위치와 현재 가장 가까운 합만 기억한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 합이 작은데 오른쪽을 옮겨 합을 더 작게 만든다.
 * - 합이 큰데 왼쪽을 옮겨 합을 더 크게 만든다.
 * - 같은 위치의 값을 두 번 사용하도록 left <= right까지 반복한다.
 * - 합과 target 사이의 거리를 int로 계산해 큰 값에서 넘침이 생긴다.
 * - 거리가 같을 때 더 작은 합을 선택하는 조건을 빠뜨린다.
 */
public final class TwoPointerSolution02 {

    private TwoPointerSolution02() {
    }

    public static long solve(int[] values, long target) {
        // 정렬된 양끝의 합은 왼쪽을 옮기면 커지고 오른쪽을 옮기면 작아진다.
        // target과의 비교로 한쪽을 버려도 되므로 모든 위치 쌍을 확인할 필요가 없다.

        // [1] 왼쪽과 오른쪽을 양끝에 두고 첫 합을 가장 가까운 합으로 기억한다.
        int left = 0;
        int right = values.length - 1;
        long closestSum = (long) values[left] + values[right];

        while (left < right) {
            // [2] 현재 양끝 합과 target 사이의 거리를 계산한다.
            long currentSum = (long) values[left] + values[right];
            long currentDistance = Math.abs(currentSum - target);
            long closestDistance = Math.abs(closestSum - target);

            // [3] 더 가깝거나, 거리가 같으면서 더 작은 합이면 기억한 합을 바꾼다.
            if (currentDistance < closestDistance
                    || (currentDistance == closestDistance && currentSum < closestSum)) {
                closestSum = currentSum;
            }

            // [4] target과 같으면 바로 반환한다.
            if (currentSum == target) {
                return currentSum;
            }

            // [5] 합이 작으면 왼쪽을 옮기고, 크면 오른쪽을 옮긴다.
            if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        return closestSum;
    }
}

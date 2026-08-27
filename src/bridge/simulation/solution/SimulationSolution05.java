package bridge.simulation.solution;

/*
 * 정답 풀이: 두 적재 구역의 경계 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - 경계를 한 칸 옮길 때 이동하는 짐은 하나뿐이다.
 * - 모든 경계마다 양쪽 합을 처음부터 다시 계산할 필요가 없다.
 * - 최대 100_000개의 1_000_000_000을 더하면 int 범위를 넘는다.
 *
 * 양쪽 누적합을 선택한 이유
 * 오른쪽 전체 합을 한 번 준비하면 경계를 옮길 때 한 값만 왼쪽에 더하고 오른쪽에서 뺄 수 있다.
 * 그러면 각 경계의 두 합을 O(1)에 확인할 수 있다.
 *
 * 풀이 순서
 * 1. 모든 짐의 합을 long으로 구해 rightWeight로 둔다.
 * 2. leftWeight를 0으로 시작한다.
 * 3. 마지막 짐 전까지만 앞에서부터 한 짐을 왼쪽으로 옮긴다.
 * 4. leftWeight에는 더하고 rightWeight에서는 뺀다.
 * 5. 두 합이 각 한도 이하이면 유효한 경계 수를 늘린다.
 * 6. 모든 가능한 경계를 확인한 수를 반환한다.
 *
 * 예시 데이터 흐름
 * 전체 합 10에서 첫 짐 2를 옮기면 왼쪽 2, 오른쪽 8이라 오른쪽 한도를 넘는다.
 * 다음 짐 4를 옮기면 왼쪽 6, 오른쪽 4라 두 한도를 만족한다.
 * 다음 경계는 왼쪽 9가 되어 한도를 넘으므로 결과는 1이다.
 *
 * 시간 복잡도: O(n), 전체 합과 경계 확인을 위해 배열을 각각 한 번 순회한다.
 * 공간 복잡도: O(1), 입력 길이와 관계없이 두 합과 개수만 저장한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 마지막 짐까지 왼쪽으로 옮기면 오른쪽이 빈 잘못된 경계를 센다.
 * - 오른쪽에서 값을 빼기 전에 조건을 확인하면 경계 위치가 한 칸 어긋난다.
 * - 누적합을 int에 저장하면 최대 입력에서 음수로 넘칠 수 있다.
 */
public final class SimulationSolution05 {

    private SimulationSolution05() {
    }

    public static int solve(int[] weights, long leftCapacity, long rightCapacity) {
        long rightWeight = 0;
        for (int weight : weights) {
            rightWeight += weight;
        }

        long leftWeight = 0;
        int validBoundaryCount = 0;
        for (int index = 0; index < weights.length - 1; index++) {
            leftWeight += weights[index];
            rightWeight -= weights[index];
            if (leftWeight <= leftCapacity && rightWeight <= rightCapacity) {
                validBoundaryCount++;
            }
        }

        return validBoundaryCount;
    }
}

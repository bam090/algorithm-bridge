package bridge.simulation.problem;

/*
 * 문제: 두 적재 구역의 경계 찾기
 *
 * 문제 설명
 * 한 줄로 놓인 짐의 무게가 weights에 순서대로 들어 있다.
 * 짐 사이 한 곳에 경계를 두어 왼쪽 구역과 오른쪽 구역으로 나눈다.
 * 두 구역에는 각각 짐이 하나 이상 있어야 한다.
 *
 * 왼쪽 무게 합이 leftCapacity 이하이고 오른쪽 무게 합이 rightCapacity 이하인 경계의 수를 반환한다.
 * 경계는 첫 번째 짐 뒤부터 마지막 짐 앞까지만 놓을 수 있다.
 *
 * 입력과 출력
 * - 입력: 짐 무게 배열 weights, 왼쪽 한도 leftCapacity, 오른쪽 한도 rightCapacity
 * - 출력: 두 한도를 모두 지키는 경계의 수
 *
 * 입출력 예시
 * weights = [2, 4, 3, 1], leftCapacity = 6, rightCapacity = 5
 * 결과 = 1
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 두 구역에 짐이 하나 이상 있으려면 어디까지 경계로 확인해야 하는가?
 * - 유효한 경계라고 판단하려면 두 구역의 합과 각 한도를 어떻게 비교해야 하는가?
 * - 한 경계의 계산이 끝난 뒤 다음 경계에서 다시 사용할 수 있는 정보는 무엇인가?
 * - 최대 입력의 전체 무게 합은 어떤 자료형에 안전하게 저장할 수 있는가?
 */
public final class SimulationProblem05 {

    private SimulationProblem05() {
        solve(new int[]{2, 4, 3, 1}, 6, 5);
        // 예상 출력: 1
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 오른쪽 전체 무게를 먼저 구하고, 경계를 한 칸씩 옮길 때마다 같은 무게를 왼쪽에 더하고 오른쪽에서 빼며 두 한도를 만족하는 경계를 센다.
     */
    //endregion

    /*
     * 제약 조건
     * - 0 <= weights.length <= 100_000
     * - 0 <= weights[i] <= 1_000_000_000
     * - 0 <= leftCapacity, rightCapacity <= 100_000_000_000_000
     * - 경계의 왼쪽과 오른쪽에는 각각 짐이 하나 이상 있어야 한다.
     * - weights.length가 2보다 작으면 0을 반환한다.
     * - 누적 무게는 int 범위를 넘을 수 있으므로 long으로 계산한다.
     * - weights 배열은 바꾸지 않는다.
     */
    public static int solve(int[] weights, long leftCapacity, long rightCapacity) {
        int answer = 0;
        // 여기에 직접 구현한다.
        return answer;
    }
}

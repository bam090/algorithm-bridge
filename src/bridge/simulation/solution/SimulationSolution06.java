package bridge.simulation.solution;

/*
 * 정답 풀이: 반복 작업 묶음 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - 두 수의 곱은 totalActions와 정확히 같아야 한다.
 * - 순서를 바꾼 같은 약수 쌍은 한 번만 세어야 한다.
 * - 곱 조건을 만족해도 두 수의 합 조건을 따로 확인해야 한다.
 * - 첫 쌍을 찾은 뒤에도 모든 후보를 확인해야 전체 개수를 구할 수 있다.
 *
 * 대칭 후보 축소를 선택한 이유
 * 약수 하나를 찾으면 짝은 나눗셈으로 바로 정해진다.
 * 작은 약수만 확인하면 모든 곱 조합을 중복 없이 확인할 수 있다.
 * 약수 후보 수만큼만 반복하므로 totalActions 전체를 확인하는 것보다 바르다.
 *
 * 풀이 순서
 * 1. Math.sqrt로 totalActions의 제곱근 이하 정수 경계를 구하고 정수 나눗셈으로 보정한다.
 * 2. 1부터 그 경계까지 totalActions의 약수인지 확인한다.
 * 3. 약수이면 나눗셈으로 짝이 되는 큰 수를 구한다.
 * 4. 두 수의 합이 reportInterval의 배수인지 확인한다.
 * 5. 조건을 통과할 때마다 개수를 늘리고 모든 후보를 확인한 뒤 반환한다.
 *
 * 예시 데이터 흐름
 * 36의 순서 없는 약수 쌍은 (1, 36), (2, 18), (3, 12), (4, 9), (6, 6)이다.
 * 그중 합이 5의 배수인 쌍은 (2, 18), (3, 12) 두 개이므로 2를 반환한다.
 *
 * 시간 복잡도: O(√totalActions), 1부터 제곱근까지 약수 후보를 확인한다.
 * 공간 복잡도: O(1), 후보 두 수와 개수만 사용한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 첫 통과 쌍을 찾자마자 반환하면 나머지 쌍을 세지 못한다.
 * - 두 약수의 순서를 바꾼 경우를 따로 세면 개수가 두 배가 된다.
 * - 제곱수의 같은 수 쌍을 두 번 세면 안 된다.
 * - Math.sqrt의 부동소수점 근삿값을 long으로 바꾸면 일단 정수 경계를 얻을 수 있다.
 *   그런 다음 정수 나눗셈으로 경계의 앞뒤를 비교해 부동소수점 반올림과 관계없이 정확한 바로 아래 정수로 보정한다.
 */
public final class SimulationSolution06 {

    private SimulationSolution06() {
    }

    public static int solve(long totalActions, int reportInterval) {
        long limit = (long) Math.sqrt(totalActions);
        while (limit + 1 <= totalActions / (limit + 1)) {
            limit++;
        }
        while (limit > totalActions / limit) {
            limit--;
        }

        int validPairCount = 0;
        for (long candidate = 1; candidate <= limit; candidate++) {
            if (totalActions % candidate != 0) {
                continue;
            }

            long partner = totalActions / candidate;
            if ((candidate + partner) % reportInterval == 0) {
                validPairCount++;
            }
        }

        return validPairCount;
    }
}

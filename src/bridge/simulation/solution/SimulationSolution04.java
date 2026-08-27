package bridge.simulation.solution;

/*
 * 정답 풀이: 숫자 상태 줄이기
 *
 * 문제에서 발견해야 했던 단서
 * - 현재 수를 분석해 다음 수와 0의 개수를 동시에 구해야 한다.
 * - 한 자리 수가 되면 더 변환하지 않는다.
 * - 입력은 long이지만 각 자릿값의 합과 통계는 int 범위 안이다.
 *
 * 반복 상태를 선택한 이유
 * 한 번의 변환 결과가 바로 다음 변환의 입력이다.
 * current를 갱신하면서 변환 횟수와 0의 누적 개수를 따로 기억하면 필요한 상태를 모두 잃지 않는다.
 *
 * 풀이 순서
 * 1. current가 두 자리 이상인 동안 반복한다.
 * 2. current의 마지막 자릿값을 하나씩 꺼낸다.
 * 3. 자릿값은 digitSum에 더하고 0이면 zeroDigits를 늘린다.
 * 4. 자릿값 합을 다음 current로 바꾸고 rounds를 늘린다.
 * 5. 한 자리 수가 되면 [current, rounds, zeroDigits]를 반환한다.
 *
 * 예시 데이터 흐름
 * 940의 자릿값 합은 13이고 0은 한 번 나온다.
 * 13의 자릿값 합은 4이고 0은 더 나오지 않는다.
 * 두 번 변환한 최종 결과는 [4, 2, 1]이다.
 *
 * 시간 복잡도: O(d), d는 모든 변환에서 확인한 십진 자릿수의 합이다.
 * 공간 복잡도: O(1), 입력 크기와 관계없이 같은 수의 변수만 사용한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - digitSum을 변환마다 0으로 되돌리지 않으면 이전 합이 섞인다.
 * - 이미 한 자리인 0을 한 번 변환한 것으로 세면 안 된다.
 * - 0의 개수와 변환 횟수를 같은 변수로 관리하면 두 통계가 섞인다.
 */
public final class SimulationSolution04 {

    private SimulationSolution04() {
    }

    public static int[] solve(long number) {
        long current = number;
        int rounds = 0;
        int zeroDigits = 0;

        while (current >= 10) {
            long remaining = current;
            int digitSum = 0;

            while (remaining > 0) {
                int digit = (int) (remaining % 10);
                digitSum += digit;
                if (digit == 0) {
                    zeroDigits++;
                }
                remaining /= 10;
            }

            current = digitSum;
            rounds++;
        }

        return new int[]{(int) current, rounds, zeroDigits};
    }
}

package bridge.hash.solution;

import java.util.HashMap;
import java.util.Map;

/*
 * 정답 풀이: 마지막 보정값으로 기록 다시 계산하기
 *
 * 문제에서 발견해야 했던 단서
 * - 장치 ID는 같은 장치를 계속 가리키는 이름표다.
 * - 같은 ID의 보정 계수는 뒤의 값으로 바뀐다.
 * - 마지막 계수를 알아야 앞의 기록도 계산할 수 있다.
 *
 * Map과 두 번 순회를 선택한 이유
 * Map에 ID와 계수를 put하면 같은 ID의 이전 계수가 자연스럽게 새 값으로 바뀐다.
 * 첫 순회에서 마지막 계수를 확정하고 두 번째 순회에서 원래 기록 순서대로 계산할 수 있다.
 *
 * 풀이 순서
 * 1. 모든 기록을 읽어 ID별 마지막 보정 계수를 저장한다.
 * 2. 결과 배열을 기록 수만큼 만든다.
 * 3. 기록을 처음부터 다시 읽는다.
 * 4. 각 측정값에 그 ID의 마지막 계수를 곱해 같은 위치에 저장한다.
 *
 * 예시 데이터 흐름
 * ID/계수: A/2, B/3, A/4 → 마지막 계수 {A:4, B:3}
 * A의 10 → 10 * 4 = 40
 * B의 5 → 5 * 3 = 15
 * A의 -2 → -2 * 4 = -8
 *
 * 시간 복잡도: 평균 O(n)
 * 공간 복잡도: O(n + k), k는 서로 다른 장치 ID 수
 *
 * 초보자가 실수하기 쉬운 부분
 * - 기록을 처음 읽을 때 바로 계산하면 뒤에서 바뀐 계수를 적용하지 못한다.
 * - HashMap 순서대로 결과를 만들면 원래 기록 순서를 잃는다.
 * - int끼리 먼저 곱하면 long에 담기 전에 값이 넘칠 수 있다.
 */
public final class HashSolution04 {

    private HashSolution04() {
    }

    public static long[] solve(String[] deviceIds, int[] factors, int[] readings) {
        Map<String, Integer> finalFactors = new HashMap<>();
        for (int index = 0; index < deviceIds.length; index++) {
            finalFactors.put(deviceIds[index], factors[index]);
        }

        long[] adjustedReadings = new long[deviceIds.length];
        for (int index = 0; index < deviceIds.length; index++) {
            long factor = finalFactors.get(deviceIds[index]);
            adjustedReadings[index] = factor * readings[index];
        }
        return adjustedReadings;
    }
}

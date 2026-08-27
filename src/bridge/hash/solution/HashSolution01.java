package bridge.hash.solution;

import java.util.HashSet;
import java.util.Set;

/*
 * 정답 풀이: 반대 코드 짝이 처음 완성된 위치
 *
 * 문제에서 발견해야 했던 단서
 * - 현재 값의 짝은 부호를 바꾼 하나의 값이다.
 * - 전체 배열이 아니라 현재 위치보다 앞의 값만 확인해야 한다.
 * - 가장 먼저 완성되는 순간을 찾으면 바로 끝낼 수 있다.
 *
 * Set을 선택한 이유
 * 앞에 나온 값의 존재 여부만 필요하다.
 * Set을 사용하면 이전 값 전체를 매번 다시 훑지 않고 평균 O(1)에 확인할 수 있다.
 *
 * 풀이 순서
 * 1. 이전 코드를 기억할 빈 Set을 만든다.
 * 2. 현재 코드의 반대 값이 Set에 있는지 확인한다.
 * 3. 있다면 현재 배열 인덱스에 1을 더해 반환한다.
 * 4. 없다면 현재 코드를 Set에 저장하고 다음 위치로 간다.
 * 5. 끝까지 찾지 못하면 -1을 반환한다.
 *
 * 예시 데이터 흐름
 * [7, 3, -7, 4]
 * 7: 이전 값 없음 → {7}
 * 3: -3 없음 → {7, 3}
 * -7: 반대 값 7 있음 → 현재 위치 3 반환
 *
 * 시간 복잡도: 평균 O(n)
 * 공간 복잡도: O(n)
 *
 * 초보자가 실수하기 쉬운 부분
 * - 현재 값을 먼저 저장하면 [0] 하나도 짝이 있다고 잘못 판단한다.
 * - 배열 인덱스 i를 그대로 반환하면 0부터 센 위치가 된다.
 */
public final class HashSolution01 {

    private HashSolution01() {
    }

    public static int solve(int[] codes) {
        Set<Integer> previousCodes = new HashSet<>();

        for (int index = 0; index < codes.length; index++) {
            int code = codes[index];
            if (previousCodes.contains(-code)) {
                return index + 1;
            }
            previousCodes.add(code);
        }

        return -1;
    }
}

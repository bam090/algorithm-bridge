package bridge.set.solution;

import java.util.HashSet;
import java.util.Set;

/** 순환 점검 기록의 첫 오류 문제의 정답과 풀이 설명이다. */
public final class SetSolution02 {

    private SetSolution02() {
    }

    public static int[] solve(int[] checkCodes, int inspectors, int allowedGap) {
        // 첫 중복을 찾으려면 앞에서 본 코드를 기억하면서 기록을 한 번만 확인하면 된다.
        // HashSet의 add 결과로 중복을 바로 알고, 같은 순회에서 이웃 간격도 함께 검사할 수 있다.

        // [1] 아직 본 코드가 없는 빈 HashSet을 만든다.
        Set<Integer> seenCodes = new HashSet<>();

        for (int i = 0; i < checkCodes.length; i++) {
            int code = checkCodes[i];

            // [2] 현재 코드를 집합에 넣으며 중복인지 확인한다.
            boolean repeated = !seenCodes.add(code);

            // [3] 두 번째 기록부터 현재 코드와 바로 앞 코드의 차이를 확인한다.
            boolean gapBroken = i > 0 && Math.abs(code - checkCodes[i - 1]) > allowedGap;

            if (repeated || gapBroken) {
                // [4] 처음 어긴 인덱스를 담당 번호로 바꿔 코드와 함께 반환한다.
                int inspectorNumber = i % inspectors + 1;
                return new int[]{inspectorNumber, code};
            }
        }

        // [5] 끝까지 오류가 없으면 빈 배열을 반환한다.
        return new int[]{};
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 이전에 나온 코드를 다시 사용하면 바로 오류다.
     * - 두 번째 기록부터는 바로 앞 코드와의 차이도 함께 확인해야 한다.
     * - 오류가 여러 개여도 가장 먼저 나온 기록 하나만 반환한다.
     * - 점검자는 정해진 수만큼 반복해서 차례를 맡는다.
     *
     * 이 개념을 선택한 이유
     * - HashSet의 add()는 처음 본 값이면 true, 이미 있으면 false를 반환한다.
     * - 배열을 앞에서부터 한 번만 확인하면 첫 오류에서 바로 멈출 수 있다.
     *
     * 풀이 순서
     * 1. 아직 본 코드가 없는 빈 HashSet을 만든다.
     * 2. 현재 코드를 집합에 넣으며 중복인지 확인한다.
     * 3. 두 번째 기록부터 현재 코드와 바로 앞 코드의 차이를 확인한다.
     * 4. 둘 중 하나라도 어기면 i % inspectors + 1로 담당 번호를 구해 코드와 함께 반환한다.
     * 5. 끝까지 오류가 없으면 빈 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - checkCodes=[10, 12, 14, 10], inspectors=3, allowedGap=5
     * - 10, 12, 14는 처음 나왔고 앞 코드와의 차이도 5 이하다.
     * - 인덱스 3의 10은 이미 본 코드이므로 첫 오류다.
     * - 담당 번호는 3 % 3 + 1 = 1이다.
     * - 반환: [1, 10]
     *
     * 복잡도
     * - 평균 시간 O(n): 기록 n개를 앞에서부터 한 번 확인한다.
     * - 공간 O(n): 모든 코드가 다르면 HashSet에 n개가 들어간다.
     *
     * 자주 하는 실수
     * - 첫 번째 기록에서도 존재하지 않는 앞 코드를 읽는다.
     * - 차이가 allowedGap와 같은 기록을 오류로 처리한다.
     * - 마지막 오류까지 계속 진행해 첫 오류를 덮어쓴다.
     * - i % inspectors만 반환해 점검자 번호가 0부터 시작하게 한다.
     */
}

package bridge.backtracking.problem;

/*
 * 문제 | 에너지 조절 장치 점검 순서
 *
 * 한 번씩 점검해야 하는 에너지 조절 장치가 있다.
 * i번 장치는 현재 에너지가 requiredEnergy[i] 이상일 때 점검할 수 있다.
 * 점검하면 signedEnergyChange[i]를 현재 에너지에 더한다.
 * 변화량은 충전이면 양수, 소모면 음수이며, 점검 뒤 에너지는 0 이상이어야 한다.
 *
 * 모든 장치를 한 번씩 점검할 수 있는 순서 중 사전식으로 가장 앞선 순서를 반환하라.
 * 장치 번호는 1부터 세며, 사전식 순서는 첫 번호부터 비교해 더 작은 번호가 먼저 나오는 순서다.
 * 모든 장치를 점검할 수 없으면 빈 배열을 반환한다.
 *
 * 입력과 출력
 * - 입력: 시작 에너지 initialEnergy, 장치별 requiredEnergy와 signedEnergyChange
 * - 출력: 모든 장치를 점검하는 사전식 첫 1기반 장치 번호 순서 또는 빈 배열
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 현재 에너지가 필요 에너지 이상이어도 변화량을 더한 값이 음수라면 점검할 수 있는가?
 * - 한 번 점검한 장치를 같은 순서에서 다시 고르지 않게 무엇을 기억해야 하는가?
 * - 가능한 순서 중 가장 앞선 순서를 얻으려면 후보를 어떤 번호부터 확인해야 하는가?
 * - 모든 장치를 점검한 순간 현재 순서를 왜 복사해야 하는가?
 */
public final class BacktrackingProblem03 {

    private BacktrackingProblem03() {
        solve(
                5,
                new int[]{4, 6, 2},
                new int[]{-3, 4, 4}
        );
        // 예상 출력: [1, 3, 2]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 낮은 번호부터 아직 점검하지 않은 장치를 골라 에너지를 바꿔 재귀하고, 모든 장치를 점검한 첫 순서를 복사해 반환하며 실패한 선택은 취소한다.
     */
    //endregion

    /*
     * 제약 조건
     * - requiredEnergy와 signedEnergyChange는 null이 아니다.
     * - 1 <= requiredEnergy.length <= 8
     * - requiredEnergy.length == signedEnergyChange.length
     * - 0 <= initialEnergy <= 1,000
     * - 0 <= requiredEnergy[i] <= 1,000
     * - -1,000 <= signedEnergyChange[i] <= 1,000
     * - 장치를 점검한 뒤 에너지가 0보다 작아지는 순서는 허용하지 않는다.
     * - 입력 배열을 바꾸지 않는다.
     * - 장치가 8개일 때 모든 점검 순서를 보아도 완성 순서는 최대 8!개이다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(
            int initialEnergy,
            int[] requiredEnergy,
            int[] signedEnergyChange
    ) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 낮은 인덱스부터 점검 표시, 필요 에너지와 변화 뒤 에너지를 확인한다.
     * - 모든 장치를 골랐으면 현재 1기반 순서를 복사하고, 실패해 돌아오면 점검 표시를 지운다.
     */
    //endregion
}

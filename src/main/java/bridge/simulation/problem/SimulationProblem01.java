package bridge.simulation.problem;

//region 문제: 수치 조절 기록
/*
 조절기의 현재 값은 initialValue이다.
 commands를 앞에서부터 실행하며 "UP"은 값을 1 올리고 "DOWN"은 값을 1 내린다.
 바꾼 값이 minimumValue보다 작거나 maximumValue보다 크면 그 명령은 무시한다.

 모든 명령을 처리한 뒤 최종 값과 무시한 명령 수를 순서대로 담은 새 배열을 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 시작 값 initialValue, 명령 배열 commands, 허용 최솟값 minimumValue, 허용 최댓값 maximumValue
 - 출력: [최종 값, 무시한 명령 수]
 */
//endregion

//region 입출력 예시
/*
 initialValue = 0, commands = ["UP", "UP", "DOWN"], minimumValue = -1, maximumValue = 1
 결과 = [0, 1]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - "UP"과 "DOWN"은 각각 어떤 변화량으로 바꿀 수 있는가?
 - 현재 값을 바로 바꾸기 전에 어떤 후보 값을 먼저 계산해야 하는가?
 - 후보가 범위를 벗어나면 현재 값과 무시한 명령 수는 어떻게 달라지는가?
 */
//endregion

public final class SimulationProblem01 {

    private SimulationProblem01() {
        solve(0, new String[]{"UP", "UP", "DOWN"}, -1, 1);
        // 예상 출력: [0, 1]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     명령을 변화량으로 바꾸고 후보 값을 계산한 뒤, 범위 안이면 확정하고 범위 밖이면 무시한 횟수를 늘려 최종 값과 함께 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - -1_000 <= minimumValue <= initialValue <= maximumValue <= 1_000
     - 0 <= commands.length <= 2_000
     - commands[i]는 "UP" 또는 "DOWN"이다.
     - commands 배열은 바꾸지 않고 항상 새 int 배열을 반환한다.
     */
    //endregion
    public static int[] solve(int initialValue, String[] commands, int minimumValue, int maximumValue) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - "UP"은 1, "DOWN"은 -1로 바꿀 수 있다.
     - current + change를 candidate에 먼저 저장한다.
     - candidate가 범위 안일 때만 current에 저장한다.
     */
    //endregion
}

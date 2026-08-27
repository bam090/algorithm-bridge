package bridge.simulation.problem;

//region 문제: 숫자 상태 줄이기
/*
 0 이상의 정수 number가 한 자리 수가 될 때까지 다음 변환을 반복한다.
 현재 수의 십진수 각 자릿값을 더해 다음 수를 만들고, 이번에 확인한 0의 개수를 누적한다.

 변환이 끝나면 최종 한 자리 수, 변환 횟수, 확인한 0의 총개수를 순서대로 담은 새 배열을 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 0 이상의 정수 number
 - 출력: [최종 한 자리 수, 변환 횟수, 확인한 0의 총개수]
 */
//endregion

//region 입출력 예시
/*
 number = 940
 결과 = [4, 2, 1]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 현재 수의 각 십진 자릿값을 차례로 꺼내려면 나눗셈과 나머지를 어떻게 사용할 수 있는가?
 - 한 번의 변환에서 다음 수와 0의 개수를 각각 어디에 저장할 수 있는가?
 - 변환 횟수와 0의 총개수는 언제 증가하는가?
 - 두 자리 이상인 수의 자릿값 합은 왜 원래 수보다 작아져 결국 종료되는가?
 */
//endregion

public final class SimulationProblem04 {

    private SimulationProblem04() {
        solve(940);
        // 예상 출력: [4, 2, 1]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     현재 수의 자릿값 합과 0의 개수를 구하고 두 통계를 갱신한 뒤, 자릿값 합을 다음 상태로 삼아 한 자리 수가 될 때까지 반복한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 0 <= number <= 9_999_999_999_999_999
     - number가 이미 한 자리이면 변환 횟수와 확인한 0의 개수는 0이다.
     - 반환 순서는 [최종 한 자리 수, 변환 횟수, 확인한 0의 총개수]이다.
     */
    //endregion
    public static int[] solve(long number) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - value % 10은 마지막 자릿값이고 value / 10은 마지막 자릿값을 뺀 수이다.
     - 변환 한 번마다 digitSum과 zeroCount를 새로 0에서 시작한다.
     */
    //endregion
}

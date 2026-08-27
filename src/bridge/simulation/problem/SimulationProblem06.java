package bridge.simulation.problem;

//region 문제: 반복 작업 묶음 찾기
/*
 totalActions개의 작업을 같은 크기의 여러 묶음으로 나누려고 한다.
 묶음 수와 묶음마다 처리할 작업 수를 곱하면 totalActions가 되어야 한다.
 두 수의 합이 reportInterval의 배수일 때만 진행 상황을 정확히 보고할 수 있다.

 묶음 수와 묶음당 작업 수의 순서는 구분하지 않는다.
 따라서 [2, 18]과 [18, 2]는 같은 약수 쌍으로 한 번만 세어야 한다.
 곱과 보고 간격 조건을 모두 만족하는 순서 없는 약수 쌍의 개수를 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 전체 작업 수 totalActions, 보고 간격 reportInterval
 - 출력: 조건을 만족하는 순서 없는 약수 쌍의 개수
 */
//endregion

//region 입출력 예시
/*
 totalActions = 36, reportInterval = 5
 결과 = 2
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - [2, 18]과 [18, 2]를 같은 쌍으로 볼 때 중복을 막을 기준은 무엇인가?
 - 두 수가 totalActions의 약수 쌍인지 확인하려면 어떤 관계를 검사해야 하는가?
 - 약수 쌍의 합과 reportInterval로 보고 간격 조건을 어떻게 판단할 수 있는가?
 - 조건을 만족하는 한 쌍을 찾은 뒤에도 나머지 후보를 확인해야 하는 이유는 무엇인가?
 */
//endregion

public final class SimulationProblem06 {

    private SimulationProblem06() {
        solve(36, 5);
        // 예상 출력: 2
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     순서를 바꾼 같은 쌍은 한 번만 확인하고, 곱 조건과 두 수의 합 조건을 모두 만족하는 약수 쌍을 모두 세어 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 1 <= totalActions <= 1_000_000_000_000
     - 1 <= reportInterval <= 1_000
     - 순서를 바꾼 같은 쌍은 한 번만 세고, 두 수가 같은 제곱 쌍도 한 번 세어야 한다.
     - 최대 반환값은 int 범위에 든다.
     - 조건을 만족하는 약수 쌍이 없으면 0을 반환한다.
     */
    //endregion
    public static int solve(long totalActions, int reportInterval) {
        int answer = 0;
        // 여기에 직접 구현한다.
        return answer;
    }
}

package bridge.hash.problem;

//region 문제: 마지막 보정값으로 기록 다시 계산하기
/*
 이 문제에서 연습할 것
 변하지 않는 ID별 마지막 값을 먼저 기억한 뒤, 입력을 다시 읽어 원래 순서의 결과를 만든다.

 문제 설명
 측정 기록마다 장치 ID, 그 시점에 등록된 보정 계수, 원본 측정값이 주어진다.
 같은 장치 ID가 다시 나오면 그 장치의 보정 계수가 새 값으로 바뀐다.
 모든 기록이 끝난 뒤 장치별 마지막 보정 계수를 그 장치의 모든 원본 측정값에 곱한다.
 기록 순서를 유지한 long 배열을 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 장치 ID deviceIds, 등록 계수 factors, 원본 측정값 readings
 - 출력: 장치별 마지막 계수를 적용한 기록 순서의 측정값
 */
//endregion

//region 입출력 예시
/*
 deviceIds = ["A", "B", "A"]
 factors = [2, 3, 4]
 readings = [10, 5, -2]
 결과 = [40, 15, -8]
 A의 마지막 계수는 4이고 B의 마지막 계수는 3이다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 같은 ID에 값을 다시 put하면 Map에는 어떤 값이 남는가?
 - 첫 기록을 읽는 즉시 계산하면 뒤에서 바뀌는 계수를 알 수 있는가?
 - 결과는 장치별 묶음 순서인가, 처음 기록된 순서인가?
 - 두 int를 곱한 뒤 long에 담으면 곱셈 도중의 범위를 안전하게 지킬 수 있는가?
 */
//endregion

public final class HashProblem04 {

    private HashProblem04() {
        solve(
                new String[]{"A", "B", "A"},
                new int[]{2, 3, 4},
                new int[]{10, 5, -2}
        );
        // 예상 출력: [40, 15, -8]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     ID별 마지막 보정 계수를 먼저 저장하고, 기록을 다시 읽으며 각 측정값에 마지막 계수를 곱해 원래 순서로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 0 <= deviceIds.length <= 100_000
     - deviceIds.length == factors.length == readings.length
     - 각 장치 ID는 null이 아니며 길이는 1 이상 20 이하이다.
     - 0 <= factors[i] <= 1_000_000
     - -1_000_000 <= readings[i] <= 1_000_000
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion
    public static long[] solve(String[] deviceIds, int[] factors, int[] readings) {
        long[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 첫 번째 반복에서는 Map에 ID와 계수를 put해 마지막 계수만 남긴다.
     - 두 번째 반복에서는 곱하기 전에 한 값을 long으로 바꿔 int 범위를 넘는 결과도 보존한다.
     */
    //endregion
}

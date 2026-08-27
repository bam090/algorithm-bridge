package bridge.array.onedimensional.problem;

//region 문제: 반복 점검표 통과시키기
/*
 실제 점검 코드 observations와 여러 점검표 cycles가 주어진다.
 각 점검표의 코드는 끝까지 읽으면 처음부터 다시 반복된다.
 점검표를 반복해서 읽었을 때 observations와 코드가 일치하는 위치를 센다.
 일치하는 위치가 minimumMatches개 이상인 점검표 번호를 반환하라.
 점검표 번호는 1부터 세며 입력 순서대로 반환한다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 길이가 짧은 점검표를 observations 끝까지 반복해서 읽으려면 어떤 계산이 필요한가?
 - 점검표마다 일치 개수를 따로 기억하려면 무엇을 만들 수 있는가?
 - 가장 많이 일치한 점검표가 아니라 어떤 점검표를 결과에 넣어야 하는가?
 - 결과 배열을 만들기 전에 통과한 점검표의 개수를 어떻게 알 수 있는가?
 */
//endregion

public final class ArrayProblem11 {

    private ArrayProblem11() {
        solve(
                new int[]{2, 4, 2, 4, 2, 5},
                new int[][]{{2, 4}, {2, 5, 2}, {4}},
                4
        );
        // 예상 출력: new int[]{1}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     각 점검표를 나머지 연산으로 반복해 일치 개수를 센 뒤, 기준 이상인 점검표 번호만 입력 순서대로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - observations는 null이 아니며 길이는 0 이상 1,000 이하이다.
     - cycles는 null이 아니며 점검표 개수는 1 이상 50 이하이다.
     - 각 점검표는 null이 아니며 길이는 1 이상 50 이하이다.
     - observations와 cycles의 각 코드는 -1,000 이상 1,000 이하이다.
     - minimumMatches는 0 이상 observations.length 이하이다.
     - 원본 observations와 cycles는 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] observations, int[][] cycles, int minimumMatches) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - observations의 인덱스 i에서 cycles[cycle][i % cycles[cycle].length]를 비교한다.
     - 일치 개수를 모두 구한 뒤, 기준을 통과한 개수만큼 결과 배열을 만든다.
     */
    //endregion
}

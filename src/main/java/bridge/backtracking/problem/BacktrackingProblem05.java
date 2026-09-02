package bridge.backtracking.problem;

//region 문제: 프로젝트 연습 시간 배분
/*
 pointsByHours[project][hours]는 project번 프로젝트에 hours시간을 배정했을 때 얻는 점수다.
 프로젝트별로 0시간부터 그 행의 마지막 위치만큼 배정할 수 있고, 전체 배정 시간은
 hourLimit 이하여야 한다.

 모든 프로젝트의 점수 합이 가장 큰 시간 배정 배열을 반환하라.
 최고 점수가 같으면 전체 사용 시간이 더 적은 배열을 고른다.
 점수와 사용 시간도 같으면 0번 프로젝트부터 비교해 처음 다른 위치의 시간이 더 적은 배열을 고른다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 프로젝트별 시간에 따른 점수표 pointsByHours와 전체 시간 한도 hourLimit
 - 출력: 프로젝트별 배정 시간을 담은 배열
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 한 프로젝트에 배정할 수 있는 시간 후보의 시작과 끝은 어디인가?
 - 다음 프로젝트로 이동할 때 남은 시간과 현재 점수는 어떻게 바뀌는가?
 - 모든 프로젝트의 배정을 마쳤는지 어떤 값으로 알 수 있는가?
 - 최고 점수가 같은 두 배열은 어떤 순서로 비교해야 하는가?
 - 더 좋은 배열을 찾았을 때 현재 배정 배열을 그대로 저장하면 이후 탐색에서 어떻게 되는가?
 */
//endregion

public final class BacktrackingProblem05 {

    private BacktrackingProblem05() {
        solve(
                new int[][]{{0, 4, 7}, {0, 5, 6}},
                2
        );
        // 예상 출력: [1, 1]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     한도 안에서 프로젝트별 시간을 하나씩 배분하고 완성된 점수를 계산한 뒤, 최고 점수와 동점 규칙에 맞는 배정 배열을 복사해 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - pointsByHours는 null이 아니다.
     - 1 <= pointsByHours.length <= 8
     - 1 <= pointsByHours[project].length <= 6
     - pointsByHours[project][0] == 0
     - 한 행의 점수는 시간이 늘어날수록 작아지지 않는다.
     - 0 <= pointsByHours[project][hours] <= 1,000
     - 0 <= hourLimit <= 12
     - 입력 배열을 바꾸지 않는다.
     - 시간 한도를 무시해도 완성 배열 수는 최대 6^8이므로 제한 안에서 실행할 수 있다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[][] pointsByHours, int hourLimit) {
        int[] answer = {};
        return answer;
    }
}

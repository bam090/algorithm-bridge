package bridge.hash.problem;

//region 문제: 검수자마다 맡을 최우선 프로젝트 찾기
/*
 이 문제에서 연습할 것
 대상별 고유 참여자를 모아 기준을 확인하고, 통과한 대상 중 각 참여자에게 가장 중요한 하나를 고른다.

 문제 설명
 reviewerOrder에는 결과를 받을 검수자 ID가 순서대로 주어진다.
 projectIds에는 프로젝트 ID가, priorities에는 같은 위치 프로젝트의 우선순위가 주어진다.
 우선순위 숫자가 클수록 더 중요한 프로젝트다.
 relations의 각 행은 {검수자 ID, 프로젝트 ID}인 검수 기록이다.
 같은 검수자가 같은 프로젝트를 여러 번 검수해도 한 번만 인정한다.

 서로 다른 검수자가 minimumReviewers명 이상인 프로젝트만 배정 후보가 된다.
 각 검수자는 후보 중 자신이 실제로 검수한 프로젝트 하나만 맡는다.
 우선순위가 가장 큰 프로젝트를 고르고, 우선순위도 같으면 프로젝트 ID가 사전 순으로 앞선 것을 고른다.
 맡을 프로젝트가 없으면 빈 문자열을 사용한다.
 결과는 reviewerOrder 순서의 String 배열로 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 검수자 순서 reviewerOrder, 프로젝트 projectIds, 우선순위 priorities,
 검수 관계 relations, 후보 기준 minimumReviewers
 - 출력: reviewerOrder 순서의 검수자별 최우선 프로젝트 ID, 없으면 빈 문자열
 */
//endregion

//region 입출력 예시
/*
 reviewerOrder = ["A", "B", "C"]
 projectIds = ["P", "Q", "R"]
 priorities = [3, 5, 5]
 relations = [["A", "P"], ["A", "P"], ["B", "P"],
 ["A", "Q"], ["C", "Q"], ["B", "R"], ["C", "R"]]
 minimumReviewers = 2
 결과 = ["Q", "R", "Q"]
 A의 P 검수 기록은 중복이어도 한 번만 인정하며, C는 같은 우선순위에서 Q를 고른다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 같은 검수자와 프로젝트의 중복 관계를 어떻게 한 번만 저장할 수 있는가?
 - 후보가 되려면 프로젝트마다 서로 다른 검수자가 몇 명 있어야 하는가?
 - 기준을 통과했어도 그 프로젝트를 검수하지 않은 사람에게 배정해도 되는가?
 - 우선순위가 같을 때 어떤 프로젝트 ID를 골라야 하는가?
 - 검수자별 선택을 reviewerOrder 순서에 맞추려면 ID의 위치를 어떻게 기억할 수 있는가?
 */
//endregion

public final class HashProblem06 {

    private HashProblem06() {
        solve(
                new String[]{"A", "B", "C"},
                new String[]{"P", "Q", "R"},
                new int[]{3, 5, 5},
                new String[][]{
                        {"A", "P"}, {"A", "P"}, {"B", "P"},
                        {"A", "Q"}, {"C", "Q"}, {"B", "R"}, {"C", "R"}
                },
                2
        );
        // 예상 출력: ["Q", "R", "Q"]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     프로젝트별 고유 검수자 집합을 만들고, 기준을 넘긴 프로젝트를 각 검수자의 현재 선택과 우선순위·ID 순으로 비교한 뒤, 검수자 목록 순서대로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 1 <= reviewerOrder.length <= 1_000
     - 0 <= projectIds.length <= 10_000
     - projectIds.length == priorities.length
     - 0 <= relations.length <= 100_000
     - 검수자 ID와 프로젝트 ID는 각각 서로 다르며 null이 아니고 길이는 1 이상 20 이하이다.
     - 모든 relations[i]의 길이는 2이다.
     - relations[i][0]은 reviewerOrder에 있고 relations[i][1]은 projectIds에 있다.
     - 0 <= priorities[i] <= 1_000
     - 1 <= minimumReviewers <= reviewerOrder.length
     - 같은 검수자와 프로젝트의 관계는 여러 번 나올 수 있다.
     - 입력 배열과 각 relations 행은 바꾸지 않는다.
     */
    //endregion
    public static String[] solve(
            String[] reviewerOrder,
            String[] projectIds,
            int[] priorities,
            String[][] relations,
            int minimumReviewers
    ) {
        String[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - Map<String, Set<String>>에 프로젝트별 고유 검수자를 모으면 중복 관계가 사라진다.
     - 기준을 통과한 프로젝트마다 관련 검수자의 현재 선택과 우선순위·ID를 비교한다.
     */
    //endregion
}

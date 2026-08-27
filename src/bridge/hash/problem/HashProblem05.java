package bridge.hash.problem;

/*
 * 문제: 팀별 작업 순서표 만들기
 *
 * 이 문제에서 연습할 것
 * 팀별 합계와 구성원을 함께 모은 뒤, 팀 순서와 팀 안의 순서를 서로 다른 기준으로 정한다.
 *
 * 문제 설명
 * 각 작업에는 고유한 작업 ID, 담당 팀, 예상 소요 시간, 긴급도가 있다.
 * 먼저 팀의 전체 예상 소요 시간이 짧은 순서로 팀을 정렬한다.
 * 전체 시간이 같으면 팀 이름이 사전 순으로 앞선 팀이 먼저 온다.
 *
 * 한 팀 안에서는 긴급도가 높은 작업이 먼저 온다.
 * 긴급도가 같으면 예상 시간이 짧은 작업, 그것도 같으면 작업 ID가 사전 순으로 앞선 작업이 먼저 온다.
 * 각 팀에서 앞의 perTeamLimit개 작업만 골라 팀 순서대로 이어 붙인 작업 ID 배열을 반환한다.
 * 팀의 작업이 제한보다 적으면 있는 작업만 고른다.
 *
 * 입력과 출력
 * - 입력: 작업 ID taskIds, 팀 teams, 예상 시간 minutes, 긴급도 urgency, 팀별 선택 수 perTeamLimit
 * - 출력: 모든 정렬 기준을 적용해 선택한 작업 ID 배열
 *
 * 입출력 예시
 * taskIds = ["R1", "B1", "R2", "B2", "R3"]
 * teams = ["red", "blue", "red", "blue", "red"]
 * minutes = [30, 10, 20, 15, 10]
 * urgency = [5, 3, 5, 4, 5]
 * perTeamLimit = 2
 * 결과 = ["B2", "B1", "R3", "R2"]
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 팀의 전체 시간과 팀에 속한 작업 위치를 각각 어디에 모을 수 있는가?
 * - 팀의 정렬 기준과 팀 안 작업의 정렬 기준은 어떻게 다른가?
 * - 첫 번째 기준이 같을 때 어떤 기준을 다음으로 비교해야 하는가?
 * - 작업 ID 대신 배열 위치를 묶어 두면 나머지 정보도 함께 비교할 수 있지 않은가?
 * - 한 팀의 작업 수가 perTeamLimit보다 적을 때 몇 개를 선택해야 하는가?
 */
public final class HashProblem05 {

    private HashProblem05() {
        solve(
                new String[]{"R1", "B1", "R2", "B2", "R3"},
                new String[]{"red", "blue", "red", "blue", "red"},
                new int[]{30, 10, 20, 15, 10},
                new int[]{5, 3, 5, 4, 5},
                2
        );
        // 예상 출력: ["B2", "B1", "R3", "R2"]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 팀별 전체 시간과 작업 위치를 모으고, 팀과 팀 안 작업을 각각의 기준으로 정렬한 뒤, 팀마다 제한 개수만 순서대로 담아 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - 0 <= taskIds.length <= 10_000
     * - taskIds.length == teams.length == minutes.length == urgency.length
     * - 작업 ID는 서로 다르며 null이 아니고 길이는 1 이상 20 이하이다.
     * - 팀 이름은 null이 아니며 길이는 1 이상 20 이하이다.
     * - 0 <= minutes[i] <= 1_000
     * - 0 <= urgency[i] <= 1_000
     * - 1 <= perTeamLimit <= 10
     * - 입력 배열은 바꾸지 않는다.
     */
    public static String[] solve(
            String[] taskIds,
            String[] teams,
            int[] minutes,
            int[] urgency,
            int perTeamLimit
    ) {
        String[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }
}

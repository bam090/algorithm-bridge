package bridge.hash.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 정답 풀이: 팀별 작업 순서표 만들기
 *
 * 문제에서 발견해야 했던 단서
 * - 팀 순서를 정하려면 팀별 전체 시간이 필요하다.
 * - 팀 안 순서를 정하려면 작업의 여러 값을 차례대로 비교해야 한다.
 * - 팀마다 선택할 수 있는 작업 수가 정해져 있다.
 *
 * Map과 정렬을 선택한 이유
 * Map으로 팀별 전체 시간과 팀에 속한 배열 위치를 함께 모을 수 있다.
 * 팀 목록과 팀별 작업 위치 목록을 각각 문제의 비교 기준대로 정렬할 수 있다.
 *
 * 풀이 순서
 * 1. 팀별 전체 예상 시간을 더하고 작업의 배열 위치를 모은다.
 * 2. 팀을 전체 시간 오름차순, 팀 이름 오름차순으로 정렬한다.
 * 3. 각 팀의 작업을 긴급도 내림차순, 시간 오름차순, 작업 ID 오름차순으로 정렬한다.
 * 4. 팀마다 제한 개수까지 작업 ID를 결과 목록에 담는다.
 * 5. 결과 목록을 String 배열로 바꾼다.
 *
 * 예시 데이터 흐름
 * blue 전체 시간 25, red 전체 시간 60 → blue가 먼저
 * blue: B2(긴급도 4), B1(긴급도 3) → B2, B1
 * red: 긴급도 5끼리 시간과 ID를 비교 → R3, R2를 선택
 * 최종 [B2, B1, R3, R2]
 *
 * 시간 복잡도: O(n log n + g log g), g는 팀 수
 * 공간 복잡도: O(n + g)
 *
 * 초보자가 실수하기 쉬운 부분
 * - 팀의 전체 시간을 내림차순으로 정렬하면 문제 조건과 반대가 된다.
 * - 첫 기준이 같을 때 다음 기준을 빠뜨리면 결과가 일정하지 않다.
 * - 항상 두 개를 고르면 perTeamLimit이 다른 입력을 처리하지 못한다.
 * - 입력 배열 자체를 정렬하면 서로 같은 위치에 있던 정보의 연결이 끊어진다.
 */
public final class HashSolution05 {

    private HashSolution05() {
    }

    public static String[] solve(
            String[] taskIds,
            String[] teams,
            int[] minutes,
            int[] urgency,
            int perTeamLimit
    ) {
        // 팀 순서와 팀 안의 작업 순서는 서로 다른 기준으로 정해야 한다.
        // Map으로 팀별 합계와 작업 위치를 모은 뒤 두 목록을 따로 정렬하면 입력 배열을 바꾸지 않는다.

        // [1] 팀별 전체 예상 시간을 더하고 작업의 배열 위치를 모은다.
        Map<String, Integer> totalMinutes = new HashMap<>();
        Map<String, List<Integer>> taskIndexes = new HashMap<>();

        for (int index = 0; index < taskIds.length; index++) {
            String team = teams[index];
            totalMinutes.merge(team, minutes[index], Integer::sum);
            taskIndexes.computeIfAbsent(team, ignored -> new ArrayList<>()).add(index);
        }

        // [2] 팀을 전체 시간 오름차순, 팀 이름 오름차순으로 정렬한다.
        List<String> teamOrder = new ArrayList<>(taskIndexes.keySet());
        teamOrder.sort((left, right) -> {
            int byTotalMinutes = Integer.compare(totalMinutes.get(left), totalMinutes.get(right));
            if (byTotalMinutes != 0) {
                return byTotalMinutes;
            }
            return left.compareTo(right);
        });

        List<String> selectedTaskIds = new ArrayList<>();
        for (String team : teamOrder) {
            List<Integer> indexes = taskIndexes.get(team);

            // [3] 각 팀의 작업을 긴급도 내림차순, 시간 오름차순, 작업 ID 오름차순으로 정렬한다.
            indexes.sort((left, right) -> compareTasks(left, right, taskIds, minutes, urgency));

            // [4] 팀마다 제한 개수까지 작업 ID를 결과 목록에 담는다.
            int selectedCount = Math.min(perTeamLimit, indexes.size());
            for (int index = 0; index < selectedCount; index++) {
                selectedTaskIds.add(taskIds[indexes.get(index)]);
            }
        }

        // [5] 결과 목록을 String 배열로 바꾼다.
        return selectedTaskIds.toArray(String[]::new);
    }

    private static int compareTasks(
            int left,
            int right,
            String[] taskIds,
            int[] minutes,
            int[] urgency
    ) {
        // 앞의 비교 기준이 같을 때만 다음 기준을 확인한다.
        int byUrgency = Integer.compare(urgency[right], urgency[left]);
        if (byUrgency != 0) {
            return byUrgency;
        }

        int byMinutes = Integer.compare(minutes[left], minutes[right]);
        if (byMinutes != 0) {
            return byMinutes;
        }

        return taskIds[left].compareTo(taskIds[right]);
    }
}

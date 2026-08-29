package bridge.hash.solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * 정답 풀이: 검수자마다 맡을 최우선 프로젝트 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - 같은 검수자와 프로젝트 관계는 한 번만 인정한다.
 * - 프로젝트가 후보가 되는지는 서로 다른 검수자 수로 판단한다.
 * - 후보 프로젝트는 실제로 검수한 사람에게만 되돌려 비교한다.
 * - 우선순위가 같으면 프로젝트 ID로 동점을 해결한다.
 * - 최종 결과는 reviewerOrder 순서를 따라야 한다.
 *
 * Map과 Set을 선택한 이유
 * 프로젝트를 Map의 key로 두고 검수자 Set을 값으로 두면 프로젝트별 관계를 모으면서 중복도 없앨 수 있다.
 * 검수자 ID와 결과 위치를 Map으로 연결하면 원래 순서의 선택을 바로 갱신할 수 있다.
 *
 * 풀이 순서
 * 1. 검수자 ID별 reviewerOrder 위치를 Map에 저장한다.
 * 2. 프로젝트별 고유 검수자 Set을 만든다.
 * 3. 고유 검수자 수가 기준 이상인 프로젝트만 확인한다.
 * 4. 그 프로젝트를 검수한 사람마다 현재 선택과 우선순위를 비교한다.
 * 5. 우선순위가 크거나, 같으면서 ID가 사전 순으로 앞서면 선택을 바꾼다.
 *
 * 예시 데이터 흐름
 * P의 검수자: A, A, B → Set {A, B}, 우선순위 3
 * Q의 검수자: A, C → Set {A, C}, 우선순위 5
 * R의 검수자: B, C → Set {B, C}, 우선순위 5
 * A는 Q, B는 R, C는 우선순위 동률에서 Q를 선택 → [Q, R, Q]
 *
 * 시간 복잡도: 평균 O(r + p + m + u)
 * 공간 복잡도: O(r + p + u)
 * r은 검수자 수, p는 프로젝트 수, m은 전체 검수 기록 수, u는 중복을 제거한 검수 관계 수다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 중복 기록을 그대로 세면 한 사람이 기준 인원을 여러 명처럼 채운다.
 * - 후보가 된 프로젝트를 검수하지 않은 사람에게도 배정하면 안 된다.
 * - 우선순위 동점에서 프로젝트 ID 기준을 빠뜨리면 결과가 일정하지 않다.
 * - HashMap의 순서로 반환하면 reviewerOrder와 결과 위치가 달라진다.
 */
public final class HashSolution06 {

    private HashSolution06() {
    }

    public static String[] solve(
            String[] reviewerOrder,
            String[] projectIds,
            int[] priorities,
            String[][] relations,
            int minimumReviewers
    ) {
        // 같은 검수 관계는 한 번만 인정하므로 프로젝트별 검수자를 Set으로 모아야 한다.
        // Map으로 프로젝트와 검수자 위치를 연결하면 후보를 관련 검수자에게 바로 되돌려 비교할 수 있다.

        // [1] 검수자 ID별 reviewerOrder 위치를 Map에 저장한다.
        Map<String, Integer> reviewerIndexes = new HashMap<>();
        for (int index = 0; index < reviewerOrder.length; index++) {
            reviewerIndexes.put(reviewerOrder[index], index);
        }

        // [2] 프로젝트별 고유 검수자 Set을 만든다.
        Map<String, Set<String>> reviewersByProject = new HashMap<>();
        for (String[] relation : relations) {
            reviewersByProject
                    .computeIfAbsent(relation[1], ignored -> new HashSet<>())
                    .add(relation[0]);
        }

        String[] selectedProjects = new String[reviewerOrder.length];
        Arrays.fill(selectedProjects, "");
        int[] selectedPriorities = new int[reviewerOrder.length];
        Arrays.fill(selectedPriorities, -1);

        // [3] 고유 검수자 수가 기준 이상인 프로젝트만 확인한다.
        for (int projectIndex = 0; projectIndex < projectIds.length; projectIndex++) {
            String projectId = projectIds[projectIndex];
            Set<String> reviewers = reviewersByProject.get(projectId);
            if (reviewers == null || reviewers.size() < minimumReviewers) {
                continue;
            }

            int priority = priorities[projectIndex];

            // [4] 그 프로젝트를 검수한 사람마다 현재 선택과 우선순위를 비교한다.
            for (String reviewer : reviewers) {
                int reviewerIndex = reviewerIndexes.get(reviewer);

                // [5] 우선순위가 크거나, 같으면서 ID가 사전 순으로 앞서면 선택을 바꾼다.
                if (isBetterProject(
                        projectId,
                        priority,
                        selectedProjects[reviewerIndex],
                        selectedPriorities[reviewerIndex]
                )) {
                    selectedProjects[reviewerIndex] = projectId;
                    selectedPriorities[reviewerIndex] = priority;
                }
            }
        }
        return selectedProjects;
    }

    private static boolean isBetterProject(
            String candidateId,
            int candidatePriority,
            String selectedId,
            int selectedPriority
    ) {
        // 우선순위가 먼저이고, 같은 경우에만 프로젝트 ID로 동점을 푼다.
        return candidatePriority > selectedPriority
                || candidatePriority == selectedPriority && candidateId.compareTo(selectedId) < 0;
    }
}

package bridge.greedy.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 오류 출처 묶음 고르기 문제의 정답과 풀이 설명이다. */
public final class GreedySolution05 {

    private GreedySolution05() {
    }

    public static String[] solve(String[] sourceLabels, int targetRecords, int sourceLimit) {
        // Map과 큰 묶음 우선 선택을 사용하는 이유:
        // 같은 출처는 함께 조사하므로 개수를 모으고, 큰 묶음부터 골라야 제한 안에서 가장 많이 덮는다.

        // [1] 출처별 오류 기록 수를 Map에 센다.
        Map<String, Integer> counts = new HashMap<>();
        for (String label : sourceLabels) {
            counts.merge(label, 1, Integer::sum);
        }

        // [2] 출처를 기록 수 내림차순, 이름 사전순으로 정렬한다.
        List<Map.Entry<String, Integer>> groups = new ArrayList<>(counts.entrySet());
        groups.sort((left, right) -> {
            int byCount = Integer.compare(right.getValue(), left.getValue());
            if (byCount != 0) {
                return byCount;
            }
            return left.getKey().compareTo(right.getKey());
        });

        List<String> selected = new ArrayList<>();
        int coveredRecords = 0;

        // [3] 큰 묶음부터 이름과 기록 수를 누적하며 목표에 도달하면 즉시 멈춘다.
        for (Map.Entry<String, Integer> group : groups) {
            if (coveredRecords >= targetRecords) {
                break;
            }
            if (selected.size() >= sourceLimit) {
                break;
            }
            selected.add(group.getKey());
            coveredRecords += group.getValue();
        }

        // [4] sourceLimit 안에서 성공하면 이름 목록, 실패하면 빈 배열을 반환한다.
        if (coveredRecords < targetRecords) {
            return new String[]{};
        }
        return selected.toArray(String[]::new);
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 같은 출처의 기록은 일부만 고를 수 없고 모두 함께 조사한다.
     * - 조사할 수 있는 출처 수가 정해져 있고 그 안에서 목표 기록 수에 도달해야 한다.
     * - 결과는 출처 수가 아니라 실제로 선택한 이름 목록이다.
     *
     * 이 선택이 안전한 이유
     * - 작은 묶음을 골랐는데 더 큰 묶음을 남겼다면 둘을 바꾸어도 선택한 출처 수는 같고 누적 기록 수는 줄지 않는다.
     * - 따라서 큰 묶음부터 고르는 순서는 sourceLimit 안에서 목표에 도달할 가능성을 가장 크게 남긴다.
     *
     * 풀이 순서
     * 1. 출처별 오류 기록 수를 Map에 센다.
     * 2. 출처를 기록 수 내림차순, 이름 사전순으로 정렬한다.
     * 3. 큰 묶음부터 이름과 기록 수를 누적하며 목표에 도달하면 즉시 멈춘다.
     * 4. sourceLimit 안에서 성공하면 이름 목록, 실패하면 빈 배열을 반환한다.
     *
     * 예시 데이터 흐름
     * - api는 3개, ui는 2개, db는 1개다.
     * - api를 고르면 목표 4개 중 3개를 확인한다.
     * - ui를 더 고르면 누적 5개로 목표에 도달하고 즉시 멈춘다.
     * - [api,ui]를 반환한다.
     *
     * 복잡도
     * - 시간 O(n + g log g): n개 기록을 세고 g개 출처를 정렬한다.
     * - 공간 O(g): 출처별 개수와 선택한 이름을 저장한다.
     *
     * 자주 하는 실수
     * - Set만 만들어 출처별 기록 수를 잃는다.
     * - 작은 묶음부터 골라 불필요하게 많은 출처를 선택한다.
     * - 기록 수 동점에서 이름 순서를 빠뜨린다.
     * - 목표에 도달했는데도 sourceLimit까지 출처를 계속 추가한다.
     * - 출처 수 제한에서 실패했는데 부분 선택 목록을 반환한다.
     */
}

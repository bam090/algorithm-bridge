package bridge.tree.solution;

import java.util.HashMap;
import java.util.Map;

/** 폴더 변경 영향 점수 문제 정답과 풀이 설명이다. */
public final class TreeSolution04 {

    private TreeSolution04() {
    }

    public static long[] solve(
            String[] outputOrder,
            String[][] relations,
            String[] eventNames,
            int[] eventPoints
    ) {
        Map<String, Integer> outputIndexByName = new HashMap<>();
        for (int index = 0; index < outputOrder.length; index++) {
            outputIndexByName.put(outputOrder[index], index);
        }

        Map<String, String> parentByChild = new HashMap<>();
        for (String[] relation : relations) {
            parentByChild.put(relation[0], relation[1]);
        }

        long[] totals = new long[outputOrder.length];
        for (int event = 0; event < eventNames.length; event++) {
            String current = eventNames[event];
            long points = eventPoints[event];
            while (current != null) {
                totals[outputIndexByName.get(current)] += points;
                current = parentByChild.get(current);
            }
        }
        return totals;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - outputOrder와 relations의 행 순서는 서로 맞지 않고 관계 행 자체도 섞여 있다.
     * - 부모 이름이 outputOrder에서 자식보다 뒤에 나와도 관계를 찾을 수 있어야 한다.
     * - 한 사건의 점수는 현재 폴더에서 뿌리까지 같은 값으로 더해진다.
     * - 여러 사건의 점수는 덮어쓰지 않고 누적한다.
     * - 결과 순서는 Map 순서가 아니라 outputOrder 순서다.
     *
     * 이 개념을 선택한 이유
     * - 이름과 결과 위치를 Map에 연결하면 매번 outputOrder 전체를 찾지 않아도 된다.
     * - 자식 이름과 부모 이름을 별도 Map에 연결하면 관계 행 순서에 기대지 않아도 된다.
     * - 뿌리는 자식으로 들어 있지 않으므로 부모 이름을 찾지 못했을 때 자연스럽게 멈출 수 있다.
     *
     * 풀이 순서
     * 1. outputOrder의 각 이름을 결과 인덱스와 연결한다.
     * 2. relations의 각 자식 이름을 부모 이름과 연결한다.
     * 3. 사건 이름부터 시작해 그 이름의 결과 칸에 점수를 더한다.
     * 4. 부모 이름을 찾을 수 없을 때까지 같은 동작을 반복한다.
     * 5. 모든 사건을 처리한 뒤 outputOrder와 같은 순서의 totals를 반환한다.
     *
     * 예시 데이터 흐름
     * - summer의 5점은 summer, photo, root에 각각 더해진다.
     * - work의 2점은 work와 root에 각각 더해진다.
     * - outputOrder 순서 summer, root, work, photo의 결과는 [5, 7, 2, 5]다.
     *
     * 복잡도
     * - 이름 n개, 관계 r개, 사건 e개, 최대 깊이 d에서 시간은 O(n + r + e * d)다.
     * - d는 뿌리를 포함해 한 부모 사슬에서 만나는 폴더 수이며 최대 50이다.
     * - 공간 O(n + r): 결과 위치 Map, 부모 관계 Map과 결과 배열이 필요하다.
     *
     * 자주 하는 실수
     * - outputOrder와 relations의 같은 인덱스가 서로 연결된다고 가정한다.
     * - 부모가 항상 자식보다 먼저 나온다고 가정해 아직 만들지 않은 관계를 찾는다.
     * - 현재 폴더에만 더하고 부모를 빠뜨리거나 뿌리 바로 전에 멈춘다.
     * - 같은 폴더의 이전 점수를 덮어쓴다.
     * - Map의 순서대로 결과를 만들거나 int[]를 사용해 큰 누적값이 넘친다.
     */
}

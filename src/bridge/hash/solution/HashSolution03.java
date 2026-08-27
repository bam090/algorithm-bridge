package bridge.hash.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 정답 풀이: 순서가 달라도 같은 구성의 연속 구간 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - pattern과 비교할 구간의 길이는 항상 같다.
 * - 값의 순서보다 값별 등장 횟수가 중요하다.
 * - 이웃한 두 창은 맨 앞 값 하나와 새로 들어온 값 하나만 다르다.
 *
 * Map과 슬라이딩 윈도우를 선택한 이유
 * Map은 값별 등장 횟수를 기억한다.
 * 첫 창을 만든 뒤 빠지는 값과 들어오는 값만 갱신하면 매번 창 전체를 다시 세지 않아도 된다.
 *
 * 풀이 순서
 * 1. pattern의 값별 개수를 만든다.
 * 2. stream의 첫 창 개수를 만든다.
 * 3. 두 개수표가 같으면 시작 위치를 기록한다.
 * 4. 창을 옮기며 빠지는 값은 줄이고 들어오는 값은 늘린다.
 * 5. 각 위치에서 개수표를 비교하고 모든 시작 위치를 배열로 바꾼다.
 *
 * 예시 데이터 흐름
 * pattern [1, 2, 2] → {1:1, 2:2}
 * 첫 창 [2, 1, 2] → 같은 개수이므로 시작 1 기록
 * 다음 창 [1, 2, 3] → 2를 하나 빼고 3을 넣어 불일치
 * 마지막 창 [2, 2, 1] → 다시 같은 개수이므로 시작 5 기록
 *
 * 시간 복잡도: O(n * d), d는 pattern 또는 한 창에 들어 있는 서로 다른 값의 최대 개수
 * 공간 복잡도: O(n + d), 일치한 시작 위치 목록과 결과 배열까지 포함한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 개수가 0인 key를 남기면 실제 개수가 같아도 Map.equals 결과가 false가 된다.
 * - 마지막 창을 비교하기 전에 반복을 끝낼 수 있다.
 * - 중복 값의 개수를 무시하고 Set만 비교하면 잘못된 창도 통과한다.
 */
public final class HashSolution03 {

    private HashSolution03() {
    }

    public static int[] solve(int[] stream, int[] pattern) {
        if (pattern.length > stream.length) {
            return new int[0];
        }

        Map<Integer, Integer> targetCounts = new HashMap<>();
        for (int value : pattern) {
            targetCounts.merge(value, 1, Integer::sum);
        }

        Map<Integer, Integer> windowCounts = new HashMap<>();
        for (int index = 0; index < pattern.length; index++) {
            windowCounts.merge(stream[index], 1, Integer::sum);
        }

        List<Integer> starts = new ArrayList<>();
        if (targetCounts.equals(windowCounts)) {
            starts.add(1);
        }

        for (int right = pattern.length; right < stream.length; right++) {
            int outgoing = stream[right - pattern.length];
            decreaseCount(windowCounts, outgoing);

            int incoming = stream[right];
            windowCounts.merge(incoming, 1, Integer::sum);

            if (targetCounts.equals(windowCounts)) {
                starts.add(right - pattern.length + 2);
            }
        }

        int[] answer = new int[starts.size()];
        for (int index = 0; index < starts.size(); index++) {
            answer[index] = starts.get(index);
        }
        return answer;
    }

    private static void decreaseCount(Map<Integer, Integer> counts, int value) {
        int nextCount = counts.get(value) - 1;
        if (nextCount == 0) {
            counts.remove(value);
        } else {
            counts.put(value, nextCount);
        }
    }
}

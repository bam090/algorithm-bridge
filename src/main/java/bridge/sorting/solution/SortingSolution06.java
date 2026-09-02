package bridge.sorting.solution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 정답 풀이: 그룹별 새 항목 수 보고하기
 *
 * 문제에서 발견해야 했던 단서
 * - 그룹 이름과 항목 목록은 콜론으로 나뉘고 항목끼리는 쉼표로 나뉜다.
 * - 항목 수가 같으면 그룹 이름을 다시 비교해야 한다.
 * - 현재 그룹에서 새 항목 수를 센 뒤 모든 현재 항목을 다음 그룹을 위해 기억해야 한다.
 * - 그룹은 서로 포함 관계가 아니므로 새 항목이 없거나 여러 개일 수 있다.
 *
 * 파싱, 정렬과 Set을 선택한 이유
 * 문자열을 나누면 그룹 이름과 항목 배열을 각각 비교하고 확인할 수 있다.
 * 그룹을 항목 수와 이름 기준으로 정렬하면 입력 순서와 관계없이 처리 순서를 정할 수 있다.
 * Set은 앞에서 한 번이라도 나온 항목인지 확인하면서 처음 본 항목을 바로 저장할 수 있다.
 * 그룹 이름이 서로 다르므로 항목 수 동점도 이름 비교로 해결되어 안정 정렬은 필요하지 않다.
 *
 * 풀이 순서
 * 1. 각 문자열을 콜론에서 나눠 그룹 이름과 항목 부분을 얻는다.
 * 2. 항목 부분을 쉼표에서 나눠 Group으로 저장한다.
 * 3. Group을 항목 수 오름차순, 그룹 이름 사전순으로 정렬한다.
 * 4. 작은 그룹부터 확인하며 Set에 처음 추가된 항목 수를 센다.
 * 5. "그룹이름=새항목수"를 결과 배열에 저장한다.
 *
 * 예시 데이터 흐름
 * red는 항목 1개라 먼저 처리해 apple을 처음 기억하고 red=1을 만든다.
 * 항목 2개인 blue와 green은 이름순으로 처리한다.
 * blue에서는 pear와 plum이 새 항목이므로 blue=2, green의 apple과 pear는 이미 나와 green=0이다.
 * gold에서는 kiwi만 새 항목이므로 gold=1이다.
 *
 * 시간 복잡도: O(t + n log n), t는 모든 문자열에 적힌 항목 수의 합이고 n은 그룹 수다.
 * 공간 복잡도: O(t), 파싱한 항목과 이미 본 항목을 저장한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 입력 배열의 순서대로 그룹을 처리한다.
 * - 항목 수가 같은 그룹의 이름 기준을 빠뜨린다.
 * - 새 항목 하나의 이름만 찾고 나머지 새 항목을 세지 않는다.
 * - 새 항목 하나를 찾자마자 반복을 멈춰 나머지 새 항목을 세거나 기억하지 못한다.
 * - 문제 제약에 없는 공백 제거 또는 잘못된 입력 복구 규칙을 임의로 추가한다.
 */
public final class SortingSolution06 {

    private SortingSolution06() {
    }

    public static String[] solve(String[] groupRecords) {
        // 기록 안의 그룹 이름과 항목을 먼저 나눠야 처리 순서를 비교할 수 있다.
        // 두 기준으로 정렬한 뒤 Set을 쓰면 앞에서 본 항목인지 바로 구분할 수 있다.
        Group[] groups = new Group[groupRecords.length];
        // [1] 각 문자열을 콜론에서 나눠 그룹 이름과 항목 부분을 얻는다.
        for (int index = 0; index < groupRecords.length; index++) {
            int separator = groupRecords[index].indexOf(':');
            String name = groupRecords[index].substring(0, separator);
            // [2] 항목 부분을 쉼표에서 나눠 Group으로 저장한다.
            String[] items = groupRecords[index].substring(separator + 1).split(",");
            groups[index] = new Group(name, items);
        }

        // [3] Group을 항목 수 오름차순, 그룹 이름 사전순으로 정렬한다.
        Arrays.sort(groups, (left, right) -> {
            int byItemCount = Integer.compare(left.items().length, right.items().length);
            if (byItemCount != 0) {
                return byItemCount;
            }
            return left.name().compareTo(right.name());
        });

        // [4] 작은 그룹부터 확인하며 Set에 처음 추가된 항목 수를 센다.
        Set<String> seen = new HashSet<>();
        String[] answer = new String[groups.length];
        for (int index = 0; index < groups.length; index++) {
            int newItemCount = 0;
            for (String item : groups[index].items()) {
                if (seen.add(item)) {
                    newItemCount++;
                }
            }
            // [5] "그룹이름=새항목수"를 결과 배열에 저장한다.
            answer[index] = groups[index].name() + "=" + newItemCount;
        }
        return answer;
    }

    private record Group(String name, String[] items) {
    }
}

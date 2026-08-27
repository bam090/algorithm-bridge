package bridge.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 해시는 값을 찾을 때 처음부터 끝까지 다시 훑지 않도록 이름표를 붙여 기억하는 방법이다.
 *
 * <p>{@link Map}은 이름표(key)와 값(value)을 한 쌍으로 저장한다.
 * 같은 이름표에 값을 다시 넣으면 이전 값이 새 값으로 바뀐다.</p>
 *
 * <p>{@link Set}은 어떤 값이 이미 있었는지만 기억한다.
 * 같은 값을 여러 번 넣어도 한 번만 저장된다.</p>
 */
public final class HashGuide {

    private HashGuide() {
    }

    public static void main(String[] args) {
        countWithMap();
        rememberOnceWithSet();
        removeAUsedCount();
        groupUniqueValues();
        rememberLatestValue();
    }

    private static void countWithMap() {
        String[] colors = {"red", "blue", "red"};
        Map<String, Integer> counts = new HashMap<>();

        for (String color : colors) {
            counts.merge(color, 1, Integer::sum);
        }

        System.out.println("[1] Map으로 개수 세기");
        System.out.println("red: " + counts.get("red"));
        System.out.println("blue: " + counts.get("blue"));
        System.out.println();
    }

    private static void rememberOnceWithSet() {
        Set<String> visited = new HashSet<>();

        boolean firstVisit = visited.add("A");
        boolean repeatedVisit = visited.add("A");

        System.out.println("[2] Set으로 한 번만 기억하기");
        System.out.println("첫 저장 성공: " + firstVisit);
        System.out.println("중복 저장 성공: " + repeatedVisit);
        System.out.println("저장된 값 개수: " + visited.size());
        System.out.println();
    }

    private static void removeAUsedCount() {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("apple", 2);

        String used = "apple";
        int nextCount = stock.get(used) - 1;
        if (nextCount == 0) {
            stock.remove(used);
        } else {
            stock.put(used, nextCount);
        }

        System.out.println("[3] 사용한 개수 줄이기");
        System.out.println("남은 apple: " + stock.getOrDefault("apple", 0));
        System.out.println();
    }

    private static void groupUniqueValues() {
        Map<String, Set<String>> membersByTeam = new HashMap<>();
        membersByTeam.computeIfAbsent("blue", ignored -> new HashSet<>()).add("bam");
        membersByTeam.computeIfAbsent("blue", ignored -> new HashSet<>()).add("bam");

        System.out.println("[4] 이름표마다 중복 없이 모으기");
        System.out.println("blue 팀 인원 수: " + membersByTeam.get("blue").size());
        System.out.println();
    }

    private static void rememberLatestValue() {
        Map<String, Integer> latestScore = new HashMap<>();
        latestScore.put("bam", 70);
        latestScore.put("bam", 85);

        System.out.println("[5] 같은 이름표의 최신 값 기억하기");
        System.out.println("bam의 최신 점수: " + latestScore.get("bam"));
        System.out.println();
    }

    /*
     * 해시를 떠올릴 문제의 단서
     *
     * - 같은 값이 이미 나왔는지 빠르게 확인해야 한다.
     * - 이름이나 번호별로 개수를 세어야 한다.
     * - 중복을 한 번으로 처리해야 한다.
     * - 이름표별 최신 값이나 묶음을 기억해야 한다.
     * - 고정된 길이의 연속 구간에서 맨 앞 값을 빼고 새 값을 넣는 일을 반복한다.
     */

    /*
     * 자주 사용하는 연산
     *
     * Map
     * - put(key, value): 이름표와 값을 저장한다.
     * - get(key): 이름표에 연결된 값을 가져온다.
     * - getOrDefault(key, defaultValue): 값이 없을 때 기본값을 사용한다.
     * - containsKey(key): 이름표가 있는지 확인한다.
     * - merge(key, value, rule): 기존 값과 새 값을 규칙대로 합친다.
     * - remove(key): 이름표와 값을 함께 지운다.
     *
     * Set
     * - add(value): 처음 보는 값이면 저장하고 true를 반환한다.
     * - contains(value): 값이 이미 있는지 확인한다.
     * - remove(value): 저장된 값을 지운다.
     */

    /*
     * 입력 데이터가 처리되는 흐름
     *
     * 입력 하나를 읽는다.
     * → 그 값을 이름표로 쓸지, 이름표에 연결할 값으로 쓸지 정한다.
     * → Map 또는 Set에서 이전 상태를 확인한다.
     * → 개수, 최신 값, 중복 여부를 갱신한다.
     * → 필요한 결과를 입력 순서에 맞게 만든다.
     */

    /*
     * 초보자가 자주 하는 실수
     *
     * - 개수를 세어야 하는데 Set을 사용해 중복 횟수를 잃는다.
     * - 없는 key를 get한 결과 null을 숫자처럼 사용한다.
     * - 개수가 0이 된 key를 남겨 두어 두 Map이 다르다고 판단한다.
     * - HashMap과 HashSet의 순서가 입력 순서와 같을 것이라고 생각한다.
     * - String을 ==로 비교한다. 문자열 내용은 equals로 비교한다.
     * - 배열이나 목록을 key로 쓰면서 그 내용을 나중에 바꾼다.
     */

    /*
     * 코딩 테스트 문제를 읽는 순서
     *
     * 1. 무엇을 이름표로 삼아야 하는지 찾는다.
     * 2. 필요한 것이 존재 여부인지, 개수인지, 연결된 값인지 구분한다.
     * 3. 중복을 한 번만 처리해야 하는지 확인한다.
     * 4. 결과가 입력 순서를 따라야 하는지 확인한다.
     * 5. 값을 넣기 전에 확인할지, 넣은 뒤 확인할지 정한다.
     * 6. 범위의 끝값, 빈 입력, 중복 입력으로 손으로 결과를 확인한다.
     */
}

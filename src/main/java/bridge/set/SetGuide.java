package bridge.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 집합 주제에는 서로 다른 두 도구가 있다.
 * HashSet은 "이 값을 전에 보았는가?"를 빠르게 확인하는 값 보관함이다.
 * 유니온-파인드는 "두 항목이 같은 연결 그룹인가?"를 빠르게 확인하는 방법이다.
 *<p>
 * 두 도구는 이름에 집합이 들어가지만 같은 문제에 억지로 함께 사용하지 않는다.
 * 정확히 같은 값의 중복을 다루면 HashSet을, 관계를 따라 묶인 그룹을 다루면 유니온-파인드를 떠올린다.
 */
public final class SetGuide {

    private SetGuide() {
    }

    public static void main(String[] args) {
        // 1. HashSet 갈래: 같은 값은 한 번만 보관한다.
        Set<Integer> seenCodes = new HashSet<>();
        int[] codes = {10, 20, 10, 940};

        System.out.println("[1] HashSet에 처음 본 값만 저장");
        for (int code : codes) {
            boolean firstTime = seenCodes.add(code);
            System.out.println(code + " | 처음인가: " + firstTime + " | 고유 개수: " + seenCodes.size());
        }
        System.out.println("20을 보았는가: " + seenCodes.contains(20));
        seenCodes.remove(20);
        System.out.println("20을 지운 뒤 보았는가: " + seenCodes.contains(20));

        // 2. 유니온-파인드 갈래: 연결된 항목은 하나의 대표를 공유한다.
        int[] parent = {0, 1, 2, 3, 4};
        int[] groupSize = {1, 1, 1, 1, 1};

        union(parent, groupSize, 0, 1);
        union(parent, groupSize, 2, 3);
        union(parent, groupSize, 1, 3);

        System.out.println("\n[2] 유니온-파인드로 연결 그룹 만들기");
        System.out.println("3의 대표를 찾기 전 부모: " + Arrays.toString(parent));
        System.out.println("0의 대표: " + find(parent, 0));
        System.out.println("3의 대표: " + find(parent, 3));
        System.out.println("3의 대표를 찾은 뒤 부모: " + Arrays.toString(parent));
        System.out.println("0과 3은 같은 그룹인가: " + (find(parent, 0) == find(parent, 3)));
    }

    private static int find(int[] parent, int value) {
        if (parent[value] != value) {
            parent[value] = find(parent, parent[value]);
        }
        return parent[value];
    }

    private static void union(int[] parent, int[] groupSize, int first, int second) {
        int firstRoot = find(parent, first);
        int secondRoot = find(parent, second);
        if (firstRoot == secondRoot) {
            return;
        }

        if (groupSize[firstRoot] < groupSize[secondRoot]) {
            int temporary = firstRoot;
            firstRoot = secondRoot;
            secondRoot = temporary;
        }
        parent[secondRoot] = firstRoot;
        groupSize[firstRoot] += groupSize[secondRoot];
    }

    /*
     * 3. HashSet에서 자주 쓰는 동작
     *
     * - set.add(value)      : 처음 넣은 값이면 true, 이미 있으면 false
     * - set.contains(value) : 값이 들어 있으면 true
     * - set.remove(value)   : 값이 있으면 지우고 true
     * - set.size()          : 서로 다른 값의 개수
     *
     * HashSet은 값의 출력 순서를 보장하지 않는다.
     * 입력 순서를 그대로 반환해야 하면 원본 배열을 다시 앞에서부터 확인한다.
     * 같은 값이 몇 번 나왔는지 세어야 하면 Set이 아니라 Map이나 빈도 배열이 필요하다.
     */

    /*
     * 4. 유니온-파인드가 움직이는 순서
     *
     * 1) parent[i] = i로 각 항목을 자기 그룹에서 시작한다.
     * 2) find(i)는 부모를 따라가 parent[root] == root인 최종 대표를 찾는다.
     * 3) union(a, b)는 a와 b의 최종 대표가 다를 때 한 대표를 다른 대표 아래에 연결한다.
     * 4) find()가 지나온 부모를 최종 대표에 바로 연결하면 다음 대표 찾기가 짧아진다.
     *    이것을 경로 압축이라고 한다.
     */

    /*
     * 5. 문제에서 두 도구를 구분하는 단서
     *
     * HashSet 단서
     * - 중복을 한 번만 세어야 한다.
     * - 이전에 나온 값인지 확인해야 한다.
     * - 사용한 값이나 방문한 관계를 기억해야 한다.
     *
     * 유니온-파인드 단서
     * - 연결 요청이 계속 들어온다.
     * - 직접 연결되지 않아도 연결을 따라가면 같은 그룹인지 묻는다.
     * - 연결과 같은 그룹 확인이 여러 번 섞여 나온다.
     */

    /*
     * 6. 자주 하는 실수
     *
     * - HashSet의 출력 순서를 입력 순서라고 생각한다.
     * - add()의 반환값을 쓰지 않고 contains()와 add()를 불필요하게 두 번 호출한다.
     * - 유니온-파인드에서 parent[a]와 parent[b]만 비교하고 최종 대표를 찾지 않는다.
     * - 항목 a를 항목 b에 바로 붙여 이미 연결된 전체 그룹을 끊어 놓는다.
     * - 같은 그룹을 다시 연결할 때 그룹 수나 크기를 또 바꾼다.
     */

    /*
     * 7. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 정확히 같은 값의 중복인가, 여러 관계가 만든 연결 그룹인가?
     * 2) HashSet이라면 고유 개수, 사용 이력, 입력 순서 중 무엇이 필요한가?
     * 3) 유니온-파인드라면 항목 번호 범위와 처음 그룹 수는 얼마인가?
     * 4) 연결할 때 두 항목 자체가 아니라 두 최종 대표를 찾았는가?
     * 5) 같은 그룹 확인 전에 두 최종 대표를 다시 찾았는가?
     * 6) 빈 입력, 중복 값, 자기 자신 연결과 반복 연결을 손으로 확인했는가?
     */
}

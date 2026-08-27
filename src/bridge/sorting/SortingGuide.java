package bridge.sorting;

import java.util.Arrays;

/**
 * 정렬은 여러 값을 정해 둔 순서에 맞게 다시 놓는 일이다.
 * 숫자는 작은 값부터, 작업은 우선순위가 높은 것부터 놓는 식으로 기준을 먼저 정한다.
 *
 * <p>정렬된 값은 서로 가까운 값끼리 이웃하게 된다.
 * 그래서 전체 쌍을 모두 비교하지 않고 이웃만 확인하거나, 두 정렬된 흐름을 앞에서부터 비교할 수 있다.</p>
 *
 * <p>입력을 그대로 보존해야 하면 복사본을 만든 뒤 복사본을 정렬한다.
 * Java의 {@link Arrays#sort(int[])}는 전달받은 배열 자체의 순서를 바꾼다.</p>
 */
public final class SortingGuide {

    private SortingGuide() {
    }

    public static void main(String[] args) {
        sortACopy();
        restoreFromCounts();
        sortWithTwoRules();
        compareNeighbors();
    }

    private static void sortACopy() {
        int[] original = {7, 2, 5};
        int[] sorted = Arrays.copyOf(original, original.length);
        Arrays.sort(sorted);

        System.out.println("[1] 원본을 남기고 정렬하기");
        System.out.println("원본: " + Arrays.toString(original));
        System.out.println("정렬: " + Arrays.toString(sorted));
        System.out.println();
    }

    private static void restoreFromCounts() {
        int[] values = {2, 0, 2, 1};
        int[] counts = new int[3];

        for (int value : values) {
            counts[value]++;
        }

        int[] restored = new int[values.length];
        int writeIndex = 0;
        for (int value = 0; value < counts.length; value++) {
            for (int count = 0; count < counts[value]; count++) {
                restored[writeIndex++] = value;
            }
        }

        System.out.println("[2] 값의 범위가 작을 때 개수로 정렬하기");
        System.out.println("개수: " + Arrays.toString(counts));
        System.out.println("복원: " + Arrays.toString(restored));
        System.out.println();
    }

    private static void sortWithTwoRules() {
        Task[] tasks = {
                new Task("A", 2, 30),
                new Task("B", 3, 40),
                new Task("C", 3, 20)
        };

        Arrays.sort(tasks, (left, right) -> {
            int byPriority = Integer.compare(right.priority(), left.priority());
            if (byPriority != 0) {
                return byPriority;
            }
            return Integer.compare(left.minutes(), right.minutes());
        });

        System.out.println("[3] 첫 기준이 같으면 다음 기준 비교하기");
        System.out.println(Arrays.toString(tasks));
        System.out.println();
    }

    private static void compareNeighbors() {
        int[] times = {40, 5, 17, 20};
        Arrays.sort(times);

        int minimumGap = Integer.MAX_VALUE;
        for (int index = 1; index < times.length; index++) {
            minimumGap = Math.min(minimumGap, times[index] - times[index - 1]);
        }

        System.out.println("[4] 정렬한 뒤 이웃만 비교하기");
        System.out.println("정렬: " + Arrays.toString(times));
        System.out.println("가장 작은 간격: " + minimumGap);
    }

    private record Task(String id, int priority, int minutes) {
    }

    /*
     * 정렬을 떠올릴 문제의 단서
     *
     * - 작은 값부터 또는 큰 값부터 처리하라고 한다.
     * - 첫 번째 기준이 같을 때 두 번째 기준을 적용해야 한다.
     * - 두 입력이 이미 정렬되어 있다.
     * - 가장 가까운 값이나 이웃 관계를 찾아야 한다.
     * - 값의 종류가 작고 범위가 분명해 개수만 세어도 순서를 복원할 수 있다.
     * - 정렬한 결과를 그대로 내는 대신 그 결과로 다른 값을 계산해야 한다.
     */

    /*
     * 자주 사용하는 Java 기능
     *
     * - Arrays.copyOf(array, length): 원본과 다른 배열을 만든다.
     * - Arrays.copyOfRange(array, from, to): from부터 to 바로 전까지 복사한다.
     * - Arrays.sort(intArray): 숫자 배열 자체를 오름차순으로 바꾼다.
     * - Arrays.sort(objectArray, comparator): 비교 규칙에 따라 객체 배열을 정렬한다.
     * - Integer.compare(left, right): 뺄셈의 정수 넘침 없이 두 숫자를 비교한다.
     */

    /*
     * 데이터가 처리되는 흐름
     *
     * 문제에서 순서 기준을 찾는다.
     * → 원본을 바꿔도 되는지 확인한다.
     * → 필요하면 복사본이나 빈도 배열을 만든다.
     * → 첫 기준과 동점 기준을 빠짐없이 적용한다.
     * → 정렬 결과에서 필요한 값, 통계 또는 관계를 만들어 반환한다.
     */

    /*
     * 초보자가 자주 하는 실수
     *
     * - 원본 보존 조건을 확인하지 않고 입력 배열을 바로 정렬한다.
     * - 첫 기준만 비교해 동점일 때의 순서가 달라진다.
     * - Comparator에서 right - left처럼 빼서 정수 넘침을 만든다.
     * - 두 정렬된 흐름 중 한쪽이 끝난 뒤 남은 값을 처리하지 않는다.
     * - copyOfRange의 마지막 위치가 포함되지 않는다는 점을 놓친다.
     * - 정렬하면 관련 값이 이웃한다는 사실을 쓰지 않고 모든 쌍을 비교한다.
     */

    /*
     * 코딩 테스트 문제를 읽는 순서
     *
     * 1. 결과가 어떤 순서여야 하는지 찾는다.
     * 2. 첫 기준이 같을 때 적용할 다음 기준을 모두 적는다.
     * 3. 입력이 이미 정렬되었는지, 값의 범위가 작은지 확인한다.
     * 4. 원본을 바꿔도 되는지 확인한다.
     * 5. 정렬 결과 자체가 답인지, 정렬 뒤 계산한 값이 답인지 구분한다.
     * 6. 빈 입력, 원소 하나, 동점, 중복과 범위 끝값으로 결과를 손으로 확인한다.
     */
}

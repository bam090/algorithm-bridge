package bridge.twopointer;

import java.util.Arrays;

/**
 * 투 포인터는 배열이나 연속 구간의 두 위치를 함께 움직이며 답을 찾는 방법이다.
 * 한 위치를 움직일 때 건너뛴 값이 앞으로도 답이 될 수 없는 이유가 있어야 한다.
 *
 * <p>두 정렬 배열의 앞에서 각각 시작할 수도 있고, 한 정렬 배열의 양끝에서 시작할 수도 있다.
 * 연속 구간에서는 왼쪽과 오른쪽이 구간의 시작과 끝을 나타낸다.</p>
 */
public final class TwoPointerGuide {

    private TwoPointerGuide() {
    }

    public static void main(String[] args) {
        moveTheSmallerValue();
        moveOneEnd();
        shrinkAContinuousRange();
    }

    private static void moveTheSmallerValue() {
        int[] first = {2, 6};
        int[] second = {3, 6};
        int firstPosition = 0;
        int secondPosition = 0;

        System.out.println("[1] 두 정렬 흐름의 앞을 비교하기");
        System.out.println("비교: " + first[firstPosition] + "와 " + second[secondPosition]);

        if (first[firstPosition] < second[secondPosition]) {
            firstPosition++;
        }

        System.out.println("다음 위치: first=" + firstPosition + ", second=" + secondPosition);
        System.out.println();
    }

    private static void moveOneEnd() {
        int[] ordered = {1, 5, 9};
        int target = 8;
        int left = 0;
        int right = ordered.length - 1;
        int sum = ordered[left] + ordered[right];

        System.out.println("[2] 정렬 배열의 양끝 합을 확인하기");
        System.out.println("배열: " + Arrays.toString(ordered) + ", 양끝 합: " + sum);

        if (sum > target) {
            right--;
        }

        System.out.println("합이 크므로 다음 위치: left=" + left + ", right=" + right);
        System.out.println();
    }

    private static void shrinkAContinuousRange() {
        int[] values = {2, 1, 4};
        int left = 0;
        int right = 2;
        int sum = values[0] + values[1] + values[2];

        System.out.println("[3] 연속 구간의 왼쪽을 줄이기");
        System.out.println("처음 구간: left=" + left + ", right=" + right + ", 합=" + sum);

        sum -= values[left];
        left++;

        System.out.println("왼쪽 값을 뺀 구간: left=" + left + ", right=" + right + ", 합=" + sum);
    }

    /*
     * 투 포인터를 떠올릴 문제의 단서
     *
     * - 두 입력이 이미 정렬되어 있고 현재 값끼리 비교해야 한다.
     * - 정렬된 한 배열에서 두 값의 합이나 차이를 확인해야 한다.
     * - 연속 구간을 늘리거나 줄이며 조건을 만족하는 길이 또는 위치를 찾아야 한다.
     * - 한 위치를 앞으로 옮기면 이전 위치를 다시 확인할 필요가 없다.
     */

    /*
     * 두 위치를 시작하는 방법
     *
     * - 두 정렬 흐름: 두 위치를 각각 0에서 시작한다.
     * - 정렬 배열의 양끝: 왼쪽은 0, 오른쪽은 length - 1에서 시작한다.
     * - 연속 구간: 왼쪽과 오른쪽을 0에서 시작하고 오른쪽으로 값을 넣는다.
     *
     * 시작 위치를 정한 뒤에는 현재 비교 결과가 어느 위치를 움직일지 결정한다.
     */

    /*
     * 위치를 움직여도 안전한 이유 확인하기
     *
     * 정렬된 두 흐름에서 더 작은 현재 값은 다른 흐름의 현재 값보다도 작다.
     * 따라서 같은 값을 찾는 중이라면 더 작은 값이 있는 쪽만 앞으로 옮겨도 된다.
     *
     * 정렬 배열의 양끝 합이 너무 작으면 오른쪽 값을 더 작게 바꿔도 합은 커지지 않는다.
     * 이때는 왼쪽을 옮겨야 한다. 합이 너무 크면 반대로 오른쪽을 옮긴다.
     *
     * 연속 구간의 값이 모두 0 이상이면 오른쪽 값을 넣을 때 합이 줄지 않고,
     * 왼쪽 값을 뺄 때 합이 늘지 않는다. 이 조건이 구간의 이동 방향을 정한다.
     */

    /*
     * Java에서 함께 확인할 것
     *
     * - Arrays.copyOf 또는 clone(): 정렬 전에 원본과 다른 배열을 만든다.
     * - Arrays.sort(array): 양끝 값의 크기 관계를 사용할 수 있게 정렬한다.
     * - long: 큰 값의 합, 두 값의 차이와 위치 쌍의 개수를 안전하게 계산한다.
     */

    /*
     * 초보자가 자주 하는 실수
     *
     * - 왜 건너뛰어도 되는지 확인하지 않고 값이 작아 보이는 쪽을 움직인다.
     * - 값을 기록한 뒤 한쪽 위치만 옮겨 반대쪽의 같은 위치를 여러 번 사용한다.
     * - 서로 다른 두 값을 고르는 양끝 문제에서 left <= right로 반복해 같은 위치의 값을 두 번 사용한다.
     * - 연속 구간에 음수가 있는데 합만 보고 한 방향으로 움직인다.
     * - 입력 배열을 직접 정렬해 원본 보존 조건을 어긴다.
     * - 합과 쌍의 개수를 int로 계산해 범위를 넘긴다.
     */

    /*
     * 코딩 테스트 문제를 읽는 순서
     *
     * 1. 두 위치가 각각 무엇을 가리키는지 말한다.
     * 2. 두 위치의 시작점을 정한다.
     * 3. 현재 비교 결과별로 어느 위치를 움직일지 적는다.
     * 4. 움직이면서 버린 값이 다시 답이 될 수 없는 이유를 설명한다.
     * 5. 두 위치가 만나거나 한 흐름이 끝나는 종료 조건을 확인한다.
     * 6. 빈 입력, 값이 같은 경우, 경계 합과 큰 입력으로 손으로 검산한다.
     */
}

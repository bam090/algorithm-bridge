package bridge.array.onedimensional.solution;

/** 기준을 통과한 기록만 모으기 문제의 정답과 풀이 설명이다. */
public final class ArraySolution04 {

    private ArraySolution04() {
    }

    public static int[] solve(int[] values, int minimum) {
        int resultLength = 0;
        for (int value : values) {
            if (value >= minimum) {
                resultLength++;
            }
        }

        int[] result = new int[resultLength];
        int resultIndex = 0;
        for (int value : values) {
            if (value >= minimum) {
                result[resultIndex] = value;
                resultIndex++;
            }
        }
        return result;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 배열은 만든 뒤 길이를 늘리거나 줄일 수 없다.
     * - 통과할 값의 수는 배열을 확인하기 전에는 알 수 없다.
     * - 원래 순서와 중복을 그대로 유지해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 첫 번째 순회로 결과 길이를 알아낸 뒤 정확한 크기의 배열을 만들 수 있다.
     * - 두 번째 순회에서 만난 순서대로 넣으면 별도의 정렬 없이 원래 순서가 유지된다.
     *
     * 풀이 순서
     * 1. values를 순회해 minimum 이상인 값의 개수를 센다.
     * 2. 그 개수로 result를 만든다.
     * 3. values를 다시 순회하며 조건을 통과한 값을 result[resultIndex]에 넣는다.
     * 4. 값을 넣을 때마다 resultIndex를 1 늘린다.
     * 5. result를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[12, 7, 15, 9, 15], minimum=10
     * - 첫 번째 순회에서 12, 15, 15를 세어 resultLength=3
     * - 길이가 3인 result를 만들면 처음 값은 [0, 0, 0]이다.
     * - 12 -> result[0], 첫 번째 15 -> result[1], 두 번째 15 -> result[2]
     * - 결과: [12, 15, 15]
     *
     * 복잡도
     * - 시간 O(n): 배열을 두 번 순회하지만 2n은 입력 크기에 비례하므로 O(n)이다.
     * - 공간 O(k): 통과한 값 k개를 담는 결과 배열이 필요하다. 결과를 제외한 추가 공간은 O(1)이다.
     *
     * 자주 하는 실수
     * - 입력 길이로 결과를 만들어 뒤쪽에 의미 없는 0을 남긴다.
     * - 입력 인덱스를 결과 인덱스로 그대로 써 중간 칸이 비거나 범위를 벗어난다.
     * - 중복 값을 한 번만 넣거나 순서를 바꾼다.
     */
}

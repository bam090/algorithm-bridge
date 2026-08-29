package bridge.array.onedimensional.solution;

/** 처음 등장한 기록만 남기기 문제의 정답과 풀이 설명이다. */
public final class ArraySolution09 {

    private ArraySolution09() {
    }

    public static int[] solve(int[] values) {
        // 앞부분을 직접 확인하는 이유:
        // 처음 등장한 순서를 지켜야 하므로 값을 정렬할 수 없다.
        // 배열 길이가 작아 앞에 같은 값이 있는지 확인하고, 개수를 센 뒤 결과를 채울 수 있다.

        // [2] isFirstOccurrence가 true인 값의 개수를 센다.
        int uniqueCount = 0;
        for (int i = 0; i < values.length; i++) {
            if (isFirstOccurrence(values, i)) {
                uniqueCount++;
            }
        }

        // [3] 센 개수와 같은 길이의 result를 만든다.
        int[] result = new int[uniqueCount];

        // [4] 배열을 다시 확인해 isFirstOccurrence가 true인 값만 result의 다음 칸에 넣는다.
        int resultIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (isFirstOccurrence(values, i)) {
                result[resultIndex] = values[i];
                resultIndex++;
            }
        }

        // [5] result를 반환한다.
        return result;
    }

    private static boolean isFirstOccurrence(int[] values, int index) {
        // [1] 현재 인덱스보다 앞에 같은 값이 있으면 false, 없으면 true를 반환한다.
        for (int previous = 0; previous < index; previous++) {
            if (values[previous] == values[index]) {
                return false;
            }
        }
        return true;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 같은 값은 첫 번째 한 번만 결과에 들어가야 한다.
     * - 처음 등장한 순서를 유지해야 하므로 정렬하면 안 된다.
     * - 결과 길이는 중복을 확인하기 전에는 알 수 없다.
     *
     * 이 개념을 선택한 이유
     * - 현재 값보다 앞에 같은 값이 없다면 그 값은 처음 등장한 값이다.
     * - 첫 번째 순회로 결과 길이를 구하고, 두 번째 순회로 정확한 크기의 배열을 채울 수 있다.
     * - 값의 범위와 배열 길이가 작으므로 앞부분을 직접 확인하는 방법으로 배열 연습에 집중한다.
     *
     * 풀이 순서
     * 1. 현재 인덱스보다 앞에 같은 값이 있으면 false, 없으면 true를 반환한다.
     * 2. isFirstOccurrence가 true인 값의 개수를 센다.
     * 3. 센 개수와 같은 길이의 result를 만든다.
     * 4. 배열을 다시 확인해 isFirstOccurrence가 true인 값만 result의 다음 칸에 넣는다.
     * 5. result를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[3, 1, 3, 2, 1]
     * - 처음 등장한 값은 순서대로 3, 1, 2이므로 uniqueCount=3
     * - result=[0, 0, 0]을 만든다.
     * - 3 -> result[0], 1 -> result[1], 두 번째 3은 건너뜀
     * - 2 -> result[2], 두 번째 1은 건너뜀
     * - 결과: [3, 1, 2]
     *
     * 복잡도
     * - 시간 O(n²): 각 값마다 앞부분을 다시 확인한다.
     * - 공간 O(k): 처음 등장한 값 k개를 담는 결과 배열이 필요하다. 결과를 제외한 추가 공간은 O(1)이다.
     *
     * 자주 하는 실수
     * - 바로 이전 값과만 비교해 떨어져 있는 중복을 남긴다.
     * - 값을 정렬해 처음 등장한 순서를 바꾼다.
     * - 중복을 센 개수가 아니라 입력 길이로 결과 배열을 만든다.
     */
}

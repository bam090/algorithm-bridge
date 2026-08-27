package bridge.array.onedimensional.solution;

import java.util.Arrays;

/** 기록의 중앙값 찾기 문제의 정답과 풀이 설명이다. */
public final class ArraySolution08 {

    private ArraySolution08() {
    }

    public static int solve(int[] values) {
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length / 2];
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 중앙값은 값을 작은 순서로 놓았을 때 한가운데에 있는 값이다.
     * - values의 길이는 홀수라서 가운데 값이 정확히 하나다.
     * - 원본 배열을 바꾸면 안 된다.
     *
     * 이 개념을 선택한 이유
     * - 정렬하면 가운데 값의 위치를 바로 계산할 수 있다.
     * - Arrays.sort()는 전달한 배열을 직접 바꾸므로, clone()으로 복사한 배열을 정렬해야 원본을 지킬 수 있다.
     *
     * 풀이 순서
     * 1. values를 복사해 sorted를 만든다.
     * 2. sorted를 오름차순으로 정렬한다.
     * 3. sorted.length / 2 인덱스의 값을 반환한다.
     *
     * 예시 데이터 흐름
     * - 입력: values=[8, 2, 5, 1, 9]
     * - 복사: sorted=[8, 2, 5, 1, 9], values=[8, 2, 5, 1, 9]
     * - 정렬: sorted=[1, 2, 5, 8, 9]
     * - 가운데 인덱스: 5 / 2 = 2
     * - 반환값: sorted[2]인 5
     *
     * 복잡도
     * - 시간 O(n log n): 값 n개를 정렬한다.
     * - 공간 O(n): 원본을 지키기 위해 길이 n의 복사 배열을 만든다.
     *
     * 자주 하는 실수
     * - 원본 values를 바로 정렬해 입력을 바꾼다.
     * - 정렬하지 않은 원본의 가운데 칸을 반환한다.
     * - 가운데 인덱스를 length / 2가 아니라 length / 2 + 1로 계산한다.
     */
}

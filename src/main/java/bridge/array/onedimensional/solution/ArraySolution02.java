package bridge.array.onedimensional.solution;

/** 허용 범위 안의 기록 수 문제의 정답과 풀이 설명이다. */
public final class ArraySolution02 {

    private ArraySolution02() {
    }

    public static int solve(int[] values, int minimum, int maximum) {
        // 한 번 순회하는 이유:
        // 모든 값 가운데 범위 안에 있는 값의 개수만 필요하므로,
        // 값을 한 번씩 확인하면서 count만 늘리면 된다.

        // [1] count를 0으로 시작한다.
        int count = 0;

        // [2] 배열의 각 value를 읽는다.
        for (int value : values) {
            // [3] value >= minimum && value <= maximum이면 count를 1 늘린다.
            if (value >= minimum && value <= maximum) {
                count++;
            }
        }

        // [4] 순회가 끝난 뒤 count를 반환한다.
        return count;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 모든 기록 중 조건을 만족하는 값의 "개수"만 필요하다.
     * - minimum 이상과 maximum 이하를 모두 만족해야 하며 두 경계도 포함한다.
     *
     * 이 개념을 선택한 이유
     * - 각 값을 앞에서부터 한 번씩 보면서, 범위 안에 있을 때 개수를 늘리면 된다.
     * - 인덱스가 필요하지 않아 값을 바로 읽는 향상된 for문이 잘 맞는다.
     *
     * 풀이 순서
     * 1. count를 0으로 시작한다.
     * 2. 배열의 각 value를 읽는다.
     * 3. value >= minimum && value <= maximum이면 count를 1 늘린다.
     * 4. 순회가 끝난 뒤 count를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[-2, 0, 5, 7, 10], 범위=[0, 7]
     * - -2: 범위 밖, count=0
     * - 0: 0 이상이고 7 이하이므로 count=1
     * - 5: 범위 안, count=2
     * - 7: 0 이상이고 7 이하이므로 count=3
     * - 10: 범위 밖, 최종 count=3
     *
     * 복잡도
     * - 시간 O(n): n개의 값을 각각 한 번 확인한다.
     * - 공간 O(1): 입력 크기와 관계없이 고정된 개수의 변수만 사용한다.
     *
     * 자주 하는 실수
     * - 경계를 제외하는 > 또는 <를 사용한다.
     * - 두 조건을 &&가 아니라 ||로 연결해 거의 모든 값을 센다.
     */
}

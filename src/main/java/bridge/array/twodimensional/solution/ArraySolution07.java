package bridge.array.twodimensional.solution;

/** 날짜별 기록 합계 문제의 정답과 풀이 설명이다. */
public final class ArraySolution07 {

    private ArraySolution07() {
    }

    public static int[] solve(int[][] records) {
        // 중첩 반복을 선택한 이유:
        // 날짜마다 행 길이가 다르므로 바깥에서 행을 고르고 현재 행의 값만 끝까지 확인한다.
        // 행마다 합계 하나를 같은 번호의 결과 칸에 저장하면 문제의 출력과 바로 맞는다.

        // [1] 행의 개수와 같은 길이의 rowSums를 만든다.
        int[] rowSums = new int[records.length];

        // [2] 바깥 반복문에서 row번째 행을 고른다.
        for (int row = 0; row < records.length; row++) {
            // [3] 현재 행의 합 rowSum을 0으로 시작한다.
            int rowSum = 0;

            // [4] 안쪽 반복문에서 records[row]의 값을 모두 rowSum에 더한다.
            for (int column = 0; column < records[row].length; column++) {
                rowSum += records[row][column];
            }

            // [5] rowSum을 rowSums[row]에 저장한다.
            rowSums[row] = rowSum;
        }

        // [6] 모든 행을 처리한 뒤 rowSums를 반환한다.
        return rowSums;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - records의 각 행이 하루의 측정값을 나타낸다.
     * - 날짜마다 측정 횟수가 다를 수 있으므로 행의 길이도 서로 다를 수 있다.
     * - 행마다 합계 하나가 필요하므로 결과 길이는 records.length와 같다.
     * - 원본 records는 바꾸지 않고 각 값을 읽어서 합계만 계산해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 바깥 반복문으로 행을 하나씩 고르고, 안쪽 반복문으로 현재 행의 값을 모두 확인할 수 있다.
     * - 안쪽 반복문의 끝을 records[row].length로 정하면 길이가 다른 행과 빈 행도 같은 코드로 처리할 수 있다.
     *
     * 풀이 순서
     * 1. 행의 개수와 같은 길이의 rowSums를 만든다.
     * 2. 바깥 반복문에서 row번째 행을 고른다.
     * 3. 현재 행의 합 rowSum을 0으로 시작한다.
     * 4. 안쪽 반복문에서 records[row]의 값을 모두 rowSum에 더한다.
     * 5. rowSum을 rowSums[row]에 저장한다.
     * 6. 모든 행을 처리한 뒤 rowSums를 반환한다.
     *
     * 예시 데이터 흐름
     * - records=[[3, 1, 2], [10], [-2, 2, 5], []]
     * - 0행: 3 + 1 + 2 = 6  -> rowSums[0]=6
     * - 1행: 10 = 10         -> rowSums[1]=10
     * - 2행: -2 + 2 + 5 = 5 -> rowSums[2]=5
     * - 3행: 값이 없음       -> rowSums[3]=0
     * - 결과: [6, 10, 5, 0]
     *
     * 복잡도
     * - 시간 O(r+n): 행 r개를 확인하고 전체 원소 n개를 한 번씩 더한다.
     * - 공간 O(r): 행별 합을 담는 길이 r의 결과 배열이 필요하다.
     * - 반환 배열을 제외한 추가 공간은 O(1)이다.
     *
     * 자주 하는 실수
     * - 안쪽 반복문의 조건에 records.length를 사용해 현재 행의 길이를 무시한다.
     * - 모든 행이 records[0].length와 같은 길이라고 가정한다.
     * - rowSum을 바깥 반복문 밖에서 한 번만 0으로 만들어 이전 행의 합이 다음 행에 섞인다.
     */
}

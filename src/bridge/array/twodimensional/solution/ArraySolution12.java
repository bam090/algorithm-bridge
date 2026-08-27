package bridge.array.twodimensional.solution;

/** 같은 번호의 행과 열 합계 문제의 정답과 풀이 설명이다. */
public final class ArraySolution12 {

    private ArraySolution12() {
    }

    public static int[] solve(int[][] table) {
        int[] crossSums = new int[table.length];

        for (int index = 0; index < table.length; index++) {
            int crossSum = 0;
            for (int offset = 0; offset < table.length; offset++) {
                crossSum += table[index][offset];
                if (offset != index) {
                    crossSum += table[offset][index];
                }
            }
            crossSums[index] = crossSum;
        }
        return crossSums;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - table은 행과 열의 개수가 같은 정사각형 표다.
     * - index번째 결과 하나에는 index번째 행과 index번째 열의 값이 모두 필요하다.
     * - table[index][index]는 행과 열이 만나는 한 칸이므로 한 번만 더해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 바깥 반복문으로 결과 번호를 고르고, 안쪽 반복문으로 같은 번호의 행과 열을 함께 읽을 수 있다.
     * - 행 값은 table[index][offset], 열 값은 두 인덱스를 바꾼 table[offset][index]로 읽는다.
     *
     * 풀이 순서
     * 1. table.length와 같은 길이의 crossSums를 만든다.
     * 2. 바깥 반복문에서 결과 번호 index를 고른다.
     * 3. 안쪽 반복문에서 index번째 행의 값을 더한다.
     * 4. 교차점이 아니라면 index번째 열의 값도 더한다.
     * 5. 합계를 crossSums[index]에 저장하고 모든 번호를 처리한 뒤 반환한다.
     *
     * 예시 데이터 흐름
     * - table=[[1, 2, 3], [4, 5, 6], [7, 8, 9]]
     * - 0번: 0행 1+2+3, 0열의 나머지 4+7 -> 17
     * - 1번: 1행 4+5+6, 1열의 나머지 2+8 -> 25
     * - 2번: 2행 7+8+9, 2열의 나머지 3+6 -> 33
     * - 결과: [17, 25, 33]
     *
     * 복잡도
     * - 시간 O(n²): n개 결과마다 행과 열의 n개 위치를 확인한다.
     * - 공간 O(n): n개의 합계를 담는 결과 배열이 필요하다. 결과를 제외한 추가 공간은 O(1)이다.
     *
     * 자주 하는 실수
     * - table[index][index]를 행에서 한 번, 열에서 한 번 더해 두 번 센다.
     * - table[offset][index] 대신 table[index][offset]을 다시 읽어 행만 두 번 더한다.
     * - 2차원 결과 배열을 만들어 문제에서 요구한 1차원 요약과 다른 값을 반환한다.
     */
}

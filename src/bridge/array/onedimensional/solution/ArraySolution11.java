package bridge.array.onedimensional.solution;

/** 반복 점검표 통과시키기 문제의 정답과 풀이 설명이다. */
public final class ArraySolution11 {

    private ArraySolution11() {
    }

    public static int[] solve(int[] observations, int[][] cycles, int minimumMatches) {
        // 나머지 연산과 점검표별 배열을 선택한 이유:
        // 반복되는 점검표는 i % 점검표 길이로 현재 코드를 찾을 수 있다.
        // 점검표마다 일치 개수를 따로 저장하면 기준을 넘긴 번호만 순서대로 고를 수 있다.

        // [1] 점검표 개수와 같은 길이의 matchCounts를 만든다.
        int[] matchCounts = new int[cycles.length];

        // [2] 각 점검표에 대해 observations를 처음부터 끝까지 확인한다.
        for (int cycle = 0; cycle < cycles.length; cycle++) {
            for (int i = 0; i < observations.length; i++) {
                int expected = cycles[cycle][i % cycles[cycle].length];

                // [3] observations[i]와 반복 점검표의 현재 코드가 같으면 해당 개수를 늘린다.
                if (observations[i] == expected) {
                    matchCounts[cycle]++;
                }
            }
        }

        // [4] minimumMatches 이상인 점검표 개수를 센다.
        int passedCount = 0;
        for (int matchCount : matchCounts) {
            if (matchCount >= minimumMatches) {
                passedCount++;
            }
        }

        // [5] 통과한 개수로 결과 배열을 만들고 1부터 세는 점검표 번호를 입력 순서대로 넣는다.
        int[] passedCycles = new int[passedCount];
        int resultIndex = 0;
        for (int cycle = 0; cycle < matchCounts.length; cycle++) {
            if (matchCounts[cycle] >= minimumMatches) {
                passedCycles[resultIndex] = cycle + 1;
                resultIndex++;
            }
        }

        // [6] passedCycles를 반환한다.
        return passedCycles;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 각 점검표는 길이가 달라도 처음부터 다시 반복된다.
     * - 점검표마다 일치 개수를 따로 계산해야 한다.
     * - 가장 큰 개수가 아니라 minimumMatches 이상인 모든 점검표 번호가 필요하다.
     *
     * 이 개념을 선택한 이유
     * - i % 점검표길이는 i가 점검표 끝을 넘을 때 다시 0부터 시작하게 한다.
     * - 점검표 번호와 같은 인덱스의 matchCounts에 개수를 저장하면 여러 개수를 섞지 않고 관리할 수 있다.
     * - 통과 개수를 먼저 세면 고정 길이 배열을 정확한 크기로 만들 수 있다.
     *
     * 풀이 순서
     * 1. 점검표 개수와 같은 길이의 matchCounts를 만든다.
     * 2. 각 점검표에 대해 observations를 처음부터 끝까지 확인한다.
     * 3. observations[i]와 cycles[cycle][i % 점검표길이]가 같으면 해당 개수를 늘린다.
     * 4. minimumMatches 이상인 점검표 개수를 센다.
     * 5. 통과한 개수로 결과 배열을 만들고 1부터 세는 점검표 번호를 입력 순서대로 넣는다.
     *
     * 예시 데이터 흐름
     * - observations=[2, 4, 2, 4, 2, 5]
     * - 1번 점검표 [2, 4]는 [2, 4, 2, 4, 2, 4]로 읽어 5개가 일치한다.
     * - 2번 점검표 [2, 5, 2]는 2개가 일치한다.
     * - 3번 점검표 [4]는 2개가 일치한다.
     * - minimumMatches=4 이상인 점검표는 1번이므로 결과는 [1]이다.
     *
     * 복잡도
     * - 시간 O(c×n): 점검표 c개마다 observations의 값 n개를 확인한다.
     * - 공간 O(c): 점검표별 일치 개수와 통과 번호를 저장한다.
     *
     * 자주 하는 실수
     * - observations의 인덱스를 점검표에 그대로 사용해 점검표 끝에서 범위를 벗어난다.
     * - 점검표가 바뀔 때 일치 개수를 새로 구분하지 않아 개수가 섞인다.
     * - 가장 많이 일치한 점검표만 반환한다.
     * - 0부터 시작하는 배열 인덱스를 그대로 반환해 점검표 번호가 한 칸 작아진다.
     */
}

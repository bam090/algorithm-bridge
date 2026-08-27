package bridge.array.onedimensional.solution;

/** 가장 긴 연속 상승 기록 문제의 정답과 풀이 설명이다. */
public final class ArraySolution06 {

    private ArraySolution06() {
    }

    public static int solve(int[] values) {
        if (values.length == 0) {
            return 0;
        }

        int currentLength = 1;
        int bestLength = 1;

        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[i - 1]) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            bestLength = Math.max(bestLength, currentLength);
        }
        return bestLength;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - "연속"이므로 떨어진 원소를 골라 이어 붙일 수 없다.
     * - 현재 값이 바로 이전 값보다 큰지 여부만 알면 상승이 이어지는지 판단할 수 있다.
     * - 가장 긴 길이를 구하려면 현재 상승 길이와 지금까지의 가장 긴 길이를 따로 기억해야 한다.
     *
     * 이 개념을 선택한 이유
     * - 배열을 한 번 확인하면서 currentLength와 bestLength를 갱신하면 모든 상승 구간의 길이를 알 수 있다.
     * - 실제 구간 원소를 따로 저장할 필요가 없으므로 추가 배열은 만들지 않는다.
     *
     * 풀이 순서
     * 1. 빈 배열이면 0을 반환한다.
     * 2. 원소가 있는 경우 현재 길이와 최고 길이를 1로 시작한다.
     * 3. 두 번째 원소부터 이전 값과 비교한다.
     * 4. 더 크면 현재 길이를 늘리고, 같거나 작으면 현재 길이를 1로 다시 시작한다.
     * 5. 매 위치에서 최고 길이를 갱신한다.
     * 6. bestLength를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[3, 4, 6, 2, 5, 7, 1]
     * - 시작: currentLength=1, bestLength=1
     * - 4>3: currentLength=2, bestLength=2
     * - 6>4: currentLength=3, bestLength=3
     * - 2<=6: currentLength=1, bestLength=3
     * - 5>2: currentLength=2, bestLength=3
     * - 7>5: currentLength=3, bestLength=3
     * - 1<=7: currentLength=1, bestLength=3
     *
     * 복잡도
     * - 시간 O(n): 배열을 한 번 순회한다.
     * - 공간 O(1): 입력 크기와 관계없이 고정된 개수의 변수만 사용한다.
     *
     * 자주 하는 실수
     * - 값이 같아도 상승으로 세는 >=를 사용한다.
     * - 구간이 끊길 때 currentLength를 0으로 바꿔 현재 원소 하나를 놓친다.
     * - 최고 길이를 반복문이 끝난 뒤 한 번만 갱신해, 중간에 끝난 가장 긴 구간을 놓친다.
     */
}

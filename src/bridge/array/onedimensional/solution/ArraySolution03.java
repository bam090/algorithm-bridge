package bridge.array.onedimensional.solution;

/** 연속 기록의 변화량 문제의 정답과 풀이 설명이다. */
public final class ArraySolution03 {

    private ArraySolution03() {
    }

    public static int[] solve(int[] values) {
        int[] changes = new int[values.length - 1];
        for (int i = 1; i < values.length; i++) {
            changes[i - 1] = values[i] - values[i - 1];
        }
        return changes;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 각 결과는 현재 값에서 바로 이전 값을 뺀 값이다.
     * - 첫 번째 값에는 이전 값이 없으므로 결과 개수는 입력보다 하나 적다.
     *
     * 이 개념을 선택한 이유
     * - 이웃한 두 위치가 모두 필요하므로 인덱스를 쓰는 일반 for문이 알맞다.
     * - 결과 길이를 미리 정확히 알 수 있어 바로 새 배열을 만들 수 있다.
     *
     * 풀이 순서
     * 1. 입력보다 길이가 1 작은 changes를 만든다.
     * 2. 이전 값이 존재하는 입력 인덱스 1부터 순회한다.
     * 3. values[i] - values[i - 1]을 changes[i - 1]에 저장한다.
     * 4. changes를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[10, 13, 12, 12], changes의 길이는 3
     * - i=1: 13-10=3  -> changes[0]=3
     * - i=2: 12-13=-1 -> changes[1]=-1
     * - i=3: 12-12=0  -> changes[2]=0
     * - 결과: [3, -1, 0]
     *
     * 복잡도
     * - 시간 O(n): 두 번째 값부터 끝까지 한 번 순회한다.
     * - 공간 O(n): 반환할 변화량 배열이 필요하다. 반환 배열을 제외한 추가 공간은 O(1)이다.
     *
     * 자주 하는 실수
     * - 입력과 같은 길이의 결과 배열을 만들어 사용하지 않는 칸을 남긴다.
     * - i=0에서 values[i - 1]을 읽으려 한다.
     * - 현재 값과 이전 값의 뺄셈 순서를 바꾼다.
     */
}

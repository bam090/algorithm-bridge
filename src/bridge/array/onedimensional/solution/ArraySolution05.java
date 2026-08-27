package bridge.array.onedimensional.solution;

/** 여러 구간의 기록 합 문제의 정답과 풀이 설명이다. */
public final class ArraySolution05 {

    private ArraySolution05() {
    }

    public static long[] solve(int[] values, int[][] ranges) {
        long[] prefixSums = new long[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + values[i];
        }

        long[] answers = new long[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = ranges[i][1];
            answers[i] = prefixSums[end] - prefixSums[start - 1];
        }
        return answers;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 같은 배열 values에 대한 구간 합을 최대 100,000번 묻는다.
     * - 각 구간의 값을 매번 하나씩 다시 더하면 이미 읽은 값을 반복해서 읽게 된다.
     * - 문제의 위치 번호는 1부터 세며, 구간의 시작과 끝을 모두 포함한다.
     *
     * 이 개념을 선택한 이유
     * - 누적 합은 첫 값부터 각 위치까지의 합을 한 번만 계산해 저장한다.
     * - 구간 하나의 합을 두 누적 합의 차이로 바로 구할 수 있다.
     *
     * 풀이 순서
     * 1. 맨 앞에 0을 둔 길이 values.length + 1의 prefixSums를 만든다.
     * 2. prefixSums[i+1]에 values[0]부터 values[i]까지의 합을 저장한다.
     * 3. 각 [start, end]에 대해 prefixSums[end] - prefixSums[start-1]을 계산한다.
     * 4. 계산한 값을 질문과 같은 인덱스의 answers에 저장한다.
     * 5. answers를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[4, 1, 3, 2, 5]
     * - prefixSums=[0, 4, 5, 8, 10, 15]
     * - [1,3]: prefixSums[3]-prefixSums[0]=8-0=8
     * - [2,5]: prefixSums[5]-prefixSums[1]=15-4=11
     * - [4,4]: prefixSums[4]-prefixSums[3]=10-8=2
     * - answers=[8, 11, 2]
     *
     * 복잡도
     * - 값 n개와 질문 q개를 처리하는 시간은 O(n+q)다.
     * - 공간 O(n+q): 누적 합 배열에 O(n), 반환 배열에 O(q)가 필요하다.
     * - 반환 배열을 제외한 추가 공간은 O(n)이다.
     *
     * 자주 하는 실수
     * - int로 누적해 합이 약 21억을 넘을 때 잘못된 값이 된다.
     * - prefixSums[start - 1] 대신 prefixSums[start]를 빼 구간의 첫 값까지 제외한다.
     * - 문제의 위치 번호가 1부터 시작한다는 사실을 놓쳐 인덱스가 한 칸 어긋난다.
     */
}

package bridge.array.onedimensional.solution;

/** 기록 한 칸 바로잡기 문제의 정답과 풀이 설명이다. */
public final class ArraySolution01 {

    private ArraySolution01() {
    }

    public static int[] solve(int[] readings, int slotNumber, int correctedValue) {
        int[] result = readings.clone();
        int index = slotNumber - 1;
        result[index] = correctedValue;
        return result;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 문제에서 "slotNumber번째"는 1부터 세는 위치다.
     * - "새 배열을 반환하고 원본은 바꾸지 말라"는 조건이 있다.
     *
     * 이 개념을 선택한 이유
     * - 위치를 알기 때문에 전체를 검색할 필요 없이 해당 인덱스에 바로 접근할 수 있다.
     * - result = readings처럼 배열 참조만 대입하면 두 변수가 같은 배열을 가리킨다.
     * - 원본을 지키기 위해 clone()으로 독립된 배열을 만든다.
     *
     * 풀이 순서
     * 1. readings의 모든 값을 새 배열 result에 복사한다.
     * 2. slotNumber에서 1을 빼 Java 인덱스로 바꾼다.
     * 3. result의 그 인덱스만 correctedValue로 바꾼다.
     * 4. result를 반환한다.
     *
     * 예시 데이터 흐름
     * - 입력: readings=[18, 20, 19], slotNumber=2, correctedValue=21
     * - 복사 후: result=[18, 20, 19], readings=[18, 20, 19]
     * - 인덱스: 2 - 1 = 1
     * - result[1] 변경 후: result=[18, 21, 19]
     * - 원본 readings는 [18, 20, 19]로 남는다.
     *
     * 복잡도
     * - 시간 O(n): 길이 n인 배열을 복사해야 한다.
     * - 공간 O(n): 원본과 별개인 길이 n의 결과 배열이 필요하다.
     *
     * 자주 하는 실수
     * - int[] result = readings;로 두 변수가 같은 배열을 가리키게 한다.
     * - slotNumber를 그대로 인덱스로 사용해 한 칸 뒤를 바꾼다.
     */
}

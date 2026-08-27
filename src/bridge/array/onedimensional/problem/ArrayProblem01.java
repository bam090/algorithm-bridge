package bridge.array.onedimensional.problem;

//region 문제: 기록 한 칸 바로잡기
/*
 측정값이 기록 순서대로 배열에 들어 있다. slotNumber번째 기록이 잘못되었다.
 그 칸만 correctedValue로 바꾼 새 배열을 반환하라.
 원본 readings는 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 문제에서 말하는 2번째 칸의 Java 배열 인덱스는 몇 번인가?
 - 원본을 보존하려면 결과 변수가 원본과 같은 배열을 가리켜도 되는가?
 - 첫 번째와 마지막 위치를 고치면 사용할 인덱스는 각각 무엇인가?
 */
//endregion

public final class ArrayProblem01 {

    private ArrayProblem01() {
        solve(new int[]{18, 20, 19}, 2, 21);
        // 예상 출력: new int[]{18, 21, 19}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     원본 배열을 복사한 뒤, 주어진 위치의 값만 고친 새 배열을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - readings는 null이 아니며 길이는 1 이상 100 이하이다.
     - 각 측정값과 correctedValue는 -1,000 이상 1,000 이하이다.
     - slotNumber는 문제에서 1부터 세는 위치이며, 1 이상 readings.length 이하이다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] readings, int slotNumber, int correctedValue) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - Java 배열의 첫 칸은 인덱스 0이다.
     - 문제에서 1부터 세는 위치 번호에서 1을 빼면 Java 배열의 인덱스가 된다.
     - readings의 값을 새 배열에 복사한 뒤, 새 배열의 한 칸만 바꾼다.
     */
    //endregion
}

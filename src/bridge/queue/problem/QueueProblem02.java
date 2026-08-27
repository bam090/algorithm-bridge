package bridge.queue.problem;

//region 문제: 검수 순서에서 낮은 점수 빼기
/*
 검수할 점수가 scores에 앞에서부터 들어 있다.
 rotations의 각 값은 이번 검수 전에 맨 앞 값을 맨 뒤로 보내는 횟수다.
 각 명령대로 줄을 옮긴 뒤 맨 앞 점수가 removalLimit 이하이면 그 점수를 제거한다.
 기준보다 크면 맨 앞에 그대로 둔다. 모든 명령을 처리한 뒤 남은 순서를 새 배열로 반환하라.
 큐가 비면 남은 명령은 건너뛰고, 원본 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 맨 앞 값을 맨 뒤로 한 번 보내면 줄의 순서는 어떻게 바뀌는가?
 - 이동 횟수가 현재 줄의 길이보다 클 때 같은 순서가 반복되는 주기는 얼마인가?
 - 점수가 removalLimit와 같으면 제거해야 하는가?
 - 제거한 뒤 새 맨 앞 값은 무엇이며, 큐가 비면 다음 명령은 어떻게 처리해야 하는가?
 */
//endregion

public final class QueueProblem02 {

    private QueueProblem02() {
        solve(new int[]{4, 9, 2, 7}, new int[]{2, 1, 0}, 4);
        // 예상 출력: new int[]{9, 7}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     명령마다 앞 값을 정해진 횟수만큼 뒤로 옮긴 뒤 기준 이하인 맨 앞 값만 제거하고, 모든 명령 뒤에 남은 순서를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - scores는 null이 아니며 길이는 0 이상 1,000 이하이다.
     - 각 점수와 removalLimit는 -1,000 이상 1,000 이하이다.
     - rotations는 null이 아니며 길이는 0 이상 1,000 이하이다.
     - 각 이동 횟수는 0 이상 1,000 이하이다.
     - 원본 scores와 rotations는 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] scores, int[] rotations, int removalLimit) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 큐가 비어 있지 않다면 이동 횟수를 현재 큐 길이로 나눈 나머지만큼 옮겨도 결과가 같다.
     - 한 번 옮길 때는 poll()로 꺼낸 값을 offer()로 다시 넣는다.
     - 이동이 끝난 뒤 peek()로 맨 앞을 확인하고, 기준 이하일 때만 poll()로 제거한다.
     */
    //endregion
}

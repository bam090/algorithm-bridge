package bridge.queue.problem;

//region 문제: 맨 앞 순번을 한 번 미루기
/*
 처리할 순번이 order에 앞에서부터 들어 있다.
 맨 앞 순번을 꺼내 맨 뒤로 한 번 보낸 뒤, 바뀐 순서를 새 배열로 반환하라.
 빈 배열은 그대로 빈 새 배열을 반환하고, 원본 order는 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 큐에서 먼저 들어온 값을 꺼내는 동작은 무엇인가?
 - 꺼낸 값을 줄의 맨 뒤에 넣는 동작은 무엇인가?
 - 값이 하나뿐이면 한 번 옮긴 뒤 순서는 어떻게 되는가?
 - 원본 배열을 바꾸지 않고 큐의 순서를 결과 배열에 어떻게 담을 수 있는가?
 */
//endregion

public final class QueueProblem01 {

    private QueueProblem01() {
        solve(new int[]{10, 20, 30, 40});
        // 예상 출력: new int[]{20, 30, 40, 10}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     모든 값을 큐에 넣고, 맨 앞 값을 한 번 꺼내 맨 뒤에 넣은 뒤 바뀐 순서를 새 배열로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - order는 null이 아니며 길이는 0 이상 100,000 이하이다.
     - 각 순번은 -1,000,000 이상 1,000,000 이하이다.
     - 원본 order는 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] order) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - order의 값을 앞에서부터 queue.offer()로 넣는다.
     - 큐가 비어 있지 않을 때 queue.poll()로 앞 값을 꺼내 queue.offer()로 다시 넣는다.
     - 회전이 끝난 큐를 앞에서부터 읽어 같은 길이의 결과 배열에 담는다.
     */
    //endregion
}

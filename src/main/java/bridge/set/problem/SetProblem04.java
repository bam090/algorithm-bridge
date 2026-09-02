package bridge.set.problem;

//region 문제: 장비 연결 감사의 실패 요청 찾기
/*
 0번부터 deviceCount-1번까지의 장비가 처음에는 서로 다른 연결 그룹에 있다.
 actions, firstIds, secondIds의 같은 인덱스가 요청 하나를 나타낸다.
 요청은 배열 순서대로 처리하며 요청 번호는 1부터 센다.

 - LINK 요청은 두 장비가 속한 전체 그룹을 연결한다.
 - AUDIT 요청은 두 장비가 현재 같은 그룹인지 확인한다.

 AUDIT 시점에 두 장비가 다른 그룹이면 그 요청 번호를 기록하라.
 모든 요청을 처리한 뒤 실패한 AUDIT 요청 번호만 새 배열로 반환하라.
 입력 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 뒤에 나오는 LINK를 앞선 AUDIT보다 먼저 적용해도 되는가?
 - 두 장비의 바로 위 부모만 같으면 같은 그룹이라고 판단해도 되는가?
 - 같은 그룹을 다시 LINK할 때 그룹 수나 크기가 또 바뀌어도 되는가?
 - 결과에 기록하는 번호는 AUDIT만 센 번호인가, 모든 요청을 센 번호인가?
 - 실패 개수를 미리 모를 때 최대 크기의 배열과 실제 개수를 어떻게 함께 사용할 수 있는가?
 */
//endregion

public final class SetProblem04 {

    private SetProblem04() {
        solve(
                6,
                new String[]{"AUDIT", "LINK", "LINK", "AUDIT", "AUDIT", "LINK", "AUDIT"},
                new int[]{0, 0, 1, 0, 0, 2, 0},
                new int[]{1, 1, 2, 2, 5, 5, 5}
        );
        // 예상 출력: new int[]{1, 5}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     요청을 순서대로 처리하면서 LINK는 압축해 찾은 두 대표를 합치고 AUDIT은 두 대표가 다를 때 요청 번호를 기록한 뒤 실패 번호를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - deviceCount는 0 이상 100,000 이하이다.
     - actions, firstIds와 secondIds는 null이 아니며 길이가 같다.
     - 요청 수는 0 이상 200,000 이하이다.
     - actions의 각 값은 "LINK" 또는 "AUDIT"이다.
     - deviceCount가 0이면 세 입력 배열은 비어 있다.
     - 각 장비 번호는 0 이상 deviceCount-1 이하이다.
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(
            int deviceCount,
            String[] actions,
            int[] firstIds,
            int[] secondIds
    ) {
        int[] answer = {};
        return answer;
    }
}

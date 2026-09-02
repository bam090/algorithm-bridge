package bridge.tree.problem;

//region 문제: 폴더 변경 영향 점수
/*
 outputOrder에는 폴더 이름이 결과를 반환할 순서대로 들어 있다.
 relations의 각 행은 {자식 폴더 이름, 바로 위 부모 폴더 이름}이며 행 순서는 섞여 있다.
 뿌리 폴더는 relations의 자식 이름으로 등장하지 않는다.
 eventNames[i] 폴더에서 eventPoints[i]만큼의 변경이 생기면 그 폴더와 모든 부모 폴더에
 같은 점수를 더한다. 모든 변경을 처리한 뒤 outputOrder 순서의 누적 점수를 반환하라.
 점수는 음수나 0일 수도 있다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 사건 이름의 결과 위치를 매번 처음부터 찾지 않으려면 어떤 연결 정보가 필요한가?
 - 관계 행의 순서와 관계없이 자식 이름으로 부모 이름을 바로 찾으려면 무엇을 연결해야 하는가?
 - 부모 관계가 없는 뿌리에 도착했는지는 어떻게 알 수 있는가?
 - 같은 폴더에서 여러 사건이 생기면 이전 점수를 덮어써야 하는가, 더해야 하는가?
 - 누적 점수가 int 범위를 넘을 수 있는가? 결과를 outputOrder 순서로 만들려면 무엇을 기준으로 해야 하는가?
 */
//endregion

public final class TreeProblem04 {

    private TreeProblem04() {
        solve(
                new String[]{"summer", "root", "work", "photo"},
                new String[][]{
                        {"work", "root"},
                        {"summer", "photo"},
                        {"photo", "root"}
                },
                new String[]{"summer", "work"},
                new int[]{5, 2}
        );
        // 예상 출력: new long[]{5, 7, 2, 5}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     outputOrder의 이름별 결과 위치와 relations의 자식별 부모 이름을 연결한 뒤,
     각 사건 이름부터 부모 관계가 없을 때까지 점수를 더하고 outputOrder 순서로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - outputOrder는 null이 아니며 길이는 1 이상 1,000 이하이다.
     - 이름은 서로 다르며 길이 1 이상 20 이하의 영문 소문자와 숫자로 이루어진 문자열이다.
     - relations는 null이 아니며 길이는 outputOrder.length - 1이다.
     - relations의 각 행은 길이 2인 {자식 이름, 부모 이름}이고, 두 이름은 모두 outputOrder에 있다.
     - 뿌리 하나만 자식으로 등장하지 않으며, 나머지 이름은 자식으로 정확히 한 번 등장한다.
     - 관계 행 순서는 섞여 있고, 부모가 outputOrder에서 자식보다 뒤에 나올 수도 있다.
     - 관계는 하나의 트리를 이루며 부모를 따라가면 반드시 뿌리에서 끝난다.
     - 뿌리를 포함해 한 부모 사슬에서 만나는 폴더 수인 깊이는 50 이하이다.
     - eventNames와 eventPoints는 null이 아니며 길이는 같고 0 이상 100,000 이하이다.
     - 모든 eventNames 값은 outputOrder에 들어 있다.
     - 각 eventPoints 값은 -1,000,000 이상 1,000,000 이하이다.
     - 누적값은 int 범위를 넘을 수 있으므로 long으로 계산한다.
     - 모든 입력 배열은 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static long[] solve(
            String[] outputOrder,
            String[][] relations,
            String[] eventNames,
            int[] eventPoints
    ) {
        long[] answer = {};
        return answer;
    }
}

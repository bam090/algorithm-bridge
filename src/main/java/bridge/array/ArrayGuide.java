package bridge.array;

import java.util.Arrays;

/**
 * 배열은 같은 종류의 값을 "번호가 붙은 칸"에 순서대로 보관한다.
 *<p>
 * 인덱스:   0   1   2
 * 값:      70  80  90
 *<p>
 * Java 배열의 인덱스는 항상 0부터 시작한다.
 * 길이가 3이면 사용할 수 있는 인덱스는 0부터 2까지다.
 *<p>
 * 배열의 선언과 생성
 * 자료형[] 배열이름 = new 자료형[배열크기];
 */
public class ArrayGuide {

    public static void main(String[] args) {
        // 1. 만들기 -> 읽기 -> 바꾸기 -> 길이 확인
        int[] scores = {70, 80, 90};
        int[] emptySlots = new int[3];

        System.out.println("[1] 배열의 기본 사용");
        System.out.println("처음 값: " + Arrays.toString(scores));
        System.out.println("scores[0]: " + scores[0]);

        scores[1] = 85;
        System.out.println("두 번째 값 수정: " + Arrays.toString(scores));
        System.out.println("길이: " + scores.length);
        System.out.println("new int[3]의 기본값: " + Arrays.toString(emptySlots));

        // new int[길이]로 만든 각 칸은 0으로 시작한다.
        // boolean[]은 false, 참조 타입 배열은 null로 시작한다.

        // 2. 인덱스가 필요할 때는 일반 for문을 사용한다.
        // 배열 끝까지 안전하게 확인하려면 i < scores.length를 사용한다.
        System.out.println("\n[2] 인덱스별 합계 흐름");
        System.out.println("인덱스 | 현재 값 | 합계");

        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
            System.out.println(i + " | " + scores[i] + " | " + sum);
        }

        // 값만 읽고 인덱스가 필요 없다면 다음 형태도 사용할 수 있다.
        // for (int score : scores) { ... }
        // 서로 다른 두 위치를 한 번씩 짝지으려면 바깥 인덱스를 i로 고르고,
        // 안쪽 인덱스를 i + 1부터 시작한다.

        // 3. 짧은 배열을 반복해서 읽을 때는 나머지 연산으로 위치를 되돌린다.
        int[] pattern = {2, 4};
        System.out.println("\n[3] 반복 배열의 위치");
        for (int i = 0; i < 6; i++) {
            System.out.println(i + " -> " + pattern[i % pattern.length]);
        }

        // 4. 같은 배열을 가리키는 것과 독립적으로 복사하는 것은 다르다.
        // int[] sameArray = scores;를 실행하면 두 변수가 같은 배열을 가리킨다.
        // clone()은 값을 복사한 독립적인 int[]가 필요할 때 사용한다.
        System.out.println("\n[4] 원본과 복사본");

        int[] copiedScores = scores.clone();
        copiedScores[0] = 100;

        System.out.println("원본: " + Arrays.toString(scores));
        System.out.println("복사본만 수정: " + Arrays.toString(copiedScores));

        // 정렬은 배열 자체를 바꾼다. 원본을 지켜야 하면 먼저 복사한다.
        int[] unsortedScores = {90, 70, 85};
        int[] sortedScores = unsortedScores.clone();
        Arrays.sort(sortedScores);

        System.out.println("정렬 전 원본: " + Arrays.toString(unsortedScores));
        System.out.println("정렬한 복사본: " + Arrays.toString(sortedScores));

        // 5. 2차원 배열은 여러 개의 1차원 배열을 행으로 묶은 것이다.
        // table[행][열] 순서로 한 칸을 찾는다.
        int[][] table = {
                {10, 20, 30},
                {40, 50},
                {60, 70, 80, 90}
        };

        System.out.println("\n[5] 2차원 배열의 행별 합계 흐름");
        System.out.println("행 | 열 | 현재 값 | 행 합계");

        for (int row = 0; row < table.length; row++) {
            int rowSum = 0;
            for (int column = 0; column < table[row].length; column++) {
                rowSum += table[row][column];
                System.out.println(
                        row + " | " + column + " | " + table[row][column] + " | " + rowSum
                );
            }
        }

        // table.length는 행의 개수다.
        // table[row].length는 현재 행의 칸 수다.
        // Java의 2차원 배열은 행마다 길이가 다를 수 있다.
        // 바깥 반복문은 행을 고르고, 안쪽 반복문은 그 행의 값을 왼쪽부터 확인한다.

        // table[row][column]은 row번째 행에서 한 칸을 읽는다.
        // 같은 column을 두고 row를 바꾸면 같은 열의 다른 칸을 읽는다.
        int valueFromRow = table[0][1];
        int valueFromColumn = table[1][0];

        System.out.println("\n[6] 행과 열에서 한 칸씩 읽기");
        System.out.println("0번 행의 1번 열: " + valueFromRow);
        System.out.println("1번 행의 0번 열: " + valueFromColumn);
    }

    /*
     * 7. 문제에서 배열을 떠올릴 단서
     *
     * - 값의 개수가 먼저 정해져 있고, 처리 중 배열 길이를 바꿀 필요가 없다.
     * - "몇 번째 값"처럼 위치로 값을 찾아야 한다.
     * - 입력 순서나 위치별 결과를 그대로 보관해야 한다.
     * - 같은 배열의 여러 위치를 반복해서 조회하거나 계산한다.
     *
     * 배열은 만든 뒤 길이를 바꿀 수 없다.
     * 결과 개수를 모르면 먼저 개수를 세는 방법을 생각한다.
     */

    /*
     * 8. 자주 쓰는 연산과 실수
     *
     * 먼저 익힐 것
     * - values[index]           : 한 칸 읽기
     * - values[index] = value   : 한 칸 바꾸기
     * - values.length           : 전체 칸 수
     * - i < values.length       : 모든 칸을 안전하게 확인
     * - table[row][column]      : 2차원 배열의 한 칸 읽기
     * - table.length            : 행의 개수
     * - table[row].length       : 현재 행의 칸 수
     *
     * 필요할 때 사용할 것
     * - Arrays.toString(values) : 배열 내용 출력
     * - values.clone()          : 값을 복사한 새 배열 만들기
     * - Arrays.sort(values)     : 배열 자체를 오름차순으로 정렬하기
     * - pattern[i % pattern.length] : 짧은 배열을 반복해서 읽기
     *
     * 잘못: i <= values.length  -> 올바름: i < values.length
     * 공유가 목적: int[] same = values
     * 독립 복사:   int[] copy = values.clone()
     * 잘못: 빈 배열에서 values[0] 읽기
     * 잘못: column < table.length
     * 올바름: column < table[row].length
     * 주의: 큰 정수를 많이 더할 때는 int 대신 long이 필요할 수 있다.
     */

    /*
     * 9. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 출력: 값 하나인가, 배열인가? 결과 길이를 바로 알 수 있는가?
     * 2) 경계: 문제에서 위치를 1부터 세는가? Java 배열 인덱스로 어떻게 바꿀까?
     * 3) 모양: 2차원 배열이면 행마다 길이가 같은가? 현재 행의 길이는 어디서 읽는가?
     * 4) 순회: 값만 필요한가, 인덱스·이웃·이전 상태도 필요한가?
     * 5) 원본: 입력을 바꿔도 되는가, 새 배열이 필요한가?
     * 6) 검증: 제약에서 허용하는 빈 배열, 원소 하나, 첫 칸, 마지막 칸을 손으로 추적했는가?
     * 7) 효율: 입력이 커져도 같은 범위를 불필요하게 반복하지 않는가?
     * 8) 조합: 서로 다른 두 위치라면 두 번째 위치를 첫 번째 위치 다음부터 확인하면 되는가?
     */
}

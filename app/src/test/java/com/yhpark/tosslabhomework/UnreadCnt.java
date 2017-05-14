package com.yhpark.tosslabhomework;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ppyh0 on 2017-05-14.
 * <p>
 * 문항 N 개의 메세지가 있다. 각 메세지 번호는 +1씩 오름 차순이며 X 부터 시작한다. 예를 들어 N-5 X-100 이면 메세지 번호 배열은 [100, 101, 102, 103, 104] 이다.
 * A배열의 요소는 각 사람이 각 메세지 번호까지 읽었다는 것을 뜻한다. 예를 들어 A[] = {103, 102, 104} 이면 총 3사람이 0번 메세지부터 각 103 102 104번 메세지까지 읽은 것이다.
 * • 단 X번 부터 N 개 까지의 메세지는 전체 메세지의 일부이기 때문에 배열 A 의 Element는 해당 범위를 벗어날 수 있다.
 * <p>
 * X번 메세지 부터 N개까지의 메세지를 읽지 않은 사람의 숫자 배열로 리턴하라.
 * 예시 1 > N - 5 / X - 100 / A - [103, 102, 104]
 * 100 - 다 읽음 ( 세 사람 모두 103, 102, 104번까지 읽었으므로 )
 * 101 - 다 읽음
 * 102 - 다 읽음
 * 103 - 1사람 안 읽음 ( 한 사람이 102번까지 밖에 안 읽었으므로 )
 * 104 - 2 사람 안 읽음 ( 두 사람이 각 103, 102번까지 밖에 안 읽었으므로 )
 * RESULT 리턴 배열 - [0, 0, 0, 1, 2]
 * <p>
 * 예시 2 > N - 4 / X - 1000 / A - [ 900, 1001, 900, 1001, 705, 1002 ]
 * 1000 - 3사람 안 읽음
 * 1001 - 3사람 안 읽음
 * 1002 - 5사람 안 읽음
 * 1003 - 6사람 안 읽음
 * RESULT 리턴 배열 - [3, 3, 5, 6]
 * <p>
 * 예시 3 > N - 6 / X - 1 / A - [ 3, 3, 5, 3, 3 ]
 * 1 - 다 읽음
 * 2 - 다 읽음
 * 3 - 다 읽음
 * 4 - 4사람 안 읽음
 * 5 - 4사람 안 읽음
 * 6 - 5사람 안 읽음
 * RESULT 리턴 배열 - [ 0, 0, 0, 4, 4, 5 ] *
 * <p>
 * [ Input Description ]
 * N - int형, N >= 0
 * X - int형, X >= 0
 * A배열의 Element - 양수 int형
 * A배열의 Max Size - 100000
 * 유의사항
 * 1. 평가 기준
 * 1 ) 가장 빠른 시간 시간 복잡도 ( O(N * A.size ) 는 피하도록 할 것 )
 * 2 ) 제출 시간 단, 자료구조 라이브러리(Collection)은 다양하게 사용 가능 ( 공간 복잡도 무시 )
 * 2. 메서드 원형
 * int[] getUnreadCnts(intN, int X, int[] A) {
 * // 구현 하시오.
 * }
 */

public class UnreadCnt {

    @Test
    public void solution() {
        Assert.assertArrayEquals(new int[]{0, 0, 0, 1, 2}, getUnreadCnts(5, 100, new int[]{103, 102, 104}));
        Assert.assertArrayEquals(new int[]{3, 3, 5, 6}, getUnreadCnts(4, 1000, new int[]{900, 1001, 900, 1001, 705, 1002}));
        Assert.assertArrayEquals(new int[]{0, 0, 0, 4, 4, 5}, getUnreadCnts(6, 1, new int[]{3, 3, 5, 3, 3}));
    }

    // ( O(N * A.size ) 는 피하도록 할 것 )
    public int[] getUnreadCnts(int N, int X, int[] A) {
        int[] result = new int[N];
        for (int i = 0; i < result.length; i++) {
            result[i] = A.length;
        }

        int a = 0;
        int b = 0;

        int temp = X;

        while (a < A.length) {
            if (temp <= A[a]) {
                result[b] = result[b] - 1;
                temp++;
                b++;
            } else {
                temp = X;
                a++;
                b = 0;
            }
        }

        return result;
    }
}

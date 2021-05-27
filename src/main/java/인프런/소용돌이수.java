package 인프런;

public class 소용돌이수 {
    public static int solution(int n) {
        int[][] map = new int[n+2][n+2];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int cnt = 1, loops = n, dir = 0;

        int x = 0, y = 0;
        while (cnt <= n * 2 - 1) {
            for (int i = 0; i < loops-1; i++) {
                map[x][y] = cnt++;
                x += dx[dir];
                y += dy[dir];
            }

            dir = (dir + 1) % 4;
            if (dir % 2 == 1) loops--;
        }
        System.out.println();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += map[i][i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        return answer;
    }

    public static void main(String[] args) {
        int solution = solution(3);
        System.out.println(solution);


    }


}

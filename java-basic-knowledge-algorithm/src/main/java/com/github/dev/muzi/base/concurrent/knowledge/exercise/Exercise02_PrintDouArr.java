package com.github.dev.muzi.base.concurrent.knowledge.exercise;

/**
 * 面试题29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 示例 1：
 *      输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *      输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *      输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *      输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 限制：
 *      0 <= matrix.length <= 100
 *      0 <= matrix[i].length <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Exercise02_PrintDouArr {


    /*
     *      xmin          xmax
     *      ----------------> x轴
     * ymin |
     *      |
     *      |
     *      |
     * ymax V
     *      y轴
     */
    public static int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int xmin = 0, ymin = 0, xmax = matrix[0].length - 1, ymax = matrix.length - 1, count = 0;
        int[] result = new int[matrix[0].length  * matrix.length];
        for (; ; ) {
            // ymin点关联点x轴从xmin走到xmax
            for (int i = xmin; i <= xmax; i++) {
                result[count++] = matrix[ymin][i];
            }
            // ymin需要自增（顺时针走了一行，行向下压缩）
            if (++ymin > ymax){
                break;
            }

            // xmax点关联点y轴从ymin走到ymax
            for (int i = ymin; i <= ymax; i++) {
                result[count++] = matrix[i][xmax];
            }
            // xmax需要自减（顺时针走了一列，向左压缩）
            if (--xmax < xmin){
                break;
            }

            // ymax关联的点x轴从xmax走到xmin
            for (int i = xmax; i >= xmin; i--) {
                result[count++] = matrix[ymax][i];
            }
            // ymax需要自减（顺时针走了一行，向上压缩）
            if (--ymax < ymin){
                break;
            }

            // xmin关联点点y轴从ymax走到ymin
            for (int i = ymax; i >= ymin; i--) {
                result[count++] = matrix[i][xmin];
            }
            // xmin自增（向右压缩）
            if (++xmin > xmax){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        System.out.println();
        System.out.println();

        int[] res = spiralOrder(matrix);
        for (int i = 0; i <res.length  ; i++) {
            System.out.print( res[i] + " ");

        }
    }
}

// package src.Funtions;
// import java.util.Scanner;
// import src.Matrix.matrixOP;

// public class bicubic {
//     public static Scanner scan;

//     public static double[][] resizeMatrix(double[][] m, int oldRow, int oldCol, int newRow, int newCol) {
//         double[][] newM = new double[newRow][newCol];

//         if (oldRow * oldCol != newRow * newCol) {
//             return newM;
//         }
//         // pindahin isi
//         int idx = 0;
//         for (int i = 0; i < oldRow; i++) {
//             for (int j = 0; j < oldCol; j++) {
//                 newM[idx/oldCol][idx%newCol] = m[i][j];
//                 idx++;
//             }
//         }
//         return newM;
//     }

//     public static double[][] bicubicInterpolation (double[][] m, int x, int y) {
//         int i, j;
//         int row = 0;
//         int col = 0;

//         // resize matriks jadi 16x1
//         double[][] mResized = resizeMatrix(m, 4, 4, 16, 1);

//         double[][] mBesar = new double[16][16];
//         for (y = -1; y < 4; y++) {
//             for (x = -1; x < 4; x++) {
//                 for (j = 0; j < 4; j++) {
//                     for (i = 0; i < 4; i++) {
//                         mBesar[row][col] = (Math.pow(x, i) * Math.pow(y, j));
//                         col++; // isinya per row
//                     }
//                 }
//                 row++;
//             }
//         }
//     }
// }

# Cholesky-Decomposition
CholeskyDecomposition
 
wiki - https://ru.wikipedia.org/wiki/Разложение_Холецкого

данный класс разлкладывает матрицу методом Холе́цког
Основной класс - CholeskyDecomposition

содержит два основных метода:
double[][] cholesky( double[][]A)
double[] FindeX(double[][] A, double[] B)

cholesky( double[][]A ) - непосредственно раскладывать входную матрицу А методом Холецкого и возвращает результат, матрицу L.

FindeX(double[][] A, double[] B) - находит вектор X уравнения типа Ax=b методом Холецкого.
В качестве входных данных идут Матрица А и вектор b.

Также содержит методы:
double[][] Transpose(double[][]L) - транспонирует матрицу
double determinant(double[][]A) - находит детерминант
double[][] inverseA(double[][]A) - находит обратную матрицу
double[] multiplyMatrixOnVector(double[][] A, double[] vector) - умнажает матрицу на вектор
double[][] multiplyMatrixOnMatrix(double[][] A, double[][] B) - умнажает матрицу на матрицу
double[][] cofactor(double[][] A) - находит матрицу с минорами елементов матрицы А

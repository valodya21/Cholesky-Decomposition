package cholesky;

public class CholeskyDecomposition{
    public double[] FindeX(double[][] A, double[] B){
        boolean error = false;
        
        for(int i=0; i < A.length; i++)
            for(int j=0; j < A[i].length; j++)
                if(A[i][j] < 0){
                    System.out.println("A["+(i+1)+"]["+(j+1)+"]= "+A[i][j]+" < 0");
                    error = true;
                }
                
        if(!error){
            double[][] L = cholesky(A);
            double[][] inverseL = inverseA(L);
            double[] Y = multiplyMatrixOnVector(inverseL, B);
            
            double[][] Lt = Transpose(L);
            double[][] inverseLt = inverseA(Lt);
            double[] X = multiplyMatrixOnVector(inverseLt, Y);
            return X;
        }
        
        double[] ret = new double[1];
        ret[0] = -1.0;
        return ret;
    }
    
    public double[][] cholesky( double[][]A ){
        double[][] L = new double[A.length][A.length];
            
        L[0][0] = Math.sqrt(A[0][0]);
            
        for(int i=1; i< A.length; i++)
            L[i][0] = A[i][0]/L[0][0];
            
        for(int i=1; i< A.length; i++)
        {
            double sum1 = 0;
            for(int p=0; p<=i; p++)
                sum1+= L[i][p]*L[i][p];
            
            L[i][i] = Math.sqrt(A[i][i] - sum1);
            
            for(int j=i+1; j<A.length; j++){
                
                double sum2 = 0;
                for(int p=0; p<=i; p++)
                    sum2+= L[i][p]*L[j][p];
                
                L[j][i] = (A[j][i]-sum2)/L[i][i];
            }
        }
        return L;
    }
    
    public double[][] Transpose(double[][]L){
        double [][] LT = new double[L.length][L.length];
        
        for(int i=0; i<L.length; i++)
            for(int j=0; j<L.length; j++)
                LT[i][j] = L[j][i];
        
        return LT;
    }
    
    public double determinant(double[][]A){
        if(A.length == 2)
            return (A[0][0]*A[1][1] - A[1][0]*A[0][1]);
        
        else if(A.length >= 3){
            double step = 1.0;
            double answer = 0;
            for(int j=0; j< A.length; j++){
                double[][] matrix = new double[A.length-1][A.length-1];
                
                for(int ii = 0; ii<matrix.length ;ii++)
                    for(int jj = 0; jj<matrix.length ;jj++)
                            if(jj>=j) matrix[ii][jj] = A[ii+1][jj+1];
                            else matrix[ii][jj] = A[ii+1][jj];
                
                answer += step*A[0][j]*determinant(matrix);
                if(step > 0) step = -1.0;
                else step = 1.0;
            }
            return answer;
        }else if(A.length == 1) return A[0][0];
        return -1;
    }
    
    public double[][] inverseA(double[][]A){
        double[][]answer = new double[A.length][A.length];
        
        double D = 1.0/determinant(A);
        
        double[][]At = Transpose(cofactor(A));
        
        for(int i=0;i<A.length;i++)
            for(int j=0;j<A.length;j++)
                answer[i][j] = D*At[i][j];
        
        return answer;
    }
    
    public double[] multiplyMatrixOnVector(double[][] A, double[] vector){
        double[] answer = new double[vector.length];
        for(int i=0; i<A.length;i++){   
            answer[i]=0;
            for(int j=0; j<A.length; j++)
                answer[i] += A[i][j]*vector[j]; 
        }
        return answer;
    }
    
    public double[][] multiplyMatrixOnMatrix(double[][] A, double[][] B){
        double[][] answer = new double[B.length][B.length];
        
        for(int i=0; i<A.length;i++){
            for(int j=0; j<A.length; j++)
                for(int r=0; r<A.length; r++)
                    answer[i][j] += A[i][r]*B[r][j];
        }   
        
        return answer;
    }
    
    public double[][] cofactor(double[][] A){
        double[][] answer = new double[A.length][A.length];
        double[][] matrix = new double[A.length-1][A.length-1];
        
        for(int i=0; i < A.length; i++)
            for(int j=0; j < A.length; j++){
                for(int I=0; I < matrix.length; I++)
                for(int J=0; J < matrix.length; J++)
                        if(J>=j && I>=i) matrix[I][J] = A[I+1][J+1];
                        else if(J>=j && I<i)  matrix[I][J] = A[I][J+1];
                        else if(J<j && I>=i)  matrix[I][J] = A[I+1][J];
                        else matrix[I][J] = A[I][J];
                answer[i][j] = Math.pow(-1, i+j)*determinant(matrix);
            }
        return answer;
    }
}

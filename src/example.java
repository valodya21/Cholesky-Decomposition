public class example{
    public static void main(String[] args){

        double[][]A = {{5, 2, 3},
                       {2, 6, 1},
                       {3, 1, 7}};
        
        double[] B = {10, 20, 30};

        ch = new CholeskyDecomposition();
        double[] answer = ch.FindeX(A, B);
        
        System.out.println("\nX=");
        for(int i=0; i<answer.length; i++)
            System.out.println(answer[i]);
    }
}

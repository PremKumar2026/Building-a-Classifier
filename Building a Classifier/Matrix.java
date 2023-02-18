
import java.util.Random;

public class Matrix {
    
    // Copy all your Matrix code from a previous lab here
    
    private double[][] vals;
    
    public String toString() {
        String result = "";
        
        result += "{\n";
        for(int r = 0; r < vals.length; r++) {
            result += " {";
            for(int c = 0; c < vals[0].length-1; c++) {
                result += vals[r][c] + ", ";
            }
            result += vals[r][vals[0].length-1] + "}\n";
        }
        result += "}\n";
        
        return result;
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    // Part 2
    
    public Matrix(int initnumRows,int initnumCols){
        vals = new double[initnumRows][initnumCols];
    }
    
    public Matrix(double[][] initVals){
        vals = initVals;
    }
    
    public int getNumRows(){
        return vals.length;
    }
    
    public int getNumCols(){
        return vals[0].length;
    }
    
    public double get(int row, int col){
        return vals[row][col];
    }
    
    public boolean hasSameNumRowsAs(Matrix other){
        return this.getNumRows() == other.getNumRows();
    }
    
    public boolean hasSameNumColsAs(Matrix other){
        return this.getNumCols() == other.getNumCols();
    }
    
    public double sumAll(){
        double sum = 0;
        for (int r = 0; r < this.getNumRows(); r++){
            for (int c = 0; c < this.getNumCols(); c++){
                sum += this.get(r, c);
            }
        }
        return sum;
    }
    
    public Matrix plusScalar(double x){
        double sum;
        double [][] result = new double[vals.length][vals[0].length];
        for (int r = 0; r < this.vals.length; r++){
            for (int c = 0; c < this.vals[0].length; c++){
                sum = x + this.vals[r][c];
                result[r][c] = sum;
            }
        }
        return new Matrix(result);
    }
    
    public Matrix timesScalar(double x){
        double sum;
        double [][] result = new double[vals.length][vals[0].length];
        for (int r = 0; r < this.vals.length; r++){
            for (int c = 0; c < this.vals[0].length; c++){
                sum = x * this.vals[r][c];
                result[r][c] = sum;
            }
        }
        return new Matrix(result);
    }
    
    public Matrix elementwisePlus(Matrix other){
        double sum;
        double [][] result = new double[vals.length][vals[0].length];
        if (this.hasSameNumRowsAs(other) && (this.hasSameNumColsAs(other)))
            for (int r = 0; r < this.vals.length; r++){
                for (int c = 0; c < this.vals[0].length; c++){
                    sum = this.vals[r][c] + other.get(r, c);
                    result[r][c] = sum;
                }
            }
        else
            throw new MatrixException("Matrices with dimensions (" + this.vals.length + ", " + this.vals[0].length + ") and (" + other.vals.length + ", " + other.vals[0].length + ") can't be added.");
        return new Matrix(result);
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    // Part 3
    
    public Matrix transpose(){
        int temp;
        double tempVal;
        double [][] result = new double[vals[0].length][vals.length];
        for (int r = 0; r < this.vals.length; r++){
            for (int c = 0; c < this.vals[0].length; c++){
                result[c][r] = this.vals[r][c];
                }
            }
        return new Matrix(result);
    }
    
    public Matrix sigmoid(){
        double [][] result = new double[vals.length][vals[0].length];
        for (int r = 0; r < this.vals.length; r++){
            for (int c = 0; c < this.vals[0].length; c++){
                result[r][c] = (1/(1 + Math.exp(-(this.get(r, c)))));
            }
        }
        return new Matrix(result);
    }
    ///////////////////////////////////////////////////////////////////////////////
    // Given
        
    public String toPartialString(int size) {
        String result = "";
        
        result += "Shape: (" + getNumRows() + ", " + getNumCols() + ")\n";
        
        int rStop;
        int cStop;
        if (vals.length < size)
            rStop = vals.length;
        else
            rStop = size;
            
        if (vals[0].length < size)
            cStop = vals[0].length;
        else
            cStop = size;
        // int rStop = vals.length < size ? vals.length : size;
        // int cStop = vals[0].length < size ? vals[0].length : size;
        
        result += "{\n";
        for(int r = 0; r < rStop; r++) {
            result += " {";
            for(int c = 0; c < cStop-1; c++) {
                result += vals[r][c] + ", ";
            }
            result += vals[r][cStop-1];
            
            if (vals[0].length > size) // if there are more columns than what we're printing
                result += ", ...";      // include ... at the end of the row
                
            // Put a comma at the end of each row except the last in the full matrix
            if (r == vals.length-1)
                result += "}\n";
            else
                result += "},\n";
        }
        
        if (rStop < vals.length) // if there are more rows, put elipses here
            result += " ...\n";
        
        result += "}\n";
        
        return result;
    }
    
    public Matrix matMult(Matrix other){
        double [][] result = new double[this.vals.length][other.vals[0].length];
        double sum;
        if (this.vals[0].length != other.vals.length){
            throw new MatrixException("Matrices with dimensions (" + this.vals.length + ", " + this.vals[0].length + ") and (" + other.vals.length + ", " + other.vals[0].length + ") can't be added.");
        }
        for (int r = 0; r < result.length; r++){
            for (int c = 0; c < result[0].length; c++){
                sum = 0;
                for (int i = 0; i < this.vals[0].length; i++){
                        sum += this.get(r, i) * other.get(i, c);
                    }
                result[r][c] = sum;
            }
        }
        return new Matrix(result);
    }
}

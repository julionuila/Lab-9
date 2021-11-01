package src;

public class Cord{
    private int rowNum;
    private int columnNum ;
    public Cord(int rowNum, int columnNum){
        this.rowNum = rowNum;
        this.columnNum = columnNum;
    }
    public Cord(String cordString){
        this.rowNum = Integer.parseInt(cordString.substring(0,cordString.indexOf(',')));
        this.columnNum = Integer.parseInt(cordString.substring(cordString.indexOf(',')+1));
    }
    public int getRowNum() {
        return rowNum;
    }
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
    public int getColumnNum() {
        return columnNum;
    }
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }
    @Override
    public String toString() {
        return "Cord [columnNum=" + columnNum + ", rowNum=" + rowNum + "]";
    }

}
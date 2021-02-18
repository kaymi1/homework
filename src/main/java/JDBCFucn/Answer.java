package JDBCFucn;

class Answer {
    private int n;
    private String result;

    public Answer(int n) {
        this.setN(n);
    }

    public Answer(int n, String result) {
        this.setN(n);
        this.setResult(result);
    }

    public Answer(){}

    public int getN() {
        return n;
    }

    public String getResult() {
        return result;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

package compile;

public class Answer {
    //通过 error 表示当前的错误类型
    //约定 error 为0表示没有错误，error 为1表示编译出错，error为2表示运行出错。
    private int error;
    //表示具体的出错原因，可能是编译错误，也有可能是运行错误（异常信息）
    private String reason;
    //执行时标准输出对应的内容
    private String stdout;
    //执行时标准错误对应的内容
    private String stderr;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "error=" + error +
                ", reason='" + reason + '\'' +
                ", stdout='" + stdout + '\'' +
                ", stderr='" + stderr + '\'' +
                '}';
    }
}

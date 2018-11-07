package io.fourfinanceit.requestparam;


import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

public class LoanRequestParam {

    @QueryParam("userName")
    @NotNull
    private String userName;

    @QueryParam("amount")
    @NotNull
    private int loanAmount;

    private String remoteAddr;

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }
}

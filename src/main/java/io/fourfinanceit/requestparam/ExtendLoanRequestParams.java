package io.fourfinanceit.requestparam;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

public class ExtendLoanRequestParams {

    @NotNull
    @QueryParam("loanId")
    private long loanId;

    @NotNull
    @QueryParam("userName")
    private String userName;

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

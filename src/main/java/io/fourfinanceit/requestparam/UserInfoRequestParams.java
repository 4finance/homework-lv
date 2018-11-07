package io.fourfinanceit.requestparam;

import org.hibernate.validator.constraints.NotBlank;

import javax.ws.rs.QueryParam;

public class UserInfoRequestParams {

    @NotBlank
    @QueryParam("userName")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

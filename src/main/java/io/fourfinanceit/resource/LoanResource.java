package io.fourfinanceit.resource;

import com.google.inject.Inject;
import io.fourfinanceit.requestparam.ExtendLoanRequestParams;
import io.fourfinanceit.requestparam.LoanRequestParam;
import io.fourfinanceit.requestparam.PayLoanRequestParams;
import io.fourfinanceit.requestparam.UserInfoRequestParams;
import io.fourfinanceit.service.LoanService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class LoanResource {

    private LoanService loanService;

    @Inject
    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    @GET
    @Path("/getInfo")
    public Response getInfo(@BeanParam @Valid UserInfoRequestParams requestParams) {
        return callService(() -> loanService.getByName(requestParams));
    }

    @GET
    @Path("/applyForLoan")
    public Response applyForLoan(@BeanParam @Valid LoanRequestParam requestParams,
                                 @Context HttpServletRequest req) {
        requestParams.setRemoteAddr(req.getRemoteAddr());
        return callService(() -> loanService.applyForLoan(requestParams));
    }

    @GET
    @Path("/payLoan")
    public Response payLoan(@BeanParam @Valid PayLoanRequestParams requestParams) {
        return callService(() -> loanService.payLoan(requestParams));
    }

    @GET
    @Path("/extendLoan")
    public Response extendLoan(@BeanParam @Valid ExtendLoanRequestParams requestParams) {
        return callService(() -> loanService.extendLoan(requestParams));
    }

    @GET
    @Path("/init")
    public Response init(@QueryParam("name") String name) {
        return callService(() -> loanService.initialise(name));
    }

    protected Response callService(Supplier<CompletableFuture<Response>> supplier) {
        return supplier
                .get()
                .exceptionally(exception ->
                        Response.serverError()
                                .entity(exception.getMessage())
                                .status(Response.Status.INTERNAL_SERVER_ERROR)
                                .build()
                ).join();
    }
}

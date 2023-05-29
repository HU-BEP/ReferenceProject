package nl.hu.bep.referenceproject.webservices;

import nl.hu.bep.referenceproject.model.Account;
import nl.hu.bep.referenceproject.model.Company;
import nl.hu.bep.referenceproject.persistence.EncodedBase64;
import nl.hu.bep.referenceproject.persistence.UploadsManager;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.AbstractMap;

@Path("/accounts")
public class AccountsResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccountFullJackson(Account account) {
        if (Company.getCompany().addAccount(account)) {
            if (!account.getAvatarBase64().isEmpty()) {
                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
                String uploadId = UploadsManager.saveUploadToAzure(base64);
                account.setAvatarUploadId(uploadId);
            }

            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

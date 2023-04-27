package calculationApi;
//This class will contain the REST API endpoints for performing calculations and retrieving calculation history.

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class CalculationResource {
    private CalculationService calculationService = new CalculationService();
    
    @POST
    @Path("/calc")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CalculationResult createCalculation(Calculation calculation) {
        try {
            return calculationService.performCalculation(calculation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @GET
    @Path("/calculations")
    @Produces(MediaType.APPLICATION_JSON)
    public int[] getCalculations() {
        try {
            int lastResult = calculationService.getLastCalculationResult();
            return new int[] { lastResult };
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


package rso.project.ratings.api.v1.resources;

import rso.project.ratings.Rating;
import rso.project.ratings.services.RatingsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/ratings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RatingsResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private RatingsBean ratingsBean;

    @GET
    public Response getRatings() {

        List<Rating> ratings = ratingsBean.getRatings(uriInfo);

        return Response.ok(ratings).build();
    }

    @GET
    @Path("/{ratingId}")
    public Response getRating(@PathParam("ratingId") String ratingId) {

        Rating rating = ratingsBean.getRating(ratingId);

        if (rating == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(rating).build();
    }

    @POST
    public Response createRating(Rating rating) {

        if (rating.getName() == null || rating.getName().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            rating = ratingsBean.createRating(rating);
        }

        if (rating.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(rating).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(rating).build();
        }
    }

    @PUT
    @Path("{ratingId}")
    public Response putRating(@PathParam("ratingId") String ratingId, Rating rating) {

        rating = ratingsBean.putRating(ratingId, rating);

        if (rating == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (rating.getId() != null)
                return Response.status(Response.Status.OK).entity(rating).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{ratingId}")
    public Response deleteCustomer(@PathParam("ratingId") String ratingId) {

        boolean deleted = ratingsBean.deleteRating(ratingId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

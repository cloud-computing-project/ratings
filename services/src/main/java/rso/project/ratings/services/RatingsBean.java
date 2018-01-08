package rso.project.ratings.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import rso.project.ratings.Rating;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
public class RatingsBean {

    @Inject
    private EntityManager em;

    public List<Rating> getRatings(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Rating.class, queryParameters);

    }

    public Rating getRating(String ratingId) {

        Rating rating = em.find(Rating.class, ratingId);

        if (rating == null) {
            throw new NotFoundException();
        }

        return rating;
    }

    public Rating createRating(Rating rating) {

        try {
            beginTx();
            em.persist(rating);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return rating;
    }

    public Rating putRating(String ratingId, Rating rating) {

        Rating c = em.find(Rating.class, ratingId);

        if (c == null) {
            return null;
        }

        try {
            beginTx();
            rating.setId(c.getId());
            rating = em.merge(rating);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return rating;
    }

    public boolean deleteRating(String ratingId) {

        Rating rating = em.find(Rating.class, ratingId);

        if (rating != null) {
            try {
                beginTx();
                em.remove(rating);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}

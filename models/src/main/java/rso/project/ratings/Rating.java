package rso.project.ratings;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "ratings")
@NamedQueries(value =
        {
                @NamedQuery(name = "Rating.getAll", query = "SELECT o FROM ratings o"),
                @NamedQuery(name = "Rating.findByTitle", query = "SELECT o FROM ratings o WHERE o.productId = " +
                        ":productId")
        })
@UuidGenerator(name = "idGenerator")
public class Rating {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String productId;

    private String rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){return productId;}

    public void setName(String name) {this.productId = productId;}

    public String getDescription(){return rating;}

    public void setDescription(String description){this.rating = rating;}


}

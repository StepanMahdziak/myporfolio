package mahdziak.cars.saloncars.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "country")
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    private List<User> users = new ArrayList<>();

}

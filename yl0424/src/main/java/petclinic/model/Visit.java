package petclinic.model;

import javax.persistence.*;

@Entity
@Table(name="all_visits")
public class Visit {

    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private Pet pet;

    @ManyToOne
    private Vet vet;

    private String description;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void deleteVisit(){
        if(pet.getVisits().contains(this)){
            pet.getVisits().remove(this);
        }
        if(vet.getVisits().contains(this)){
            vet.getVisits().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Visit{" +
                "description='" + description + '\'' +
                ", pet=" + pet.getName() +
                ", vet=" + vet.getFirstName() + " " + vet.getLastName() +
                '}';
    }
}

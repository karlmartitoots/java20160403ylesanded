package petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="all_owners")
public class Owner extends Person {

  // one Owner has many Pets
  @OneToMany(cascade = CascadeType.ALL,
          orphanRemoval = true)
  private Set<Pet> pets = new HashSet<>();

  public Set<Pet> getPets() {
    return this.pets;
  }

  public void addPet(Pet pet) {
    pets.add(pet);
    pet.setOwner(this);
  }

  public void removePet(Pet pet){
    if(pets.contains(pet)){
      pets.remove(pet);
    }
  }

  @Override
  public String toString() {
    return super.toString() + " + Owner{" +
        "pets=" + pets +
        '}';
  }
}

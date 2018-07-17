package petclinic.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="all_vets")
public class Vet extends Person {

  @ElementCollection
  private Set<String> specialties = new HashSet<>();

  public Set<String> getSpecialties() {
    return Collections.unmodifiableSet(this.specialties);
  }

  public void addSpecialty(String specialty) {
    specialties.add(specialty);
  }

  @OneToMany(cascade = CascadeType.ALL,
          orphanRemoval = true)
  private Set<Visit> visits = new HashSet<>();

  public Set<Visit> getVisits() {
    return this.visits;
  }

  public void addVisit(Visit newVisit) {
    visits.add(newVisit);
  }

  @Override
  public String toString() {
    return super.toString() + " + Vet{" +
        "specialties=" + specialties +
        ", visits=" + visits +
        '}';
  }
}

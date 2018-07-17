package petclinic.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "all_pets")
public class Pet {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "pet_name")
  private String name;

  @Column(name = "pet_birthdate")
  private LocalDate birthDate;

  // many Pets can belong to one Owner
  @ManyToOne
  private Owner owner;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Owner getOwner() {
    return this.owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  @OneToMany(cascade = CascadeType.ALL)
  @Cascade(org.hibernate.annotations.CascadeType.DELETE)
  private Set<Visit> visits = new HashSet<>();

  public Set<Visit> getVisits() {
    return this.visits;
  }

  public void addVisit(Visit visit){
    this.visits.add(visit);
  }

  public void rename(String newName){
    this.name = newName;
  }

  @Override
  public String toString() {
    return "Pet{" +
        "name='" + name + '\'' +
        ", birthDate=" + birthDate +
        ", owner=" + owner.getFirstName() + " " + owner.getLastName() +
        ", visits=" + visits +
        '}';
  }
}

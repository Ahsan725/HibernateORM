import javax.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pet_id")
  private int petId;

  @Column(name = "pet_name")
  private String name;

  @Column(name = "species")
  private String species;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private Owner owner;

  public Pet() {

  }
  public Pet(String name, String species, Owner owner) {
      this.name = name;
      this.species = species;
      this.owner = owner;
  }

  public int getPetId() {
    return petId;
  }
  public void setPetId(int petId) {
    this.petId = petId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSpecies() {
    return species;
  }
  public void setSpecies(String species) {
    this.species = species;
  }
  public Owner getOwner() {
    return owner;
  }
  public void setOwner(Owner owner) {
    this.owner = owner;
  }
}
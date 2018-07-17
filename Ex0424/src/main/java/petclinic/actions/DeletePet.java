package petclinic.actions;

import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.model.Vet;
import petclinic.model.Visit;

import javax.persistence.EntityManager;
import java.util.List;

public class DeletePet extends TransactionalCommand{

    public DeletePet() {
        super("deletepet");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if(params.length != 2){
            System.out.println("deletepet <petName>");
            return;
        }

        List<Pet> pets = entityManager.createQuery("from Pet", Pet.class).getResultList();
        Pet thePet = getPet(params[1], pets);
        if (thePet == null) return;

        Owner theOwner = entityManager.find(Owner.class, thePet.getOwner().getId());
        theOwner.removePet(thePet);
        for (Visit visit : thePet.getVisits()) {
            entityManager.find(Vet.class, visit.getVet().getId());
            entityManager.find(Pet.class, visit.getPet().getId());
            visit.deleteVisit();
        }
        entityManager.remove(thePet);
        System.out.println("Removed pet: " + thePet);
    }

    private Pet getPet(String param, List<Pet> pets) {
        if(pets.isEmpty()){
            System.out.println("No pets.");
            return null;
        }

        boolean petExists = false;
        Pet thePet = null;
        for (Pet pet : pets) {
            if(pet.getName().equals(param)){
                petExists = true;
                thePet = pet;
                break;
            }
        }
        if(!petExists){
            System.out.println("Specified pet doesn't exist.");
            return null;
        }
        return thePet;
    }
}

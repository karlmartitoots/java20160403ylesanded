package petclinic.actions;

import petclinic.model.Pet;
import petclinic.model.Visit;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class ViewPetsVisits extends TransactionalCommand{

    public ViewPetsVisits() {
        super("viewpetsvisits");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if(params.length != 2){
            System.out.println("viewpetsvisits <petName>");
            return;
        }

        List<Pet> pets = entityManager.createQuery("from Pet", Pet.class).getResultList();
        Pet thePet = getPet(params[1], pets);
        if (thePet == null) return;

        Set<Visit> theVisits = thePet.getVisits();
        if(theVisits.isEmpty()){
            System.out.println("No visits.");
        }

        for (Visit visit : theVisits) {
            System.out.println(visit);
        }
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

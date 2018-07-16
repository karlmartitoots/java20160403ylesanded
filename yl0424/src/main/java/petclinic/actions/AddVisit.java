package petclinic.actions;

import petclinic.model.Pet;
import petclinic.model.Vet;
import petclinic.model.Visit;

import javax.persistence.EntityManager;
import java.util.List;

public class AddVisit extends TransactionalCommand{

    public AddVisit() {
        super("addvisit");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if(params.length != 4){
            System.out.println("addvisit <vetId> <petName> <visitDescription>");
            return;
        }

        List<Vet> vets = entityManager.createQuery("from Vet", Vet.class).getResultList();
        Vet theVet = getVet(params[1], vets);
        if (theVet == null) return;

        List<Pet> pets = entityManager.createQuery("from Pet", Pet.class).getResultList();
        Pet thePet = getPet(params[2], pets);
        if (thePet == null) return;

        Visit visit = new Visit();
        visit.setPet(thePet);
        thePet.addVisit(visit);
        visit.setVet(theVet);
        theVet.addVisit(visit);
        visit.setDescription(params[3]);
        entityManager.persist(visit);
        System.out.println("added " + visit);

    }

    private Pet getPet(String param, List<Pet> pets) {
        Pet thePet = null;
        if(pets.isEmpty()){
            System.out.println("No pets.");
            return null;
        }
        boolean petExists = false;
        for (Pet pet : pets) {
            if (pet.getName().equals(param)){
                petExists = true;
                thePet = pet;
                break;
            }
        }
        if(!petExists){
            System.out.println("Pet doesn't exist.");
            return null;
        }
        return thePet;
    }

    private Vet getVet(String param, List<Vet> vets) {
        Vet theVet = null;
        if (vets.isEmpty()) {
            System.out.println("No vets.");
        }
        boolean vetExists = false;
        for (Vet vet : vets) {
            try {
                if (String.valueOf(vet.getId()).equals(param)) {
                    vetExists = true;
                    theVet = vet;
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("<vetId> has to be Long type!");
                return null;
            }
        }
        if(!vetExists){
            System.out.println("Vet doesn't exist.");
            return null;
        }
        return theVet;
    }
}

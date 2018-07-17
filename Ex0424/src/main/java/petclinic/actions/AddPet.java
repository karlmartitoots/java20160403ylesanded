package petclinic.actions;

import petclinic.model.Owner;
import petclinic.model.Pet;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class AddPet extends TransactionalCommand{

    public AddPet() {
        super("addpet");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if (params.length != 6) {
            System.out.println("addpet <ownersId> <petsName> <petBirthYear> <petBirthMonth> <petBirthDayOfMonth>");
            return;
        }

        List<Owner> owners = entityManager.createQuery("from Owner", Owner.class).getResultList();
        Owner theOwner = getOwner(params[1], owners);
        if (theOwner == null) return;

        Pet pet = new Pet();
        pet.setName(params[2]);
        try {
            pet.setBirthDate(LocalDate.of(Integer.parseInt(params[3]), Integer.parseInt(params[4]), Integer.parseInt(params[5])));
        }catch(NumberFormatException e){
            System.out.println("Birth date can only be an integer!");
            return;
        }
        theOwner.addPet(pet);
        entityManager.persist(pet);
        System.out.println("added " + pet);
    }

    private Owner getOwner(String param, List<Owner> owners) {
        if (owners.isEmpty()) {
            System.out.println("No owners yet.");
            return null;
        }

        boolean ownerExists = false;
        Owner theOwner = null;
        for (Owner owner : owners) {
            if (String.valueOf(owner.getId()).equals(param)){
                ownerExists = true;
                theOwner = owner;
            }
        }

        if(!ownerExists){
            System.out.println("Owner doesn't exist.");
            return null;
        }
        return theOwner;
    }
}

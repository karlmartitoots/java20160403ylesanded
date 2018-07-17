package petclinic.actions;

import petclinic.model.Pet;

import javax.persistence.EntityManager;
import java.util.List;

public class RenamePet extends TransactionalCommand{

    public RenamePet() {
        super("renamepet");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if (params.length != 3) {
            System.out.println("renamepet <currentName> <newName>");
            return;
        }

        List<Pet> pets = entityManager.createQuery("from Pet", Pet.class).getResultList();
        Pet thePet = getPet(params[1], pets);
        if (thePet == null) return;

        thePet.rename(params[2]);
        entityManager.createQuery("update Pet p " +
                "set p.name = :newName " +
                "where p.name = :oldName" )
                .setParameter("oldName", params[1])
                .setParameter("newName", params[2])
                .executeUpdate();
        System.out.println("renamed " + thePet);
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

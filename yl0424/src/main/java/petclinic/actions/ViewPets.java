package petclinic.actions;

import petclinic.model.Pet;

import javax.persistence.EntityManager;
import java.util.List;

public class ViewPets extends TransactionalCommand{

    public ViewPets() {
        super("viewpets");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if(params.length != 1){
            System.out.println("viewpets");
            return;
        }

        if(!params[0].equals("viewpets")){
            return;
        }

        List<Pet> pets = entityManager.createQuery("from Pet", Pet.class).getResultList();
        if (pets.isEmpty()) {
            System.out.println("No pets.");
        }

        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }
}

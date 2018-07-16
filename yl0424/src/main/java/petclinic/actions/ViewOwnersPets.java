package petclinic.actions;

import petclinic.model.Owner;
import petclinic.model.Pet;

import javax.persistence.EntityManager;
import java.util.List;

public class ViewOwnersPets extends TransactionalCommand {

    public ViewOwnersPets() {
        super("viewownerspets");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if (params.length != 2) {
            System.out.println("viewownerspets <ownersId>");
            return;
        }

        List<Owner> owners = entityManager.createQuery("from Owner", Owner.class).getResultList();

        Owner theOwner = getOwner(params[1], owners);
        if (theOwner == null) return;

        for (Pet pet : theOwner.getPets()) {
            System.out.println(pet);
        }
    }

    private Owner getOwner(String param, List<Owner> owners) {
        if(owners.isEmpty()){
            System.out.println("No owners.");
            return null;
        }

        boolean ownerExists = false;
        Owner theOwner = null;
        for (Owner owner : owners) {
            if (String.valueOf(owner.getId()).equals(param)) {
                ownerExists = true;
                theOwner = owner;
                break;
            }
        }
        if(!ownerExists){
            System.out.println("Owner doesn't exist.");
            return null;
        }
        return theOwner;
    }
}

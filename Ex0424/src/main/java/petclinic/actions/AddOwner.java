package petclinic.actions;

import petclinic.model.Owner;

import javax.persistence.EntityManager;

public class AddOwner extends TransactionalCommand {

    public AddOwner() {
        super("addowner");
    }

    @Override
    protected void run(EntityManager entityManager, String[] params) throws Exception {
        if (params.length != 3) {
            System.out.println("addowner <firstName> <lastName>");
            return;
        }

        Owner owner = new Owner();
        owner.setFirstName(params[1]);
        owner.setLastName(params[2]);
        entityManager.persist(owner);
        System.out.println("added " + owner);
    }
}

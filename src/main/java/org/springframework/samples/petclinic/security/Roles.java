package org.springframework.samples.petclinic.security;

import org.springframework.stereotype.Component;

@Component
public class Roles {

    public final String OWNER_ADMIN = "ROLE_OWNER_ADMIN";
    public final String VET_ADMIN = "ROLE_VET_ADMIN";
    public final String ADMIN = "ROLE_ADMIN";
    //por si hay que modificar esta en el poducto rest controllero
    public final String PROD_ADMIN = "ROLE_PROD_ADMIN";
}

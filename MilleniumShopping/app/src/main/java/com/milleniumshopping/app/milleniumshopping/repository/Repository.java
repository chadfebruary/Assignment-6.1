package com.milleniumshopping.app.milleniumshopping.repository;

import java.util.Set;

/**
 * Created by 208023429 on 4/21/2016.
 */
public interface Repository <Entity, Identity> {

    Entity findById(Identity identity1, Identity identity2);
    Entity save(Entity entity);
    Entity update(Entity entity);
    Entity delete (Entity entity);
    int deleteAll();
    Set<Entity> findAll();
}

package com.tsyb.project;

import java.util.List;

public interface RolesService {

    List<Roles> findAll();

    Roles findById(int id);

    void save(Roles role);

    void deleteById(int id);

}



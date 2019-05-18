package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Transactional
public class UsersServiceImpl implements UsersService {


    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private GenericWebApplicationContext context;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(int id) {
        Optional<Users> result = usersRepository.findById(id);

        Users users = null;

        if (result.isPresent()) {
            users = result.get();
        } else {
            throw new RuntimeException("Did not find user id - " + id);
        }

        return users;
    }

    @Override
    public void save(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setValid(false);
        Roles userRole = rolesRepository.findByTypeUser("client");
        user.setRoles(new ArrayList<>(Arrays.asList(userRole)));
        usersRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {
        usersRepository.deleteById(theId);
    }


   @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }else {

                context.registerBean("usertemp", Users.class, () -> user);

        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public Users findByUserName(String userName){
        return usersRepository.findByUserName(userName);
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Roles> roles){
        return roles.stream()
                .map(new Function<Roles, SimpleGrantedAuthority>() {
                    @Override
                    public SimpleGrantedAuthority apply(Roles role) {
                        return new SimpleGrantedAuthority(role.getTypeUser());
                    }
                })
                .collect(Collectors.toList());
    }
}



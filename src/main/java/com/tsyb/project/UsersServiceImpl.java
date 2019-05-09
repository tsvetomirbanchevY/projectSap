package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class UsersServiceImpl implements UsersService {


    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

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
        usersRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {
        usersRepository.deleteById(theId);
    }


   ///@Override
   // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   //     Users user = usersRepository.findByEmail(email);
  //      if (user == null){
   //         throw new UsernameNotFoundException("Invalid username or password.");
   //     }
    //    return new org.springframework.security.core.userdetails.User(user.getEmail(),
    //            user.getPassword(),
    //            mapRolesToAuthorities(user.getRoles()));
  //  }

    public Users findByEmail(String email){
        return usersRepository.findByEmail(email);
    }


 //   private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Roles> roles){
  //      return roles.stream()
   //             .map(new Function<Roles, SimpleGrantedAuthority>() {
    //                @Override
     //               public SimpleGrantedAuthority apply(Roles role) {
    //                    return new SimpleGrantedAuthority(role.getTypeUser());
    //                }
    //            })
     //           .collect(Collectors.toList());
  //  }
}



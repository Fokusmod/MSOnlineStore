package com.geekbrains.authservice.service;

import com.geekbrains.apiservice.annotation.ExecutionTime;
import com.geekbrains.authservice.model.Privilege;
import com.geekbrains.authservice.model.Role;
import com.geekbrains.authservice.model.User;
import com.geekbrains.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @ExecutionTime
    public User getUser(String username){
       return userRepository.findByUsername(username).get();
    }

    @Override
    @Transactional
    @ExecutionTime
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("This user - " + username + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getRolesFromUser(user.getRoles()));
    }
    @ExecutionTime
    public Collection<? extends GrantedAuthority> getRolesFromUser(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }
    @ExecutionTime
    public List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    @ExecutionTime
    public List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
            privileges.add(role.getName());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
}

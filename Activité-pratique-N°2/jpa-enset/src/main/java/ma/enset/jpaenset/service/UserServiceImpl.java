package ma.enset.jpaenset.service;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(rolename);

        if (user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
            //userRepository.save(user);
        }
    }

    @Override
    public User authentification(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (username==null) throw new RuntimeException("UserName or Password Incorrect");
        if (user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("UserName or Password Incorrect");
    }
}

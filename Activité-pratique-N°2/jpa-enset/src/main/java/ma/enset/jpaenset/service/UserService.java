package ma.enset.jpaenset.service;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;

public interface UserService {
    User addUser(User user);
    Role addNewRole(Role role);

    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String rolename);
    User authentification(String username, String password);
}

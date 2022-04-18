package ma.enset.students_managment.sec.service;

import ma.enset.students_managment.sec.entities.AppRole;
import ma.enset.students_managment.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String descreption);
    void addRoleToUser(String userName, String roleName);
    void removeRoleFromUser(String userName, String roleName);
    AppUser loadUserByUserName(String username);
}

package ma.enset.students_managment.sec.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.students_managment.sec.entities.AppRole;
import ma.enset.students_managment.sec.entities.AppUser;
import ma.enset.students_managment.sec.reposotories.AppRoleRepository;
import ma.enset.students_managment.sec.reposotories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if( ! password.equals(rePassword) ) throw new RuntimeException(" Les deux mots de passe ne sont pas identiques ! ");
        String hashedPassword = passwordEncoder.encode( password) ;
        AppUser appUser = new AppUser();
        appUser.setActive(true);
        appUser.setPassword(hashedPassword);
        appUser.setUsername(username);
        appUser.setUserId( UUID.randomUUID().toString() );
        AppUser savedUser = appUserRepository.save( appUser ); // ?? !
        return savedUser ;
    }

    @Override
    public AppRole saveNewRole(String roleName, String descreption) {
        AppRole role = appRoleRepository.findByRoleName(roleName);
        if( role != null ) throw new RuntimeException("Role '"+roleName+"'exist!");
        role = new AppRole();
        role.setRoleName(roleName);
        role.setDesciption( descreption);
        AppRole saved = appRoleRepository.save( role );
        return saved;
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser user = appUserRepository.findByUsername( userName );
        if( user ==null )
            throw new RuntimeException(" user '"+userName+"' Not exist !");
        AppRole role = appRoleRepository.findByRoleName( roleName);
        if( role ==null )
            throw new RuntimeException(" Role '"+roleName+"' Not exist !");
        user.getRoles().add(role);
    }

    @Override
    public void removeRoleFromUser(String userName, String roleName) {
        AppUser user = appUserRepository.findByUsername( userName );
        if( user ==null )
            throw new RuntimeException(" User '"+userName+"' Not exist !");
        AppRole role = appRoleRepository.findByRoleName( roleName);
        if( role ==null )
            throw new RuntimeException(" Role '"+roleName+"' Not exist !");
        user.getRoles().remove(role);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}

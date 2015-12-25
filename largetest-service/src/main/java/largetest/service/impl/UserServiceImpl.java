package largetest.service.impl;

import largetest.dao.IUserDAO;
import largetest.domain.Role;
import largetest.domain.User;
import largetest.domain.dto.RoleDTO;
import largetest.domain.dto.SpringUser;
import largetest.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private IUserDAO userDAO;

    @Override
    public SpringUser loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getByEmail(s);
        if(user != null){
            SpringUser springUser = new SpringUser();
            springUser.setEnabled(true);
            springUser.setPassword(user.getPassword());
            springUser.setUsername(user.getEmail());

            if(user.getAuthorities() != null){
                List<RoleDTO> roles = new ArrayList<RoleDTO>();
                for(Role role: user.getAuthorities()){
                    RoleDTO dto = new RoleDTO();
                    dto.setAuthority(role.getAuthority());
                    roles.add(dto);
                }
                springUser.setAuthorities(roles);
            }
            return springUser;
        }
        return null;
    }
}
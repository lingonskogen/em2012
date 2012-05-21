package se.lingonskogen.em2012.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.domain.UserDao;

public class GaeAuthProvider implements UserDetailsService
{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDao userDao = new UserDao();
        for (User user : userDao.findAll())
        {
            if (user.getUserName().equals(username))
            {
                String password = user.getPassword();
                List<GrantedAuthority> authorities = Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("user"));
                return new org.springframework.security.core.userdetails.User(username, password, authorities);
            }
        }
        throw new UsernameNotFoundException("User was not found");
    }

}

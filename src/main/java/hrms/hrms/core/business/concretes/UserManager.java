package hrms.hrms.core.business.concretes;

import hrms.hrms.core.business.abstracts.UserService;
import hrms.hrms.core.dataAccess.abstracts.UserDao;
import hrms.hrms.core.entities.User;
import hrms.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;

    }
    
    @Override
    public DataResult<List<User>> getAll() {
        List<User> user = this.userDao.findAll();
        return new SuccessDataResult<List<User>>(user,"Data listed");
    }

    @Override
    public DataResult<User> getByUserId(int userId) {
        if(userDao.getById(userId)!=null){
            User user = this.userDao.getById(userId);
            return new SuccessDataResult<User>(user,"Data listed");
        }
        return new ErrorDataResult<User>("User Id doesn't exist");
    }



    @Override
    public Result delete(int userId) {
        if(userDao.getById(userId)!=null){
            userDao.deleteById(userId);
            return new SuccessResult("User deleted");
        }
        return new ErrorResult("User Id doesn't exist");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByEmail(username);

        return user.orElseThrow(() ->  new UsernameNotFoundException("Invalid Credentials"));

    }
}
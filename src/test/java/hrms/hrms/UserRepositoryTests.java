package hrms.hrms;

import hrms.hrms.core.dataAccess.abstracts.UserDao;
import hrms.hrms.core.entities.User;
import hrms.hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.hrms.entities.concretes.JobSeeker;
import hrms.hrms.entities.concretes.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    UserDao userRepository;
    @Autowired
    JobSeekerDao jobSeekerDao;

    @Test
    public void testCreateUser(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "Muslera26.";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        JobSeeker newUser = new JobSeeker("Ahmet","Dayı","12345678912",new Date(21072022),"muslera1@gmail.com", encodedPassword,encodedPassword);

        JobSeeker savedUser = jobSeekerDao.save(newUser);
    }

    @Test
    public void testAssignRoleToUser() {
        Integer userId = 1;
        User user = userRepository.findById(userId).get();
        user.addRole(new Role(1));





    }
}

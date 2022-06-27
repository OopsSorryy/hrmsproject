package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerDao extends JpaRepository<JobSeeker,Integer> {

    JobSeeker getByJobSeekerId(int jobSeekerId);

    JobSeeker getByFirstNameAndLastName(String jobSeekerFirstName, String jobSeekerLastName);
    JobSeeker getByNationalityId(String nationalityId);
    JobSeeker getByEmail(String email);


}

package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.ForeignLanguage;
import hrms.hrms.entities.concretes.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobExperienceDao extends JpaRepository<JobExperience,Integer> {

    JobExperience getByJobExperienceId(int jobExperienceId);


}

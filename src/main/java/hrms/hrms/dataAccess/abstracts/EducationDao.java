package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationDao extends JpaRepository<Education,Integer> {

    Education getByEducationId(int educationId);

    Education deleteByEducationId(int educationId);
}

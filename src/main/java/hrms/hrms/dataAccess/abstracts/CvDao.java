package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.Cv;
import hrms.hrms.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvDao extends JpaRepository<Cv,Integer> {

    Cv getByCvId(int cvId);

}

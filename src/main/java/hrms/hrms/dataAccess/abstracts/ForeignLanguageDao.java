package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.Cv;
import hrms.hrms.entities.concretes.ForeignLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForeignLanguageDao extends JpaRepository<ForeignLanguage,Integer> {

    ForeignLanguage getByForeignLanguageId(int foreignLanguageId);


}

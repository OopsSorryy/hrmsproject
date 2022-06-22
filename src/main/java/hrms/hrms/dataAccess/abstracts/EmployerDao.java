package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.Employer;

import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployerDao extends JpaRepository<Employer,Integer> {
    Employer getByEmployerId(int employerId);
    Employer deleteByEmployerId(int employerId);
    Employer getByEmployerName(String employerName);
    Employer getByWebSite(String website);
    Employer getByTelephoneNumber(String telephoneNumber);

    Employer getByEmail(String email);
}

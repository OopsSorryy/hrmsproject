package hrms.hrms.dataAccess.abstracts;

import hrms.hrms.entities.concretes.JobAdvertisement;
import hrms.hrms.entities.dtos.JobAdvertisementWithEmployerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {

    JobAdvertisement getByJobId(int jobPositionId);
    JobAdvertisement getByJobName(String jobPositionName);


    @Query("Select new hrms.hrms.entities.dtos.JobAdvertisementWithEmployerDto"
            + "(j.jobName,j.jobDescription,j.jobCity,j.jobSalary,j.jobOpenPosition,j.deadlineDate,j.releaseDate, e.employerName) "
            + "From Employer e Inner Join e.jobAdvertisements j")
    List<JobAdvertisementWithEmployerDto> getJobAdvertisementWithEmployerName();
}

package hrms.hrms.dataAccess.abstracts;


import hrms.hrms.entities.concretes.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDao extends JpaRepository<Skill,Integer> {

    Skill getBySkillId(int skillId);


}

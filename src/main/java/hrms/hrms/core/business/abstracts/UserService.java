package hrms.hrms.core.business.abstracts;

import hrms.hrms.core.entities.User;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;

import java.util.List;

public interface UserService  {

    DataResult<List<User>> getAll();
    DataResult<User> getByUserId(int userId);
    Result delete(int userId);

}

package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.UserDetail;
import bg.fmi.rateuni.mappers.DisciplineMapper;
import bg.fmi.rateuni.mappers.UserMapper;
import bg.fmi.rateuni.models.*;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DisciplineMapper disciplineMapper;

    @Autowired
    private DisciplineCrudService disciplineCrudService;

    public User getUserByEmail (String email) {
        User user = userCrudService.getUserByEmail(email).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User with this email does not exist");
        }

        return user;
    }

    public UserDetail getUserDetails (UUID userId) {
        User user = userCrudService.getUserById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        List<Discipline> disciplines = userCrudService.getDisciplinesByUserId(userId);
        Programme programme = disciplines.getFirst().getProgramme();
        Faculty faculty = programme.getFaculty();
        University university = faculty.getUniversity();

        UserDetail userDetail = userMapper.mapToUserDetail(user, university, faculty, programme);
        userDetail.setDisciplines(disciplines.stream()
                .map(discipline -> disciplineMapper.mapToDto(discipline))
                .toList());

        return userDetail;
    }

    public BaseResponse addUserToDiscipline (UserDetail userDetail, UUID disciplineId) {
        userCrudService.addUserDiscipline(userDetail.getId(),
                disciplineCrudService.getDisciplineById(disciplineId).get());

        disciplineCrudService.addUserToDiscipline(disciplineId,
                userCrudService.getUserById(userDetail.getId()).get());

        return new BaseResponse("User added to discipline");
    }

}

package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.response.UserDetail;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.facultyNumber", target = "facultyNumber")
    @Mapping(source = "university.name", target = "universityName")
    @Mapping(source = "faculty.name", target = "facultyName")
    @Mapping(source = "programme.name", target = "programmeName")
    UserDetail mapToUserDetail(User user, University university, Faculty faculty, Programme programme);
}

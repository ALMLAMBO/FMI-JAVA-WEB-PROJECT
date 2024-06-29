package bg.fmi.rateuni.services.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateUniversityRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.mappers.FacultyMapper;
import bg.fmi.rateuni.mappers.UniversityMapper;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.services.crud.FacultyCrudService;
import bg.fmi.rateuni.services.crud.UniversityCrudService;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UniversityService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UniversityServiceDiffblueTest {
    @MockBean
    private FacultyCrudService facultyCrudService;

    @MockBean
    private FacultyMapper facultyMapper;

    @MockBean
    private UniversityCrudService universityCrudService;

    @MockBean
    private UniversityMapper universityMapper;

    @Autowired
    private UniversityService universityService;

    /**
     * Method under test:
     * {@link UniversityService#createUniversity(CreateUniversityRequest)}
     */
    @Test
    void testCreateUniversity() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");
        Optional<University> ofResult = Optional.of(university);
        when(universityCrudService.getUniversityByName(Mockito.<String>any())).thenReturn(ofResult);

        CreateUniversityRequest universityRequest = new CreateUniversityRequest();
        universityRequest.setHqAddress("42 Main St");
        universityRequest.setName("Name");
        universityRequest.setRector("Rector");

        // Act
        BaseResponse actualCreateUniversityResult = universityService.createUniversity(universityRequest);

        // Assert
        verify(universityCrudService).getUniversityByName(eq("Name"));
        assertEquals("University with name Name already exists", actualCreateUniversityResult.getMessage());
    }

    /**
     * Method under test:
     * {@link UniversityService#createUniversity(CreateUniversityRequest)}
     */
    @Test
    void testCreateUniversity2() {
        // Arrange
        doNothing().when(universityCrudService).createUpdateUniversity(Mockito.<University>any());
        Optional<University> emptyResult = Optional.empty();
        when(universityCrudService.getUniversityByName(Mockito.<String>any())).thenReturn(emptyResult);

        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");
        when(universityMapper.mapFromCreateRequest(Mockito.<CreateUniversityRequest>any())).thenReturn(university);

        CreateUniversityRequest universityRequest = new CreateUniversityRequest();
        universityRequest.setHqAddress("42 Main St");
        universityRequest.setName("Name");
        universityRequest.setRector("Rector");

        // Act
        BaseResponse actualCreateUniversityResult = universityService.createUniversity(universityRequest);

        // Assert
        verify(universityMapper).mapFromCreateRequest(isA(CreateUniversityRequest.class));
        verify(universityCrudService).createUpdateUniversity(isA(University.class));
        verify(universityCrudService).getUniversityByName(eq("Name"));
        assertEquals("University created successfully", actualCreateUniversityResult.getMessage());
    }

    /**
     * Method under test:
     * {@link UniversityService#createUniversity(CreateUniversityRequest)}
     */
    @Test
    void testCreateUniversity3() {
        // Arrange
        doThrow(new IllegalArgumentException("University created successfully")).when(universityCrudService)
                .createUpdateUniversity(Mockito.<University>any());
        Optional<University> emptyResult = Optional.empty();
        when(universityCrudService.getUniversityByName(Mockito.<String>any())).thenReturn(emptyResult);

        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");
        when(universityMapper.mapFromCreateRequest(Mockito.<CreateUniversityRequest>any())).thenReturn(university);

        CreateUniversityRequest universityRequest = new CreateUniversityRequest();
        universityRequest.setHqAddress("42 Main St");
        universityRequest.setName("Name");
        universityRequest.setRector("Rector");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> universityService.createUniversity(universityRequest));
        verify(universityMapper).mapFromCreateRequest(isA(CreateUniversityRequest.class));
        verify(universityCrudService).createUpdateUniversity(isA(University.class));
        verify(universityCrudService).getUniversityByName(eq("Name"));
    }

    /**
     * Method under test: {@link UniversityService#deleteUniversity(UUID)}
     */
    @Test
    void testDeleteUniversity() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");
        Optional<University> ofResult = Optional.of(university);
        doNothing().when(universityCrudService).deleteUniversity(Mockito.<UUID>any());
        when(universityCrudService.getUniversityById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act
        BaseResponse actualDeleteUniversityResult = universityService.deleteUniversity(UUID.randomUUID());

        // Assert
        verify(universityCrudService).deleteUniversity(isA(UUID.class));
        verify(universityCrudService).getUniversityById(isA(UUID.class));
        assertEquals("University deleted successfully", actualDeleteUniversityResult.getMessage());
    }

    /**
     * Method under test: {@link UniversityService#deleteUniversity(UUID)}
     */
    @Test
    void testDeleteUniversity2() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");
        Optional<University> ofResult = Optional.of(university);
        doThrow(new IllegalArgumentException("University deleted successfully")).when(universityCrudService)
                .deleteUniversity(Mockito.<UUID>any());
        when(universityCrudService.getUniversityById(Mockito.<UUID>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> universityService.deleteUniversity(UUID.randomUUID()));
        verify(universityCrudService).deleteUniversity(isA(UUID.class));
        verify(universityCrudService).getUniversityById(isA(UUID.class));
    }

    /**
     * Method under test: {@link UniversityService#deleteUniversity(UUID)}
     */
    @Test
    void testDeleteUniversity3() {
        // Arrange
        Optional<University> emptyResult = Optional.empty();
        when(universityCrudService.getUniversityById(Mockito.<UUID>any())).thenReturn(emptyResult);

        // Act
        universityService.deleteUniversity(UUID.randomUUID());

        // Assert
        verify(universityCrudService).getUniversityById(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link UniversityService#addFacultyToUniversity(UUID, CreateFacultyRequest)}
     */
    @Test
    void testAddFacultyToUniversity() {
        // Arrange
        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);
        Optional<Faculty> ofResult = Optional.of(faculty);
        when(facultyCrudService.getFacultyByName(Mockito.<String>any())).thenReturn(ofResult);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");
        Optional<University> ofResult2 = Optional.of(university2);
        when(universityCrudService.getUniversityById(Mockito.<UUID>any())).thenReturn(ofResult2);
        UUID universityId = UUID.randomUUID();

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act
        BaseResponse actualAddFacultyToUniversityResult = universityService.addFacultyToUniversity(universityId,
                facultyRequest);

        // Assert
        verify(facultyCrudService).getFacultyByName(eq("Name"));
        verify(universityCrudService).getUniversityById(isA(UUID.class));
        assertEquals("Faculty with name Name already exists", actualAddFacultyToUniversityResult.getMessage());
    }

    /**
     * Method under test:
     * {@link UniversityService#addFacultyToUniversity(UUID, CreateFacultyRequest)}
     */
    @Test
    void testAddFacultyToUniversity2() {
        // Arrange
        when(facultyCrudService.getFacultyByName(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");
        Optional<University> ofResult = Optional.of(university);
        when(universityCrudService.getUniversityById(Mockito.<UUID>any())).thenReturn(ofResult);
        UUID universityId = UUID.randomUUID();

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> universityService.addFacultyToUniversity(universityId, facultyRequest));
        verify(facultyCrudService).getFacultyByName(eq("Name"));
        verify(universityCrudService).getUniversityById(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link UniversityService#addFacultyToUniversity(UUID, CreateFacultyRequest)}
     */
    @Test
    void testAddFacultyToUniversity3() {
        // Arrange
        Optional<Faculty> emptyResult = Optional.empty();
        when(facultyCrudService.getFacultyByName(Mockito.<String>any())).thenReturn(emptyResult);
        doNothing().when(facultyCrudService).createUpdateFaculty(Mockito.<Faculty>any());

        University university = new University();
        university.setFaculties(new HashSet<>());
        university.setHqAddress("42 Main St");
        university.setId(UUID.randomUUID());
        university.setName("Name");
        university.setRector("Rector");

        Faculty faculty = new Faculty();
        faculty.setAddress("42 Main St");
        faculty.setDean("Dean");
        faculty.setId(UUID.randomUUID());
        faculty.setName("Name");
        faculty.setPrograms(new HashSet<>());
        faculty.setUniversity(university);
        when(facultyMapper.mapFromCreateRequest(Mockito.<CreateFacultyRequest>any())).thenReturn(faculty);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");
        Optional<University> ofResult = Optional.of(university2);
        when(universityCrudService.getUniversityById(Mockito.<UUID>any())).thenReturn(ofResult);
        UUID universityId = UUID.randomUUID();

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act
        BaseResponse actualAddFacultyToUniversityResult = universityService.addFacultyToUniversity(universityId,
                facultyRequest);

        // Assert
        verify(facultyMapper).mapFromCreateRequest(isA(CreateFacultyRequest.class));
        verify(facultyCrudService).createUpdateFaculty(isA(Faculty.class));
        verify(facultyCrudService).getFacultyByName(eq("Name"));
        verify(universityCrudService).getUniversityById(isA(UUID.class));
        assertEquals("Faculty added successfully", actualAddFacultyToUniversityResult.getMessage());
    }
}

package bg.fmi.rateuni.services.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.dto.response.FacultyResponse;
import bg.fmi.rateuni.dto.response.ProgrammeResponse;
import bg.fmi.rateuni.mappers.FacultyMapper;
import bg.fmi.rateuni.mappers.ProgrammeMapper;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.services.crud.FacultyCrudService;
import bg.fmi.rateuni.services.crud.ProgrammeCrudService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class FacultyServiceDiffblueTest {
    @MockBean
    private FacultyCrudService facultyCrudService;

    @MockBean
    private FacultyMapper facultyMapper;

    @Autowired
    private FacultyService facultyService;

    @MockBean
    private ProgrammeCrudService programmeCrudService;

    @MockBean
    private ProgrammeMapper programmeMapper;

    /**
     * Method under test: {@link FacultyService#getFacultyById(UUID)}
     */
    @Test
    void testGetFacultyById() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);

        FacultyInfoResponse facultyInfoResponse = new FacultyInfoResponse();
        facultyInfoResponse.setAddress("42 Main St");
        facultyInfoResponse.setDean("Dean");
        facultyInfoResponse.setIdInfoResponse(UUID.randomUUID());
        facultyInfoResponse.setName("Name");
        facultyInfoResponse.setPrograms(new ArrayList<>());
        when(facultyMapper.mapToInfoResponseDto(Mockito.<Faculty>any())).thenReturn(facultyInfoResponse);
        when(programmeCrudService.getProgramsByFacultyId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        FacultyInfoResponse actualFacultyById = facultyService.getFacultyById(UUID.randomUUID());

        // Assert
        verify(facultyMapper).mapToInfoResponseDto(isA(Faculty.class));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
        assertTrue(actualFacultyById.getPrograms().isEmpty());
        assertSame(facultyInfoResponse, actualFacultyById);
    }

    /**
     * Method under test: {@link FacultyService#getFacultyById(UUID)}
     */
    @Test
    void testGetFacultyById2() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);

        FacultyInfoResponse facultyInfoResponse = new FacultyInfoResponse();
        facultyInfoResponse.setAddress("42 Main St");
        facultyInfoResponse.setDean("Dean");
        facultyInfoResponse.setIdInfoResponse(UUID.randomUUID());
        facultyInfoResponse.setName("Name");
        facultyInfoResponse.setPrograms(new ArrayList<>());
        when(facultyMapper.mapToInfoResponseDto(Mockito.<Faculty>any())).thenReturn(facultyInfoResponse);
        when(programmeCrudService.getProgramsByFacultyId(Mockito.<UUID>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> facultyService.getFacultyById(UUID.randomUUID()));
        verify(facultyMapper).mapToInfoResponseDto(isA(Faculty.class));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
    }

    /**
     * Method under test: {@link FacultyService#getFacultyById(UUID)}
     */
    @Test
    void testGetFacultyById3() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);

        FacultyInfoResponse facultyInfoResponse = new FacultyInfoResponse();
        facultyInfoResponse.setAddress("42 Main St");
        facultyInfoResponse.setDean("Dean");
        facultyInfoResponse.setIdInfoResponse(UUID.randomUUID());
        facultyInfoResponse.setName("Name");
        facultyInfoResponse.setPrograms(new ArrayList<>());
        when(facultyMapper.mapToInfoResponseDto(Mockito.<Faculty>any())).thenReturn(facultyInfoResponse);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty2);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        ArrayList<Programme> programmeList = new ArrayList<>();
        programmeList.add(programme);
        when(programmeCrudService.getProgramsByFacultyId(Mockito.<UUID>any())).thenReturn(programmeList);

        ProgrammeResponse programmeResponse = new ProgrammeResponse();
        programmeResponse.setDescription("The characteristics of someone or something");
        programmeResponse.setIdResponse(UUID.randomUUID());
        programmeResponse.setTitle("Dr");
        when(programmeMapper.mapToDto(Mockito.<Programme>any())).thenReturn(programmeResponse);

        // Act
        FacultyInfoResponse actualFacultyById = facultyService.getFacultyById(UUID.randomUUID());

        // Assert
        verify(facultyMapper).mapToInfoResponseDto(isA(Faculty.class));
        verify(programmeMapper).mapToDto(isA(Programme.class));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
        assertEquals(1, actualFacultyById.getPrograms().size());
        assertSame(facultyInfoResponse, actualFacultyById);
    }

    /**
     * Method under test: {@link FacultyService#getFacultyById(UUID)}
     */
    @Test
    void testGetFacultyById4() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);

        FacultyInfoResponse facultyInfoResponse = new FacultyInfoResponse();
        facultyInfoResponse.setAddress("42 Main St");
        facultyInfoResponse.setDean("Dean");
        facultyInfoResponse.setIdInfoResponse(UUID.randomUUID());
        facultyInfoResponse.setName("Name");
        facultyInfoResponse.setPrograms(new ArrayList<>());
        when(facultyMapper.mapToInfoResponseDto(Mockito.<Faculty>any())).thenReturn(facultyInfoResponse);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty2);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        University university3 = new University();
        university3.setFaculties(new HashSet<>());
        university3.setHqAddress("17 High St");
        university3.setId(UUID.randomUUID());
        university3.setName("42");
        university3.setRector("42");

        Faculty faculty3 = new Faculty();
        faculty3.setAddress("17 High St");
        faculty3.setDean("42");
        faculty3.setId(UUID.randomUUID());
        faculty3.setName("42");
        faculty3.setPrograms(new HashSet<>());
        faculty3.setUniversity(university3);

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty3);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Mr");

        ArrayList<Programme> programmeList = new ArrayList<>();
        programmeList.add(programme2);
        programmeList.add(programme);
        when(programmeCrudService.getProgramsByFacultyId(Mockito.<UUID>any())).thenReturn(programmeList);

        ProgrammeResponse programmeResponse = new ProgrammeResponse();
        programmeResponse.setDescription("The characteristics of someone or something");
        programmeResponse.setIdResponse(UUID.randomUUID());
        programmeResponse.setTitle("Dr");
        when(programmeMapper.mapToDto(Mockito.<Programme>any())).thenReturn(programmeResponse);

        // Act
        FacultyInfoResponse actualFacultyById = facultyService.getFacultyById(UUID.randomUUID());

        // Assert
        verify(facultyMapper).mapToInfoResponseDto(isA(Faculty.class));
        verify(programmeMapper, atLeast(1)).mapToDto(Mockito.<Programme>any());
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
        assertEquals(2, actualFacultyById.getPrograms().size());
        assertSame(facultyInfoResponse, actualFacultyById);
    }

    /**
     * Method under test: {@link FacultyService#createFaculty(CreateFacultyRequest)}
     */
    @Test
    void testCreateFaculty() {
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

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act
        BaseResponse actualCreateFacultyResult = facultyService.createFaculty(facultyRequest);

        // Assert
        verify(facultyCrudService).getFacultyByName(eq("Name"));
        assertEquals("Faculty with name Name already exists", actualCreateFacultyResult.getMessage());
    }

    /**
     * Method under test: {@link FacultyService#createFaculty(CreateFacultyRequest)}
     */
    @Test
    void testCreateFaculty2() {
        // Arrange
        doNothing().when(facultyCrudService).createUpdateFaculty(Mockito.<Faculty>any());
        Optional<Faculty> emptyResult = Optional.empty();
        when(facultyCrudService.getFacultyByName(Mockito.<String>any())).thenReturn(emptyResult);

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

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act
        BaseResponse actualCreateFacultyResult = facultyService.createFaculty(facultyRequest);

        // Assert
        verify(facultyMapper).mapFromCreateRequest(isA(CreateFacultyRequest.class));
        verify(facultyCrudService).createUpdateFaculty(isA(Faculty.class));
        verify(facultyCrudService).getFacultyByName(eq("Name"));
        assertEquals("Faculty created successfully", actualCreateFacultyResult.getMessage());
    }

    /**
     * Method under test: {@link FacultyService#createFaculty(CreateFacultyRequest)}
     */
    @Test
    void testCreateFaculty3() {
        // Arrange
        doThrow(new IllegalArgumentException("Faculty created successfully")).when(facultyCrudService)
                .createUpdateFaculty(Mockito.<Faculty>any());
        Optional<Faculty> emptyResult = Optional.empty();
        when(facultyCrudService.getFacultyByName(Mockito.<String>any())).thenReturn(emptyResult);

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

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> facultyService.createFaculty(facultyRequest));
        verify(facultyMapper).mapFromCreateRequest(isA(CreateFacultyRequest.class));
        verify(facultyCrudService).createUpdateFaculty(isA(Faculty.class));
        verify(facultyCrudService).getFacultyByName(eq("Name"));
    }

    /**
     * Method under test:
     * {@link FacultyService#updateFaculty(UUID, CreateFacultyRequest)}
     */
    @Test
    void testUpdateFaculty() {
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
        doNothing().when(facultyCrudService).createUpdateFaculty(Mockito.<Faculty>any());
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);
        when(facultyMapper.mapFromCreateRequest(Mockito.<CreateFacultyRequest>any())).thenReturn(faculty2);
        UUID id = UUID.randomUUID();

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act
        BaseResponse actualUpdateFacultyResult = facultyService.updateFaculty(id, facultyRequest);

        // Assert
        verify(facultyMapper).mapFromCreateRequest(isA(CreateFacultyRequest.class));
        verify(facultyCrudService).createUpdateFaculty(isA(Faculty.class));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        assertEquals("Faculty updated successfully", actualUpdateFacultyResult.getMessage());
    }

    /**
     * Method under test:
     * {@link FacultyService#updateFaculty(UUID, CreateFacultyRequest)}
     */
    @Test
    void testUpdateFaculty2() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(facultyMapper.mapFromCreateRequest(Mockito.<CreateFacultyRequest>any()))
                .thenThrow(new IllegalArgumentException("Faculty updated successfully"));
        UUID id = UUID.randomUUID();

        CreateFacultyRequest facultyRequest = new CreateFacultyRequest();
        facultyRequest.setAddress("42 Main St");
        facultyRequest.setDean("Dean");
        facultyRequest.setName("Name");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> facultyService.updateFaculty(id, facultyRequest));
        verify(facultyMapper).mapFromCreateRequest(isA(CreateFacultyRequest.class));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
    }

    /**
     * Method under test: {@link FacultyService#deleteFaculty(UUID)}
     */
    @Test
    void testDeleteFaculty() {
        // Arrange
        doNothing().when(facultyCrudService).deleteFaculty(Mockito.<UUID>any());

        // Act
        BaseResponse actualDeleteFacultyResult = facultyService.deleteFaculty(UUID.randomUUID());

        // Assert
        verify(facultyCrudService).deleteFaculty(isA(UUID.class));
        assertEquals("Faculty deleted successfully", actualDeleteFacultyResult.getMessage());
    }

    /**
     * Method under test: {@link FacultyService#deleteFaculty(UUID)}
     */
    @Test
    void testDeleteFaculty2() {
        // Arrange
        doThrow(new IllegalArgumentException("Faculty deleted successfully")).when(facultyCrudService)
                .deleteFaculty(Mockito.<UUID>any());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> facultyService.deleteFaculty(UUID.randomUUID()));
        verify(facultyCrudService).deleteFaculty(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link FacultyService#addProgrammeToFaculty(UUID, CreateProgrammeRequest)}
     */
    @Test
    void testAddProgrammeToFaculty() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty2);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult2 = Optional.of(programme);
        when(programmeCrudService.getProgrammeByTitle(Mockito.<String>any())).thenReturn(ofResult2);
        UUID facultyId = UUID.randomUUID();

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act
        BaseResponse actualAddProgrammeToFacultyResult = facultyService.addProgrammeToFaculty(facultyId, programmeRequest);

        // Assert
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).getProgrammeByTitle(eq("Dr"));
        assertEquals("Programme with name Dr already exists", actualAddProgrammeToFacultyResult.getMessage());
    }

    /**
     * Method under test:
     * {@link FacultyService#addProgrammeToFaculty(UUID, CreateProgrammeRequest)}
     */
    @Test
    void testAddProgrammeToFaculty2() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(programmeCrudService.getProgrammeByTitle(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("foo"));
        UUID facultyId = UUID.randomUUID();

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> facultyService.addProgrammeToFaculty(facultyId, programmeRequest));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).getProgrammeByTitle(eq("Dr"));
    }

    /**
     * Method under test:
     * {@link FacultyService#addProgrammeToFaculty(UUID, CreateProgrammeRequest)}
     */
    @Test
    void testAddProgrammeToFaculty3() {
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
        when(facultyCrudService.getFacultyById(Mockito.<UUID>any())).thenReturn(ofResult);
        Optional<Programme> emptyResult = Optional.empty();
        when(programmeCrudService.getProgrammeByTitle(Mockito.<String>any())).thenReturn(emptyResult);
        doNothing().when(programmeCrudService).createUpdateProgramme(Mockito.<Programme>any());

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("42 Main St");
        university2.setId(UUID.randomUUID());
        university2.setName("Name");
        university2.setRector("Rector");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("42 Main St");
        faculty2.setDean("Dean");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("Name");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty2);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        when(programmeMapper.mapFromCreateRequest(Mockito.<CreateProgrammeRequest>any())).thenReturn(programme);
        UUID facultyId = UUID.randomUUID();

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act
        BaseResponse actualAddProgrammeToFacultyResult = facultyService.addProgrammeToFaculty(facultyId, programmeRequest);

        // Assert
        verify(programmeMapper).mapFromCreateRequest(isA(CreateProgrammeRequest.class));
        verify(facultyCrudService).getFacultyById(isA(UUID.class));
        verify(programmeCrudService).createUpdateProgramme(isA(Programme.class));
        verify(programmeCrudService).getProgrammeByTitle(eq("Dr"));
        assertEquals("Programme added successfully", actualAddProgrammeToFacultyResult.getMessage());
    }

    /**
     * Method under test: {@link FacultyService#getFacultiesForUniversity(UUID)}
     */
    @Test
    void testGetFacultiesForUniversity() {
        // Arrange
        when(facultyCrudService.getFacultiesByUniversityId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        List<FacultyResponse> actualFacultiesForUniversity = facultyService.getFacultiesForUniversity(UUID.randomUUID());

        // Assert
        verify(facultyCrudService).getFacultiesByUniversityId(isA(UUID.class));
        assertTrue(actualFacultiesForUniversity.isEmpty());
    }

    /**
     * Method under test: {@link FacultyService#getFacultiesForUniversity(UUID)}
     */
    @Test
    void testGetFacultiesForUniversity2() {
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

        ArrayList<Faculty> facultyList = new ArrayList<>();
        facultyList.add(faculty);
        when(facultyCrudService.getFacultiesByUniversityId(Mockito.<UUID>any())).thenReturn(facultyList);

        FacultyResponse facultyResponse = new FacultyResponse();
        facultyResponse.setAddress("42 Main St");
        facultyResponse.setDean("Dean");
        facultyResponse.setIdResponse(UUID.randomUUID());
        facultyResponse.setName("Name");
        when(facultyMapper.mapToDto(Mockito.<Faculty>any())).thenReturn(facultyResponse);

        // Act
        List<FacultyResponse> actualFacultiesForUniversity = facultyService.getFacultiesForUniversity(UUID.randomUUID());

        // Assert
        verify(facultyMapper).mapToDto(isA(Faculty.class));
        verify(facultyCrudService).getFacultiesByUniversityId(isA(UUID.class));
        assertEquals(1, actualFacultiesForUniversity.size());
        assertSame(facultyResponse, actualFacultiesForUniversity.get(0));
    }

    /**
     * Method under test: {@link FacultyService#getFacultiesForUniversity(UUID)}
     */
    @Test
    void testGetFacultiesForUniversity3() {
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

        University university2 = new University();
        university2.setFaculties(new HashSet<>());
        university2.setHqAddress("17 High St");
        university2.setId(UUID.randomUUID());
        university2.setName("42");
        university2.setRector("42");

        Faculty faculty2 = new Faculty();
        faculty2.setAddress("17 High St");
        faculty2.setDean("42");
        faculty2.setId(UUID.randomUUID());
        faculty2.setName("42");
        faculty2.setPrograms(new HashSet<>());
        faculty2.setUniversity(university2);

        ArrayList<Faculty> facultyList = new ArrayList<>();
        facultyList.add(faculty2);
        facultyList.add(faculty);
        when(facultyCrudService.getFacultiesByUniversityId(Mockito.<UUID>any())).thenReturn(facultyList);

        FacultyResponse facultyResponse = new FacultyResponse();
        facultyResponse.setAddress("42 Main St");
        facultyResponse.setDean("Dean");
        facultyResponse.setIdResponse(UUID.randomUUID());
        facultyResponse.setName("Name");
        when(facultyMapper.mapToDto(Mockito.<Faculty>any())).thenReturn(facultyResponse);

        // Act
        List<FacultyResponse> actualFacultiesForUniversity = facultyService.getFacultiesForUniversity(UUID.randomUUID());

        // Assert
        verify(facultyMapper, atLeast(1)).mapToDto(Mockito.<Faculty>any());
        verify(facultyCrudService).getFacultiesByUniversityId(isA(UUID.class));
        assertEquals(2, actualFacultiesForUniversity.size());
        assertSame(facultyResponse, actualFacultiesForUniversity.get(0));
    }

    /**
     * Method under test: {@link FacultyService#getFacultiesForUniversity(UUID)}
     */
    @Test
    void testGetFacultiesForUniversity4() {
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

        ArrayList<Faculty> facultyList = new ArrayList<>();
        facultyList.add(faculty);
        when(facultyCrudService.getFacultiesByUniversityId(Mockito.<UUID>any())).thenReturn(facultyList);
        when(facultyMapper.mapToDto(Mockito.<Faculty>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> facultyService.getFacultiesForUniversity(UUID.randomUUID()));
        verify(facultyMapper).mapToDto(isA(Faculty.class));
        verify(facultyCrudService).getFacultiesByUniversityId(isA(UUID.class));
    }
}

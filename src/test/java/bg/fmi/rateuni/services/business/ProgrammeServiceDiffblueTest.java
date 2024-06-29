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

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
import bg.fmi.rateuni.dto.response.ProgrammeResponse;
import bg.fmi.rateuni.mappers.DisciplineMapper;
import bg.fmi.rateuni.mappers.ProgrammeMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.ProgrammeCrudService;
import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;

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

@ContextConfiguration(classes = {ProgrammeService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProgrammeServiceDiffblueTest {
    @MockBean
    private DisciplineCrudService disciplineCrudService;

    @MockBean
    private DisciplineMapper disciplineMapper;

    @MockBean
    private ProgrammeCrudService programmeCrudService;

    @MockBean
    private ProgrammeMapper programmeMapper;

    @Autowired
    private ProgrammeService programmeService;

    /**
     * Method under test: {@link ProgrammeService#getProgrammeById(UUID)}
     */
    @Test
    void testGetProgrammeById() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(ofResult);

        DisciplineResponse disciplineResponse = new DisciplineResponse();
        disciplineResponse.setCategory(DisciplineCategory.CSF);
        disciplineResponse.setCredits(10.0d);
        disciplineResponse.setId(UUID.randomUUID());
        disciplineResponse.setName("Name");
        disciplineResponse.setReviews(new ArrayList<>());
        disciplineResponse.setType(DisciplineType.ELECTIVE);
        when(disciplineMapper.mapToDto(Mockito.<Discipline>any())).thenReturn(disciplineResponse);

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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");
        Optional<Programme> ofResult2 = Optional.of(programme2);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult2);

        ProgrammeInfoResponse programmeInfoResponse = new ProgrammeInfoResponse();
        programmeInfoResponse.setDescription("The characteristics of someone or something");
        programmeInfoResponse.setDisciplines(new ArrayList<>());
        programmeInfoResponse.setIdInfoResponse(UUID.randomUUID());
        programmeInfoResponse.setTitle("Dr");
        when(programmeMapper.mapToInfoResponseDto(Mockito.<Programme>any())).thenReturn(programmeInfoResponse);

        // Act
        ProgrammeInfoResponse actualProgrammeById = programmeService.getProgrammeById(UUID.randomUUID());

        // Assert
        verify(disciplineMapper).mapToDto(isA(Discipline.class));
        verify(programmeMapper).mapToInfoResponseDto(isA(Programme.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
        assertEquals(1, actualProgrammeById.getDisciplines().size());
        assertSame(programmeInfoResponse, actualProgrammeById);
    }

    /**
     * Method under test: {@link ProgrammeService#getProgrammeById(UUID)}
     */
    @Test
    void testGetProgrammeById2() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(programmeMapper.mapToInfoResponseDto(Mockito.<Programme>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> programmeService.getProgrammeById(UUID.randomUUID()));
        verify(programmeMapper).mapToInfoResponseDto(isA(Programme.class));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
    }

    /**
     * Method under test: {@link ProgrammeService#getProgrammeById(UUID)}
     */
    @Test
    void testGetProgrammeById3() {
        // Arrange
        Optional<Discipline> emptyResult = Optional.empty();
        when(disciplineCrudService.getDisciplineById(Mockito.<UUID>any())).thenReturn(emptyResult);

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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult);

        ProgrammeInfoResponse programmeInfoResponse = new ProgrammeInfoResponse();
        programmeInfoResponse.setDescription("The characteristics of someone or something");
        programmeInfoResponse.setDisciplines(new ArrayList<>());
        programmeInfoResponse.setIdInfoResponse(UUID.randomUUID());
        programmeInfoResponse.setTitle("Dr");
        when(programmeMapper.mapToInfoResponseDto(Mockito.<Programme>any())).thenReturn(programmeInfoResponse);

        // Act
        ProgrammeInfoResponse actualProgrammeById = programmeService.getProgrammeById(UUID.randomUUID());

        // Assert
        verify(programmeMapper).mapToInfoResponseDto(isA(Programme.class));
        verify(disciplineCrudService).getDisciplineById(isA(UUID.class));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
        assertTrue(actualProgrammeById.getDisciplines().isEmpty());
        assertSame(programmeInfoResponse, actualProgrammeById);
    }

    /**
     * Method under test:
     * {@link ProgrammeService#createProgramme(CreateProgrammeRequest)}
     */
    @Test
    void testCreateProgramme() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme);
        when(programmeCrudService.getProgrammeByTitle(Mockito.<String>any())).thenReturn(ofResult);

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act
        BaseResponse actualCreateProgrammeResult = programmeService.createProgramme(programmeRequest);

        // Assert
        verify(programmeCrudService).getProgrammeByTitle(eq("Dr"));
        assertEquals("Programme with title Dr already exists", actualCreateProgrammeResult.getMessage());
    }

    /**
     * Method under test:
     * {@link ProgrammeService#createProgramme(CreateProgrammeRequest)}
     */
    @Test
    void testCreateProgramme2() {
        // Arrange
        doNothing().when(programmeCrudService).createUpdateProgramme(Mockito.<Programme>any());
        Optional<Programme> emptyResult = Optional.empty();
        when(programmeCrudService.getProgrammeByTitle(Mockito.<String>any())).thenReturn(emptyResult);

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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        when(programmeMapper.mapFromCreateRequest(Mockito.<CreateProgrammeRequest>any())).thenReturn(programme);

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act
        BaseResponse actualCreateProgrammeResult = programmeService.createProgramme(programmeRequest);

        // Assert
        verify(programmeMapper).mapFromCreateRequest(isA(CreateProgrammeRequest.class));
        verify(programmeCrudService).createUpdateProgramme(isA(Programme.class));
        verify(programmeCrudService).getProgrammeByTitle(eq("Dr"));
        assertEquals("Programme created successfully", actualCreateProgrammeResult.getMessage());
    }

    /**
     * Method under test:
     * {@link ProgrammeService#createProgramme(CreateProgrammeRequest)}
     */
    @Test
    void testCreateProgramme3() {
        // Arrange
        doThrow(new IllegalArgumentException("Programme created successfully")).when(programmeCrudService)
                .createUpdateProgramme(Mockito.<Programme>any());
        Optional<Programme> emptyResult = Optional.empty();
        when(programmeCrudService.getProgrammeByTitle(Mockito.<String>any())).thenReturn(emptyResult);

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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        when(programmeMapper.mapFromCreateRequest(Mockito.<CreateProgrammeRequest>any())).thenReturn(programme);

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> programmeService.createProgramme(programmeRequest));
        verify(programmeMapper).mapFromCreateRequest(isA(CreateProgrammeRequest.class));
        verify(programmeCrudService).createUpdateProgramme(isA(Programme.class));
        verify(programmeCrudService).getProgrammeByTitle(eq("Dr"));
    }

    /**
     * Method under test:
     * {@link ProgrammeService#updateProgramme(UUID, CreateProgrammeRequest)}
     */
    @Test
    void testUpdateProgramme() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme);
        doNothing().when(programmeCrudService).createUpdateProgramme(Mockito.<Programme>any());
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult);

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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");
        when(programmeMapper.mapFromCreateRequest(Mockito.<CreateProgrammeRequest>any())).thenReturn(programme2);
        UUID id = UUID.randomUUID();

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act
        BaseResponse actualUpdateProgrammeResult = programmeService.updateProgramme(id, programmeRequest);

        // Assert
        verify(programmeMapper).mapFromCreateRequest(isA(CreateProgrammeRequest.class));
        verify(programmeCrudService).createUpdateProgramme(isA(Programme.class));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
        assertEquals("Programme updated successfully", actualUpdateProgrammeResult.getMessage());
    }

    /**
     * Method under test:
     * {@link ProgrammeService#updateProgramme(UUID, CreateProgrammeRequest)}
     */
    @Test
    void testUpdateProgramme2() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult);
        when(programmeMapper.mapFromCreateRequest(Mockito.<CreateProgrammeRequest>any()))
                .thenThrow(new IllegalArgumentException("Programme updated successfully"));
        UUID id = UUID.randomUUID();

        CreateProgrammeRequest programmeRequest = new CreateProgrammeRequest();
        programmeRequest.setDescription("The characteristics of someone or something");
        programmeRequest.setTitle("Dr");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> programmeService.updateProgramme(id, programmeRequest));
        verify(programmeMapper).mapFromCreateRequest(isA(CreateProgrammeRequest.class));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
    }

    /**
     * Method under test: {@link ProgrammeService#deleteProgramme(UUID)}
     */
    @Test
    void testDeleteProgramme() {
        // Arrange
        doNothing().when(programmeCrudService).deleteProgramme(Mockito.<UUID>any());

        // Act
        BaseResponse actualDeleteProgrammeResult = programmeService.deleteProgramme(UUID.randomUUID());

        // Assert
        verify(programmeCrudService).deleteProgramme(isA(UUID.class));
        assertEquals("Programme deleted successfully", actualDeleteProgrammeResult.getMessage());
    }

    /**
     * Method under test: {@link ProgrammeService#deleteProgramme(UUID)}
     */
    @Test
    void testDeleteProgramme2() {
        // Arrange
        doThrow(new IllegalArgumentException("Programme deleted successfully")).when(programmeCrudService)
                .deleteProgramme(Mockito.<UUID>any());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> programmeService.deleteProgramme(UUID.randomUUID()));
        verify(programmeCrudService).deleteProgramme(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link ProgrammeService#addDisciplineToProgramme(UUID, CreateDisciplineRequest)}
     */
    @Test
    void testAddDisciplineToProgramme() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());
        Optional<Discipline> ofResult = Optional.of(discipline);
        when(disciplineCrudService.getDisciplineByName(Mockito.<String>any())).thenReturn(ofResult);

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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");
        Optional<Programme> ofResult2 = Optional.of(programme2);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult2);
        UUID programmeId = UUID.randomUUID();

        CreateDisciplineRequest disciplineRequest = new CreateDisciplineRequest();
        disciplineRequest.setCategory(DisciplineCategory.CSF);
        disciplineRequest.setCredits(10.0d);
        disciplineRequest.setName("Name");
        disciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act
        BaseResponse actualAddDisciplineToProgrammeResult = programmeService.addDisciplineToProgramme(programmeId,
                disciplineRequest);

        // Assert
        verify(disciplineCrudService).getDisciplineByName(eq("Name"));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
        assertEquals("Discipline with name Name already exists", actualAddDisciplineToProgrammeResult.getMessage());
    }

    /**
     * Method under test:
     * {@link ProgrammeService#addDisciplineToProgramme(UUID, CreateDisciplineRequest)}
     */
    @Test
    void testAddDisciplineToProgramme2() {
        // Arrange
        when(disciplineCrudService.getDisciplineByName(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("foo"));

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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult);
        UUID programmeId = UUID.randomUUID();

        CreateDisciplineRequest disciplineRequest = new CreateDisciplineRequest();
        disciplineRequest.setCategory(DisciplineCategory.CSF);
        disciplineRequest.setCredits(10.0d);
        disciplineRequest.setName("Name");
        disciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> programmeService.addDisciplineToProgramme(programmeId, disciplineRequest));
        verify(disciplineCrudService).getDisciplineByName(eq("Name"));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
    }

    /**
     * Method under test:
     * {@link ProgrammeService#addDisciplineToProgramme(UUID, CreateDisciplineRequest)}
     */
    @Test
    void testAddDisciplineToProgramme3() {
        // Arrange
        Optional<Discipline> emptyResult = Optional.empty();
        when(disciplineCrudService.getDisciplineByName(Mockito.<String>any())).thenReturn(emptyResult);
        doNothing().when(disciplineCrudService).createUpdateDiscipline(Mockito.<Discipline>any());

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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        Discipline discipline = new Discipline();
        discipline.setAssistants("Assistants");
        discipline.setCategory(DisciplineCategory.CSF);
        discipline.setCredits(10.0d);
        discipline.setDescription("The characteristics of someone or something");
        discipline.setId(UUID.randomUUID());
        discipline.setLecturer("Lecturer");
        discipline.setName("Name");
        discipline.setProgramme(programme);
        discipline.setRatings(new HashSet<>());
        discipline.setType(DisciplineType.ELECTIVE);
        discipline.setUsers(new HashSet<>());
        when(disciplineMapper.mapFromCreateRequest(Mockito.<CreateDisciplineRequest>any())).thenReturn(discipline);

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

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
        programme2.setId(UUID.randomUUID());
        programme2.setTitle("Dr");
        Optional<Programme> ofResult = Optional.of(programme2);
        when(programmeCrudService.getProgrammeById(Mockito.<UUID>any())).thenReturn(ofResult);
        UUID programmeId = UUID.randomUUID();

        CreateDisciplineRequest disciplineRequest = new CreateDisciplineRequest();
        disciplineRequest.setCategory(DisciplineCategory.CSF);
        disciplineRequest.setCredits(10.0d);
        disciplineRequest.setName("Name");
        disciplineRequest.setType(DisciplineType.ELECTIVE);

        // Act
        BaseResponse actualAddDisciplineToProgrammeResult = programmeService.addDisciplineToProgramme(programmeId,
                disciplineRequest);

        // Assert
        verify(disciplineMapper).mapFromCreateRequest(isA(CreateDisciplineRequest.class));
        verify(disciplineCrudService).createUpdateDiscipline(isA(Discipline.class));
        verify(disciplineCrudService).getDisciplineByName(eq("Name"));
        verify(programmeCrudService).getProgrammeById(isA(UUID.class));
        assertEquals("Discipline added successfully", actualAddDisciplineToProgrammeResult.getMessage());
    }

    /**
     * Method under test: {@link ProgrammeService#getProgramsForFaculty(UUID)}
     */
    @Test
    void testGetProgramsForFaculty() {
        // Arrange
        when(programmeCrudService.getProgramsByFacultyId(Mockito.<UUID>any())).thenReturn(new ArrayList<>());

        // Act
        List<ProgrammeResponse> actualProgramsForFaculty = programmeService.getProgramsForFaculty(UUID.randomUUID());

        // Assert
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
        assertTrue(actualProgramsForFaculty.isEmpty());
    }

    /**
     * Method under test: {@link ProgrammeService#getProgramsForFaculty(UUID)}
     */
    @Test
    void testGetProgramsForFaculty2() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
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
        List<ProgrammeResponse> actualProgramsForFaculty = programmeService.getProgramsForFaculty(UUID.randomUUID());

        // Assert
        verify(programmeMapper).mapToDto(isA(Programme.class));
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
        assertEquals(1, actualProgramsForFaculty.size());
        assertSame(programmeResponse, actualProgramsForFaculty.get(0));
    }

    /**
     * Method under test: {@link ProgrammeService#getProgramsForFaculty(UUID)}
     */
    @Test
    void testGetProgramsForFaculty3() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

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

        Programme programme2 = new Programme();
        programme2.setDescription("Description");
        programme2.setDisciplines(new HashSet<>());
        programme2.setFaculty(faculty2);
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
        List<ProgrammeResponse> actualProgramsForFaculty = programmeService.getProgramsForFaculty(UUID.randomUUID());

        // Assert
        verify(programmeMapper, atLeast(1)).mapToDto(Mockito.<Programme>any());
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
        assertEquals(2, actualProgramsForFaculty.size());
        assertSame(programmeResponse, actualProgramsForFaculty.get(0));
    }

    /**
     * Method under test: {@link ProgrammeService#getProgramsForFaculty(UUID)}
     */
    @Test
    void testGetProgramsForFaculty4() {
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

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setDisciplines(new HashSet<>());
        programme.setFaculty(faculty);
        programme.setId(UUID.randomUUID());
        programme.setTitle("Dr");

        ArrayList<Programme> programmeList = new ArrayList<>();
        programmeList.add(programme);
        when(programmeCrudService.getProgramsByFacultyId(Mockito.<UUID>any())).thenReturn(programmeList);
        when(programmeMapper.mapToDto(Mockito.<Programme>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> programmeService.getProgramsForFaculty(UUID.randomUUID()));
        verify(programmeMapper).mapToDto(isA(Programme.class));
        verify(programmeCrudService).getProgramsByFacultyId(isA(UUID.class));
    }
}

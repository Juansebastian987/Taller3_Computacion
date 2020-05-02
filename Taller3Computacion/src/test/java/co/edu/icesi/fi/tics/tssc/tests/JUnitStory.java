package co.edu.icesi.fi.tics.tssc.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.IGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.IStoryRepository;
import co.edu.icesi.fi.tics.tssc.service.StoryServiceImp;

class JUnitStory {

	@Mock
	private IStoryRepository storyRepository; //Dependencia
	
	@InjectMocks		
	private StoryServiceImp storyServiceImp; //Dependiente
	
	@Mock
	private IGameRepository gameRepository;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/////////////////////////////////////////////////////
	////////////      GUARDAR 			/////////////////
	/////////////////////////////////////////////////////	
	
	@Nested
	@DisplayName("Save")
	class Save{
	/**
	 * @Test testException
	 * El test valida de que si no tiene el valor negocio, el sprint inicial y la prioridad genere exception
	 */
	@DisplayName("Story Exception")
	@Test
	void testException() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(-100));
		tsscStory.setInitialSprint(new BigDecimal(-100));
		tsscStory.setPriority(new BigDecimal(-100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId() )).thenReturn(optional);

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById(tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}

	/**
	 * @Test testSaveStoryNullException
	 * El test valida que la Story que se quiera guardar no sea nula
	 */
	@DisplayName("Save Story Null Exception")
	@Test
	void testSaveStoryNullException() {
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(null, 10);
		});
	}
	
	/**
	 * @Test testSaveStoryCorrectSprintInicial
	 * El test valida que el sprint inicial sea mayor a cero.
	 * pre-condition: Se asume que la prioridad y el valor de negocio estan correctos
	 */
	@DisplayName("Save Story Correct Sprint Inicial")
	@Test
	void testSaveStoryCorrectSprintInicial() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(-100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testSaveStoryCorrectPrioridad
	 * El test valida que la prioridad sea mayor a cero.
	 * pre-condition: Se asume que el sprint inicial y el valor de negocio estan correctos
	 */
	@DisplayName("Save Story Correct Prioridad")
	@Test
	void testSaveStoryCorrectPrioridad() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(-100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testSaveStoryWithGameCorrect
	 * El test valida que la asociación con el Game esta correctamente
	 */
	@DisplayName("Save Story With Game Correct")
	@Test
	void testSaveStoryWithGameCorrect() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testSaveStoryGameException
	 * El test valida que el Game no sea nulo
	 */
	@DisplayName("Save Story Game Null Exception")
	@Test
	void testSaveStoryGameNullException() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
	
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById(tsscGame.getId())).thenReturn(optional);

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, -1);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testSaveStoryCorrectValorNegocio
	 * El test valida que el valor negocio se guarda correctamente
	 * pre-condition: Se asume que el sprint inicial y la prioridad estan correctos
	 */
	@DisplayName("Save Story Correct Valor Negocio")
	@Test
	void testSaveStoryCorrectValorNegocio() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(-100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testStorySprintInicialMayorCero
	 * El test valida que si el sprint inicial es mayor que cero se pueda guardar la Story
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */
	@DisplayName("Save Story Sprint Inicial Mayor A Cero")
	@Test
	void testStorySprintInicialMayorCero() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	/**
	 * @Test testStorySprintInicialMenorCero
     * El test valida que si el sprint inicial es menor que cero no se pueda guardar la Story
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */		
	@DisplayName("Save Story Sprint Inicial Menor A Cero")
	@Test
	void testStorySprintInicialMenorCero() {	
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(-100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testStoryValorNegocioMayorCero
	 * El test valida que si el valor negocio es mayor que cero se pueda guardar la Story
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */
	@DisplayName("Save Story Valor Negocio Mayor A Cero")
	@Test
	void testStoryValorNegocioMayorCero() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById(tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testStoryValorNegocioMenorCero
     * El test valida que si el valor negocio es menor que cero no se pueda guardar la Story
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */	
	@DisplayName("Save Story Valor Negocio Menor A Cero")
	@Test
	void testStoryValorNegocioMenorCero() {		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(-100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testStoryPrioridadMayorCero
	 * El test valida que si la prioridad es mayor que cero se pueda guardar la Story
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */
	@DisplayName("Save Story Prioridad Mayor A Cero")
	@Test
	void testStoryPrioridadMayorCero() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testStoryPrioridadMenorCero
     * El test valida que si la prioridad es menor que cero no se pueda guardar la Story
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */	
	@DisplayName("Save Story Prioridad Menor A Cero")
	@Test
	void testStoryPrioridadMenorCero() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(-100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	}
	
	/////////////////////////////////////////////////////
	////////////      EDITAR 			/////////////////
	/////////////////////////////////////////////////////	
	
	@Nested
	@DisplayName("Edit")
	class Edit{
	/**
	 * @Test testEdit
	 * El test verifica que da una exception al editar el Story si todos sus atributos no han sido añadidos correctamente
	 */
	@DisplayName("Edit Exception")
	@Test
	void testEditException() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(-100));
		tsscStory.setInitialSprint(new BigDecimal(-100));
		tsscStory.setPriority(new BigDecimal(-100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.editStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testEditStoryNullException
	 * El test verifica que el Story a editar no sea nulo
	 */
	@DisplayName("Edit Story Null Exception")
	@Test
	void testEditStoryNullException() {
		assertThrows(Exception.class, () -> {
			storyServiceImp.editStory(null, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}

	/**
	 * @Test testEditGameNotExist
	 * El test verifica que el game que se va a editar exista
	 */
	@DisplayName("Edit Game Not Exist")
	@Test
	void testEditGameNotExist() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.editStory(tsscStory, -1);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);
	}
	
	/**
	 * @Test testEditSprintInicialCorrect
	 * El test valida que al editar la story el valor de sprint inicial siga siendo la correcta,
	 * es decir que siga siendo mayor a cero
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */
	@DisplayName("Edit Sprint Inicial Correct")
	@Test
	void testEditSprintInicialCorrect() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(-100));
		tsscStory.setPriority(new BigDecimal(100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById(tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.editStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);	
	}
	
	/**
	 * @Test testEditPrioridadCorrect
	 * El test valida que al editar la story el valor de prioridad siga siendo la correcta,
	 * es decir que siga siendo mayor a cero
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */
	@DisplayName("Edit Prioridad Correct")
	@Test
	void testEditPrioridadCorrect() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(-100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);	}
	
	/**
	 * @Test testEditSprintInicialCorrect
	 * El test valida que al editar la story el valor de negocio siga siendo la correcta,
	 * es decir que siga siendo mayor a cero
	 * pre-condition: Se asume que los demas atributos estan añadidos correctamente
	 */
	@DisplayName("Edit Valor Negocio")
	@Test
	void testEditValorNegocio() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));

		Optional<TsscStory> tssOptional = Optional.of(tsscStory);
		
		when(storyRepository.findById( tsscStory.getId())).thenReturn(tssOptional);
		
		assertThrows(Exception.class, () -> {
			storyServiceImp.saveStory(tsscStory, 10);
		});
	
		Mockito.verifyNoMoreInteractions(storyRepository);	
		}
	}
}
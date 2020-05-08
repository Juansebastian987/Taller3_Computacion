package co.edu.icesi.fi.tics.tssc.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.IGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.ITopicRepository;
import co.edu.icesi.fi.tics.tssc.service.GameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TopicServiceImp;

@RunWith(MockitoJUnitRunner.class)
class JUnitGame {
	
	@Mock
	private IGameRepository gameRepository; //Dependencia
	
	@InjectMocks		
	private GameServiceImp gameServiceImp; //Dependiente
	
	@Mock
	private ITopicRepository topicRepository;
	
	@InjectMocks		
	private TopicServiceImp topicServiceImp; //Dependiente
	
	
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
	 * @Test testSaveException
	 * El test valida que el guardado se hace correctamente solo si tiene tanto la cantidad grupos como el sprint
	 * estan correctamente
	 */
	@DisplayName("Save Exception")
	@Test
	void testSaveException() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(-10);
		tsscGame.setNSprints(-10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGame(tsscGame);
		});
	
		Mockito.verifyNoMoreInteractions(gameRepository);
	}

	/**
	 * @Test testSaveSprintCorrect
	 * El Test valida que al tener su sprint correctamente el Game se guarde.
	 * pre-conditin: Se asume que su group esta correctamente.
	 */
	@DisplayName("Save Sprint Correct")
	@Test
	void testSaveSprintCorrect() {		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(1);
		tsscGame.setNSprints(10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		try {			
			assertTrue(gameServiceImp.saveGame(tsscGame).equals(tsscGame));
			verify(gameRepository, times(1)).save(tsscGame);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
	
	/**
	 * @Test testSaveGroupsCorrect
	 * El Test valida que al tener su group correctamente el Topic de guarde
	 * pre-condition: Se asume que su sprint esta correctamente
	 */
	@DisplayName("Save Group Correct")
	@Test
	void testSaveGroupsCorrect() {		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		try {			
			assertTrue(gameServiceImp.saveGame(tsscGame).equals(tsscGame));
			verify(gameRepository, times(1)).save(tsscGame);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @Test testSaveGameExistTopic
	 * El test valida que exista el topic que quiere guardar en el Game, sino existe el Topic
	 * lanza una excepcion
	 */
	@DisplayName("Save Game Exist Topic")
	@Test
	void testSaveGameExistTopic() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(-1);
		tsscGame.setNSprints(-1);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameTopic(tsscGame, -1);
		});
	
		Mockito.verifyNoMoreInteractions(gameRepository);
	}
	
	/**
	 * @Test testSaveSprintMenorCero
	 * El test valida que si el sprint es menor que cero no se pueda guardar el Topic
	 * pre-condition: Se asume que su group esta correctamente
	 */
	@DisplayName("Save Menor A Cero")
	@Test
	void testSaveSprintMenorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(-10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGame(tsscGame);
		});
	
		Mockito.verifyNoMoreInteractions(gameRepository);
		
	}
	
	/**
	 * @Test testSaveSprintMayorCero
	 * El test valida que si el sprint es mayor que cero si se pueda guardar el Topic
	 * pre-condition: Se asume que su group esta correctamente
	 */
	@DisplayName("Save Sprint Mayor A Cero")
	@Test
	void testSaveSprintMayorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		try {			
			assertTrue(gameServiceImp.saveGame(tsscGame).equals(tsscGame));
			verify(gameRepository, times(1)).save(tsscGame);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
	/**
	 * @Test testSaveGroupMenorCero
	 * El test valida que si el grupo es menor que cero no se pueda guardar el Topic
	 * pre-condition: Se asume que su sprint esta correctamente
	 */
	@DisplayName("Save Group Menor A Cero")
	@Test
	void testSaveGroupMenorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(-10);
		tsscGame.setNSprints(10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGame(tsscGame);
		});
	
		Mockito.verifyNoMoreInteractions(gameRepository);
	}
	
	/**
	 * @Test testSaveGroupMayorCero
	 * El test valida que si el grupo es mayor que cero si se pueda guardar el Topic
	 * pre-condition: Se asume que su sprint esta correctamente
	 */
	@DisplayName("Save Group Mayor A Cero")
	@Test
	void testSaveGroupMayorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		try {			
			assertTrue(gameServiceImp.saveGame(tsscGame).equals(tsscGame));
			verify(gameRepository, times(1)).save(tsscGame);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
	/**
	 * @Test testSaveTopicNullException
	 * El test valida que lo que se este guardando no sea un topic nulo
	 */
	@DisplayName("Save Topic Null Exception")
	@Test
	void testSaveTopicNullException() {
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameTopic(null, 1);
		});
	}
	
	/**
	 * @Test testSaveGameNullException
	 * El test valida que lo que se este guardando no sea un Game nulo
	 */
	@DisplayName("Save Game Null Exception")
	@Test
	void testSaveGameNullException() {
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGame(null);
		});
	
		Mockito.verifyNoMoreInteractions(gameRepository);	
		}
	
	/**
	 * @Test testSaveTopicCorrect
	 * El test valida que se guarda correctemente el Topic al Game
	 */
	@DisplayName("Save Topic Correct")
	@Test
	void testSaveTopicCorrect() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(1);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		Optional<TsscTopic> topicc = Optional.of(topic);
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(topic)).thenReturn(topic);
		when(topicRepository.findById( topic.getId())).thenReturn(topicc);
		when(gameRepository.findById(tsscGame.getId())).thenReturn(optional);
		
		try {			
			assertTrue(gameServiceImp.saveGameTopic(tsscGame, 10).equals(tsscGame));
			verify(gameRepository, times(1)).save(tsscGame);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	}
	
	/////////////////////////////////////////////////////
	////////////      EDITAR 			/////////////////
	/////////////////////////////////////////////////////
	
	@Nested
	@DisplayName("Edit")
	class Edit{
	/**
	 * @Test testEditCorrect 
	 * El test valida que se edita correctamente el Game
	 */
	@DisplayName("Edit Correct")
	@Test
	void testEditCorrect() {
		TsscGame tsscGame = new TsscGame();

		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		try {			
			assertTrue(gameServiceImp.editGame(tsscGame, 10).equals(tsscGame));
			verify(gameRepository, times(1)).save(tsscGame);
			verify(gameRepository, times(1)).findById(tsscGame.getId());
		} catch (Exception e) {
			e.getStackTrace();
		}
	  }
	
	/**
	 * @Test testEditException
	 * El test valida que al editar el juego cumpla con su cantidad de sprint y group
	 */
	@DisplayName("Edit Exception")
	@Test
	void testEditException() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(-1);
		tsscGame.setNSprints(-1);
		tsscGame.setId(10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.editGame(tsscGame, 10);
		});
		
	}
	
	/**
	 * @Test testGameNull
	 * El test valida que el juego a editar no sea nulo
	 */
	@DisplayName("Edit Game Null")
	@Test
	void testGameNull() {
		assertThrows(Exception.class, () -> {
			gameServiceImp.editGame(null, 10);
		});
		
	}
	
	/**
	 * @Test testEditTopicNull
	 * El test valida que el tema que quiere editar al juego no sea nulo
	 */
	@DisplayName("Edit Topic Null")
	@Test
	void testEditTopicNull() {
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameTopic(null, -1);
		});	
		}
	
	/**
	 * @Test testEditTopicNotExist
	 * El test valida que exista el tema que quiere editar al juego exist
	 */
	@DisplayName("Edit Topic Not Exist")
	@Test
	void testEditTopicNotExist() {
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameTopic(null, -1);
		});	
	}
	
	/**
	 * @Test testEditGroupCorrect
	 * El test valida que al editar el juego la cantidad de group siga siendo la correcta,
	 * es decir que siga siendo mayor a cero, sino lanza excepcion
	 * pre-condition: se asume que la cantidad de sprint esta correcto
	 */
	@DisplayName("Edit Group Correct")
	@Test
	void testEditGroupCorrect() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(-1);
		tsscGame.setNSprints(10);
		tsscGame.setId(10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.editGame(tsscGame,10);
		});
	}
	
	/**
	 * @Test testEditSprintCorrect
	 * El test valida que al editar el juego la cantidad de spring siga siendo la correcta,
	 * es decir que siga siendo mayor a cero, sino lanza excepcion
	 * pre-condition: se asume que la cantidad de group esta correcto
	 */
	@DisplayName("Edit Sprint Correct")
	@Test
	void testEditSprintCorrect() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(-1);
		tsscGame.setId(10);
		
		TsscTopic topic = new TsscTopic();
		topic.setId(10);
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(topic);
		
		Optional<TsscGame> optional =  Optional.of(tsscGame);
		
		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(gameRepository.findById( tsscGame.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.editGame(tsscGame,10);
		});
	}
	}
	
	
	
	/////////////////////////////////////////////////////
	////////////      PUNTO D			/////////////////
	/////////////////////////////////////////////////////

	@Nested
	@DisplayName("Punto D")
	class PuntoD{
	/**
	 * @Test testTopicNull
	 * Este test se encarga de verificar que el topic no sea null
	 */
	@DisplayName("Test Topic Null")
	@Test
	public void testTopicNull() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setName("Segundo");
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setName("Segundo");
		tsscTopic.setDefaultSprints(-100);
		tsscTopic.setDefaultGroups(100);

		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		
		Optional<TsscTopic> tssOptional1 = Optional.of(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(tssOptional1);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameWithTopic(null, 10);
		});
	}


	/**
	 * @Test testSaveTopicGroupMayorCero
	 * Este test verifica que el topic tenga un group mayor a cero
	 */
	@DisplayName("Test Save Topic Group Mayor A Cero ")
	@Test
	public void testSaveTopicGroupMayorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setName("Segundo");
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setName("Segundo");
		tsscTopic.setDefaultSprints(-100);
		tsscTopic.setDefaultGroups(100);

		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		
		Optional<TsscTopic> tssOptional1 = Optional.of(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(tssOptional1);
		
		try {
			assertTrue(gameServiceImp.saveGame(tsscGame).equals(tsscGame));
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

	/**
	 * @Test testSaveTopicGroupMenorCero
	 * Este test verifica que el topic no tenga un group menor a cero
	 */
	@DisplayName("Test Save Topic Group Menor A Cero ")
	@Test
	public void testSaveTopicGroupMenorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setName("Segundo");
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setName("Segundo");
		tsscTopic.setDefaultSprints(100);
		tsscTopic.setDefaultGroups(-100);

		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		
		Optional<TsscTopic> tssOptional1 = Optional.of(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(tssOptional1);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameWithTopic(tsscGame,1);
		});
	}

	/**
	 * @throws Exception 
	 * @Test testSaveGameTopic
	 * Este test valida que se pueda guardar un Game apartir de un Topic
	 */
	@DisplayName("Test Save Game For Topic")
	@Test
	public void testSaveGameTopic() throws Exception {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setName("Segundo");
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setName("Segundo");
		tsscTopic.setDefaultSprints(100);
		tsscTopic.setDefaultGroups(100);

		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		
		Optional<TsscTopic> tssOptional1 = Optional.of(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(tssOptional1);
		
		try {
			assertTrue(gameServiceImp.saveGameWithTopic(tsscGame, tsscTopic.getId()).getNGroups().equals(tsscGame.getNGroups()));
			verify(gameRepository, times(1)).save(tsscGame);

	} catch (Exception e) {
		e.getStackTrace();
		}	
	}
	
	/**
	 * @Test testSaveTopicSprintMenorCero
	 * Este test verifica que el topic no tenga un sprint menor a cero
	 */
	@DisplayName("Test Save Topic Sprint Menor A Cero ")
	@Test
	public void testSaveTopicSprintMenorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setName("Segundo");
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setName("Segundo");
		tsscTopic.setDefaultSprints(-100);
		tsscTopic.setDefaultGroups(100);

		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		
		Optional<TsscTopic> tssOptional1 = Optional.of(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(tssOptional1);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameWithTopic(tsscGame, 1);
		});
		
	}

	/**
	 * @Test testSaveTopicSprintMayorCero
	 * Este test verifica que el topic no tenga un sprint menor a cero
	 */
	@DisplayName("Test Save Topic Sprint Mayor A Cero ")
	@Test
	public void testSaveTopicSprintMayorCero() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setName("Segundo");
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setName("Segundo");
		tsscTopic.setDefaultSprints(100);
		tsscTopic.setDefaultGroups(100);

		when(gameRepository.save(tsscGame)).thenReturn(tsscGame);
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		
		Optional<TsscTopic> tssOptional1 = Optional.of(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(tssOptional1);
		
		assertThrows(Exception.class, () -> {
			gameServiceImp.saveGameWithTopic(tsscGame,1);
		});
		}
	}
}
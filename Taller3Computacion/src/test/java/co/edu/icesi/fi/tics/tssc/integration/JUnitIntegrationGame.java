package co.edu.icesi.fi.tics.tssc.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.GameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TopicServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
class JUnitIntegrationGame {

	@Autowired
	private GameServiceImp gameServiceImp;
	
	@Autowired
	private TopicServiceImp topicServiceImp;

	private TsscTopic tsscTopic;
	
	private TsscGame tssGame;
	

	@BeforeEach
	public void setUp() {

		tsscTopic = new TsscTopic();
		tsscTopic.setDefaultGroups(1);
		tsscTopic.setDefaultSprints(1);
		
		tssGame = new TsscGame();
		tssGame.setNGroups(1);
		tssGame.setNSprints(1);
		
		try {
			topicServiceImp.saveTopic(tsscTopic);
			gameServiceImp.saveGame(tssGame);
		} catch (Exception e) {
			fail();
		}		
	}

	/**
	 * @Test testIntegrationEditGame
	 * El test valida que se guarda correctamente el Game
	 */
	@DisplayName("Test Integration Save Game")
	@Test
	@Transactional
	public void testIntegrationSaveGame() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setNSprints(10);
		tsscGame.setNGroups(10);

		try {
			assertTrue(gameServiceImp.saveGame(tsscGame).equals(tsscGame));
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationSaveGameConTopic
	 * El test valida que se guarda correctamente el Game con un Topic asociado
	 */
	@DisplayName("Test Integration Save GameWithTopic")
	@Test
	@Transactional
	public void testIntegrationSaveGameConTopic() throws Exception {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(tsscTopic);

		try {
			assertTrue(gameServiceImp.saveGameTopic(tsscGame, 1).equals(tsscGame));
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationSaveGameConTopic
	 * El test valida que se guarda correctamente un Game a partir de un Topic
	 */
	@DisplayName("Test Integration Save GameWithTopic2")
	@Test
	@Transactional
	public void testIntegrationSaveGameConTopic2() throws Exception{
		TsscGame tsscGame = new TsscGame(); //Esta es la clase base con la cual se va a modificar dado el id del topic
		tsscGame.setNGroups(10); 
		tsscGame.setNSprints(10);
		
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setNGroups(10); //Valores base para la comparación
		tsscGame2.setNSprints(10); // Valores base para la comparación
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		
		gameServiceImp.saveGame(tsscGame);
		topicServiceImp.saveTopic(tsscTopic);
		
		try {
			assertTrue(gameServiceImp.saveGameWithTopic(tsscGame, tsscTopic.getId()).getNGroups() == tsscGame2.getNGroups());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationEditGame
	 * El test valida que se edita correctamente el Game
	 */
	@DisplayName("Test Integration Edit Game")
	@Test
	@Transactional
	public void testIntegrationEditGame() throws Exception {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		
		tsscGame.setTsscTopic(tsscTopic);
		gameServiceImp.saveGame(tsscGame);
		
		try {
			assertTrue(gameServiceImp.editGame(tsscGame, 1).equals(tsscGame));			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
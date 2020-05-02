package co.edu.icesi.fi.tics.tssc.dao;


import static org.junit.Assert.assertNotNull;
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
@RunWith(SpringRunner.class)
@SpringBootTest
class JUnitGameDao {

	@Autowired
	private IGameDao gameServiceImp;
	
	@Autowired
	private ITopicDao topicServiceImp;

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
	public void testIntegrationSaveGame() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(10);
		tsscGame.setNSprints(10);
		tsscGame.setNGroups(10);

		try {
			gameServiceImp.saveGame(tssGame);
			assertNotNull(gameServiceImp.findById(tsscGame.getId()));
		} catch (Exception e) {
			fail();
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
			gameServiceImp.editGame(tsscGame);
			assertNotNull(gameServiceImp.findById(tsscGame.getId()));
		} catch (Exception e) {
			fail();
		}
	}
}

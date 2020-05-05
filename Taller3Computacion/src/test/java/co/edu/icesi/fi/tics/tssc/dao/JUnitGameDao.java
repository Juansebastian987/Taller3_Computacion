package co.edu.icesi.fi.tics.tssc.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class JUnitGameDao {

	@Autowired
	private IGameDao iGameDao;
	
	@Autowired
	private IStoryDao iStoryDao;
	
	@Autowired
	private ITopicDao iTopicDao;

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
			iTopicDao.saveTopic(tsscTopic);
			iGameDao.saveGame(tssGame);
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testIntegrationSaveGame() {
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		try {
			iGameDao.saveGame(tsscGame);
			assertNotNull(iGameDao.findById(tsscGame.getId()).get(0));
			assertEquals("tsscGame", iGameDao.findById(tsscGame.getId()).get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationEditGame
	 * El test valida que se edita correctamente el Game
	 */
	@DisplayName("Test Integration Edit Game")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testIntegrationEditGame() throws Exception {
		
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		TsscGame tsscGame2 = new TsscGame();
		tsscGame.setName("tsscGame2");
		tsscGame2.setNGroups(10);
		tsscGame2.setNSprints(10);
		
		iGameDao.saveGame(tsscGame2);
		iGameDao.editGame(tsscGame);

		assertNotNull(iGameDao.findByName("tsscGame2"));
		
		iGameDao.delete(tsscGame);	
	}
	

	@DisplayName("Test Integration Delete")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete() {
		
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);		
		iGameDao.delete(tsscGame);
		
		assertEquals(0, iGameDao.findAll().size());
	}
	
	@DisplayName("Test Integration FindById")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findById() {
		
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);

		try {
			iGameDao.saveGame(tsscGame);
			assertNotEquals(0, iGameDao.findById(tsscGame.getId()).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@DisplayName("Test Integration findByName")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByName() {
		
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		try {
			iGameDao.saveGame(tsscGame);
			assertNotEquals(0, iGameDao.findByName("tsscGame").get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}						

	}
	
	@DisplayName("Test Integration findByDescription")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDescription() {
		
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setDescription("Description");
				
		try {
			iGameDao.saveGame(tsscGame);
			assertNotNull(iGameDao.findByDescription(tsscGame.getTsscStories().get(0).getDescription()).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DisplayName("Test Integration findByDate")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDate() {
		
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		tsscGame.setScheduledDate(LocalDate.of(2020, 05, 05));
		
		try {
			iGameDao.saveGame(tsscGame);
			assertNotNull(iGameDao.findByDate(LocalDate.of(2020, 01, 01), LocalDate.of(2020, 12, 12)).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DisplayName("Test Integration findByDateAndTimeRange")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDateAndTimeRange() {
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		tsscGame.setScheduledTime(LocalTime.of(5, 5));
		tsscGame.setScheduledDate(LocalDate.of(2020, 05, 05));
		
		try {
			iGameDao.saveGame(tsscGame);
			assertEquals(1, iGameDao.findByDateAndTimeRange(LocalDate.of(2020, 05, 05), LocalTime.of(5, 5)).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DisplayName("Test Integration DateChronometer")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dateChronometer() {
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		tsscGame.setScheduledDate(LocalDate.of(2020, 05, 05));
		
		try {
			iGameDao.saveGame(tsscGame);
			assertNotNull(iGameDao.dateChronometer(LocalDate.of(2020, 05, 05)).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	}
	
	@DisplayName("Test Integration findByScheduledGame")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByScheduledGame() {
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		tsscGame.setScheduledDate(LocalDate.of(2020, 05, 05));
		
		try {
			iGameDao.saveGame(tsscGame);
			assertNotNull(iGameDao.findByScheduledGame(LocalDate.of(2020, 05, 05)).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@DisplayName("Test Integration deleteAll")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAll() {
		assertNotNull(iGameDao);
		iStoryDao.deleteAll();
		iGameDao.deleteAll();
		iTopicDao.deleteAll();

		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		iGameDao.delete(tsscGame);
		assertEquals(0, iGameDao.findAll().size());
	}
}
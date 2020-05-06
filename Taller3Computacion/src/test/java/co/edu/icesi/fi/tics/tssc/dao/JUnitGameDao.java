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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
@RunWith(SpringRunner.class)
//@SpringBootTest
@ContextConfiguration("/applicationContext.xml")
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
	 * @Test testIntegrationSaveGame
	 * El test valida que se guarda correctamente el Game
	 */
	@DisplayName("Test Dao Save Game")
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
	 * @Test testIntegrationEditGame
	 * El test valida que se edita correctamente el Game
	 */
	@DisplayName("Test Dao Edit Game")
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
	

	/**
	 * @Test delete
	 * El test valida que se elimina correctamente el Game
	 */
	@DisplayName("Test Dao Delete")
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
	
	/**
	 * @Test findById
	 * El test valida que se encuentra correctamente el Game por su Id
	 */
	@DisplayName("Test Dao FindById")
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
	
	/**
	 * @Test findByName
	 * El test valida que se encuentra correctamente el Game por su Nombre
	 */
	@DisplayName("Test Dao findByName")
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
	
	/**
	 * @Test findByDescription
	 * El test valida que se encuentra correctamente el Game por su Descripción
	 */
	@DisplayName("Test Dao findByDescription")
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
	
	/**
	 * @Test findByDate
	 * El test valida que se encuentra correctamente el Game por su Fecha
	 */
	@DisplayName("Test Dao findByDate")
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
	
	/**
	 * @Test findByDateAndTimeRange
	 * El test valida que se pueda permitir que los juegos puedan buscarse por rango de 
	 * fecha o por la combinación de una fecha y rango de horas.
	 */
	@DisplayName("Test Dao findByDateAndTimeRange")
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
	

	/**
	 * @Test dateChronometer
	 * El test valida que muestra los juegos que están programados para una fecha pero tienen menos de 
	 * diez historias asociadas para una fecha dada o no tienen al menos un cronómetro especificado.
	 */
	@DisplayName("Test Dao DateChronometer")
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
		}	
		
	}
	
	
	/**
	 * @Test findByScheduledGame
	 * El test valida que se encuentra el game que están programados para una fecha pero tienen menos de diez 
	 * historias asociadas para una fecha dada
	 */
	@DisplayName("Test Dao findByScheduledGame")
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
		
	
	/**
	 * @Test deleteAll
	 * El test valida que se elimine todos los Games correctamente
	 */
	@DisplayName("Test Dao deleteAll")
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
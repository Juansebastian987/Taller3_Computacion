package co.edu.icesi.fi.tics.tssc.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

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
class JUnitTopicDao {
	
	@Autowired
	private ITopicDao iTopicDao;

	@Autowired
	private IGameDao iGameDao;
	
	private TsscTopic tsscTopic;
	
	@BeforeEach
	public void setUp() {
		tsscTopic = new TsscTopic();
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		try {
			iTopicDao.saveTopic(tsscTopic);
		} catch (Exception e) {
			fail();
		}		
	}

	/**
	 * @Test testIntegrationSaveTopic
	 * El test valida que se guarda correctamente el Topic
	 */
	@DisplayName("Test Dao Save Topic")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testIntegrationSaveTopic() {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();

		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
		
		try {
			iTopicDao.saveTopic(tsscTopic);
			assertNotNull(iTopicDao.findById(tsscTopic.getId()).get(0));
			assertEquals("Descripcion", iTopicDao.findById(tsscTopic.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationEditTopic
	 * El test valida que se edita correctamente el Topic
	 */
	@DisplayName("Test Dao Edit Topic")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testIntegrationEditTopic() throws Exception {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
		
		TsscTopic tsscTopic2 = new TsscTopic();
		tsscTopic2.setName("tsscTopic2");
		tsscTopic2.setDefaultGroups(10);
		tsscTopic2.setDefaultSprints(10);
		tsscTopic2.setDescription("Description2");
		
		iTopicDao.saveTopic(tsscTopic2);
		iTopicDao.editTopic(tsscTopic);

		assertNotNull(iTopicDao.findByName("tsscTopic2"));
		
		iTopicDao.delete(tsscTopic);
		
	}
	
	/**
	 * @Test delete
	 * El test valida que se elimine un Topic correctamente
	 */
	@DisplayName("Test Dao One delete")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete() {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
	
		iTopicDao.delete(tsscTopic);
		assertEquals(0, iTopicDao.findAll().size());
	}
	
	/**
	 * @Test findById
	 * El test valida que se encuentre un Topic por su ID
	 */
	@DisplayName("Test Dao findById")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findById() {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();

		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
			
		try {
			iTopicDao.saveTopic(tsscTopic);
			assertNotEquals(0, iTopicDao.findById(tsscTopic.getId()).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * @Test findByName
	 * El test valida que se encuentre un Topic por su Nombre
	 */
	@DisplayName("Test Dao findByName")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByName() {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();

		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
		
		try {
			iTopicDao.saveTopic(tsscTopic);
			assertNotEquals(0, iTopicDao.findByName("TsscTopic").get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
		
	/**
	 * @Test findByDescription
	 * El test valida que se encuentre un Topic por su Descripción
	 */
	@DisplayName("Test Dao findByDescription")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDescription() {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();

		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
		
		try {
			iTopicDao.saveTopic(tsscTopic);
			assertNotEquals(0, iTopicDao.findByDescription("Description").get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}	
	
	/**
	 * @Test findByDate
	 * El test valida que se encuentre un Topic por su Fecha
	 */
	@DisplayName("Test Dao findByDate")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDate() {
		assertNotNull(iTopicDao);
		iTopicDao.deleteAll();

		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("Description");
		
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
	
}

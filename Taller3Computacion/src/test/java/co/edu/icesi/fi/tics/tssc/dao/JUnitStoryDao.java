package co.edu.icesi.fi.tics.tssc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class JUnitStoryDao {

	@Autowired
	private IStoryDao iStoryDao;

	@Autowired
	private IGameDao iGameDao;

	private TsscGame tsscGame;
	
	private TsscStory tsscStory;

	@Before
	public void setUp() {
		tsscStory = new TsscStory();
		tsscStory.setId(10);
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));
		
		tsscGame = new TsscGame();
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);

		try {
			iGameDao.saveGame(tsscGame);
			iStoryDao.saveStory(tsscStory);
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * @throws Exception 
	 * @Test testIntegrationSaveStory
	 * El test valida que se guarda correctamente la Story
	 */
	@DisplayName("Test Dao Save Story")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testIntegrationSaveStory() throws Exception {
		assertNotNull(iStoryDao);
		iStoryDao.deleteAll();

		TsscStory tsscStory = new TsscStory();
		tsscStory.setDescription("Descripcion");
		
		try {
			iStoryDao.saveStory(tsscStory);
			assertNotNull(iStoryDao.findById(tsscStory.getId()).get(0));
			assertEquals("Descripcion", iStoryDao.findById(tsscStory.getId()).get(0).getAltDescripton());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception 
	 * @Test testIntegrationEditStory
	 * El test valida que se edita correctamente la Story
	 */
	@DisplayName("Test Dao Edit Story")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testIntegrationEditStory() throws Exception {
		assertNotNull(iStoryDao);
		iStoryDao.deleteAll();
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setDescription("Description1");
		
		TsscStory tsscStory2 = new TsscStory();
		tsscStory2.setDescription("Description2");

		iStoryDao.saveStory(tsscStory2);
		iStoryDao.editStory(tsscStory);

		assertNotNull(iGameDao.findByName("Description2"));
		iStoryDao.delete(tsscStory);
		
	}
	
	/**
	 * @Test delete
	 * El test valida que se elimina una Story correctamente
	 */
	@DisplayName("Test Dao One delete")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete() {
		assertNotNull(iStoryDao);
		iStoryDao.deleteAll();

		TsscStory tsscStory = new TsscStory();
		tsscStory.setDescription("Description1");
		
		try {
			iStoryDao.delete(tsscStory);
			assertEquals(0, iStoryDao.findAll().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * @Test findById
	 * El test valida que se encuentra una Story por su ID
	 */
	@DisplayName("Test Dao findById")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findById() {
		assertNotNull(iStoryDao);
		iStoryDao.deleteAll();

		TsscStory tsscStory = new TsscStory();
		tsscStory.setDescription("Description1");

		try {
			iStoryDao.saveStory(tsscStory);
			assertNotEquals(0, iStoryDao.findById(tsscStory.getId()).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * @Test deleteAll
	 * El test valida que se eliminen todas las Stories correctamente
	 */
	@DisplayName("Test Dao deleteAll")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAll() {
		assertNotNull(iStoryDao);
		iStoryDao.deleteAll();
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setDescription("Description1");

		iStoryDao.delete(tsscStory);
		assertEquals(0, iStoryDao.findAll().size());
	}
}

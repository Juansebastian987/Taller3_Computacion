package co.edu.icesi.fi.tics.tssc.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.service.GameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.StoryServiceImp;


@RunWith(SpringRunner.class)
@SpringBootTest
class JUnitIntegrationStory {

	@Autowired
	private StoryServiceImp storyServiceImp;

	@Autowired
	private GameServiceImp gameService;

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
			gameService.saveGame(tsscGame);
			storyServiceImp.saveStory(tsscStory, (int) tsscGame.getId());
		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * @throws Exception 
	 * @Test testIntegrationSaveStory
	 * El test valida que se guarda correctamente la Story
	 */
	@DisplayName("Test Integration Save Story")
	@Test
	@Transactional
	public void testIntegrationSaveStory() throws Exception {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setInitialSprint(new BigDecimal(100));
		tsscStory.setBusinessValue(new BigDecimal(100));
		tsscStory.setPriority(new BigDecimal(100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		tsscStory.setTsscGame(tsscGame);
		
	//	try {
			assertTrue(storyServiceImp.saveStory(tsscStory, 1).equals(tsscStory));
	//	} catch (Exception e) { 
	//		fail();
	//	}

	}

	/**
	 * @throws Exception 
	 * @Test testIntegrationEditStory
	 * El test valida que se edita correctamente la Story
	 */
	@DisplayName("Test Integration Edit Story")
	@Test
	@Transactional
	public void testIntegrationEditStory() throws Exception {
		
		TsscStory tsscStory1 = new TsscStory();
		tsscStory1.setInitialSprint(new BigDecimal(100));
		tsscStory1.setBusinessValue(new BigDecimal(100));
		tsscStory1.setPriority(new BigDecimal(100));
		
		TsscStory tsscStory2 = new TsscStory();
		tsscStory2.setInitialSprint(new BigDecimal(100));
		tsscStory2.setBusinessValue(new BigDecimal(100));
		tsscStory2.setPriority(new BigDecimal(100));
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setNGroups(100);
		tsscGame.setNSprints(100);
		
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setNGroups(100);
		tsscGame2.setNSprints(100);
		
		tsscStory1.setTsscGame(tsscGame);
		tsscStory2.setTsscGame(tsscGame2);
		
		storyServiceImp.saveStory(tsscStory1, 1);
		storyServiceImp.saveStory(tsscStory2, 2);
		
		try {
			assertTrue(storyServiceImp.editStory(tsscStory1, 1).getBusinessValue()==tsscStory1.getBusinessValue());		
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

}
package co.edu.icesi.fi.tics.tssc.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TopicServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
class JUnitIntegrationTopic {
	

	@Autowired
	private TopicServiceImp topicServiceImp;

	private TsscTopic tsscTopic;
	
	@BeforeEach
	public void setUp() {
		tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		try {
			topicServiceImp.saveTopic(tsscTopic);
		} catch (Exception e) {
			e.getStackTrace();
		}		
	}

	/**
	 * @Test testIntegrationSaveTopic
	 * El test valida que se guarda correctamente el Topic
	 */
	@DisplayName("Test Integration Save Topic")
	@Test
	public void testIntegrationSaveTopic() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setDefaultGroups(1);
		tsscTopic.setDefaultSprints(1);

		try {
			assertTrue(topicServiceImp.saveTopic(tsscTopic).equals(tsscTopic));
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationEditTopic
	 * El test valida que se edita correctamente el Topic
	 */
	@DisplayName("Test Integration Edit Topic")
	@Test
	@Transactional
	public void testIntegrationEditTopic() throws Exception {
		TsscTopic tsscTopic1 = new TsscTopic();
		tsscTopic1.setDefaultGroups(10);
		tsscTopic1.setDefaultSprints(10);
		tsscTopic1.setName("test1");
				
		topicServiceImp.saveTopic(tsscTopic1);
		
		try {
			assertTrue(topicServiceImp.editTopic(tsscTopic1,1).getDefaultGroups() == tsscTopic.getDefaultGroups());	
		} catch (Exception e) {
			e.getStackTrace();
		}	
	}
}
package co.edu.icesi.fi.tics.tssc.tests;

import static org.junit.jupiter.api.Assertions.*;

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

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.ITopicRepository;
import co.edu.icesi.fi.tics.tssc.service.TopicServiceImp;

import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.transaction.Transactional;

@RunWith(MockitoJUnitRunner.class)
class JUnitTopic {

	@Mock
	private ITopicRepository topicRepository; //Dependencia
	
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
		 * El test valida la exception de cuando las cantidad de group y sprint no son las correctas
		 */
		@DisplayName("Save Exception")
		@Test
		void testSaveException() {
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(-1);
			tsscTopic.setDefaultSprints(-1);
			
			assertThrows(Exception.class, () -> {
				topicServiceImp.saveTopic(tsscTopic);
			});
		
			Mockito.verifyNoMoreInteractions(topicRepository);
		}
		
		/**
		 * @Test testSaveNotZero
		 * Este test valida que la cantidad de los elementos no sea cero
		 */
		@DisplayName("Save Not Zero")
		@Test
		void testSaveNotZero() {
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(0);
			tsscTopic.setDefaultSprints(0);
			
			assertThrows(Exception.class, () -> {
				topicServiceImp.saveTopic(tsscTopic);
			});
		
			Mockito.verifyNoMoreInteractions(topicRepository);
		}
		
		/**
		 * @Test testSaveSprintCorrect
		 * El Test valida que al tener su sprint correctamente el Topic se guarde.
		 * pre-conditin: Se asume que su group esta correctamente.
		 */
		@DisplayName("Save Sprint Correct")
		@Test
		@Transactional
		void testSaveSprintCorrect() {		
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(10);
			tsscTopic.setDefaultSprints(10);
			
			when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
			try {
				assertTrue(topicServiceImp.saveTopic(tsscTopic).equals(tsscTopic));
				verify(topicRepository, times(1)).save(tsscTopic);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		/**
		 * @Test testSaveGroupsCorrect
		 * El Test valida que al tener su group correctamente el Topic de guarde
		 * pre-condition: Se asume que su sprint esta correctamente
		 */
		@DisplayName("Save Groups Correct")
		@Test
		@Transactional
		void testSaveGroupsCorrect() {
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(1);
			tsscTopic.setDefaultSprints(10);
			
			when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
			try {
				assertTrue(topicServiceImp.saveTopic(tsscTopic).equals(tsscTopic));
				verify(topicRepository, times(1)).save(tsscTopic);
			} catch (Exception e) {
				e.getStackTrace();
			}
			
		}
		
		/**
		 * @Test testSaveSprintMenorCero
		 * El test valida que si el sprint es menor que cero no se pueda guardar el Topic
		 * pre-condition: Se asume que su group esta correctamente
		 */
		@DisplayName("Save Sprint Menor A Cero")
		@Test
		void testSaveSprintMenorCero() {
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(10);
			tsscTopic.setDefaultSprints(-1);
			
			assertThrows(Exception.class, () -> {
				topicServiceImp.saveTopic(tsscTopic);
			});
		
			Mockito.verifyNoMoreInteractions(topicRepository);
			
		}
		
		/**
		 * @Test testSaveSprintMayorCero
		 * El test valida que si el sprint es mayor que cero si se pueda guardar el Topic
		 * pre-condition: Se asume que su group esta correctamente
		 */
		@DisplayName("Save Sprint Mayor A Cero")
		@Test
		void testSaveSprintMayorCero() {
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(10);
			tsscTopic.setDefaultSprints(10);
			
			when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
			try {
				assertTrue(topicServiceImp.saveTopic(tsscTopic).equals(tsscTopic));
				verify(topicRepository, times(1)).save(tsscTopic);
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
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(-1);
			tsscTopic.setDefaultSprints(10);
			
			assertThrows(Exception.class, () -> {
				topicServiceImp.saveTopic(tsscTopic);
			});
		
			Mockito.verifyNoMoreInteractions(topicRepository);
			
		}
		
		/**
		 * @Test testSaveGroupMayorCero
		 * El test valida que si el grupo es mayor que cero si se pueda guardar el Topic
		 * pre-condition: Se asume que su sprint esta correctamente
		 */
		@DisplayName("Save Group Mayor A Cero")
		@Test
		void testSaveGroupMayorCero() {
			TsscTopic tsscTopic = new TsscTopic();
			tsscTopic.setDefaultGroups(20);
			tsscTopic.setDefaultSprints(1);
			
			when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
			try {
				assertTrue(topicServiceImp.saveTopic(tsscTopic).equals(tsscTopic));
				verify(topicRepository, times(1)).save(tsscTopic);
			} catch (Exception e) {
				e.getStackTrace();
			}
		
		}
		
		/**
		 * @Test testSaveTopicNull
		 * El test valida que lo que se este guardando no sea un topic nulo
		 */
		@DisplayName("Save Topic Nulo")
		@Test
		void testSaveTopicNull() {
			TsscTopic tsscTopic = null;
			assertThrows(Exception.class, () -> {
				topicServiceImp.saveTopic(tsscTopic);
			});
			Mockito.verifyNoMoreInteractions(topicRepository);
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
	 * El test valida que se edita el Topic correctamente, tanto su group y sprint estan correctamente
	 */
	@DisplayName("Edit Topic Correct")
	@Test
	void testEditCorrect() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setDefaultGroups(30);
		tsscTopic.setDefaultSprints(30);
		tsscTopic.setName("test1");
		
		Optional<TsscTopic> optional =  Optional.of(tsscTopic);
		
		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(optional);
		
		try {			
			assertTrue(topicServiceImp.editTopic(tsscTopic, 10).equals(tsscTopic));
			verify(topicRepository, times(1)).save(tsscTopic);
			verify(topicRepository, times(1)).findById(tsscTopic.getId());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @Test testEditException
	 * El test valida que al editar el juego cumpla con su cantidad de sprint y group, sino lanza exception
	 */
	@DisplayName("Edit Topic Exception")
	@Test
	void testEditException() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setDefaultGroups(-1);
		tsscTopic.setDefaultSprints(-1);
		tsscTopic.setName("test1");

		Optional<TsscTopic> optional =  Optional.of(tsscTopic);

		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			topicServiceImp.editTopic(tsscTopic,10);
		});	
	}
	
	/**
	 * @Tets testEditTopicNull
	 * El test valida que el topic a editar no sea nulo
	 */
	@DisplayName("Edit Topic Nulo")
	@Test
	void testEditTopicNull() {
		TsscTopic tsscTopic = null;
		assertThrows(Exception.class, () -> {
			topicServiceImp.editTopic(tsscTopic, 10);
		});
		Mockito.verifyNoMoreInteractions(topicRepository);
		
	}
	
	
	/**
	 * @Test testEditGroupCorrect
	 * El test valida que al editar el juego la cantidad de group siga siendo la correcta,
	 * es decir que siga siendo mayor a cero, sino lanza una excepcion
	 * pre-condition: se asume que la cantidad de sprint esta correcto
	 */
	@DisplayName("Edit Group Correct")
	@Test
	void testEditGroupCorrect() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setDefaultGroups(-1);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setName("test1");

		Optional<TsscTopic> optional =  Optional.of(tsscTopic);

		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			topicServiceImp.editTopic(tsscTopic,10);
		});
	}
	
	/**
	 * @Test testEditSprintCorrect
	 * El test valida que al editar el juego la cantidad de spring siga siendo la correcta,
	 * es decir que siga siendo mayor a cero, sino lanza una excepcion
	 * pre-condition: se asume que la cantidad de group esta correcto
	 */
	@DisplayName("Edit Sprint Correct")
	@Test
	void testEditSprintCorrect() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(10);
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(-1);
		tsscTopic.setName("test1");

		Optional<TsscTopic> optional =  Optional.of(tsscTopic);

		when(topicRepository.save(tsscTopic)).thenReturn(tsscTopic);
		when(topicRepository.findById( tsscTopic.getId())).thenReturn(optional);
		
		assertThrows(Exception.class, () -> {
			topicServiceImp.editTopic(tsscTopic,10);
		});
	
	}
	}
}
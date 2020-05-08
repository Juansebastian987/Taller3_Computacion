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

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class JUnitAdminDao {

	@Autowired
	private IAdminDao iAdminDao;

	private TsscAdmin tsscAdmin;
	
	/**
	 * @Test testIntegrationSaveTopic
	 * El test valida que se guarda correctamente el Admin
	 */
	@DisplayName("Test Dao Save Admin")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAdmin() {
		assertNotNull(iAdminDao);
		iAdminDao.deleteAll();
		
		TsscAdmin tsscAdmin = new TsscAdmin();
		tsscAdmin.setUsername("Juan sebastian");
		tsscAdmin.setSuperAdmin("ADMIN");
		tsscAdmin.setPassword("{noop}123456");
		
		try {
			iAdminDao.save(tsscAdmin);
			assertNotNull(iAdminDao.findById(tsscAdmin.getId()).get(0));
			assertEquals("Juan sebastian", iAdminDao.findById(tsscAdmin.getId()).get(0).getUsername());
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
	/**
	 * @throws Exception 
	 * @Test testIntegrationEditTopic
	 * El test valida que se edita correctamente el Admin
	 */
	@DisplayName("Test Dao Edit Admin")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testEditAdmin() throws Exception {
		assertNotNull(iAdminDao);
		iAdminDao.deleteAll();
		
		TsscAdmin tsscAdmin = new TsscAdmin();
		tsscAdmin.setUsername("Juan sebastian");
		tsscAdmin.setSuperAdmin("SUPERADMIN");
		tsscAdmin.setPassword("{noop}123456");
		
		TsscAdmin tsscAdmin2 = new TsscAdmin();
		tsscAdmin2.setUsername("Juan Cardona");
		tsscAdmin2.setSuperAdmin("SUPERADMIN");
		tsscAdmin2.setPassword("{noop}12345678");
		
		iAdminDao.save(tsscAdmin2);
		iAdminDao.edit(tsscAdmin2);

		try {
			assertEquals(1,iAdminDao.findById(tsscAdmin2.getId()).size());			
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
	
	/**
	 * @Test delete
	 * El test valida que se elimine un Admin correctamente
	 */
	@DisplayName("Test Dao Delete Admin")
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAdmin() {
		assertNotNull(iAdminDao);
		iAdminDao.deleteAll();
		
		TsscAdmin tsscAdmin = new TsscAdmin();
		tsscAdmin.setUsername("Juan sebastian");
		tsscAdmin.setSuperAdmin("SUPERADMIN");
		tsscAdmin.setPassword("{noop}123456");
		
		iAdminDao.delete(tsscAdmin);
		assertEquals(0, iAdminDao.findAll().size());
		
	}

}

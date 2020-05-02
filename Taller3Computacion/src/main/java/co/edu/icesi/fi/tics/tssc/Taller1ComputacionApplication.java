package co.edu.icesi.fi.tics.tssc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.service.AdminServiceImp;

@SpringBootApplication
public class Taller1ComputacionApplication {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext c = SpringApplication.run(Taller1ComputacionApplication.class, args);	
		AdminServiceImp adminService = c.getBean(AdminServiceImp.class);
		
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("SUPER_ADMIN");
		admin.setPassword("{noop}123456");
		admin.setSuperAdmin("SUPER_ADMIN");		
		adminService.saveTsscAdmin(admin);
		
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setUsername("ADMIN");
		admin2.setPassword("{noop}123456");
		admin2.setSuperAdmin("ADMIN");	
		adminService.saveTsscAdmin(admin2);
	}

}

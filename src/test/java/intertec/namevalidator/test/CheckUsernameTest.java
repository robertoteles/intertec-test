package intertec.namevalidator.test;

import java.util.Iterator;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import intertec.namevalidator.bean.Result;
import intertec.namevalidator.model.Username;
import intertec.namevalidator.service.UtilityService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/spring-*-test.xml" })
public class CheckUsernameTest extends TestCase {
	private static final Logger logger = Logger.getLogger(CheckUsernameTest.class.getName());
	@Autowired
	UtilityService utilityService;

	@Test
	public void testCheckUsername() {
		String usernameString = "PodamnTo";
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");
		System.out.println("------------------TEST 1--------------------------");
		System.out.println("--------RESTRICTED WORD IN USERNAME SUGGESTION----");
		System.out.println("------------------GENERATED-----------------------");
		Result result = utilityService.checkUsername(usernameString);
		assertTrue("Verify if suggestion Array provides elements when use word banned damn ",
				!result.getUsernames().isEmpty());
		for (Iterator iterator = result.getUsernames().iterator(); iterator.hasNext();) {
			Username type = (Username) iterator.next();
			System.out.println(type.getName());
		}
		
//		//------------------------------------------------------------------------
//		//------------------------------------------------------------------------
//		//------------------------------------------------------------------------
//		String duplicatedUername = "Loperz";
//		System.out.println("--------------------------------------------------");
//		System.out.println("--------------------------------------------------");
//		System.out.println("------------------TEST 1--------------------------");
//		System.out.println("--------USERNAME SUGGESTION ALREADY EXISTS ON BD--");
//		System.out.println("------------------GENERATED-----------------------");
//		Result resultDuplicatedUsername = utilityService.checkUsername(duplicatedUername);
//		assertTrue("Verify if suggestion Array provides elements when use word banned damn ",
//				!result.getUsernames().isEmpty());
//		for (Iterator iterator = resultDuplicatedUsername.getUsernames().iterator(); iterator.hasNext();) {
//			Username type = (Username) iterator.next();
//			System.out.println(type.getName());
//		}
//		
	}
	
	
}

package intertec.namevalidator.service;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intertec.namevalidator.model.RestrictedWord;
import intertec.namevalidator.model.SuggestedWord;
import intertec.namevalidator.model.Username;

@Service
public class PrepareDataService implements InitializingBean {
	
	private static final Logger logger = Logger.getLogger(PrepareDataService.class.getName());

	@Autowired
	private SessionFactory sessionFactory;
	
	public void afterPropertiesSet() throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		long restrictedWordsCount = (Long)session.createQuery("select count(*) from RestrictedWord")
			.uniqueResult();
		logger.info("There are currently "+restrictedWordsCount+" users in the system");
		if (restrictedWordsCount == 0)
		{
			RestrictedWord restrictedWord1= new RestrictedWord("cannabis");
			RestrictedWord restrictedWord2 = new RestrictedWord("abuse");
			RestrictedWord restrictedWord3 = new RestrictedWord("crack");
			RestrictedWord restrictedWord4 = new RestrictedWord("damn");
			RestrictedWord restrictedWord5 = new RestrictedWord("drunk");
			RestrictedWord restrictedWord6 = new RestrictedWord("grass");

			
			session.save(restrictedWord1);
			session.save(restrictedWord2);
			session.save(restrictedWord3);
			session.save(restrictedWord4);
			session.save(restrictedWord5);
			session.save(restrictedWord6);
			
			System.out.println("===================================================================");
			System.out.println();System.out.println();System.out.println();
			System.out.println("RESTRICTED WORDS LIST CREATED");
			System.out.println();System.out.println();System.out.println();
			System.out.println("===================================================================");
			
			SuggestedWord suggestedWord1= new SuggestedWord("age");
			SuggestedWord suggestedWord2= new SuggestedWord("all");
			SuggestedWord suggestedWord3= new SuggestedWord("ence");
			SuggestedWord suggestedWord4= new SuggestedWord("doom");
			SuggestedWord suggestedWord5= new SuggestedWord("ee");
			SuggestedWord suggestedWord6= new SuggestedWord("hood");
			SuggestedWord suggestedWord7= new SuggestedWord("ism");
			SuggestedWord suggestedWord8= new SuggestedWord("tst");

			
			session.save(suggestedWord1);
			session.save(suggestedWord2);
			session.save(suggestedWord3);
			session.save(suggestedWord4);
			session.save(suggestedWord5);
			session.save(suggestedWord6);
			session.save(suggestedWord7);
			session.save(suggestedWord8);
			
			
			Username username= new Username("Loperz"); 
			session.save(username);
			
			System.out.println("===================================================================");
			System.out.println();System.out.println();System.out.println();
			System.out.println("SUGGESTED WORDS LIST CREATED");
			System.out.println();System.out.println();System.out.println();
			System.out.println("===================================================================");
			
		}
		tx.commit();
		session.close();
	}
}

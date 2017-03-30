package intertec.namevalidator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import intertec.namevalidator.bean.Result;
import intertec.namevalidator.model.RestrictedWord;
import intertec.namevalidator.model.Username;

@Service
public class UtilityService {

	private static final Logger logger = Logger.getLogger(UtilityService.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param username
	 * @return
	 */
	public Result checkUsername(String usernameString) {
		Result result = new Result();

		Session session = sessionFactory.openSession();
		Query queryRestricted = session.createQuery("from RestrictedWord r");
		Query queryUsername = session.createQuery("from Username u where u.name =:username");
		// Query querySuggested = session.createQuery("from SuggestedWord s");

		Username username = (Username) queryUsername.setParameter("username", usernameString).uniqueResult();
		if (username != null) {
			result.setUsernames(createSuggestions(queryUsername, username.getName()));
			result.setMessage("A username: " + username.getName()
					+ " already exists in BD, please choose any of the suggestion bellow");
		} else {
			result.setIsValid(true);
		}

		/**
		 * If username not exists, check if username has a banned word
		 */
		if (result.getIsValid()) {
			List<RestrictedWord> restrictedWords = queryRestricted.list();

			String suggestionSeed = "";
			for (Iterator iterator = restrictedWords.iterator(); iterator.hasNext();) {
				RestrictedWord restrictedWord = (RestrictedWord) iterator.next();
				result.setIsValid(!usernameString.toUpperCase().contains(restrictedWord.getWord().toUpperCase()));
				if (!result.getIsValid()) {
					suggestionSeed = usernameString.replaceAll(restrictedWord.getWord(), "");
					break;
				}
			}
			if (!result.getIsValid()) {
				result.setUsernames(createSuggestions(queryUsername, suggestionSeed));
				result.setMessage("The word: " + usernameString
						+ "  is not allowed as username, please choose one of the suggestion bellow:");
			}
		}
		return result;
	}

	/**
	 * 
	 * @param queryUsername
	 * @param username
	 * @return
	 */
	private List<Username> createSuggestions(Query queryUsername, String username) {
		int suggestionCounter = 0;
		List<Username> suggestionList = new ArrayList<Username>();
		while (suggestionCounter < 14) {
			String suggestUsername = createSuggestion(username, suggestionCounter);
			Username usernameNew = (Username) queryUsername.setParameter("username", suggestUsername).uniqueResult();
			if (usernameNew == null) {
				suggestionList.add(new Username(suggestUsername));
			}
			suggestionCounter++;
		}
		Collections.sort(suggestionList);
		return suggestionList;
	}

	/**
	 * 
	 * @param username
	 * @param i
	 * @return
	 */
	private String createSuggestion(String username, int i) {
		return username + i;
	}

	/**
	 * 
	 * @param username
	 */
	public void saveUsername(Username username) {
		sessionFactory.getCurrentSession().save(username);
		sessionFactory.close();

	}

}

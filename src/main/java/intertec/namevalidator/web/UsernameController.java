package intertec.namevalidator.web;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;

//import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import intertec.namevalidator.bean.Result;
import intertec.namevalidator.model.RestrictedWord;
import intertec.namevalidator.model.Username;
import intertec.namevalidator.service.UtilityService;

@Controller
public class UsernameController {

	private static final Logger logger = Logger.getLogger(UtilityService.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UtilityService facadeAbstract;

	@RequestMapping(value = "/addUsername.htm", method = RequestMethod.POST)
	public ModelAndView addUsername(Username username, Errors errors) {

		logger.info("================");
		logger.info(username.getName());
		logger.info("================");
		if (username.getName().length()<6 )
		{
			ModelAndView mav = new ModelAndView("messages");
			mav.addObject("message", "At least 6 character requerided");
			return mav;
		}
		Result result = facadeAbstract.checkUsername(username.getName());
		if (!result.getIsValid()) {
			ModelAndView mav = new ModelAndView("addSuggestion");
			mav.addObject("result", result);
			return mav;
		}

		sessionFactory.getCurrentSession().save(username);
		sessionFactory.close();
		return new ModelAndView("redirect:/allUsernames.htm");
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addRestrictedWord.htm")
	public String addRestrictedWord() {
		return "addRestrictedWord";
	}

	@RequestMapping(value="/addRestrictedWord.htm", method=RequestMethod.POST)
	public ModelAndView addRestrictedWord(RestrictedWord  restrictedWord, Errors errors)
	{
		Result result = new Result(); 
		try{
		sessionFactory.getCurrentSession().save(restrictedWord);
		sessionFactory.close();
		}catch (HibernateException e) {
			result.setIsValid(false); 
			result.setMessage("The word: "+restrictedWord +" already exists in bd");
			ModelAndView mav = new ModelAndView("addRestrictedWord");
			mav.addObject("result", result );
			return mav;
		}
		
		return new ModelAndView("redirect:/allRestrictedWords.htm");
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addUsername.htm")
	public String addUsername() {
		return "addUsername";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/allUsernames.htm")
	public ModelAndView allUsernames() {
		List<Username> usernames = sessionFactory.getCurrentSession().createQuery("FROM Username").list();

		ModelAndView mav = new ModelAndView("allUsernames");
		mav.addObject("usernames", usernames);
		return mav;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/allRestrictedWords.htm")
	public ModelAndView allRestrictedWords() {
		List<RestrictedWord> restrictedWords = sessionFactory.getCurrentSession().createQuery("FROM RestrictedWord r").list();

		ModelAndView mav = new ModelAndView("allRestrictedWords");
		mav.addObject("restrictedWords", restrictedWords);
		return mav;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addSuggestion.htm", method = RequestMethod.GET)
	public ModelAndView addSuggestions(@RequestParam("suggestion") String suggestion) {
		facadeAbstract.saveUsername(new Username(suggestion));
		ModelAndView mav = new ModelAndView("messages");
		mav.addObject("message", "User Name :" + suggestion + " Created!");
		return mav;
	}

}

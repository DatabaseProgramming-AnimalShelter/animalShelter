package controller.review;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Animal;
import model.Review;
import model.service.AnimalManager;
import model.service.ExistingUserException;
import model.service.ReviewManager;

public class RegisterReviewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterReviewController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int animal_id = Integer.parseInt(request.getParameter("animal_id"));
		
		Animal animal = null;
		AnimalManager animalManager = AnimalManager.getInstance();

		animal = animalManager.findAnimal(animal_id);
		
       if (request.getMethod().equals("GET")) {
   		  // GET request: 리뷰 등록 form 요청	
          log.debug("ApplyForm Request");
          
		  request.setAttribute("animal_id", animal_id);

          return "/review/registerForm.jsp";   
       }  
     
      Review review = new Review(
    	 animal.getAnimal_id(), 
    	 user_id,
         request.getParameter("title"),
         request.getParameter("content")
         );
      
      try {
         ReviewManager manager = ReviewManager.getInstance();
         manager.create(review);

         log.debug("Create Review : {}", review);
         return "redirect:/review/list";

      } catch (ExistingUserException e) {   
         request.setAttribute("registerFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("review", review);
         return "/review/registerForm.jsp";
      }
    }
}
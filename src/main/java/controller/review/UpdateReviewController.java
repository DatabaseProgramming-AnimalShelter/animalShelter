package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.AnimalManager;
import model.service.ReviewManager;
import model.Animal;
import model.Review;

public class UpdateReviewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateReviewController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: 커뮤니티 수정 form 요청	
			System.out.println("@@@@@@@@@@review post_id: ----------" + post_id );

    		ReviewManager manager = ReviewManager.getInstance();
			Review review = manager.findReview(post_id);	// 수정하려는 커뮤니티 정보 검색
			request.setAttribute("review", review);			
				
			return "/review/updateForm.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
    	
		Review review = new Review(
				post_id,
		        request.getParameter("title"),
		        request.getParameter("content")
		 );
		      
    	log.debug("Update Review : {}", review);

    	ReviewManager manager = ReviewManager.getInstance();
		manager.update(review);		
		
        return "redirect:/review/list";
    }
}
package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ReviewManager;

public class DeleteReviewController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int post_id = Integer.parseInt(request.getParameter("post_id"));

		ReviewManager manager = ReviewManager.getInstance();

		manager.remove(post_id);

		return "redirect:/review/list";

	}
}
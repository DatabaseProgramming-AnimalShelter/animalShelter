package controller.review;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

        String title = null;
        String content = null;
        String filename = null;
        File dir = null;
       
       String user_id = UserSessionUtils.getLoginUserId(request.getSession());
      int animal_id = Integer.parseInt(request.getParameter("animal_id"));
      
      Animal animal = null;
      AnimalManager animalManager = AnimalManager.getInstance();
      animal = animalManager.findAnimal(animal_id);
      
        if (request.getMethod().equals("GET")) {
          log.debug("ApplyForm Request");
          ReviewManager reviewManager = ReviewManager.getInstance();
          Review review = reviewManager.findUserReview(user_id, animal_id);
          
          if(review != null) {
             request.setAttribute("review", review);
             return "/review/updateForm.jsp";
          }
         request.setAttribute("animal_id", animal_id);
         return "/review/registerForm.jsp";
        }  

      boolean check = ServletFileUpload.isMultipartContent(request);

      if (check) {

         ServletContext context = request.getServletContext();
         String path = context.getRealPath("/upload");
         dir = new File(path);
         if (!dir.exists())
            dir.mkdir();
         try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(10 * 1024);
            factory.setRepository(dir);
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(10 * 1024 * 1024);
            upload.setHeaderEncoding("utf-8");

            List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
            for (int i = 0; i < items.size(); ++i) {
               FileItem item = (FileItem) items.get(i);

               String value = item.getString("utf-8");
               if (item.isFormField()) {
                  if (item.getFieldName().equals("title")) title = value;
                  else if (item.getFieldName().equals("content")) content = value;
               } else {
                  if (item.getFieldName().equals("image")) {
                         filename = item.getName();
                         if(filename == null || filename.trim().length() == 0) continue;
                         filename = filename.substring(filename.lastIndexOf("\\") + 1);
        
                         File file = new File(dir, filename);
                         item.write(file);
                  }
               }
            }
         } catch (SizeLimitExceededException e) {
            e.printStackTrace();
         } catch (FileUploadException e) {
            e.printStackTrace();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      Review review = new Review(
               animal.getAnimal_id(), 
               user_id,
               title,
                content,
                filename
                );
      
      try {
         ReviewManager manager = ReviewManager.getInstance();
         manager.create(review);
         
         request.setAttribute("review", review);
       request.setAttribute("dir", dir);
       request.setAttribute("filename", filename);

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
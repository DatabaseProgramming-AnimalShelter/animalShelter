package controller.animal;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.animal.RegisterAnimalController;
import controller.user.UserSessionUtils;
import model.Animal;
import model.service.ExistingUserException;
import model.service.AnimalManager;
import model.service.AnimalNotFoundException;

import model.Heart;
import model.service.HeartManager;
//import model.service.AnimalNotFoundException;

//파일 업로드를 위한 API를 사용하기 위해...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;

public class RegisterAnimalController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterAnimalController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String species = null;
		String age = null;
		String location = null;
		String gender = null;
		String weight = null;
		String etc = null;
		String filename = null;
		File dir = null;
		if (request.getMethod().equals("GET")) {
			// GET request: 회원정보 등록 form 요청
			log.debug("ApplyForm Request");
			return "/animal/registerForm.jsp";
		}

		boolean check = ServletFileUpload.isMultipartContent(request);
		// 전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		// 만약 multipart가 아니라면 파일 전송을 처리하지 않는다.

		if (check) {// 파일 전송이 포함된 상태가 맞다면

			// 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트의 폴더 밑에 upload 폴더가 생성됨
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			dir = new File(path);

			// Tomcat 외부의 폴더에 저장하려면 아래와 같이 절대 경로로 폴더 이름을 지정함
//			dir = new File("C:/Temp");

			if (!dir.exists())
				dir.mkdir();
			// 전송된 파일을 저장할 실제 경로를 만든다.

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
				factory.setSizeThreshold(10 * 1024);
				// 메모리에 한번에 저장할 데이터의 크기를 설정한다.
				// 10kb 씩 메모리에 데이터를 읽어 들인다.
				factory.setRepository(dir);
				// 전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.

				ServletFileUpload upload = new ServletFileUpload(factory);
				// Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
				upload.setSizeMax(10 * 1024 * 1024);
				// 업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
				upload.setHeaderEncoding("utf-8");
				// 업로드 되는 내용의 인코딩을 설정한다.

				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				// upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
				for (int i = 0; i < items.size(); ++i) {
					FileItem item = (FileItem) items.get(i);
					System.out.println(item);
					// commons-fileupload를 사용하여 전송받으면
					// 모든 parameter는 FileItem 클래스에 하나씩 저장된다.

					String value = item.getString("utf-8");
					// 넘어온 값에 대한 한글 처리를 한다.
//                   System.out.println("@@@@@@@@@@@@Create Animal :---------------------"+value);
					if (item.isFormField()) {// 일반 폼 데이터라면...
						if (item.getFieldName().equals("species"))
							species = value;
						// key 값이 name이면 name 변수에 값을 저장한다.
						else if (item.getFieldName().equals("age"))
							age = value;
						// key 값이 id이면 id 변수에 값을 저장한다.
						else if (item.getFieldName().equals("location"))
							location = value;
						// key 값이 pw이면 pw 변수에 값을 저장한다.
						else if (item.getFieldName().equals("gender"))
							gender = value;
						// key 값이 pw이면 pw 변수에 값을 저장한다.
						else if (item.getFieldName().equals("weight"))
							weight = value;
						// key 값이 pw이면 pw 변수에 값을 저장한다.
						else if (item.getFieldName().equals("etc"))
							etc = value;
						// key 값이 pw이면 pw 변수에 값을 저장한다.
					} else {// 파일이라면...
						if (item.getFieldName().equals("image")) {
							//key 값이 picture이면 파일 저장을 한다.
                			filename = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//파일이 전송되어 오지 않았다면 건너 뛴다.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
                			File file = new File(dir, filename);
                			item.write(file);
                			//파일을 upload 경로에 실제로 저장한다.
                			//FileItem 객체를 통해 바로 출력 저장할 수 있다.
						}
					}
				}
			} catch (SizeLimitExceededException e) {
				// 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();
			} catch (FileUploadException e) {
				// 파일 업로드와 관련되어 발생할 수 있는 예외 처리
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("@@@@@@@@@@@@Create Animal :---------------------" + species);
		Animal animal = new Animal(Integer.parseInt(species), Integer.parseInt(age), location, filename, gender, weight,
				etc, 0);
		System.out.println("Create Animal :---------------------" + animal.getAge());
	
		
		log.debug("Create Animal : {}", animal);
		int animal_id = 0;
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		try {
			AnimalManager manager = AnimalManager.getInstance();
			animal_id = manager.create(animal);
			//동물 id받아온거로 여기서 하트 객체 추기
			System.out.println("-------+id"+ animal_id+user_id);
			Heart heart = new Heart(animal_id, user_id);
			System.out.println("111111111Create Heart :-------+id"+ heart.getUser_id()+"---------------" + heart);
			HeartManager h_manager = HeartManager.getInstance();
			h_manager.create(heart);
			System.out.println("22222222222h_manager.cCreate Heart :--------------------" + heart);
			
			
			
			animal = manager.findAnimal(animal_id);
			
			request.setAttribute("animal", animal);
			request.setAttribute("dir", dir);
			request.setAttribute("filename", filename);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + animal);
			return "/animal/view.jsp";

		} catch (ExistingUserException e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("animal", animal);
			return "/animal/registerForm.jsp";
		} catch (AnimalNotFoundException e) {
			return "redirect:/animal/list";
		}

	}
}
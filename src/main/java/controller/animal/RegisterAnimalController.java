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
import model.Animal;
import model.service.ExistingUserException;
import model.service.AnimalManager;

//���� ���ε带 ���� API�� ����ϱ� ����...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

//���� �뷮 �ʰ��� ���� Exception Ŭ������ FileUploadBase Ŭ������ Inner Ŭ������ ó��
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
    		// GET request: ȸ������ ��� form ��û	
    		log.debug("ApplyForm Request");
			return "/animal/registerForm.jsp";   
	    }
    	
    	boolean check = ServletFileUpload.isMultipartContent(request);
		//���۵� �������� ���ڵ� Ÿ���� multipart ���� ���θ� üũ�Ѵ�.
		//���� multipart�� �ƴ϶�� ���� ������ ó������ �ʴ´�.
		
		if(check) {//���� ������ ���Ե� ���°� �´ٸ�
			
			// �Ʒ��� ���� �ϸ� Tomcat ���ο� ����� ������Ʈ�� ���� �ؿ� upload ������ ������ 
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			dir = new File(path);
			
			// Tomcat �ܺ��� ������ �����Ϸ��� �Ʒ��� ���� ���� ��η� ���� �̸��� ������
			// File dir = new File("C:/Temp");
			
			if(!dir.exists()) dir.mkdir();
			//���۵� ������ ������ ���� ��θ� �����.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //���� ���ۿ� ���� �⺻���� ���� Factory Ŭ������ �����Ѵ�.
                factory.setSizeThreshold(10 * 1024);
                //�޸𸮿� �ѹ��� ������ �������� ũ�⸦ �����Ѵ�.
                //10kb �� �޸𸮿� �����͸� �о� ���δ�.
                factory.setRepository(dir);
                //���۵� �������� ������ ������ �ӽ� ������ �����Ѵ�.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory Ŭ������ ���� ���� ���ε� �Ǵ� ������ ó���� ��ü�� �����Ѵ�.
                upload.setSizeMax(10 * 1024 * 1024);
                //���ε� �� ������ �ִ� �뷮�� 10MB���� ���� ����Ѵ�.
                upload.setHeaderEncoding("utf-8");
                //���ε� �Ǵ� ������ ���ڵ��� �����Ѵ�.
                                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
                //upload ��ü�� ���۵Ǿ� �� ��� �����͸� Collection ��ü�� ��´�.
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	System.out.println(item);
                	//commons-fileupload�� ����Ͽ� ���۹����� 
                	//��� parameter�� FileItem Ŭ������ �ϳ��� ����ȴ�.
                	
                	String value = item.getString("utf-8");
                	//�Ѿ�� ���� ���� �ѱ� ó���� �Ѵ�.
//                	System.out.println("@@@@@@@@@@@@Create Animal :---------------------"+value);
                	if(item.isFormField()) {//�Ϲ� �� �����Ͷ��...                		
                		if(item.getFieldName().equals("species")) species = value;
                		//key ���� name�̸� name ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("age")) age = value;
                		//key ���� id�̸� id ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("location")) location = value;
                		//key ���� pw�̸� pw ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("gender")) gender = value;
                		//key ���� pw�̸� pw ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("weight")) weight = value;
                		//key ���� pw�̸� pw ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("etc")) etc = value;
                		//key ���� pw�̸� pw ������ ���� �����Ѵ�.
                	}
                	else {//�����̶��...
                		if(item.getFieldName().equals("image")) {
                		//key ���� picture�̸� ���� ������ �Ѵ�.
                			filename = item.getName();//���� �̸� ȹ�� (�ڵ� �ѱ� ó�� ��)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//������ ���۵Ǿ� ���� �ʾҴٸ� �ǳ� �ڴ�.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//���� �̸��� ������ ��ü ��α��� �����ϱ� ������ �̸� �κи� �����ؾ� �Ѵ�.
                			//���� C:\Web_Java\aaa.gif��� �ϸ� aaa.gif�� �����ϱ� ���� �ڵ��̴�.
                			File file = new File(dir, filename);
                			item.write(file);
                			
                			//������ upload ��ο� ������ �����Ѵ�.
                			//FileItem ��ü�� ���� �ٷ� ��� ������ �� �ִ�.
         
//                			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ dir);
                		}
                	}
                }
			}catch(SizeLimitExceededException e) {
			//���ε� �Ǵ� ������ ũ�Ⱑ ������ �ִ� ũ�⸦ �ʰ��� �� �߻��ϴ� ����ó��
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //���� ���ε�� ���õǾ� �߻��� �� �ִ� ���� ó��
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
		}		
		System.out.println("@@@@@@@@@@@@Create Animal :---------------------"+species);
    	Animal animal = new Animal(
    			Integer.parseInt(species),
    			Integer.parseInt(age),location,filename,gender,weight,etc,
    			0
    			);
    	System.out.println("Create Animal :---------------------"+animal);
    	log.debug("Create Animal : {}", animal);
    	try {
    		AnimalManager manager = AnimalManager.getInstance();
    		int animal_id = manager.create(animal);

    		animal = manager.findAnimal(animal_id);
    		request.setAttribute("dir", dir);
    		request.setAttribute("filename", filename);
    		request.setAttribute("animal", animal);		// ���⵿�� ���� ����	
    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ dir);

    		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+animal);
    		return "/animal/view.jsp";	
    		
    	} catch (ExistingUserException e) {	
    		request.setAttribute("registerFailed", true);
    		request.setAttribute("exception", e);
    		request.setAttribute("animal", animal);
    		return "/animal/registerForm.jsp";
    	}
    }
}

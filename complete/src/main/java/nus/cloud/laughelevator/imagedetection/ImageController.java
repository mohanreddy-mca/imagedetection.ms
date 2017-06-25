package nus.cloud.laughelevator.imagedetection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nus.cloud.laughelevator.imagedetection.storage.SaveImageCloudStorage;


@RestController
@RequestMapping("ms/imagedetection")
public class ImageController {
    
    private OutputStream outputStream;
    
    private SaveImageCloudStorage saveImageCloudStorage;

	@RequestMapping("/saveimage")
    public String index() {
        return "Greetings from Spring Boot! and this ms is for Receive Face Detection Images from UI and saves into cloud.";
    }
    
    @RequestMapping(value="/saveimage", method=RequestMethod.POST, 
    		consumes = {MediaType.ALL_VALUE},
    		produces = {MediaType.IMAGE_JPEG_VALUE})
    public void saveimage( HttpServletRequest req, HttpServletResponse res) {
    	
    	System.out.println("========>"+req.getContentLength()+"===="+req.getContentType());
    	  try {
    		  InputStream inputStream = req.getInputStream();
    		  outputStream = new FileOutputStream(new File("C:\\temp\\mohan1.jpeg"));
    		  
    		  int read = 0;
    			byte[] bytes = new byte[1024];

    			/*while ((read = inputStream.read(bytes)) != -1) {
    				outputStream.write(bytes, 0, read);
    			}*/
    			
    			saveImageCloudStorage.saveImage(bytes);

    			System.out.println("Done!");
    			
    			// Send Response
    			sendImageResponse(req,res);
    			

    	  } catch (Exception e) 
    	  { e.printStackTrace();}
    	  
    	  
    	
        //return "Greetings from Spring Boot! and this ms is for Receive Face Detection Images from UI and saves into cloud.";
    }
    
    private void sendImageResponse(HttpServletRequest req, HttpServletResponse resp){

    	try{
    		
    		File file = new File("C:\\temp\\mohan1.jpeg");
    		resp.setContentType(MediaType.IMAGE_JPEG_VALUE);
    		resp.setContentLength((int)file.length());

    		FileInputStream in = new FileInputStream(file);
    		OutputStream out = resp.getOutputStream();

    		// Copy the contents of the file to the output stream
    		byte[] buf = new byte[1024];
    		int count = 0;
    		while ((count = in.read(buf)) >= 0) {
    			out.write(buf, 0, count);
    		}
    		out.close();
    		in.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
}

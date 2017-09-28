package facedetection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FaceDetection
 */
@WebServlet("/FaceDetection")
@MultipartConfig
public class FaceDetection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		String description = request.getParameter("description");
		
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		
		InputStream fileContent = filePart.getInputStream();
		
		File uploads = new File("/Users/YanXi/Desktop/test/" + description + "_" + fileName);
		
		try {
			Files.copy(fileContent, uploads.toPath());
		} catch (IOException e) {
			
		}
		
		String inImg = uploads.getAbsolutePath();
		
		Smoother.smooth(inImg);
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><body>");
		out.println("<h2>" + fileName + "<br>" + description + "<br>");
		out.println("</body></html>");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

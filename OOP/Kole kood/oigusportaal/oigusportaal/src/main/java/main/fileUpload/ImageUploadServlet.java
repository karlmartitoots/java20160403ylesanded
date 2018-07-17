package main.fileUpload;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

import javax.servlet.*;
import javax.servlet.http.*;

import main.search.DBConnection;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class ImageUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8593560099522002883L;

	private String imageName = "";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		String absolutePath = "/var/lib/oigusportaal/";
		String contextPath = "/oigusportaal/photos/";

		// System.out.println("Context Path = " + contextPath);

		int action = 0;
		int generalId = 0;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		boolean isThere = false;
		String[] extensions = {};
		String[] extensionsImage = { ".png", ".jpg", ".jpeg", ".gif" };
		String[] extensionsDocument = { ".odt", ".doc", ".pdf", ".docx" };
		// System.out.println("request: " + request);
		if (!isMultipart) {
			System.out.println("File Not Uploaded");
		} else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			@SuppressWarnings("rawtypes")
			List items = null;

			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException ex) {
				Logger.getLogger(ImageUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println("items: " + items);
			
			@SuppressWarnings("rawtypes")
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					System.out.println("name: " + name);
					String value = item.getString();
					System.out.println("value: " + value);
					if (name.equals("generalId")) {
						generalId = Integer.parseInt(value);
						// System.out.println("Generaalne id: " + generalId);
					}					
					if (name.equals("action")) {
						action = Integer.parseInt(value);
						System.out.println("Action is: " + action);
					}
				}
			}
			@SuppressWarnings("rawtypes")
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					
				}
				else {
					if (action == 1 || action == 2) {
						extensions = extensionsImage;
					} else {
						extensions = extensionsDocument;
					}
					try {
						String itemName = item.getName();
						Random generator = new Random();
						int r = Math.abs(generator.nextInt());

						String reg = "[.*]";
						String replacingtext = "";
						System.out.println("Text before replacing is:-" + itemName);
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();

						while (matcher.find()) {
							matcher.appendReplacement(buffer, replacingtext);
						}
						int IndexOf = itemName.indexOf(".");
						String domainName = itemName.substring(IndexOf);
						System.out.println("domainName: " + domainName);

						
						for (int i = 0; i < extensions.length; i++) {
							System.out.println(extensions[i]);
							if (domainName.equals(extensions[i])) {
								isThere = true;
							}
						}
						if (isThere) {
							String finalimage = buffer.toString() + "_" + r + domainName;
							imageName = finalimage;
							System.out.println("Final Image===" + finalimage);

							File savedFile = new File(absolutePath + finalimage);
							item.write(savedFile);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (action == 2) {
						request.setAttribute("attorneyId", generalId);
					}
					if (action == 3) {
						request.setAttribute("storyId", generalId);
					}
					if (isThere) {
						DBConnection conn = new DBConnection();
						Connection curConnection = null;
						PreparedStatement smt = null;
						curConnection = conn.getConnection();
						String finalPath = contextPath + imageName;
						// System.out.println("Final path: " + finalPath);					

						try {
							String sql = "";
							if (action == 1) {
								sql = "UPDATE bureau SET image='" + finalPath + "' WHERE bureauid='" + generalId + "';";
							} else if (action == 2) {
								sql = "UPDATE attorney SET imgpath='" + finalPath + "' WHERE attorneyid='" + generalId + "';";
							} else if (action == 3) {
								sql = "UPDATE successstory SET filepath='" + finalPath + "' WHERE ssid='" + generalId + "';";
							}
							System.out.println(finalPath);
							smt = curConnection.prepareStatement(sql);
							smt.executeUpdate();
							smt.close();
							curConnection.close();

							if (action == 1) {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/BureauProfileServlet");
								rd.forward(request, response);
							} else if (action == 2) {
								// System.out.println("Yupp");
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/LawyerProfileServlet");
								rd.forward(request, response);
							} else if (action == 3) {
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/StoryProfileServlet");
								rd.forward(request, response);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					else{
						if (action == 1) {
							request.setAttribute("fail", true);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/BureauProfileServlet");
							rd.forward(request, response);
						} else if (action == 2) {
							request.setAttribute("fail", true);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/LawyerProfileServlet");
							rd.forward(request, response);
						} else if (action == 3) {
							request.setAttribute("fail", true);
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/StoryProfileServlet");
							rd.forward(request, response);
						}
					}
				}

			}
		}

	}

}
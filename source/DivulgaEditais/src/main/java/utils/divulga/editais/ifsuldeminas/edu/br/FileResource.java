package utils.divulga.editais.ifsuldeminas.edu.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import services.divulga.editais.ifsuldeminas.edu.br.NoticeService;

@Path("/file")
public class FileResource extends HttpServlet {
	
	@Context
    private HttpServletRequest request;	

	private static final long serialVersionUID = 1L;
	private static String _filesPath = "context.path";

	@GET
	@Produces("application/pdf")
	@Path("/download/{folderName}/{fileName}")
	public Response downloadFile(
			@PathParam("folderName") String folderName, 
			@PathParam("fileName")String fileName) {
		String path = getFilesPath() + File.separatorChar + folderName + File.separatorChar + fileName;
		File file = null;
		try {
			file = new File(path);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(path).build();
		}
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		return response.build();
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFile(
			@FormDataParam("notice") FormDataBodyPart jsonPart,
			@FormDataParam("uploadfile") InputStream uploadedInputStream,
			@FormDataParam("uploadfile") FormDataContentDisposition fileDetail) {
		try {
			jsonPart.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		    Notice notice = jsonPart.getValueAs(Notice.class);
		    NoticeService service = new NoticeService(request);
		    service.create(notice);
		    
		    int fileSize = saveFile(notice.getNoticeId(), uploadedInputStream, fileDetail);
			
			return Response.ok(Integer.toString(fileSize), MediaType.TEXT_PLAIN).build();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
	}
	
	private int saveFile(int noticeId, InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
		String fileName = fileDetail.getFileName();
		String path = createDirectoryStructure(noticeId);
		String fullPath = path + fileName;
		int fileSize;
		try {
			OutputStream outStream = new FileOutputStream(fullPath);
			fileSize = IOUtils.copy(uploadedInputStream, outStream);
			fileSize = fileSize / 1024;
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
		return fileSize;
	}

	private String createDirectoryStructure(int parameter) {
		String path = getFilesPath() + File.separatorChar + String.valueOf(parameter) + File.separatorChar;
		File targetDir = new File(path);

		if (!targetDir.exists()) {
			System.out.println("Criando Estrutura de Pastas: " + targetDir.getPath());
			boolean result = false;

			try {
				targetDir.mkdirs();
				result = true;
			} catch (SecurityException se) {
				throw se;
			}
			if (result) {
				System.out.println("Estrutura de Pastas Criada!");
			}
		}
		return path;
	}
	
	private String getFilesPath() {
		return PropsUtil.getInstance().getProperty(_filesPath);
	}
}

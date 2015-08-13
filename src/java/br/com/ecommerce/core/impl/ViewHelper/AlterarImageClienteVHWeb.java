/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Felipe Monteiro
 */
@MultipartConfig
        (
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 4,   // 4MB
        maxRequestSize = 1024 * 1024 * 4 // 4MB
)
public class AlterarImageClienteVHWeb extends HttpServlet implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request)
    {       
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
         boolean isMultipart = ServletFileUpload.isMultipartContent(request);
         
         Cliente cliente = (Cliente) request.getSession().getAttribute("user");
         
         String arquivo = cliente.getNome()+".jpg";
         
         if(isMultipart)
         {
             ServletFileUpload upload = new ServletFileUpload();
             
             try
             {
                 FileItemIterator iter = upload.getItemIterator(request);
                 
                 while(iter.hasNext())
                 {
                     FileItemStream item = iter.next();
                     
                     InputStream input = item.openStream();
                     
                     File file = new File("imagens","img.jpg");
                     
                     System.out.println(file.getAbsolutePath());
                     
                     FileOutputStream out = new FileOutputStream(file);
                     
                     byte[] buffer = new byte[2048];
                     int nlidos;
                     
                     while((nlidos = input.read(buffer)) >= 0)
                     {
                         out.write(buffer,0,nlidos);
                     }
                     
                     out.flush();
                     out.close();
                 }
              
                 request.getRequestDispatcher("/../../index.jsp").forward(request, response);
              
             } catch (FileUploadException ex)
             {
                 ex.printStackTrace();
             }
         }
    }
}

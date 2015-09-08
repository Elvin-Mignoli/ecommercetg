/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IViewHelper;
import br.com.ecommerce.core.impl.dao.AutenticarDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload; */

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
        /* 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
         
         Usuario usuario = (Usuario) request.getSession().getAttribute("user");
         
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
         
         String data = sdf.format(Calendar.getInstance().getTime());
         
         String arquivo = "";
         
         String path = "C:\\Users\\java\\Documents\\NetBeansProjects\\ecomercetg\\web\\imagens\\cliente";
         
         if(isMultipart)
         {
             ServletFileUpload upload = new ServletFileUpload();
             
             try
             {
                 FileItemIterator iter = upload.getItemIterator(request);
                 
                 while(iter.hasNext())
                 {
                     FileItemStream item = iter.next();
                     
                     arquivo = data+"_"+item.getName();
                     
                     InputStream input = item.openStream();
                     
                     FileOutputStream out = new FileOutputStream(new File(path,arquivo));
                     
                     byte[] buffer = new byte[2048];
                     int nlidos;
                     
                     while((nlidos = input.read(buffer)) >= 0)
                     {
                         out.write(buffer,0,nlidos);
                     }
                     
                     out.flush();
                     out.close();
                 }
                 
                 path = "http://localhost:8080/Ecommerce/imagens/cliente/";
                 usuario.setImagem(path+arquivo);
                 
                 //grava o caminho do arquivo no banco de dados!
                 AutenticarDAO dao = new AutenticarDAO();
                 
                 dao.AtualizaImagem(usuario);
                 
                 request.getSession().setAttribute("user", usuario);
                 
                 request.setAttribute("MsgAtualiza", "Imagem alterada com sucesso!");              
             } catch (FileUploadException ex)
             {
                 ex.printStackTrace();
                 request.setAttribute("MsgAtualiza", "Falha ao fazer o upload da imagem =/");
             }
             catch(SQLException ex)
             {
                 ex.printStackTrace();
                 request.setAttribute("MsgAtualiza", "Erro ao persistir a imagem");
             }
             finally
             {
                request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
             }
         } */
    }
}

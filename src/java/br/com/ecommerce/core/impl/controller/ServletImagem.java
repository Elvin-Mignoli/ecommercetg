/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Felipe Monteiro
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 4,   // 4MB
        maxRequestSize = 1024 * 1024 * 4 // 4MB
)
public class ServletImagem extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Collection<Part> p = request.getParts();
        
        for(Part part : request.getParts())
        {
            System.out.println(part.getName());
            System.out.println(part.getSize());
            System.out.println(part.getInputStream());
        }
        
        request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
    }
}

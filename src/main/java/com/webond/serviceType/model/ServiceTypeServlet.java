package com.webond.serviceType.model;

import java.io.IOException;
import java.util.List;

import com.webond.serviceType.model.ServiceTypeService;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servicetype/servicetype.do")
public class ServiceTypeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("getAll".equals(action)) {
            ServiceTypeService serviceTypeSvc = new ServiceTypeService();
            List<ServiceTypeVO> list = serviceTypeSvc.getAll();

            req.setAttribute("serviceTypeList", list);

            RequestDispatcher successView =
                    req.getRequestDispatcher("/servicetype/listAllServiceType.jsp");
            successView.forward(req, res);
        }

        if ("getOne_For_Display".equals(action)) {
            Integer serviceTypeId = Integer.valueOf(req.getParameter("serviceTypeId"));

            ServiceTypeService serviceTypeSvc = new ServiceTypeService();
            ServiceTypeVO serviceTypeVO = serviceTypeSvc.getOneServiceType(serviceTypeId);

            req.setAttribute("serviceTypeVO", serviceTypeVO);

            RequestDispatcher successView =
                    req.getRequestDispatcher("/servicetype/listOneServiceType.jsp");
            successView.forward(req, res);
        }
        
        if("insert".equals(action)) {
        	String typeName = req.getParameter("typeName");
        	String description = req.getParameter("description");
        	Integer typeMode = Integer.valueOf(req.getParameter("typeMode"));
        	String imgURL = req.getParameter("imgURL");
        	
        	ServiceTypeService serTypeSvc = new ServiceTypeService();
            ServiceTypeVO serviceTypeVO = serTypeSvc.addServiceType(
                    typeName,
                    description,
                    typeMode,
                    imgURL
            );
            //測試用
            System.out.println("新增後的 serviceTypeId = " + serviceTypeVO.getServiceTypeId());
            req.setAttribute("serviceTypeVO", serviceTypeVO);

            RequestDispatcher successView =
                    req.getRequestDispatcher("/servicetype/listOneServiceType.jsp");
            successView.forward(req, res);
            return;
        }
        
        if ("getOne_For_Update".equals(action)) {
            Integer serviceTypeId = Integer.valueOf(req.getParameter("serviceTypeId"));
            
            ServiceTypeService serviceTypeSvc = new ServiceTypeService();
            ServiceTypeVO serviceTypeVO = serviceTypeSvc.getOneServiceType(serviceTypeId);

            req.setAttribute("serviceTypeVO", serviceTypeVO);

            RequestDispatcher successView =
                    req.getRequestDispatcher("/servicetype/update_service_type_input.jsp");
            successView.forward(req, res);
            return;
        }
        if ("delete".equals(action)) {
            Integer serviceTypeId = Integer.valueOf(req.getParameter("serviceTypeId"));

            ServiceTypeService serviceTypeSvc = new ServiceTypeService();
            serviceTypeSvc.deleteServiceType(serviceTypeId);

            List<ServiceTypeVO> list = serviceTypeSvc.getAll();
            req.setAttribute("serviceTypeList", list);

            RequestDispatcher successView =
                    req.getRequestDispatcher("/servicetype/listAllServiceType.jsp");
            successView.forward(req, res);
            return;
        }
        
        if ("update".equals(action)) {
            Integer serviceTypeId = Integer.valueOf(req.getParameter("serviceTypeId"));
            String typeName = req.getParameter("typeName");
            String description = req.getParameter("description");
            Integer typeMode = Integer.valueOf(req.getParameter("typeMode"));
            String imgURL = req.getParameter("imgURL");

            ServiceTypeService serviceTypeSvc = new ServiceTypeService();

            ServiceTypeVO serviceTypeVO = serviceTypeSvc.updateServiceType(
                    serviceTypeId,
                    typeName,
                    description,
                    typeMode,
                    imgURL
            );

            req.setAttribute("serviceTypeVO", serviceTypeVO);

            RequestDispatcher successView =
                    req.getRequestDispatcher("/servicetype/listOneServiceType.jsp");
            successView.forward(req, res);
            return;
        }
    }
}
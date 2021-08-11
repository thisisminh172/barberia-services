package com.barberia.app.services;

import com.barberia.app.dto.PaymentReportDto;
import com.barberia.app.models.Payment;
import com.barberia.app.repositories.PaymentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private PaymentRepository paymentRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\project_4\\barberia-services\\reports";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentReportDto> paymentReportDtos = new ArrayList<PaymentReportDto>();
        for(int i = 0; i< payments.size();i++){

            PaymentReportDto newPaymentReport = new PaymentReportDto();
            newPaymentReport.setId(payments.get(i).getId());
            newPaymentReport.setEmployeeName(payments.get(i).getTurn().getEmployee().getFirstName());
            newPaymentReport.setDatetime(payments.get(i).getTurn().getBooking().getChosenTimeSlot().format(formatter));
            String method = payments.get(i).getPaymentMethod()=="cash"?"CASH":"MOMO wallet";
            newPaymentReport.setPaymentMethod(method);
            String totalPrice = String.format("%,d",(int) payments.get(i).getTotalPrice()) +" vnđ";
            newPaymentReport.setTotalPrice(totalPrice);
            paymentReportDtos.add(newPaymentReport);
        }
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:paymentlist.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(paymentReportDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Lê Minh");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\paymentlist.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\paymentlist.pdf");
        }
        return "Report generated in path: "+path;
    }
}

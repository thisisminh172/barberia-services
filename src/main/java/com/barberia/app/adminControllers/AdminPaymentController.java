package com.barberia.app.adminControllers;

import com.barberia.app.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class AdminPaymentController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/admin/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportService.exportReport(format);
    }
}

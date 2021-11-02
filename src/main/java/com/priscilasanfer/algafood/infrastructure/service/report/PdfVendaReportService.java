package com.priscilasanfer.algafood.infrastructure.service.report;

import com.priscilasanfer.algafood.domain.filter.VendaDiariaFilter;
import com.priscilasanfer.algafood.domain.service.VendaQueryService;
import com.priscilasanfer.algafood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

//    @Override
//    public byte[] emitirVendasDiarias(VendasDiariasFilter filter, String timeOffset) {
//        try {
//            var inputStream = this.getClass().getResourceAsStream(
//                    "/relatorios/vendasdiarias.jasper");
//
//            var parametros = new HashMap<String, Object>();
//            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
//
//            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filter, timeOffset);
//            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
//
//            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
//
//            return JasperExportManager.exportReportToPdf(jasperPrint);
//        } catch (Exception e) {
//            throw new ReportException("Não foi possivel emitir relatório de vendas", e);
//        }
//    }

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream(
                    "/relatorios/vendasdiarias.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
        }
    }
}

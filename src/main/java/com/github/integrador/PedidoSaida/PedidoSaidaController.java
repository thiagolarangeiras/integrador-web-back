package com.github.integrador.PedidoSaida;

import com.github.integrador.PedidoSaida.pdf.PdfPedidoDados;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pedido-saida")
public class PedidoSaidaController {
    @Autowired private PedidoSaidaService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoSaidaGetDto> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PedidoSaidaGetDto>> getAll(@RequestParam Integer page, @RequestParam Integer count) {
        return ResponseEntity.ok(service.getAll(page, count));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody PedidoSaidaPostDto dto) {
        return ResponseEntity.ok(service.post(dto));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoSaidaGetDto> patch(@PathVariable("id") Integer id, @RequestBody PedidoSaidaPostDto dto) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoSaidaGetDto> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void gerarPdf(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"pedido.pdf\"");
        var doc = new Document(PageSize.A4, 20f, 20f, 20f, 20f);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        PdfPedidoDados dados = service.getDadosPdf(id);
        service.documento1(doc, dados);
        doc.close();
    }
}

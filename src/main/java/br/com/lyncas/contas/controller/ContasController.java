package br.com.lyncas.contas.controller;

import br.com.lyncas.contas.domain.dto.ContasDTO;
import br.com.lyncas.contas.domain.entity.Contas;
import br.com.lyncas.contas.exception.ResourceNotFoundException;
import br.com.lyncas.contas.service.GenericService;
import br.com.lyncas.contas.service.impl.ContasServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/contas")
@AllArgsConstructor
public class ContasController {

    private GenericService<Contas> service;

    @GetMapping("/{dataVencimento}/descricao/{descricao}")
    public ResponseEntity<Page<Contas>> getAll(@PathVariable(value = "dataVencimento") Date dataVencimento,
                                               @PathVariable(value = "descricao") String descricao,
                                               Pageable pageable){
        Page<Contas> contas = service.getContas(dataVencimento,descricao,pageable);
        if(contas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contas,HttpStatus.OK);
    }

    @GetMapping("/dataVencimentoInicio/{dataVencimentoInicio}/dataVencimentoFinal/{dataVencimentoFinal}")
    public ResponseEntity<Page<ContasDTO>> getValorTotalPorPeriodo(@PathVariable(value = "dataVencimentoInicio") Date dataVencimentoIncial,
                                                  @PathVariable(value = "dataVencimentoFinal") Date dataVencimentoFinal,
                                                  Pageable pageable) {
        Page<ContasDTO> contas = service.getValorTotalPorPeriodo(dataVencimentoIncial,dataVencimentoFinal,pageable);
        if(contas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contas,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contas> getObject(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Contas contas = service.getId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + id));
        return new ResponseEntity<>(contas,HttpStatus.OK);
    }

    @PostMapping(value="/")
    public ResponseEntity<Contas> create(@RequestBody Contas contas){
        service.save(contas);
        return new ResponseEntity<Contas>(HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Contas> update(@PathVariable("id") Integer id,@RequestBody Contas contas)throws ResourceNotFoundException{
        Contas item = service.getId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contas não encontrado: " + contas.getId()));
        item = service.save(contas);
        return new ResponseEntity<Contas>(item,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Contas> delete(@PathVariable("id") Integer id)throws ResourceNotFoundException{
        Contas contas = service.getId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + id));

        service.remove(contas);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
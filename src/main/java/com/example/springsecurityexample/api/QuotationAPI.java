package com.example.springsecurityexample.api;

import com.example.springsecurityexample.model.Quotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuotationAPI {

    private List<Quotation> quotations;

    public QuotationAPI() {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("To, że milczę nie znaczy, że nie mam nic do powiedzenia.","Jonathan Carroll"));
        quotations.add(new Quotation("Lepiej zaliczać się do niektórych, niż do wszystkich.","Andrzej Sapkowski"));
    }

    @GetMapping("/api")
    public ResponseEntity<List<Quotation>> getQuotation(){
        return ResponseEntity.ok(quotations);
    }

    @PostMapping("/api")
    public ResponseEntity<List<Quotation>> addQuotation(@RequestBody Quotation quotation){
        return new ResponseEntity(quotations.add(quotation),HttpStatus.CREATED);
    }

    @DeleteMapping("/api")
    public ResponseEntity deleteQuotation(@RequestParam int index){
        Optional<Quotation> quotation = Optional.ofNullable(quotations.get(index));
        if(quotation.isPresent()){
            quotations.remove(index);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

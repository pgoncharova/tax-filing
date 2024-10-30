package com.pgoncharova.taxfiling.taxpayer;

import com.pgoncharova.taxfiling.system.Result;
import com.pgoncharova.taxfiling.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/taxpayers")
public class TaxpayerController {

    private final TaxpayerService taxpayerService;

    private final TaxpayerDtoToTaxpayerConverter taxpayerDtoToTaxpayerConverter;

    private final TaxpayerToTaxpayerDtoConverter taxpayerToTaxpayerDtoConverter;

    @Autowired
    public TaxpayerController(TaxpayerService taxpayerService,
                              TaxpayerDtoToTaxpayerConverter taxpayerDtoToTaxpayerConverter,
                              TaxpayerToTaxpayerDtoConverter taxpayerToTaxpayerDtoConverter) {
        this.taxpayerService = taxpayerService;
        this.taxpayerDtoToTaxpayerConverter = taxpayerDtoToTaxpayerConverter;
        this.taxpayerToTaxpayerDtoConverter = taxpayerToTaxpayerDtoConverter;
    }

    @PostMapping
    public Result addTaxpayer(@Valid @RequestBody Taxpayer newTaxpayer) {
        Taxpayer savedTaxpayer = this.taxpayerService.save(newTaxpayer);
        TaxpayerDto savedTaxpayerDto = this.taxpayerToTaxpayerDtoConverter.convert(savedTaxpayer);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedTaxpayerDto);
    }

    @GetMapping
    public Result findAllTaxpayers() {
        List<Taxpayer> foundTaxpayers = this.taxpayerService.findAll();

        List<TaxpayerDto> taxpayerDtos = foundTaxpayers.stream()
                .map(this.taxpayerToTaxpayerDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "Find All Success", taxpayerDtos);
    }

    @GetMapping("/search")
    public Result searchTaxpayers(@RequestParam(required = false) String ssn,
                                  @RequestParam(required = false) String lastName) {
        List<Taxpayer> foundTaxpayers = this.taxpayerService.findByFilters(ssn, lastName);

        List<TaxpayerDto> taxpayerDtos = foundTaxpayers.stream()
                .map(taxpayerToTaxpayerDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "Search Success", taxpayerDtos);
    }

    @GetMapping("/{taxpayerId}")
    public Result findTaxpayerById(@PathVariable Long taxpayerId) {
        Taxpayer foundTaxpayer = this.taxpayerService.findById(taxpayerId);
        TaxpayerDto taxpayerDto = this.taxpayerToTaxpayerDtoConverter.convert(foundTaxpayer);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", taxpayerDto);
    }

    // TODO: This is not used to update password. Need another changePassword method in this class.
    @PutMapping("/{taxpayerId}")
    public Result updateTaxpayer(@PathVariable Long taxpayerId, @Valid @RequestBody TaxpayerDto taxpayerDto) {
        Taxpayer update = this.taxpayerDtoToTaxpayerConverter.convert(taxpayerDto);
        Taxpayer updatedTaxpayer = this.taxpayerService.update(taxpayerId, update);
        TaxpayerDto updatedTaxpayerDto = this.taxpayerToTaxpayerDtoConverter.convert(updatedTaxpayer);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedTaxpayerDto);
    }

    @DeleteMapping("/{taxpayerId}")
    public Result deleteTaxpayer(@PathVariable Long taxpayerId) {
        this.taxpayerService.delete(taxpayerId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}

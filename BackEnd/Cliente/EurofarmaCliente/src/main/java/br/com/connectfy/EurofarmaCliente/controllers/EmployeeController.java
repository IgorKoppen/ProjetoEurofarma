package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.ChangePasswordDTO;
import br.com.connectfy.EurofarmaCliente.dtos.PhoneNumberUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeInsertDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUpdateDTO;
import br.com.connectfy.EurofarmaCliente.dtos.employee.EmployeeUserProfileInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.training.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.services.EmployeeInsertService;
import br.com.connectfy.EurofarmaCliente.services.EmployeeService;
import br.com.connectfy.EurofarmaCliente.specification.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eurofarma/employee")
@Tag(name = "Employee", description = "Controller Employee")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeInsertService employeeInsertService;


    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Cria um funcionário", description = "Cria um novo funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)

            })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeInfoDTO> insert(@RequestBody @Valid EmployeeInsertDTO dto) {
        EmployeeInfoDTO employeeDTO = employeeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(employeeDTO);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Consulta um funcionário", description = "Retorna um funcionário a partir de um id",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeInfoDTO> findById(@PathVariable Long id) {
        EmployeeInfoDTO dto = employeeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Retorna informações parciais de funcionário", description = "Retorna informações paciais de um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @GetMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeUserProfileInfoDTO> findEmployeeUserProfileInfo(@PathVariable Long id) {
        EmployeeUserProfileInfoDTO employeeUserInfo = employeeService.findEmployeeUserProfileInfoById(id);
        return ResponseEntity.ok(employeeUserInfo);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Consulta funcionários com paginação", description = "Retorna funcionários com paginação",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content)
            })
    @GetMapping(value = "/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<EmployeeInfoDTO>>> findWithPagination(@PageableDefault(size = 10, direction = Sort.Direction.ASC, sort = "name") Pageable pageable,@Parameter(hidden = true) PagedResourcesAssembler<EmployeeInfoDTO> assembler) {
        Page<EmployeeInfoDTO> employeePage = employeeService.findWithPagination(pageable);
        PagedModel<EntityModel<EmployeeInfoDTO>> pagedModel = assembler.toModel(employeePage);
        return ResponseEntity.ok(pagedModel);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Atualiza um funcionário", description = "Atualiza um funcionário a partir de um id",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeInfoDTO> update(@PathVariable Long id,
                                                  @RequestBody @Valid EmployeeUpdateDTO employeeDTO) {
       EmployeeInfoDTO employee = employeeService.update(id,employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Exclui um funcionário", description = "Exclui um funcionário a partir de um id",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Atualiza a senha de um funcionário", description = "Atualiza a senha de um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PatchMapping(value = "/updatePassword/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
       employeeService.updatePassword(id,changePasswordDTO);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyAuthority('admin','treinador','funcionario')")
    @Operation(summary = "Atualiza o telefone de um funcionário", description = "Atualiza o número de telefone de um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)
            })
    @PatchMapping(value = "/changePhone/{id}", produces ="application/json")
    public ResponseEntity<Void> updatePhone(@PathVariable Long id,@RequestBody @Valid PhoneNumberUpdateDTO updateDTO) {
      employeeService.updateCellphoneNumber(id, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Desabilita um funcionário", description = "Desabilita um funcionário",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
            })
    @PatchMapping(value = "/disable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> disable(@PathVariable Long id) {
       employeeService.toggleEmployeeStatus(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Realiza uma busca avançada de funcionários",
            description = "Retorna uma lista paginada de funcionários com base em vários " +
                    " parâmetros opcionais de busca, como nome, sobrenome, registro de funcionário, status de ativação, função, permissão e departamento.",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            })
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<EmployeeInfoDTO>>> searchEmployee(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "employeeRegistration", required = false) Long employeeRegistration,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "roleId", required = false) Long roleId,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "permissionId", required = false) String permissionId,
            @RequestParam(value = "permissionDescription", required = false) String permissionDescription,
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "departmentName", required = false) String departmentName,

            @PageableDefault(direction = Sort.Direction.ASC)
            Pageable pageable,
            @Parameter(hidden = true)   PagedResourcesAssembler<EmployeeInfoDTO> assembler) {

        List<SearchCriteria> params = new ArrayList<>();


        if (name != null) {
            params.add(new SearchCriteria("name", ":", name));
        }
        if (surname != null) {
            params.add(new SearchCriteria("surname", ":", surname));
        }
        if (employeeRegistration != null) {
            params.add(new SearchCriteria("employeeRegistration", ":", employeeRegistration));
        }
        if (enabled != null) {
            params.add(new SearchCriteria("enabled", ":", enabled));
        }
        if (roleId != null) {
            params.add(new SearchCriteria("roleId", "=", roleId));
        }
        if (roleName != null) {
            params.add(new SearchCriteria("roleName", "=", roleName));
        }
        if (permissionId != null) {
            params.add(new SearchCriteria("permissionId", "=", permissionId));
        }
        if (permissionDescription != null) {
            params.add(new SearchCriteria("permissionDescription", "=", permissionDescription));
        }
        if (departmentId != null) {
            params.add(new SearchCriteria("departmentId", "=", departmentId));
        }
        if (departmentName != null) {
            params.add(new SearchCriteria("departmentName", "=", departmentName));
        }
        Page<EmployeeInfoDTO> employeeInfoDTOPage =  employeeService.search(params, pageable);
        PagedModel<EntityModel<EmployeeInfoDTO>> pagedModel = assembler.toModel(employeeInfoDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @Operation(summary = "Cria todos os funcionarios", description = "Cria todos os funcionarios com base em um arquivo Excel",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content)

            })
    @PostMapping(value = "/createAllEmployees", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeInfoDTO>> createAllEmployees(@RequestPart("file") MultipartFile file) throws IOException {
        List<EmployeeInfoDTO> employees = employeeInsertService.readExcelFile(file);
        return ResponseEntity.ok(employees);
    }
}


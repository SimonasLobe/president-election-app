package com.nasdaq.presidentelectionapp;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

   @ApiResponses(value = { @ApiResponse(content = { @Content(mediaType = "application/json",
         array = @ArraySchema(schema = @Schema(implementation = Employee.class))) }) })
   @GetMapping(path = "/{name}/{age}" )
   public ResponseEntity<Employee> getEmploy(@PathVariable String name,
                                             @PathVariable Integer age) {
      Employee employee = new Employee.EmployeeBuilder().name(name).age(age).build();

      return ResponseEntity.ok(employee);
   }

}

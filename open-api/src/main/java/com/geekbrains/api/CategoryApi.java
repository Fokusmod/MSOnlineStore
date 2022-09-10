/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.geekbrains.api;

import com.geekbrains.model.Category;
import com.geekbrains.model.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-04T04:25:54.001546200+03:00[Europe/Moscow]")
@Validated
@Tag(name = "category", description = "Everything about your categories")
public interface CategoryApi {

    default CategoryApiDelegate getDelegate() {
        return new CategoryApiDelegate() {};
    }

    /**
     * GET /category : gettingAllCategories
     *
     * @return OK (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "findAllCategories",
        summary = "gettingAllCategories",
        tags = { "category" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
            }),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/category",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Category>> findAllCategories(
        
    ) {
        return getDelegate().findAllCategories();
    }

}

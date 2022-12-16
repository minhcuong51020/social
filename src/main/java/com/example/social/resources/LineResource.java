package com.example.social.resources;

import com.example.social.dto.request.LineCreateOrUpdateRequest;
import com.example.social.dto.request.LineSearchRequest;
import com.example.social.dto.response.LineResponse;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/social")
public interface LineResource {

    @PostMapping("/api/line")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<LineResponse> create(@RequestBody LineCreateOrUpdateRequest request);

    @PostMapping("/api/line/{id}/update")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<LineResponse> update(@PathVariable("id") String id, @RequestBody LineCreateOrUpdateRequest request);

    @GetMapping ("/api/line/{id}/detail")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<LineResponse> detail(@PathVariable("id") String id);

    @PostMapping ("/api/line/{id}/delete")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<LineResponse> delete(@PathVariable("id") String id);

    @GetMapping ("/api/line")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    PagingResponse<LineResponse> search(LineSearchRequest request);

    @GetMapping ("/api/line/owner")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<LineResponse> findByOwner();

}

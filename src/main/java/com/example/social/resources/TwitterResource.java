package com.example.social.resources;

import com.example.social.dto.request.TwitterCreateOrUpdateRequest;
import com.example.social.dto.request.TwitterSearchRequest;
import com.example.social.dto.response.TwitterResponse;
import com.hmc.common.dto.response.PagingResponse;
import com.hmc.common.dto.response.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/social")
public interface TwitterResource {

    @PostMapping("/api/twitter")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<TwitterResponse> create(@RequestBody TwitterCreateOrUpdateRequest request);

    @PostMapping("/api/twitter/{id}/update")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<TwitterResponse> update(@PathVariable("id") String id,
                                     @RequestBody TwitterCreateOrUpdateRequest request);

    @PostMapping("/api/twitter/{id}/delete")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<TwitterResponse> delete(@PathVariable("id") String id);

    @GetMapping("/api/twitter/{id}/detail")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    Response<TwitterResponse> detail(@PathVariable("id") String id);

    @GetMapping("/api/twitter")
    @PreAuthorize("hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})")
    PagingResponse<TwitterResponse> search(TwitterSearchRequest request);
}

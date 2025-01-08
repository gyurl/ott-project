package com.ott.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ott.project.service.PartyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parties")
public class PartyController {

    private final PartyService partyService;
     private final PartyMatchingService PartyMatchingService;

    //파티 생성
    @PostMapping
    public ResponseEntity<CreatePartyResponse> CreateParty(@RequestBody @Valid CreatePartyRequest request, @AuthenticationPrincipal UserDetails userDetails){
       Long partyId = partyService.CreateParty(request, Long.parseLong(userDetails.getUsername()));
       return ResponseEntity.ok(new CreatePartyResponse(partyId));
    }
}

package com.ott.project.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ott.project.domain.Party;
import com.ott.project.domain.PartyStatus;
import com.ott.project.domain.PaymentInfo;
import com.ott.project.domain.PaymentType;
import com.ott.project.domain.Member;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PartyDto {
    
    //요청 DTO
    public record CreateRequest(
        @NotBlank
        String ottService,
        @Min(2) 
        int memberMax,
        @NotBlank 
        String shareId,
        @NotBlank 
        String sharePassword,
        @Positive 
        int price,
        @NotNull 
        PaymentType paymentType,
        @NotBlank 
        String paymentNumber,
        @NotBlank 
        String bankName,
        String cardExpiry
        ){            
        }

        public record joinRequest(
            @NotNull
            String ottService,
            @NotNull
            PaymentInfo memberPaymentInfo
        ) {
        }

        //응답 DTO
        public record CreateResponse(Long partyId) {
        }

        public record joinResponse(
            boolean matched,
            Long partyId,
            Long waitingQueueId
        ) {
            public static JoinResponse from(JoinResult result){
                return new joinResponse(
                    result.isMathched(),
                    result.getPartyId(),
                    result.getWaitingQueueId()
                );
            }
        }

        public record PartyDetailResponse(
            Long id,
            String ottService,
            int memberMax,
            PartyStatus status,
            BigDecimal partyMemberPay,
            LocalDate partyDay,
            Member partyLeader
        ) {
            public static PartyDetailResponse from(Party party){
                Member partyLeader = party.getPartyLeader();
                return new PartyDetailResponse(
                    party.getId(),
                    party.getOttService(),
                    party.getMemberMax(),
                    party.getStatus(),
                    party.getPartyMemberPay(),
                    party.getPartyDay(),
                    new Member(partyLeader.id(), partyLeader.name(), partyLeader.nickname())
                 );
            }
        

        public record Member(
            Long id,
            String name,
            String nickname
        ) {
            // public Member(Member member){
            //     this(member.getId(),
            //          member.getName(),
            //          member.getNickname());
            // 
            }
        }
}

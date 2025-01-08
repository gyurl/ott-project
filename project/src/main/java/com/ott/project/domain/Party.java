package com.ott.project.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ott_service") //서비스 종류
    private String ottService;

    @Column(name = "member_max") //파티 최대인원
    private int memberMax;

    @Column(name = "share_id") // 공유할 id
    private String shareId;

    @Column(name = "sharepassword") //공유할 비밀번호
    private String sharepassword;

    @Column(name = "party_day")
    private LocalDate partyDay; //파티 생성 기간


    @Column(name = "price") //ott 월 이용료
    private int price;

    @Enumerated(EnumType.STRING) // 파티상태
    private PartyStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_leader_id")
    private Member partyLeader; //파티장 정보

    @Embedded
    private PaymentInfo setpaymentInfo; //정산받을 계좌/카드

    private int paymentDay; //매월 정기 결제일
    private BigDecimal partyMemberPay; //파티원 1인당 분담금

    

    
}


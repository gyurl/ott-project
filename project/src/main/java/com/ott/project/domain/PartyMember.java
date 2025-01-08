package com.ott.project.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PartyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL AUTO
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "join_date") //가입날짜
    private LocalDate joinDate;

    @Column(name = "payment_status") //결제상태
    private boolean paymentStatus;

    @Embedded
    private PaymentInfo memberPaymentInfo; //파티원 결제 정보
}

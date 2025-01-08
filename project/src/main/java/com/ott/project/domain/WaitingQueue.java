package com.ott.project.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WaitingQueue { //대기열
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "ott_service", nullable = false)
    private String ottService; //ott서비스

    @Column(name = "wait_start_time", nullable = false)
    private LocalDateTime waitStartTime; //대기시간
    
    @Embedded
    private PaymentInfo memberPaymentInfo;

    @Builder
    public WaitingQueue(Member member, String ottService, LocalDateTime waitStartTime, PaymentInfo memberPaymentInfo){
        this.member = member;
        this.ottService = ottService;
        this.waitStartTime = waitStartTime;
        this.memberPaymentInfo = memberPaymentInfo;
    }
}

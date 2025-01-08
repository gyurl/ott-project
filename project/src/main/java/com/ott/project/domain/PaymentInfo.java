package com.ott.project.domain;

import com.ott.project.security.StringEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo { //결제 정보

    @Enumerated(EnumType.STRING)
    @Column(nullable = false )
    private PaymentType paymentType; //계좌 or 카드드

    @Column(nullable = false)
    @Convert(converter = StringEncryptor.class) //계좌번호 or 카드번호
    private String paymentNumber;

    @Column(nullable = false) //예금주 or 소유자명
    private String bankName;

    //카드 전용 필드
    @Column
    @Convert(converter = StringEncryptor.class)
    private String cardExpiry;

    @Column
    @Convert(converter = StringEncryptor.class)
    private String cardCvc;
}

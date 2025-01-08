package com.ott.project.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum PaymentType {
    //파티장 정산받을 계좌/카드
    CARD("카드"),
    ACCOUNT("계좌");

    private final String description;
}

package com.ott.project.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum PartyStatus { //파티 상태
    RECRUITING("모집중"),
    FULL("모집완료"),
    CLOSED("종료됨");

    private final String description;
}

package com.football.api.demo.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EnlistRequest {
    private int playerId;
    private String clubCode;
}

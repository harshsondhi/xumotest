package com.sondhi.xumotest.xumotest.model;

import lombok.*;

import java.util.Date;

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Campaign {
    Long campaignid;
    Date startdate;
}

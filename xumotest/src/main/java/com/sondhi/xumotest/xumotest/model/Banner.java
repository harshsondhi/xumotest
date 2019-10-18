package com.sondhi.xumotest.xumotest.model;


import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banner {
    Long campaignid;
    Long bannerid;
    String bannerurl;
}

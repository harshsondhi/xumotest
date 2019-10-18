package com.sondhi.xumotest.xumotest.controllers;

import com.sondhi.xumotest.xumotest.model.Banner;
import com.sondhi.xumotest.xumotest.model.Campaign;
import com.sondhi.xumotest.xumotest.service.BannerService;
import com.sondhi.xumotest.xumotest.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/xumoapi")
public class XumoTestController {

    private final BannerService bannerServivce;
    private final CampaignService campaignService;

    @Autowired
    XumoTestController (BannerService bannerServivce, CampaignService campaignService){
        this.bannerServivce = bannerServivce;
        this.campaignService = campaignService;
    }



    @GetMapping( value="/banners/{campaignid}")
    public List<Banner> getCBanners(@PathVariable long campaignid ){
        List<Banner> banners = bannerServivce.findAll();
        List<Banner> resulB = banners.stream().filter(n -> n.getCampaignid() == campaignid).collect(Collectors.toList());
        return resulB;
    }

    @GetMapping( value="/campaigns/{campaignid}")
    public List<Campaign> getCampaigns(@PathVariable long campaignid ){
        List<Campaign> campaigns =campaignService.findAll();
        List<Campaign> resultA  = campaigns.stream().filter(n -> n.getCampaignid() == campaignid).collect(Collectors.toList());
        return resultA;
    }
}

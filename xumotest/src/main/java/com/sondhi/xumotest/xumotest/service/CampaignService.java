package com.sondhi.xumotest.xumotest.service;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sondhi.xumotest.xumotest.model.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"campaigns"})
public class CampaignService {
    private List<Campaign> campaigns = new ArrayList<>();

    @Autowired
    CampaignService() {}

    @PostConstruct
    private void fillCompaigns(){

        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();
            Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("campaign.csv").toURI()));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
               // System.out.println(line[0] + " -to- " + line[1]);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                //Date date = fmt.parse(line[1]);
                //System.out.println(date.toString());
                campaigns.add(Campaign.builder().campaignid(Long.valueOf(line[0])).startdate(fmt.parse(line[1])).build());
                //System.out.println("HARSH============@@@@@@@@@@@@@@@@@" + campaigns.size());
            }
            System.out.println("HARSH  CampaignService ============@@@@@@@@@@@@@@@@@" + campaigns.size());
            reader.close();
            csvReader.close();
        }catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }

    }


    @Cacheable
    public List<Campaign> findAll(){
        return this.campaigns;
    }


}

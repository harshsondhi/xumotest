package com.sondhi.xumotest.xumotest;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sondhi.xumotest.xumotest.model.Campaign;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableCaching
public class XumotestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(XumotestApplication.class, args);
		Campaign t = new Campaign();
		t.setCampaignid(1L);
		Campaign c = Campaign.builder().campaignid(2l).startdate(new Date()).build();
		System.out.println(c.toString());
	}

   @Override
    public void run(String... args) throws Exception {
	    System.out.println("Harsh...............................@@@@@@@@@@@@@@@@@@@@@");

	   campaignFileInMemory("");
	   bannerFileInMemory("");
   }

   //Assuming  file is in resources
    private void campaignFileInMemory(String fileName) throws URISyntaxException, IOException, ParseException {
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(',')
				.withIgnoreQuotations(true)
				.build();
		Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("campaign.csv").toURI()));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
		String[] line;
		while((line = csvReader.readNext()) != null) {
                  System.out.println(line[0] + " -to- " + line[1]);
                 SimpleDateFormat fmt = new  SimpleDateFormat("yyyy-MM-dd");
                 Date date = fmt.parse(line[1] );
                 System.out.println(date.toString());
		}
		reader.close();
        csvReader.close();
		//CSVReader reader = new CSVReader(new FileReader("data.csv"), ',' , '"' , 1);
	}

	private void bannerFileInMemory(String fileName) throws URISyntaxException, IOException, ParseException {
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(',')
				.withIgnoreQuotations(true)
				.build();
		Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("banner.csv").toURI()));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
		String[] line;
		while((line = csvReader.readNext()) != null) {
			System.out.println(line[0] + " -to- " + line[1] + " -to- "  + line[2]);

		}
		reader.close();
		csvReader.close();
		//CSVReader reader = new CSVReader(new FileReader("data.csv"), ',' , '"' , 1);
	}


}

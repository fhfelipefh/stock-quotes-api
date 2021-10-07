package br.com.fhfelipefh.stockquotesapi;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Log4j2
@EnableScheduling
@SpringBootApplication
public class StockQuotesApiApplication {

    private final QuoteRepository quoteRepository;

    public StockQuotesApiApplication(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StockQuotesApiApplication.class, args);
    }

    @Scheduled(fixedDelay = 1000)
    public void generateData() {
        log.info(quoteRepository.findFirstBySymbolOrderByTimestampDesc("TESTE")
                .map(this::generateNewData)
                .orElseGet(this::initializeData));
    }

    private Quote initializeData() {
        return quoteRepository.save(Quote.builder()
                .symbol("TESTE")
                .openValue(0.555)
                .closeValue(0.555)
                .timestamp(new Date())
                .build());
    }

    private Quote generateNewData(Quote quote) {
        return quoteRepository.save(Quote.builder()
                .symbol(quote.getSymbol())
                .openValue(quote.getOpenValue() * new RandomDataGenerator().nextUniform(-0.15, 0.50))
                .closeValue(quote.getCloseValue() * new RandomDataGenerator().nextUniform(-0.15, 0.60))
                .timestamp(new Date())
                .build());

    }


}

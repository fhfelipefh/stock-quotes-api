package br.com.fhfelipefh.stockquotesapi;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QUOTE")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private Double openValue;

    private Double closeValue;

    private Date  timestamp;


}

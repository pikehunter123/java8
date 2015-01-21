/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8;

import biz.c24.io.api.C24;
import biz.c24.trade.Trade;
import biz.c24.trade.Trades;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author kononov446
 */
public class Java8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Stream<String> currencies = Stream.of("GBP", "EUR", "USD", "CAD", "AUD", "JPY", "HKD" );
            currencies
                    .filter(c->c.contains("A"))
                    .forEach( System.out::println );
            
            
            Trades tradeData = C24.parse(Trades.class).from(new File("TradesFile2.csv"));
            ArrayList<Trade> tradesList = new ArrayList<>(Arrays.asList(tradeData.getTrade()));
            Stream<Trade> tradeStream = tradesList.stream(); 
            /*tradeStream
                    //.filter( t -> t.getId()== 9 )
                    .sorted( Comparator.comparing(Trade::getBuySell) )
                    .forEach(System.out::print);*/
            
          //  tradeStream.forEach(z-> System.out.println(BigDecimal.valueOf(Long.parseLong(z.getAmount1().replace(",", ""))).multiply(BigDecimal.TEN) ));

            /*boolean match = tradeStream
                    .allMatch(t -> BigDecimal.valueOf(Long.parseLong(t.getAmount1().replace(",", ""))).multiply(BigDecimal.valueOf(t.getExchangeRate()))
              .compareTo(BigDecimal.valueOf(Long.parseLong(t.getAmount2().replace(",", "")))) == 0);
            
System.out.println("allMatch = " + match); */
/*
boolean cou=tradeStream
                    .noneMatch(t -> BigDecimal.valueOf(Long.parseLong(t.getAmount1().replace(",", ""))).multiply(BigDecimal.valueOf(t.getExchangeRate()))
              .compareTo(BigDecimal.valueOf(Long.parseLong(t.getAmount2().replace(",", "")))) == 0);
System.out.println("noMatch = " + cou);*/

      /*      tradeStream = Stream.generate(() -> {
       return createTrade();
}); 

tradeStream
       .limit(100)
       .forEach(System.out::print); */
            
            
       /*     Map<String, Long> map = tradeStream
       .limit(1_000_000)
       .collect(Collectors.groupingBy(t -> t.getCurrency1() + "/" + t.getCurrency2(),
              Collectors.counting())); 

System.out.println("map = " + map); */
            
 Map<String, Long> map = tradeStream
       .limit(1_000_000)
        .peek(t-> occasionallyDebug(t))
       .collect(Collectors.groupingBy(t -> t.getCurrency1() + "/" + t.getCurrency2(),
              Collectors.counting())); 

System.out.println("map = " + map);          
            





            

        } catch (IOException ex) {
            Logger.getLogger(Java8.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    private static void occasionallyDebug( Trade trade ) {
       //if( trade.getId() % 100_000 == 0 ) 
       {
              System.out.print("DEBUG: " + trade);
       }
}
    
}

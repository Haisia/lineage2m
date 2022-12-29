package prac.lineage2m.lineage2m.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import prac.lineage2m.lineage2m.dto.ItemDto;
import prac.lineage2m.lineage2m.dto.ItemSearchDto;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * ref : https://gngsn.tistory.com/154
 * <p>
 * API 호출 예)
 * https://dev-api.plaync.com/
 * l2m/v1.0/market/items/search
 * ?search_keyword=%ED%95%B8%EB%93%9C%20%EC%98%A4%EB%B8%8C%20%EC%B9%B4%EB%B8%8C%EB%A6%AC%EC%98%A4
 * &sale=false
 * &page=1&size=10
 */
public class ItemSearchRepositoryImpl implements ItemSearchRepository {

  public void getItemStocks() throws IOException {
    String urlString = "https://dev-api.plaync.com/l2m/v1.0/market/items/search?search_keyword=%ED%95%B8%EB%93%9C%20%EC%98%A4%EB%B8%8C%20%EC%B9%B4%EB%B8%8C%EB%A6%AC%EC%98%A4&sale=false&page=1&size=10";
    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    ObjectMapper mapper = new ObjectMapper();

    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    conn.setRequestProperty("Authorization", "Bearer eyJraWQiOiI2YWFmYzEzZi1hMGJjLTQ1YjYtYTUyMS00YTAyMGUzMTljYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiI3MERFOTg3NS01OEZCLTQ0RTYtOEQ2MS0yNTMxQTU4REUzQTIifQ.aYZQcKmLGAW0-y-XE6xnOAy0q77w5MQUWPDCpErsHz8P8neb6VagevXAyke9quon7MqTpa5qufjIn8zJl2POGuBx-epQ2qKz-nBixSYyuxExOr8RnFJVROYHOoJ2X9xWsIkIrFi0O3dESSZvWOxEXi2KvFnoBAoKoqf7XA3CGBEjkCsHytbPOilwypE0AXvhaasglzUiYVzeUDyTKdn7h9SedVq-jmnvdzsOs-tCIlUvKesKLg1kFVy7_inipXWHuQrTtAtSVkN4-O2RtG_Pocl2wMHYBrOawxPbttS8ac35kMxzPPp7MkxsW6Krz6SVfGzsHJ0CCBIgHElyDzPIeQ");

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuilder response = new StringBuilder();

    String readLine;
    while ((readLine = in.readLine()) != null) {
      response.append(readLine);
    }
    in.close();

    String responseJson = response.toString();
    System.out.println(responseJson);
    ItemSearchDto itemSearchDto = mapper.readValue(responseJson, ItemSearchDto.class);
    System.out.println("itemSearchDto = " + itemSearchDto);
  }
  public List<ItemDto> getItemStocksToItemDto() throws IOException {



    String authKey = "Bearer eyJraWQiOiI2YWFmYzEzZi1hMGJjLTQ1YjYtYTUyMS00YTAyMGUzMTljYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiI3MERFOTg3NS01OEZCLTQ0RTYtOEQ2MS0yNTMxQTU4REUzQTIifQ.aYZQcKmLGAW0-y-XE6xnOAy0q77w5MQUWPDCpErsHz8P8neb6VagevXAyke9quon7MqTpa5qufjIn8zJl2POGuBx-epQ2qKz-nBixSYyuxExOr8RnFJVROYHOoJ2X9xWsIkIrFi0O3dESSZvWOxEXi2KvFnoBAoKoqf7XA3CGBEjkCsHytbPOilwypE0AXvhaasglzUiYVzeUDyTKdn7h9SedVq-jmnvdzsOs-tCIlUvKesKLg1kFVy7_inipXWHuQrTtAtSVkN4-O2RtG_Pocl2wMHYBrOawxPbttS8ac35kMxzPPp7MkxsW6Krz6SVfGzsHJ0CCBIgHElyDzPIeQ";
    String urlString = "https://dev-api.plaync.com/l2m/v1.0/market/items/search?search_keyword=%ED%95%B8%EB%93%9C%20%EC%98%A4%EB%B8%8C%20%EC%B9%B4%EB%B8%8C%EB%A6%AC%EC%98%A4&sale=false&page=1&size=10";
    ObjectMapper mapper = new ObjectMapper();

    WebClient client = WebClient.builder()
            .baseUrl(urlString)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Authorization", authKey)
            .build();

    System.out.println("555555555555555555"+client.head().header("Authorization"));

    Mono<Object> response = client.get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Object.class).log();
    Object object = response.block();

    ItemSearchDto itemSearchDto = mapper.convertValue(object, ItemSearchDto.class);

    System.out.println("########################################");
    System.out.println("itemSearchDto = " + itemSearchDto);
    System.out.println("itemSearchDto.getContents() = " + itemSearchDto.getContents());
    System.out.println("itemSearchDto.getPagination() = " + itemSearchDto.getPagination());
    System.out.println(itemSearchDto.getPagination().toString());

    for (ItemDto content : itemSearchDto.getContents()) {
      System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      System.out.println(content.toString());
    }





//    return Arrays.stream(objects)
//            .map(itemDto -> mapper.convertValue(itemDto, ItemDto.class))
//            .collect(Collectors.toList());
    return null;

//    return response.block();
  }

}

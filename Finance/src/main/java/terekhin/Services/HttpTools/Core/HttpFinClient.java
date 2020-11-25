package terekhin.Services.HttpTools.Core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import terekhin.Services.Mapping.Currency;
import terekhin.Services.Variables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class HttpFinClient {

   public Currency getExchangeRates(LocalDate date) throws Exception {

      try {
         DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
         URL url = new URL(Variables.API_URL + "/exchange_rates?json&date=" + date.toString(fmt));
         HttpURLConnection http = (HttpURLConnection) url.openConnection();
         http.setRequestProperty("Content-Type","application/json");
         InputStream is = http.getInputStream();
         try  {


            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            Currency c = mapper(strBuf, Currency.class);
            return c;

         }finally {
            is.close();
         }

      }catch(IOException e)
      {
         throw new Exception("HttpTools: GetData Error:"+ e.getMessage());
      }
   }

   private  <T> T mapper(String buf, Class<T> type) throws IOException {

      try {
         ObjectMapper _mapper = new ObjectMapper();
         _mapper.registerModule(new JavaTimeModule());
         return _mapper.readerFor(type).<T>readValue(buf);
      }catch(IOException ex)
      {
         System.out.println(buf);
         throw new IOException(ex.getMessage());
      }
   }
   private byte[] responseBodyToArray(InputStream is) throws IOException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] buf = new byte[10240];
      int r;

      do {
         r = is.read(buf);
         if (r > 0) bos.write(buf, 0, r);
      } while (r != -1);

      return bos.toByteArray();
   }
}

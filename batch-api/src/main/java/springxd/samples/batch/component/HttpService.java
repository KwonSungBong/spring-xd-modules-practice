package springxd.samples.batch.component;

import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

    private final OkHttpClient okHttpClient = new OkHttpClient();
//    private final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    private final MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    public String requestGet(String url) throws Exception {
        Request request = new Request.Builder()
//                .header("Authorization", Credentials.basic("", ""))
                .url(url)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
    public String requestPost(String url, String bodyContent) throws Exception {
        RequestBody body = RequestBody.create(mediaType, bodyContent);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}

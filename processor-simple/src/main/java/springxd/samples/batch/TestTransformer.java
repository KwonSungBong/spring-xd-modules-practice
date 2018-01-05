package springxd.samples.batch;

import org.springframework.stereotype.Service;

/**
 * Created by ksb on 2018. 1. 1..
 */
@Service
public class TestTransformer {

    public String transform(String payload) {
        return "test" + payload;
    }

}

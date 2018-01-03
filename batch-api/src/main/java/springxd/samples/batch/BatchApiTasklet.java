package springxd.samples.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springxd.samples.batch.component.HttpService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ksb on 2018. 1. 1..
 */
@Service
public class BatchApiTasklet implements Tasklet, StepExecutionListener {

    @Autowired
    HttpService httpService;

    @Value("${test}")
    private String test;

    @Value("${testtest}")
    private String testtest;

    public BatchApiTasklet() {
        super();
    }

    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {

        final JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        List<String> urlList = new ArrayList<>();

        if (jobParameters != null && !jobParameters.isEmpty()) {

            final Set<Map.Entry<String, JobParameter>> parameterEntries = jobParameters.getParameters().entrySet();

            System.out.println(String.format("The following %s Job Parameter(s) is/are present:", parameterEntries.size()));

            for (Map.Entry<String, JobParameter> jobParameterEntry : parameterEntries) {
                System.out.println(String.format(
                        "Parameter name: %s; isIdentifying: %s; type: %s; value: %s",
                        jobParameterEntry.getKey(),
                        jobParameterEntry.getValue().isIdentifying(),
                        jobParameterEntry.getValue().getType().toString(),
                        jobParameterEntry.getValue().getValue()));

                if (jobParameterEntry.getKey().startsWith("url")) {
                    urlList.add(jobParameterEntry.getValue().getValue().toString());
                }
            }
        }

        if(urlList.size() == 0) {
            String url = "https://www.google.co.kr";
            String result = httpService.requestGet(url);
            System.out.println("result : " + result);
        } else {
            for(String url : urlList) {
                String result = httpService.requestGet(url);
                System.out.println("result : " + result);
            }
        }

        System.out.println("test : " + test);
        System.out.println("testtest : " + testtest);

        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // To make the job execution fail, set the step execution to fail
        // and return failed ExitStatus
        // stepExecution.setStatus(BatchStatus.FAILED);
        // return ExitStatus.FAILED;
        return ExitStatus.COMPLETED;
    }

}
